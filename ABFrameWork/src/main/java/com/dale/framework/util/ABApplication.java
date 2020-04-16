package com.dale.framework.util;

import android.app.Application;

import com.dale.constant.LibApplication;
import com.dale.framework.R;
import com.dale.utils.FileUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

public class ABApplication extends Application {

	private static ABApplication instance;

	static {
		SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
			layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
			return new ClassicsHeader(context);
		});
		SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> new ClassicsFooter(context).setDrawableSize(20));
	}

	public static ABApplication getInstance() {
		return instance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
	}

}
