package com.example.nishant.savage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.media.Image;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

import static android.R.attr.animateFirstView;
import static android.R.attr.breadCrumbShortTitle;
import static android.R.attr.cacheColorHint;
import static android.R.attr.fillAfter;
import static android.R.attr.fillEnabled;
import static android.R.attr.fingerprintAuthDrawable;
import static android.R.attr.finishOnCloseSystemDialogs;
import static android.R.attr.value;

public class gamecontainer extends AppCompatActivity {
    gamewindow[] an = new gamewindow[11];
    RelativeLayout relativeLayout;

    float screenwidth, screenheight;
    private int i = 0;
    Random rm;
    float x_point;

    int remove=0;                  //100dp = 250f    254 for cartoon

    int width,height;

    ImageView check;



    Button lane, left, right;
    static ImageView cartoon;

    TranslateAnimation transAnimation;

    static float x_pos;
    static float y_pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamecontainer);

        screenheight = getWindowManager().getDefaultDisplay().getHeight();

        screenwidth = getWindowManager().getDefaultDisplay().getWidth();

        Log.d("screensize", String.valueOf(screenheight));

        rm = new Random();

        cartoon = (ImageView) findViewById(R.id.cartoon);

        cartoon.post(new Runnable() {
            @Override
            public void run() {
                width = cartoon.getMeasuredWidth();
                height = cartoon.getMeasuredHeight();

                Log.d("cartoonwidth", String.valueOf(height));


            }
        });

        Log.d("cartoonwidth", (String.valueOf(cartoon.getTop()+" , "+cartoon.getBottom())));


        check = (ImageView) findViewById(R.id.check);

        relativeLayout = (RelativeLayout) findViewById(R.id.n);

        left = (Button) findViewById(R.id.left);
        right = (Button) findViewById(R.id.right);



        left.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getRawX();


                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction, 50);
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mHandler == null) return true;
                        mHandler.removeCallbacks(mAction);
                        mHandler = null;
                        break;
                }
                return false;

            }

            Runnable mAction = new Runnable() {
                @Override
                public void run() {


                    cartoon.setX(cartoon.getX() - 30);


                    mHandler.postDelayed(this, 50);
                }
            };


            //        cartoon.setTranslationX(cartoon.getTranslationX() - 30);

        });


        right.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;

            @Override
            public boolean onTouch(View v, MotionEvent event) {


                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction, 50);
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mHandler == null) return true;
                        mHandler.removeCallbacks(mAction);
                        mHandler = null;
                        break;
                }
                return false;

            }

            Runnable mAction = new Runnable() {
                @Override
                public void run() {




                    cartoon.setX(cartoon.getX() + 30);
                    check.setTranslationX(cartoon.getTranslationX()-254);


                    mHandler.postDelayed(this, 50);
                }
            };
        });


        lane = (Button) findViewById(R.id.lane);
        lane.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getRawX();

                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    cartoon.setTranslationX(x);

                    Log.d("changevalue1", String.valueOf(cartoon.getX()));

                }
                return true;
            }
        });

     /*   left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Xposition", String.valueOf(cartoon.getX()));

                cartoon.setTranslationX(cartoon.getTranslationX()-10);

            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Xposition", String.valueOf(cartoon.getX()));

                cartoon.setTranslationX(cartoon.getTranslationX()+10);

            }
        });*/



       (new Thread(new Runnable() {
            @Override
            public void run() {
                int level=1;
                while (!Thread.interrupted())
                    try {
                        if (i < 10) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    if(remove==0) {
                                        x_point = (rm.nextInt((int) (screenwidth - 90))+ 40);
                                        an[i] = new gamewindow(getApplicationContext(), x_point);

                                        move(an[i], screenheight, x_point, i);
                                        relativeLayout.addView(an[i]);

                                    }
                                    else {
                                     }

                                    i++;

                                }
                            });

                        } else {
                  /*          final int finalLevel = level;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    Toast.makeText(getApplicationContext(),("Level "+ finalLevel),Toast.LENGTH_SHORT).show();



                                }
                            });
                            level++ ;
*/
                  Thread.interrupted();


                        }
                        Thread.sleep(3000/level);

                    } catch (InterruptedException e) {
                        //error
                    }
            }
        })).start();
    }



    public void move(final gamewindow an, final float screenwidth, final float x_point, final int i) {


        ObjectAnimator translateXAnimation = ObjectAnimator.ofFloat(an, "translationX", 0f, 0f);
        ObjectAnimator translateYAnimation = ObjectAnimator.ofFloat(an, "translationY", 0f, screenwidth );

        final AnimatorSet set = new AnimatorSet();
        set.setDuration(10000);
        set.playTogether(translateXAnimation, translateYAnimation);
        set.start();

        translateXAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                x_pos = (Float) animation.getAnimatedValue();
            }
        });

        translateYAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {                                //  height = y_pos + 250
                                                                                             // 35    170
                y_pos = (Float) animation.getAnimatedValue();
                Log.d("changevalueY", String.valueOf(cartoon.getBottom()));

                if ((y_pos + 100) >= cartoon.getTop() && (y_pos <= cartoon.getBottom())) {
          //          Log.d(("collide"+ i), String.valueOf(cartoon.getBottom()));


                    if ( (((x_point-35) < cartoon.getX()) && (x_point+35 > (cartoon.getX()))) || ((x_point-35 > (cartoon.getX())) && (x_point+35 < cartoon.getX()+ 200))||(((x_point-35)<(cartoon.getX()+200)&&((x_point + 35)>(cartoon.getX()+200))))){


                        Log.d("valuesare", (String.valueOf((x_point))+" and "+ String.valueOf(x_point-35)+" and "+ cartoon.getX()+" and "+ (cartoon.getX()+250)));


                        set.cancel();
                        showAlert("You lose! Better luck next time!! ");


                        remove=1;

                    }


                }
                if(y_pos > cartoon.getBottom()){

                    relativeLayout.removeView(an);

                }

            }


        });

    }
    public void  showAlert(String str) {


        CharSequence colors[] = new CharSequence[] {"Play Again", "High scrores","Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(gamecontainer.this);

        builder.setCancelable(false);

        builder.setTitle(str)
                .setItems(colors, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        switch ( id ){
                            case 0:{
                                startActivity(getIntent());
                                break;
                            }

                            case 1:{
                                Toast.makeText(getApplicationContext(),"hiscore",Toast.LENGTH_SHORT).show();
                                break;
                            }
                            case 2:{
                                finish();
                                break;
                            }
                        }
                    }
                });
        // Create the AlertDialog object and return it
        AlertDialog alert11 = builder.create();
        alert11.show();

    }
}
