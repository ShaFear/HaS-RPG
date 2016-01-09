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
import com.example.jereczem.hasrpg.playgame.HunterData;

import java.util.ArrayList;

/**
 * Created by Michał on 2016-01-07.
 */
public class HunterDataAdapter extends ArrayAdapter<ArrayData<HunterData>> {
    private Double myLatitude = 0.0;
    private Double myLongitude = 0.0;

    public void setMyLatitude(Double myLatitude) {
        this.myLatitude = myLatitude;
    }

    public void setMyLongitude(Double myLongitude) {
        this.myLongitude = myLongitude;
    }

    public HunterDataAdapter(Context context, int resource, ArrayList<ArrayData<HunterData>> hunterDatas) {
        super(context, resource, hunterDatas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.item_hunter, null);
        }

        ArrayData<HunterData> hunterData = getItem(position);

        if(hunterData != null){
            setViewFromHunterData(hunterData, v);
        }
        return v;
    }

    private void setViewFromHunterData(ArrayData<HunterData> hunterData, View v) {
        TextView hunterName = (TextView) v.findViewById(R.id.hunterDataName);
        TextView hunterLvl = (TextView) v.findViewById(R.id.hunterDataLevel);
        TextView hunterDistance = (TextView) v.findViewById(R.id.hunterDataDistance);

        HunterData hunter = hunterData.getData();

        hunterName.setText(hunter.getName());
        hunterLvl.setText(String.valueOf(hunter.getHunter().getLevel()));

        Location myLocation = new Location(LocationManager.GPS_PROVIDER);
        myLocation.setLatitude(myLatitude);
        myLocation.setLongitude(myLongitude);

        Location hunterLocation = new Location(LocationManager.GPS_PROVIDER);
        hunterLocation.setLatitude(hunter.getLatitude());
        hunterLocation.setLongitude(hunter.getLongitude());

        Float distance = myLocation.distanceTo(hunterLocation);
        hunterDistance.setText(String.valueOf(distance.intValue()));
    }
}
