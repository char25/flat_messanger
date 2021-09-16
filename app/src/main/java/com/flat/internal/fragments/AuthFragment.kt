package com.flat.internal.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View

import android.widget.EditText
import android.widget.RelativeLayout
import com.flat.internal.MainActivity
import com.flat.internal.R
import com.flat.internal.constant.FbSing
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AuthFragment : BaseFragment(R.layout.fragment_auth) {

    var TAG = "AuthFragment";

    var PhoneNum : String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val PhoneNumberEditText : EditText = view.findViewById(R.id.PhoneNumberEditText);

        view.findViewById<RelativeLayout>(R.id.LoginWithPhone).setOnClickListener {
            PhoneNum = PhoneNumberEditText.text.toString()

            val options = PhoneAuthOptions.newBuilder(FbSing.Instance().FbAuth!!)
                .setPhoneNumber(PhoneNum)       // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(MainActivity.Ref!!)                 // Activity (for callback binding)
                .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
        }
    }

    open var callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {}
        override fun onVerificationFailed(e: FirebaseException) { Log.w(TAG, "onVerificationFailed", e) }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {

            var Args = Bundle()
            Args.putString("PhoneNum", PhoneNum)
            Args.putString("VerificationId", verificationId)

            ExecuteActionFragment(R.id.Act_GoTo_VerificationFragment, Args)
        }
    }
}