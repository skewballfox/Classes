mpi_compile(){
  output="$(basename $1)"
  mpicc -O2 -o $output $1
}

mpi_run(){
mpirun -c $2 ./$1
}

touch n1_data.md n2_data.md n3_data.md


