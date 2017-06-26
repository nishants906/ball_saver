package com.example.nishant.savage;

import android.animation.ValueAnimator;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.nishant.savage.R;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class gamecontainer extends AppCompatActivity {
    gamewindow an;
    RelativeLayout relativeLayout;

    float screenwidth,screenheight;
    private int i = 1;
    FrameLayout frame;
    Random rm ;
    float x_point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamecontainer);

        screenheight = getWindowManager().getDefaultDisplay().getHeight();

        screenwidth= getWindowManager().getDefaultDisplay().getWidth();

        rm = new Random();

        relativeLayout = (RelativeLayout) findViewById(R.id.n);

        Handler handler1 = new Handler();
        for (int a = 1; a<=10 ;a++) {
            handler1.postDelayed(new Runnable() {

                @Override
                public void run() {

                    x_point = rm.nextInt((int) screenwidth);
                    an = new gamewindow(getApplicationContext(),x_point);
                    relativeLayout.addView(an);

                    move(an,screenheight);
                }
            }, 2000 * a);
        }
    }



    public static void move(final gamewindow an,float width) {
        ValueAnimator va = ValueAnimator.ofFloat(0,width-50);
        int mDuration = 10000; //in millis
        va.setDuration(mDuration);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                an.setTranslationY((float) animation.getAnimatedValue());
            }
        });
        va.setRepeatCount(5);
        va.start();

    }
}
