package com.game.first.androidgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class RatingsActivity extends AppCompatActivity {

    TeamsRatingAdapter adapter;
    Game gameInstance;

    ListView listView;
    TextView currentTeam;
    Button startRoundButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings);
        setTitle(R.string.team_rating_title);

        gameInstance = getIntent().getParcelableExtra(TeamsActivity.GAME_INSTANCE);
        adapter = new TeamsRatingAdapter(getApplicationContext(), gameInstance);

        listView = findViewById(R.id.listViewTeams);
        currentTeam = findViewById(R.id.teamNameReady);
        startRoundButton = findViewById(R.id.startRoundButton);

        currentTeam.setText(gameInstance.getCurrentTeam());

        listView.setFooterDividersEnabled(true);
        listView.setAdapter(adapter);

        startRoundButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, RoundActivity.class);
            intent.putExtra(TeamsActivity.GAME_INSTANCE, gameInstance);
            startActivity(intent);
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
