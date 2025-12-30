package org.tywrapstudios.hookt.types.components.data

import kotlinx.serialization.Serializable
import org.tywrapstudios.hookt.types.components.ComponentType

/**
 * The data format for a [org.tywrapstudios.hookt.types.components.SeparatorComponent].
 *
 * @param divider Whether the separator should display a visual divider
 * @param spacing The padding of the separator, either 1 or 2
 */
@Serializable
data class SeparatorData(
    override val type: Int = ComponentType.Separator.value,
    override val id: Int?,
    val divider: Boolean?,
    val spacing: Int?,
) : ComponentData, ContainerChildData
