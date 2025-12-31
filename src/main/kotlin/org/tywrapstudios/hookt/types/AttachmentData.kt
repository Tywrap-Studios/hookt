package org.tywrapstudios.hookt.types

import kotlinx.serialization.Serializable

@Serializable
data class AttachmentData(
    val id: ULong,
    val filename: String,
    val description: String?,
)
