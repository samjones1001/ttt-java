# ttt-java ![Build Status](https://travis-ci.com/samjones1001/ttt-java.svg?branch=master) [![codecov](https://codecov.io/gh/samjones1001/ttt-java/branch/master/graph/badge.svg)](https://codecov.io/gh/samjones1001/ttt-java)

A Tic Tac Toe implementation in Java.

### Setup

This project uses [Gradle](https://gradle.org/) to manage dependencies.

- Clone this repository and navigate to its root directory in the command line
- If not already present on your system, install gradle using `brew install gradle` for macOS, or `sdk install gradle 6.0` for other Unix-based operating systems.
- Build a jar using `./gradlew jar`

### Tests

In order to run the test suite, navigate to the projects root directory in the command line and run `./gradlew test`.

### Execution

The programme can be executed by making use of the jar built during setup:

```shell
$ java jar [path/to/jar/file]
```
