package com.konkuk.nongboohae.presentation.base

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.konkuk.nongboohae.BuildConfig

class GlobalApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "3284a9b05a9b22089d8f51535c03fd05")
    }
}