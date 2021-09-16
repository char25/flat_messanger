package com.flat.internal.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import com.flat.internal.MainActivity
import com.flat.internal.R
import com.flat.internal.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainFragment : BaseFragment(R.layout.fragment_main) {

    var TAG = "MainFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*view.findViewById<Button>(R.id.VerifyPhoneNumber).setOnClickListener {

        }*/
    }
}