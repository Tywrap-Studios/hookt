package org.tywrapstudios.hookt.types.components

import org.tywrapstudios.hookt.types.components.data.FileComponentData
import org.tywrapstudios.hookt.types.components.data.UnfurledMediaItem

/**
 * A file component. This component visually resembles a downloadable
 * attachment (that does not have an embed like images), but it can be used and
 * ordered in the middle of the message instead of being forced to be at the bottom.
 *
 * Uploading files is not supported at the moment, and as this component's
 * unfurled media item [file] only accepts the `attachment://` protocol, it
 * is sadly unusable. We're actively working on a file-uploading API.
 */
class FileComponent : Component<FileComponentData> {
    override var type: ComponentType = ComponentType.File
    override var id: Int? = null

    /**
     * [FileComponentData.file]
     */
    var file: UnfurledMediaItem? = null

    /**
     * [FileComponentData.spoiler]
     *
     * This visually adds a spoiler blur to the entire component. While this
     * may not have much use because there will never be an embedded visual attachment,
     * it can still be used to spoiler the file name if needed.
     */
    var spoiler: Boolean? = null

    override fun getData(): FileComponentData {
        if (file == null) {
            throw IllegalStateException("Media Item for File Component is null")
        }
        if (!file!!.url.startsWith("attachment://")) {
            throw IllegalStateException("Media Item for File Component can only be of attachment://")
        }
        return FileComponentData(
            id = id,
            file = file!!,
            spoiler = spoiler
        )
    }
}