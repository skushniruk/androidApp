package com.game.first.androidgame;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class TeamsAdapter extends BaseAdapter {

    private final ArrayList<String> teams;
    private final Context context;

    public TeamsAdapter(Context context) {
        teams = new ArrayList<>();
        this.context = context;
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

    public void add(String team) {
        teams.add(team);
        notifyDataSetChanged();
    }

    public void set(int position, String newTeam) {
        teams.set(position, newTeam);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        teams.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String team = (String) getItem(position);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.team_item, parent, false);
        }
        EditText teamName = convertView.findViewById(R.id.teamName);
        teamName.setText(team);
        teamName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                set(position, s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Button deleteButton = convertView.findViewById(R.id.deleteTeam);
        deleteButton.setOnClickListener(v -> remove(position));
        return convertView;
    }
}
