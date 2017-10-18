import builderkit.BuilderGenerator
import builderkit.TestData

class Generator {
    companion object {
        @JvmStatic fun main(vararg args: String) {
            BuilderGenerator.generates(TestData::class)
        }
    }
}
