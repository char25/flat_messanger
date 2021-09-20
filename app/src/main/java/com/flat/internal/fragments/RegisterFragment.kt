package com.flat.internal.fragments

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.navigation.findNavController
import com.flat.internal.R
import com.flat.internal.constant.FbSing
import com.flat.internal.models.User

class RegisterFragment : BaseFragment(R.layout.fragment_register) {

    var TAG = "RegisterFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val FirstNameEditText : EditText = view.findViewById(R.id.FirstNameEditText)
        val LastNameEditText : EditText = view.findViewById(R.id.LastNameEditText)

        view.findViewById<RelativeLayout>(R.id.Register).setOnClickListener {
            val UserRef = FbSing.Instance().FbDb!!.getReference("Users/" + FbSing.Instance().MyPhoneNum + "/User")

            UserRef.setValue(User(
                FirstNameEditText.text.toString(),
                LastNameEditText.text.toString(),
                FbSing.Instance().FbAuth!!.currentUser!!.phoneNumber.toString()
            ))

            view.findNavController().navigate(R.id.Act_GoTo_LoadingFragment)
        }
    }
}