package com.sawitpro.weightbridge.ui.feature.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sawitpro.weightbridge.data.model.entity.WeighingTicketEntity
import com.sawitpro.weightbridge.databinding.ItemWeighingBinding

class WeighingListAdapter(private val onClick: (WeighingTicketEntity) -> Unit,
    private val onEditClick: () -> Unit) :
    ListAdapter<WeighingTicketEntity, RecyclerView.ViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemWeighingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeighingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as WeighingViewHolder).bind(getItem(position))
    }

    inner class WeighingViewHolder(
        private val binding: ItemWeighingBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WeighingTicketEntity) = with(binding) {
            tvDriverName.text = item.driverName
            tvLicenseNum.text = item.licenseNumber
            tvDateTime.text = item.date
            root.setOnClickListener {
                //onClick.invoke()
            }
            vEdit.setOnClickListener {
                onEditClick.invoke()
            }
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<WeighingTicketEntity>() {
            override fun areItemsTheSame(
                oldItem: WeighingTicketEntity,
                newItem: WeighingTicketEntity
            ): Boolean =
                oldItem.uId == newItem.uId && oldItem.driverName == newItem.driverName

            override fun areContentsTheSame(
                oldItem: WeighingTicketEntity,
                newItem: WeighingTicketEntity
            ): Boolean = oldItem == newItem
        }
    }
}