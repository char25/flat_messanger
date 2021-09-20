package com.flat.internal.models

import com.flat.internal.constant.FbSing
import java.io.Serializable

class Message : Serializable {
    var sender : String? = null
    var message : String? = null

    constructor(sender : String?, message : String?) {
        this.sender = sender
        this.message = message
    }

    constructor() {

    }
}