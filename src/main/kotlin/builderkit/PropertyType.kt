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

package builderkit

import com.squareup.kotlinpoet.TypeVariableName
import com.squareup.kotlinpoet.asClassName
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.jvm.jvmErasure

internal sealed class PropertyType(open val rawType: KClass<*>, open val optional: Boolean) {

    open val name: String
        get() {
            val typeString = this.rawType.asClassName().simpleName()
            return when (this.optional) {
                true  -> typeString + "?"
                false -> typeString
            }
        }

    val typeVariableName: TypeVariableName
            get() = TypeVariableName(this.name, this.rawType)

    companion object {
        fun from(returnType: KType): PropertyType = when (returnType.arguments.isEmpty()) {
            true  -> Normal(returnType.jvmErasure, returnType.isMarkedNullable)
            false -> {
                val types = returnType.arguments.map {
                    this.from(it.type!!)
                }
                Generic(returnType.jvmErasure, returnType.isMarkedNullable, types)
            }
        }
    }

    data class Normal(override val rawType: KClass<*>, override val optional: Boolean): PropertyType(rawType, optional)

    data class Generic(
            override val rawType: KClass<*>, override val optional: Boolean, private val typeArguments: List<PropertyType>
    ): PropertyType(rawType, optional) {
        override val name: String
            get() {
                val typeString = this.rawType.asClassName().simpleName() + "<" + this.typeArguments.joinToString { it.name } + ">"
                return when (this.optional) {
                    true  -> typeString + "?"
                    false -> typeString
                }
            }
    }
}