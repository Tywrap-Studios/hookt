package org.tywrapstudios.hookt.types.components.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.tywrapstudios.hookt.types.components.ComponentType

@Serializable
data class ContainerData(
    override val type: Int = ComponentType.Container.value,
    override val id: Int?,
//    val type: Int = ComponentType.Container.value,
//    val id: Int?,
    val components: List<ContainerChildData>,
    @SerialName("accent_color")
    val accentColor: Int?,
    val spoiler: Boolean?
) : ComponentData
