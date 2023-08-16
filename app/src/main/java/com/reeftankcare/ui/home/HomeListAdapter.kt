package com.reeftankcare.ui.home

import android.icu.text.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.reeftankcare.database.Measurement
import com.reeftankcare.databinding.ItemMeasurementBinding

class HomeHolder(
    private val binding: ItemMeasurementBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(measurement: Measurement) {
        binding.measerItemTextView.text = measurement.id.toString()
        binding.dateItemTextView.text = DateFormat.getDateInstance().format(measurement.date)
        binding.caNumberItemTextView.text = measurement.calcium.toString()
        binding.mgNumberItemTextView.text = measurement.magnesium.toString()
        binding.khNumberItemTextView.text = measurement.kh.toString()
        binding.temperNumberItemTextView.text = measurement.temperature.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${measurement.id} clicked!",
                Toast.LENGTH_LONG
            )
                .show()
        }
    }
}

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
        holder.bind(measurement)
    }

    override fun getItemCount() = measurements.size
}