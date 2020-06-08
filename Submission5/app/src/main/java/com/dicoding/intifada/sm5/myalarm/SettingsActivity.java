package com.dicoding.intifada.sm5.myalarm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;
import com.dicoding.intifada.sm5.R;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbarReminder;
    private SwitchCompat switchDaily;
    private LinearLayout llBtnSettLang;
    private String MYSAVE_PREF = "my_savepref_reminder";
    private String KEY_DAILY = "key_pref_daily";
    private ConstraintLayout containerConstraint;
    private AlarmReceiverDaily alarmReceiverDaily;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        toolbarReminder = findViewById(R.id.toolbar_reminder);
        switchDaily = findViewById(R.id.switch_daily_reminder);
        //switchRelease = findViewById(R.id.switch_release_reminder);
        llBtnSettLang = findViewById(R.id.ll_btn_lang_settings);
        containerConstraint = findViewById(R.id.coordinator_reminder);

        alarmReceiverDaily = new AlarmReceiverDaily();

        llBtnSettLang.setOnClickListener(this);
        switchDaily.setOnClickListener(this);
        switchDaily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dailyPref(true);
                } else {
                    dailyPref(false);
                }
            }
        });
        //set
        setActionBarToolbar();
        checkIsDaily();
    }

    private void setActionBarToolbar() {
        setSupportActionBar(toolbarReminder);
        toolbarReminder.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);
        toolbarReminder.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private boolean radRefDaily() {
        SharedPreferences mSharedPreferences = this.getSharedPreferences(MYSAVE_PREF, Context.MODE_PRIVATE);
        return mSharedPreferences.getBoolean(KEY_DAILY, false);
    }

    private void checkIsDaily() {
        boolean isDaily = radRefDaily();
        if (isDaily) {
            switchDaily.setChecked(true);
        } else {
            switchDaily.setChecked(false);
        }
    }

    private void dailyPref(boolean isDaily) {
        SharedPreferences sharedPreferences = this.getSharedPreferences(MYSAVE_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_DAILY, isDaily);
        editor.apply();
    }

    private void setAlarmDaily() {
        alarmReceiverDaily.setOneTimeAlarm(this);
    }

    private void rebortAlarmDaily() {
        alarmReceiverDaily.cancelAlarm(this);
    }

    private void showSnackbar(String msg) {
        Snackbar snackbar = Snackbar.make(containerConstraint, msg, 5000);
        snackbar.show();
    }

    @Override
    public void onClick(View v) {
        String msg;
        int id = v.getId();
        if (id == llBtnSettLang.getId()) {
            Intent changeLangIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(changeLangIntent);

        } else if (id == switchDaily.getId()) {
            if (switchDaily.isChecked()) {
                setAlarmDaily();
                msg = getResources().getString(R.string.reminder_daily);
                showSnackbar(msg);
            } else {
                rebortAlarmDaily();
                msg = getResources().getString(R.string.reminder_rebort_daily);
                showSnackbar(msg);
            }

        }
    }
}
