package com.example.jereczem.hasrpg.view.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by Michał on 2016-01-09.
 */
public class AttackButton extends Button{

    Integer attackedId;

    public AttackButton(Context context) {
        super(context);
    }

    public AttackButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AttackButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AttackButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public Integer getAttackedId() {
        return attackedId;
    }

    public void setAttackedId(Integer attackedId) {
        this.attackedId = attackedId;
    }




}
