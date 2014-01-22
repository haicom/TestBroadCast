package com.example.testbroadcast;

import android.content.Context;
import android.content.SharedPreferences;

public class EdpPreferenceUtil {
    private static final String CONFIG_NAME = "edp_config";

    public static final String DEFAULT_VALUE = "default";

    public static final String DEFAULT_WIFI_SSID = "default_wifi_ssid";

    // device_admin active state
    public static final String ADMIN_STATE_KEY = "admin";
    public static final String ADMIN_LIVE = "live";
    public static final String ADMIN_DEAD = "dead";

    //wifi state
    public static final String WIFI_KEY = "wifi";
    public static final String WIFI_OPEN = "wifi_open";
    public static final String WIFI_CLOSE = "wifi_close";
    public final static String WIFI_NEED_CONNECT = "wifi_need_connect";
    //bluetooth
    public static final String BLUETOOTH_KEY = "bluetooth";
    public static final String BLUETOOTH_OPEN = "bluetooth_open";
    public static final String BLUETOOTH_CLOSE = "bluetooth_close";
    //network
    public static final String NETWORK_KEY = "network";
    public static final String NETWORK_OPEN = "network_open";
    public static final String NETWORK_CLOSE = "network_close";

    // blue tooth
    public static final String BLUETOOTH_STATE_WANTED = "BLUETOOTH_STATE_WANTED";

    // wifi state
    public static final String WIFI_STATE_WANTED = "WIFI_STATE_WANTED";

    public static void setStringValue(Context context, String key,  String value) {
        SharedPreferences sp = context.getSharedPreferences(CONFIG_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value).commit();
    }

    public static String getStringValue(Context context, String key, String defValue) {
        SharedPreferences sp = context.getSharedPreferences(CONFIG_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public static void setBooleanValue(Context context, String key,  boolean value) {
        SharedPreferences sp = context.getSharedPreferences(CONFIG_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value).commit();
    }

    public static boolean getBooleanValue(Context context, String key, boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences(CONFIG_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    public static void setIntValue(Context context, String key,  int value) {
        SharedPreferences sp = context.getSharedPreferences(CONFIG_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value).commit();
    }

    public static int getIntValue(Context context, String key, int defValue) {
        SharedPreferences sp = context.getSharedPreferences(CONFIG_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }
}
