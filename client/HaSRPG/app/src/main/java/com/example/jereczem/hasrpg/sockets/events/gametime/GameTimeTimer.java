package com.example.jereczem.hasrpg.sockets.events.gametime;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.playgame.GameStatus;
import com.example.jereczem.hasrpg.sockets.SocketServerConnector;
import com.example.jereczem.hasrpg.view.activities.HunterGameActivity;
import com.example.jereczem.hasrpg.view.activities.HunterResultActivity;
import com.example.jereczem.hasrpg.view.dialogs.Alerts;

/**
 * Created by Michał on 2016-01-06.
 */
public class GameTimeTimer extends CountDownTimer {
    TextView gameTime;
    HunterGameActivity activity;
    SocketServerConnector sConnector;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public GameTimeTimer(long millisInFuture, long countDownInterval, HunterGameActivity activity, SocketServerConnector sConnector) {
        super(millisInFuture, countDownInterval);
        gameTime = (TextView) activity.findViewById(R.id.gameTimeHunter);
        this.activity = activity;
        this.sConnector = sConnector;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        long seconds = millisUntilFinished / 1000;
        GameTimeEvent.sentGameTimeEvent(sConnector, seconds);
        activity.getGameData().setGameTime(seconds);
    }

    @Override
    public void onFinish() {
        GameTimeEvent.sentGameTimeEvent(sConnector, 0);
        activity.getGameData().setGameTime(0);
        activity.getGameData().setStatus(GameStatus.CHASE_WINS);
    }
}
