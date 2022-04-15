package com.xiyou.advance.modulespublic.common.base

import android.app.Application
import android.text.TextUtils
import com.xiyou.advance.modulesbase.libbase.cache.Preferences
import dagger.hilt.android.HiltAndroidApp
import com.xiyou.advance.modulesbase.libbase.util.PropertiesUtil
import com.xiyou.advance.modulespublic.common.utils.DisplayUtil
import com.xiyou.advance.modulespublic.common.utils.ToastUtil
import com.xiyou.advance.modulespublic.common.constant.KeyPool
import java.util.*

@HiltAndroidApp
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val isRelease = PropertiesUtil.props.getProperty("isRelease")
        if (!TextUtils.equals(isRelease, "true")) {
        }
        DisplayUtil.init(this)
        ToastUtil.init(this)
        if (TextUtils.isEmpty(Preferences.getString(KeyPool.ID, ""))) {
            val uuid = UUID.randomUUID().toString()
            Preferences.saveString(KeyPool.ID, uuid)
        }
    }
}