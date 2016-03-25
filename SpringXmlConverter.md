The Spring XML Converter takes a traditional Spring XML file containing `<bean/>` elements with `<constructor-arg/>` or `<property/>` tags and converts it to a regular Java class which implements the Guice Module interface and uses @Named injection points so that you can switch your configuration from XML to being mostly Java code with some properties file for the actual Strings you really do want to change between production / testing / development.

You can run the converter from the command line if you wish assuming a suitable classpath.

```
java org.guiceyfruit.spring.converter.SpringConverter someSpring.xml
```

The output will then be a Java class source file.

Note that this code is currently experimental in nature; it doesn't implement 100% of all the possible XML formats - but its intended as a migration tool, trying to migrate as much of your XML code as possible and minimise the amount of manual coding required; but its not perfect yet.

**Note that we love patches!** Please feel free to improve this code and contribute it back if you find yourself migrating lots of Spring XML files back into Java code and find things this code cannot yet handle!