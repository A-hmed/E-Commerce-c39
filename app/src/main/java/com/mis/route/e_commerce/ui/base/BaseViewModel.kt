package com.mis.route.e_commerce.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mis.route.e_commerce.ui.model.ViewMessage

open class BaseViewModel: ViewModel() {
    open var isLoadingLiveData = MutableLiveData<Boolean>()
    open var viewMessageLiveData = MutableLiveData<ViewMessage>()

}
