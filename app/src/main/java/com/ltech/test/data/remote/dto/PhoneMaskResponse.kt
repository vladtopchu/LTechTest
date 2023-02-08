package com.ltech.test.data.remote.dto

import com.ltech.test.domain.model.PhoneMask

data class PhoneMaskResponse(
    var phoneMask: String? = null
) {
    fun toModel() = PhoneMask(phoneMask ?: "")
}