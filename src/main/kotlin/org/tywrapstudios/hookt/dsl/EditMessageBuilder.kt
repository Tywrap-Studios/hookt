package org.tywrapstudios.hookt.dsl

import org.tywrapstudios.hookt.forms.EditMessageForm
import org.tywrapstudios.hookt.types.Embed

class EditMessageBuilder : FormBuilder<EditMessageForm> {
    var content: String? = null
    var embeds: List<Embed> = mutableListOf()
        set(value) {
            field = value.toMutableList()
        }
    var flags: Int? = null

    @HooktDsl
    fun embed(block: EmbedBuilder.() -> Unit) {
        (this.embeds as MutableList<Embed>).add(EmbedBuilder().also(block).build())
    }

    override fun build(): EditMessageForm = EditMessageForm(
        content,
        embeds,
        flags,
    )
}