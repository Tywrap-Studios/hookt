package org.tywrapstudios.hookt.forms

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.tywrapstudios.hookt.types.Embed

/**
 * Serializable form body to execute a webhook and send a message.
 *
 * @param content The plain text of the message
 * @param username The custom name to give the webhook for this message
 * @param avatarUrl The custom avatar URL to give the webhook for this message
 * @param tts Whether this message is Text-to-speech
 * @param embeds A list containing [Embed] objects
 * @param flags [Message flags](https://discord.com/developers/docs/resources/message#message-object-message-flags)
 * combined as a [Bit Field](https://en.wikipedia.org/wiki/Bit_field)
 * into an [Int]
 * (only `SUPPRESS_EMBEDS`, `SUPPRESS_NOTIFICATIONS` and `IS_COMPONENTS_V2` can be set)
 * @param threadName Name of a thread to create (requires the webhook channel to be a forum or media channel)
 * @param appliedTags A list containing IDs of tags to apply to the thread (requires the webhook channel to be a forum or media channel)
 */
@Serializable
data class ExecuteForm(
    val content: String?,
    val username: String?,
    @SerialName("avatar_url")
    val avatarUrl: String?,
    val tts: Boolean?,
    val embeds: List<Embed>?,
    val flags: Int?,
    @SerialName("thread_name")
    val threadName: String?,
    @SerialName("applied_tags")
    val appliedTags: List<ULong>?,
) {
    /**
     * Validates whether this object can be used as a form body, most notably
     * checking if a value for **at least one of** content or embeds has been passed, otherwise,
     * returns `this` instance.
     *
     * @throws IllegalStateException When the instance does not contain at least one of content or embeds
     */
    fun validate(): ExecuteForm {
        if (content == null && embeds.isNullOrEmpty()) {
            throw IllegalStateException("Either content or embeds must be set for webhook execution, but they're both null or empty")
        }
        return this
    }
}