package com.example.nishant.savage;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.constraint.solver.widgets.Rectangle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Nishant on 23/06/17.
 */


public class gamewindow extends View { //you have to create a new java file and then insert the same file in the xml of the page in which you want the canvas
    Paint paint = new Paint();
    Paint paintx = new Paint();
    Paint painto = new Paint();
    Paint painto1 = new Paint();

    Timer t;

    Random rm;
    int canvas_width, canvas_height;
    int c_y;


    float cartoon_x,cartoon_y;

    float x_circle;
    int radius=50;

    Bitmap ball;
    int ball_x,ball_y;



    public gamewindow(Context runnable, float xPoint, float x, float y) {
        super( runnable);
        cartoon_x=x;
        cartoon_y=y;

        paint.setAntiAlias(true);
        paint.setStrokeWidth(10f);
        paint.setColor(Color.rgb(245, 125, 10));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);

        paintx.setStrokeWidth(10);
        paintx.setColor(Color.RED);
        paintx.setStyle(Paint.Style.FILL_AND_STROKE);

        painto.setColor(Color.rgb(10, 125, 245));
        painto.setStyle(Paint.Style.FILL);

        painto1.setColor(Color.rgb(255, 224, 178));
        painto1.setStyle(Paint.Style.FILL);
        rm = new Random();

        x_circle=xPoint;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas_width = canvas.getWidth();
        canvas_height = canvas.getHeight();

        ball = BitmapFactory.decodeResource(getResources(),R.drawable.ball);

         Bitmap resized = Bitmap.createScaledBitmap(ball,(int)(ball.getWidth()*0.25), (int)(ball.getHeight()*0.25), true);
        Log.d("canvasradius", String.valueOf(x_circle));

        canvas.drawBitmap(resized,0,c_y,null);

    }




}
