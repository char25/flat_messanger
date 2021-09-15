package com.flat.internal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class VerificationFragment : BaseFragment(R.layout.fragment_verification) {

    var TAG = "VerificationFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val VerifyTextView : TextView = view.findViewById(R.id.VerifyTextView)
        VerifyTextView.setText(getString(R.string.code_has_sended) + " " + AuthFragment.AuthSingleTon!!.PhoneNum)

        view.findViewById<Button>(R.id.VerifyPhoneNumber).setOnClickListener {
            val VerificationCodeEditText : EditText = view.findViewById(R.id.VerificationCodeEditText)
            AuthFragment.AuthSingleTon!!.VerifyPhoneNumber(VerificationCodeEditText.text.toString())
        }
    }
}