package org.tywrapstudios.hookt.types.components.data

import kotlinx.serialization.Serializable
import org.tywrapstudios.hookt.types.components.ComponentType

@Serializable
data class ThumbnailData(
    override val type: Int = ComponentType.Thumbnail.value,
    override val id: Int?,
    val media: UnfurledMediaItem,
    val description: String?,
    val spoiler: Boolean?,
) : ComponentData, SectionAccessoryData
