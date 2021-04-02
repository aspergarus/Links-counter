Links-counter
===================

One day I was wandering how many bookmarks I have in my browser. So I exported them into html, and then write this project to count bookmarks for every folder. If you dare to run this project, firstly export your bookmarks, then click on the button in the top center window and choose exported file.

It was first expience with Scala and ScalaFX.


Content
-------

* `src/main/scala/app/App.scala` - entrypoint of ScalaFX application.
* `build.sbt` - the main SBT configuration file.
* `project/build.properties` - version of SBT to use.
* `project/plugins.sbt` - plugins used for creation of IDEA and Eclipse projects + build in native env.


How to build and Run
--------------------

1. Install [Java 11 JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html) or newer.

2. Install [SBT](http://www.scala-sbt.org/)

3. Run the example: change to directory containing this example and use SBT to
   build and run the example:

   ```
    %> sbt run
   ```

   It will download needed dependencies, including Scala and ScalaFX, and run 
   the example code. 


Import into IDEA or NetBeans
----------------------------

IntelliJ IDEA and NetBeans with Scala plugins can directly import SBT projects. 


Create project for Eclipse
-------------------------

If you want to create project that can be used with Eclipse, inside
this project directory, at command prompt type:

    %> sbt eclipse

SBT Native Packager
----------------------

Current project is configured to build package with all dependencies for Windows.
Before running command of building package instgall ["WIX Toolset"](http://wixtoolset.org/)

	%> sbt windows:packageBin

To build on other platforms take a look at [sbt-native-packager docs](https://www.scala-sbt.org/sbt-native-packager/gettingstarted.html#create-a-package)