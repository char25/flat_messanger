package com.flat.internal.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.flat.internal.R

class MyDialogFragment(var message : String) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getDialog()!!.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));

        val view = inflater.inflate(R.layout.fragment_dialog, container, false)
        val MessageTextView = view.findViewById<TextView>(R.id.MessageTextView)

        MessageTextView.setText(message)

        view.findViewById<RelativeLayout>(R.id.CancelDialog).setOnClickListener {
            this.dismiss()
        }

        return view
    }
}