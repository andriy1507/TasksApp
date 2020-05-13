package com.spaceapps.tasks.repository.mapper

import com.spaceapps.tasks.core.model.Category
import com.spaceapps.tasks.local.model.CategoryLocal

fun Category.toCategoryLocal(): CategoryLocal {
    return CategoryLocal(
        id = id,
        name = name,
        color = color,
        icon = icon
    )
}

fun CategoryLocal.toCategory(): Category {
    return Category(id,name, color, icon)
}