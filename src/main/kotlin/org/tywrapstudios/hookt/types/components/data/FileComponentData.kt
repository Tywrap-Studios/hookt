package org.tywrapstudios.hookt.types.components.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.tywrapstudios.hookt.types.components.ComponentType

@Serializable
data class FileComponentData(
    override val type: Int = ComponentType.File.value,
    override val id: Int?,
//    val type: Int = ComponentType.File.value,
//    val id: Int?,
    val file: UnfurledMediaItem,
    val spoiler: Boolean?
) : ComponentData, ContainerChildData
