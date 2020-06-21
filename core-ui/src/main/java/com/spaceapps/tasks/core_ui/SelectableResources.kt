package com.spaceapps.tasks.core_ui

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.spaceapps.tasks.core_ui.R.color.*
import com.spaceapps.tasks.core_ui.R.drawable.*

object SelectableResources {

    @ColorRes
    val COLORS :List<Int> =
        listOf(
            red,
            pink,
            purple,
            deep_purple,
            indigo,
            blue,
            green,
            lime,
            yellow,
            amber,
            orange,
            deep_orange
        )
    @DrawableRes
    val ICONS:List<Int> =
        listOf(
            ic_sentiment_very_satisfied,
            ic_sentiment_very_dissatisfied,
            ic_spa,
            ic_weekend,
            ic_cake,
            ic_computer,
            ic_fastfood,
            ic_fitness_center,
            ic_flash_on,
            ic_local_bar,
            ic_local_hospital,
            ic_looks,
            ic_palette,
            ic_restaurant,
            ic_school
        )
}