package com.mis.route.e_commerce.data.models.category

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id: String? = null,
    val name: String? = null,
    val slug: String? = null,
    val image: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null
) : Parcelable