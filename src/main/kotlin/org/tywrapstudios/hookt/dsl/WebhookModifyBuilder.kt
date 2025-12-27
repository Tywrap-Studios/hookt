package org.tywrapstudios.hookt.dsl

import org.tywrapstudios.hookt.forms.WebhookModifyForm
import org.tywrapstudios.hookt.types.ImageData
import org.tywrapstudios.hookt.types.throwIfInvalid

class WebhookModifyBuilder : FormBuilder<WebhookModifyForm> {
    var name: String? = null
    var avatar: ImageData? = null

    override fun build(): WebhookModifyForm = WebhookModifyForm(
        name ?: throw IllegalStateException("Name for webhook modification is null"),
        avatar?.throwIfInvalid { true }
    )
}