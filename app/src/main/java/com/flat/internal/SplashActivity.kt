package com.flat.internal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import com.flat.internal.constant.FirebaseConst
import com.google.android.material.imageview.ShapeableImageView

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        FirebaseConst()

        var Logo : ShapeableImageView = findViewById(R.id.Logo);
        Logo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.logo))

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            this.finish()
        }, 1300)
    }
}