#class note
0. for k=0.1: stop:10
1.  for image indx=1:N_training
2.    for clss c=0:9
        calculate mapc for img indx
        L=argmax(mapc)
        if L= GroundTruth(indx):#label?
          correct++


F-value = num_positive+k/num_total+ vk

v is fixed, k is mutable
