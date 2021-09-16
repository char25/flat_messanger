package com.flat.internal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    companion object {
        var Ref : MainActivity? = null
        //var navHostFragment : NavHostFragment? = null

        /*fun ExecuteActionFragment(fragInt : Int) {
            navHostFragment!!.navController.navigate(fragInt)
        }*/
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Ref = this
        //navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }
}