package com.example.jereczem.hasrpg.view.adapters;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.playgame.ArrayData;
import com.example.jereczem.hasrpg.playgame.ChaseData;
import com.example.jereczem.hasrpg.playgame.ChaseStatus;
import com.example.jereczem.hasrpg.playgame.HunterData;
import com.example.jereczem.hasrpg.sockets.events.attack.AttackEvent;
import com.example.jereczem.hasrpg.view.fragments.LobbiesFragment;

import java.util.ArrayList;

/**
 * Created by Michał on 2016-01-07.
 */
public class ChaseDataAdapter extends ArrayAdapter<ArrayData<ChaseData>> {
    private Double myLatitude = null;
    private Double myLongitude = null;

    HunterData hunterData = null;

    public void setMyLatitude(Double myLatitude) {
        this.myLatitude = myLatitude;
    }

    public void setMyLongitude(Double myLongitude) {
        this.myLongitude = myLongitude;
    }

    public ChaseDataAdapter(Context context, int resource, ArrayList<ArrayData<ChaseData>> chaseData, HunterData hunterData) {
        super(context, resource, chaseData);
        this.hunterData = hunterData;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.item_chase, null);
        }

        ArrayData<ChaseData> chaseData = getItem(position);

        if(chaseData != null){
            setViewFromChaseData(chaseData, v);
        }
        return v;
    }

    private void setViewFromChaseData(final ArrayData<ChaseData> chaseData, View v) {
        TextView chaseName = (TextView) v.findViewById(R.id.chaseDataName);
        TextView chaseLvl = (TextView) v.findViewById(R.id.chaseDataLevel);
        TextView chaseDistance = (TextView) v.findViewById(R.id.chaseDataDistance);
        TextView chaseStatus = (TextView) v.findViewById(R.id.chaseDataStatus);

        ChaseData chase = chaseData.getData();

        chaseName.setText(chase.getName());
        chaseLvl.setText(String.valueOf(chase.getChase().getLevel()));


        chaseStatus.setText(chase.getStatus().name());

        AttackButton attackButton = (AttackButton) v.findViewById(R.id.attackChaseDataButton);
        attackButton.setAttackedId(chaseData.getUserID());
        attackButton.setClickable(false);
        attackButton.setText("can't");

        if((myLatitude != null && myLongitude != null) && (chase.getLatitude() != 0.0 && chase.getLongitude() != 0.0)) {
            Location myLocation = new Location(LocationManager.GPS_PROVIDER);
            myLocation.setLatitude(myLatitude);
            myLocation.setLongitude(myLongitude);

            Location chaseLocation = new Location(LocationManager.GPS_PROVIDER);
            chaseLocation.setLatitude(chase.getLatitude());
            chaseLocation.setLongitude(chase.getLongitude());

            Float distance = myLocation.distanceTo(chaseLocation);
            chaseDistance.setText(String.valueOf(distance.intValue()));

            if(distance <= hunterData.getHunter().getAttackRange()){
                attackButton.setClickable(true);
                attackButton.setText("KILL!");
            }
        }

    }
}
