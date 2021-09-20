package com.flat.internal.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import com.flat.internal.MainActivity
import com.flat.internal.R
import com.flat.internal.constant.FbSing
import com.google.firebase.auth.PhoneAuthProvider

class VerificationFragment : BaseFragment(R.layout.fragment_verification) {

    var TAG = "VerificationFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var PhoneNum : String = this.arguments?.get("PhoneNum").toString()
        var VerificationId : String = this.arguments?.get("VerificationId").toString()

        val VerificationCodeEditText : EditText = view.findViewById(R.id.VerificationCodeEditText)
        val VerifyTextView : TextView = view.findViewById(R.id.VerifyTextView)

        VerifyTextView.setText(getString(R.string.code_has_sended) + " " + PhoneNum)

        view.findViewById<RelativeLayout>(R.id.VerifyPhoneNumber).setOnClickListener {
            VerifyPhoneNumber(VerificationId, VerificationCodeEditText.text.toString())
        }
    }

    fun VerifyPhoneNumber(VerificationId : String, code : String) {
        val credential = PhoneAuthProvider.getCredential(VerificationId, code)

        FbSing.Instance().FbAuth!!.signInWithCredential(credential)
            .addOnCompleteListener(MainActivity.Ref!!) { task ->
                if (task.isSuccessful) {
                    ExecuteActionFragment(R.id.Act_GoTo_LoadingFragment)
                }
                else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    ShowFragmentDialog("You write incorrect verification code, check your inbox messages, and find correct code!")
                }
            }
    }
}