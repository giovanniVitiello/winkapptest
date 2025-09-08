package com.wink.app.domain

class PagedList<out T>(
        val data: List<T>,
        val page: Int,
        val isCompleted: Boolean) {

    val size = data.size

    fun isEmpty() = data.isEmpty()

    companion object {
        fun <T> empty() = PagedList<T>(emptyList(), -1, false)
    }
}

fun <T> List<T>.toPagedList(page: Int = 0, isCompleted: Boolean = false) =
        PagedList(this, page, isCompleted)

fun <T> PagedList<T>.addPage(other: PagedList<T>) =
        PagedList(data.plus(other.data), other.page, other.isCompleted)
