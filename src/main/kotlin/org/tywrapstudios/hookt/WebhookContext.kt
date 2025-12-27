package org.tywrapstudios.hookt

import io.ktor.client.*

data class WebhookContext(
    val url: String,
    val client: HttpClient
) {
    val id = url.split("/").last()
    val token = url.split("/")[url.split("/").lastIndex - 1]
}