package org.tywrapstudios.hookt.types

/**
 * See [Discord Docs](https://discord.com/developers/docs/resources/message#message-object-message-flags)
 */
enum class MessageFlag(val shift: Int) {
    SUPPRESS_EMBEDS(2),
    SUPPRESS_NOTIFICATIONS(12),
    IS_COMPONENTS_V2(15),
}