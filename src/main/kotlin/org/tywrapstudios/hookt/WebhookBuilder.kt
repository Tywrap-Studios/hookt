package org.tywrapstudios.hookt

import io.ktor.client.statement.*
import kotlinx.serialization.json.Json
import org.tywrapstudios.hookt.dsl.ExecuteBuilder
import org.tywrapstudios.hookt.dsl.HooktDsl
import org.tywrapstudios.hookt.dsl.WebhookBuilder

val WebhookJson = Json {
    encodeDefaults = true
    isLenient = true
    allowSpecialFloatingPointValues = true
    allowStructuredMapKeys = true
    useArrayPolymorphism = false
    explicitNulls = false
}

val TestJson = Json {
    encodeDefaults = true
    isLenient = true
    allowSpecialFloatingPointValues = true
    allowStructuredMapKeys = true
    prettyPrint = true
    useArrayPolymorphism = false
    explicitNulls = false
}

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

/**
 * DSL function to quickly execute for a URL. Use this if you don't want
 * or need, or can't make a "permanent" [DiscordWebhook] instance yourself.
 * @return A [Pair] containing the used webhook and the [HttpResponse] from the execution
 */
@HooktDsl
suspend inline fun execute(url: String, block: ExecuteBuilder.() -> Unit): Pair<DiscordWebhook, HttpResponse> {
    val webhook = Webhook(url)
    val response = webhook.execute(block = block)
    return webhook to response
}

/**
 * DSL function to quickly send a simple message to a URL. Use this if you
 * don't want or need, or can't make a "permanent" [DiscordWebhook] instance
 * yourself.
 * @return A [Pair] containing the used webhook and the [HttpResponse] from
 * the execution
 */
@HooktDsl
suspend inline fun execute(
    url: String,
    message: String,
    block: ExecuteBuilder.() -> Unit = {}
): Pair<DiscordWebhook, HttpResponse> {
    return execute(url) {
        this.content = message
        this.block()
    }
}