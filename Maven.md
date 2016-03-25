If you use [maven](http://maven.apache.org/) then just update your pom.xml to reference the GuiceyFruit repository

For releases use the [release repo](http://guiceyfruit.googlecode.com/svn/repo/releases/)

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

Or snapshots use the [snapshots repo](http://guiceyfruit.googlecode.com/svn/repo/snapshots/)
```
  <repositories>
    <repository>
      <id>guiceyfruit.snapshot</id>
      <name>GuiceyFruit Snapshot Repository</name>
      <url>http://guiceyfruit.googlecode.com/svn/repo/snapshots/</url>
      <snapshots>
        <enabled>true</enabled>
      </snapshots>
      <releases>
        <enabled>false</enabled>
      </releases>
    </repository>
  </repositories>
```

Then you can just add a dependency on GuiceyFruit such as

```
    <dependency>
      <groupId>org.guiceyfruit</groupId>
      <artifactId>guiceyfruit-core</artifactId>
      <version>2.0-beta-7</version>
    </dependency>
```