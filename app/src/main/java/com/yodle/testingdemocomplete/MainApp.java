package com.yodle.testingdemocomplete;

import android.app.Application;

import com.google.gson.Gson;
import com.yodle.testingdemocomplete.persistence.Datastore;

public class MainApp extends Application {
    private Gson gson;
    private Datastore datastore;

    @Override
    public void onCreate() {
        super.onCreate();
        gson = new Gson();
        datastore = new Datastore(this, gson);
    }

    public Gson getGson() {
        return gson;
    }

    public Datastore getDatastore() {
        return datastore;
    }
}
