package com.mis.route.e_commerce.ui.utils

import android.graphics.Rect
import android.view.View
import android.widget.ScrollView

object UIConstants {

    const val WOMEN_FASHION_CATEGORY_ID = "6439d58a0049ad0b52b9003f"

    const val PASSED_CATEGORIES_LIST = "PassedCategoriesList"
    const val PASSED_CATEGORY = "PassedCategory"
    const val PASSED_POSITION = "PassedPosition"

    /**
     * Performs an action by invoking [action] when the [view] gets visible
     * due to scrolling change. The [view] should be a child of the [ScrollView]
     * on which this function is called.
     *
     * @param view the view that will be checked for visibility
     * @param action a function to be invoked when the check is true
     */
    fun ScrollView.whenViewIsShown(view: View, action: () -> Unit) {
        this.viewTreeObserver.addOnScrollChangedListener {
            val scrollViewRect = Rect()
            this.getLocalVisibleRect(scrollViewRect)

            val childRect = Rect()
            view.getLocalVisibleRect(childRect)

            if (childRect.intersect(scrollViewRect)) action.invoke()
        }
    }
}