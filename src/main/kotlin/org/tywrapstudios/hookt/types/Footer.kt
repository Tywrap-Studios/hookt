package org.tywrapstudios.hookt.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Footer(
    val text: String,
    @SerialName("icon_url")
    val iconUrl: String?,
    @SerialName("proxy_icon_url")
    val proxyIconUrl: String?,
)
