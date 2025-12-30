package org.tywrapstudios.hookt.types.components

import org.tywrapstudios.hookt.types.components.data.ComponentData

interface Component<T : ComponentData> {
    var type: ComponentType
    var id: Int?

    fun getData(): T
}