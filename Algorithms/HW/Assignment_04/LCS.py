import numpy as np

def LCS(sequence_1,sequence_2):
    length_1,length_2=len(sequence_1),len(sequence_2)
    print("lengths:")
    print(length_1)
    print(length_2)
    LCS_length=[[0]*(length_2+1) for i in range(length_1+1)]
    LCS_list=[[0]*(length_2+1) for i in range(length_1+1)]
    k=0
    consecutive=False
    for i in range(1,length_1+1):
        print("loops")
        print("i=",i)
        for j in range(1,length_2+1):
            print("j=",j)
            if sequence_1[i-1].upper()==sequence_2[j-1].upper():
                #print("value:")
                #print(LCS_length[i-1][j-1])
                #print(LCS_length[i][j])
                LCS_length[i][j]=LCS_length[i-1][j-1]+1
                    LCS_list[i][j]=1
            else:
                print("lengths:")
                print(LCS_length[i-1][j])
                print(LCS_length[i][j-1])
                LCS_length[i][j]=max(LCS_length[i-1][j],LCS_length[i][j-1])
                if LCS_length[i-1][j]<LCS_length[i][j-1]:
                    LCS_list[i][j]=2
                else:
                    LCS_list[i][j]=3
                k+=1
    spacer=''.join([' 'for i in range(max(length_1,length_2)-min(length_1,length_2))])
    for i in range(len(LCS_length)):
        
        for j in range (len(LCS_length[0])):
            print (LCS_length[i][j],end='')
        print()
    print('\n')
    for i in range(len(LCS_list)):
        
        for j in range (len(LCS_list[0])):
            print (LCS_list[i][j],end='')
        print()
    return LCS_length[-1][-1],LCS_list[-1][-1]

if __name__=="__main__":
    #l,d=LCS("GATTAGA","ATTGAG")
    l,d=LCS("Southern Miss", "TO THE TOP")
    print("length is ",l)
    print(d)
