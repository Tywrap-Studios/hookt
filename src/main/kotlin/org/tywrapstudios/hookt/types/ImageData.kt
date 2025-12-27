package org.tywrapstudios.hookt.types

typealias ImageData = String

fun ImageData?.isValid(nullPolicy: (ImageData?) -> Boolean = { false }): Boolean {
    if (this == null) return nullPolicy(this)
    return this.startsWith("data:image/") || this.startsWith("https://")
}

fun ImageData.throwIfInvalid(nullPolicy: (ImageData?) -> Boolean = { false }): ImageData {
    if (this.isValid(nullPolicy)) {
        return this
    } else {
        throw IllegalStateException("Image data is invalid")
    }
}