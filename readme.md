# BuilderKit

[![apache licensed](https://img.shields.io/badge/License-Apache_2.0-d94c32.svg)](./license-apache.md)
[![builderkit](https://img.shields.io/badge/Kotlin-BuilderKit-3B5998.svg)](https://github.com/atsushi130/BuilderKit.git)
![kotlin](https://img.shields.io/badge/Language-Kotlin-f88909.svg)
[![travis](https://travis-ci.org/atsushi130/BuilderKit.svg?branch=develop)](https://travis-ci.org/atsushi130/BuilderKit)
[![Issues](https://img.shields.io/github/issues-raw/atsushi130/BuilderKit.svg)](https://github.com/atsushi130/BuilderKit/issues)
[![Version](https://img.shields.io/badge/Version-0.3.0-00b7c9.svg)](https://github.com/atsushi130/BuilderKit/issues)

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
    <version>0.3.0</version>
</dependency>
```

**Gradle**
```gradle
compile 'com.github.atsushi130:builderkit:0.3.0'
```
