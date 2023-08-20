package com.reeftankcare.ui.home

import android.icu.text.DateFormat
import androidx.recyclerview.widget.RecyclerView
import com.reeftankcare.database.Measurement
import com.reeftankcare.databinding.ItemMeasurementBinding

class HomeHolder(
    private val binding: ItemMeasurementBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        measurement: Measurement,
        onMeasureClicked: (measureID: Long) -> Unit
    ) {
        binding.measerItemTextView.text = measurement.id.toString()
        binding.dateItemTextView.text = DateFormat.getDateInstance().format(measurement.date)
        binding.caNumberItemTextView.text = measurement.calcium.toString()
        binding.mgNumberItemTextView.text = measurement.magnesium.toString()
        binding.khNumberItemTextView.text = measurement.kh.toString()
        binding.temperNumberItemTextView.text = measurement.temperature.toString()

        binding.root.setOnClickListener {
            onMeasureClicked(measurement.id)
        }
    }
}