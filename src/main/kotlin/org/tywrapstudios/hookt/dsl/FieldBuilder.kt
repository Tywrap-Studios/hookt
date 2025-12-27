package org.tywrapstudios.hookt.dsl

import org.tywrapstudios.hookt.types.Field

class FieldBuilder : FormBuilder<Field> {
    var name: String? = null
    var value: String? = null
    var inline: Boolean? = null

    override fun build(): Field = Field(
        name ?: throw IllegalStateException("Name for field is null"),
        value ?: throw IllegalStateException("Value for field is null"),
        inline
    )
}