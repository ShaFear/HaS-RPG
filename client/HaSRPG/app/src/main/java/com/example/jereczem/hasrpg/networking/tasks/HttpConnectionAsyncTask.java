package com.example.jereczem.hasrpg.networking.tasks;

import android.os.AsyncTask;

import com.example.jereczem.hasrpg.networking.HttpResponse;

import java.io.IOException;

/**
 * Created by jereczem on 01.08.15.
 */
public abstract class HttpConnectionAsyncTask extends AsyncTask<Object, Void, HttpResponse> {
    @Override
    protected HttpResponse doInBackground(Object... params) {
        return null;
    }
}
