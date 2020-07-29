package com.example.facegallery.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.facegallery.R
import com.example.facegallery.ui.main.MainActivity
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton

class LoginActivity : AppCompatActivity() {

    lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val accessToken = AccessToken.getCurrentAccessToken()
        if (accessToken != null && !accessToken.isExpired) {
            Intent(this@LoginActivity, MainActivity::class.java).apply {
                startActivity(this)
            }
            finish()
        }

        setContentView(R.layout.activity_login)

        callbackManager = CallbackManager.Factory.create()

        findViewById<LoginButton>(R.id.btLogin).apply {
            setPermissions("user_photos")
        }

        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult>{
                override fun onSuccess(result: LoginResult?) {
                    Intent(this@LoginActivity, MainActivity::class.java).apply {
                        startActivity(this)
                    }
                }

                override fun onCancel() {
                    // not implemented
                }

                override fun onError(error: FacebookException?) {
                    // not implemented
                }
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}