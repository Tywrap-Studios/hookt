package org.tywrapstudios.hookt.types.components

import org.tywrapstudios.hookt.types.components.data.ComponentData

/**
 * Interface implemented by every component class, it has the basic
 * [type] and optional [id] "parameters".
 *
 * The idea is that the component classes double as builder classes for
 * the accompanying [T] data classes, so we can use it in Ktor while
 * still keeping the DSL separated. Alongside this, you should have a better
 * view of what a component contains rather than looking at just the options
 * because of the DSL names.
 *
 * That way we can keep the internal nature and explanations of the serialisation classes
 * separate from the explanations on the usage of the components.
 *
 * Every component **must** have an empty constructor, otherwise, we can't automatically
 * build them and use them through reflection. In case there is a custom component
 * type that does need constructors with parameters, preferably make separate DSL functions
 * and a new base interface as to not accidentally interfere with the "automatic" DSL
 * functions hookt provides through use of reflection.
 */
interface Component<T : ComponentData> {
    /**
     * [ComponentData.type]
     */
    var type: ComponentType

    /**
     * [ComponentData.id]
     */
    var id: Int?

    /**
     * This "builds" and returns the contents of the component
     * into [T] in order for it to be passed onto Ktor for
     * serialisation.
     */
    fun getData(): T
}