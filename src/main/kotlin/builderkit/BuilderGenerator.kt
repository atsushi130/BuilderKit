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

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.TypeSpec
import extension.defineBuildFunction
import extension.defineWithFunctions
import extension.definePrimaryConstructor
import java.io.File
import kotlin.reflect.KClass

class BuilderGenerator(private val indent: String, private val path: File) {

    /**
     * Generate kClasses Builder class
     * @param kClasses target classes
     */
    fun generates(vararg kClasses: KClass<*>) {
        BuilderGenerator.generates(kClasses.toList(), this.indent, this.path)
    }

    companion object {

        /**
         * Default output file path
         */
        private val path = File("generate/src")

        /**
         * Default indent
         */
        private val defaultIndent = "    "

        /**
         * Generate kClasses Builder class
         * @param kClasses target classes
         */
        fun generates(vararg kClasses: KClass<*>) {
            this.generates(kClasses.toList(), this.defaultIndent, this.path)
        }

        /**
         * Generate kClasses Builder class
         * @param kClasses target classes
         * @param indent indent
         * @param path output file path
         */
        private fun generates(kClasses: List<KClass<*>>, indent: String, path: File) {
            kClasses.forEach {
                this.generate(it, indent, path)
            }
        }

        /**
         * Generate kClass Builder class
         * @param kClass target class
         * @param indent indent
         * @param path output file path
         */
        private fun generate(kClass: KClass<*>, indent: String, path: File) {

            val classInformation = ClassInformation.from(kClass)

            val file = FileSpec.builder(classInformation.packageName, "${classInformation.className}Builder").indent(indent)
                    .addType(
                            TypeSpec.classBuilder("${classInformation.className}Builder")
                                    // Define primary constructor
                                    .definePrimaryConstructor(classInformation)
                                    // Define build method
                                    .defineBuildFunction(classInformation)
                                    // Define with method
                                    .defineWithFunctions(classInformation).build()
                    ).build()

            file.writeTo(path)
        }
    }
}
