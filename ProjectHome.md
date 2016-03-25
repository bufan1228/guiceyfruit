GuiceyFruit provides a number of utilities on top of the [Guice](http://code.google.com/p/google-guice/) 2.x to help developers work with standard [Annotations](Annotations.md) for injection and lifecycles from [JSR 250](http://jcp.org/en/jsr/detail?id=250), [Spring](Spring.md), EJB3 and JPA as well as supporting [Maven](Maven.md) and improved [Testing](Testing.md)

GuiceyFruit includes these features
  * Support for @Resource injection for JSR 250 / EJB3 and @PersistenceContext injection from JPA (see [Annotations](Annotations.md))
  * Support for [Spring](Spring.md) annotations and lifecycle interfaces
  * [Lifecycle](Lifecycle.md) support such as for @PostConstruct, @PreDestroy from JSR 250 & EJB3
  * [a Guice based JNDI provider](JNDI.md) to help you make that J2EE code all Guicey underneath! :)
  * [Guicey Testing](Testing.md) for easy injection of resources into your test classes in JUnit3, JUnit4 or TestNG with flexible mapping to the Guice Modules for your test cases
  * [a Spring XML to Java code converter](SpringXmlConverter.md) to help you turn that Spring XML into Java code
  * [Maven](Maven.md) fully mavenized for easy use from Maven builds

## News ##

The [2.0](Download.md) release has just been performed after the Guice 2.0 release! Get it while its hot!
