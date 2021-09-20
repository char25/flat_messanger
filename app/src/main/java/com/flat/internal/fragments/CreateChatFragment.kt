package com.flat.internal.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.fragment.app.FragmentManager
import com.flat.internal.R
import com.flat.internal.constant.Fb
import com.flat.internal.constant.FbSing
import com.flat.internal.models.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.util.*

class CreateChatFragment : BaseFragment(R.layout.fragment_create_chat) {

    var TAG = "CreateChatFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AppBarInit(view, R.drawable.left_arrow, View.OnClickListener {
            fragmentManager!!.popBackStack()
        }, "Create chat")

        val PhoneEditText = view.findViewById<EditText>(R.id.PhoneEditText)

        view.findViewById<RelativeLayout>(R.id.CreateChat).setOnClickListener {

            FbSing.Instance()
                .FbDb!!
                .getReference("Users/" + PhoneEditText.text.toString())
                .addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val UserObj = dataSnapshot.child("User").getValue(User::class.java)

                        FbSing.Instance()
                            .FbDb!!
                            .getReference("Users/" + FbSing.Instance().MyPhoneNum + "/Chats/" + UserObj!!.Phonenumber!!)
                            .addListenerForSingleValueEvent(object : ValueEventListener {
                                override fun onDataChange(dataSnapshot: DataSnapshot) {
                                    if (!dataSnapshot.exists()) {
                                        Fb.FindUserAndCreateChat(UserObj!!.Phonenumber!!)
                                        ExecuteActionFragment(R.id.Act_GoTo_MainFragment)
                                    }
                                    else {
                                        ShowFragmentDialog("This member exist in your profile, write another number.")
                                    }
                                }
                                override fun onCancelled(databaseError: DatabaseError) { Log.d(TAG, databaseError.toString()) }
                            })
                    }
                    else {
                        ShowFragmentDialog("User with this phone number not exist.")
                    }

                }
                override fun onCancelled(databaseError: DatabaseError) { Log.d(TAG, databaseError.toString()) }
            })
        }
    }
}