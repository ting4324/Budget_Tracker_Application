package com.example.isaving2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class About : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
    }


    //three dotted interfaced linked to menu_small//
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_small, menu)
        return true
    }

    //linked to dashboard,log out page, about page//
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item1 -> {
                val intent = Intent(this, Dashboard::class.java)
                startActivity(intent)
                return true
            }
            R.id.item2 -> {
                val intent = Intent(this, LoggedIn::class.java)
                startActivity(intent)
                return true
            }
            R.id.item3 -> {
                val intent = Intent(this, About::class.java)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}