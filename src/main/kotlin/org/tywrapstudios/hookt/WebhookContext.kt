package org.tywrapstudios.hookt

import io.ktor.client.*

/**
 * A context for the webhook, it contains the base URL and a Ktor [HttpClient]
 * that is used for the execution of the requests.
 *
 * You can also retrieve the individual [id] and [token] values if needed.
 * @see Webhook
 * @see DiscordWebhook
 *
 * @param url The URL of the webhook's endpoint
 * @param client A Ktor [HttpClient], needs `ContentNegotiation` with some `json()` config
 */
data class WebhookContext(
    val url: String,
    val client: HttpClient
) {
    /**
     * The ID of the webhook.
     */
    val id = url.split("/")[url.split("/").lastIndex - 1].toULong()

    /**
     * The token that authenticates the webhook.
     */
    val token = url.split("/").last()
}