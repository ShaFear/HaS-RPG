package com.example.jereczem.hasrpg.playgame;

import android.location.Location;

import com.example.jereczem.hasrpg.game.users.Chase;

/** Chase in playgame data */
public class ChaseData implements Comparable<Chase>{

    private Chase chase;
    private ChaseStatus status = ChaseStatus.ALIVE;
    private Double latitude = 0.0;
    private Double longitude = 0.0;

    public ChaseData(Chase chase){
        this.chase = chase;
    }

    @Override
    public int compareTo(Chase another) {
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
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
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
