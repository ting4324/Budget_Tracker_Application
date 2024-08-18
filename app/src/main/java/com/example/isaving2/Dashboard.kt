package com.example.isaving2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_dashboard.*

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        //six cardview with function to each class)//
        cardview.setOnClickListener{
            startActivity(Intent(this,MainTransaction::class.java))
        }
        cardview2.setOnClickListener{
            startActivity(Intent(this,TransactionDash::class.java))
        }
        cardview6.setOnClickListener{
            startActivity(Intent(this,LoggedIn::class.java))
        }
        cardview4.setOnClickListener{
            startActivity(Intent(this,Suggestion::class.java))
        }
        cardview5.setOnClickListener{
            startActivity(Intent(this,About::class.java))
        }
    }

}