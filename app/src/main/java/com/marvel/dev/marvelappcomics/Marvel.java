package com.marvel.dev.marvelappcomics;

import android.util.Log;

import com.arnaudpiroelle.marvel.api.MarvelApi;
import com.arnaudpiroelle.marvel.api.exceptions.AuthorizationException;
import com.arnaudpiroelle.marvel.api.exceptions.EntityNotFoundException;
import com.arnaudpiroelle.marvel.api.exceptions.QueryException;
import com.arnaudpiroelle.marvel.api.exceptions.RateLimitException;
import com.arnaudpiroelle.marvel.api.objects.Comic;
import com.arnaudpiroelle.marvel.api.objects.ref.DataWrapper;
import com.arnaudpiroelle.marvel.api.services.sync.ComicsService;

import retrofit.RestAdapter;

import static android.content.ContentValues.TAG;

class Marvel {
    private static volatile Marvel instance;

    private final ComicsService comicsService;

    private Marvel() {
        MarvelApi.configure()
                .withApiKeys(Const.PUBLIC_KEY, Const.PRIVATE_KEY)
                .withLogLevel(RestAdapter.LogLevel.NONE)
                .init();
        comicsService = MarvelApi.getService(ComicsService.class);
    }

    public static Marvel getInstance() {
        if (instance == null)
            synchronized (Marvel.class) {
                if (instance == null)
                    instance = new Marvel();
            }
        return instance;
    }

    public String test() {
        DataWrapper<Comic> comicList = null;
        try {
            comicList = comicsService.listComic();
        } catch (AuthorizationException | QueryException | EntityNotFoundException | RateLimitException e) {
            e.printStackTrace();
            return "none(";
        }
        Log.d(TAG, "test: DONE");
        return comicList.getStatus();
    }

    private class Const {
        static final String PRIVATE_KEY = "ce3f283ecbe3e4f4fe4b392565810d416be3c545";
        static final String PUBLIC_KEY = "696927ce1fc565b1ebe84e68ec43f989";
    }
}
