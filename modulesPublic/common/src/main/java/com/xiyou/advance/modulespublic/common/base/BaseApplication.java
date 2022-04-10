package com.xiyou.advance.modulespublic.common.base;

import android.app.Application;
import android.text.TextUtils;

import com.xiyou.advance.modulesbase.libbase.cache.Preferences;
import com.xiyou.advance.modulesbase.libbase.util.PropertiesUtil;
import com.xiyou.advance.modulespublic.common.constant.KeyPool;
import com.xiyou.advance.modulespublic.common.utils.DisplayUtil;
import com.xiyou.advance.modulespublic.common.utils.ToastUtil;

import java.util.UUID;

import dagger.hilt.android.HiltAndroidApp;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        var isRelease = PropertiesUtil.props.getProperty("isRelease");
        if(!TextUtils.equals(isRelease, "true"))
        {
        }
        DisplayUtil.Companion.init(this);
        ToastUtil.Companion.init(this);
        if(TextUtils.isEmpty(Preferences.getString(KeyPool.ID, "")))
        {
            var uuid = UUID.randomUUID().toString();
            Preferences.saveString(KeyPool.ID, uuid);
        }
    }
}
