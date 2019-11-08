import numpy as np
from string import whitespace

def get_images(file):
    images=[]
    if file=='digitdata/trainingimages':
        image_count=5000
    elif file=='digitdata/testimages':
        image_count=1000
    with open(file,'r') as f:
        image=np.zeros([28,28])
        for k in range(5000):
            for i in range(28):
                raw_data=f.readline()
                for j in range(len(raw_data)):
                    if raw_data[j]!='\n':
                        if raw_data[j]!=' ':
                            image[i,j]=1
                        else:
                            image[i,j]=0
            images.append(image)
    return images    

def print_image(image):
    print()
    print(image.shape)
    for i in range(len(image)):
        print()
        for j in range(len(image[i])):
            if image[i,j]==0:
                print(' ',end='')
            else:
                print('#',end='')


if __name__=="__main__":
    get_images('digitdata/trainingimages')
