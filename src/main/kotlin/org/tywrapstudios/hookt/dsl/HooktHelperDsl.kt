package org.tywrapstudios.hookt.dsl

import org.jetbrains.annotations.ApiStatus

@DslMarker
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.FUNCTION)
@ApiStatus.Internal
annotation class HooktHelperDsl()
