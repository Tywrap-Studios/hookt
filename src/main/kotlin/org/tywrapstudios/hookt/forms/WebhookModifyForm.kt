package org.tywrapstudios.hookt.forms

import kotlinx.serialization.Serializable
import org.tywrapstudios.hookt.types.ImageData

@Serializable
data class WebhookModifyForm(
    val name: String,
    val avatar: ImageData?,
)
