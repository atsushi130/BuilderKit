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

import builderkit.ClassInformation
import builderkit.buildModelArguments
import com.squareup.kotlinpoet.*

/**
 * Define primary constructor from class information
 * @param classInformation target class information
 */
internal fun TypeSpec.Builder.definePrimaryConstructor(classInformation: ClassInformation): TypeSpec.Builder {

    val parameters = ParameterSpec.defines(classInformation)
    val properties = PropertySpec.defines(classInformation, true, KModifier.PRIVATE)

    return this.primaryConstructor(FunSpec.constructorBuilder().addParameters(parameters).addModifiers(KModifier.PRIVATE).build()).addProperties(properties)
}

/**
 * Define build function from class information
 * @param classInformation target class information
 */
internal fun TypeSpec.Builder.defineBuildFunction(classInformation: ClassInformation): TypeSpec.Builder {
    val function = FunSpec.builder("build").addStatement("return ${classInformation.className}(${classInformation.properties.buildModelArguments()})").build()
    return this.addFunction(function)
}

/**
 * Define Functions from class information
 * @param classInformation target class information
 */
internal fun TypeSpec.Builder.defineWithFunctions(classInformation: ClassInformation): TypeSpec.Builder {

    val returnType = TypeVariableName("${classInformation.className}Builder")
    val functions  = classInformation.properties.map {
        FunSpec.define(it, returnType, "this.${it.first} = ${it.first}", "return this")
    }

    return this.addFunctions(functions)
}

/**
 * Define companion object
 * @param classInformation target class information
 */
internal fun TypeSpec.Builder.defineCompanion(classInformation: ClassInformation): TypeSpec.Builder {
    val companionProperty = PropertySpec.defineCompanion(classInformation.className).build()
    return this.companionObject(TypeSpec.companionObjectBuilder().addProperty(companionProperty).build())
}