package com.example.parking.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Map;

/**
 * Created by KomoriWu
 * on 2017-04-21.
 */

public class SharedPreferenceUtils {
    public static final String USER_ACCOUNT_NAME = "user_account_name";
    public static final String USER_ACCOUNT_KEY = "user_account_key";


    public static void saveUserAccount(Context context, String userAccount) {
        SharedPreferenceUtils.saveSharedPreference(context, USER_ACCOUNT_NAME,
                USER_ACCOUNT_KEY, userAccount);
    }

    public static String getUserAccount(Context context) {
        return SharedPreferenceUtils.getSharedPreferenceString(context, USER_ACCOUNT_NAME,
                USER_ACCOUNT_KEY);
    }

    public static void clearUserAccount(Context context) {
        SharedPreferences savedPreference = context.getSharedPreferences(USER_ACCOUNT_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = savedPreference.edit();
        editor.clear();
        editor.commit();
    }

    public static boolean saveSharedPreference(Context context, String name, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(name, Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public static boolean saveSharedPreference(Context context, String name, String key, int value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(name, Context.MODE_PRIVATE).edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    public static boolean saveSharedPreference(Context context, String name, String key, long value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(name, Context.MODE_PRIVATE).edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    public static boolean saveSharedPreference(Context context, String name, String key, boolean value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(name, Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public static String getSharedPreferenceString(Context context, String name, String key) {
        SharedPreferences savedPreference = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return savedPreference.getString(key, null);
    }

    public static String getSharedPreferenceString(Context context, String name, String key, String defaultValue) {
        SharedPreferences savedPreference = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return savedPreference.getString(key, defaultValue);
    }


    public static int getSharedPreferenceInt(Context context, String name, String key) {
        SharedPreferences savedPreference = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return savedPreference.getInt(key, -1);
    }

    public static int getSharedPreferenceInt(Context context, String name, String key, int defaultValue) {
        SharedPreferences savedPreference = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return savedPreference.getInt(key, defaultValue);
    }

    public static long getSharedPreferenceLong(Context context, String name, String key) {
        SharedPreferences savedPreference = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        try {
            return savedPreference.getLong(key, -1);
        } catch (ClassCastException e) {
            return -1;
        }
    }

    public static long getSharedPreferenceLong(Context context, String name, String key, long defaultValue) {
        SharedPreferences savedPreference = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        try {
            return savedPreference.getLong(key, defaultValue);
        } catch (ClassCastException e) {
            return defaultValue;
        }
    }

    public static boolean getSharedPreferenceBoolean(Context context, String name, String key) {
        SharedPreferences savedPreference = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return savedPreference.getBoolean(key, false);
    }

    public static boolean getSharedPreferenceBoolean(Context context, String name, String key, boolean defaultValue) {
        SharedPreferences savedPreference = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return savedPreference.getBoolean(key, defaultValue);
    }

    public static boolean removeSharedPreference(Context context, String name, String key) {
        SharedPreferences.Editor editor = context.getSharedPreferences(name, Context.MODE_PRIVATE).edit();
        editor.remove(key);
        return editor.commit();
    }

    public static boolean existSharedPreferenceKey(Context context, String name, String key) {
        SharedPreferences savedPreference = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return savedPreference.contains(key);
    }

    public static int getSharedPreferenceSize(Context context, String name) {
        SharedPreferences savedPreference = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return savedPreference.getAll().size();
    }

    public static Map<String, ?> getSharedPreferenceAll(Context context, String name) {
        SharedPreferences savedPreference = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return savedPreference.getAll();
    }

    public static void setSharedPreferencesFlag(Context context, String title, boolean content) {
        SharedPreferences pre = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pre.edit();
        editor.putBoolean(title, content);
        editor.apply();
    }
}
