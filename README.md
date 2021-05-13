[https://docs.oracle.com/javase/tutorial/deployment/jar/index.html](https://docs.oracle.com/javase/tutorial/deployment/jar/index.html)

```
jar cf jar-file input-files
```

* The c option indicates that you want to create a JAR file.   
* The f option indicates that you want the output to go to a file rather than to stdout. 
* The input-file(s) argument is a space-separated list of one or more files that you want to include in your JAR file. The input-file(s) argument can contain the wildcard * symbol. If any of the "input-files" are directories, the contents of those directories are added to the JAR archive recursively.



| Option	 | Description |
| ---------- | :-----------|
|v	|Produces verbose output on stdout while the JAR file is being built. The verbose output tells you the name of each file as it's added to the JAR file.
|0 (zero)	| Indicates that you don't want the JAR file to be compressed.
| M	| Indicates that the default manifest file should not be produced.
| m	|Used to include manifest information from an existing manifest file. The format for using this option is: `jar cmf jar-file existing-manifest input-file(s)`See Modifying a Manifest File for more information about this option. Warning: The manifest must end with a new line or carriage return. The last line will not be parsed properly if it does not end with a new line or carriage return.
|-C	| To change directories during execution of the command. See below for an example.


When you create a JAR file, the time of creation is stored in the JAR file. Therefore, even if the contents of the JAR file do not change, when you create a JAR file multiple times, the resulting files are not exactly identical. You should be aware of this when you are using JAR files in a build environment. It is recommended that you use versioning information in the manifest file, rather than creation time, to control versions of a JAR file. 



### TicTacToe build

```
jar cvf TicTacToe.jar TicTacToe.class audio images

```

You can see from this output that the JAR file TicTacToe.jar is compressed. The Jar tool compresses files by default. You can turn off the compression feature by using the 0 (zero) option, so that the command would look like:

```
jar cvf0 TicTacToe.jar TicTacToe.class audio images

```

Though the verbose output doesn't indicate it, the Jar tool automatically adds a manifest file to the JAR archive with path name META-INF/MANIFEST.MF.


In the above example, the files in the archive retained their relative path names and directory structure. The Jar tool provides the -C option that you can use to create a JAR file in which the relative paths of the archived files are not preserved. It's modeled after TAR's -C option.


As an example, suppose you wanted to put audio files and gif images used by the TicTacToe demo into a JAR file, and that you wanted all the files to be on the top level, with no directory hierarchy. You could accomplish that by issuing this command from the parent directory of the images and audio directories:

```
jar cf ImageAudio.jar -C images . -C audio .
```
The -C images part of this command directs the Jar tool to go to the images directory, and the . following -C images directs the Jar tool to archive all the contents of that directory. The -C audio . part of the command then does the same with the audio directory. The resulting JAR file would have this table of contents:

```
META-INF/MANIFEST.MF
cross.gif
not.gif
beep.au
ding.au
return.au
yahoo1.au
yahoo2.au
```


By contrast, suppose that you used a command that did not employ the -C option:

```
jar cf ImageAudio.jar images audio
```

The resulting JAR file would have this table of contents:

```
META-INF/MANIFEST.MF
images/cross.gif
images/not.gif
audio/beep.au
audio/ding.au
audio/return.au
audio/yahoo1.au
audio/yahoo2.au
```

```
$ jar tf TicTacToe.jar

META-INF/
META-INF/MANIFEST.MF
TicTacToe.class
audio/
audio/yahoo1.au
audio/return.au
audio/ding.au
audio/beep.au
audio/yahoo2.au
images/
images/not.gif
images/cross.gif
```


