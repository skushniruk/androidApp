package com.game.first.androidgame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

public class RatingsActivity extends AppCompatActivity {

    TeamsRatingAdapter adapter;
    Game gameInstance;

    ListView listView;
    TextView currentTeam;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings);
        //setTitle(R.string.teams_activity_title);

        gameInstance = getIntent().getParcelableExtra(TeamsActivity.GAME_INSTANCE);
        adapter = new TeamsRatingAdapter(getApplicationContext(), gameInstance);

        listView = findViewById(R.id.listViewTeams);
        currentTeam = findViewById(R.id.teamNameReady);

        currentTeam.setText(gameInstance.getCurrentTeam());

        listView.setFooterDividersEnabled(true);
        listView.setAdapter(adapter);
    }

}
