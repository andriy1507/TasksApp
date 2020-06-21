package com.spaceapps.tasks.core_ui.view.icon_picker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.spaceapps.tasks.core_ui.R
import com.spaceapps.tasks.core_ui.SelectableResources
import com.spaceapps.tasks.core_ui.getThemeColor
import kotlinx.android.synthetic.main.item_icon.view.*

class IconPickerAdapter : RecyclerView.Adapter<IconPickerAdapter.IconPickerViewHolder>() {

    private val icons: List<IconItem> = SelectableResources.ICONS.map { IconItem(it) }

    fun selectItem(position: Int){
        icons[position].selected = true
    }

    class IconItem(@DrawableRes val icon: Int, var selected: Boolean = false)

    class IconPickerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val selectedColor = itemView.context.getThemeColor(R.attr.colorSecondaryDark)
        private val unSelectedColor = itemView.context.getThemeColor(R.attr.colorPrimaryDark)

        fun bind(item: IconItem) {
            itemView.iconImageView.apply {
                val drawable = context.getDrawable(item.icon)?.apply {
                    setTint(if (item.selected) selectedColor else unSelectedColor)
                }
                setImageDrawable(drawable)
            }
        }

        fun setOnClickListener(listener: () -> Unit) {
            itemView.setOnClickListener {
                listener()
            }
        }
    }

    fun getSelected() = icons.indexOfFirst { it.selected }

    private fun getItem(position: Int): IconItem = icons[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconPickerViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_icon, parent, false)
        return IconPickerViewHolder(itemView)
    }

    override fun getItemCount() = icons.size

    override fun onBindViewHolder(holder: IconPickerViewHolder, position: Int) {
        holder.apply {
            bind(getItem(position))
            setOnClickListener {
                icons.forEach { it.selected = false }
                getItem(position).selected = true
                notifyDataSetChanged()
            }
        }
    }
}