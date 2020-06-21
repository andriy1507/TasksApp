package com.spaceapps.tasks.core.extensions

fun Any?.isNull() = this == null

fun Any?.notNull() = this != null

fun <E> List<E>.indexInList(index: Int?) = index in 1 until size