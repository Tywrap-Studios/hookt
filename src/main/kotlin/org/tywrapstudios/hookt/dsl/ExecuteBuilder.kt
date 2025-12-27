package org.tywrapstudios.hookt.dsl

import org.tywrapstudios.hookt.forms.ExecuteForm
import org.tywrapstudios.hookt.types.Embed

class ExecuteBuilder : FormBuilder<ExecuteForm> {
    var content: String? = null
    var username: String? = null
    var avatarUrl: String? = null
    var tts: Boolean = false
    var embeds: List<Embed> = mutableListOf()
        set(value) {
            field = value.toMutableList()
        }
    var flags: Int? = null
    var threadName: String? = null
    var appliedTags: List<ULong> = mutableListOf()
        set(value) {
            field = value.toMutableList()
        }

    @HooktDsl
    fun embed(block: EmbedBuilder.() -> Unit) {
        (this.embeds as MutableList<Embed>).add(EmbedBuilder().also(block).build())
    }

    fun tag(tag: ULong) {
        (this.appliedTags as MutableList<ULong>).add(tag)
    }

    override fun build(): ExecuteForm = ExecuteForm(
        content,
        username,
        avatarUrl,
        tts,
        embeds,
        flags,
        threadName,
        appliedTags
    )
}