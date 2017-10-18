package builderkit

typealias Property = Pair<String, PropertyType>
fun List<Property>.buildModelArguments(): String {
    return this.joinToString { (name, _) ->
        "this.$name"
    }
}