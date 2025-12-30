package org.tywrapstudios.hookt.types.components

import org.tywrapstudios.hookt.types.components.data.ContainerChildData
import org.tywrapstudios.hookt.types.components.data.SectionAccessoryData
import org.tywrapstudios.hookt.types.components.data.SectionChildData

/**
 * A subtype of [Component] specifically for components that can act as children for
 * [SectionComponent]s.
 *
 * Right now this is only [TextDisplayComponent], but it might change in the future,
 * so do not hardcode based on just [TextDisplayComponent]
 */
interface SectionChildComponent<T : SectionChildData> : Component<T>

/**
 * A subtype of [Component] specifically for components that can act as accessories for
 * [SectionComponent]s.
 *
 * Right now this is only [ThumbnailComponent], but it might change in the future,
 * so do not hardcode based on just [ThumbnailComponent]
 */
interface SectionAccessoryComponent<T : SectionAccessoryData> : Component<T>

/**
 * A subtype of [Component] specifically for components that can act as children for
 * [ContainerComponent]s.
 */
interface ContainerChildComponent<T : ContainerChildData> : Component<T>