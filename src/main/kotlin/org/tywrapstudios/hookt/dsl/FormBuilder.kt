package org.tywrapstudios.hookt.dsl

interface FormBuilder<T> {
    fun build(): T
}