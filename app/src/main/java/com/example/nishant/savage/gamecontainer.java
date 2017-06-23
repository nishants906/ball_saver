package com.example.nishant.savage;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class gamecontainer extends AppCompatActivity {

    public static Activity act_1e;

    public Handler frame = new Handler();
    private static final int FRAME_RATE = 40; //25 frames per second
    private gamewindow canvas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        act_1e = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamecontainer);


    }


}
