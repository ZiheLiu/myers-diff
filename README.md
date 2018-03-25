# myers-diff
[![Build Status](https://travis-ci.org/ZiheLiu/myers-diff.svg?branch=master)](https://travis-ci.org/ZiheLiu/myers-diff)

A shell tool to diff two files based with algorithm Myers.
Here is the brief note of [Myers](https://github.com/ZiheLiu/myers-diff/blob/master/static/Myers.md).

## Sample
### old file
![old file](https://github.com/ZiheLiu/myers-diff/blob/master/static/old.png)

### new file
![new file](https://github.com/ZiheLiu/myers-diff/blob/master/static/new.png)

### diff
![new file](https://github.com/ZiheLiu/myers-diff/blob/master/static/diff.png)


## Dependencies
- `maven` > 3.0
- `java` > 8.0




## Usages

```shell
# copy pre-commit to ./.git/hooks
$ bash install.sh
# install dependencies
$ mvn install

# test
$ mvn test
# clean ./target
$ mvn clean
# package source code to ./target/project-name.jar
$ mvn package
# run the jar package. 
# First argument is old file.
# Seoncd argument is new file.
$ java -jar target/myers-diff-0.0.1.jar ./static/old ./static/new
```




