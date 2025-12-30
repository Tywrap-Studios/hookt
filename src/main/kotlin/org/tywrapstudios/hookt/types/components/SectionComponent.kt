package org.tywrapstudios.hookt.types.components

import org.tywrapstudios.hookt.dsl.HooktDsl
import org.tywrapstudios.hookt.types.components.data.SectionAccessoryData
import org.tywrapstudios.hookt.types.components.data.SectionChildData
import org.tywrapstudios.hookt.types.components.data.SectionData

/**
 * A section component. Sections are like regular pieces of text, but with two key differences.
 * Firstly, the section can be split into 3 [SectionChildComponent]s, right now you
 * can only yse [TextDisplayComponent]s, however, that might change in the future. Secondly,
 * the section can contain a so-called "accessory". Right now you can only use a
 * [ThumbnailComponent] which displays in the plain message at the
 * top right similar to embeds, however, this may also change in the future.
 */
class SectionComponent : Component<SectionData>, ContainerChildComponent<SectionData> {
    override var type: ComponentType = ComponentType.Section
    override var id: Int? = null

    /**
     * [SectionData.components]
     */
    val components: List<SectionChildData> = mutableListOf()

    /**
     * [SectionData.accessory]
     */
    var accessory: SectionAccessoryData? = null

    /**
     * DSL function to add a [SectionChildComponent] to the [components].
     *
     * Similar to [ContainerComponent]s, you need to manually specify which
     * component you want to add through the use of type args, then populate the
     * component as usual:
     * ```kotlin
     * addComponent<TextDisplayComponent> {
     *     content = "# Hello world!"
     * }
     * addComponent<TextDisplayComponent> {
     *     content = "I am a text display"
     * }
     * ```
     *
     * This function may throw errors as it uses reflection
     * to build the component into [SectionChildData].
     */
    @HooktDsl
    inline fun <reified T : SectionChildComponent<*>> addComponent(block: T.() -> Unit) {
        val clazz = T::class.java
        (components as MutableList<SectionChildData>)
            .add(clazz.getConstructor().newInstance().also(block).getData())
    }

    /**
     * DSL function to set [accessory] to a [SectionAccessoryComponent].
     *
     * Similar to [addComponent], you need to manually specify which
     * component you want to add through the use of type args, then populate the
     * component as usual:
     * ```kotlin
     * addAccessory<ThumbnailComponent> {
     *     media = UnfurledMediaItem("https://foxpictures.com/cute_fox")
     * }
     * ```
     *
     * This function may throw errors as it uses reflection
     * to build the component into [SectionAccessoryData].
     *
     * Uploading files is not supported at the moment, you will have to use `https://` in
     * order to display the media. As for `attachment://`, it
     * is sadly unusable. We're actively working on a file-uploading API.
     */
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