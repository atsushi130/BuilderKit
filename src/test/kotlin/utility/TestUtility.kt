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

package utility

import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.full.memberProperties

private fun String.Companion.toKTypeFrom(kClass: KClass<*>): KType {
    return kClass.memberProperties.first().returnType
}

val String.Companion.kType: KType
    get() {
        data class Mock(val arg: String)
        return this.toKTypeFrom(Mock::class)
    }

val String.Companion.listStringKType: KType
    get() {
        data class Mock(val arg: List<String>)
        return this.toKTypeFrom(Mock::class)
    }

val String.Companion.doubleNestedListStringKType: KType
    get() {
        data class Mock(val arg: List<List<String>>)
        return this.toKTypeFrom(Mock::class)
    }

val String.Companion.pairStringKType: KType
    get() {
        data class Mock(val arg: Pair<String, String>)
        return this.toKTypeFrom(Mock::class)
    }
