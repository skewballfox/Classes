/*
    Author: Joshua Ferguson
    Purpose: simulate a multi

 */
use rand::{thread_rng, Rng};

/*
##############################   Macro Definitions   ##################################
*/

macro_rules! random_int_array
{
    ($random_array:ident,$size:ident,$min:ident,$max:ident)=>
    (

        let mut $random_array: [i32; $size]=[0;$size];
        let mut rng = thread_rng();
        for i in 0..$random_array.len()
        {
            
            let random_value: i32 = rng.gen_range($min, $max);
            
            $random_array[i]=random_value;
            
        }
    )

}

/*
##############################   Main Body   ##################################
*/

fn main() 
{   
    //initialize the data
    random_int_array!(data_set_1,100,0,99);
    random_int_array!(data_set_2,100,0,99);
    random_int_array!(data_set_3,100,0,99);
    random_int_array!(priorities,100,1,3);
}

