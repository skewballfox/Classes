/*
    Author: Joshua Ferguson
    Purpose: perform an insertion sort, and let me play around with rust for a bit

 */
use rand::{thread_rng, Rng};

/*
##############################   Macro Definitions   ##################################
*/

macro_rules! random_int_array
{
    ($size:ident,$random_array:ident,$min:ident,$max:ident)=>
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
    println!("Hello, world!");
}

