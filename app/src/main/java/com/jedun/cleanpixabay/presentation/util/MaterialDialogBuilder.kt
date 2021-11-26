package com.jedun.cleanpixabay.presentation.util

import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jedun.cleanpixabay.R

fun Fragment.showDialog(positiveAction: () -> Unit) {

    MaterialAlertDialogBuilder(this.requireContext())
        .setTitle(resources.getString(R.string.show_details))
        .setMessage(resources.getString(R.string.show_image_details))
        .setNegativeButton(resources.getString(R.string.decline)) { dialog, which ->
            dialog.dismiss()
        }
        .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
            positiveAction()
        }
        .show()

}