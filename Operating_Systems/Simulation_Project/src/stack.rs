/*
    Author: Joshua Ferguson
    Note: ttps://rust-unofficial.github.io/too-many-lists/print.html#an-obligatory-public-service-announcement
    Purpose: perform an insertion sort, and let me play around with rust for a bit

 */
use std::mem;

pub struct List
{
    head: Link,
}

enum Link
{
    Empty,
    More(Box<Node>),
}

struct Node
{
    elem: i32,
    next: Link,
}

impl List
{
    pub fn new() -> Self
    {
        List { head: Link::Empty }
    }

    pub fn push(&mut self, elem: i32)
    {
        let new_node= Box::new(Node {
            elem: elem,
            next: mem::replace( &mut self.head, Link::Empty),

        });

        self.head = Link::More(new_node);
    }
}