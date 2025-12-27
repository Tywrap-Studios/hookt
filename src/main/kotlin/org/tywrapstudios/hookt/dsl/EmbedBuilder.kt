package org.tywrapstudios.hookt.dsl

import org.tywrapstudios.hookt.types.*
import java.awt.Color
import kotlin.time.Clock
import kotlin.time.Instant

class EmbedBuilder : FormBuilder<Embed> {
    var title: String? = null
    var description: String? = null
    var url: String? = null

    var timestamp: Instant? = null

    fun timestampNow() {
        this.timestamp = Clock.System.now()
    }

    var color: Color? = null
    fun rgb(int: Int) {
        this.color = Color(int)
    }

    fun rgb(r: Int, g: Int, b: Int) {
        this.color = Color(r, g, b)
    }

    fun hex(hex: String) {
        this.color = Color(hex.hexToInt())
    }

    var footer: Footer? = null
    fun footer(text: String, iconUrl: String? = null, proxyIconUrl: String? = null) {
        this.footer = Footer(text, iconUrl, proxyIconUrl)
    }

    var image: Image? = null
    fun image(url: String, proxyUrl: String? = null, height: Int? = null, width: Int? = null) {
        this.image = Image(url, proxyUrl, height, width)
    }

    var thumbnail: Thumbnail? = null
    fun thumbnail(url: String, proxyUrl: String? = null, height: Int? = null, width: Int? = null) {
        this.thumbnail = Thumbnail(url, proxyUrl, height, width)
    }

    var video: Video? = null
    fun video(url: String, proxyUrl: String? = null, height: Int? = null, width: Int? = null) {
        this.video = Video(url, proxyUrl, height, width)
    }

    var author: Author? = null

    @HooktDsl
    fun author(block: AuthorBuilder.() -> Unit) {
        this.author = AuthorBuilder().also(block).build()
    }

    var fields: List<Field> = mutableListOf()
        set(value) {
            field = value.toMutableList()
        }

    @HooktDsl
    fun field(block: FieldBuilder.() -> Unit) {
        (this.fields as MutableList<Field>).add(FieldBuilder().also(block).build())
    }

    override fun build(): Embed = Embed(
        title,
        description,
        url,
        timestamp.toString(),
        color?.rgb,
        footer,
        image,
        thumbnail,
        video,
        author,
        fields
    )
}