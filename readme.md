# Top ten words, as example of Inverted Index

A command-line Java app that counts unique words from a text file and lists the top 10 occurrences.
Uses English locale and treat hyphen and apostrophe as part of a word.

The is started from words.WordsApplication

## Definitions: Index, InvertedIndex

### Index
index here is a table where each key is associated to multiple values

### Inverted Index
Values and Keys switch roles: values are used to locate keys. We want to know eher certain keys of interest
occur. We create an Inverted Index where each key is asociated with a set of locations.

### Counting Inverted Index
Our is an Inverted Index where the key is a number/ count that is associated to all the words that have that count.
This is not as a Frequency Table as here 

[Top Words](https://github.com/atdance/topwords)

## Libraries used
- maven
- maven-failsafe-plugin
- junit
- Java SE 8
