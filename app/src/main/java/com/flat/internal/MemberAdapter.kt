package com.flat.internal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.flat.internal.fragments.BaseFragment
import com.flat.internal.models.Member

class MemberAdapter(FragBase : BaseFragment) : RecyclerView.Adapter<MemberAdapter.Holder>() {

    var ArrayItems = ArrayList<Member>()
    var BaesFragRef : BaseFragment? = null

    init {
        this.BaesFragRef = FragBase
    }

    class Holder(ItemView : View) : RecyclerView.ViewHolder(ItemView) {

        val view = ItemView

        fun Update(member : Member) {

            var MemberNameTextView = view.findViewById<TextView>(R.id.ChatNameTextView)
            MemberNameTextView.setText(member.Name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.member_item, parent, false)

        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.Update(ArrayItems[position])

        /*
            After clicking by item, in bundle args setting 'Member' object by index from array
            then changing 'MainFragment' on 'ChatFragment' with 'Member' model.
        */
        holder.view.setOnClickListener(View.OnClickListener {

            var Args = Bundle()
            Args.putSerializable("MemberObj", ArrayItems[position])

            BaesFragRef!!.ExecuteActionFragment(R.id.Act_GoTo_ChatFragment, Args)
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