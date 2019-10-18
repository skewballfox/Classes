/*
    Author: Joshua Ferguson
    Purpose: efficiently check if a word is a palindrome
 */
/*
##############################   Macro Definitions   ##################################
*/
macro_rules! print_array 
{
    ($array:ident) => (
            print!("{}","[ ");
            for x in $array.iter()
            {
                print!("{} ",x)
                
            }
            println!("{}","]");
            )
}

/*
##############################   Main Loop   ##################################
*/
fn main() 
{
    
    let mut array: [i32; 12]= [7, 6, 12, 9, 3, 4, 5, 11, 9, 14, 2, 8];
    
    print!("unsorted array : ");
    print_array!(array);
    insertion_sort(&mut array, None);
}


/*
##############################   Function Definitions   ##################################
*/
fn insertion_sort(array: &mut [i32], start: Option<usize>)
 {
     println!("starting insertions sort");
     let mut count= 0;
     let n = start.unwrap_or(1);
    // loop going from optional start to end
    for i in n..array.len() 
    {
        for j in (1..i+1).rev() 
        {
            count+=1;
            if array[j-1] <= array[j] {break; }
            let temp = array[j-1];
            array[j-1] = array[j];
            array[j] = temp;
            print_array!(array);
        }

    }
    println!("total number of comparisons: {}", count);
}
