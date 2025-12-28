package org.tywrapstudios.hookt

import org.tywrapstudios.hookt.dsl.HooktDsl
import org.tywrapstudios.hookt.dsl.WebhookBuilder

/**
 * DSL function to create a new [DiscordWebhook] instance with a given [WebhookContext].
 *
 * You generally only use this one if you want to use a custom [io.ktor.client.HttpClient].
 * @see Webhook
 */
@HooktDsl
@Suppress("FunctionName")
inline fun Webhook(block: WebhookBuilder.() -> Unit): DiscordWebhook {
    val context = WebhookBuilder().also(block).build()
    return DiscordWebhook(context)
}

/**
 * DSL function to create a new [DiscordWebhook] with a [url], optionally
 * adding extra configuration to the [WebhookContext].
 * @param url The URL of the webhook's endpoint
 */
@HooktDsl
@Suppress("FunctionName")
inline fun Webhook(url: String, block: WebhookBuilder.() -> Unit = {}): DiscordWebhook {
    return Webhook {
        this.url = url
        this.block()
    }
}