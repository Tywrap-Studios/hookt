package org.tywrapstudios.hookt.types.components

import org.tywrapstudios.hookt.dsl.HooktDsl
import org.tywrapstudios.hookt.dsl.HooktHelperDsl
import org.tywrapstudios.hookt.types.components.data.ContainerChildData
import org.tywrapstudios.hookt.types.components.data.ContainerData
import java.awt.Color

/**
 * A container component. This component visually resembles an embed, though it is
 * not actually an embed and allows for use of components that inherit
 * [ContainerChildComponent] in the content of the embed-like structure.
 *
 * Alongside this, you can set an [accentColor] (similar to [org.tywrapstudios.hookt.types.Embed.color]),
 * but most importantly, apply a [spoiler] blur to the entire embed-like structure
 * (including the [accentColor]). This allows for more apparent spoilers of certain
 * texts where needed.
 */
class ContainerComponent : Component<ContainerData> {
    override var type: ComponentType = ComponentType.Container
    override var id: Int? = null

    /**
     * [ContainerData.components]
     */
    val components: List<ContainerChildData> = mutableListOf()

    /**
     * [ContainerData.accentColor]
     */
    var accentColor: Color? = null

    /**
     * [ContainerData.spoiler]
     */
    var spoiler: Boolean? = null

    /**
     * DSL function to add a [ContainerChildComponent] to the [components].
     *
     * You need to manually specify which component you want to add through
     * the use of type args, then populate the component as usual:
     * ```
     * addComponent<TextDisplayComponent> {
     *     content = "Hello world!"
     * }
     * ```
     *
     * This function may throw errors as it uses reflection to build the component
     * into [ContainerChildData].
     */
    @HooktDsl
    inline fun <reified T : ContainerChildComponent<*>> addComponent(block: T.() -> Unit) {
        val clazz = T::class.java
        (components as MutableList<ContainerChildData>)
            .add(clazz.getConstructor().newInstance().also(block).getData())
    }

    /**
     * Set [accentColor] using an RGB int.
     */
    @HooktHelperDsl
    fun rgb(int: Int) {
        this.accentColor = Color(int)
    }

    /**
     * Set [accentColor] using RGB values.
     * @param r The **red** value
     * @param g The **green** value
     * @param b The **blue** value
     */
    @HooktHelperDsl
    fun rgb(r: Int, g: Int, b: Int) {
        this.accentColor = Color(r, g, b)
    }

    /**
     * Set [accentColor] using a string HEX value.
     */
    @HooktHelperDsl
    fun hex(hex: String) {
        this.accentColor = Color(
            hex
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