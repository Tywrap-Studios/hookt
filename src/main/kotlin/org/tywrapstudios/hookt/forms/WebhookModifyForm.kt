package org.tywrapstudios.hookt.forms

import kotlinx.serialization.Serializable
import org.tywrapstudios.hookt.types.ImageData

/**
 * Serializable form body to modify a webhook.
 *
 * @param name The new default name for the webhook
 * @param avatar The new default avatar for the webhook
 */
@Serializable
data class WebhookModifyForm(
    val name: String,
    val avatar: ImageData?,
)
