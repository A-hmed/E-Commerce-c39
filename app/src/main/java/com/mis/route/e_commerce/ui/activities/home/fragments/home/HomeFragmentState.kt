package com.mis.route.e_commerce.ui.activities.home.fragments.home

import com.mis.route.domain.models.product.Product
import com.mis.route.e_commerce.data.models.category.Category

sealed class HomeFragmentState {
    class CategoriesLoaded(val categoriesList: List<Category?>) : HomeFragmentState()
    class ProductsLoaded(val productsList: List<Product?>) : HomeFragmentState()
    class CategoryClicked(val category: Category, val position: Int) : HomeFragmentState()
}
