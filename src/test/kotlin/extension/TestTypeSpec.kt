/*
 * Copyright (C) 2017 Atsushi Miyake.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package extension

import builderkit.*
import com.squareup.kotlinpoet.*
import org.junit.Test
import kotlin.test.assertEquals

class TestTypeSpec {

    private val testProperty         = PropertyBuilder().withName("property").build()
    private val testClassInformation = ClassInformationBuilder().withClassName("Test").withProperties(this.testProperty).build()

    @Test
    fun buildable_primary_constructor() {

        val (name, type) = this.testProperty
        val parameterSpec = when (type) {
            is PropertyType.Normal  -> ParameterSpec.builder(name, TypeVariableName(type.name))
            is PropertyType.Generic -> ParameterSpec.builder(name, type.typeVariableName)
        }

        val propertySpec = when (type) {
            is PropertyType.Normal  -> PropertySpec.builder(name, TypeVariableName(type.name))
            is PropertyType.Generic -> PropertySpec.builder(name, type.typeVariableName)
        }

        val parameter = parameterSpec.build()
        val property  = propertySpec.initializer(name).mutable(true).addModifiers(KModifier.PRIVATE).build()

        val expect = TypeSpec.classBuilder(this.testClassInformation.className)
                             .primaryConstructor(FunSpec.constructorBuilder().addParameter(parameter).build()).addProperty(property)
                             .build()

        val result = TypeSpec.classBuilder(this.testClassInformation.className).definePrimaryConstructor(this.testClassInformation).build()

        assertEquals(expect, result)
    }

    @Test
    fun buildable_build_function() {

        val properties = listOf(this.testProperty)
        val expect = TypeSpec.classBuilder(this.testClassInformation.className)
                             .addFunction(
                                     FunSpec.builder("build")
                                            .addStatement("return ${this.testClassInformation.className}(${properties.buildModelArguments()})")
                                            .build()
                             ).build()

        val result = TypeSpec.classBuilder(this.testClassInformation.className).defineBuildFunction(this.testClassInformation).build()

        assertEquals(expect, result)
    }

    @Test
    fun buildable_with_method() {

        val (name, type) = this.testProperty
        val returnType   = TypeVariableName("${this.testClassInformation.className}Builder")

        val methodName = "with${name.translateFirstCharacterToUpperCase()}"
        val funSpec    = when (type) {
            is PropertyType.Normal  -> FunSpec.builder(methodName).addParameter(name, TypeVariableName(type.name))
            is PropertyType.Generic -> FunSpec.builder(methodName).addParameter(name, type.typeVariableName)
        }

        val function = funSpec.addStatement("this.$name = $name")
                              .addStatement("return this")
                              .returns(returnType)
                              .build()

        val expect = TypeSpec.classBuilder(this.testClassInformation.className).addFunction(function).build()
        val result = TypeSpec.classBuilder(this.testClassInformation.className).defineWithFunctions(this.testClassInformation).build()
        assertEquals(expect, result)
    }
}