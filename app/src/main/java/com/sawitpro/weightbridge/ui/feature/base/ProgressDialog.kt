package com.sawitpro.weightbridge.ui.feature.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.sawitpro.weightbridge.R

class ProgressDialog : DialogFragment() {
    private var isShowing = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_loading, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        dialog?.setCancelable(false)
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (!isShowing) {
            isShowing = true
            super.show(manager, tag)
        }
    }

    override fun dismiss() {
        if (isShowing) {
            isShowing = false
            super.dismiss()
        }
    }
}