package com.example.isaving2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.widget.ImageView
import com.example.isaving2.R
import kotlinx.android.synthetic.main.activity_introductory.*

class IntroductoryActivity : AppCompatActivity() {
    lateinit var logo: ImageView
    lateinit var appName: ImageView
    lateinit var moneyImg: ImageView
    lateinit var lottieAnimationView: LottieAnimationView
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introductory)
        logo = findViewById(R.id.logo)
        appName = findViewById(R.id.app_name)
        moneyImg = findViewById(R.id.img)
        lottieAnimationView = findViewById(R.id.lottie)
        moneyImg.animate().translationY(-1600f).setDuration(1000).startDelay = 2000
        logo.animate().translationY(1400f).setDuration(1000).startDelay = 2000
        appName.animate().translationY(1400f).setDuration(1000).startDelay = 2000
        lottieAnimationView.animate().translationY(1400f).setDuration(1000).startDelay = 2000

            handler = Handler()
            handler.postDelayed({

                // Delay and Start Activity
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            } , 2100) // here we're delaying to startActivity after 3seconds

        }
    }


