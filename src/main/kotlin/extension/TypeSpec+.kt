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

    this.primaryConstructor(FunSpec.constructorBuilder().addParameters(parameters).build()).addProperties(properties).build()
    return this
}

/**
 * Define build function from class information
 * @param classInformation target class information
 */
internal fun TypeSpec.Builder.defineBuildFunction(classInformation: ClassInformation): TypeSpec.Builder {
    this.addFunction(
            FunSpec.builder("build").addStatement("return ${classInformation.className}(${classInformation.properties.buildModelArguments()})").build())
    return this
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

    this.addFunctions(functions)
    return this
}