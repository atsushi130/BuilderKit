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

import org.junit.Test
import kotlin.test.assertEquals

data class TestData(val zArg: Int, val xArg: String?)

class TestClassInformation {

    @Test
    fun analyzable() {

        val testProperty1  = PropertyBuilder().withName("zArg").withType(PropertyType.Normal(Int::class, false)).build()
        val testProperty2  = PropertyBuilder().withName("xArg").withType(PropertyType.Normal(String::class, true)).build()
        val expect         = ClassInformationBuilder().withClassName("TestData").withProperties(testProperty1, testProperty2).build()

        val result = ClassInformation.from(TestData::class)
        assertEquals(expect, result)
    }
}