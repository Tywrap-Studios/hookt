package org.tywrapstudios.hookt.dsl

/**
 * Interface for all builder classes.
 */
interface FormBuilder<T> {
    /**
     * Builds the options from the builder into [T] so
     * it can be used as an instance for a form body.
     */
    fun build(): T
}