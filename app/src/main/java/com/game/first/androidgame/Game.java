package com.game.first.androidgame;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game implements Parcelable {

    private int roundTime = 60;
    private ArrayList<String> teams;
    private ArrayList<Integer> ratings;
    private int currentTeamId = 0;
    private ArrayList<String> currentGuesedWords;
    private ArrayList<String> currentSkippedWords;

    public Game(ArrayList<String> teams) {
        this.teams = teams;
        initializeRatings(teams);
    }
    //the constructor to recreate object
    protected Game(Parcel in) {
        roundTime = in.readInt();
        currentTeamId = in.readInt();
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
        return teams.get(currentTeamId);
    }

    public String getNextTeam() {
        if (currentTeamId >= teams.size() - 1) {
            currentTeamId = 0;
        } else {
            currentTeamId++;
        }
        currentGuesedWords = new ArrayList<>();
        currentSkippedWords = new ArrayList<>();
        return getCurrentTeam();
    }

    public void guesWord(String word) {
        if (currentGuesedWords == null) {
            currentGuesedWords = new ArrayList<>();
        }
        currentGuesedWords.add(word);
    }

    public void skipWord(String word) {
        if (currentSkippedWords == null) {
            currentSkippedWords = new ArrayList<>();
        }
        currentSkippedWords.add(word);
    }

    public Integer getNumOfGuesedWords() {
        if (currentGuesedWords == null) {
            return 0;
        }
        return currentGuesedWords.size();
    }

    public Integer getNumOfSkippedWords() {
        if (currentSkippedWords == null) {
            return 0;
        }
        return currentSkippedWords.size();
    }

    public ArrayList<String> getGuessedWords() {
        return currentGuesedWords;
    }

    public ArrayList<String> getSkippedWords() {
        return currentSkippedWords;
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
        dest.writeInt(currentTeamId);
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
