package org.tywrapstudios.hookt.types

/**
 * ## Image Data
 * [Discord Image Data](https://discord.com/developers/docs/reference#image-data)
 * is a [Data URI scheme](https://en.wikipedia.org/wiki/Data_URI_scheme)
 * that supports JPG, GIF, and PNG formats. An example Data URI format is:
 * ```
 * data:image/jpeg;base64,BASE64_ENCODED_JPEG_IMAGE_DATA
 * ```
 * Ensure you use the proper content type (`image/jpeg`, `image/png`, `image/gif`)
 * that matches the image data being provided.
 *
 * ### Signed Attachment CDN URLs
 *
 * Alternatively, you can use [Signed Attachment CDN URLs](https://discord.com/developers/docs/reference#signed-attachment-cdn-urls)
 * .
 * Attachments uploaded to Discord's CDN (like user and bot-uploaded images)
 * have signed URLs with a preset expiry time. Discord automatically refreshes
 * attachment CDN URLs that appear within the client, so when your app receives a
 * payload with a signed URL (like when you fetch a message), it will be valid.
 *
 * When passing CDN URLs into API fields, like [Image.url] in an embed image
 * object and [org.tywrapstudios.hookt.forms.ExecuteForm.avatarUrl] for webhooks,
 * your app can pass the CDN URL without any parameters as the value and Discord
 * will automatically render and refresh the URL.
 *
 * The standard [CDN endpoints](https://discord.com/developers/docs/reference#image-formatting-cdn-endpoints)
 * are not signed, so they will not expire.
 *
 * ###### Example Attachment CDN URL
 * ```md
 * https://cdn.discordapp.com/attachments/1012345678900020080/1234567891233211234/my_image.png?ex=65d903de&is=65c68ede&hm=2481f30dd67f503f54d020ae3b5533b9987fae4e55f2b4e3926e08a3fa3ee24f&
 * ```
 * ###### Attachment CDN URL Parameters
 * ```md
 * ex       Hex timestamp indicating when an attachment CDN URL will expire
 * is       Hex timestamp indicating when the URL was issued
 * hm       Unique signature that remains valid until the URL's expiration
 * ```
 */
typealias ImageData = String

/**
 * Checks whether the [ImageData] instance follows the basic "formats" for Discord
 * image data, that is, starting with `data:image/` (Base64) or `https://` (CDN). You
 * can optionally specify what happens if the [ImageData] is `null`
 * @param nullPolicy Specify what to return if the [ImageData] is `null`, defaults to returning `false`
 */
fun ImageData?.isValid(nullPolicy: (ImageData?) -> Boolean = { false }): Boolean {
    if (this == null) return nullPolicy(this)
    return this.startsWith("data:image/") || this.startsWith("https://")
}

/**
 * Checks whether the [ImageData] instance follows the basic "formats" for Discord
 * image data, that is, starting with `data:image/` (Base64) or `https://` (CDN)
 * using [isValid] and throws [IllegalStateException] if it returns false. You can
 * optionally specify what happens if the [ImageData] is `null` as per [isValid].
 * @param nullPolicy Specify what to pass to [isValid] if the [ImageData] is `null`, defaults to returning `false`
 * @return This [ImageData] if the check is successful
 * @throws IllegalStateException When the check is unsuccessful
 */
fun ImageData?.throwIfInvalid(nullPolicy: (ImageData?) -> Boolean = { false }): ImageData? {
    if (this.isValid(nullPolicy)) {
        return this
    } else {
        throw IllegalStateException("Image data is invalid")
    }
}