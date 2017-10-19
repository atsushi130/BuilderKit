package extension

/**
 * First character translate to UpperCase
 * @return translated string
 */
internal fun String.translateFirstCharacterToUpperCase(): String {
    return when (this.length) {
        0 -> ""
        1 -> this.toUpperCase()
        else -> this[0].toUpperCase() + this.substring(1)
    }
}