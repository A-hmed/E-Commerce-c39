package com.mis.route.e_commerce.ui.activities.home.fragments.categories

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mis.route.e_commerce.R
import com.mis.route.e_commerce.data.models.category.Category
import com.mis.route.e_commerce.databinding.FragmentCategoriesBinding
import com.mis.route.e_commerce.ui.base.BaseFragment
import com.mis.route.e_commerce.ui.utils.UIConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : BaseFragment<FragmentCategoriesBinding>() {

    private val viewModel: CategoriesViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderUiState()
        getPreLoadedCategories()
    }

    private fun getPreLoadedCategories() {
        if (arguments == null) viewModel.invoke(CategoriesFragmentAction.LoadAllCategories)
        val categoriesList = getArrayList()
        bindCategories(categoriesList ?: emptyList())
    }

    @Suppress("DEPRECATION")
    private fun getArrayList(): ArrayList<Category?>? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getParcelableArrayList(
                UIConstants.PASSED_CATEGORIES_LIST,
                Category::class.java
            )
        } else {
            requireArguments().getParcelableArrayList(UIConstants.PASSED_CATEGORIES_LIST)
        }
    }

    private fun renderUiState() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is CategoriesFragmentState.CategoriesLoaded -> bindCategories(state.categoriesList)
            }
        }
    }

    private fun bindCategories(categoriesList: List<Category?>) {
        TODO("Not yet implemented")
        binding.categoriesShimmerViewContainer.stopShimmer()
        binding.categoriesShimmerViewContainer.isVisible = false
    }

    override fun getLayoutId(): Int = R.layout.fragment_categories
}