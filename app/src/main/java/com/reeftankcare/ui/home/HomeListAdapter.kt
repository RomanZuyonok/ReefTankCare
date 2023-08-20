package com.reeftankcare.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reeftankcare.database.Measurement
import com.reeftankcare.databinding.ItemMeasurementBinding

class HomeListAdapter(
    private val measurements: List<Measurement>,
    private val onMeasureClicked: (measureID: Long ) -> Unit
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
        holder.bind(measurement, onMeasureClicked)
    }

    override fun getItemCount() = measurements.size
}