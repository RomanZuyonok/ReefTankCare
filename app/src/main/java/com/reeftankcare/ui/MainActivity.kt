package com.reeftankcare.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.reeftankcare.R
import com.reeftankcare.databinding.ActivityMainBinding
import com.reeftankcare.repository.SharedPreferenceRepository
import com.reeftankcare.ui.home.HomeListFragment
import com.reeftankcare.ui.onboarding.OnBoardingFragment
import com.reeftankcare.utilits.replaceFragment

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = checkNotNull(_binding) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.fragment_container)
        binding.bottomNavigation.setupWithNavController(navController)



        if (SharedPreferenceRepository.isFirstOpen()) {
            SharedPreferenceRepository.setIsFirstOpen(false)
            //  findNavController().navigate()
            supportFragmentManager.replaceFragment(R.id.fragment_container, HomeListFragment())
        } else {
            supportFragmentManager.replaceFragment(R.id.fragment_container, OnBoardingFragment())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}