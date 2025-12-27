package org.tywrapstudios.hookt.forms

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.tywrapstudios.hookt.types.Embed

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
)