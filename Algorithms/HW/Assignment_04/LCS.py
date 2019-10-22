import numpy as np

def LCS(sequence_1,sequence_2):
    length_1,length_2=len(sequence_1),len(sequence_2)
    LCS_length=[[0]*(length_2+1) for i in range(length_1+1)]
    LCS_index=[[0]*(length_2+1) for i in range(length_1+1)]
    for i in range(1,length_1+1):
        for j in range(1,length_2+1):
            if sequence_1[i-1].upper()==sequence_2[j-1].upper():
                LCS_length[i][j]=LCS_length[i-1][j-1]+1
                LCS_index[i][j]=3
            else:
                LCS_length[i][j]=max(LCS_length[i-1][j],LCS_length[i][j-1])
                if LCS_length[i-1][j]<LCS_length[i][j-1]:
                    LCS_index[i-1][j-1]=2
                else:
                    LCS_index[i-1][j-1]=1
    return LCS_length[-1][-1],LCS_index
                
def print_LCS(LCS_index,sequence_1,i,j):
  print(i)
  print(sequence_1)
  if i==0 or j==0:
    return
  if LCS_index[i][j]==3:
    print_LCS(LCS_index,sequence_1,i-1,j-1)
    print(sequence_1[i-1],end='')
  elif LCS_index[i][j]==2:
    print_LCS(LCS_index,sequence_1,i-1,j)
  else:
    print_LCS(LCS_index,sequence_1,i,j-1)
if __name__=="__main__":
    sequence_1="GATTAGA"
    sequence_2="ATTGAG"
    length,indexes=LCS(sequence_1,sequence_2)
    #l,d=LCS("Southern Miss", "TO THE TOP")
    print("length is ",length)
    print_LCS(indexes,sequence_1,len(sequence_1),len(sequence_2))
