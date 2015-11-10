package com.example.jereczem.hasrpg.view.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.view.toolbar.ToolbarSetter;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.Objects;

public class GameActivity extends AppCompatActivity {

    AppCompatActivity activity = this;
    Toast toast;

    private Socket socket;
    {
        try {
            socket = IO.socket("http://192.168.1.128:3000/");
        } catch (URISyntaxException e) {}
    }

    private JSONObject jsonData = new JSONObject();
    {
        try {
            jsonData.put("room", 10);
            jsonData.put("userID", 2);
        } catch (JSONException e) {}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        new ToolbarSetter(this);
        socket.connect();
        socket.emit("joinRoom", jsonData.toString());
        socket.on("user_disconnected", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                (new AsyncTask<Object, Object, Object>() {
                    @Override
                    protected Object doInBackground(Object[] objects) {
                        return objects[0];
                    }

                    @Override
                    protected void onPostExecute(Object o) {
                        Toast.makeText(activity, o.toString(), Toast.LENGTH_LONG).show();
                    }
                }).execute(args[0]);
            }
        });
    }
}
