package org.jetbrains.squash

import org.jetbrains.squash.expressions.*

interface Name {
    val id: String
}

data class Identifier(override val id: String) : Name {
    init {
        if (id.isEmpty()) error("Identifier cannot be empty")
    }

    override fun toString(): String = "[$id]"
}

data class QualifiedIdentifier<out N : Name>(val parent: N, val identifier: Identifier) : Name {
    override val id: String get() = identifier.id
    override fun toString(): String = "$parent.$identifier"
}

interface NamedExpression<out N : Name, out T> : Expression<T> {
    val name: N
}

