package com.dale.framework_demo.ui;


import com.dale.framework_demo.Person;
import com.dale.framework.ui.IBaseView;

import java.util.List;

public interface MainContract {

    interface IPresenter {
        void initRequest();
        void onLoadMore(int page);
        void onRefresh();
    }

    interface IView extends IBaseView {
        void initSuccess(List<Person> list);
        void onLoadMoreSuccess(List<Person> list);
        void ononRefreshSuccess(List<Person> list);
    }

}
