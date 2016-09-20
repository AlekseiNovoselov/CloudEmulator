package com.example.aleksei.cloudemulator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class CameraEventReceiver extends BroadcastReceiver {

    private static final String LOG_TAG = CameraEventReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(LOG_TAG, "onReveice");
        Toast.makeText(context, "New Photo Clicked", Toast.LENGTH_LONG).show();
        Intent i = new Intent(context, ChatHeadService.class);
        context.startService(i);
    }
}