package org.tywrapstudios.hookt.dsl

import org.tywrapstudios.hookt.types.*
import java.awt.Color
import kotlin.time.Clock
import kotlin.time.Instant

/**
 * Builder class for [Embed].
 */
class EmbedBuilder : FormBuilder<Embed> {
    /**
     * [Embed.title]
     */
    var title: String? = null

    /**
     * [Embed.description]
     */
    var description: String? = null

    /**
     * [Embed.url]
     */
    var url: String? = null

    /**
     * [Embed.timestamp]
     */
    var timestamp: Instant? = null

    /**
     * Set [timestamp] to [Clock.System.now].
     */
    @HooktHelperDsl
    fun timestampNow() {
        this.timestamp = Clock.System.now()
    }

    /**
     * [Embed.color]
     */
    var color: Color? = null

    /**
     * Set [color] using an RGB int.
     */
    @HooktHelperDsl
    fun rgb(int: Int) {
        this.color = Color(int)
    }

    /**
     * Set [color] using RGB values.
     * @param r The **red** value
     * @param g The **green** value
     * @param b The **blue** value
     */
    @HooktHelperDsl
    fun rgb(r: Int, g: Int, b: Int) {
        this.color = Color(r, g, b)
    }

    /**
     * Set [color] using a string HEX value.
     */
    @HooktHelperDsl
    fun hex(hex: String) {
        this.color = Color(
            hex
                .replace("#", "")
                .replace("0x", "")
                .hexToInt()
        )
    }

    /**
     * [Embed.footer]
     */
    var footer: Footer? = null

    /**
     * Helper function to set the [footer].
     */
    @HooktHelperDsl
    fun footer(text: String, iconUrl: String? = null, proxyIconUrl: String? = null) {
        this.footer = Footer(text, iconUrl, proxyIconUrl)
    }

    /**
     * [Embed.image]
     */
    var image: Image? = null

    /**
     * Helper function to set the [image].
     */
    @HooktHelperDsl
    fun image(url: String, proxyUrl: String? = null, height: Int? = null, width: Int? = null) {
        this.image = Image(url, proxyUrl, height, width)
    }

    /**
     * [Embed.thumbnail]
     */
    var thumbnail: Thumbnail? = null

    /**
     * Helper function to set the [thumbnail].
     */
    @HooktHelperDsl
    fun thumbnail(url: String, proxyUrl: String? = null, height: Int? = null, width: Int? = null) {
        this.thumbnail = Thumbnail(url, proxyUrl, height, width)
    }

    /**
     * [Embed.video]
     */
    var video: Video? = null

    /**
     * Helper function to set the [video].
     */
    @HooktHelperDsl
    fun video(url: String, proxyUrl: String? = null, height: Int? = null, width: Int? = null) {
        this.video = Video(url, proxyUrl, height, width)
    }

    /**
     * [Embed.author]
     */
    var author: Author? = null

    /**
     * DSL function to set the [author] value.
     */
    @HooktDsl
    fun author(block: AuthorBuilder.() -> Unit) {
        this.author = AuthorBuilder().also(block).build()
    }

    /**
     * [Embed.fields]
     */
    var fields: List<Field>? = null

    /**
     * DSL function to add a [Field] to the [fields] value.
     */
    @HooktDsl
    fun field(block: FieldBuilder.() -> Unit) {
        if (this.fields == null) {
            this.fields = mutableListOf()
        }
        (this.fields as MutableList<Field>).add(FieldBuilder().also(block).build())
    }

    override fun build(): Embed {
        val stamp: String? = if (timestamp == null) null else timestamp.toString()
        val col = color?.rgb?.and(0xFFFFFF)
        return Embed(
            title,
            description,
            url,
            stamp,
            col,
            footer,
            image,
            thumbnail,
            video,
            author,
            fields
        )
    }
}