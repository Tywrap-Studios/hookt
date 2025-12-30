package org.tywrapstudios.hookt.types.components.data

import kotlinx.serialization.Serializable
import org.tywrapstudios.hookt.types.components.ComponentType

/**
 * The data format for a [org.tywrapstudios.hookt.types.components.TextDisplayComponent].
 *
 * @param content The plain text of the display
 */
@Serializable
data class TextDisplayData(
    override val type: Int = ComponentType.TextDisplay.value,
    override val id: Int?,
    val content: String
) : ComponentData, SectionChildData, ContainerChildData
