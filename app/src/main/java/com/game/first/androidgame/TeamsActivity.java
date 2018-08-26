package com.game.first.androidgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.game.first.androidgame.MainActivity.CREATE_NEW;

public class TeamsActivity extends AppCompatActivity {

    private static final String SAVED_INSTANCE_ARRAY = "saved_teams";
    private static final String FIRST_ELEMENT = "first_element";
    private Button continueGameButton;

    TeamsAdapter adapter;
    ListView listView;
    public boolean isRunBefore = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teams_activity);
        setTitle(R.string.teams_activity_title);

        if (getIntent().getBooleanExtra(MainActivity.CREATE_NEW, true)) {
            adapter = new TeamsAdapter(getApplicationContext());
        } else {
            adapter = new TeamsAdapter(getApplicationContext(), loadData());
        }

        listView = findViewById(R.id.list);

        listView.setFooterDividersEnabled(true);

        TextView footerView = (TextView) ((LayoutInflater) getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.teams_footer_layout, null, false);
        listView.addFooterView(footerView);
        listView.setAdapter(adapter);

        footerView.setOnClickListener(v -> adapter.add(""));

        continueGameButton.findViewById(R.id.start_game_button);
        continueGameButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveData(adapter.getTeams());
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveData(adapter.getTeams());
    }

    private void saveData(ArrayList<String> teams) {
        SharedPreferences sp = getApplicationContext().getSharedPreferences(SAVED_INSTANCE_ARRAY, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(FIRST_ELEMENT, teams.size());
        for (int i = 0; i < teams.size(); i++) {
            edit.putString(Integer.toString(i), teams.get(i));
        }
        edit.commit();
     }

     private ArrayList<String> loadData() {
         SharedPreferences sp = getApplicationContext().getSharedPreferences(SAVED_INSTANCE_ARRAY, Context.MODE_PRIVATE);
         int size = sp.getInt(FIRST_ELEMENT, 0);
         ArrayList<String> teams = new ArrayList<>();
         for (int i = 0; i < size; i++) {
             String team = sp.getString(Integer.toString(i), null);
             if (team == null)
                 continue;
             teams.add(team);
         }
         return teams;
     }

}
