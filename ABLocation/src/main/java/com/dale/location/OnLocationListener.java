package com.dale.location;


import com.baidu.location.BDLocation;

public interface OnLocationListener {
    /**
     * 收到定位回调
     * @param bdLocation
     */
    void onLocation(BDLocation bdLocation);


}
