package org.tywrapstudios.hookt.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * An author for an embed according to Discord's
 * (Embed Author Structure)[https://discord.com/developers/docs/resources/message#embed-object-embed-author-structure]
 * .
 *
 * @param name The name of the author
 * @param url A URL associated with the author (only supports http(s))
 * @param iconUrl A URL of the author's icon (only supports http(s) and attachments)
 * @param proxyIconUrl A proxied URL of the author's icon
 *
 * @see Embed
 */
@Serializable
data class Author(
    val name: String,
    val url: String?,
    @SerialName("icon_url")
    val iconUrl: String?,
    @SerialName("proxy_icon_url")
    val proxyIconUrl: String?,
)
