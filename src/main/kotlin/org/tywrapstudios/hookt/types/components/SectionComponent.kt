package org.tywrapstudios.hookt.types.components

import org.tywrapstudios.hookt.dsl.HooktDsl
import org.tywrapstudios.hookt.types.components.data.SectionAccessoryData
import org.tywrapstudios.hookt.types.components.data.SectionChildData
import org.tywrapstudios.hookt.types.components.data.SectionData
import kotlin.jvm.java

class SectionComponent : Component<SectionData>, ContainerChildComponent<SectionData> {
    override var type: ComponentType = ComponentType.Section
    override var id: Int? = null
    val components: List<SectionChildData> = mutableListOf()
    var accessory: SectionAccessoryData? = null

    @HooktDsl
    inline fun <reified T : SectionChildComponent<*>> addComponent(block: T.() -> Unit) {
        val clazz = T::class.java
        (components as MutableList<SectionChildData>)
            .add(clazz.getConstructor().newInstance().also(block).getData())
    }

    @HooktDsl
    inline fun <reified T : SectionAccessoryComponent<*>> addAccessory(block: T.() -> Unit) {
        val clazz = T::class.java
        accessory = clazz.getConstructor().newInstance().also(block).getData()
    }

    override fun getData(): SectionData {
        if (components.size !in 1..3) {
            throw IllegalStateException("Sections should contain 1-3 Components")
        }
        return SectionData(
            id = id,
            components = components,
            accessory = accessory ?: throw IllegalStateException("Accessory for Section Component is null"),
        )
    }
}