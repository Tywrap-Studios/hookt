package org.tywrapstudios.hookt.types.components

import org.tywrapstudios.hookt.dsl.HooktDsl
import org.tywrapstudios.hookt.types.components.data.MediaGalleryData
import org.tywrapstudios.hookt.types.components.data.MediaGalleryItem
import org.tywrapstudios.hookt.types.components.data.UnfurledMediaItem

/**
 * A media gallery component. This component is the same visually as uploading regular
 * media, however, it can be used and ordered in the middle of the message instead
 * of being forced to be at the bottom.
 *
 * You can upload one piece of media too, and it will be displayed properly, even
 * in the middle of messages.
 *
 * Uploading files is not supported at the moment, you will have to use `https://` in
 * order to display the media. As for `attachment://`, it
 * is sadly unusable. We're actively working on a file-uploading API.
 */
class MediaGalleryComponent : Component<MediaGalleryData>, ContainerChildComponent<MediaGalleryData> {
    override var type: ComponentType = ComponentType.MediaGallery
    override var id: Int? = null

    /**
     * [MediaGalleryData.items]
     *
     * The order of the items in this list should in theory matter and reflect
     * in the actual gallery.
     */
    val items: List<MediaGalleryItem> = mutableListOf()

    /**
     * DSL function to add a [MediaGalleryItem] to the [items] list.
     */
    @HooktDsl
    fun item(block: GalleryItemBuilder.() -> Unit) {
        (items as MutableList<MediaGalleryItem>)
            .add(GalleryItemBuilder().also(block).build())
    }

    inner class GalleryItemBuilder {
        /**
         * [MediaGalleryItem.media]
         */
        val media: UnfurledMediaItem? = null

        /**
         * [MediaGalleryItem.description]
         */
        val description: String? = null

        /**
         * [MediaGalleryItem.spoiler]
         *
         * The spoiler blur will only be applied to this piece of media,
         * not to all the other ones in the gallery.
         */
        val spoiler: Boolean? = null

        /**
         * Builds the options from the builder into [MediaGalleryItem]
         * so it can be used as an instance for a form body.
         */
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