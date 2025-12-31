package org.tywrapstudios.hookt

import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import org.tywrapstudios.hookt.dsl.EditMessageBuilder
import org.tywrapstudios.hookt.dsl.ExecuteBuilder
import org.tywrapstudios.hookt.dsl.HooktDsl
import org.tywrapstudios.hookt.dsl.WebhookModifyBuilder
import org.tywrapstudios.hookt.forms.ExecuteForm
import java.io.File

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
        var url = context.url
        val form = ExecuteBuilder().apply(block).build().validate()
        val queries = mutableListOf<String>()
        if (thread != null) {
            queries.add("thread=$thread")
        }
        if (form.components?.isNotEmpty() == true) {
            queries.add("with_components=true")
        }
        if (queries.isNotEmpty()) {
            url += "?${queries.joinToString("&")}"
        }
        println(TestJson.encodeToString(form))
        println(url)
        val hasFiles = form.files.isNotEmpty()
        return context.client.post(url) {
            if (hasFiles) {
                contentType(ContentType.MultiPart.FormData)
                val files = form.files.map {
                    FileContent(it, ContentType.fromFilePath(it.path).getOrElse(0) {
                        ContentType.Application.OctetStream
                    })
                }
                setBody(getMultipartFormData(form, files))
            } else {
                contentType(ContentType.Application.Json)
                setBody(form)
            }
            expectSuccess = true
        }
    }

    fun getMultipartFormData(form: ExecuteForm, files: List<FileContent>): MultiPartFormDataContent {
        return MultiPartFormDataContent(
            formData {
                append(
                    "payload_json",
                    WebhookJson.encodeToString(form),
                    Headers.build {
                        append(HttpHeaders.ContentType, ContentType.Application.Json)
                    }
                )

                for (i in files.indices) {
                    append(
                        "files[$i]",
                        files[i].file.readBytes(),
                        Headers.build {
                            append(HttpHeaders.ContentDisposition, "filename=\"${files[i].file.name}\"")
                            append(HttpHeaders.ContentType, files[i].type)
                        }
                    )
                }
            }
        )
    }

    data class FileContent(val file: File, val type: ContentType)

    /**
     * Helper DSL function to send a plain message to Discord via the webhook.
     * @param message The plain content to send
     * @param thread The ID of a thread within a webhook's channel to send the message to
     */
    @HooktDsl
    suspend inline fun execute(message: String, thread: ULong? = null): HttpResponse {
        return execute(thread) {
            content = message
        }
    }

    /**
     * DSL function to modify the webhook's information, such as its name and avatar.
     * @param reason The audit log reason for the changes
     */
    @HooktDsl
    suspend inline fun modify(reason: String? = null, block: WebhookModifyBuilder.() -> Unit): HttpResponse {
        return context.client.patch(context.url) {
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
    suspend inline fun delete(reason: String? = null): HttpResponse {
        return context.client.delete(context.url) {
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
    suspend inline fun editMessage(
        message: ULong,
        thread: ULong? = null,
        block: EditMessageBuilder.() -> Unit
    ): HttpResponse {
        val base = "${context.url}/messages/$message"
        val url = if (thread != null) "$base?thread_id=$thread" else base
        return context.client.patch(url) {
            contentType(ContentType.Application.Json)
            setBody(EditMessageBuilder().also(block).build())
        }
    }

    /**
     * Function to delete a message previously sent by the webhook.
     * @param message The ID of the message
     * @param thread The ID of the thread that contains the message
     */
    suspend inline fun deleteMessage(message: ULong, thread: ULong? = null): HttpResponse {
        val base = "${context.url}/messages/$message"
        val url = if (thread != null) "$base?thread_id=$thread" else base
        return context.client.delete(url) {
            expectSuccess = true
        }
    }
}