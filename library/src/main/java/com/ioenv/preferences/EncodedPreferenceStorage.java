package com.ioenv.preferences;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;

/**
 * @author liujinyu <simon.jinyu.liu@gmail.com>
 */
public class EncodedPreferenceStorage extends Preferences.DefaultPreferenceStorage {

    public EncodedPreferenceStorage(Context context) {
        this(context, "");
    }

    public EncodedPreferenceStorage(Context context, String name) {
        super(context, name);
    }

    @Override
    public boolean has(String key) {
        return super.has(encode(key));
    }

    @Override
    public void set(String key, String value) {
        super.set(encode(key), encode(value));
    }

    @Override
    public String get(String key, String defValue) {
        String result = get(encode(key), null);
        return result == null ? defValue : decode(result);
    }

    @Override
    public Map<String, ?> getAll() {
        Map<String, ?> map = super.getAll();
        Map<String, String> result = new HashMap<String, String>(map.size());
        for (String key : map.keySet()) {
            result.put(key, decode(String.valueOf(map.get(key))));
        }

        return result;
    }

    @Override
    public void remove(String key) {
        super.remove(encode(key));
    }

    private String encode(String plainText) {
        if (TextUtils.isEmpty(plainText)) {
            return "";
        }

        return Base64.encodeToString(plainText.getBytes(), Base64.DEFAULT);
    }

    private String decode(String cipherText) {
        if (TextUtils.isEmpty(cipherText)) {
            return "";
        }

        try {
            return new String(Base64.decode(cipherText, Base64.DEFAULT), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
