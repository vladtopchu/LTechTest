package com.ltech.test.utils

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toastShort(message: String) {
    Toast.makeText(
        requireContext(),
        message,
        Toast.LENGTH_SHORT
    ).show()
}

