package org.tywrapstudios.hookt.forms

import kotlinx.serialization.Serializable
import org.tywrapstudios.hookt.types.Embed

@Serializable
data class EditMessageForm(
    val content: String?,
    val embeds: List<Embed>?,
    val flags: Int?,
)
