import numpy as np
import json
import logging


class naive_bayes_classifier():
    def __init__(self,metadata):

        self.digits=[digit_class(digit) for digit in range(10)]
        self.training_sample_size=metadata["training_sample_size"]
        self.test_sample_size=metadata["training_sample_size"]
        self.train_model(metadata["training_data"],metadata["training_labels"])
        self.test_model(metadata["test_data"],metadata["test_labels"])
        self.command_list=['map']

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
        with open(test_data,'r') as data: 
            with open(test_labels,'r') as labels:
                for k in range(self.test_sample_size):
                    label=get_label(labels)
                    estimate=self.map_estimation(data)
                    if estimate==label:
                        print("woop")
                        self.correct_count+=1
                    else:
                        print("oops")
                    print(label,' ',estimate)
                    print()
        print("got  {} out of {} correct".format(self.correct_count, self.test_sample_size))

    def map_estimation(self,test_file):
        """

        """
        probs=[]
        #print(len(digits))
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
                    print("somethings fishy: ",j)
                    quit()
        for digit in self.digits:
            probs.append(digit.get_posterior_probability())


        return np.argmax([x.get_posterior_probability() for x in self.digits])

    def Publish(self,command):
        if command in self.command_list:
            for classification in self.digits:
                classification.update(command)
        else:
            print("Not a valid command")
    
class digit_class():
    def __init__(self,identifier,training_data_count=5000):
        #self.images=[]
        self.name=identifier
        self.pixel_counts=np.zeros([28,28,2])
        self.image_count=0
        self.posterior_probability=0
    
    def get_image(self, training_data):
        
        self.image_count+=1
        for i in range(28):
            raw_data=training_data.readline()
            for j in range(len(raw_data)):
                if raw_data[j]!='\n':
                    if raw_data[j]!=' ':
                        #image[i,j]=1
                        self.pixel_counts[i,j,1]+=1
                    else:
                        #image[i,j]=0
                        self.pixel_counts[i,j,0]+=1
        #self.images.append(image)
    
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
                        laplace = self.pixel_counts[i,j,p] + 1#self.empirical_frequency
                        smoothing = self.image_count + 1#self.empirical_frequency*2
                        self.likely_image[i,j,p] = laplace / smoothing
        #self.print_image('counts')
        #self.print_image('likelys')
    
    def print_image(self,case='counts'):
        """
        renders binary images in terminal, works with any N*N binary array
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
        print(self.empirical_frequency)
        self.set_likely_image(sample_size)

    def update_map(self,i,j,pixel):
        """
        NOTE the log of class has already been added to the posterior probability at the end
        of the finish training function 
        """
        if i==0 and j==0:
            self.posterior_probability=abs(np.log(self.empirical_frequency))
        self.posterior_probability+=abs(np.log(self.likely_image[i,j,pixel]))        

    def get_posterior_probability(self):
        '''if self.name==0 or self.name==5:
            print("posterior probability for {}: ".format(self.name))
            print(self.posterior_probability)
            print()'''
        return self.posterior_probability
    
    def update(self,command):
        if command=='map':
            self.update_map()
    

if __name__=="__main__":
    try:
        with open('metadata.json', encoding='utf8') as data_file:
            data=json.loads(data_file.read())
    except FileNotFoundError:
        print("make the file")
    except json.decoder.JSONDecodeError:
        print('something fucked up')

    print(data)        
    classifier=naive_bayes_classifier(data)
    #digits=train_model('digitdata/trainingimages','digitdata/traininglabels')
    #test_model('digitdata/testimages','digitdata/traininglabels',digits)