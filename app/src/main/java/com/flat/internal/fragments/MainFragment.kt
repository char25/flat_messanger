package com.flat.internal.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flat.internal.MainActivity
import com.flat.internal.MemberAdapter
import com.flat.internal.R
import com.flat.internal.models.Member
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

        var MemberRecyclerView = view.findViewById<RecyclerView>(R.id.MemberRecyclerView)

        /*
            Creation MemberAdapter with 'BaseFragment' args
            BaseFragment reference using for start next 'ChatFragment'
            and using 'Member' model in chat fragment after click by item.
        */
        var MemAdapter = MemberAdapter(this)

        MemberRecyclerView.layoutManager = LinearLayoutManager(view.context)
        MemberRecyclerView.adapter = MemAdapter

        view.findViewById<ImageView>(R.id.AppHdrLeftButtonImg).setOnClickListener(View.OnClickListener {
            MemAdapter.AddItem(Member("Vasya shadow 2000"))
        })

        for (i in 0..15) {
            MemAdapter.AddItem(Member("Vasya shadow MAXORXX $i"))
        }

        /*view.findViewById<Button>(R.id.VerifyPhoneNumber).setOnClickListener {

        }*/
    }
}