package com.example.nishant.savage;

import android.animation.ValueAnimator;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nishant.savage.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class gamecontainer extends AppCompatActivity implements View.OnClickListener {
    gamewindow[] an = new gamewindow[10];
    RelativeLayout relativeLayout;

    float screenwidth,screenheight;
    private int i = 0;
    Random rm ;
    float x_point;

    int x,y;

    Button left,right;
    ImageView cartoon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamecontainer);

        screenheight = getWindowManager().getDefaultDisplay().getHeight();

        screenwidth= getWindowManager().getDefaultDisplay().getWidth();

        rm = new Random();


        relativeLayout = (RelativeLayout) findViewById(R.id.n);

        left = (Button) findViewById(R.id.left);
        right = (Button) findViewById(R.id.right);

        left.setOnClickListener(this);
        right.setOnClickListener(this);
        cartoon= (ImageView) findViewById(R.id.cartoon);

        Handler handler1 = new Handler();
        for (int a = 0; a<10 ;a++) {
            handler1.postDelayed(new Runnable() {

                @Override
                public void run() {

                    x_point = rm.nextInt((int) screenwidth);
                    x = (int) cartoon.getX();
                    y = (int) cartoon.getY();
                    an[i] = new gamewindow(getApplicationContext(),x_point,x,y) ;

                    move(an[i],screenheight);
                    relativeLayout.addView(an[i]);
                    i++;

                    Log.d("valueto", String.valueOf(i));

}
            }, 1000 * a);

            Log.d("valueto", String.valueOf(a));
        }
    }



    public static void move(final gamewindow an,float width) {
        ValueAnimator va = ValueAnimator.ofFloat(0,width-50);
        int mDuration = 3000; //in millis
        va.setDuration(mDuration);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                an.setTranslationY((float) animation.getAnimatedValue());
            }
        });
        va.start();

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.left :{
                cartoon.setX(cartoon.getX() - 10);

            }
            case R.id.right:{
                cartoon.setX(cartoon.getX() + 10);
            }


        }

    }
}