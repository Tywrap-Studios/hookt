package org.tywrapstudios.hookt.types.components.data

import kotlinx.serialization.Serializable
import org.tywrapstudios.hookt.types.components.ComponentType

/**
 * The data format for a [org.tywrapstudios.hookt.types.components.MediaGalleryComponent].
 * 
 * @param items A list of gallery media items
 * @see MediaGalleryItem
 */
@Serializable
data class MediaGalleryData(
    override val type: Int = ComponentType.MediaGallery.value,
    override val id: Int?,
    val items: List<MediaGalleryItem>
) : ComponentData, ContainerChildData

/**
 * An item containing media and other options to display
 * in a [org.tywrapstudios.hookt.types.components.MediaGalleryComponent].
 * 
 * @param media An unfurled media item that contains an `attachment://` or `https://`
 * reference to the piece of media
 * @param description An optional description of the media
 * @param spoiler Whether to apply a spoiler blur to the media
 */
@Serializable
data class MediaGalleryItem(
    val media: UnfurledMediaItem,
    val description: String?,
    val spoiler: Boolean?,
)
