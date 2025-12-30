package org.tywrapstudios.hookt.types.components

import org.tywrapstudios.hookt.types.components.data.TextDisplayData

class TextDisplayComponent : Component<TextDisplayData>, SectionChildComponent<TextDisplayData>,
    ContainerChildComponent<TextDisplayData> {

    override var type: ComponentType = ComponentType.TextDisplay
    override var id: Int? = null
    var content: String? = null

    override fun getData(): TextDisplayData = TextDisplayData(
        id = id,
        content = content ?: throw IllegalStateException("Content for Text Display Component is null")
    )
}