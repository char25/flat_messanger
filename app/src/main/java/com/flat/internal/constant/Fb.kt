package com.flat.internal.constant

import com.flat.internal.models.Member
import com.flat.internal.models.Message
import com.flat.internal.models.User
import java.util.*

open class Fb {
    companion object {

        fun FindUserAndCreateChat(phonenum : String?) : Boolean{
            //val UserRef = FbSing.Instance().FbDb!!.getReference("Users/" + FbSing.Instance().MyPhoneNum + "/Chats/" + phonenum)

            val randomString = UUID.randomUUID().toString().substring(0,20)

            FbSing.Instance()
                .FbDb!!
                .getReference("Users/" + FbSing.Instance().MyPhoneNum + "/Chats/" + phonenum)
                .setValue(randomString)

            FbSing.Instance()
                .FbDb!!
                .getReference("Users/" + phonenum + "/Chats/" + FbSing.Instance().MyPhoneNum)
                .setValue(randomString)

            FbSing.Instance()
                .FbDb!!
                .getReference("Chats/" + randomString)
                .setValue(0)

            return true
        }
    }
}