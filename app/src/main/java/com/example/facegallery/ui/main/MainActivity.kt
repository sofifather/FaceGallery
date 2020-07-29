package com.example.facegallery.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.facegallery.R
import com.example.facegallery.ui.login.LoginActivity
import com.facebook.login.LoginManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.logout ->{
                LoginManager.getInstance().logOut()
                Intent(this, LoginActivity::class.java).apply {
                    startActivity(this)
                    finish()
                }
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}