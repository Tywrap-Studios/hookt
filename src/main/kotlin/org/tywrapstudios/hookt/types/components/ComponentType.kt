package org.tywrapstudios.hookt.types.components

/**
 * Component types for [Component.type]. See the
 * [Discord documentation](https://discord.com/developers/docs/components/reference#component-object-component-types)
 * for the list of all of them, including ones **not** supported by non-app webhooks.
 *
 * @param value The raw integer value of the component type
 */
enum class ComponentType(val value: Int) {
    Section(9),
    TextDisplay(10),
    Thumbnail(11),
    MediaGallery(12),
    File(13),
    Separator(14),
    Container(17);

    companion object {
        /**
         * Gets a [org.tywrapstudios.hookt.types.components.ComponentType] based
         * on the raw integer value.
         *
         * Note: this is **not based on index**, it is based on the values laid
         * out in the [Discord documentation](https://discord.com/developers/docs/components/reference#component-object-component-types).
         *
         * @throws IllegalArgumentException When the value does not exist in this codebase
         */
        fun fromValue(value: Int): ComponentType {
            return when (value) {
                9 -> Section
                10 -> TextDisplay
                11 -> Thumbnail
                12 -> MediaGallery
                13 -> File
                14 -> Separator
                17 -> Container
                else -> throw IllegalArgumentException("Unknown component type $value")
            }
        }
    }
}
