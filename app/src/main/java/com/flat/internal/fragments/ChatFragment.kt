package com.flat.internal.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flat.internal.MemberAdapter
import com.flat.internal.MessageAdapter
import com.flat.internal.R
import com.flat.internal.constant.FbSing
import com.flat.internal.models.Member
import com.flat.internal.models.Message
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ChatFragment : BaseFragment(R.layout.fragment_chat) {

    var TAG = "LoadingFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var MemberObj = this.arguments?.get("MemberObj") as Member

        val MessageEditText = view.findViewById<EditText>(R.id.MessageEditText)

        AppBarInit(view, R.drawable.left_arrow, View.OnClickListener {
            fragmentManager!!.popBackStack()
        }, MemberObj.Name)

        view.findViewById<ImageView>(R.id.SentMessageImageView).setOnClickListener(View.OnClickListener {
            Log.d(TAG, MessageEditText.text.toString())
            MessageEditText.text.clear()
        })

        var MsgAdapter = MessageAdapter()

        val RecyclerView = view.findViewById<RecyclerView>(R.id.MessageRecyclerView)

        RecyclerView.layoutManager = LinearLayoutManager(view.context)
        RecyclerView.adapter = MsgAdapter

        MsgAdapter.SetArray(MemberObj.Messages)
    }
}