package com.dale.framework_demo.ui;


import com.dale.framework.ui.IBaseView;

import java.util.List;

public interface MainContract {

    interface IPresenter {
        void initRequest();
        void onLoadMore(int page);
        void onRefresh();
    }

    interface IView extends IBaseView {
        void initSuccess(List<String> list);
        void onLoadMoreSuccess(List<String> list);
        void ononRefreshSuccess(List<String> list);
    }

}
