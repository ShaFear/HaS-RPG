package com.example.jereczem.hasrpg.sockets.events;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import com.example.jereczem.hasrpg.settings.ServerSettings;
import com.example.jereczem.hasrpg.sockets.SocketServerConnector;
import com.example.jereczem.hasrpg.view.activities.GameActivity;
import com.github.nkzawa.emitter.Emitter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by jereczem on 10.11.15.
 */
public abstract class HandShakeEvent<T extends GameActivity>{
    protected SocketServerConnector sConnector;
    protected String name;
    protected JSONObject eventInformation;
    protected T activity;

    public HandShakeEvent(JSONObject eventInformation, SocketServerConnector sConnector, T activity){
        try {
            this.sConnector = sConnector;
            this.name = eventInformation.getString("name");
            this.eventInformation = eventInformation;
            this.activity = activity;
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    protected abstract JSONObject getHandShakingEvent() throws JSONException;

    protected abstract void beforeHandShakeReaction(T activity) throws JSONException;

    protected abstract void afterHandShakeReaction(T activity) throws JSONException;

    public void runEvent(){
        EventTask eventTask = new EventTask(true);
        eventTask.execute();
    }

    protected class EventTask extends AsyncTask<Object, Boolean, Void> {

        public EventTask(Boolean isHandShakeNeeded) {
            this.isHandShakeNeeded = isHandShakeNeeded;
        }

        protected Boolean isHandShakeNeeded;

        @Override
        protected Void doInBackground(Object... objects) {
            if(isHandShakeNeeded){
                publishProgress(false);
                sConnector.getSocket().on(ServerSettings.SOCKET_EVENT, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        try {
                            JSONObject eInfo = new JSONObject(args[0].toString());
                            JSONObject hInfo = getHandShakingEvent();
                            Iterator<?> keys = hInfo.keys();
                            Boolean areTheSame = true;
                            while (keys.hasNext()){
                                String key = (String)keys.next();
                                if(eInfo.has(key)){
                                    if(!eInfo.get(key).equals(hInfo.get(key))){
                                        areTheSame = false;
                                        break;
                                    }
                                }
                            }
                            if (areTheSame)
                                publishProgress(true);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                publishProgress(true);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Boolean[] values) {
            try{
                if(values[0].equals(true)){
                    afterHandShakeReaction(activity);
                } else{
                    beforeHandShakeReaction(activity);
                }
            } catch (JSONException e){
                e.printStackTrace();
            }
        }
    }
}
