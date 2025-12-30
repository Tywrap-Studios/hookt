package org.tywrapstudios.hookt.types.components.data

import kotlinx.serialization.Serializable

/**
 * A Discord unfurled media item.
 *
 * @param url The URL to the piece of media, can only be through either the
 * `attachment://` protocol or `https://`
 */
@Serializable
data class UnfurledMediaItem(
    val url: String,
)
