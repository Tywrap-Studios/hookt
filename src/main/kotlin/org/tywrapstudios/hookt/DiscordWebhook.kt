package org.tywrapstudios.hookt

import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import org.tywrapstudios.hookt.dsl.EditMessageBuilder
import org.tywrapstudios.hookt.dsl.ExecuteBuilder
import org.tywrapstudios.hookt.dsl.HooktDsl
import org.tywrapstudios.hookt.dsl.WebhookModifyBuilder

class DiscordWebhook(val context: WebhookContext) {
    @HooktDsl
    suspend inline fun execute(thread: ULong? = null, block: ExecuteBuilder.() -> Unit) {
        val url = if (thread != null) "${context.url}?thread_id=$thread" else context.url
        context.client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(ExecuteBuilder().apply(block).build())
            expectSuccess = true
        }
    }

    @HooktDsl
    suspend inline fun execute(message: String, thread: ULong? = null) {
        execute(thread) {
            content = message
        }
    }

    @HooktDsl
    suspend inline fun modify(reason: String? = null, block: WebhookModifyBuilder.() -> Unit) {
        context.client.patch(context.url) {
            contentType(ContentType.Application.Json)
            if (reason != null) header("X-Audit-Log-Reason", reason)
            setBody(WebhookModifyBuilder().also(block).build())
            expectSuccess = true
        }
    }

    suspend inline fun delete(reason: String? = null) {
        context.client.delete(context.url) {
            if (reason != null) header("X-Audit-Log-Reason", reason)
            expectSuccess = true
        }
    }

    suspend inline fun editMessage(message: ULong, thread: ULong? = null, block: EditMessageBuilder.() -> Unit) {
        val base = "${context.url}/messages/$message"
        val url = if (thread != null) "$base?thread_id=$thread" else base
        context.client.patch(url) {
            contentType(ContentType.Application.Json)
            setBody(EditMessageBuilder().also(block).build())
        }
    }

    suspend inline fun deleteMessage(message: ULong, thread: ULong? = null) {
        val base = "${context.url}/messages/$message"
        val url = if (thread != null) "$base?thread_id=$thread" else base
        context.client.delete(url)
    }
}