package org.tywrapstudios.hookt.types.components

import org.tywrapstudios.hookt.types.components.data.ContainerChildData
import org.tywrapstudios.hookt.types.components.data.SectionAccessoryData
import org.tywrapstudios.hookt.types.components.data.SectionChildData

interface SectionChildComponent<T : SectionChildData> : Component<T>
interface SectionAccessoryComponent<T : SectionAccessoryData> : Component<T>
interface ContainerChildComponent<T : ContainerChildData> : Component<T>