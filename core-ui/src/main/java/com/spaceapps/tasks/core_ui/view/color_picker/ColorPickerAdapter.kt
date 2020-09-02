package com.spaceapps.tasks.core_ui.view.color_picker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.spaceapps.tasks.core_ui.R
import com.spaceapps.tasks.core_ui.SelectableResources
import com.spaceapps.tasks.core_ui.databinding.ItemColorBinding
import com.spaceapps.tasks.core_ui.visibleIf

class ColorPickerAdapter : RecyclerView.Adapter<ColorPickerAdapter.ColorPickerViewHolder>() {

    private fun getItem(position: Int) = colors[position]

    class ColorItem(@ColorRes val color: Int, var selected: Boolean = false)

    fun getSelected() = colors.indexOfFirst { it.selected }

    fun selectItem(position: Int){
        colors[position].selected = true
    }

    private val colors: List<ColorItem> = SelectableResources.COLORS.map { ColorItem(it) }

    class ColorPickerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding by lazy { ItemColorBinding.bind(itemView) }

        fun bind(item: ColorItem) {
            itemView.apply {
                binding.colorCircleView.setCardBackgroundColor(ContextCompat.getColor(context, item.color))
                binding.checkImageView.visibleIf(item.selected)
            }
        }

        fun setOnClickListener(listener: () -> Unit) {
            itemView.setOnClickListener {
                listener()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorPickerViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_color, parent, false)
        return ColorPickerViewHolder(itemView)
    }

    override fun getItemCount() = colors.size

    override fun onBindViewHolder(holder: ColorPickerViewHolder, position: Int) {
        holder.apply {
            bind(getItem(position))
            setOnClickListener {
                colors.forEach {
                    it.selected = false
                }
                getItem(position).selected = true
                notifyDataSetChanged()
            }
        }
    }
}