# About:

This little Maven project is based on the GraalVM "Implementing Native Methods in Java with Native Image"
page, located at https://www.graalvm.org/reference-manual/native-image/ImplementingNativeMethodsInJavaWithSVM/.
It suggests that this can be used to build native shared libraries, which can be used directly by standard
java clients, using the JNI interface. The project if built demonstrates that while the build works for
adding long integers, it does not appear to work (and in fact crashes/core dumps) if the parameters are
java Strings.

# Prerequisites

Maven 3.6
GraalVM Java 21.0.0 - 21.2.0 including
* Native Image (gu install native image)

# Platform:
* Tested on Ubuntu Linux  
* uname -a output: 
   Linux my_vm 5.11.0-27-generic #29~20.04.1-Ubuntu SMP Wed Aug 11 15:58:17 UTC 2021 x86_64 x86_64 x86_64 GNU/Linux

# Build
This is a standard maven project with two subjects, the native lib and a client.
To build the native shared library and its standard java client run

   mvn clean package

# Artifacts
The two components built by the project are:
1. The "libnativeimpl.so" shared object library, containing an "evaluate" and an "add"
   endpoint. Requires Graal compiler and libraries 
2. The java appdemo.jar jarfile, which loads the libnativeimpl shared lib and whose 
   "main" entry point can either invoke the native add(long,long) or evaluate(String) functions.
   

# The Problem:

A NullPointerException (& core dump) occurs when the "evaluate()" is invoked. 
The "add()" function works fine. The only difference is that one is working
with input/output long Java parameters while "evaluate" is working with
Java String input/output.

To see the problem, after build, run the script:

  runtests

