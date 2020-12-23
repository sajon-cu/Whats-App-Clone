package com.example.whatsappclone.framework

import androidx.appcompat.app.AppCompatActivity
import com.kaopiz.kprogresshud.KProgressHUD

abstract class BaseActivity : AppCompatActivity() {
    private var progress: KProgressHUD? = null

    protected fun showProgress(label: String? = null) {
        if (progress == null) {
            progress = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
        }
        progress?.setLabel(label)
        progress?.show()
    }

    protected fun hideProgress() {
        progress?.run {
            if (isShowing) dismiss()
        }
    }

    protected fun progress(show: Boolean, label: String? = null) {
        if (show) showProgress(label)
        else hideProgress()
    }
}