package com.ioenv.preferences;

import org.junit.After;
import org.junit.Before;

import android.test.AndroidTestCase;
import android.util.Log;

/**
 * @author liujinyu <simon.jinyu.liu@gmail.com>
 */
public class EncodedPreferenceTest extends AndroidTestCase {
    private static final String TAG = EncodedPreferenceTest.class.getSimpleName();

    @Before
    public void setUp() throws Exception {
        Preferences.init(new EncodedPreferenceStorage(getContext()));
        Preferences.clear();
    }

    @After
    public void tearDown() throws Exception {
        Log.i(TAG, "Preference dump: " + Preferences.dump());
        Preferences.clear();
    }
}
