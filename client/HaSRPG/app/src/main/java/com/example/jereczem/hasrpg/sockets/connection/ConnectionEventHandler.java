package com.example.jereczem.hasrpg.sockets.connection;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by jereczem on 10.11.15.
 */
public class ConnectionEventHandler {
    public ConnectionEventHandler(ConnectionEvent connectionEvent, AppCompatActivity activity) {
        new ConnectionAsyncTask().execute(activity, connectionEvent);
    }

    private class ConnectionAsyncTask extends AsyncTask<Object, Void, Void>{
        AppCompatActivity activity;
        ConnectionEvent connectionEvent;

        @Override
        protected Void doInBackground(Object... objects) {
            activity = (AppCompatActivity)objects[0];
            connectionEvent = (ConnectionEvent)objects[1];
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(activity, connectionEvent.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
