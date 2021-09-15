package com.flat.internal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View

import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.NavHostFragment
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

        view.findViewById<Button>(R.id.LoginWithPhone).setOnClickListener {
            AuthSingleTon = Auth(PhoneNumberEditText.text.toString())
            AuthSingleTon!!.bindPhoneBuiilder()
        }
    }
}