package com.dale.constant;

import android.app.Application;

import androidx.core.content.FileProvider;

import com.dale.utils.AppException;
import com.dale.utils.LogUtils;
import com.dale.utils.MMKVUtil;
import com.dale.utils.SPUtils;
import com.dale.utils.TopActivityManager;
import com.tencent.mmkv.MMKV;


public final class LibApplication {


    private static Application sApplication;

    private LibApplication() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void init(final Application app) {
        sApplication = app;
        Thread.setDefaultUncaughtExceptionHandler(AppException.getAppExceptionHandler(sApplication));
        TopActivityManager.getInstance().init(sApplication);
        SPUtils.getInstance(app);
        MMKVUtil.init(app);
    }


    public static Application getApp() {
        return sApplication;
    }



    public static final class FileProvider4UtilCode extends FileProvider {
        @Override
        public boolean onCreate() {
            return true;
        }
    }


}
