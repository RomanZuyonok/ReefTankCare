package com.reeftankcare.ui.changewater

import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.reeftankcare.databinding.FragmentChangeWaterBinding
import com.reeftankcare.workmanager.NotifyWorker
import java.util.concurrent.TimeUnit

class ChangeWaterFragment : Fragment() {

    private var _binding: FragmentChangeWaterBinding? = null
    private val binding
        get() = checkNotNull(_binding) {}

    private var chosenYear = 0
    private var chosenMonth = 0
    private var chosenDay = 0
    private var chosenHour = 0
    private var chosenMin = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangeWaterBinding.inflate(inflater,container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val descriptionText = binding.messegeEditText.text.toString()
        val today = Calendar.getInstance()

        binding.datePicker.init(
            today.get(Calendar.YEAR), today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        )
        { _, year, month, day ->
            chosenYear = year
            chosenMonth = month
            chosenDay = day
        }

         binding.timePicker.setOnTimeChangedListener { _, hour, minute ->
            chosenHour = hour
            chosenMin = minute
        }

        binding.setBtn.setOnClickListener {

            val userSelectedDateTime = Calendar.getInstance()
            userSelectedDateTime.set(chosenYear, chosenMonth, chosenDay, chosenHour, chosenMin)


            val todayDateTime = Calendar.getInstance()


            val delayInSeconds =
                (userSelectedDateTime.timeInMillis / 1000L) - (todayDateTime.timeInMillis / 1000L)


            createWorkRequest(descriptionText, delayInSeconds)


            Toast.makeText(requireContext(), "Reminder set", Toast.LENGTH_SHORT).show()

            findNavController().navigate(ChangeWaterFragmentDirections.showHomeFragment())
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
                    "title" to "Reminder",
                    "message" to message,
                )
            )
            .build()

        WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)
    }
}