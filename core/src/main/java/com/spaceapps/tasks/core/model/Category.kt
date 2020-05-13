package com.spaceapps.tasks.core.model

data class Category(
    val id: Int = 0,
    val name: String,
    val color: Int,
    val icon: Int
) : DomainEntity