package org.tywrapstudios.hookt.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias Thumbnail = Image
typealias Video = Image

@Serializable
data class Image(
    val url: String,
    @SerialName("proxy_url")
    val proxyUrl: String?,
    val height: Int?,
    val width: Int?,
)
