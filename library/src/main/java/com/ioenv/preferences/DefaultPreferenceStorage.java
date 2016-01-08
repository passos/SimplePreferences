package com.ioenv.preferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

/**
 * @author liujinyu <simon.jinyu.liu@gmail.com>
 */
public class DefaultPreferenceStorage extends AbstractPreferenceStorage {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener;
    private List<OnPreferenceChangeListener> onPreferenceChangeListenerList;

    @Override
    public void init(Context context, String name) {
        if (context == null) {
            throw new IllegalArgumentException("Illegal context");
        }

        if (TextUtils.isEmpty(name)) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        } else {
            sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        }
    }

    @Override
    public boolean has(String key) {
        return sharedPreferences.contains(key);
    }

    @Override
    public Map<String, ?> getAll() {
        return sharedPreferences.getAll();
    }

    @Override
    public String get(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }

    @Override
    public void set(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    @Override
    public void registerOnPreferenceChangedListener(OnPreferenceChangeListener listener) {
        if (onPreferenceChangeListenerList == null) {
            onPreferenceChangeListenerList = new ArrayList<OnPreferenceChangeListener>();
            onSharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                    if (onPreferenceChangeListenerList != null) {
                        for (OnPreferenceChangeListener preferenceChangeListener : onPreferenceChangeListenerList) {
                            preferenceChangeListener.OnPreferenceChange(DefaultPreferenceStorage.this, key);
                        }
                    }
                }
            };
            sharedPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
        }

        onPreferenceChangeListenerList.add(listener);
    }

    @Override
    public void unregisterOnPreferenceChangedListener(OnPreferenceChangeListener listener) {
        if (onPreferenceChangeListenerList != null) {
            onPreferenceChangeListenerList.remove(listener);
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
        }
    }
}
