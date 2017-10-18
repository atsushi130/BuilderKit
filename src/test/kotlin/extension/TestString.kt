package extension

import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import kotlin.test.assertEquals

@RunWith(Enclosed::class)
class TestString {

    class TranslateFirstCharacterToUpperCase {

        @Test
        fun able_to_translate_empty_character() {
            val result = "".translateFirstCharacterToUpperCase()
            assertEquals("", result)
        }

        @Test
        fun able_to_translate_one_character() {
            val result = "a".translateFirstCharacterToUpperCase()
            assertEquals("A", result)
        }

        @Test
        fun able_to_translate_character() {
            val result = "test".translateFirstCharacterToUpperCase()
            assertEquals("Test", result)
        }
    }
}