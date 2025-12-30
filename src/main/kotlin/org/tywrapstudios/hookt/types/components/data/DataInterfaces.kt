package org.tywrapstudios.hookt.types.components.data

import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

interface SectionAccessoryData : ComponentData
interface SectionChildData : ComponentData
interface ContainerChildData : ComponentData

val ComponentsSerializersModule = SerializersModule {
    polymorphic(ComponentData::class) {
        subclass(ContainerData::class)
        subclass(FileComponentData::class)
        subclass(MediaGalleryData::class)
        subclass(SectionData::class)
        subclass(SeparatorData::class)
        subclass(TextDisplayData::class)
        subclass(ThumbnailData::class)
    }

    polymorphic(SectionAccessoryData::class) {
        subclass(ThumbnailData::class)
    }

    polymorphic(SectionChildData::class) {
        subclass(TextDisplayData::class)
    }

    polymorphic(ContainerChildData::class) {
        subclass(FileComponentData::class)
        subclass(MediaGalleryData::class)
        subclass(SectionData::class)
        subclass(SeparatorData::class)
        subclass(TextDisplayData::class)
    }
}