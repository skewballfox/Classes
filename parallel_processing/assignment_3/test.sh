#!/usr/bin/bash

omp_run(){
./$1 1  $2 >> $3
./$1 2  $2 >> $3
./$1 4  $2 >> $3
./$1 8  $2 >> $3
./$1 16 $2 >> $3

}

# compile the code beforehand

./serial_pi_estimator 100 >> n1_data.md 
./serial_pi_estimator 10 >> n2_data.md
./serial_pi_estimator 1 >> n3_data.md

echo serial portion complete

echo -e "\n\n" >> n1_data.md
echo -e "\n\n" >> n2_data.md
echo -e "\n\n" >> n3_data.md

omp_run OpenMP_pred_pi_estimator 100 n1_data.md
omp_run OpenMP_pred_pi_estimator 10 n2_data.md
omp_run OpenMP_pred_pi_estimator 1 n3_data.md

echo program 1 portion complete

echo -e "\n\n" >> n1_data.md
echo -e "\n\n" >> n2_data.md
echo -e "\n\n" >> n3_data.md


omp_run OpenMP_pfor_pi_estimator 100 n1_data.md
omp_run OpenMP_pfor_pi_estimator 10 n2_data.md
omp_run OpenMP_pfor_pi_estimator 1 n3_data.md
echo program 2 portion complete
