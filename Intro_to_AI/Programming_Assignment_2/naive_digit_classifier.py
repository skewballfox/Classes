import numpy as np
import json
import logging


class naive_bayes_classifier():
    def __init__(self,metadata_location,debug=False,quickrun=True):
        #set up logging
        if debug==True:
            logging.basicConfig(level=logging.DEBUG, filename='naive_bayes_classifier.log', format='%(asctime)s %(levelname)s: %(message)s')
        else:
            logging.basicConfig(level=logging.INFO, filename='naive_bayes_classifier.log',format='%(asctime)s %(levelname)s: %(message)s')
        self.logger=logging.getLogger(__name__)
        
        #get the metadata and set the appropriate variables
        metadata=self.get_metadata(metadata_location)
        
        self.logger.debug("metadata: %s", metadata)

        #This is a list of classification objects, their properties
        #are related to the probabilies of the features that compose them
        self.digits=[digit_class(digit,debug) for digit in range(10)]
        
        self.training_sample_size=metadata["training_sample_size"]
        self.test_sample_size=metadata["test_sample_size"]
        

        self.confusion_matrix=np.zeros([10,10])
        self.classification_rate=np.zeros([10,2])

        #unless otherwise specified begin training and testing
        if quickrun==True:
            self.train_model(metadata["training_data"],metadata["training_labels"])
            self.test_model(metadata["test_data"],metadata["test_labels"])



    def get_metadata(self,file):
            try:
                with open(file, encoding='utf8') as data_file:
                    data=json.loads(data_file.read())
                    return data
            except FileNotFoundError:
                self.logger.critical("metadata file %s not found", file)
                print("DON'T PANIC! Check the logs")
                quit()
            except json.decoder.JSONDecodeError:
                self.logger.critical("%s doesn't seem to be properly formatted", file)
                print("DON'T PANIC! Check the logs")
                quit()

    def get_label(self,label_file):
        """get_label(label_file)
        grabs the next label from a data file
        """
        raw_data=label_file.readline()
        value=int(raw_data)
        return value
    
    def train_model(self,training_data,training_labels):
        with open(training_data,'r') as data: 
            with open(training_labels,'r') as labels:
                for k in range(self.training_sample_size):
                    label=self.get_label(labels)
                    self.digits[label].get_image(data)
        for digit in self.digits:
            #let each digit class know that no more data will be added
            digit.finish_training(self.training_sample_size)

    def test_model(self,test_data,test_labels):
        self.correct_count=0
        try:
            with open(test_data,'r') as data:
                try:
                    with open(test_labels,'r') as labels:
                        for k in range(self.test_sample_size):
                            label=self.get_label(labels)
                            self.classification_rate[label,1]+=1
                            estimate=self.map_estimation(data)
                            if estimate==label:
                                self.classification_rate[label,0]+=1
                                self.correct_count+=1
                            else:
                                self.confusion_matrix[label,estimate]+=1
                except FileNotFoundError:
                    self.logger.critical("Test label file %s not found", test_labels)
        except FileNotFoundError:
            self.logger.critical("Test data file %s not found", test_labels)
        self.logger.info("got  {} out of {} correct".format(self.correct_count, self.test_sample_size))
        self.print_classification_rate()

    def map_estimation(self,test_file):
        """
        NOTE Potentially add a image count in order to debug issues with data being improperly formated
        """
        probs=[]
        for i in range(28):
            raw_data=test_file.readline()
            for j in range(28):
                try:
                    if raw_data[j]!=' ':
                        pixel=1
                    else:
                        pixel=0
                    for digit in self.digits:
                        digit.update_map(i,j,pixel)
                except IndexError:
                    self.logger.critical("check the map estimation for issues with iterating through each pixel")
        for digit in self.digits:
            probs.append(digit.get_posterior_probability())
            
        self.logger.debug("the list of posterior probabilities %s", probs)
        return np.argmax([x.get_posterior_probability() for x in self.digits])
    
    def print_classification_rate(self):
        for i in range(10):
            self.logger.info("classification of {} had a success rate of {}".format(i, self.classification_rate[i,0]/self.classification_rate[i,1]))
        for i in range(10):
            for j in range(10):
                if j!=0:
                    if self.confusion_matrix[i,j]!=0:
                        self.logger.info("classification of {} was confused for {} {} times".format(i,j, self.confusion_matrix[i,j]))
    
    

class digit_class():
    def __init__(self,identifier,debug=False):
        self.name=identifier
        self.pixel_counts=np.zeros([28,28,2])
        self.image_count=0
        self.posterior_probability=0
        self.debug=debug
    
    def get_image(self, training_data):
        
        self.image_count+=1
        for i in range(28):
            raw_data=training_data.readline()
            for j in range(len(raw_data)):
                if raw_data[j]!='\n':
                    if raw_data[j]!=' ':
                        self.pixel_counts[i,j,1]+=1
                    else:
                        self.pixel_counts[i,j,0]+=1
    
    def set_likely_image(self,sample_size):
        self.likely_image=np.zeros([28,28,2])
        for i in range(28):
            for j in range(28):
                    for p in range(2):
                        #NOTE:
                        #Additive smoothing is a type of shrinkage estimator, 
                        # as the resulting estimate will be between the empirical 
                        # estimate xi / N, and the uniform probability 1/d
                        # I'm going to ballpark it and use the priors as k
                        laplace = self.pixel_counts[i,j,p] + self.empirical_frequency
                        smoothing = self.image_count + self.empirical_frequency*2
                        self.likely_image[i,j,p] = laplace / smoothing
        if self.debug==True:
            print("image composed of counts")
            self.print_image('counts')
            print("image composed of probabilities of the pixels after calculating probablity with additive smoothing:")
            self.print_image('likelys')
    
    def print_image(self,case='counts'):
        """
        renders binary images in terminal, works with any N*N binary array
        TODO:figure out how to put this in a log file
        """
        print()
        current_pixel=0
        for i in range(28):
            print()
            for j in range(28):
                if case=='counts':
                    current_pixel=np.argmax([x for x in self.pixel_counts[i,j]])
                elif case=='likelys':
                    current_pixel=np.argmax([x for x in self.likely_image[i,j]])
                if current_pixel==0:
                    print(' ',end='')
                else:
                    print('#',end='')
        print()
    
    def set_empirical_frequency(self,sample_size):
        self.empirical_frequency=self.image_count/sample_size
    
    def finish_training(self,sample_size):
        self.set_empirical_frequency(sample_size)
        self.posterior_probability=abs(np.log(self.empirical_frequency))
        self.set_likely_image(sample_size)

    def update_map(self,i,j,pixel):
        """
        NOTE the log of class has already been added to the posterior probability at the end
        of the finish training function 
        """
        if i==0 and j==0:
            self.posterior_probability=np.log(self.empirical_frequency)
        self.posterior_probability+=np.log(self.likely_image[i,j,pixel])        

    def get_posterior_probability(self):
        return self.posterior_probability
    
 
    

if __name__=="__main__":
    classifier=naive_bayes_classifier('metadata.json')