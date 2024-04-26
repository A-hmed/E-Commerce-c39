package com.mis.route.e_commerce.ui.activities.home.fragments.categories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mis.route.e_commerce.domain.usecases.categories.GetAllCategoriesUseCase
import com.mis.route.e_commerce.ui.activities.home.fragments.home.HomeFragmentState
import com.mis.route.e_commerce.ui.base.BaseViewModel
import com.mis.route.e_commerce.ui.model.ViewMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
) : BaseViewModel() {
    val state = MutableLiveData<CategoriesFragmentState>()

    fun invoke(action: CategoriesFragmentAction) {
        when (action) {
            CategoriesFragmentAction.LoadAllCategories -> getAllCategories()
        }
    }

    private fun getAllCategories() {
        viewModelScope.launch {
            try {
                state.value = CategoriesFragmentState.CategoriesLoaded(
                    getAllCategoriesUseCase.execute()
                )
            } catch (e: Exception) {
                viewMessageLiveData.value = ViewMessage(message = e.message)
            }
        }
    }
}