package org.tywrapstudios.hookt.types

import kotlinx.serialization.Serializable

/**
 * A field for an embed according to Discord's
 * [Embed Field Structure](https://discord.com/developers/docs/resources/message#embed-object-embed-field-structure)
 * .
 *
 * @param name The name (title) of the field
 * @param value The value (description/body) of the field
 * @param inline Whether to inline the field into the embed, making it appear side-to-side
 * with other inlined fields.
 *
 * @see Embed
 */
@Serializable
data class Field(
    val name: String,
    val value: String,
    val inline: Boolean?,
)
