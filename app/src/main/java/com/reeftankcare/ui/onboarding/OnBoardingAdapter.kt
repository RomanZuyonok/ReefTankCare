package com.reeftankcare.ui.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.reeftankcare.ui.onboarding.OnBoardingStepFragment.Companion.getOnBoardingStepFragment

class OnBoardingAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    private val listFragment =
        arrayListOf(
            getOnBoardingStepFragment(StepOnBoarding.STEP_ONE),
            getOnBoardingStepFragment(StepOnBoarding.STEP_TWO),
            getOnBoardingStepFragment(StepOnBoarding.STEP_THREE)
        )

    override fun getCount() = listFragment.size

    override fun getItem(position: Int): Fragment = listFragment[position]


}