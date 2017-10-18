package builderkit

import utility.kType

class PropertyBuilder(private var name: String = "propertyName", private var type: PropertyType = PropertyType.from(String.kType)) {

    fun build(): Property = Property(this.name, this.type)

    fun withName(name: String): PropertyBuilder {
        this.name = name
        return this
    }

    fun withType(type: PropertyType): PropertyBuilder {
        this.type = type
        return this
    }
}
