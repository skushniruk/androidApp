package com.game.first.androidgame;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game implements Parcelable {

    private int roundTime = 60;
    private ArrayList<String> teams;
    private ArrayList<Integer> ratings;
    private String currentTeam;

    public Game(ArrayList<String> teams) {
        this.teams = teams;
        initializeRatings(teams);
        currentTeam = teams.get(0);
    }
    //the constructor to recreate object
    protected Game(Parcel in) {
        roundTime = in.readInt();
        currentTeam = in.readString();
        teams = in.createStringArrayList();
        ratings = new ArrayList<>();
        ratings = in.readArrayList(Integer.class.getClassLoader());
    }

    public Map<String, Integer> getTeamsAndRatings() {
        HashMap<String, Integer> teamsAndRatings = new HashMap<>();
        for (int i = 0; i < teams.size(); i++) {
            teamsAndRatings.put(teams.get(i), ratings.get(i));
        }
        return teamsAndRatings;
    }

    public ArrayList<String> getTeams() {
        return teams;
    }

    public String getCurrentTeam() {
        return currentTeam;
    }

    private void initializeRatings(ArrayList<String> teams) {
        ratings = new ArrayList<>();
        for (int i = 0; i < teams.size(); i++) {
            ratings.add(0);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(roundTime);
        dest.writeString(currentTeam);
        dest.writeStringList(teams);
        dest.writeList(ratings);
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

}
