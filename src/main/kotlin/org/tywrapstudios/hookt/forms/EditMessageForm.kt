package org.tywrapstudios.hookt.forms

import kotlinx.serialization.Serializable
import org.tywrapstudios.hookt.types.Embed

/**
 * Serializable form body to edit a message previously sent by the webhook.
 *
 * @param content The new plain text of the message
 * @param embeds A list containing the new [Embed] objects
 * @param flags [Message flags](https://discord.com/developers/docs/resources/message#message-object-message-flags)
 * combined as a [Bit Field](https://en.wikipedia.org/wiki/Bit_field)
 * into an [Int]
 * (only `SUPPRESS_EMBEDS`, `SUPPRESS_NOTIFICATIONS` and `IS_COMPONENTS_V2` can be set)
 */
@Serializable
data class EditMessageForm(
    val content: String?,
    val embeds: List<Embed>?,
    val flags: Int?,
)
