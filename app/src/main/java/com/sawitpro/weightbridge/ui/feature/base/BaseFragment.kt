package com.sawitpro.weightbridge.ui.feature.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

abstract class BaseFragment : Fragment() {

    private val progressDialog by lazy {
        ProgressDialog()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigateUp()
                }
            }
        )
    }

    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showFullLoading() {
        hideFullLoading()
        progressDialog.show(parentFragmentManager, progressDialog.tag)
    }

    fun hideFullLoading() {
        if (progressDialog.isAdded) {
            progressDialog.dismiss()
        }
    }
}