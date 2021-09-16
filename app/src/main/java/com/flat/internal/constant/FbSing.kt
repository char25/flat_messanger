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

            return Singleton!!
        }
    }

    var FbAuth : FirebaseAuth
    var FbDb : FirebaseDatabase

    init {
        FbAuth = FirebaseAuth.getInstance()
        FbDb = FirebaseDatabase.getInstance()
    }
}