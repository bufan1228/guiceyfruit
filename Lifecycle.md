GuiceyFruit supports pluggable Lifecycle support. This means your beans or POJOs can be notified when they have completed being injected, or when they are being destroyed (if they are within a Scope like Singleton, Session, Request or [TestScope](Testing.md)) such that there is a clear point when the scope goes away).

The simplest way to work with lifecycles is to use JSR 250 the standard annotations used in EJB3 and Spring.

```
public class MyBean {

  @PostConstruct
  public void start() {...}

  @PreDestroy
  public void stop() {...}
}
```

The above MyBean will have its start() method called after it has been injected.

### Invoking @PreDestroy ###

Note that GuiceyFruit only supports the invocation of @PreDestroy hooks on objects bound to a scope - when that scope is explicitly closed.

For example if the above bean is bound into the Singleton scope either like this

```
@Singleton
public class MyBean {

  @PreDestroy
  public void stop() {...}
}
```

or this

```
Guice.createInjector(new AbstractModule() {
  public void configure() {
    bind(MyBean.class).in(Singleton.class);
  }
});
```

Then if the singleton scope is closed as follows

```
Injectors.close(injector);
```


Then the stop() method is invoked on your bean.

If you are using a custom scope instead of Singleton then you can close that scope via

```
Injectors.close(injector, MyScopeAnnotation.class);
```

**Note** that if you are using [Testing](Testing.md) with the **@TestScoped** and **@ClassScoped** annotations then these scopes are implicitly closed for you by the [Testing](Testing.md) framework - along with the singleton scope.

## Enabling JSR 250 ##

To enable the JSR 250 lifecycle support you need to add the **Jsr250Module to your injector.**

This has the additional benefit of supporting @Resource injection using any JNDI provider such as the [Guicey JNDI](JNDI.md)

## Enabling Spring ##

Spring supports InitializingBean and DisposableBean interfaces on its beans for lifecycle. Its quite common that spring related libraries use either JSR 250 or these Spring interfaces.

To enable the Spring lifecycles, just add the **SpringModule** to your injector.