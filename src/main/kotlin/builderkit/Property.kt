package builderkit

internal typealias Property = Pair<String, PropertyType>
internal fun List<Property>.buildModelArguments(): String {
    return this.joinToString { (name, _) ->
        "this.$name"
    }
}