# BuilderKit

[![apache licensed](https://img.shields.io/badge/license-Apache_2.0-d94c32.svg)](./license-apache.md)
[![builderkit](https://img.shields.io/badge/kotlin-BuilderKit-3B5998.svg)](https://github.com/atsushi130/BuilderKit.git)
![kotlin](https://img.shields.io/badge/Language-Kotlin-f88909.svg)
[![travis](https://travis-ci.org/atsushi130/BuilderKit.svg?branch=develop)](https://travis-ci.org/atsushi130/BuilderKit)

## Usage
First, implement Generator class to `/src/gen/kotlin`.
```kotlin
class Generator {
    companion object {
        @JvmStatic fun main(vararg args: String) {
            BuilderGenerator.generates(ModelClass::class)
        }
    }
}
```

Second, make a directory for generator.
```
❯ mkdir generate/src
```

## Dependency
**Maven**
```xml
<dependency>
    <groupId>com.github.atsushi130</groupId>
    <artifactId>builderkit</artifactId>
    <version>0.1.2</version>
</dependency>
```