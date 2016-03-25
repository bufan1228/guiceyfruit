**NOTE** we no longer depend on a patched Guice! Yay!

The following Guice patches have been applied to the GuiceyFruit build of Guice

  * [78](http://code.google.com/p/google-guice/issues/detail?id=78) for constructor interceptors to be able to support lifecycles
  * [62](http://code.google.com/p/google-guice/issues/detail?id=62) for full lifecycle support such as for Jsr250
  * [258](http://code.google.com/p/google-guice/issues/detail?id=258) for custom annotation injection points to be better able to support things like @Resource from JSR 250 or @Autowire from Spring or @Context from JAX-RS etc
  * [259](http://code.google.com/p/google-guice/issues/detail?id=259) for a JNDI provider based on Guice. See GuiceyJndi for more details

## Generating Patches Against Guice ##

If you are a guice committer and fancy applying these patches (please!) to the trunk of guice the following commands will generate a patch

for the guice core src

```
svn diff http://guiceyfruit.googlecode.com/svn/trunk/guice/core/src/main/java/com/ http://guiceyfruit.googlecode.com/svn/mirrors/guice/trunk/src/com/
```

and test cases

```
svn diff http://guiceyfruit.googlecode.com/svn/trunk/guice/core/src/test/java/com/ http://guiceyfruit.googlecode.com/svn/mirrors/guice/trunk/test/com/
```

for the servlet scopes

```
svn diff http://guiceyfruit.googlecode.com/svn/trunk/guice/servlet/src/main/java/com/ http://guiceyfruit.googlecode.com/svn/mirrors/guice/trunk/servlet/src/com/
```