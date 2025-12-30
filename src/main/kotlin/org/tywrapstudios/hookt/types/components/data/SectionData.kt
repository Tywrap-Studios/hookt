package org.tywrapstudios.hookt.types.components.data

import kotlinx.serialization.Serializable
import org.tywrapstudios.hookt.types.components.ComponentType

@Serializable
data class SectionData(
    override val type: Int = ComponentType.Section.value,
    override val id: Int?,
    val components: List<SectionChildData>,
    val accessory: SectionAccessoryData
) : ComponentData, ContainerChildData
