The following injection annotations are supported in GuiceyFruit from 2.0-beta-6 onwards

| **Module** | **Annotation** | Description |
|:-----------|:---------------|:------------|
| JSR 250 from **guiceyfruit-core** | @PostConstruct | A post construction callback method to validate optional injection points are valid. See [Lifecycle](Lifecycle.md) |
|            | @PreDestroy    | A hook for closing resources when the singleton or a custom Scope in Guice is closed via the ` Injectors.close() ` method. See [Lifecycle](Lifecycle.md) |
|            | @Resource      | Binds a field/parameter value to an item in [JNDI](JNDI.md) or named bindings in Guice |
| [Spring](Spring.md) via **guiceyfruit-spring** | @Autowired     | Binds a field/parameter value to an item using its type as the key. The value can be an array, collection or map (with string keys) to bind to all of the available values of a certain type |
|            | @NoAutowire    | A binding annotation which excludes this Guice binding from being used in an @Autowired match |
|            | @Qualifier     | Allows you to annotate an injection point with a named dependency which is then resolved to a @Named binding in Guice. Or you can annotate your own annotations with @Qualifier and with Guice's @BindingAnnotation to associate with a binding or provides method. For more details see [Spring](Spring.md) |
| JPA from **guiceyfruit-jpa** | @PersistenceContext | Used to inject a JPA EntityManager |

## Limitations ##

Currently due to the Guice restriction that @Inject must be used on all constructors we can only use injection annotations like @Resource and @Autowired on fields and methods; not on constructors.

We may try patch Guice to remove this restriction in future versions of GuiceyFruit