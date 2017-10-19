package extension

import builderkit.Property
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeVariableName

/**
 * Define function
 */
internal fun FunSpec.Companion.define(property: Property, returnType: TypeVariableName, vararg statements: String): FunSpec {

    val (name, type) = property
    val methodName   = "with${name.translateFirstCharacterToUpperCase()}"

    val funSpec = FunSpec.builder(methodName).addParameter(name, TypeVariableName(type.name))
    statements.forEach {
        funSpec.addStatement(it)
    }

    return funSpec.returns(returnType).build()
}