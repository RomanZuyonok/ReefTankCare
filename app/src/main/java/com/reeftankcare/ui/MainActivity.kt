package com.reeftankcare.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.reeftankcare.R
import com.reeftankcare.repository.SharedPreferenceRepository
import com.reeftankcare.ui.home.HomeListFragment
import com.reeftankcare.ui.onboarding.OnBoardingFragment
import com.reeftankcare.utilits.replaceFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (SharedPreferenceRepository.isFirstOpen()) {
            SharedPreferenceRepository.setIsFirstOpen(false)
            supportFragmentManager.replaceFragment(R.id.fragment_container, HomeListFragment())
        } else {
            supportFragmentManager.replaceFragment(R.id.fragment_container, OnBoardingFragment())
        }
    }
}