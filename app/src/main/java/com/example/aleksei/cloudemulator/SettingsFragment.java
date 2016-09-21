package com.example.aleksei.cloudemulator;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.SwitchPreferenceCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SettingsFragment extends PreferenceFragmentCompat {

    private static final String LOG_TAG = SettingsFragment.class.getSimpleName();

    public final static String SETTING_ICON_CAMERA_KEY = "setting_icon_on_camera";

    public static int OVERLAY_PERMISSION_REQ_CODE_CHATHEAD = 1234;
    public static int OVERLAY_PERMISSION_REQ_CODE_CHATHEAD_MSG = 5678;

    SwitchPreferenceCompat iconOnCameraPermition;

    public static SettingsFragment newInstance() {
        SettingsFragment settingsFragment = new SettingsFragment();
        Bundle arguments = new Bundle();
        settingsFragment.setArguments(arguments);
        return settingsFragment;
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.pref_settings);
        iconOnCameraPermition = (SwitchPreferenceCompat) findPreference(SETTING_ICON_CAMERA_KEY);

        if (iconOnCameraPermition != null) {
            iconOnCameraPermition.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                @Override
                public boolean onPreferenceChange(Preference arg0, Object isdataTrafficEnabled) {
                    boolean isEnabled = (Boolean)isdataTrafficEnabled;
                    Log.e(LOG_TAG, "PERM: " + isEnabled);
                    if (isEnabled) {
                        if(Utils.canDrawOverlays(getContext()))
                            Log.e(LOG_TAG, "Already have permision");
                        else{
                            requestPermission(OVERLAY_PERMISSION_REQ_CODE_CHATHEAD_MSG);
                            return false;
                        }
                    }
                    return true;
                }
            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        view.setBackgroundColor(Color.WHITE);
        return view;
    }

    private void requestPermission(int requestCode){
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        intent.setData(Uri.parse("package:" + getActivity().getPackageName()));
        startActivityForResult(intent, requestCode);
    }

    public void handleOnActivityResult() {
        refreshPrefStatus();
    }

    private void refreshPrefStatus() {
        if (iconOnCameraPermition != null) {
            if (!Utils.canDrawOverlays(getContext())) {
                iconOnCameraPermition.setChecked(false);
            } else {
                iconOnCameraPermition.setChecked(true);
            }
        } else {
            Log.e(LOG_TAG, "icon null");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
