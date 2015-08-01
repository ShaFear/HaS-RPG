package com.example.jereczem.hasrpg.networking.tasks;

import com.example.jereczem.hasrpg.networking.HttpConnection;
import com.example.jereczem.hasrpg.networking.HttpResponse;

import java.io.IOException;

/**
 * Created by jereczem on 01.08.15.
 */
public class HttpGetAsyncTask extends HttpConnectionAsyncTask {
    @Override
    protected HttpResponse doInBackground(Object... params) {
        String url = (String) params[0];
        try {
            return HttpConnection.get(url);
        } catch (IOException e) {
            return new HttpResponse(500, e.toString());
        }
    }
}
