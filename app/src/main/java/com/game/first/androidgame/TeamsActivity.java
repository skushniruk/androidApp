package com.game.first.androidgame;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.TextView;

public class TeamsActivity extends AppCompatActivity {

    TeamsAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teams_activity);
        setTitle(R.string.teams_activity_title);

        adapter = new TeamsAdapter(getApplicationContext());
        listView = findViewById(R.id.list);

        listView.setFooterDividersEnabled(true);

        TextView footerView = (TextView) ((LayoutInflater) getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.teams_footer_layout, null, false);
        listView.addFooterView(footerView);
        listView.setAdapter(adapter);

        footerView.setOnClickListener(v -> adapter.add(""));
    }
}
