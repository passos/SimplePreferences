package com.ioenv.preferences;

import java.lang.reflect.Type;
import java.util.Map;

import android.content.Context;

/**
 *
 *
 * @author liujinyu <simon.jinyu.liu@gmail.com>
 */
public class Preferences {

    private static PreferenceStorage preferenceStorage;

    public static void init(Context context, Class<? extends PreferenceStorage> preferenceStoreClass, String name) {
        try {
            Preferences.preferenceStorage = preferenceStoreClass.newInstance();
        } catch (IllegalAccessException e1) {
            throw new RuntimeException("PreferenceStorage initial error");
        } catch (InstantiationException e2) {
            throw new RuntimeException("PreferenceStorage initial error");
        }
        preferenceStorage.init(context, name);
    }

    private static void assertInitialized() {
        if (preferenceStorage == null) {
            throw new RuntimeException("call Preferences.init() first before use it");
        }
    }

    public static void registerOnChangeListener(OnPreferenceChangeListener listener) {
        assertInitialized();
        preferenceStorage.registerOnPreferenceChangedListener(listener);
    }

    public static void unregisterOnChangeListener(OnPreferenceChangeListener listener) {
        assertInitialized();
        preferenceStorage.unregisterOnPreferenceChangedListener(listener);
    }

    public static void set(String key, String value) {
        assertInitialized();
        preferenceStorage.set(key, value);
    }

    public static void set(String key, boolean value) {
        assertInitialized();
        preferenceStorage.set(key, value);
    }

    public static void set(String key, int value) {
        assertInitialized();
        preferenceStorage.set(key, value);
    }

    public static void set(String key, long value) {
        assertInitialized();
        preferenceStorage.set(key, value);
    }

    public static void set(String key, float value) {
        assertInitialized();
        preferenceStorage.set(key, value);
    }

    public static void set(String key, Object value) {
        assertInitialized();
        preferenceStorage.set(key, value);
    }

    public static boolean has(String key) {
        assertInitialized();
        return preferenceStorage.has(key);
    }

    public static Map<String, ?> getAll() {
        assertInitialized();
        return preferenceStorage.getAll();
    }

    public static String get(String key) {
        assertInitialized();
        return preferenceStorage.get(key);
    }

    public static String get(String key, String defValue) {
        assertInitialized();
        return preferenceStorage.get(key, defValue);
    }

    public static boolean get(String key, boolean defValue) {
        assertInitialized();
        return preferenceStorage.get(key, defValue);
    }

    public static int get(String key, int defValue) {
        assertInitialized();
        return preferenceStorage.get(key, defValue);
    }

    public static long get(String key, long defValue) {
        assertInitialized();
        return preferenceStorage.get(key, defValue);
    }

    public static float get(String key, float defValue) {
        assertInitialized();
        return preferenceStorage.get(key, defValue);
    }

    public static <T> T get(String key, Class<T> clz, T defValue) {
        assertInitialized();
        return preferenceStorage.get(key, clz, defValue);
    }

    public static <T> T get(String key, Type typeOfT, T defValue) {
        assertInitialized();
        return preferenceStorage.get(key, typeOfT, defValue);
    }
}
