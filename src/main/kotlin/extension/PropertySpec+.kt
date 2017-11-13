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
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec

/**
 * Define properties from classInformation
 * @param classInformation Result of class analysis
 * @param mutable is Mutable?
 * @param modifier Access control etc.
 */
internal fun PropertySpec.Companion.defines(classInformation: ClassInformation, mutable: Boolean, modifier: KModifier): List<PropertySpec> {
    return classInformation.properties.map { (name, property) ->
        PropertySpec.builder(name, property.typeVariableName).initializer(name).mutable(mutable).addModifiers(modifier).build()
    }
}
