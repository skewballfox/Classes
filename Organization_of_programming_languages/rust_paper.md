# Rust

## links

[ how is rust faster than c++ ](https://www.quora.com/How-is-Rust-faster-than-C++)

[accurate mental model of rust's reference types](https://docs.rs/dtolnay/0.0.6/dtolnay/macro._02__reference_types.html)

TLDR: mut variables are exclusive reference where nonmutables are a shared reference, rather than our normal understanding of a const. this mean the value pointed to by the reference can be shared with other references, and thus it should't be modified. 