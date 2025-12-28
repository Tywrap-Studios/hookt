package org.tywrapstudios.hookt.types

import kotlinx.serialization.Serializable

/**
 * An embed according to Discord's
 * [Embed Object](https://discord.com/developers/docs/resources/message#embed-object)
 * .
 *
 * @param title The title of the embed
 * @param description The description (content) of the embed
 * @param url URL of the embed (TODO)
 * @param timestamp ISO8601 formatted string timestamp, can be got
 * from [kotlin.time.Instant.toString]
 * @param color An RGB int
 * @param footer A [Footer] object
 * @param image An [Image] object
 * @param thumbnail A [Thumbnail] object
 * @param video A [Video] object
 * @param author An [Author] object
 * @param fields A list containing [Field] objects
 */
@Serializable
data class Embed(
    val title: String?,
    val description: String?,
    val url: String?,
    val timestamp: String?,
    val color: Int?,
    val footer: Footer?,
    val image: Image?,
    val thumbnail: Thumbnail?,
    val video: Video?,
    val author: Author?,
    val fields: List<Field>?,
)