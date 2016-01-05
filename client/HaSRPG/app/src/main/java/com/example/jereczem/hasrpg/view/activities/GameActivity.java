package com.example.jereczem.hasrpg.view.activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.data.player.PlayerData;
import com.example.jereczem.hasrpg.data.player.PlayerDataReceiver;
import com.example.jereczem.hasrpg.game.lobbies.Lobby;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;
import com.example.jereczem.hasrpg.settings.LobbySettings;
import com.example.jereczem.hasrpg.settings.ServerSettings;
import com.example.jereczem.hasrpg.sockets.SocketServerConnector;
import com.example.jereczem.hasrpg.sockets.events.EventName;
import com.example.jereczem.hasrpg.sockets.events.connection.ConnectionEvent;
import com.example.jereczem.hasrpg.sockets.events.disconnection.DisconnectionEvent;
import com.example.jereczem.hasrpg.sockets.events.gpslocation.GpsLocationEvent;
import com.example.jereczem.hasrpg.sockets.events.runtime.RunTimeEvent;
import com.example.jereczem.hasrpg.view.toolbar.ToolbarSetter;
import com.github.nkzawa.emitter.Emitter;

import org.json.JSONException;
import org.json.JSONObject;

public class GameActivity extends AppCompatActivity implements LocationListener {
    public Lobby lobby;
    public PlayerData playerData;
    SocketServerConnector sConnector;
    AppCompatActivity a = this;

    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        new ToolbarSetter(this);
        setSocketConnection();
        setSocketListener();
        setLocationManager();
    }

    @Override
    public void onBackPressed() {
        //do nothing
    }

    private void setSocketListener() {
        sConnector.getSocket().on(ServerSettings.SOCKET_EVENT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                callGameEvent(args[0].toString());
            }
        });
    }

    private void setSocketConnection() {
        playerData = PlayerDataReceiver.fromString(new HttpResponseReceiver("mycharacters").receive().getMessage());
        lobby = (Lobby) this.getIntent().getExtras().getSerializable(LobbySettings.LOBBY_TO_GAME_INTENT_TAG);
        sConnector = new SocketServerConnector(lobby.getLobbyID(), playerData.getUserID());
    }

    private void callGameEvent(String arg) {
        try {
            JSONObject eventInformation = new JSONObject(arg);
            String name = eventInformation.getString("name");
            EventName event = EventName.getEventName(name);
            switch (event) {
                case CONNECTION: {
                    new ConnectionEvent(eventInformation, sConnector, this).runEvent();
                    break;
                }
                case DISCONNECTION: {
                    new DisconnectionEvent(eventInformation, sConnector, this).runEvent();
                    break;
                }
                case GPS_LOCATION: {
                    new GpsLocationEvent(eventInformation, sConnector, this).runEvent();
                    break;
                }
                case RUN_TIME: {
                    new RunTimeEvent(eventInformation, sConnector, this).runEvent();
                    break;
                }
                case NONE: {
                    Log.d("HASLOG", eventInformation.toString());
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setLocationManager() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        /* CAL METHOD requestLocationUpdates */

        // Parameters :
        //   First(provider)    :  the name of the provider with which to register
        //   Second(minTime)    :  the minimum time interval for notifications,
        //                         in milliseconds. This field is only used as a hint
        //                         to conserve power, and actual time between location
        //                         updates may be greater or lesser than this value.
        //   Third(minDistance) :  the minimum distance interval for notifications, in meters
        //   Fourth(listener)   :  a {#link LocationListener} whose onLocationChanged(Location)
        //                         method will be called for each location update


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.d("HASGPS", ":(");
            Toast.makeText(this, " :(", Toast.LENGTH_SHORT);

            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 10, this);
        Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(lastKnownLocation != null) {
            GpsLocationEvent.sentEvent(sConnector, this, lastKnownLocation);
            Log.d("HASGPS", "last know: " + lastKnownLocation.toString());
        }


        /********* After registration onLocationChanged method  ********/
        /********* called periodically after each 3 sec ***********/
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("HASGPS", location.toString());
        GpsLocationEvent.sentEvent(sConnector, this, location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("HASGPS", provider.toString() + " " + status + " " + extras.toString());
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("HASGPS", provider.toString());
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("HASGPS", provider.toString());
    }
}
