package com.game.first.androidgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class CongratulationActivity extends AppCompatActivity {

    private TextView winnerTextView;
    private Button newGameButton;
    private Button onceMoreRoundButton;
    private Game gameInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulation);

        gameInstance = getIntent().getParcelableExtra(TeamsActivity.GAME_INSTANCE);
        winnerTextView = findViewById(R.id.winnerTeamLabel);
        newGameButton = findViewById(R.id.newGameButton);
        onceMoreRoundButton = findViewById(R.id.onceMoreRound);

        winnerTextView.setText(gameInstance.getWinner());
        onceMoreRoundButton.setOnClickListener(v -> {
            gameInstance.setMaxNumOfRounds(gameInstance.getMaxNumOfRounds() + 1);
            Intent intent = new Intent();
            intent.putExtra(TeamsActivity.GAME_INSTANCE, gameInstance);
            setResult(RESULT_OK, intent);
            finish();
        });
        newGameButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, TeamsActivity.class);
            intent.putExtra(MainActivity.CREATE_NEW, false);
            startActivity(intent);
        });
    }
}
