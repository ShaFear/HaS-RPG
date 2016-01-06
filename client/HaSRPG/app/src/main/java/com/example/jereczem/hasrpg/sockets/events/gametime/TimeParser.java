package com.example.jereczem.hasrpg.sockets.events.gametime;

/**
 * Created by Michał on 2016-01-06.
 */
public class TimeParser {
    /**
     * Parse long time (seconds) into format "HH:MM:SS"
     *
     * @param time
     * @return
     */
    public static String fromLong(long time){
        long hours = time / 3600;
        long minutes = (time % 3600) / 60;
        long seconds = (time % 60);
        return parseZeros(hours) + ":" + parseZeros(minutes) + ":" + parseZeros(seconds);
    }

    private static String parseZeros(long num){
        StringBuilder zeros = new StringBuilder();
        if(num < 10)
            zeros.append('0');
        zeros.append(num);
        return zeros.toString();
    }
}
