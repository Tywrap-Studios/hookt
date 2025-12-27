package org.tywrapstudios.hookt

import org.tywrapstudios.hookt.dsl.HooktDsl
import org.tywrapstudios.hookt.dsl.WebhookBuilder

@HooktDsl
@Suppress("FunctionName")
inline fun Webhook(block: WebhookBuilder.() -> Unit): DiscordWebhook {
    val context = WebhookBuilder().also(block).build()
    return DiscordWebhook(context)
}

@HooktDsl
@Suppress("FunctionName")
inline fun Webhook(url: String, block: WebhookBuilder.() -> Unit = {}): DiscordWebhook {
    return Webhook {
        this.url = url
        this.block()
    }
}