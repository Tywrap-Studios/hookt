package org.tywrapstudios.hookt.types.components.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.tywrapstudios.hookt.types.components.ComponentType

@Serializable
data class MediaGalleryData(
    override val type: Int = ComponentType.MediaGallery.value,
    override val id: Int?,
//    val type: Int = ComponentType.MediaGallery.value,
//    val id: Int?,
    val items: List<MediaGalleryItem>
) : ComponentData, ContainerChildData

@Serializable
data class MediaGalleryItem(
    val media: UnfurledMediaItem,
    val description: String?,
    val spoiler: Boolean?,
)
