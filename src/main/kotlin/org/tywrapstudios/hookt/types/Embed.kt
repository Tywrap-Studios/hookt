package org.tywrapstudios.hookt.types

import kotlinx.serialization.Serializable

@Serializable
data class Embed(
    val title: String?,
    val description: String?,
    val url: String?,
    val timestamp: String?,
    val color: Int?,
    val footer: Footer?,
    val image: Image?,
    val thumbnail: Thumbnail?,
    val video: Video?,
    val author: Author?,
    val fields: List<Field>?,
)