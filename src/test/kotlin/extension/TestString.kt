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