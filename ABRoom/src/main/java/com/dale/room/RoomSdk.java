package com.dale.room;

public class RoomSdk {

    private static class RoomHolder {
        private static IDBConfig isdkConfig = new DBConfig();
    }

    public static IDBConfig ins() {
        return RoomHolder.isdkConfig;
    }
}
