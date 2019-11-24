package com.dale.location;

import android.app.Application;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.dale.constant.PermissionConstants;
import com.dale.utils.PermissionUtils;
import com.dale.utils.StringUtils;
import com.dale.utils.ToastUtils;

/**
 * create by Dale
 * create on 2019/7/26
 * description: 定位设置
 */
public class LocationConfig extends BDAbstractLocationListener implements ILocationConfig {

    BDLocation location;
    Application app;
    OnLocationListener onLocationListener;
    boolean allow = false;
    LocationClient mLocClient;

    @Override
    public void initSDK(Application app) {
        this.app = app;
    }

    @Override
    public ILocationConfig setOnLocationListener(OnLocationListener onLocationListener) {
        this.onLocationListener = onLocationListener;
        return this;
    }

    @Override
    public ILocationConfig setAllowFirst(boolean allow) {
        this.allow = allow;
        return this;
    }

    @Override
    public void startLocation() {
        PermissionUtils.permission(PermissionConstants.getPermissions(PermissionConstants.LOCATION)).callback(new PermissionUtils.SimpleCallback() {
            @Override
            public void onGranted() {
                mLocClient = new LocationClient(app);
                mLocClient.registerLocationListener(LocationConfig.this);
                LocationClientOption locationOption = new LocationClientOption();
                locationOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
                locationOption.setCoorType("bd09ll");
                locationOption.setScanSpan(1000);
                locationOption.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
                locationOption.setIsNeedLocationDescribe(true);//可选，设置是否需要地址描述
                locationOption.setNeedDeviceDirect(false);//可选，设置是否需要设备方向结果
                locationOption.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
                locationOption.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
                locationOption.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
                locationOption.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
                locationOption.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
                locationOption.setOpenGps(true);//可选，默认false，设置是否开启Gps定位
                locationOption.setIsNeedAltitude(false);//可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
                locationOption.setOpenAutoNotifyMode();//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者，该模式下开发者无需再关心定位间隔是多少，定位SDK本身发现位置变化就会及时回调给开发者
                locationOption.setOpenAutoNotifyMode(3000, 1, LocationClientOption.LOC_SENSITIVITY_HIGHT);//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者
                mLocClient.setLocOption(locationOption);
                mLocClient.start();
            }

            @Override
            public void onDenied() {
                ToastUtils.showLong("权限不足");
            }
        }).request();
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        if(bdLocation != null && !StringUtils.isEmpty(bdLocation.getAddrStr())){
            if(allow){
                mLocClient.stop();
            }
            this.location = bdLocation;
            if(onLocationListener != null){
                onLocationListener.onLocation(location);
            }
        }
    }
}
