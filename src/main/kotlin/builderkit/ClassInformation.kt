package builderkit

import com.squareup.kotlinpoet.asClassName
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

internal data class ClassInformation(val className: String, val properties: List<Property>) {
    companion object {
        /**
         * Get class information
         * @param kClass target class
         */
        internal fun from(kClass: KClass<*>): ClassInformation {

            val parameters = kClass.primaryConstructor?.parameters ?: throw BuilderGeneratorError.NotFoundPrimaryConstructor()

            val properties = parameters.map {
                val property = PropertyType.from(it.type)
                val name     = it.name ?: throw BuilderGeneratorError.NotFoundParameterName()
                Property(name, property)
            }

            return ClassInformation(kClass.asClassName().simpleName(), properties)
        }
    }
}