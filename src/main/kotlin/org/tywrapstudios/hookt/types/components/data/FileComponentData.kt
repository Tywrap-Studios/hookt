package org.tywrapstudios.hookt.types.components.data

import kotlinx.serialization.Serializable
import org.tywrapstudios.hookt.types.components.ComponentType

/**
 * The data format for a [org.tywrapstudios.hookt.types.components.FileComponent].
 * 
 * @param file An unfurled media item that contains an `attachment://` reference
 * to the file
 * @param spoiler Whether to apply a spoiler blur to the file
 */
@Serializable
data class FileComponentData(
    override val type: Int = ComponentType.File.value,
    override val id: Int?,
    val file: UnfurledMediaItem,
    val spoiler: Boolean?
) : ComponentData, ContainerChildData
