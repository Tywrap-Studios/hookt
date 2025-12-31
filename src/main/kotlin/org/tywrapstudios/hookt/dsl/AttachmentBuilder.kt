package org.tywrapstudios.hookt.dsl

import org.tywrapstudios.hookt.types.AttachmentData

class AttachmentBuilder : FormBuilder<AttachmentData> {

    var id: ULong? = null
    var filename: String? = null
    var description: String? = null

    override fun build(): AttachmentData {
        return AttachmentData(
            id ?: throw IllegalStateException("ID for Attachment is null"),
            filename ?: throw IllegalStateException("Filename for Attachment is null"),
            description
        )
    }
}