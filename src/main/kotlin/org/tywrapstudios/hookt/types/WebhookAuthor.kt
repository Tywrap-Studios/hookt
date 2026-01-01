package org.tywrapstudios.hookt.types

import kotlinx.serialization.Serializable

@Serializable
data class WebhookAuthor(
    val id: ULong,
    val username: String,
    val avatar: String?,
)
