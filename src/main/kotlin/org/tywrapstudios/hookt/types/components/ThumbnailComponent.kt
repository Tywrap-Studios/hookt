package org.tywrapstudios.hookt.types.components

import org.tywrapstudios.hookt.types.components.data.ThumbnailData
import org.tywrapstudios.hookt.types.components.data.UnfurledMediaItem

class ThumbnailComponent : Component<ThumbnailData>, SectionAccessoryComponent<ThumbnailData> {
    override var type: ComponentType = ComponentType.Thumbnail
    override var id: Int? = null
    var media: UnfurledMediaItem? = null
    var description: String? = null
    var spoiler: Boolean? = null

    override fun getData(): ThumbnailData = ThumbnailData(
        id = id,
        media = media ?: throw IllegalStateException("Media of Thumbnail Component is null"),
        description = description,
        spoiler = spoiler
    )
}