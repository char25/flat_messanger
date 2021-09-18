package com.flat.internal.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flat.internal.R
import com.flat.internal.constant.FbSing
import com.flat.internal.models.Member
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ChatFragment : BaseFragment(R.layout.fragment_chat) {

    var TAG = "LoadingFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var MemberObj = this.arguments?.get("MemberObj") as Member


        AppBarInit(view, R.drawable.left_arrow, View.OnClickListener {
            this.fragmentManager!!.popBackStack()
        }, MemberObj.Name)

    }
}