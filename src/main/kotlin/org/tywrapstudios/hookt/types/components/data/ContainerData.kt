package org.tywrapstudios.hookt.types.components.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.tywrapstudios.hookt.types.components.ComponentType

/**
 * The data format for a [org.tywrapstudios.hookt.types.components.ContainerComponent].
 * @param components A list containing [ContainerChildData] objects from
 * [org.tywrapstudios.hookt.types.components.ContainerChildComponent]s
 * @param accentColor The colour of the "embed" the container makes
 * @param spoiler Whether to apply a spoiler to the container
 */
@Serializable
data class ContainerData(
    override val type: Int = ComponentType.Container.value,
    override val id: Int?,
    val components: List<ContainerChildData>,
    @SerialName("accent_color")
    val accentColor: Int?,
    val spoiler: Boolean?
) : ComponentData
