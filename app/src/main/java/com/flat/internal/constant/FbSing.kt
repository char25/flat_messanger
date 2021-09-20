package com.flat.internal.constant

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FbSing {

    companion object {

        var Singleton : FbSing? = null

        fun Instance() : FbSing {
            if (Singleton == null) {
                this.Singleton = FbSing()
            }

            if (this.Singleton!!.MyPhoneNum == null && this.Singleton!!.FbAuth.uid != null) {
                this.Singleton!!.MyPhoneNum =
                    this.Singleton!!.FbAuth.currentUser!!.phoneNumber.toString()
            }

            return Singleton!!
        }
    }

    var FbAuth : FirebaseAuth
    var FbDb : FirebaseDatabase
    var MyPhoneNum : String? = null

    init {
        FbAuth = FirebaseAuth.getInstance()
        FbDb = FirebaseDatabase.getInstance()
    }
}