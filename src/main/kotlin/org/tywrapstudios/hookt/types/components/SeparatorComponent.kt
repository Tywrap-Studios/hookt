package org.tywrapstudios.hookt.types.components

import org.tywrapstudios.hookt.types.components.data.SeparatorData

/**
 * A separator component. A separator is a (visual) divider between
 * two other components. In case [divider] is set to false, it simply acts
 * as padding, of which the [spacing] can be 1 or 2. When [divider] is true,
 * it also shows a visual line through the middle of the padding, providing
 * an easy way to keep things organised.
 */
class SeparatorComponent : Component<SeparatorData>, ContainerChildComponent<SeparatorData> {
    override var type: ComponentType = ComponentType.Separator
    override var id: Int? = null

    /**
     * [SeparatorData.divider]
     *
     * If true, a line will be displayed through the padding.
     */
    var divider: Boolean? = null

    /**
     * [SeparatorData.spacing]
     */
    var spacing: Int? = null

    override fun getData(): SeparatorData {
        if (spacing !in 1..2 && spacing != null) {
            throw IllegalStateException("Spacing of Separator Components can only be 1-2")
        }
        return SeparatorData(
            id = id,
            divider = divider,
            spacing = spacing,
        )
    }
}