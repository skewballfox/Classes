import re
import numpy as np
from pprint import pprint


def get_table_row(line): 
    data=[float(val) for val in line.replace(" ","").split("|") if re.match(r'^(\-)?\d*\.\d+|\d+',val)]
    return data
    
def get_error_percentage(real,estimate):
    return ((estimate-real)/real)
pi_real=3.141592
    
assignment_directory="./assignment_1/"
report_file = "report.md"
data_files=("n1_data.md", "n2_data.md", "n3_data.md")

data=[]

for line in  open(assignment_directory+report_file, 'r'):
    #print(line)
    if line.startswith(("| 1","| 2","| 4","| 8")):
        data.append(get_table_row(line))

runtime_data,pi_data=data[(len(data)//2):],data[:(len(data)//2)]
serial_pi_estimation,sp_pi_estimations,cc_pi_estimations={},{},{}
n=0

for row in pi_data:
    row.pop(0)
    cc_pi_estimations[2**n]=row[1::2]
    sp_pi_estimations[2**n]=row[0::2]
    n+=1

serial_runtimes,sp_runtimes,cc_runtimes={},{},{}
n=0

for row in runtime_data:
    row.pop(0)
    cc_runtimes[2**n]=row[1::2]
    sp_runtimes[2**n]=row[0::2]
    n+=1

pprint(sp_runtimes)


