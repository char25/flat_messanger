package com.flat.internal.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import com.flat.internal.R
import com.flat.internal.constant.FbSing
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class LoadingFragment : BaseFragment(R.layout.fragment_loading) {

    var TAG = "LoadingFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FragmentView = view;

        if (FbSing.Instance().FbAuth!!.uid != null) {

            FbSing.Instance()
                .FbDb!!
                .getReference("Users/" + FbSing.Instance().MyPhoneNum + "/User")
                .addListenerForSingleValueEvent(object : ValueEventListener {

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (!dataSnapshot.exists()) {

                            ExecuteActionFragment(R.id.Act_GoTo_RegisterFragment)
                        }
                        else {
                            ExecuteActionFragment(R.id.Act_GoTo_MainFragment)
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) { Log.d(TAG, databaseError.toString()) }
                })
        }
        else {
            ExecuteActionFragment(R.id.Act_GoTo_AuthFragment)
        }
    }
}