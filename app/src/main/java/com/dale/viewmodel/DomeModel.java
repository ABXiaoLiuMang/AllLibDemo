package com.dale.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dale.log.LogUtils;

public class DomeModel extends ViewModel {

    //创建LiveData(可以创建多个不同类型的 LiveData..)
    private MutableLiveData<String> mDomeLiveData;


    public DomeModel(){
        LogUtils.d("DomeModel 构造函数");
    }

    public void setDomeInfo(int count){
        mDomeLiveData.setValue("phone_str:" + count);
    }

    /**
     *
     * @return
     */
    public MutableLiveData<String> getmDomeLiveData(){
        if(mDomeLiveData == null){
            mDomeLiveData = new MutableLiveData<>();
        }
        return mDomeLiveData;
    }
    // 当MyActivity被销毁时，Framework会调用ViewModel的onCleared()
    @Override
    protected void onCleared() {
        super.onCleared();
    }

}
