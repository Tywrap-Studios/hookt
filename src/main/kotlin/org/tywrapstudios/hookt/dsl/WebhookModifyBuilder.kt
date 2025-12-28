package org.tywrapstudios.hookt.dsl

import org.tywrapstudios.hookt.forms.WebhookModifyForm
import org.tywrapstudios.hookt.types.ImageData
import org.tywrapstudios.hookt.types.throwIfInvalid

/**
 * Builder class for [WebhookModifyForm].
 */
class WebhookModifyBuilder : FormBuilder<WebhookModifyForm> {
    /**
     * [WebhookModifyForm.name]
     *
     * Must be set, otherwise [IllegalStateException] will be thrown.
     */
    var name: String? = null

    /**
     * [WebhookModifyForm.avatar]
     * @see ImageData
     */
    var avatar: ImageData? = null

    override fun build(): WebhookModifyForm = WebhookModifyForm(
        name ?: throw IllegalStateException("Name for webhook modification is null"),
        avatar.throwIfInvalid { true }
    )
}