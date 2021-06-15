package com.example.paging3

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paging3.databinding.RecyclerRowBinding
import com.example.paging3.network.CharacterData

class RecyclerViewAdapter : PagingDataAdapter<CharacterData, RecyclerViewAdapter.MyViewHolder>(DiffUtilCallBack) {
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("ASDG:OSFJ:ALSJFASHFLKAFHIAUS", "getItem(position) ${getItem(position)}")
        Log.d("ASDG:OSFJ:ALSJFASHFLKAFHIAUS", "position ${position}")
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RecyclerRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class MyViewHolder(private val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CharacterData?) {
            binding.nameTextView.text = item?.name
            binding.descTextView.text = item?.species

            Glide.with(binding.imageView)
                .load(item?.image)
                .circleCrop()
                .into(binding.imageView)
        }
    }

    object DiffUtilCallBack: DiffUtil.ItemCallback<CharacterData>() {
        override fun areItemsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean = oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean = oldItem == newItem

    }
}