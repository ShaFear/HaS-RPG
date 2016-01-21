package com.example.jereczem.hasrpg.playgame;

import com.example.jereczem.hasrpg.game.users.Chase;

import java.io.Serializable;
import java.util.Observable;

/** Chase in playgame data */
public class ChaseData extends Observable implements Comparable<ChaseData>, Serializable {

    private Chase chase;
    private ChaseStatus status = ChaseStatus.ALIVE;
    private Double latitude = 0.0;
    private Double longitude = 0.0;
    private String name;

    public String getName() {
        return name;
    }

    public ChaseData(Chase chase, String name){
        this.chase = chase;
        this.name = name;
    }

    @Override
    public int compareTo(ChaseData another) {
        if((getStatus().equals(ChaseStatus.DEAD)) && (another.getStatus().equals(ChaseStatus.ALIVE))){
            return 1;
        }
        return 0;
    }

    public Chase getChase() {
        return chase;
    }

    public void setChase(Chase chase) {
        this.chase = chase;
    }

    public ChaseStatus getStatus() {
        return status;
    }

    public void setStatus(ChaseStatus status) {
        this.status = status;
        setChanged();
        notifyObservers(GameDataChanges.CHASE);
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
        setChanged();
        notifyObservers(GameDataChanges.CHASE);
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
        setChanged();
        notifyObservers(GameDataChanges.CHASE);
    }

    @Override
    public String toString() {
        return "ChaseData{" +
                "chase=" + chase.toString() +
                ", status=" + status +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
