package com.example.jereczem.hasrpg.sockets.disconnection;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.jereczem.hasrpg.sockets.GameEvent;
import com.example.jereczem.hasrpg.sockets.SocketServerConnector;
import com.example.jereczem.hasrpg.sockets.connection.ConnectionEvent;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

/**
 * Created by jereczem on 10.11.15.
 */
public class DisconnectionEventHandler {
    public DisconnectionEventHandler(DisconnectionEvent disconnectionEvent, AppCompatActivity activity, Socket socket) {
        new ConnectionAsyncTask().execute(activity, disconnectionEvent, socket);
    }

    private class ConnectionAsyncTask extends AsyncTask<Object, Boolean, Void>{
        AppCompatActivity activity;
        DisconnectionEvent disconnectionEvent;
        Socket socket;
        ProgressDialog dialog;

        @Override
        protected Void doInBackground(Object... objects) {
            activity = (AppCompatActivity)objects[0];
            disconnectionEvent = (DisconnectionEvent)objects[1];
            socket = (Socket)objects[2];
            publishProgress(false);
            socket.on("event", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    try {
                        GameEvent gameEvent = GameEvent.createFromString(args[0].toString());
                        if(gameEvent instanceof ConnectionEvent){
                            Integer userID = ((ConnectionEvent)gameEvent).getUserID();
                            if(userID.equals(disconnectionEvent.getUserID()))
                                publishProgress(true);
                        }
                    } catch (GameEvent.NoGameEventException e) {
                        e.printStackTrace();
                    }

                }
            });
            return null;
        }

        @Override
        protected void onProgressUpdate(Boolean... values) {
            super.onProgressUpdate(values);
            if(!values[0]) {
                dialog = ProgressDialog.show(activity, "User " + disconnectionEvent.getUserID() + " is disconnected", "Please wait until user connect again", true);
            }else{
                dialog.dismiss();
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //Toast.makeText(activity, disconnectionEvent.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
