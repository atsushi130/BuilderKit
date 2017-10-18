package builderkit

import org.junit.Test
import kotlin.test.assertEquals

data class TestData(val zArg: Int, val xArg: String)

class TestClassInformation {

    @Test
    fun analyzable() {

        val testProperty1  = PropertyBuilder().withName("zArg").withType(PropertyType.Normal(Int::class)).build()
        val testProperty2  = PropertyBuilder().withName("xArg").withType(PropertyType.Normal(String::class)).build()
        val expect         = ClassInformationBuilder().withClassName("TestData").withProperties(testProperty1, testProperty2).build()

        val result = ClassInformation.from(TestData::class)
        assertEquals(expect, result)
    }
}