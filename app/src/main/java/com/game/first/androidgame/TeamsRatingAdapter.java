package com.game.first.androidgame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class TeamsRatingAdapter extends BaseAdapter {

    private final ArrayList<String> teams;
    private Context context;
    private Game gameInstance;

    public TeamsRatingAdapter(Context context, Game gameInstance) {
        teams = new ArrayList<>(gameInstance.getTeams());
        this.context = context;
        this.gameInstance = gameInstance;
    }

    @Override
    public int getCount() {
        return teams.size();
    }

    @Override
    public Object getItem(int position) {
        return teams.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String team = (String) getItem(position);
        Map<String, Integer> ratings = gameInstance.getTeamsAndRatings();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.team_rating_item, parent, false);
        }
        TextView teamNameView = convertView.findViewById(R.id.teamNameRating);
        TextView teamRatingView = convertView.findViewById(R.id.teamRating);
        teamNameView.setText(team);
        teamRatingView.setText(ratings.get(team).toString());
        return convertView;
    }
}

