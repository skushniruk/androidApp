package com.game.first.androidgame;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;

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
    }

    public void remove(int position) {
        teams.remove(position);
        notifyDataSetChanged();
    }
    private boolean deletedFlag = false;
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
                if (!deletedFlag) {
                    String newTeam = s.toString();
                    if (!newTeam.equals(getItem(position))) {
                        set(position, newTeam);
                    }
                } else {
                    deletedFlag = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Button deleteButton = convertView.findViewById(R.id.deleteTeam);
        deleteButton.setOnClickListener(v -> {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(deleteButton.getWindowToken(), 0);
            deletedFlag = true;
            remove(position);
        });
        return convertView;
    }
}
