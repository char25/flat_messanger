package com.flat.internal.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flat.internal.MemberAdapter
import com.flat.internal.R
import com.flat.internal.constant.FbSing
import com.flat.internal.models.Member
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class MainFragment : BaseFragment(R.layout.fragment_main) {

    var TAG = "MainFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var MemberRecyclerView = view.findViewById<RecyclerView>(R.id.MemberRecyclerView)

        /*
            Creation MemberAdapter with 'BaseFragment' args
            BaseFragment reference using for start next 'ChatFragment'
            and using 'Member' model in chat fragment after click by item.
        */
        var MemAdapter = MemberAdapter(this)

        MemberRecyclerView.layoutManager = LinearLayoutManager(view.context)
        MemberRecyclerView.adapter = MemAdapter

        AppBarInit(view, R.drawable.add, View.OnClickListener {
            ExecuteActionFragment(R.id.Act_GoTo_CreateChatFragment)
        }, "Flat")

        FbSing.Instance().FbDb!!
            .getReference("Users/" + FbSing.Instance().MyPhoneNum + "/Chats")
            .addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (User in dataSnapshot.children) {

                    MemAdapter.AddItem(Member(User.key.toString(), User.value.toString()))
                }
            }
            override fun onCancelled(databaseError: DatabaseError) { Log.d(TAG, databaseError.toString()) }
        })

        //FbService.startService(view.context, "This is service")

        /*view.findViewById<Button>(R.id.VerifyPhoneNumber).setOnClickListener {

        }*/
    }
}