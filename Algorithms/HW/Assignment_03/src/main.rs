/*
    Author: Joshua Ferguson
    Purpose: perform an insertion sort, and let me play around with rust for a bit

 */
use rand::{thread_rng, Rng};
use std::collections::HashSet;

/*
##############################   Macro Definitions   ##################################
*/



macro_rules! is_palindrome 
{
    ($word: ident) => 
    (
         if palindrome_check($word)
        {
            println!("{} is a palindrome", $word);
        }
        else 
        {
            println!("{} not a palindrome", $word);
        }
    )
}

macro_rules! random_gen_array
{
    (
      $random_array:ident) =>
    (
        let mut set=HashSet::new();

        let mut $random_array: [i32; 10000]=[0;10000];
        let mut rng = thread_rng();
        for i in 0..$random_array.len()
        {
            let mut done=false;
            while !done
            {
                let random_value: i32 = rng.gen_range(1, 11000);
                if !set.contains(&random_value)
                {
                    $random_array[i]=random_value;
                    set.insert(random_value);
                    done=true;
                }
            }
        }
    )
}
macro_rules! test_kdiff_function
{
  ($function:expr,$random_array: ident, $k:ident) =>
  (
    let dif_count=$function(&mut $random_array,$k);
    println!("the number of k-differential pairs is {}", dif_count);
  )
}

/*
##############################   Main Body   ##################################
*/

/*
2 b)a better approach would be to sort the set beforehand using merge sort and
  then search up to k items ahead for item+k, and break when given an item
  that is greater than k. Since the array is a set of
  distinct integers, and they are consecutive, it greatly reduces the amount of
  time spent searching for the next item.
  the time complexity is O(k*N) for the sorted_k_diff algorithm as it only
  searches k items ahead so long as the the two difference between the values being compared aren't
  greater than k.
*/

fn main() {
    let k=5;
    let word_1="racecar";
    let word_2="bananna";
    let word_3="hannah";
    is_palindrome!(word_1);
    is_palindrome!(word_2);
    is_palindrome!(word_3);
    random_gen_array!(random_array);
    test_kdiff_function!(brute_k_differential,random_array,k);
    merge_sort(& mut random_array);
    test_kdiff_function!(sorted_k_diff,random_array,k);
    

   
}



fn palindrome_check(word: &str)->bool
{
    //first version
    //if word.chars().rev().collect::<String>() == word {true} else {false}
    //third version
   if word.as_bytes().iter().zip(word.as_bytes().iter().rev()).all(|(a, b)| a == b) {true} else {false}
}

fn brute_k_differential(array: &mut [i32],k: usize)->i32
{
    let mut dif_count=0;
    let mut set=HashSet::new();

    for i in 0..array.len()
    {
        for j in 0..array.len()
        {
            if array[i]-array[j]==(k as i32) || array[j]-array[i]==(k as i32)
            {
                if !set.contains(&(array[i],array[j])) && !set.contains(&(array[j],array[i]))
                {
                    set.insert((array[i],array[j]));
                    //println!("the new differential pairs is {},{}",array[i],array[j]);
                    dif_count+=1;
                }
            }
        }
    }
    return dif_count;
}


fn merge_sort(array: &mut [i32])
{
  let middle= array.len()/2;

  if middle==0 {return;}

  merge_sort(&mut array[..middle]);
  merge_sort(&mut array[middle..]);

  let mut returned_array = array.to_vec();

  merge(&array[..middle],&array[middle..], &mut returned_array[..]);

  array.copy_from_slice(&returned_array);
}

fn merge(slice_1: &[i32], slice_2: &[i32], returned_array: &mut [i32])
{
  let mut left=0;
  let mut right=0;
  let mut index=0;

  while left < slice_1.len() && right < slice_2.len()
  {
    if slice_1[left] <= slice_2[right]
    {
      returned_array[index] = slice_1[left];
      index +=1;
      left += 1;
    } else {
      returned_array[index]=slice_2[right];
      index += 1;
      right += 1;
    }
  }

  if left < slice_1.len()
  {
    returned_array[index..].copy_from_slice(&slice_1[left..]);
  }
  if right < slice_2.len()
  {
    returned_array[index..].copy_from_slice(&slice_2[right..]);
  }
}

fn sorted_k_diff(array: &mut [i32], k: usize) ->i32
{
  let mut count=0;

  for i in 0..array.len()
  {
    //println!("{}",i);
    if i+k+1 <= array.len()
    {
      for j in i+1..i+k+1
      {
        if array[j]-array[i]<=(k as i32)
        { if array[j]-array[i]==(k as i32)
          {
            count+=1;
            //println!("{},{}",array[j],array[i]);
          }  //else{println!("{},{} not a k-diff pair",array[j],array[i]);}
        }else{break;}
      }
    }
  }
  return count;
}
