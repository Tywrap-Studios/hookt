package org.tywrapstudios.hookt.types.components.data

import kotlinx.serialization.Serializable
import org.tywrapstudios.hookt.types.components.ComponentType

/**
 * The data format for a [org.tywrapstudios.hookt.types.components.ThumbnailComponent].
 *
 * **Not to be confused** with [org.tywrapstudios.hookt.types.Thumbnail]s for [org.tywrapstudios.hookt.types.Embed]s.
 * These thumbnails are **component-based**.
 *
 * @param media An unfurled media item that contains an `attachment://`
 * or `https://` reference to the piece of media
 * @param description An optional description of the media
 * @param spoiler Whether to apply a spoiler blur to the thumbnail
 */
@Serializable
data class ThumbnailData(
    override val type: Int = ComponentType.Thumbnail.value,
    override val id: Int?,
    val media: UnfurledMediaItem,
    val description: String?,
    val spoiler: Boolean?,
) : ComponentData, SectionAccessoryData
