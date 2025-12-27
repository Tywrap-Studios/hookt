package org.tywrapstudios.hookt.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Author(
    val name: String,
    val url: String?,
    @SerialName("icon_url")
    val iconUrl: String?,
    @SerialName("proxy_icon_url")
    val proxyIconUrl: String?,
)
