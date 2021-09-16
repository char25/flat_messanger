package com.flat.internal

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.flat.internal.models.Auth
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {

    companion object {
        var SingleTon : MainActivity? = null
        var navHostFragment : NavHostFragment? = null

        fun ExecuteActionFragment(fragInt : Int) {
            navHostFragment!!.navController.navigate(fragInt)
        }

        fun openSoftKeyboard(context: Context, view: View) {
            view.requestFocus()
            // open the soft keyboard
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SingleTon = this
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        if (FirebaseAuth.getInstance().uid != null) {
            ExecuteActionFragment(R.id.Act_GoTo_MainFragment)
        }
        else {
            ExecuteActionFragment(R.id.Act_GoTo_AuthFragment)
        }
        //ExecuteActionFragment(R.id.Act_GoTo_SplashFragment)
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Users")
        myRef.setValue("Hello, World!")
    }

}