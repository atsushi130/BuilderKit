package builderkit

sealed class BuilderGeneratorError(message: String): Exception(message) {
    class NotFoundPrimaryConstructor: BuilderGeneratorError("Not found primary constructor.")
    class NotFoundParameterName: BuilderGeneratorError("Not found parameter name.")
}