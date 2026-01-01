package org.tywrapstudios.hookt.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StickerItem(
    val id: ULong,
    val name: String,
    @SerialName("format_type")
    val formatType: Int,
)
