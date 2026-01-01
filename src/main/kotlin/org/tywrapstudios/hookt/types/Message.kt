package org.tywrapstudios.hookt.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val id: ULong,
    @SerialName("channel_id")
    val channelId: ULong,
    val author: WebhookAuthor,
    val content: String,
    val timestamp: String,
    @SerialName("edited_timestamp")
    val editedTimestamp: String?,
    val tts: Boolean,
    @SerialName("mention_everyone")
    val mentionsEveryone: Boolean,
    val attachments: List<AttachmentData>,
    val embeds: List<Embed>,
    val reactions: List<Reaction>?,
    val pinned: Boolean,
    val webhookId: ULong?,
    val type: Int,
    val applicationId: ULong?,
    val flags: Int?,
    @SerialName("message_reference")
    val messageReference: MessageReference?,
    @SerialName("referenced_message")
    val referencedMessage: Message?,
    val stickerItems: List<StickerItem>?,
    val position: Int?,
)

@Serializable
data class MessageReference(
    val type: Int?,
    @SerialName("message_id")
    val messageId: ULong?,
    @SerialName("channel_id")
    val channelId: ULong?,
    @SerialName("guild_id")
    val guildId: ULong?,
    @SerialName("fail_if_not_exists")
    val failIfNotExists: Boolean?,
)