package com.mis.route.e_commerce.ui.activities.home.fragments.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.mis.route.e_commerce.data.models.category.Category
import com.mis.route.domain.models.offer.Offer
import com.mis.route.domain.models.product.Product
import com.mis.route.domain.models.subcategory.SubCategory
import com.mis.route.e_commerce.R
import com.mis.route.e_commerce.databinding.FragmentHomeBinding
import com.mis.route.e_commerce.ui.utils.UIConstants.whenViewIsShown
import com.mis.route.e_commerce.ui.activities.home.fragments.home.adapter.CategoriesRecyclerAdapter
import com.mis.route.e_commerce.ui.activities.home.fragments.home.adapter.OfferViewPagerAdapter
import com.mis.route.e_commerce.ui.activities.home.fragments.home.adapter.ProductsRecyclerAdapter
import com.mis.route.e_commerce.ui.base.BaseFragment
import com.mis.route.e_commerce.ui.utils.UIConstants
import dagger.hilt.android.AndroidEntryPoint


@SuppressLint("NotifyDataSetChanged")
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()

    // stop injection for now
    private var categoriesAdapter = CategoriesRecyclerAdapter(null)
    private var productsAdapter = ProductsRecyclerAdapter(null)
    private var isProductsAlreadyVisible = false
    private var offersAdapter = OfferViewPagerAdapter(null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderUi()
        initOffersViewPager()
        initCategoriesRecyclerView()
        initProductsRecyclerView()
    }

    private fun renderUi() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is HomeFragmentState.CategoriesLoaded -> bindCategories(state.categoriesList)
                is HomeFragmentState.ProductsLoaded -> bindProducts(state.productsList)
                is HomeFragmentState.CategoryClicked -> navigateToCategoriesScreen(state.category, state.position)
            }
        }
    }

    private fun navigateToCategoriesScreen(category: Category, position: Int) {
        val preLoadedData = Bundle().apply {
            val arrayList = ArrayList(categoriesAdapter.categoriesList!!)
            putParcelableArrayList(UIConstants.PASSED_CATEGORIES_LIST, arrayList)
            putParcelable(UIConstants.PASSED_CATEGORY, category)
            putInt(UIConstants.PASSED_POSITION, position)
        }
        findNavController().navigate(R.id.action_homeFragment_to_categoriesFragment, preLoadedData)
    }

    override fun getLayoutId(): Int = R.layout.fragment_home


    private fun bindOffers(offersList: List<Offer>?) {
        if (offersList.isNullOrEmpty()) return
        offersAdapter.offersList = offersList
        offersAdapter.notifyDataSetChanged()

    }

    private fun bindCategories(categoriesList: List<Category?>?) {
        if (categoriesList.isNullOrEmpty()) return
        // TODO: loading is delayed for debug purposes only, this MUST be removed from release versions
        Handler(Looper.getMainLooper()).postDelayed({
            binding.categoriesRecyclerViewShimmer.stopShimmer()
            binding.categoriesRecyclerViewShimmer.visibility = View.INVISIBLE
            categoriesAdapter.categoriesList = categoriesList
            categoriesAdapter.notifyDataSetChanged()
        }, 1000)
    }

    private fun bindProducts(productsList: List<Product?>?) {
        if (productsList.isNullOrEmpty()) return
        // TODO: loading is delayed for debug purposes only, this MUST be removed from release versions
        Handler(Looper.getMainLooper()).postDelayed({
            binding.lapsAccessoriesRecyclerViewShimmer.stopShimmer()
            binding.lapsAccessoriesRecyclerViewShimmer.visibility = View.INVISIBLE
            productsAdapter.productsList = productsList
            productsAdapter.notifyDataSetChanged()
        }, 1000)
    }

    private fun initProductsRecyclerView() {
        binding.rootView.whenViewIsShown(
            binding.lapsAccessoriesRecyclerViewShimmer,
            ::startLoadingProducts
        )
        binding.lapsAccessoriesRecyclerView.adapter = productsAdapter
    }

    private fun startLoadingProducts() {
        if (isProductsAlreadyVisible) return

        viewModel.invoke(HomeFragmentAction.GetProducts(UIConstants.WOMEN_FASHION_CATEGORY_ID))
        isProductsAlreadyVisible = true
    }

    private fun initCategoriesRecyclerView() {
        categoriesAdapter.setOnCategoryClickListener { category, position ->
            viewModel.invoke(HomeFragmentAction.CategoryClicked(category, position))
        }
        binding.categoriesRecyclerView.adapter = categoriesAdapter
        viewModel.invoke(HomeFragmentAction.GetAllCategories)
    }

    private fun initOffersViewPager() {
        binding.offersViewPager.adapter = offersAdapter
        TabLayoutMediator(binding.tabLayout, binding.offersViewPager) { _, _ ->
            //Some implementation
        }.attach()

        bindOffers(DummyDataProvider.getOffers())
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.lapsAccessoriesRecyclerViewShimmer.stopShimmer()
        binding.categoriesRecyclerViewShimmer.stopShimmer()
    }
}


object DummyDataProvider {
    fun getOffers(): List<Offer> {
        return listOf(
            Offer(1, 25, R.drawable.image_headset, "1", "For all Headphones \n& AirPods"),
            Offer(2, 30, R.drawable.image_beauty_products, "2", "For all Makeup\n& Skincare"),
            Offer(3, 20, R.drawable.image_laptop, "3", "For Laptops\n& Mobiles")
        )
    }
}
