package com.reeftankcare.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reeftankcare.R
import com.reeftankcare.databinding.FragmentOnboardingBinding
import com.reeftankcare.ui.home.HomeListFragment
import com.reeftankcare.utilits.replaceFragment


class OnBoardingFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = OnBoardingAdapter(parentFragmentManager)
        binding.circleIndicator.setViewPager(binding.viewPager)

        binding.skipBtn.setOnClickListener {
            parentFragmentManager.replaceFragment(R.id.fragment_container, HomeListFragment())
        }

    }
}