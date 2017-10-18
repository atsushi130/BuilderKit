package builderkit

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.TypeSpec
import extension.defineBuildFunction
import extension.defineWithFunctions
import extension.definePrimaryConstructor
import java.io.File
import kotlin.reflect.KClass

class BuilderGenerator {
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
         * @param indent indent
         * @param path output file path
         */
        fun generates(vararg kClasses: KClass<*>, indent: String = builderkit.BuilderGenerator.Companion.defaultIndent, path: File = builderkit.BuilderGenerator.Companion.path) {
            kClasses.forEach {
                BuilderGenerator.Companion.generate(it, indent, path)
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

            val file = FileSpec.builder("", "${classInformation.className}Builder").indent(indent)
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
