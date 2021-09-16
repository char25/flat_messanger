package com.flat.internal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View

import android.widget.EditText
import android.widget.RelativeLayout
import com.flat.internal.R
import com.flat.internal.models.Auth

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AuthFragment : BaseFragment(R.layout.fragment_auth) {

    var TAG = "AuthFragment";
    companion object {
        var AuthSingleTon : Auth? = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val PhoneNumberEditText : EditText = view.findViewById(R.id.PhoneNumberEditText);

        view.findViewById<RelativeLayout>(R.id.LoginWithPhone).setOnClickListener {
            AuthSingleTon = Auth(PhoneNumberEditText.text.toString())
            AuthSingleTon!!.bindPhoneBuiilder()
        }
    }
}