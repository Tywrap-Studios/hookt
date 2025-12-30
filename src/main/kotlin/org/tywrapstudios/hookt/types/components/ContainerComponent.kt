package org.tywrapstudios.hookt.types.components

import org.tywrapstudios.hookt.dsl.HooktDsl
import org.tywrapstudios.hookt.types.components.data.ContainerChildData
import org.tywrapstudios.hookt.types.components.data.ContainerData
import java.awt.Color

class ContainerComponent : Component<ContainerData> {
    override var type: ComponentType = ComponentType.Container
    override var id: Int? = null
    val components: List<ContainerChildData> = mutableListOf()
    var accentColor: Color? = null
    var spoiler : Boolean? = null

    @HooktDsl
    inline fun <reified T : ContainerChildComponent<*>> addComponent(block: T.() -> Unit) {
        val clazz = T::class.java
        (components as MutableList<ContainerChildData>)
            .add(clazz.getConstructor().newInstance().also(block).getData())
    }

    /**
     * Set [accentColor] using an RGB int.
     */
    fun rgb(int: Int) {
        this.accentColor = Color(int)
    }

    /**
     * Set [accentColor] using RGB values.
     * @param r The **red** value
     * @param g The **green** value
     * @param b The **blue** value
     */
    fun rgb(r: Int, g: Int, b: Int) {
        this.accentColor = Color(r, g, b)
    }

    /**
     * Set [accentColor] using a string HEX value.
     */
    fun hex(hex: String) {
        this.accentColor = Color(hex
            .replace("#", "")
            .replace("0x", "")
            .hexToInt()
        )
    }

    override fun getData(): ContainerData {
        val color = accentColor?.rgb?.and(0xFFFFFF)
        return ContainerData(
            id = id,
            components = components,
            accentColor = color,
            spoiler = spoiler,
        )
    }
}