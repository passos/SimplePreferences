package com.ioenv.preferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;
import android.util.Log;

import com.google.gson.reflect.TypeToken;

/**
 * @author liujinyu <simon.jinyu.liu@gmail.com>
 */
public class PreferencesTest extends AndroidTestCase {
    private static final String TAG = PreferencesTest.class.getSimpleName();

    @Before
    public void setUp() throws Exception {
        Preferences.init(getContext());
        Preferences.clear();
    }

    @After
    public void tearDown() throws Exception {
        Log.i(TAG, "Preference dump: " + Preferences.dump());
        Preferences.clear();
    }

    @Test
    public void testSetString() throws Exception {
        Preferences.set("str_config_1", "Test");

        assertEquals("Test", Preferences.get("str_config_1"));
        assertEquals("Test", Preferences.get("str_config_2", "Test"));
        assertEquals("", Preferences.get("str_config_2"));
    }

    @Test
    public void testSetInteger() throws Exception {
        Preferences.set("int_config_1", 1);

        assertEquals(1, Preferences.get("int_config_1", 0));
        assertEquals(2, Preferences.get("int_config_2", 2));
    }

    @Test
    public void testSetLong() throws Exception {
        Preferences.set("long_config_1", 1234567890L);

        assertEquals(1234567890L, Preferences.get("long_config_1", 0L));
        assertEquals(1234567890L, Preferences.get("long_config_2", 1234567890L));
    }

    @Test
    public void testSetFloat() throws Exception {
        Preferences.set("float_config_1", 1234567.89f);

        assertEquals(1234567.89f, Preferences.get("float_config_1", 0.0f));
        assertEquals(1234567.89f, Preferences.get("float_config_2", 1234567.89f));
    }

    @Test
    public void testBoolean() throws Exception {
        Preferences.set("boolean_config_1", true);

        assertEquals(true, Preferences.get("boolean_config_1", true));
        assertEquals(false, Preferences.get("boolean_config_2", false));
    }

    @Test
    public void testSetList() throws Exception {
        List<DataObject> list = new ArrayList<DataObject>() {
            {
                add(new DataObject(1, true));
                add(new DataObject(2, false));
                add(new DataObject(3, true));
            }
        };
        Preferences.set("log_list", Preferences.toJson(new ArrayList<>(list)));
        assertTrue(Preferences.has("log_list"));

        List<DataObject> result = Preferences.getList("log_list", new TypeToken<ArrayList<DataObject>>() {}.getType());
        assertEquals(list.size(), result.size());

        for (int i = 0; i < list.size(); i++) {
            assertEquals(list.get(i).time, result.get(i).time);
        }
    }

    @Test
    public void testSetStrings() throws Exception {
        List<String> list = new ArrayList<String>() {
            {
                add("String 1");
                add("String 2");
                add("String 3");
            }
        };
        Preferences.setStrings("str_list", list);
        assertTrue(Preferences.has("str_list"));

        List<String> result = Preferences.getStrings("str_list");
        assertEquals(list.size(), result.size());

        for (int i = 0; i < list.size(); i++) {
            assertEquals(list.get(i), result.get(i));
        }
    }

    @Test
    public void testRemoveAndHas() throws Exception {
        Preferences.set("some_config_1", "Test");
        assertTrue(Preferences.has("some_config_1"));

        Preferences.remove("some_config_1");
        assertTrue(!Preferences.has("some_config_1"));
    }

    @Test
    public void testClear() throws Exception {
        Preferences.clear();
        Map<String, String> map = Preferences.getAll();
        assertTrue(map.isEmpty());
    }

    public static class DataObject {
        public long time;
        public boolean status;

        public DataObject(long time, boolean status) {
            this.time = time;
            this.status = status;
        }
    }
}
