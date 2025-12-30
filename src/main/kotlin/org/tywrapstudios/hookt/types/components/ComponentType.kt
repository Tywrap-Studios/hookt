package org.tywrapstudios.hookt.types.components

enum class ComponentType(val value: Int) {
    Section(9),
    TextDisplay(10),
    Thumbnail(11),
    MediaGallery(12),
    File(13),
    Separator(14),
    Container(17);

    companion object {
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
