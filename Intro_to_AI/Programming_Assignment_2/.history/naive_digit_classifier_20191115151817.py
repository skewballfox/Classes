import numpy as np
from string import whitespace   


class digit_class():
    def __init__(self,identifier,training_data_count=5000):
        #self.images=[]
        self.name=identifier
        self.pixel_counts=np.zeros([28,28,3])
        self.feature_count=728 #28*28
        self.test_pixel_counter=0
        self.posterior_probability=0
        self.training_data_count=5000
    
    def get_image(self, training_data):
        
        #image=np.zeros([28,28])
        for i in range(28):
            raw_data=training_data.readline()
            for j in range(len(raw_data)):
                if raw_data[j]!='\n':
                    self.pixel_counts[i,j,2]+=1
                    if raw_data[j]!=' ':
                        #image[i,j]=1
                        self.pixel_counts[i,j,1]+=1
                    else:
                        #image[i,j]=0
                        self.pixel_counts[i,j,0]+=1
        #self.images.append(image)
    
    def set_likely_image(self):
        self.likely_image=np.array([28,28,2])
        for i in range(28):
            for j in range(28):
                    for p in range(2):
                        #NOTE:
                        #Additive smoothing is a type of shrinkage estimator, 
                        # as the resulting estimate will be between the empirical 
                        # estimate xi / N, and the uniform probability 1/d
                        # I'm going to ballpark it and use the priors as k
                        self.Laplace=self.pixel_counts[i,j,p]+self.empirical_frequency
                        self.smoothing=self.training_data_count+self.empirical_frequency*2
                        self.likely_image[i,j,p]=Laplace/smoothing
    
    def set_empirical_frequency(self):
        self.empirical_frequency=self.pixel_counts[28,28,2]/self.training_data_count
    
    def finish_training(self):
        self.set_empirical_frequency()
        self.posterior_probability=log(self.empirical_frequency)
        self.set_likely_image()

    def update_map(i,j,pixel):
        """
        NOTE the log of class has already been added to the posterior probability at the end
        of the finish training function 
        """
        self.posterior_probability+=log(self.likely_image([i,j,pixel]))        

    def get_posterior_probability():
        return self.posterior_probability
    
def train_model(training_data,training_labels):
    digits=[digit_class(digit) for digit in range(10)]
    with open(training_data,'r') as data: 
        with open(training_labels,'r') as labels:
            for k in range(5000):
                label=get_label(labels)
                digits[label].get_image(data)
                
    return digits    

def test_model(test_data,test_labels,digits):
    image_count=1000
    correct_count=0
    with open(test_data,'r') as data: 
        with open(test_labels,'r') as labels:
            for k in range(image_count):
                label=get_label(labels)
                guess=map_classification(get_image())
                if guess==label:
                    correct_count+=1
    print(format("got  {} out of {} correct".format(correct_count, image_count)))




def map_classification(test_file,digits):
    """

    """
    for i in range(28):
        raw_data=data_file.readline()
        for j in range(28):
            if raw_data[j]!=' ':
                pixel=1
            else:
                pixel=0
            for digit in digits:
                digit.update_map(i,j,pixel)
    #for digit in digits:
        

    return np.argmax([p for p in [x].get_posterior_probability() for x in digits])

def print_image(image):
    """
    renders binary images in terminal, works with any N*N binary array
    """
    print()
    for i in range(len(image)):
        print()
        for j in range(len(image[i])):
            if image[i,j]==0:
                print(' ',end='')
            else:
                print('#',end='')
    print()

def get_label(label_file):
    """get_label(label_file)
    grabs the next label from a data file
    """
    raw_data=label_file.readline()
    value=int(raw_data)
    print(value)
    return value
            

if __name__=="__main__":
    digits=train_model('digitdata/trainingimages','digitdata/traininglabels')
    