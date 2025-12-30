package org.tywrapstudios.hookt.types.components

import org.tywrapstudios.hookt.types.components.data.FileComponentData
import org.tywrapstudios.hookt.types.components.data.UnfurledMediaItem

class FileComponent : Component<FileComponentData> {
    override var type: ComponentType = ComponentType.File
    override var id: Int? = null
    var file: UnfurledMediaItem? = null
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