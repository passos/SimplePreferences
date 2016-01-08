package com.ioenv.preferences;

import java.lang.reflect.Type;
import java.util.Map;

import android.content.Context;

/**
 * @author liujinyu <simon.jinyu.liu@gmail.com>
 */
public interface PreferenceStorage {

    void init(Context context);

    void init(Context context, String name);

    boolean has(String key);

    String get(String key);

    String get(String key, String defValue);

    boolean get(String key, boolean defValue);

    int get(String key, int defValue);

    long get(String key, long defValue);

    float get(String key, float defValue);

    <T> T get(String key, Class<T> clz, T defValue);

    <T> T get(String key, Type typeOfT, T defValue);

    Map<String, ?> getAll();

    void set(String key, String value);

    void set(String key, boolean value);

    void set(String key, int value);

    void set(String key, long value);

    void set(String key, float value);

    void set(String key, Object value);

    void registerOnPreferenceChangedListener(OnPreferenceChangeListener listener);

    void unregisterOnPreferenceChangedListener(OnPreferenceChangeListener listener);
}
