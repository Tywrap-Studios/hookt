package org.tywrapstudios.hookt.dsl

import org.tywrapstudios.hookt.forms.ExecuteForm
import org.tywrapstudios.hookt.types.Embed

/**
 * Builder class for [ExecuteForm].
 */
class ExecuteBuilder : FormBuilder<ExecuteForm> {
    /**
     * [ExecuteForm.content]
     */
    var content: String? = null

    /**
     * [ExecuteForm.username]
     */
    var username: String? = null

    /**
     * [ExecuteForm.avatarUrl]
     */
    var avatarUrl: String? = null

    /**
     * [ExecuteForm.tts]
     */
    var tts: Boolean? = null

    /**
     * [ExecuteForm.embeds]
     */
    var embeds: List<Embed>? = null

    /**
     * [ExecuteForm.flags]
     */
    var flags: Int? = null

    /**
     * [ExecuteForm.threadName]
     */
    var threadName: String? = null

    /**
     * [ExecuteForm.appliedTags]
     */
    var appliedTags: List<ULong>? = null

    /**
     * DSL function to add an [Embed] to the [embeds] option.
     */
    @HooktDsl
    fun embed(block: EmbedBuilder.() -> Unit) {
        if (this.embeds == null) {
            this.embeds = mutableListOf()
        }
        (this.embeds as MutableList<Embed>).add(EmbedBuilder().also(block).build())
    }

    /**
     * Helper function to add a tag to the [appliedTags] option.
     * @param tag IDs of a tag to apply to the thread (requires the webhook channel to be a forum or media channel)
     */
    fun tag(tag: ULong) {
        if (this.appliedTags == null) {
            this.appliedTags = mutableListOf()
        }
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