package com.reeftankcare.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.reeftankcare.R
import com.reeftankcare.databinding.ActivityMainBinding
import com.reeftankcare.repository.SharedPreferenceRepository
import com.reeftankcare.ui.home.HomeListFragment
import com.reeftankcare.ui.onboarding.OnBoardingFragment
import com.reeftankcare.utilits.replaceFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var sharedPreferenceRepository: SharedPreferenceRepository

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = checkNotNull(_binding) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (sharedPreferenceRepository.isFirstOpen()) {
            sharedPreferenceRepository.setIsFirstOpen(false)
            supportFragmentManager.replaceFragment(R.id.fragment_container, OnBoardingFragment())
        } else {
            supportFragmentManager.replaceFragment(R.id.fragment_container, HomeListFragment())
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}