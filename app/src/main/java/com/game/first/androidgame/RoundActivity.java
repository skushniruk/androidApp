package com.game.first.androidgame;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class RoundActivity extends AppCompatActivity {

    TextView currentWordTextView;
    TextView numOfGuessedWords;
    TextView numOfSkippedWords;
    TextView timeLeftLabel;
    Game gameInstance;
    Integer roundTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);
        currentWordTextView = findViewById(R.id.currentWordTextView);
        numOfGuessedWords = findViewById(R.id.guessedWords);
        numOfSkippedWords = findViewById(R.id.skippedWords);
        timeLeftLabel = findViewById(R.id.timeLeft);
        gameInstance = getIntent().getParcelableExtra(TeamsActivity.GAME_INSTANCE);

        roundTime = gameInstance.getRoundTime();

        numOfGuessedWords.setText(gameInstance.getNumOfGuesedWords().toString());
        numOfSkippedWords.setText(gameInstance.getNumOfSkippedWords().toString());
        timeLeftLabel.setText(roundTime.toString());
        timeLeftLabel.setTextColor(Color.GREEN);

        Thread thread = new Thread(){
            @Override
            public void run() {
                while (roundTime > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    roundTime--;
                    runOnUiThread(() -> {
                        if (roundTime == 5)
                            timeLeftLabel.setTextColor(Color.RED);
                        timeLeftLabel.setText(String.valueOf(roundTime));
                    });
                }
                int currentRating = gameInstance.getRatingForCurrentTeam();
                gameInstance.setRatingForCurrentTeam(currentRating + gameInstance.getNumOfGuesedWords()
                        - gameInstance.getNumOfSkippedWords());
                Intent intent = new Intent();
                intent.putExtra(TeamsActivity.GAME_INSTANCE, gameInstance);
                setResult(RESULT_OK, intent);
                finish();
            }
        };
        thread.start();

        currentWordTextView.setOnTouchListener((v, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDrag(data, shadowBuilder, v, 0);
                v.setVisibility(View.VISIBLE);
                return true;
            } else {
                return false;
            }
        });
        View rootView = currentWordTextView.getRootView();

        rootView.setOnDragListener((v, d) -> {
            if (d.getAction() == DragEvent.ACTION_DROP) {
                int[] absolutePos = new int[2];
                currentWordTextView.getLocationOnScreen(absolutePos);
                int center = absolutePos[1] + currentWordTextView.getHeight()/2;
                if (d.getY() <= center) {
                    gameInstance.guesWord(currentWordTextView.getText().toString());
                    numOfGuessedWords.setText(gameInstance.getNumOfGuesedWords().toString());
                } else {
                    gameInstance.skipWord(currentWordTextView.getText().toString());
                    numOfSkippedWords.setText(gameInstance.getNumOfSkippedWords().toString());
                }
                currentWordTextView.setVisibility(View.VISIBLE);
                return true;
            } else {
                return false;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
                return true;
            }
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
