package com.dexmemore.testproject.adapters

import android.content.Context

import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup


import android.widget.ArrayAdapter
import android.widget.TextView

import com.dexmemore.testproject.databinding.ListItemBinding


internal class NumbAdapter internal constructor(
    context: Context,
    resource: Int,
    private val itemList: MutableList<String>
) : ArrayAdapter<NumbAdapter.ItemViewHolder>(context, resource) {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private lateinit var itemBinding: ListItemBinding

    override fun getCount(): Int {
        return this.itemList.size
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var convertView = view
        val holder: ItemViewHolder
        if (convertView == null) {
            itemBinding = ListItemBinding.inflate(inflater)
            convertView = itemBinding.root
            holder = ItemViewHolder()
            holder.name = itemBinding.textView
            convertView.tag = holder
        } else {
            holder = convertView.tag as ItemViewHolder
        }
        holder.name!!.text = this.itemList[position]
        return convertView
    }

    internal class ItemViewHolder {
        var name: TextView? = null
    }
}
