package org.tywrapstudios.hookt.types.components.data

/**
 * Interface implemented by every data class related to components.
 */
interface ComponentData {
    /**
     * The [type](https://discord.com/developers/docs/components/reference#component-object-component-types)
     * of component.
     */
    val type: Int

    /**
     * The optional custom ID for the component.
     */
    val id: Int?
}