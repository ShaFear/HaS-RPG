package com.example.jereczem.hasrpg.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.sockets.EventHandler;
import com.example.jereczem.hasrpg.sockets.SocketServerConnector;
import com.example.jereczem.hasrpg.view.toolbar.ToolbarSetter;

public class GameActivity extends AppCompatActivity {

    AppCompatActivity activity = this;
    SocketServerConnector sConnector = new SocketServerConnector(10, 10);
    EventHandler eventHandler = new EventHandler(sConnector.getSocket(), this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        new ToolbarSetter(this);

    }
}
