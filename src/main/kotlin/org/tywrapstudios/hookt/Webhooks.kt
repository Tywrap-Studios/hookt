package org.tywrapstudios.hookt

import io.ktor.client.statement.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.ClassDiscriminatorMode
import kotlinx.serialization.json.Json
import org.tywrapstudios.hookt.dsl.ExecuteBuilder
import org.tywrapstudios.hookt.dsl.HooktDsl
import org.tywrapstudios.hookt.dsl.WebhookBuilder
import org.tywrapstudios.hookt.types.components.data.ComponentsSerializersModule
import kotlin.io.path.Path
import kotlin.io.path.isRegularFile
import kotlin.io.path.readLines

@OptIn(ExperimentalSerializationApi::class)
val WebhookJson = Json {
    encodeDefaults = true
    isLenient = true
    allowSpecialFloatingPointValues = true
    allowStructuredMapKeys = true
    useArrayPolymorphism = false
    explicitNulls = false
    serializersModule = ComponentsSerializersModule
    classDiscriminatorMode = ClassDiscriminatorMode.NONE
    classDiscriminator = "KClassType"
}

@OptIn(ExperimentalSerializationApi::class)
val TestJson = Json {
    encodeDefaults = true
    isLenient = true
    allowSpecialFloatingPointValues = true
    allowStructuredMapKeys = true
    prettyPrint = true
    useArrayPolymorphism = false
    explicitNulls = false
    serializersModule = ComponentsSerializersModule
    classDiscriminator = "KClassType"
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

internal class EnvNotFoundException(val name: String) :
    Exception(
        "The environment value $name could not be found. " +
                "Please add it in a .env file or add it to your system environment values."
    )

/**
 * Gets and returns an environmental value from either a .env file or the system's
 * global environment values, or null if it does not exist in either source.
 */
fun getEnvOrNull(name: String): String? {
    val dotenv = Path(".env")
    val map: MutableMap<String, String> = mutableMapOf()
    if (dotenv.isRegularFile()) {
        for (line in dotenv.readLines()) {
            var actualLine = line.trimStart()
            if (line.isEmpty() || line.startsWith("#")) continue
            actualLine = actualLine.substringBefore("#")

            val parts = actualLine
                .split("=", limit = 2)
            map[parts[0]] = parts[1]
        }
    }

    return map[name] ?: System.getenv(name)
}

/**
 * Gets and returns an environmental value from either a .env file or the system's
 * global environment values.
 * @throws EnvNotFoundException If the value could not be found in either source
 */
fun getEnv(name: String): String {
    return getEnvOrNull(name) ?: throw EnvNotFoundException(name)
}

/**
 * Gets and returns an environmental value from either a .env file or the system's
 * global environment values, or the value that results from [default] if it does
 * not exist in either source.
 */
fun getEnvOrElse(name: String, default: () -> String): String {
    return getEnvOrNull(name) ?: default()
}