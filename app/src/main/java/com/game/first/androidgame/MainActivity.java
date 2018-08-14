package com.game.first.androidgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends Activity {

    public static final String CREATE_NEW = "create_new";

    private Button startButton;
    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        continueButton = findViewById(R.id.continueButton);
        startButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, TeamsActivity.class);
            intent.putExtra(CREATE_NEW, true);
            startActivity(intent);
        });
        continueButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, TeamsActivity.class);
            intent.putExtra(CREATE_NEW, false);
            startActivity(intent);
        });
    }
}
