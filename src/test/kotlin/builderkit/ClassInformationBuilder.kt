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

internal class ClassInformationBuilder(
        private var className: String = "className",
        private var properties: List<Property> = listOf(),
        private var packageName: String = "builderkit"
) {

    fun build(): ClassInformation = ClassInformation(this.className, this.properties, this.packageName)

    fun withClassName(className: String): ClassInformationBuilder {
        this.className = className
        return this
    }

    fun withProperties(vararg properties: Property): ClassInformationBuilder {
        this.properties = properties.asList()
        return this
    }

    fun withPackageName(packageName: String): ClassInformationBuilder {
        this.packageName = packageName
        return this
    }
}