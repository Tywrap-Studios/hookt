package org.tywrapstudios.hookt.types.components

import org.tywrapstudios.hookt.dsl.HooktDsl
import org.tywrapstudios.hookt.types.components.data.MediaGalleryData
import org.tywrapstudios.hookt.types.components.data.MediaGalleryItem
import org.tywrapstudios.hookt.types.components.data.UnfurledMediaItem

class MediaGalleryComponent : Component<MediaGalleryData>, ContainerChildComponent<MediaGalleryData> {
    override var type: ComponentType = ComponentType.MediaGallery
    override var id: Int? = null
    val items: List<MediaGalleryItem> = mutableListOf()

    @HooktDsl
    fun item(block: GalleryItemBuilder.() -> Unit) {
        (items as MutableList<MediaGalleryItem>)
            .add(GalleryItemBuilder().also(block).build())
    }

    inner class GalleryItemBuilder {
        val media: UnfurledMediaItem? = null
        val description: String? = null
        val spoiler: Boolean? = null

        fun build(): MediaGalleryItem = MediaGalleryItem(
            media ?: throw IllegalStateException("Media for Gallery Item is null"),
            description,
            spoiler
        )
    }

    override fun getData(): MediaGalleryData {
        if (items.size !in 1..10) {
            throw IllegalStateException("Media Galleries should contain 1-10 items")
        }
        return MediaGalleryData(
            id = id,
            items = items
        )
    }
}