package org.tywrapstudios.hookt.types.components.data

import kotlinx.serialization.Serializable
import org.tywrapstudios.hookt.types.components.ComponentType

@Serializable
data class SeparatorData(
    override val type: Int = ComponentType.Separator.value,
    override val id: Int?,
    val divider: Boolean?,
    val spacing: Int?,
) : ComponentData, ContainerChildData
