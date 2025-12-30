package org.tywrapstudios.hookt.types.components

import org.tywrapstudios.hookt.types.components.data.SeparatorData

class SeparatorComponent : Component<SeparatorData>, ContainerChildComponent<SeparatorData> {
    override var type: ComponentType = ComponentType.Separator
    override var id: Int? = null
    var divider: Boolean? = null
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