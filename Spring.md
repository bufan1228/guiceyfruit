as of 2.0-beta-6 or later, GuiceyFruit supports the use of Spring injection annotations (e.g. **@Autowired**) along with Spring lifecycle interfaces (**InitializingBean**, **DisposableBean**).

If you have some existing code which is using a combination of JSR250 and Spring annontations you can let GuiceyFruit inject your beans using the Guice module mechanism.

For example a bean with the following combination of @Autowired and @Resource would be injected by GuiceyFruit...

```
public class MyBean {
  @Autowired
  SomeDto someDto;

  @Resource
  DataSource customerDataSource;
```

You just need to ensure you add the SpringModule into your Guice injector. For example

```
Injector injector = Guice.createInjector(new SpringModule(), 
  new AbsractModule() {
    public void configure() {
       ... 
    }
  });
```

This also supports the [Lifecycle](Lifecycle.md) support from JSR 250 as well as invoking the Spring InitializingBean or DisposableBean interfaces

## Using @Qualifier ##

You can use the @Qualifier annotation with @Autowired to choose which instance gets injected in the usual Spring way. The difference is that @Qualifier is not annotated with the Guice @BindingAnnotation (unfortunately!), so instead we map @Qualifier annotations to @Named annotations in Guice.

So your bean could look like this

```
public class MyBean {
  @Autowired
  @Qualifier("foo")
  SomeDto someDto;
```

You can then bind some implementation like this

```
Injector injector = Guice.createInjector(new SpringModule(), 
  new AbsractModule() {
    public void configure() {
       bind(Key.get(MyBean.class, Names.named("foo")); 
    }
  });
```

Or you can use the helper method in GuiceyFruitModule to simplify that code a little

```
Injector injector = Guice.createInjector(new SpringModule(), 
  new GuiceyFruitModule() {
    public void configure() {
       bind(MyBean.class, "foo"); 
    }
  });
```


## Custom Qualifier Annotations ##

Spring allows you to create your own qualifier annotations which are themselves annotated with @Qualifier. These are quite like Guice's binding annotations which are annotated with @BindingAnnotation.

To use these with GuiceyFruit just make sure you also annotate the qualifier annotation with the **@BindingAnnotation**

For example

```
  @Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE })
  @Retention(RetentionPolicy.RUNTIME)
  @Qualifier
  @BindingAnnotation
  public static @interface TestQualifier {
  }
```

Then you can use this in a bean

```
public class MyBean {
  @Autowired
  @TestQualifier
  SomeDto someDto;
```

You can then bind some implementation like this

```
Injector injector = Guice.createInjector(new SpringModule(), 
  new GuiceyFruitModule() {
    public void configure() {
       bind(MyBean.class, TestQualifier.class); 
    }
  });
```

## Dependencies ##

If you use [Maven](Maven.md) you just need to add a dependency on **guiceyfruit-spring** (see more details on the [Maven](Maven.md) page)

So add the following to your repositories section
```
  <repositories>
    <repository>
      <id>guiceyfruit.release</id>
      <name>GuiceyFruit Release Repository</name>
      <url>http://guiceyfruit.googlecode.com/svn/repo/releases/</url>
      <snapshots>
        <enabled>false</enabled>
      </snapshots>
      <releases>
        <enabled>true</enabled>
      </releases>
    </repository>
  </repositories>
```

Then add a dependency on guiceyfruit-spring

```
    <dependency>
      <groupId>org.guiceyfruit</groupId>
      <artifactId>guiceyfruit-spring</artifactId>
      <version>2.0-beta-6</version>
    </dependency>
```