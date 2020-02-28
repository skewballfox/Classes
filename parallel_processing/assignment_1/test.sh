mpi_compile(){
  output="$(basename $1)"
  mpicc -O2 -o $output $1
}

mpi_run(){
mpirun -c $2 ./$1
}

module load openmpi-2.0/gcc

touch n1_data.md n2_data.md n3_data.md

gcc -lm serial_pi_estimator.c -o serial_pi_estimator 

echo '# Data for round 1'
./serial_pi_estimator 100 > n1_data.md 
./serial_pi_estimator 10 > n2_data.md