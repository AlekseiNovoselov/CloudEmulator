package com.example.aleksei.cloudemulator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class CameraEventReceiver extends BroadcastReceiver {

    private static final String LOG_TAG = CameraEventReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(LOG_TAG, "onReveice");
        if (canShow(context)) {
            //Toast.makeText(context, "New Photo Clicked", Toast.LENGTH_LONG).show();
            showIcon(context);
        } else {
            Log.e(LOG_TAG, "can not show");
        }
    }

    private boolean canShow(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(SettingsFragment.SETTING_ICON_CAMERA_KEY, true);
    }

    private void showIcon(Context context) {
        Intent i = new Intent(context, ChatHeadService.class);
        context.startService(i);
    }
}