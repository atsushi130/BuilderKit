# BuilderKit

[![apache licensed](https://img.shields.io/badge/License-Apache_2.0-d94c32.svg)](./license-apache.md)
[![builderkit](https://img.shields.io/badge/Kotlin-BuilderKit-3B5998.svg)](https://github.com/atsushi130/BuilderKit.git)
![kotlin](https://img.shields.io/badge/Language-Kotlin-f88909.svg)
[![travis](https://travis-ci.org/atsushi130/BuilderKit.svg?branch=develop)](https://travis-ci.org/atsushi130/BuilderKit)
[![Issues](https://img.shields.io/github/issues-raw/atsushi130/BuilderKit.svg)](https://github.com/atsushi130/BuilderKit/issues)
[![Version](https://img.shields.io/maven-central/v/com.github.atsushi130/builderkit.svg)](https://search.maven.org/#search|gav|1|g%3A"com.github.atsushi130"%20AND%20a%3A"builderkit")

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

## Outout Builder class
**Sample model class**
```kotlin
data class ModelClass(val arg1: Int, val arg2: List<String>, val arg3: MyClass)
```

**Output builder class**
```kotlin
class ModelClassBuilder(
        private var arg1: Int,
        private var arg2: List<String>,
        private var arg3: MyClass
) {
    fun build() = ModelClass(this.arg1, this.arg2, this.arg3)

    fun withArg1(arg1: Int): ModelClassBuilder {
        this.arg1 = arg1
        return this
    }

    fun withArg2(arg2: List<String>): ModelClassBuilder {
        this.arg2 = arg2
        return this
    }

    fun withArg3(arg3: MyClass): ModelClassBuilder {
        this.arg3 = arg3
        return this
    }
}
```

## Dependency
**Maven**
```xml
<dependency>
    <groupId>com.github.atsushi130</groupId>
    <artifactId>builderkit</artifactId>
    <version>0.4.0</version>
</dependency>
```

**Gradle**
```gradle
compile 'com.github.atsushi130:builderkit:0.4.0'
```
