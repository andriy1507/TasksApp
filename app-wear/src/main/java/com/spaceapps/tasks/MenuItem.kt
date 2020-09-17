package com.spaceapps.tasks

import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.spaceapps.tasks.databinding.ItemMenuBinding
import com.xwray.groupie.viewbinding.BindableItem

class MenuItem(
    @DrawableRes
    private val icon: Int,
    @StringRes
    private val title: Int
) : BindableItem<ItemMenuBinding>() {
    override fun bind(viewBinding: ItemMenuBinding, position: Int) {
        with(viewBinding){
            textViewTitle.setText(title)
            imageViewIcon.setImageResource(icon)
        }
    }

    override fun getLayout() = R.layout.item_menu

    override fun initializeViewBinding(view: View) = ItemMenuBinding.bind(view)
}