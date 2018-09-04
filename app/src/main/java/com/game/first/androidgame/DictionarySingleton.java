package com.game.first.androidgame;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DictionarySingleton {
    private static DictionarySingleton singleton;
    private Context context;
    private Set<String> words;
    private Iterator<String> currentIterator;

    private DictionarySingleton(Context context) {
        this.context = context;
        words = new HashSet<>();
        readFromFile();
    }

    public static DictionarySingleton getInstance(Context context) {
        if (singleton == null) {
            synchronized (DictionarySingleton.class) {
                if (singleton == null)
                    singleton = new DictionarySingleton(context);
            }
        }
        return singleton;
    }

    public void reInitializeDictionary() {
        currentIterator = words.iterator();
    }

    public String getRandomWord() {
        if (currentIterator != null && currentIterator.hasNext())
            return currentIterator.next();
        return null;
    }

    private void readFromFile() {
        InputStream inputStream = context.getResources().openRawResource(R.raw.dictionary);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String str;
            while ((str = reader.readLine()) != null) {
                String[] splitWords = str.split(" ");
                for (int i = 0; i < splitWords.length; i++) {
                    words.add(splitWords[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
