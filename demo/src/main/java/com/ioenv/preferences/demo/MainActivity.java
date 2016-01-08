package com.ioenv.preferences.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.ioenv.preferences.DefaultPreferenceStorage;
import com.ioenv.preferences.Preferences;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.edit_string)
    EditText editString;

    @Bind(R.id.edit_integer)
    EditText editInteger;

    @Bind(R.id.edit_float)
    EditText editFloat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        Preferences.init(getApplicationContext(), DefaultPreferenceStorage.class, "demo");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_load:
                loadSettings();
                break;
            case R.id.action_save:
                saveSettings();
                break;
        }

        return true;
    }

    private void saveSettings() {
        Preferences.set("StringConfig", editString.getText().toString());
        Preferences.set("IntegerConfig", editInteger.getText().toString());
        Preferences.set("FloatConfig", editFloat.getText().toString());
    }

    private void loadSettings() {
        editString.setText(Preferences.get("StringConfig"));
        editInteger.setText(String.format("%d", Preferences.get("IntegerConfig", 10)));
        editFloat.setText(String.format("%.2f", Preferences.get("FloatConfig", 1.76f)));
    }
}
