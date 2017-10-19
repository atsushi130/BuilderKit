package extension

import builderkit.ClassInformation
import builderkit.PropertyType
import com.squareup.kotlinpoet.ParameterSpec

/**
 * Define parameters from classInformation
 * @param classInformation Result of class analysis
 */
internal fun ParameterSpec.Companion.defines(classInformation: ClassInformation): List<ParameterSpec> {
    return classInformation.properties.map { (name, property) ->

        val parameterSpec = when (property) {
            is PropertyType.Normal  -> ParameterSpec.builder(name, property.rawType)
            is PropertyType.Generic -> ParameterSpec.builder(name, property.typeVariableName)
        }

        parameterSpec.build()
    }
}