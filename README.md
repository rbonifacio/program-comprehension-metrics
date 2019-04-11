# Program Comprehension Metrics

This project aims to implement several metrics related to program comprehension.

In the current version, it computes the metric detailed in
Posnett et al. "A Simpler Model of Software Readability", Mining
Software Repositories, 2011.

## Usage:

Execute

```
$ java -jar comprehension --file <file-path>
```

The content of the file should be just a Java method
body (and not an entire Java class). Alternatively,
you can the program using:

```
$ java -jar comprehension "<method-body>"
```

Enjoy it. 