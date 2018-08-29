package com.game.first.androidgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.widget.Button;
import android.widget.SeekBar;


public class SettingsActivity extends AppCompatActivity {


    private Button nextButton;
    private SeekBar timeRange;
    private int minRoundTime = 10;


    protected void onCreate() {
        //super.onCreate();
        setContentView(R.layout.settings_activity);
        setTitle(R.string.settings_activity_title);


        nextButton = findViewById(R.id.settings_continue_button);

        /*nextButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, TeamsActivity.class);
            intent.putExtra(CREATE_NEW, true);
            startActivity(intent);
        });*/


    }



}
