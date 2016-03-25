GuiceyFruit supports Guicey Testing by allowing you to inject your dependencies using Guice along with support for JSR 250, Spring, EJB3 or [JNDI](JNDI.md) if you require.

You perform injections using the usual **@Inject** annotation from Guice or using JSR 250 annotations like **@PostConstruct**, **@PreDestroy** and **@Resource**.

You can associate the Guice Module(s) to be used by your test case using the following rules

  * annotate your test class with the @UseModule annotation which takes the Module class as a parameter
  * if no annotation is specified then guiceyfruit will look for an inner class called TestModule implementing Module
  * specify the **org.guiceyfruit.modules** system property a space separated list of modules which is useful for reusing a test case with a different module

So you can either use the class naming convention, or use an annotation to denote which module to use. The advantage of the annotation is that you can reuse a single module class with multiple test cases.

For example using an explicit annotation

```
// lets specify an exact module
@UseModule(MyModule.class)
pubilc class MyTest extends GuiceyTestCase {
  @Inject
  Cheese cheese;

  public void testSomething() {...}
}
```

or using the naming convention

```
// lets use the inner class
pubilc class MyTest extends GuiceyTestCase {
  @Inject
  Cheese cheese;

  public void testSomething() {...}

  public static class TestModule extends AbstractModule {
   // Guice module goes here ...
  }
}
```


## JUnit3 ##

To support [JUnit3](http://junit.org/) you first add the maven dependency to your pom.xml

```
    <dependency>
      <groupId>org.guiceyfruit</groupId>
      <artifactId>guiceyfruit-junit3</artifactId>
      <version>${guiceyfruit-version}</version>
      <scope>test</scope>
    </dependency>
```

Then you just derive from **GuiceyTestCase** case as shown in the examples above or this example below which shows JSR 250 injection.

```
@UseModule(Jsr250Module.class)
pubilc class MyTest extends GuiceyTestCase {

  @Resource
  Cheese cheese;

  public void testSomething() {...}
}
```

## JUnit4 ##

For [JUnit4](http://junit.org/) you first add the maven dependency to your pom.xml

```
    <dependency>
      <groupId>org.guiceyfruit</groupId>
      <artifactId>guiceyfruit-junit4</artifactId>
      <version>${guiceyfruit-version}</version>
      <scope>test</scope>
    </dependency>
```

Then you just need to add the **@RunWith(GuiceyJUnit4.class)** annotation as shown

```
@RunWith(GuiceyJUnit4.class)
@UseModule(MyModule.class)
public class AnnotationTest {
    @Inject
    Cheese cheese;

    @Test
    public void testSomething() {...}
}
```

## TestNG ##

For [TestNG](http://testng.org) you first add the maven dependency to your pom.xml

```
    <dependency>
      <groupId>org.guiceyfruit</groupId>
      <artifactId>guiceyfruit-testng</artifactId>
      <version>${guiceyfruit-version}</version>
      <scope>test</scope>
    </dependency>
```

Then you need to derive from the **GuiceyTestCase** class as shown

```
@UseModule(MyModule.class)
public class AnnotationTest extends GuiceyTestCase {
    @Inject
    Cheese cheese;

    @Test
    public void testSomething() {...}
}
```

## Scopes and Lifecycles ##

Guicey Testing supports all of the [Lifecycles](Lifecycle.md) such as for JSR250/EJB3/Spring.

To properly use closing lifecycles, such as @PreDestroy or Spring's DisposableBean you need to use a scope which is then destroyed.

The following scopes are supported in test cases

| Scope annotation | Description |
|:-----------------|:------------|
| @Singleton       | objects stay around across all tests within the same JVM. The close is invokved by default using a JVM shutdown hook - unless the testing framework has a hook we can use |
| @ClassScoped     | the object stays around until all of the test methods are completed in the current test class - it is then closed |
| @TestScoped      | the object stays around until the test method is completed and its then closed |

Note that creating objects without associating them with a scope that can be closed will not close your objects.

## Overloading the Guice Module from Maven ##

If you configure your pom.xml so that system properties can be passed in from the command line such as

```
      <plugin>
        <artifactId>maven-surefire-plugin</artifactId>
        <configuration>
          <systemProperties>
             <property>
              <name>org.guiceyfruit.modules</name>
              <value>${org.guiceyfruit.modules}</value>
            </property>
          </systemProperties>
        </configuration>
      </plugin>
```

Then when running tests you can overload the Guice module you want to use for injecting into your test case as follows.

```
cd guiceyfruit-junit3
mvn test -Dtest=NamingConventionTest -Dorg.guiceyfruit.modules=org.guiceyfruit.testing.junit3.example.EdamModule
```

This should make the test fail (as a different Cheese is injected). You can check that the test works by using the default module by running

```
mvn test -Dtest=NamingConventionTest -Dorg.guiceyfruit.modules=
```