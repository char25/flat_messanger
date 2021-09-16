package com.flat.internal.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.navigation.findNavController
import com.flat.internal.MainActivity
import com.flat.internal.R
import com.flat.internal.constant.FirebaseConst
import com.flat.internal.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoadingFragment : BaseFragment(R.layout.fragment_loading) {

    var TAG = "LoadingFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (FirebaseConst.FbAuth!!.uid != null) {

            var UserRef = FirebaseConst.FbDb!!.getReference("Users/" + FirebaseConst.FbAuth!!.uid)
            UserRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (!dataSnapshot.exists()) {

                        view.findNavController().navigate(R.id.Act_GoTo_RegisterFragment)
                    }
                    else {
                        view.findNavController().navigate(R.id.Act_GoTo_MainFragment)
                    }
                    UserRef.removeEventListener(this)
                }

                override fun onCancelled(databaseError: DatabaseError) { Log.d(TAG, databaseError.toString()) }
            })

        }
        else {
            view.findNavController().navigate(R.id.Act_GoTo_AuthFragment)
        }
    }
}