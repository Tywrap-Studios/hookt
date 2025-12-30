package org.tywrapstudios.hookt.types.components.data

import kotlinx.serialization.Serializable
import org.tywrapstudios.hookt.types.components.ComponentType

/**
 * The data format for a [org.tywrapstudios.hookt.types.components.SectionComponent].
 *
 * @param components A list of max 3 [SectionChildData] objects from
 * [org.tywrapstudios.hookt.types.components.SectionChildComponent]s
 * @param accessory The data of a
 * [org.tywrapstudios.hookt.types.components.SectionAccessoryComponent]
 */
@Serializable
data class SectionData(
    override val type: Int = ComponentType.Section.value,
    override val id: Int?,
    val components: List<SectionChildData>,
    val accessory: SectionAccessoryData
) : ComponentData, ContainerChildData
