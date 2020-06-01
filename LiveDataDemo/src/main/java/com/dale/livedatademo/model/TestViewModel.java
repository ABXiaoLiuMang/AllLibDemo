package com.dale.livedatademo.model;

import androidx.lifecycle.ViewModel;

import com.dale.livedatademo.ui.App;

public class TestViewModel extends ViewModel {

    public void getTestNetLiveData(){
        App.netLiveData.postValue("我艹你妹");
    }

}
