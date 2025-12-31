package org.tywrapstudios.hookt.dsl

import org.tywrapstudios.hookt.forms.ExecuteForm
import org.tywrapstudios.hookt.types.Embed
import org.tywrapstudios.hookt.types.MessageFlag
import org.tywrapstudios.hookt.types.components.Component
import org.tywrapstudios.hookt.types.components.data.ComponentData

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
     * [ExecuteForm.components]
     */
    var components: List<ComponentData>? = null

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

    var componentFlag: Boolean = false

    /**
     * DSL function to add a [Component] to the [components] option.
     * Automatically adds [MessageFlag.IS_COMPONENTS_V2] to [flags].
     */
    @HooktDsl
    inline fun <reified T : Component<*>> component(block: T.() -> Unit) {
        if (components == null) {
            components = mutableListOf()
        }
        val clazz = T::class.java
        (components as MutableList<ComponentData>)
            .add(clazz.getConstructor().newInstance().also(block).getData())
        if (!componentFlag) {
            componentFlag = true
            flag(MessageFlag.IS_COMPONENTS_V2)
        }
    }

    /**
     * Helper function to add a [MessageFlag] to this message.
     */
    @HooktHelperDsl
    fun flag(flag: MessageFlag) {
        if (this.flags == null) {
            this.flags = 1 shl flag.shift
        } else {
            this.flags = this.flags!!.or(1 shl flag.shift)
        }
    }

    /**
     * Helper function to add a tag to the [appliedTags] option.
     * @param tag IDs of a tag to apply to the thread (requires the webhook channel to be a forum or media channel)
     */
    @HooktHelperDsl
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
        components,
        flags,
        threadName,
        appliedTags
    )
}