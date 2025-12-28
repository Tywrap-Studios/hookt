package org.tywrapstudios.hookt.dsl

import org.tywrapstudios.hookt.types.Field

/**
 * Builder class for [Field].
 */
class FieldBuilder : FormBuilder<Field> {
    /**
     * [Field.name]
     */
    var name: String? = null

    /**
     * [Field.value]
     */
    var value: String? = null

    /**
     * [Field.inline]
     */
    var inline: Boolean? = null

    override fun build(): Field = Field(
        name ?: throw IllegalStateException("Name for field is null"),
        value ?: throw IllegalStateException("Value for field is null"),
        inline
    )
}