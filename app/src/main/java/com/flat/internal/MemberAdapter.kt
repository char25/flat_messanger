package com.flat.internal

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.flat.internal.models.Member

class MemberAdapter : RecyclerView.Adapter<MemberAdapter.Holder>() {

    var ArrayItems = ArrayList<Member>()

    class Holder(ItemView : View) : RecyclerView.ViewHolder(ItemView) {

        val view = ItemView

        fun Update(member : Member) {

            var MemberNameTextView = view.findViewById<TextView>(R.id.MemberNameTextView)
            MemberNameTextView.setText(member.Name + " ->{$position}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.member_item, parent, false)

        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.Update(ArrayItems[position])

        holder.view.setOnClickListener(View.OnClickListener {
            Log.d("Adapter", position.toString())
        })
    }

    override fun getItemCount(): Int {
        return ArrayItems.size
    }

    fun AddItem(Item : Member) {
        ArrayItems.add(Item)
        notifyDataSetChanged()
    }
}