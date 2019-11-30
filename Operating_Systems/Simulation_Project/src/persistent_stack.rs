// 

//Asyncronous reference counter, used for thread safety

use std::sync::Arc;
/*
Data structures for persistent linked list
*/
pub struct Stack<T> 
{
    head: Link<T>,
}

type Link<T> = Option<Arc<Node<T>>>;

struct Node<T> {
    elem: T,
    next: Link<T>,
}

pub struct Iter<'a, T> 
{
    next: Option<&'a Node<T>>,
}
/*
Implementation of persistent stack functions
*/
impl<T> Stack<T>
{
    pub fn new() -> Self
    {
        Stack { head: None }
    }
    /*
    Fun fact: None of the functions that follow work in the typical way we espect linked list to operate
    this is because the items being passed to the linked list are immutable references, and thus
    work arounds are necessary to achieve expected behavior. most of these work arounds involve cloning
    
    this is an acceptable limitation given that rust uses RC garbage collection,
    an variable is destroyed when there are no more references to it
    */
    pub fn push(&self, elem:T) -> Stack<T>
    {
        /*psuedo push
        NOTE: in order to make the stack persistent we can't exactly add a new head reference
        this essentially clones the second element, and references that from the new node
        */
        Stack { head: Some(Arc::new(Node {
            elem: elem, 
            next: self.head.clone(),
        }))}
    }

    pub fn pop(&self) -> Stack<T> 
    {
        /*psuedo pop
        NOTE: in order to make the stack persistent we can't exactly remove an element
        this essentially clones the second element, and makes that the head of the new
        stack
        */
        Stack { head: self.head.as_ref().and_then(|node| node.next.clone())}
    }

    pub fn head(&self) -> Option<&T> 
    {
        self.head.as_ref().map(|node| &node.elem )
    }

    pub fn iter(&self) -> Iter<'_, T> 
    {
        Iter { next: self.head.as_ref().map(|node| &**node) }
    }
    
}

impl<T> Drop for Stack<T> 
{
    fn drop(&mut self) 
    {
        let mut head = self.head.take();
        while let Some(node) = head 
        {
            if let Ok(mut node) = Arc::try_unwrap(node)
            {
                head=node.next.take();
            } else 
            {
                break;
            }
        }
    }
}

impl<'a, T> Iterator for Iter<'a, T> 
{
    type Item = &'a T;
    fn next(&mut self) -> Option<Self::Item> {
        self.next.map(|node| {
            self.next = node.next.as_ref().map(|node| &**node);
            &node.elem
        })
    }
}

/*
Testing functions
*/
#[cfg(test)]
mod test {
    use super::Stack;

    #[test]
    fn basics() 
    {
        let list = Stack::new();
        assert_eq!(list.head(), None);

        let list = list.push(1).push(2).push(3);
        assert_eq!(list.head(), Some(&3));

        let list = list.pop();
        assert_eq!(list.head(), Some(&2));

        let list = list.pop();
        assert_eq!(list.head(), Some(&1));

        let list = list.pop();
        assert_eq!(list.head(), None);

        // Make sure empty tail works
        let list = list.pop();
        assert_eq!(list.head(), None);

    }

    #[test]
    fn iter() 
    {
        let list = Stack::new().push(1).push(2).push(3);

        let mut iter = list.iter();
        assert_eq!(iter.next(), Some(&3));
        assert_eq!(iter.next(), Some(&2));
        assert_eq!(iter.next(), Some(&1));
    }
}
