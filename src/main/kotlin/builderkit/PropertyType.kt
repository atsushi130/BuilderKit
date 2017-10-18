package builderkit

import com.squareup.kotlinpoet.TypeVariableName
import com.squareup.kotlinpoet.asClassName
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.jvm.jvmErasure

sealed class PropertyType(open val rawType: KClass<*>) {

    open val name: String
        get() = this.rawType.asClassName().simpleName()

    companion object {
        fun from(returnType: KType): PropertyType = when (returnType.arguments.size) {
            0 -> Normal(returnType.jvmErasure)
            else -> {
                val types = returnType.arguments.map {
                    this.from(it.type!!)
                }
                Generic(returnType.jvmErasure, types)
            }
        }
    }

    data class Normal(override val rawType: KClass<*>): PropertyType(rawType)

    data class Generic(override val rawType: KClass<*>, private val typeArguments: List<PropertyType>): PropertyType(rawType) {
        override val name: String
            get() {
                return this.rawType.asClassName().simpleName() + "<" + this.typeArguments.joinToString { it.name } + ">"
            }

        val typeVariableName: TypeVariableName
            get() = TypeVariableName(this.name, this.rawType)
    }
}