package org.tywrapstudios.hookt.types.components

import org.tywrapstudios.hookt.types.components.data.ThumbnailData
import org.tywrapstudios.hookt.types.components.data.UnfurledMediaItem

/**
 * A thumbnail component. This is (right now) solely for use in a [SectionComponent]
 * as an accessory. Its media displays in the plain message at the
 * top right similar to embeds. It's currently the only available
 * [SectionAccessoryComponent], though this may change in the future.
 *
 * Uploading files is not supported at the moment, you will have to use `https://` in
 * order to display the media. As for `attachment://`, it
 * is sadly unusable. We're actively working on a file-uploading API.
 */
class ThumbnailComponent : Component<ThumbnailData>, SectionAccessoryComponent<ThumbnailData> {
    override var type: ComponentType = ComponentType.Thumbnail
    override var id: Int? = null

    /**
     * [ThumbnailData.media]
     *
     * Uploading files is not supported at the moment, you will have to use `https://` in
     * order to display the media. As for `attachment://`, it
     * is sadly unusable. We're actively working on a file-uploading API.
     */
    var media: UnfurledMediaItem? = null

    /**
     * [ThumbnailData.description]
     */
    var description: String? = null

    /**
     * [ThumbnailData.spoiler]
     *
     * The spoiler blur will only be applied to the media, not the whole [SectionComponent]
     * the thumbnail is a part of.
     */
    var spoiler: Boolean? = null

    override fun getData(): ThumbnailData = ThumbnailData(
        id = id,
        media = media ?: throw IllegalStateException("Media of Thumbnail Component is null"),
        description = description,
        spoiler = spoiler
    )
}