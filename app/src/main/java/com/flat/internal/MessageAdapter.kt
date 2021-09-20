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
import com.flat.internal.constant.Fb
import com.flat.internal.constant.FbSing
import com.flat.internal.fragments.BaseFragment
import com.flat.internal.models.Member
import com.flat.internal.models.Message

class MessageAdapter : RecyclerView.Adapter<MessageAdapter.Holder>() {

    var ArrayItems = ArrayList<Message>()



    class Holder(ItemView : View) : RecyclerView.ViewHolder(ItemView) {

        val view = ItemView

        fun Update(message : Message) {

            setIsRecyclable(false)

            view.findViewById<TextView>(R.id.MessageTextView).setText(message.message)

            if (message!!.sender!!.equals(FbSing.Instance().MyPhoneNum)) {

                view.findViewById<LinearLayout>(R.id.Container).gravity = Gravity.RIGHT
                view.findViewById<LinearLayout>(R.id.MessageContainer).setBackgroundResource(R.drawable.message_border)
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

    fun SetArray(messages : ArrayList<Message>?) {
        ArrayItems = messages!!
        notifyDataSetChanged()
    }

    fun AddItem(message: Message) {
        ArrayItems.add(message)
        notifyDataSetChanged()
    }
}