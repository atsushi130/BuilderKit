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

import com.squareup.kotlinpoet.asClassName
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

internal data class ClassInformation(val className: String, val properties: List<Property>, val packageName: String) {
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

            return ClassInformation(kClass.asClassName().simpleName(), properties, kClass.java.`package`.name)
        }
    }
}