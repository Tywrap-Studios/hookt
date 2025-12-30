package org.tywrapstudios.hookt.types.components

import org.tywrapstudios.hookt.types.components.data.TextDisplayData

/**
 * A text display component. This component displays simple text, the text can be
 * decorated using Discord's Markdown features such as **bold text**, *italics* and
 * `code`.
 *
 * This component can be used in both [ContainerComponent]s and [SectionComponent]s,
 * making it quite versatile.
 */
class TextDisplayComponent : Component<TextDisplayData>, SectionChildComponent<TextDisplayData>,
    ContainerChildComponent<TextDisplayData> {

    override var type: ComponentType = ComponentType.TextDisplay
    override var id: Int? = null

    /**
     * [TextDisplayData.content]
     */
    var content: String? = null

    override fun getData(): TextDisplayData = TextDisplayData(
        id = id,
        content = content ?: throw IllegalStateException("Content for Text Display Component is null")
    )
}