package com.mis.route.e_commerce.ui.activities.home.fragments.home

import com.mis.route.e_commerce.data.models.category.Category

sealed class HomeFragmentAction {
    object GetAllCategories : HomeFragmentAction()
    class GetProducts(val categoryId: String) : HomeFragmentAction()
    class CategoryClicked(val category: Category, val position: Int) : HomeFragmentAction()
}
