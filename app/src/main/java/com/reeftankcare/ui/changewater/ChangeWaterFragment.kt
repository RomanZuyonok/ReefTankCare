package com.reeftankcare.ui.changewater

import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.reeftankcare.databinding.FragmentChangeWaterBinding
import com.reeftankcare.workmanager.NotifyWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class ChangeWaterFragment : Fragment() {

    private var _binding: FragmentChangeWaterBinding? = null
    private val binding: FragmentChangeWaterBinding
        get() = checkNotNull(_binding) {}

    private var selectedYear = 0
    private var selectedMonth = 0
    private var selectedDay = 0
    private var selectedHour = 0
    private var selectedMin = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentChangeWaterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var descriptionText = ""
        //   val today = Calendar.getInstance()

        /* binding.datePicker.init(
             today.get(Calendar.YEAR), today.get(Calendar.MONTH),
             today.get(Calendar.DAY_OF_MONTH)){}*/

        binding.messegeEditText.doAfterTextChanged { textInput ->
            descriptionText = textInput.toString()
        }


        binding.setBtn.setOnClickListener {

            binding.datePicker.setOnDateChangedListener { _, year, month, day ->
                selectedYear = year
                selectedMonth = month
                selectedDay = day

            }

            binding.timePicker.setOnTimeChangedListener { _, hour, minute ->
                selectedHour = hour
                selectedMin = minute
            }

            val userSelectedDateTime = Calendar.getInstance()
            userSelectedDateTime.set(
                selectedYear,
                selectedMonth,
                selectedDay,
                selectedHour,
                selectedMin
            )


            val todayDateTime = Calendar.getInstance()


            val delayInSeconds =
                (userSelectedDateTime.timeInMillis / 1000L) - (todayDateTime.timeInMillis / 1000L)


            createWorkRequest(descriptionText, delayInSeconds)


            Toast.makeText(requireContext(), "Reminder set", Toast.LENGTH_SHORT).show()

            findNavController().popBackStack()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createWorkRequest(message: String, timeDelayInSeconds: Long) {
        val myWorkRequest = OneTimeWorkRequestBuilder<NotifyWorker>()
            .setInitialDelay(timeDelayInSeconds, TimeUnit.SECONDS)
            .setInputData(
                workDataOf(
                    "title" to "Water change",
                    "message" to message,
                )
            )
            .build()

        WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)
    }
}