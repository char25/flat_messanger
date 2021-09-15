package com.flat.internal.models

import android.app.Activity

import android.util.Log
import com.flat.internal.MainActivity

import com.google.firebase.FirebaseException

import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit
import com.flat.internal.R

open class Auth(var PhoneNum : String) {

    var TAG = "->Auth";
    var FbAuth : FirebaseAuth
    var MyVerificationId : String = ""

    init {
        FbAuth = FirebaseAuth.getInstance()
    }

    fun bindPhoneBuiilder() {

        val options = PhoneAuthOptions.newBuilder(FbAuth)
            .setPhoneNumber(PhoneNum)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(MainActivity.SingleTon!!)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    open var callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {}
        override fun onVerificationFailed(e: FirebaseException) { Log.w(TAG, "onVerificationFailed", e) }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            MyVerificationId = verificationId

            MainActivity.ExecuteActionFragment(R.id.Act_GoTo_VerificationFragment)
        }
    }

    fun VerifyPhoneNumber(code : String) {
        val credential = PhoneAuthProvider.getCredential(MyVerificationId, code)
        signInWithPhoneAuthCredential(credential, MainActivity.SingleTon!!)
    }

    fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential, activity: Activity) {
        FbAuth.signInWithCredential(credential)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")

                    MainActivity.ExecuteActionFragment(R.id.Act_GoTo_MainFragment)

                    val user = task.result?.user
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }
}