package org.tywrapstudios.hookt.forms

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.jetbrains.annotations.ApiStatus
import org.tywrapstudios.hookt.types.AttachmentData
import org.tywrapstudios.hookt.types.Embed
import org.tywrapstudios.hookt.types.components.data.ComponentData
import java.io.File

/**
 * Serializable form body to execute a webhook and send a message.
 *
 * The [content] **cannot exceed 2000** characters.
 * The combined sum of characters from titles, descriptions,
 * authors, fields, etc. from all [embeds] **cannot exceed 6000**. This is counted
 * separately, so you can have a max total of 8000 characters.
 * Additionally, you can only have 10 [embeds].
 *
 * @param content The plain text of the message (max 2000 characters)
 * @param username The custom name to give the webhook for this message
 * @param avatarUrl The custom avatar URL to give the webhook for this message
 * @param tts Whether this message is Text-to-speech
 * @param embeds A list containing [Embed] objects (max 10 objects)
 * @param components A list containing [ComponentData] for
 * [components](https://discord.com/developers/docs/components/reference).
 * Remember you need to pass the `IS_COMPONENTS_V2` flag!
 * @param attachments A list containing [AttachmentData] for
 * files and attachments
 * @param files This is an internal value to pass onto the
 * request process in order to aid it in uploading the files
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
    val components: List<ComponentData>?,
    val attachments: List<AttachmentData>?,
    @Transient
    @ApiStatus.Internal
    val files: List<File> = emptyList(),
    val flags: Int?,
    @SerialName("thread_name")
    val threadName: String?,
    @SerialName("applied_tags")
    val appliedTags: List<ULong>?,
) {
    /**
     * Validates whether this object can be used as a form body, most notably
     * checking if a value for **at least one of** content, embeds or components
     * has been passed, otherwise, returns `this` instance.
     *
     * @throws IllegalStateException When the instance does not contain at least one of content or embeds
     */
    fun validate(): ExecuteForm {
        if (content == null && embeds.isNullOrEmpty() && components.isNullOrEmpty()) {
            throw IllegalStateException("At least one of content, embeds or components must be set for webhook execution, but they're all null or empty")
        }
        return this
    }
}