package org.tywrapstudios.hookt.dsl

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import org.tywrapstudios.hookt.WebhookContext
import org.tywrapstudios.hookt.WebhookJson

/**
 * Builder class for [WebhookContext]. Technically not a form, but
 * that does not actually matter.
 */
class WebhookBuilder : FormBuilder<WebhookContext> {
    /**
     * [WebhookContext.url]
     *
     * Must be set, otherwise [IllegalStateException] will be thrown.
     */
    var url: String? = null

    /**
     * [WebhookContext.client]
     *
     * If left empty, a [CIO] engine [HttpClient] will be used with the [ContentNegotiation]
     * plugin and [DefaultJson] configuration for [json].
     */
    var client: HttpClient? = null

    override fun build(): WebhookContext = WebhookContext(
        url ?: throw IllegalStateException("URL for webhook is null"),
        client ?: HttpClient(CIO) {
            install(ContentNegotiation) {
                json(
                    WebhookJson,
                )
            }
        }
    )
}