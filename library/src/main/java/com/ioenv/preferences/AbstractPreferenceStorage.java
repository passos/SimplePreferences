package com.ioenv.preferences;

import java.lang.reflect.Type;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * @author liujinyu <simon.jinyu.liu@gmail.com>
 */
public abstract class AbstractPreferenceStorage implements PreferenceStorage {

    @Override
    public void init(Context context) {
        init(context, null);
    }

    @Override
    public void set(String key, boolean value) {
        set(key, Boolean.toString(value));
    }

    @Override
    public void set(String key, int value) {
        set(key, Integer.toString(value));
    }

    @Override
    public void set(String key, long value) {
        set(key, Long.toString(value));
    }

    @Override
    public void set(String key, float value) {
        set(key, Float.toString(value));
    }

    @Override
    public void set(String key, Object value) {
        set(key, new Gson().toJson(value));
    }

    @Override
    public String get(String key) {
        return get(key, "");
    }

    @Override
    public boolean get(String key, boolean defValue) {
        String value = get(key);
        return has(key) ? "true".equalsIgnoreCase(value) : defValue;
    }

    @Override
    public int get(String key, int defValue) {
        try {
            return Integer.parseInt(get(key));
        } catch (NumberFormatException e) {
            return defValue;
        }
    }

    @Override
    public long get(String key, long defValue) {
        try {
            return Long.parseLong(get(key));
        } catch (NumberFormatException e) {
            return defValue;
        }
    }

    @Override
    public float get(String key, float defValue) {
        try {
            return Float.parseFloat(get(key));
        } catch (NumberFormatException e) {
            return defValue;
        }
    }

    @Override
    public <T> T get(String key, Class<T> clz, T defValue) {
        try {
            return new Gson().fromJson(get(key), clz);
        } catch (JsonSyntaxException e) {
            return defValue;
        }
    }

    @Override
    public <T> T get(String key, Type typeOfT, T defValue) {
        try {
            return new Gson().fromJson(get(key), typeOfT);
        } catch (JsonSyntaxException e) {
            return defValue;
        }
    }

    @Override
    public void registerOnPreferenceChangedListener(OnPreferenceChangeListener listener) {
        throw new RuntimeException("not supported");
    }

    @Override
    public void unregisterOnPreferenceChangedListener(OnPreferenceChangeListener listener) {
        throw new RuntimeException("not supported");
    }
}
