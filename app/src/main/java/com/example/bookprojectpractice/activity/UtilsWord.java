package com.example.bookprojectpractice.activity;

import android.content.Context;
import android.util.Log;

import com.example.bookprojectpractice.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public final class UtilsWord {

    public static String getRandomWord(Context context) {
        InputStream inputStream = context.getResources().openRawResource(R.raw.liste_mots);
        ArrayList<String> mots = new ArrayList<>();

        // Lire le fichier
        //đọc file
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                mots.add(line);
            }
        } catch (IOException e) {
            Log.e("Random", e.getMessage());
            e.printStackTrace();
        }

        // Générer un mot au hasard
        if (!mots.isEmpty()) {
            Random random = new Random();
            int index = random.nextInt(mots.size());
            return mots.get(index);
        } else {
            return null;
        }
    }
}

