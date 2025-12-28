package org.tywrapstudios.hookt.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A thumbnail for an embed according to Discord's
 * [Embed Thumbnail Structure](https://discord.com/developers/docs/resources/message#embed-object-embed-thumbnail-structure)
 * .
 *
 * @see Embed
 * @see Image
 */
typealias Thumbnail = Image
/**
 * A video for an embed according to Discord's
 * [Embed Video Structure](https://discord.com/developers/docs/resources/message#embed-object-embed-video-structure)
 * .
 *
 * @see Embed
 * @see Image
 */
typealias Video = Image

/**
 * An image for an embed according to Discord's
 * [Embed Image Structure](https://discord.com/developers/docs/resources/message#embed-object-embed-image-structure)
 * .
 *
 * Used as a `typealias` for [Thumbnail] and [Video] because they both follow a similar structure
 * and since [url] is non-null, it can be applied to both regardless of the fact that it is technically
 * optional for [Video].
 *
 * @param url Source url of the media (only supports http(s) and attachments)
 * @param proxyUrl A proxied url of the media
 * @param height The height of the media
 * @param width The width of the media
 *
 * @see Thumbnail
 * @see Video
 * @see Embed
 */
@Serializable
data class Image(
    val url: String,
    @SerialName("proxy_url")
    val proxyUrl: String?,
    val height: Int?,
    val width: Int?,
)
