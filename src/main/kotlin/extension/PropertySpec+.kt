package extension

import builderkit.ClassInformation
import builderkit.PropertyType
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.PropertySpec

/**
 * Define properties from classInformation
 * @param classInformation Result of class analysis
 * @param mutable is Mutable?
 * @param modifier Access control etc.
 */
internal fun PropertySpec.Companion.defines(classInformation: ClassInformation, mutable: Boolean, modifier: KModifier): List<PropertySpec> {
    return classInformation.properties.map { (name, property) ->
        val propertySpec = when (property) {
            is PropertyType.Normal  -> PropertySpec.builder(name, property.rawType)
            is PropertyType.Generic -> PropertySpec.builder(name, property.typeVariableName)
        }

        propertySpec.initializer(name).mutable(mutable).addModifiers(modifier).build()
    }
}
