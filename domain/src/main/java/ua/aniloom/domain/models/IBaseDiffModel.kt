package ua.aniloom.domain.models

interface IBaseDiffModel<T> {
    val id: T
    override fun equals(other: Any?): Boolean
}
