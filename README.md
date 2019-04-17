# Program Comprehension Metrics

This project aims to implement several metrics related to program comprehension.

In the current version, it computes the metric detailed in
Posnett et al. "A Simpler Model of Software Readability", Mining
Software Repositories, 2011.

## Build:

Just clone this repository and execute the following maven command.

```
$ mvn clean compile assembly:single    
```

This command will generate a file comprehension-<version>.jar in the target directory. Move this
file to a more suitable place, and rename it to comprehension.jar (just to simplify the things a bit).

If you want to write more metrics, write more test cases, or refactor the source code,
please, go ahead.  I will be happy with this. To run the test case, you just have to execute:
```
$ mvn test
```

## Usage:

Execute

```
$ java -jar comprehension.jar --file <file-path>
```

The content of the file should be just a Java method
body (and not an entire Java class). Alternatively,
you can execute the program using:

```
$ java -jar comprehension.jar "<method-body>"
```

Enjoy it. 