package org.tywrapstudios.hookt.types.components.data

import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

/**
 * A subtype of [ComponentData] specifically for components that can act
 * as accessories for [org.tywrapstudios.hookt.types.components.SectionComponent]s.
 *
 * Right now, this is only [ThumbnailData], but it might change in the future,
 * so do not hardcode based on just [ThumbnailData].
 */
interface SectionAccessoryData : ComponentData

/**
 * A subtype of [ComponentData] specifically for components that can act
 * as children for [org.tywrapstudios.hookt.types.components.SectionComponent]s.
 *
 * Right now, this is only [TextDisplayData], but it might change in the future,
 * so do not hardcode based on just [TextDisplayData].
 */
interface SectionChildData : ComponentData

/**
 * A subtype of [ComponentData] specifically for components that can act
 * as children for [org.tywrapstudios.hookt.types.components.ContainerComponent]s.
 */
interface ContainerChildData : ComponentData

/**
 * A Kotlinx Serialization module that specifies which [Serializable] classes are
 * subclasses of [ComponentData] and its subinterfaces. When adding custom component
 * support, you should make a similar module and add it to your [kotlinx.serialization.json.Json]
 * config (`serializersModule`) and then also add your custom components to the
 * appropriate polymorphism builders.
 *
 * Similarly, if you're making a custom [kotlinx.serialization.json.Json]
 * config this value must be present alongside
 * `classDiscriminatorMode = ClassDiscriminatorMode.NONE`
 * in order to remain compatible with Discord's `"type"` ([ComponentData.type])
 * option.
 */
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