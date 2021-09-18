package com.flat.internal

import android.app.ActionBar
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.flat.internal.fragments.BaseFragment
import com.flat.internal.models.Member
import com.flat.internal.models.Message

class MessageAdapter() : RecyclerView.Adapter<MessageAdapter.Holder>() {

    var ArrayItems = ArrayList<Message>()


    class Holder(ItemView : View) : RecyclerView.ViewHolder(ItemView) {

        val view = ItemView

        fun Update(message : Message) {


            val MessageTextView = view.findViewById<TextView>(R.id.MessageTextView)
            MessageTextView.setText(message.message)

            if (!message.isIncoming) {
                view.findViewById<LinearLayout>(R.id.MessageContainer).gravity = Gravity.RIGHT
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)

        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.Update(ArrayItems[position])
    }

    override fun getItemCount(): Int {
        return ArrayItems.size
    }

    fun AddItem(Item : Message) {
        ArrayItems.add(Item)
        notifyDataSetChanged()
    }

    fun SetArray(messages : ArrayList<Message>) {
        ArrayItems = messages
        notifyDataSetChanged()
    }
}