package org.tywrapstudios.hookt

import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import org.tywrapstudios.hookt.dsl.EditMessageBuilder
import org.tywrapstudios.hookt.dsl.ExecuteBuilder
import org.tywrapstudios.hookt.dsl.HooktDsl
import org.tywrapstudios.hookt.dsl.WebhookModifyBuilder

/**
 * This class represents an instance of a Discord webhook containing a context. It has
 * (DSL) commands used to execute various requests using the Ktor [WebhookContext.client].
 */
class DiscordWebhook(val context: WebhookContext) {

    /**
     * DSL function to send a message to Discord via the webhook.
     * @param thread The thread ID of a thread within a webhook's channel to send the message to
     */
    @HooktDsl
    suspend inline fun execute(thread: ULong? = null, block: ExecuteBuilder.() -> Unit): HttpResponse {
        val url = if (thread != null) "${context.url}?thread_id=$thread" else context.url
        return context.client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(ExecuteBuilder().apply(block).build().validate())
            expectSuccess = true
        }
    }

    /**
     * Helper DSL function to send a plain message to Discord via the webhook.
     * @param message The plain content to send
     * @param thread The ID of a thread within a webhook's channel to send the message to
     */
    @HooktDsl
    suspend inline fun execute(message: String, thread: ULong? = null) {
        execute(thread) {
            content = message
        }
    }

    /**
     * DSL function to modify the webhook's information, such as its name and avatar.
     * @param reason The audit log reason for the changes
     */
    @HooktDsl
    suspend inline fun modify(reason: String? = null, block: WebhookModifyBuilder.() -> Unit) {
        context.client.patch(context.url) {
            contentType(ContentType.Application.Json)
            if (reason != null) header("X-Audit-Log-Reason", reason)
            setBody(WebhookModifyBuilder().also(block).build())
            expectSuccess = true
        }
    }

    /**
     * Function to delete the webhook from the guild.
     * @param reason The audit log reason for the deletion
     */
    suspend inline fun delete(reason: String? = null) {
        context.client.delete(context.url) {
            if (reason != null) header("X-Audit-Log-Reason", reason)
            expectSuccess = true
        }
    }

    /**
     * DSL function to edit a message previously sent by the webhook.
     * @param message The ID of the message
     * @param thread The ID of the thread that contains the message
     */
    @HooktDsl
    suspend inline fun editMessage(message: ULong, thread: ULong? = null, block: EditMessageBuilder.() -> Unit) {
        val base = "${context.url}/messages/$message"
        val url = if (thread != null) "$base?thread_id=$thread" else base
        context.client.patch(url) {
            contentType(ContentType.Application.Json)
            setBody(EditMessageBuilder().also(block).build())
        }
    }

    /**
     * Function to delete a message previously sent by the webhook.
     * @param message The ID of the message
     * @param thread The ID of the thread that contains the message
     */
    suspend inline fun deleteMessage(message: ULong, thread: ULong? = null) {
        val base = "${context.url}/messages/$message"
        val url = if (thread != null) "$base?thread_id=$thread" else base
        context.client.delete(url)
        // Don't expectSuccess because Discord returns 204 No Content instead
    }
}