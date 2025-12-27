package org.tywrapstudios.hookt.dsl

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import org.tywrapstudios.hookt.WebhookContext

class WebhookBuilder : FormBuilder<WebhookContext> {
    var url: String? = null
    var client: HttpClient? = null

    override fun build(): WebhookContext = WebhookContext(
        url ?: throw IllegalStateException("URL for webhook is null"),
        client ?: HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }
        }
    )
}