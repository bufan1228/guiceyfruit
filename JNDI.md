The GuiceyFruit JNDI provider makes it easy to create a JNDI context using Guice as the dependency injection mechanism which lets you look objects up in JNDI and then delegate to Guice to dependency inject them.

## Creating the GuiceyFruit JNDI provider ##

To create a Guice JNDI provider you need to create a **jndi.properties** file on the classpath. Then you can use JNDI as follows

```
InitialContext context = new InitialContext();
```

Or if you prefer you can configure a Hashtable with the entries inside (which we'll discuss shortly)...

```
Hashtable properties = new Hashtable();
// setup the properties...
InitialContext context = new InitialContext(properties);
```

You can then look up objects in JNDI; which under the covers will cause Guice to inject objects.

For example

```
  Injector injector = (Injector) context.lookup("com.google.inject.Injector");
```

### Specifiying the properties ###

You must specify the following properties as a bare minimum

```
# Guice JNDI provider
java.naming.factory.initial = org.guiceyfruit.jndi.GuiceInitialContextFactory

# list of guice modules to boot up (space separated)
org.guiceyfruit.modules = com.acme.MyModule
```

The **org.guiceyfruit.modules** defines a space separated list of Guice modules to add to the Guice Injector.

## Binding to @Named injection points ##

The entries in the jndi.properties file will be used to bind any @Named injection points in your guice beans.

For example if you used this properties file

```
# Guice JNDI provider
java.naming.factory.initial = org.guiceyfruit.jndi.GuiceInitialContextFactory

# list of guice modules to boot up (space separated)
org.guiceyfruit.modules = com.acme.MyModule

# guice injection properties
cheese.type = Edam
```

Then this would bind to an instance of

```
public class Cheese {
  @Inject
  public Cheese(@Named("cheese.type") String type) {...
  }
}
```

## Exposing Injector bindings in JNDI ##

Any binding in Guice which has no other binding annotations attached is visible using its bound type name. For example to lookup the Guice Injector in JNDI you can use

```
  Injector injector = (Injector) context.lookup("com.google.inject.Injector");
```

By default any @Named instance will be exposed in JNDI using the naming convention

```
className/name
```

In addition you can attach the @JndiBind annotation to any class or provider method to expose objects directly into JNDI.

For example

```
@JndiBind("cheese")
public class Cheese {

  @Inject
  public Cheese(@Named("cheese.type") String type) {...
  }
}
```

The Cheese object will be bound to the JNDI name "cheese" and be injected with the "cheese.type" entry in the jndi.properties file.

Another option is to use the annotation in a module's provider method

```
public class ExampleModule extends AbstractModule {
  ...

  @Provides
  @JndiBind("blah")
  public MyBean makeBlah() {
    return new MyBean(new AnotherBean("Blah.another"), "Blah");
  }
}
```

Which will bind the result of makeBlah() to the JNDI name  "blah"

### Custom bindings using the properties file ###

You can also map Guice bindings to custom JNDI names using the properties file.

For example this jndi.properties file would expose the Guice injector as a JNDI name **myInjector**

```
# Guice JNDI provider
java.naming.factory.initial = org.guiceyfruit.jndi.GuiceInitialContextFactory

# list of guice modules to boot up (space separated)
org.guiceyfruit.modules = org.guiceyfruit.jndi.example.ExampleModule

# JNDI entries to create from bindings in the Guice Injector
org.guiceyfruit.jndi/myInjector = com.google.inject.Injector
```

Basically you use the format

```
org.guiceyfruit.jndi/jndiName = bindingClassName
```