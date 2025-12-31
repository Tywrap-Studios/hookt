package org.tywrapstudios.hookt.dsl

import org.jetbrains.annotations.ApiStatus
import org.tywrapstudios.hookt.forms.ExecuteForm
import org.tywrapstudios.hookt.types.AttachmentData
import org.tywrapstudios.hookt.types.Embed
import org.tywrapstudios.hookt.types.MessageFlag
import org.tywrapstudios.hookt.types.components.Component
import org.tywrapstudios.hookt.types.components.data.ComponentData
import java.io.File
import java.nio.file.Path
import kotlin.io.path.Path

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
     * [ExecuteForm.attachments]
     */
    var attachments: List<AttachmentData>? = null

    /**
     * [ExecuteForm.files]
     */
    @ApiStatus.Internal
    var files: MutableList<File> = emptyList<File>().toMutableList()

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

    /**
     * DSL function to add a [File] to the [files] and [attachments].
     *
     * You can reference it using `attachment://<filename>` (`filename` includes
     * dot and extension)
     *
     * @param file The file to add
     * @param autoId Whether to automatically apply the ID value in case you
     * don't specific it in the [attachment] builder. Set this to false if you
     * don't want it overridden
     * @param attachment The builder to add extra info to the attachment,
     * such as a description
     */
    @HooktDsl
    fun file(file: File, autoId: Boolean = true, attachment: AttachmentBuilder.() -> Unit = {
        id = files.size.toULong()
        filename = "${file.name}"
    }) {
        // We do this to ensure the ID will match up with the ID
        // given to the multipart/form-data in the request process.
        val idAdder: AttachmentBuilder.() -> Unit  = {
            id = files.size.toULong()
        }
        if (this.attachments == null) {
            this.attachments = mutableListOf()
        }
        val builder = if (autoId) {
            AttachmentBuilder()
                .also(attachment)
                .also(idAdder)
        } else {
            AttachmentBuilder()
                .also(attachment)
        }
        (this.attachments as MutableList<AttachmentData>)
            .add(builder.build())
        files.add(file)
    }

    /**
     * DSL function to add a [File] to the [files] and [attachments].
     *
     * You can reference it using `attachment://<filename>` (`filename` includes
     * dot and extension)
     *
     * @param path The path to the file to add
     * @param autoId Whether to automatically apply the ID value in case you
     * don't specific it in the [attachment] builder. Set this to false if you
     * don't want it overridden
     * @param attachment The builder to add extra info to the attachment,
     * such as a description
     */
    @HooktDsl
    fun file(path: Path, autoId: Boolean = true,  attachment: AttachmentBuilder.() -> Unit = {
        val file = path.toFile()
        id = files.size.toULong()
        filename = "${file.name}"
    }) {
        file(path.toFile(), autoId, attachment)
    }

    /**
     * DSL function to add a [File] to the [files] and [attachments].
     *
     * You can reference it using `attachment://<filename>` (`filename` includes
     * dot and extension)
     *
     * @param path The path to the file to add
     * @param autoId Whether to automatically apply the ID value in case you
     * don't specific it in the [attachment] builder. Set this to false if you
     * don't want it overridden
     * @param attachment The builder to add extra info to the attachment,
     * such as a description
     */
    @HooktDsl
    fun file(path: String, autoId: Boolean = true,  attachment: AttachmentBuilder.() -> Unit = {
        val file = Path(path).toFile()
        id = files.size.toULong()
        filename = "${file.name}"
    }) {
        file(Path(path).toFile(), autoId, attachment)
    }

    override fun build(): ExecuteForm = ExecuteForm(
        content,
        username,
        avatarUrl,
        tts,
        embeds,
        components,
        attachments,
        files,
        flags,
        threadName,
        appliedTags
    )
}