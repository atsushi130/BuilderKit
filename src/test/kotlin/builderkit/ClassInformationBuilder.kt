package builderkit

class ClassInformationBuilder(private var className: String = "className", private var properties: List<Property> = listOf()) {

    fun build(): ClassInformation = ClassInformation(this.className, this.properties)

    fun withClassName(className: String): ClassInformationBuilder {
        this.className = className
        return this
    }

    fun withProperties(vararg properties: Property): ClassInformationBuilder {
        this.properties = properties.asList()
        return this
    }
}