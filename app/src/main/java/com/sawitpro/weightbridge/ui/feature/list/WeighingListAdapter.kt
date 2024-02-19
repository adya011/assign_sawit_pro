package com.sawitpro.weightbridge.ui.feature.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sawitpro.weightbridge.data.model.entity.TruckDataEntity
import com.sawitpro.weightbridge.databinding.ItemWeighingBinding

class WeighingListAdapter(val onClick: (String) -> Unit) :
    ListAdapter<TruckDataEntity, RecyclerView.ViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemWeighingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeighingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as WeighingViewHolder).bind(getItem(position))
    }

    class WeighingViewHolder(
        private val binding: ItemWeighingBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TruckDataEntity) = with(binding) {
            val a: Map<String, String> = mapOf("asd" to "aqwe", "asd" to "qwe")
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<TruckDataEntity>() {
            override fun areItemsTheSame(
                oldItem: TruckDataEntity,
                newItem: TruckDataEntity
            ): Boolean =
                oldItem.id == newItem.id && oldItem.driverName == newItem.driverName

            override fun areContentsTheSame(
                oldItem: TruckDataEntity,
                newItem: TruckDataEntity
            ): Boolean = oldItem == newItem
        }
    }
}