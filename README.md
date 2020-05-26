# CS610-DSA

Code is written to implement the hashtable in java using quadratic probing to store arbitrarily long strings of ASCII chars. HashTable T will be organized as a hash-table using collision-resolution by open-addressing. Quadratic probing is used for h(k; i) and kept the choice of the quadratic function simple: i^2 so that h(k; i) = (h0(k)+i2) mod N. The keys that will be hashed are going to be English words. Thus function h'(k) is also going to be kept simple: the sum of the ASCII/Unicode values of the characters mod N, where N is the number of slots of T. Thus ’alex’ (the string is between the quotation marks) is mapped to 97+108+101+120 mod N whatever N is.
In the example below, for N = 11, h(alex;0) = 8. 
Table T however won’t store key k in it. This is because the keys are of arbitrary length. Instead, T will store pointers/references in the form of an index to another array A. The second table, array A will be a character array and will store the words maintained in T separated by NUL values. 
An insertion operation affects T and A. A word w is hashed, an available slot in T is computed and
let that slot be t. In T[t] we store an index to table A. This index is the first location that stores the first
character of w. The ending location is the \0 following w in A. New words that do not exist (never inserted,
or inserted but subsequently deleted) are appended in A. If at some point we run-out of space, we increase T and thus this
increases A as well.
A deletion will modify T as needed but will not erase w from A.
Print operaion prints nicely T and its contents i.e. index values to A. In addition it prints nicely (linear-wise in one
line) the contents of A. (For a \0 backslash is printed). but it does not print the words of A for deleted words. It
prints stars for every character of a deleted word instead.
Function Create wil create T with N slots, and A with 15N chars and initializes A to spaces. I call a class that supports and realizes A and T a lexicon.
Commands will be receievd from a file whose filename is provided by command line argument. It consists of multiple lines containing one command per line. Each command is a numeric equivalent of the function named earlier plus one more (for comment). Command 10 is Insert, Command 11 is Deletion, and Command 12 is Search. Command 13 is Print, Command 14 is Create. Command 15 is Comment: the rest of the line marked by a 15 is ignored. Command 14 for create has an argument which is the value of N. Each one of 10, 11, and 12 has an argument which is a string.
