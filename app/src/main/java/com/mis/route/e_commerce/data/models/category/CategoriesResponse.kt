package com.mis.route.e_commerce.data.models.category

import com.mis.route.domain.models.category.Metadata

data class CategoriesResponse(
    val results: Int? = null,
    val metadata: Metadata? = null,
    val data: List<Category?>? = null,
)

