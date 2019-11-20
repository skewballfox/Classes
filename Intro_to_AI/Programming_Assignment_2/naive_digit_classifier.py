import numpy as np
import json
import logging
import sys
import matplotlib.pyplot as plt


class naive_bayes_classifier():
    def __init__(self,metadata_location,debug=False,quickrun=True):
        #set up logging
        if debug==True:
            logging.basicConfig(level=logging.DEBUG, filename='naive_bayes_classifier.log', format='%(asctime)s %(levelname)s: %(message)s')
        else:
            logging.basicConfig(level=logging.INFO, filename='naive_bayes_classifier.log',format='%(asctime)s %(levelname)s: %(message)s')
        self.logger=logging.getLogger(__name__)
        
        #get the metadata and set the approppriate variables
        metadata=self.get_metadata(metadata_location)
        
        self.logger.debug("metadata: %s", metadata)

        #This is a list of classification objects, their properties
        #are related to the probabilies of the features that compose them
        self.digits=[digit_class(digit,self.logger,debug) for digit in range(10)]
        
        self.training_sample_size=metadata["training_sample_size"]
        self.test_sample_size=metadata["test_sample_size"]
        

        self.confusion_matrix=np.zeros([10,10])
        self.classification_rate=np.zeros([10,2])

        #using this to store the prototypicals
        self.image_buffer=""

        #unless otherwise specified begin training and testing
        if quickrun==True:
            self.train_model(metadata["training_data"],metadata["training_labels"])
            self.test_model(metadata["test_data"],metadata["test_labels"])
            self.evaluate_model()

    """ 
    General Functions
    """

    def get_metadata(self,file):
            try:
                with open(file, encoding='utf8') as data_file:
                    data=json.loads(data_file.read())
                    return data
            except FileNotFoundError:
                self.logger.error("metadata file %s not found", file)
                print("DON'T PANIC! Check the logs")
                quit()
            except json.decoder.JSONDecodeError:
                self.logger.error("%s doesn't seem to be properly formatted", file)
                print("DON'T PANIC! Check the logs")
                quit()

    def get_label(self,label_file):
        """get_label(label_file)
        grabs the next label from a data file
        """
        raw_data=label_file.readline()
        value=int(raw_data)
        return value
    
    """
    Model Functions for Training phase
    """ 
    def train_model(self,training_data,training_labels):
        try: 
            with open(training_data,'r') as data: 
                try:
                    with open(training_labels,'r') as labels:
                        
                        for k in range(self.training_sample_size):
                            label=self.get_label(labels)
                            self.digits[label].get_image(data)

                except FileNotFoundError:
                    self.logger.error("Training label file %s not found", training_labels)
        except FileNotFoundError:
            self.logger.error("Training data file %s not found", training_data)


        for digit in self.digits:
            #let each digit class know that no more data will be added
            digit.finish_training(self.training_sample_size)
    
    """
    Model Functions for Testing phase
    """
    def test_model(self,test_data,test_labels):
        self.correct_count=0
        try:
            with open(test_data,'r') as data:
                try:
                    with open(test_labels,'r') as labels:
                        for k in range(self.test_sample_size):
                            self.classify(data,labels)
                except FileNotFoundError:
                    self.logger.error("Test label file %s not found", test_labels)
        except FileNotFoundError:
            self.logger.error("Test data file %s not found", test_labels)
        
    def classify(self,data,labels):
        label=self.get_label(labels)
        self.classification_rate[label,1]+=1
        estimate=self.map_estimation(data)
        if estimate==label:
            self.classification_rate[label,0]+=1
            self.correct_count+=1
        else:
            self.confusion_matrix[label,estimate]+=1
        #check to see if this had the highest or lowest posterior probability
        if self.digits[estimate].check_prototypicals()==True:
            self.digits[estimate].set_prototypicals(label,self.image_buffer)
    
    def map_estimation(self,test_file):
        """
        NOTE Potentially add a image count in order to debug issues with data being improperly formated
        """
        probs=[]
        self.image_buffer=""
        for i in range(28):
            self.image_buffer+='\n'
            raw_data=test_file.readline()
            for j in range(28):
                try:
                    if raw_data[j]!=' ':
                        pixel=1
                        self.image_buffer+='#'
                    else:
                        pixel=0
                        self.image_buffer+=' '
                    
                    for digit in self.digits:
                        digit.update_map(i,j,pixel)
                except IndexError:
                    self.logger.error("check the map estimation for issues with iterating through each pixel")
        for digit in self.digits:
            probs.append(digit.get_posterior_probability())
            
        self.logger.debug("the list of posterior probabilities %s", probs)
        return np.argmax([x.get_posterior_probability() for x in self.digits])

    """
    Model Functions for Evaluation phase
    """
            
    def evaluate_model(self):
        self.logger.info("got  {} out of {} correct".format(self.correct_count, self.test_sample_size))
        for digit in self.digits:
            digit.evaluate()
        self.log_classification_rates()
        self.log_confusion_rates()


    def log_classification_rates(self):
        for i in range(10):
            self.logger.info("classification of {} had a success rate of {}".format(i, self.classification_rate[i,0]/self.classification_rate[i,1]))

    def log_confusion_rates(self):
        most_confused_digits=[]
        most_confused_counts=[0]    
        for i in range(10):
            for j in range(10):
                if j!=i:
                    if self.confusion_matrix[i,j]!=0:
                        for k in range(-1,-5,-1):
                            try:
                                if self.confusion_matrix[i,j]>most_confused_counts[k]:
                                    if i==-1:
                                        most_confused_counts.append(self.confusion_matrix[i,j])
                                        most_confused_digits.append((i,j))
                                    else:
                                        most_confused_counts.insert(k,self.confusion_matrix[i,j])
                                        most_confused_digits.insert(k,(i,j))
                            except IndexError:
                                break

                    self.logger.info("classification of {} was confused for {} {} times".format(i,j, self.confusion_matrix[i,j]))
        self.plot_images(most_confused_digits[-4:])
        self.logger.info("check generated pdf for plots of most confused images")

    def plot_images(self,confusing_digits):
        figure = plt.figure()#subplots(nrows=4,ncols=3)
        axis=[]
        rows,columns=4,3
        for pair in confusing_digits:
            self.logger.info("generating heat maps and odds ratio for {} and {}".format(pair[0],pair[1]))
            image_1=np.log(self.digits[pair[0]].likely_image[:,:,1])
            image_2=np.log(self.digits[pair[1]].likely_image[:,:,1])
            odds_ratio=image_1-image_2
            for image in (image_1,image_2,odds_ratio):
                axis.append(figure.add_subplot(rows,columns,len(axis)+1))
                plt.imshow(image,cmap=plt.get_cmap('viridis'),interpolation='nearest')

        plt.savefig("most_confused_images.pdf")

