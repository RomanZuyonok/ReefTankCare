package com.reeftankcare.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reeftankcare.database.Measurement
import com.reeftankcare.databinding.ItemMeasurementBinding

class HomeHolder(
    val binding: ItemMeasurementBinding
) : RecyclerView.ViewHolder(binding.root) {}

class HomeListAdapter(
    private val measurements: List<Measurement>
) : RecyclerView.Adapter<HomeHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMeasurementBinding.inflate(inflater, parent, false)
        return HomeHolder(binding)
    }

    override fun onBindViewHolder(
        holder: HomeHolder,
        position: Int
    ) {
        val measurement = measurements[position]
        holder.apply {
            binding.measerItemTextView.text = measurement.id.toString()
            binding.dateItemTextView.text = measurement.date.toString()
            binding.caNumberItemTextView.text = measurement.calcium.toString()
            binding.mgNumberItemTextView.text = measurement.magnesium.toString()
            binding.khNumberItemTextView.text = measurement.kh.toString()
            binding.temperNumberItemTextView.text = measurement.temperature.toString()
        }
    }

    override fun getItemCount() = measurements.size
}