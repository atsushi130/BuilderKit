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
