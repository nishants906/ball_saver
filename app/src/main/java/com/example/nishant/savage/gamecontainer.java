package com.example.nishant.savage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
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

import static android.R.attr.cacheColorHint;
import static android.R.attr.fillAfter;
import static android.R.attr.fillEnabled;
import static android.R.attr.value;

public class gamecontainer extends AppCompatActivity implements View.OnClickListener {
    gamewindow[] an = new gamewindow[11];
    RelativeLayout relativeLayout;

    float screenwidth, screenheight;
    private int i = 0;
    Random rm;
    float x_point;

    ArrayList<Rect> ball;
    Rect Player;
    int x, y;

    Button left, right;
    ImageView cartoon;

    TranslateAnimation transAnimation;

    int k = 0;
    Boolean j=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamecontainer);

        screenheight = getWindowManager().getDefaultDisplay().getHeight();

        screenwidth = getWindowManager().getDefaultDisplay().getWidth();

        Log.d("screensize", String.valueOf(screenheight));

        rm = new Random();


        relativeLayout = (RelativeLayout) findViewById(R.id.n);

        left = (Button) findViewById(R.id.left);
        right = (Button) findViewById(R.id.right);

        left.setOnClickListener(this);
        right.setOnClickListener(this);
        cartoon = (ImageView) findViewById(R.id.cartoon);

        ball = new ArrayList<Rect>();


        (new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted())
                    try {
                        if (i < 10) {
                            Thread.sleep(1000);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    x_point = rm.nextInt((int) (screenwidth - 500));
                                    x = (int) cartoon.getX();
                                    y = (int) cartoon.getY();
                                    an[i] = new gamewindow(getApplicationContext(), x_point, x, y);

                                    j = CheckCollision(cartoon, an[i]);


                                    move(an[i], screenheight);
                                    relativeLayout.addView(an[i]);

                                    i++;


                                    Log.d("valueto", String.valueOf(j));


                                    Log.d("valueof", String.valueOf(an[0].getY()));


                                }
                            });
                        } else {
                            Thread.interrupted();
                        }
                    } catch (InterruptedException e) {
                        //error
                    }
            }
        })).start();
    }

    public static void move(final gamewindow an, float width) {
        ValueAnimator va = ValueAnimator.ofFloat(0, width - 400);
        int mDuration = 3000; //in millis
        va.setDuration(mDuration);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                an.setTranslationY((float) animation.getAnimatedValue());
                Log.d("valuechange", String.valueOf(animation.getAnimatedValue()));

            }
        });
        va.start();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.left: {

                ValueAnimator va = ValueAnimator.ofFloat(cartoon.getX(), (cartoon.getX() - 10));
                int mDuration = 500; //in millis
                va.setDuration(mDuration);
                va.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        cartoon.setTranslationX(cartoon.getX());
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator animation) {

                        cartoon.setTranslationX((float) animation.getAnimatedValue());
                    }
                });
                va.start();

                break;

            }
            case R.id.right: {
                ValueAnimator va = ValueAnimator.ofFloat(cartoon.getX(), cartoon.getX() + 10);
                int mDuration = 500; //in millis
                va.setDuration(mDuration);
                va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator animation) {
                        cartoon.setTranslationX((float) animation.getAnimatedValue());
                    }
                });
                va.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        cartoon.setTranslationX(cartoon.getX());
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                va.start();

                break;
            }


        }
    }


    public boolean CheckCollision(ImageView cartoon, gamewindow balls) {
        Rect R1=new Rect(cartoon.getLeft(), cartoon.getTop(), cartoon.getRight(), cartoon.getBottom());
        Rect R2=new Rect(balls.getLeft(), balls.getTop(), balls.getRight(), balls.getBottom());
        return R1.intersect(R2);

    }
}
