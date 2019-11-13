import numpy as np
from string import whitespace

def get_images(data_file,label_file):
    images=[]
    image_count=0
    if data_file=='digitdata/trainingimages':
        image_count=5000
    elif data_file=='digitdata/testimages':
        image_count=1000
    with open(data_file,'r') as data: 
        with open(label_file,'r') as labels:
            for k in range(image_count):
            
                images.append((get_label(labels),get_image(data)))
    return images    

def get_image(data_file):
    """get_image(data_file)
    grabs a single image from the ascii data file
    
    Parameters
    ----------
        data_file: expects a txt file containing 28*28 ascii images
        ! stops iter at start of next 28 line block.
    """
    image=np.zeros([28,28])
    for i in range(28):
        raw_data=data_file.readline()
        for j in range(len(raw_data)):
            if raw_data[j]!='\n':
                if raw_data[j]!=' ':
                    image[i,j]=1
                else:
                    image[i,j]=0
    print_image(image)
    return image
        

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
    get_images('digitdata/trainingimages','digitdata/traininglabels')
