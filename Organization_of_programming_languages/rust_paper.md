# Rust

## links

[ how is rust faster than c++ ](https://www.quora.com/How-is-Rust-faster-than-C++)

[accurate mental model of rust's reference types](https://docs.rs/dtolnay/0.0.6/dtolnay/macro._02__reference_types.html)



## 1.Background       

### 1.What was the original purpose of the language? What problems were the authors trying to solve? Limitations in other languages?  Tell about it.

### 2.Who were the authors? Tell us about them and their background.

## 2.What makes the language stand out? Tell us all about the language.

### 1.What were some significant features of the original implementation?

### 2.What made the language catch on in its field?

### 3. What are some noted things that have been done with the language?

### 4. What are some of the significant changes and adaptations in the revisions over time?

## 3.Give code examples of early implementations, and recent implementations, and explain what the code is doing. How does this compare with other languages? How does it contrast with other languages?

## 4.What are some of the things the language needs to improve on?
                                                                
TLDR: mut variables are exclusive reference where nonmutables are a shared reference, rather than our normal understanding of a const. this mean the value pointed to by the reference can be shared with other references, and thus it should't be modified. 
