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

import builderkit.PropertyType.*
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import utility.*
import kotlin.test.assertEquals

@RunWith(Enclosed::class)
class TestPropertyType {

    class Normal {

        @Test
        fun analyzable() {
            val result = PropertyType.from(String.kType)
            assertEquals(Normal(String::class), result)
        }
    }

    class NestType {

        @Test
        fun analyzable_single_nested() {
            val expect = Generic(List::class, listOf(Normal(String::class)))
            val result = PropertyType.from(String.listStringKType)
            assertEquals(expect, result)
        }

        @Test
        fun analyzable_double_nested() {
            val nestType = Generic(List::class, listOf(Normal(String::class)))
            val expect   = Generic(List::class, listOf(nestType))
            val result   = PropertyType.from(String.doubleNestedListStringKType)
            assertEquals(expect, result)
        }
    }

    class TypeArguments {

        @Test
        fun analyzable_double_type_arguments() {
            val expect = Generic(Pair::class, listOf(Normal(String::class), Normal(String::class)))
            val result = PropertyType.from(String.pairStringKType)
            assertEquals(expect, result)
        }
    }
}