package com.reeftankcare.ui

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.reeftankcare.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TIME_FINISH_SPLASH = 1500L

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContentView(R.layout.activity_splash)
        lifecycleScope.launch(Dispatchers.Main) {
            delay(TIME_FINISH_SPLASH)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            this@SplashActivity.finish()
        }
    }
}