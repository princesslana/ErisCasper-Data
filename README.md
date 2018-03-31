# ErisCasper-Data

[![Build Status](https://travis-ci.org/princesslana/ErisCasper-Data.svg?branch=master)](https://travis-ci.org/princesslana/ErisCasper.java)

**Latest Release:** 
![Maven Central](https://img.shields.io/maven-central/v/com.github.princesslana/ErisCasper-Data.svg)
[![Javadocs](http://javadoc.io/badge/com.github.princesslana/ErisCasper-Data.svg)](http://javadoc.io/doc/com.github.princesslana/ErisCasper-Data)

**Latest Snapshot:** ![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/https/oss.sonatype.org/com.github.princesslana/ErisCasper-Data.svg)

Documentation for the latest release is available at https://princesslana.github.io/ErisCasper-Data

## Snapshots

Snapshots of master are automatically published to sonatype.

To use from maven add the sonatype snapshot repository:

```xml
  <repositories>
    <repository>
      <id>ossrh</id>
      <url>https://oss.sonatype.org/content/repositories/snapshots</url>
      <snapshots>
        <enabled>true</enabled>
      </snapshots>
    </repository>
  </repositories>
```

And add the ErisCasper-Data dependency.
Snapshot versions can be found [here](https://oss.sonatype.org/#nexus-search;quick~ErisCasper-Data).

```xml
  <dependency>
    <groupId>com.github.princesslana</groupId>
    <artifactId>ErisCasper-Data</artifactId>
    <version>LATEST.SNAPSHOT.VERSION</version>
  </dependency>
```
