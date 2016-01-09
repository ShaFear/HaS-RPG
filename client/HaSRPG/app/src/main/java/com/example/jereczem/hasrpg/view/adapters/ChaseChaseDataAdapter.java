package com.example.jereczem.hasrpg.view.adapters;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.playgame.ArrayData;
import com.example.jereczem.hasrpg.playgame.ChaseData;

import java.util.ArrayList;

/**
 * Created by Michał on 2016-01-07.
 */
public class ChaseChaseDataAdapter extends ArrayAdapter<ArrayData<ChaseData>> {
    private Double myLatitude = 0.0;
    private Double myLongitude = 0.0;

    public void setMyLatitude(Double myLatitude) {
        this.myLatitude = myLatitude;
    }

    public void setMyLongitude(Double myLongitude) {
        this.myLongitude = myLongitude;
    }

    public ChaseChaseDataAdapter(Context context, int resource, ArrayList<ArrayData<ChaseData>> chaseData) {
        super(context, resource, chaseData);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.item_chase_chase, null);
        }

        ArrayData<ChaseData> chaseData = getItem(position);

        if(chaseData != null){
            setViewFromChaseData(chaseData, v);
        }
        return v;
    }

    private void setViewFromChaseData(ArrayData<ChaseData> chaseData, View v) {
        TextView chaseName = (TextView) v.findViewById(R.id.chaseChaseDataName);
        TextView chaseLvl = (TextView) v.findViewById(R.id.chaseChaseDataLevel);
        TextView chaseDistance = (TextView) v.findViewById(R.id.chaseChaseDataDistance);
        TextView chaseStatus = (TextView) v.findViewById(R.id.chaseChaseDataStatus);

        ChaseData chase = chaseData.getData();

        chaseName.setText(chase.getName());
        chaseLvl.setText(String.valueOf(chase.getChase().getLevel()));

        Location myLocation = new Location(LocationManager.GPS_PROVIDER);
        myLocation.setLatitude(myLatitude);
        myLocation.setLongitude(myLongitude);

        Location chaseLocation = new Location(LocationManager.GPS_PROVIDER);
        chaseLocation.setLatitude(chase.getLatitude());
        chaseLocation.setLongitude(chase.getLongitude());

        Float distance = myLocation.distanceTo(chaseLocation);
        chaseDistance.setText(String.valueOf(distance.intValue()));

        chaseStatus.setText(chase.getStatus().name());
    }
}
