package com.mis.route.e_commerce.ui.activities.home.fragments.categories

import com.mis.route.e_commerce.data.models.category.Category

sealed class CategoriesFragmentState {
    class CategoriesLoaded(val categoriesList: List<Category?>) : CategoriesFragmentState()
}
