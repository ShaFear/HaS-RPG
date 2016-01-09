package com.example.jereczem.hasrpg.playgame;

import android.location.Location;

import com.example.jereczem.hasrpg.game.users.Hunter;

import java.util.Observable;

/** Hunter in playgame data */
public class HunterData extends Observable implements Comparable<Hunter>{

    private Hunter hunter;
    private Double latitude = 0.0;
    private Double longitude = 0.0;
    private String name;

    public String getName() {
        return name;
    }

    public HunterData(Hunter hunter, String name) {
        this.hunter = hunter;this.name=name;
    }

    @Override
    public int compareTo(Hunter another) {
        return 0;
    }

    public Hunter getHunter() {
        return hunter;
    }

    public void setHunter(Hunter hunter) {
        this.hunter = hunter;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
        setChanged();
        notifyObservers(GameDataChanges.HUNTER);
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
        setChanged();
        notifyObservers(GameDataChanges.HUNTER);
    }

    @Override
    public String toString() {
        return "HunterData{" +
                "hunter=" + hunter.toString() +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
