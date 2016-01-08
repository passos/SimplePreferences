package com.ioenv.preferences;

/**
 * Interface definition for a callback to be invoked when a
 * preference is changed.
 * 
 * @author liujinyu <simon.jinyu.liu@gmail.com>
 */
public interface OnPreferenceChangeListener {
    /**
     * Called when a preference is changed, added, or removed. This
     * may be called even if a preference is set to its existing value.
     *
     * <p>
     * This callback will be run on your main thread.
     *
     * @param preferences The {@link Preferences} that received the change.
     * @param key The key of the preference that was changed, added, or
     *            removed.
     */
    void OnPreferenceChange(PreferenceStorage preferenceStorage, String key);
}
