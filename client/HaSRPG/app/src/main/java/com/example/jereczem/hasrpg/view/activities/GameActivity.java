package com.example.jereczem.hasrpg.view.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.sockets.SocketServerConnector;
import com.example.jereczem.hasrpg.view.toolbar.ToolbarSetter;
import com.github.nkzawa.emitter.Emitter;

public class GameActivity extends AppCompatActivity {

    AppCompatActivity activity = this;
    SocketServerConnector sConnector = new SocketServerConnector(10, 10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        new ToolbarSetter(this);
        sConnector.getSocket().on("event", new Emitter.Listener() {
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
