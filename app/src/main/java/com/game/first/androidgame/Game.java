package com.game.first.androidgame;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game implements Parcelable {

    private int roundTime = 5;
    private int maxNumOfRounds = 1;
    private int currentRound;
    private ArrayList<String> teams;
    private ArrayList<Integer> ratings;
    private int currentTeamId = 0;
    private ArrayList<String> currentGuessedWords;
    private ArrayList<String> currentSkippedWords;

    public Game(ArrayList<String> teams) {
        this.teams = teams;
        currentRound = 1;
        initializeRatings(teams);
    }
    //the constructor to recreate object
    protected Game(Parcel in) {
        roundTime = in.readInt();
        maxNumOfRounds = in.readInt();
        currentRound = in.readInt();
        currentTeamId = in.readInt();
        teams = in.createStringArrayList();
        ratings = new ArrayList<>();
        ratings = in.readArrayList(Integer.class.getClassLoader());
    }

    public Integer getRoundTime() {
        return roundTime;
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

    public void setRatingForCurrentTeam(int rating) {
        ratings.set(currentTeamId, rating);
    }

    public int getRatingForCurrentTeam() {
        return ratings.get(currentTeamId);
    }

    public String getNextTeam() {
        if (currentTeamId >= teams.size() - 1) {
            currentTeamId = 0;
            currentRound++;
        } else {
            currentTeamId++;
        }
        currentGuessedWords = new ArrayList<>();
        currentSkippedWords = new ArrayList<>();
        return getCurrentTeam();
    }

    public boolean isThisFinalRound() {
        if (currentTeamId >= teams.size() - 1 && currentRound >= maxNumOfRounds)
            return true;
        else
            return false;
    }

    public String getWinner() {
        if (ratings.size() < 1)
            return null;
        int maxRating = ratings.get(0);
        int pos = 0;
        for (int i = 0; i < ratings.size(); i++) {
            if (maxRating < ratings.get(i)) {
                maxRating = ratings.get(i);
                pos = i;
            }
        }
        return teams.get(pos);
    }

    public int getMaxNumOfRounds() {
        return maxNumOfRounds;
    }

    public void setMaxNumOfRounds(int newValue) {
        maxNumOfRounds = newValue;
    }

    public void guesWord(String word) {
        if (currentGuessedWords == null) {
            currentGuessedWords = new ArrayList<>();
        }
        currentGuessedWords.add(word);
    }

    public void skipWord(String word) {
        if (currentSkippedWords == null) {
            currentSkippedWords = new ArrayList<>();
        }
        currentSkippedWords.add(word);
    }

    public Integer getNumOfGuesedWords() {
        if (currentGuessedWords == null) {
            return 0;
        }
        return currentGuessedWords.size();
    }

    public Integer getNumOfSkippedWords() {
        if (currentSkippedWords == null) {
            return 0;
        }
        return currentSkippedWords.size();
    }

    public ArrayList<String> getGuessedWords() {
        return currentGuessedWords;
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
        dest.writeInt(maxNumOfRounds);
        dest.writeInt(currentRound);
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
