package com.mis.route.e_commerce.ui.base

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mis.route.e_commerce.R


abstract class BaseFragment<DB : ViewDataBinding> : Fragment() {
    lateinit var binding: DB
    var dialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding.lifecycleOwner = this
        observeLiveData();
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    open fun observeLiveData() {
        Log.e("BaseFragment", "observeLiveData is called")

    }
    abstract fun getLayoutId(): Int

    fun showLoading() {
        val builder = AlertDialog.Builder(activity)
        builder.setView(R.layout.dialog_loading)
        dialog = builder.create();
        dialog?.show()

    }

    fun hideLoading() {
        dialog?.dismiss()
    }

    fun showDialog(
        title: String? = null,
        message: String? = null,
        posButtonTitle: String? = null,
        negButtonTitle: String? = null,
        onPosButtonClick: (() -> Unit)? = null,
        onNegButtonClick: (() -> Unit)? = null,
    ) {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(title)
        builder.setMessage(message)
        posButtonTitle.let {
            builder.setPositiveButton(
                posButtonTitle
            ) { dialog, p1 ->
                dialog.dismiss();
                onPosButtonClick?.invoke()
            };
        }
        negButtonTitle.let {
            builder.setPositiveButton(
                negButtonTitle
            ) { dialog, p1 ->
                dialog.dismiss();
                onNegButtonClick?.invoke()
            };
        }
        builder.create().show();
    }
}