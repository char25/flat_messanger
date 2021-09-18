package com.flat.internal.models

import java.io.Serializable

data class Member(var Name : String, var Messages : ArrayList<Message>) : Serializable {

}