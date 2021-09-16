package com.flat.internal.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import com.flat.internal.MainActivity
import com.flat.internal.R
import com.flat.internal.constant.FirebaseConst
import com.flat.internal.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RegisterFragment : BaseFragment(R.layout.fragment_register) {

    var TAG = "RegisterFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val FirstNameEditText : EditText = view.findViewById(R.id.FirstNameEditText)
        val LastNameEditText : EditText = view.findViewById(R.id.LastNameEditText)

        val UserRef = FirebaseConst.FbDb!!.getReference("Users/" + FirebaseConst.FbAuth!!.uid)

        view.findViewById<RelativeLayout>(R.id.Register).setOnClickListener {
            //MainActivity.ExecuteActionFragment(R.id.Act_GoTo_RegisterFragment)
            UserRef.setValue(User(
                FirstNameEditText.text.toString(),
                LastNameEditText.text.toString(),
                FirebaseConst.FbAuth!!.currentUser!!.displayName.toString()
            ))

            MainActivity.ExecuteActionFragment(R.id.Act_GoTo_LoadingFragment)
        }
    }
}