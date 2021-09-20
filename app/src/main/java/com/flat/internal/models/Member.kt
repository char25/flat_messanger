package com.flat.internal.models

import android.util.Log
import com.flat.internal.constant.Fb
import com.flat.internal.constant.FbSing
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.io.Serializable

class Member : Serializable {
    var Name : String? = null
    var ChatId : String? = null

    constructor(Name : String, ChatId : String) {
        this.Name = Name
        this.ChatId = ChatId
    }

    fun SendMessage(message : Message, chatId : String) {
        FbSing.Instance().FbDb!!.getReference("Chats/" + chatId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    val Messages = ArrayList<Message>()

                    for (chatMessage in dataSnapshot.children) {
                        Messages.add(chatMessage.getValue(Message::class.java)!!)
                    }

                    Messages.add(message)

                    FbSing.Instance()
                        .FbDb!!
                        .getReference("Chats/" + chatId)
                        .setValue(Messages)
                }
                override fun onCancelled(databaseError: DatabaseError) { Log.d("Member async INIT", databaseError.toString()) }
            })
    }

    constructor() {

    }
}