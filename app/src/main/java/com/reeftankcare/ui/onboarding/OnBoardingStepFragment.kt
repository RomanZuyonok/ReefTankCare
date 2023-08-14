package com.reeftankcare.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.reeftankcare.R
import com.reeftankcare.databinding.FragmentOnboardingStepBinding

private const val STEP_EXTRA = "stepExtra"

class OnBoardingStepFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingStepBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingStepBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val stepFragment = arguments?.getInt(STEP_EXTRA) ?: 1

        when (stepFragment) {
            StepOnBoarding.STEP_ONE.step -> {
                binding.titleTextViewStep.setText(R.string.step_one)
                binding.stepImageView.setImageResource(R.drawable.logo)
            }
            StepOnBoarding.STEP_TWO.step -> {
                binding.titleTextViewStep.setText(R.string.step_two)
                binding.stepImageView.setImageResource(R.drawable.chat)
            }
            StepOnBoarding.STEP_THREE.step -> {
                binding.titleTextViewStep.setText(R.string.step_three)
                binding.stepImageView.setImageResource(R.drawable.countries)
            }
        }
    }

    companion object {

        fun getOnBoardingStepFragment(stepNumber: StepOnBoarding): OnBoardingStepFragment {
            return OnBoardingStepFragment().apply {
                arguments = bundleOf(STEP_EXTRA to stepNumber.step)
            }
        }
    }
}