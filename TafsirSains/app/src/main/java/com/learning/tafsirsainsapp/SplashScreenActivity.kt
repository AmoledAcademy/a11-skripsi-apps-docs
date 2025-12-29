package com.learning.tafsirsainsapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.learning.tafsirsainsapp.indo.MainActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val splashScreenActivity: Button = findViewById(R.id.login)

        splashScreenActivity.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
        })
    }

    fun openLink(view: View) {
        val url = "https://drive.google.com/file/d/1yWD4wWzYo_IQFqfq7TVlHXBESdEdWHew/view?usp=sharing"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}