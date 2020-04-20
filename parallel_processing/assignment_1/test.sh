mpi_compile(){
  output="$(basename $1)"
  mpicc -O2 -std=c11 -o $output $1
}

mpi_run(){
mpirun -c 1 ./$1 $2 >> $3
mpirun -c 2 ./$1 $2 >> $3
mpirun -c 4 ./$1 $2 >> $3
mpirun -c 8 ./$1 $2 >> $3

}

# compile the code beforehand

./serial_pi_estimator 100 >> n1_data.md 
./serial_pi_estimator 10 >> n2_data.md
./serial_pi_estimator >> n3_data.md

echo -e "\n\nsingle point communicator output\n\n" >>n1_data.md
echo -e "\n\nsingle point communicator output\n\n" >>n2_data.md
echo -e "\n\nsingle point communicator output\n\n" >>n3_data.md

mpi_run sp_mpi_pi_estimator 100 n1_data.md
mpi_run sp_mpi_pi_estimator 10 n2_data.md
mpi_run sp_mpi_pi_estimator 1 n3_data.md

echo -e "\n\ncollective communication output\n\n" >>n1_data.md
echo -e "\n\ncollective communication output\n\n" >>n2_data.md
echo -e "\n\ncollective communication output\n\n" >>n3_data.md

mpi_run cc_mpi_pi_estimator 100 n1_data.md
mpi_run cc_mpi_pi_estimator 10 n2_data.md
mpi_run cc_mpi_pi_estimator 1 n3_data.md

