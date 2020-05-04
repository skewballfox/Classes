# Assignment 1 report

## Pi Generator
### Summary
In this Assignment I wrote 3 pieces of code: a serial implementaion, an SPMD implemtation and a collective communicator implementation. In both parallel implementations the tosses(as series of x,y coordinates with values ranging from 0-2) were simulated and those with a distance less than 1, a local count was updated. In both parallel implementations those local counts were aggregated into a single value that was then put over the total tosses(prior to integer division), and multiplied by 4, resulting in an approximation of pi to 6 decimal places. the only difference between the two implementations was that the cc used allreduce to send the results to p0(including p0, which ran the same function, though it was the only one with that had access to the output value), whereas the SPMD implementation all processes(save for p0) sent their results to p0, and p0 had to recieve each individual message. 

In the spirit of full disclosure I did discover some bugs in my implementation during analysis that I would have fixed had I analyzed the results sooner, but I thought it more prudent to turn in what I have and avoid the same pitfall next time.

because the amount of work performed by each process was calculated with integer division, there was likely a discrepancy in the remainder. I could have fixed this by making the pi estimation 
```
4*(circle_count/(total_tosses-((total_tosses%total_toss_dividend)*number_of_processes)
```

that would have preserved the ratio of circle_count and total_tosses, thus maintaining the efficacy of the monte carlo method(which relies on a normal distributution in random enough data).

as it stands the code did become less accurate in parallel, though I attempted to document to the extent this was the case. 

also of note is the program didn't benefit from collective communication, as the collective communication implementation performed worse on average (though to a degree that was insignificant in most cases). 

## Data
### github repo
[assignment_1](https://github.com/skewballfox/Classes/tree/master/parallel_processing/assignment_1)
### snippets
#### non-ipython code to generate report (report-gen.py)
```
for core_count in cc_runtimes:
      n=0
      print("\nEfficiency for core count "+str(core_count))
      for runtime in cc_runtimes[core_count]:
          #print(serial_data["runtime"][n])
          #print(runtime)
          #print(core_count)
          print(get_parallel_efficiency(serial_data["runtime"][n],runtime,core_count))
          n+=1
```
#### piping output to collect results (in test.sh)
```bash
mpi_run sp_mpi_pi_estimator 100 n1_data.md
mpi_run sp_mpi_pi_estimator 10 n2_data.md
mpi_run sp_mpi_pi_estimator 1 n3_data.md
```

### Screenshots

#### working on report

![i3 ftw](/home/daedalus/Workspace/Classes/parallel_processing/assignment_1/Report/Screenshot_20200310_114327.png "screenshot1")

#### command history on local shell

![on termux](/home/daedalus/Workspace/Classes/parallel_processing/assignment_1/Report/Screenshot_20200310_114327.png "screenshot2")

### Runtime and Pi estimation data

+------------------+---------------------+-----------------------+-------------------------+
|      Threads     | INT_MAX/100         | INT_MAX/10            | INT_MAX                 |
|                  +----------+----------+-----------+-----------+------------+------------+
|                  | prog_1   | prog_2   | prog_1    | prog_2    | prog_1     | prog_2     |
+------------------+----------+----------+-----------+-----------+------------+------------+
| 1                | 1.223367 | 1.217425 | 12.163910 | 12.117926 | 122.347599 | 120.911287 |
+------------------+----------+----------+-----------+-----------+------------+------------+
| 2                | 0.626475 | 0.618682 | 6.339357  | 6.189493  | 62.401576  | 61.467796  |
+------------------+----------+----------+-----------+-----------+------------+------------+
| 4                | 0.334545 | 0.325716 | 3.318278  | 3.278050  | 33.088576  | 32.645136  |
+------------------+----------+----------+-----------+-----------+------------+------------+
| 8                | 0.187872 | 0.184358 | 1.875582  | 1.830704  | 18.831717  | 18.449665  |
+------------------+----------+----------+-----------+-----------+------------+------------+
| 16               | 0.132438 | 0.124759 | 1.251546  | 1.249141  | 12.560106  | 12.349003  |
+------------------+----------+----------+-----------+-----------+------------+------------+
|                  | 3.141802 | 3.141605 | 3.141587  | 3.141577  | 3.141595   | 3.141595   |
| Estimated pi     |          |          |           |           |            |            |
| t=1              |          |          |           |           |            |            |
+------------------+----------+----------+-----------+-----------+------------+------------+
|                  | 3.142128 | 3.141913 | 3.141595  | 3.141946  | 3.141593   | 3.141596   |
| Estimated pi     |          |          |           |           |            |            |
| t=8              |          |          |           |           |            |            |
+------------------+----------+----------+-----------+-----------+------------+------------+
|                  | 3.141913            |                       | 3.141569                |
| Estimated pi     |                     |                       |                         |
| serial           |                     |                       |                         |
+------------------+---------------------+-----------------------+-------------------------+


![error_of_pi_estimation](/home/daedalus/Workspace/Classes/parallel_processing/assignment_1/Report/error_of_pi_estimation.png)
#### Runtime
| # of Processes | n1 sp    | n1 cc    | n2 sp     | n2 cc     | n3 sp      | n3 cc      |
|----------------|----------|----------|-----------|-----------|------------|------------|
| 1              | 1.493489 | 1.408041 | 11.964283 | 11.942708 | 116.282075 | 117.013246 |
| 2              | .877116  | .853008  | 6.445123  | 6.411415  | 58.521897  | 58.915367  |
| 4              | .460536  | .497405  | 3.464270  | 3.475153  | 32.528385  | 32.595666  |
| 8              | .259565  | .269562  | 2.002572  | 2.018526  | 18.980342  | 18.986749  |

#### Speedup
![speedup n1](/home/daedalus/Workspace/Classes/parallel_processing/assignment_1/Report/speedup_n1.png "speedup n1")
![speedup n3](/home/daedalus/Workspace/Classes/parallel_processing/assignment_1/Report/speedup_n3.png "speedup n3")

#### Efficiency
| # of Processes | n1 sp | n1 cc | n2 sp | n2 cc | n3 sp | n3 cc |
|----------------|-------|-------|-------|-------|-------|-------|
| 1              | 1.34  | 1.42  | .83   | .83   | .81   | .80   |
| 2              | 1.14  | 1.17  | .78   | .78   | .80   | .80   |
| 4              | 1.08  | 1.01  | .72   | .72   | .72   | .72   |
| 8              | .96   | .93   | .62   | .62   | .61   | .62   |
