package com.flat.internal.constant

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FirebaseConst {

    companion object {
        var FbAuth : FirebaseAuth? = null
        var FbDb : FirebaseDatabase? = null
    }

    init {
        FbAuth = FirebaseAuth.getInstance()
        FbDb = FirebaseDatabase.getInstance()
    }
}