package com.mis.route.e_commerce.ui.activities.home.fragments.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mis.route.e_commerce.data.models.category.Category
import com.mis.route.e_commerce.domain.usecases.categories.GetAllCategoriesUseCase
import com.mis.route.e_commerce.domain.usecases.products.GetProductsUseCase
import com.mis.route.e_commerce.ui.base.BaseViewModel
import com.mis.route.e_commerce.ui.model.ViewMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val getProductsUseCase: GetProductsUseCase
) : BaseViewModel() {

    val state = MutableLiveData<HomeFragmentState>()

    fun invoke(action: HomeFragmentAction) {
        when (action) {
            HomeFragmentAction.GetAllCategories -> getAllCategories()
            is HomeFragmentAction.GetProducts -> getProducts(action.categoryId)
            is HomeFragmentAction.CategoryClicked -> categoryClick(action.category, action.position)
        }
    }

    private fun categoryClick(category: Category, position: Int) {
        state.value = HomeFragmentState.CategoryClicked(category, position)
    }

    private fun getAllCategories() {
        viewModelScope.launch {
            try {
                state.value = HomeFragmentState.CategoriesLoaded(
                    getAllCategoriesUseCase.execute()
                )
            } catch (e: Exception) {
                viewMessageLiveData.value = ViewMessage(message = e.message)
            }
        }
    }

    private fun getProducts(categoryId: String) {
        viewModelScope.launch {
            try {
                state.value = HomeFragmentState.ProductsLoaded(
                    getProductsUseCase.execute(categoryId)
                )
            } catch (e: Exception) {
                viewMessageLiveData.value = ViewMessage(message = e.message)
            }
        }
    }
}