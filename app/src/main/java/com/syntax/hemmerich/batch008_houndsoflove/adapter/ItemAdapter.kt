package com.syntax.hemmerich.batch008_houndsoflove.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.syntax.hemmerich.batch008_houndsoflove.R
import com.syntax.hemmerich.batch008_houndsoflove.databinding.ListItemBinding

class ItemAdapter(val dataSet: List<String>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataSet[position]
        val imgUri = item.toUri().buildUpon().scheme("https").build()

        holder.binding.listImage.load(imgUri){
            error(R.drawable.baseline_error_24)
            transformations(RoundedCornersTransformation(10f))
        }

    }
}