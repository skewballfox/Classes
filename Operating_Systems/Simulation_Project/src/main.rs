/*
    Author: Joshua Ferguson
    Purpose: simulate a multi

 */

use rand::{thread_rng, Rng};
use std::fs::File;
use std::path::Path;
//mod stack;
mod persistent_stack;
/*
##############################   Macro Definitions   ##################################
*/

/*
Note: I'm leaving this here for the sake of documenting previous attempts
at completing assignment A. This was a no go due to the fact that rust's borrow
checker is rather stringent. Passing mutable references to immutable values is a no go
so there was no way to store the values in a mutable array then pass to a persistent stack
and there is no way to declare an immutable array with 100 random variables, so this approach
was a bust
*/
macro_rules! random_int_array
{
    ($random_array:ident,$size:expr,$min:expr,$max:expr)=>
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

macro_rules! rng_file
{
    ($filename:ident,$size:expr,$min:expr,$max:expr)=>
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
/*macro_rules! test_this
{
    ($data_set, $)
}*/

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


    let lifo=persistent_stack::Stack::new();
    println!("on the stack: ");
    for value in data_set_1.iter()
    {
        lifo.push(&mut value);
        println!("{:?}",lifo.head())
        
    }
    let mut iter=lifo.iter();
    let mut value=iter.next();
    println!("off the stack: ");
    println!("{:?}",lifo.head());
    println!("{:?}",value);
    while value!=None
    {
        println!("{:?}",value);        
        value=iter.next();
    }
    
    
}

