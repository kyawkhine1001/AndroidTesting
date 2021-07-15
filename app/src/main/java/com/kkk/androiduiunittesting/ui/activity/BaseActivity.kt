package com.kkk.androiduiunittesting.ui.activity

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
import com.kkk.androiduiunittesting.R

abstract class BaseActivity : AppCompatActivity() {

    abstract val layoutId: Int
    private var isShowingProgressBar: Boolean = false
    private var progressDialog: Dialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        addProgressBar(R.style.ThemeLoadingDialog)
    }

    private fun addProgressBar(@StyleRes theme:Int) {
        if (isShowingProgressBar) return
        val dialog = Dialog(this, theme)
        dialog.setContentView(R.layout.loading)
        dialog.setCancelable(false)
        progressDialog = dialog
    }

    fun showLoadingView() {
        if (isShowingProgressBar) return
        progressDialog?.show()
        isShowingProgressBar = true
    }

    fun hideLoadingView() {
        progressDialog?.dismiss()
        isShowingProgressBar = false
    }

    fun showAlert(
        message: String,
        title: String? = null,
        positiveAction: (() -> Unit)? = null,
        @StringRes negative: Int? = null,
        negativeAction: (() -> Unit)? = null,
        @DrawableRes icon: Int? = null,
        cancelable: Boolean = true
    ) {
        val dialog = AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(cancelable)
            .setPositiveButton("OK") { _, _ -> positiveAction?.invoke() }
            .create()

        negative?.let {
            dialog.setButton(Dialog.BUTTON_NEGATIVE, getText(it)) { _, _ ->
                negativeAction?.invoke()
            }
        }
        icon?.let { dialog.setIcon(it) }
        dialog.show()
    }
}