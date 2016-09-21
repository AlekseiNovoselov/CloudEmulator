package com.example.aleksei.cloudemulator;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction fTran = getSupportFragmentManager().beginTransaction();
        SettingsFragment settingsFragment = SettingsFragment.newInstance();
        fTran.replace(R.id.mainLayout, settingsFragment);
        fTran.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(LOG_TAG, "onActivityResult");
        super.onActivityResult(requestCode, resultCode, data);
        Fragment frg = getSupportFragmentManager().findFragmentById(R.id.mainLayout);
        if (frg instanceof SettingsFragment) {
            SettingsFragment settingsFragment = (SettingsFragment) frg;
            settingsFragment.handleOnActivityResult();
        }
    }
}