class digit_class():
    def __init__(self,identifier,logger,debug=False):
        self.name=identifier
        self.logger=logger
        self.pixel_counts=np.zeros([28,28,2])
        self.image_count=0
        self.posterior_probability=0
        self.debug=debug

        #this is stuff for determining the most and least
        #prototypical images from the test data
        self.lowest_post_prob=sys.float_info.max
        self.highest_post_prob=-self.lowest_post_prob-1
        self.new_highest=False
        self.new_lowest=False
        self.least_prototypical=''
        self.most_prototypical=''
    
    """
    Classification Functions for Training Phase
    """
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
    
    def set_empirical_frequency(self,sample_size):
        self.empirical_frequency=self.image_count/sample_size
    
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
            image_buffer=self.compose_image(self.pixel_counts)
            self.log_image("counts",image_buffer)
            image_buffer=self.compose_image(self.likely_image)
            self.log_image("likelys",image_buffer)
    
    
    def finish_training(self,sample_size):
        self.set_empirical_frequency(sample_size)
        self.posterior_probability=abs(np.log(self.empirical_frequency))
        self.set_likely_image(sample_size)
    
    """
    Classification Functions for Testing phase
    """
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
    
    def check_prototypicals(self):
        check_flag=False
        if self.posterior_probability<self.lowest_post_prob:
            self.new_lowest=True
            self.lowest_post_prob=self.posterior_probability
            check_flag=True
        if self.posterior_probability>self.highest_post_prob:
            self.new_highest=True
            self.highest_post_prob=self.posterior_probability
            check_flag=True
        return check_flag
    
    def set_prototypicals(self,label,image_buffer):
        if self.new_lowest==True:
            self.least_proto_label=label
            self.least_prototypical=image_buffer
            self.new_lowest=False
        if self.new_highest==True:
            self.most_proto_label=label
            self.most_prototypical=image_buffer
            self.new_highest=False

    """
    Classification Functions for Evaluation phase
    """
    
    def compose_image(self,matrix):
        """
        renders binary images in terminal, works with any N*N binary array
        TODO:figure out how to put this in a log file
        """
        image_buffer=""
        current_pixel=0
        for i in range(28):
            image_buffer+="\n"
            for j in range(28):
                current_pixel=np.argmax([x for x in matrix[i,j]])
                if current_pixel==0:
                    image_buffer+=' '
                else:
                    image_buffer+='#'
        return image_buffer
    
    def get_pixel_probability(self,i,j,f):
        return self.likely_image[i,j,f]
    
    def log_image(self,case, image_buffer):
        if case=="counts":
            self.logger.debug("image composed of counts for {} classification:\n{}".format(self.name,image_buffer))
        elif case=="likelys":
            self.logger.debug("image composed of likely pixel values for {} classification:\n{}".format(self.name,image_buffer))
        elif case=="least_proto":
            self.logger.info("least prototypical image from test data for {} classification:\n{}".format(self.name,image_buffer))
            self.logger.info(" actual label of this image: {}".format(self.least_proto_label))
        elif case=="most_proto":
            self.logger.info("most prototypical image from test data for {} classification:\n{}".format(self.name,image_buffer))
            self.logger.info(" actual label of this image: {}".format(self.most_proto_label))

            
    def evaluate(self):
        self.log_image('least_proto',self.least_prototypical)
        self.log_image('most_proto',self.most_prototypical)

    
    

if __name__=="__main__":
    classifier=naive_bayes_classifier('metadata.json')