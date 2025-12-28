package org.tywrapstudios.hookt.dsl

import org.tywrapstudios.hookt.forms.EditMessageForm
import org.tywrapstudios.hookt.types.Embed

/**
 * Builder class for [EditMessageForm].
 */
class EditMessageBuilder : FormBuilder<EditMessageForm> {
    /**
     * [EditMessageForm.content]
     */
    var content: String? = null

    /**
     * [EditMessageForm.embeds]
     */
    var embeds: List<Embed> = mutableListOf()
        set(value) {
            field = value.toMutableList()
        }

    /**
     * [EditMessageForm.flags]
     */
    var flags: Int? = null

    /**
     * DSL function to add an [Embed] to the [embeds] value.
     */
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