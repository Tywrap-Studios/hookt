package org.tywrapstudios.hookt.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A footer for an embed according to Discord's
 * [Embed Footer Structure](https://discord.com/developers/docs/resources/message#embed-object-embed-footer-structure)
 * .
 *
 * @param text The text of this footer (max 2048 characters)
 * @param iconUrl URL of the footer's icon (only supports http(s) and attachments)
 * @param proxyIconUrl A proxied url of the footer's icon
 *
 * @see Embed
 */
@Serializable
data class Footer(
    val text: String,
    @SerialName("icon_url")
    val iconUrl: String?,
    @SerialName("proxy_icon_url")
    val proxyIconUrl: String?,
)
