package com.dale.location;

/**
 * create by Dale
 * create on 2019/7/26
 * description:
 */
public class LocationSdk {

    private static class LocationSdkHolder {
        private static ILocationConfig isdkConfig = new LocationConfig();
    }

    public static ILocationConfig ins() {
        return LocationSdkHolder.isdkConfig;
    }
}
