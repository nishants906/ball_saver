package com.example.nishant.savage;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
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
    int c_x;

    Handler h;
    float x_point;
    int radius=50;

    public gamewindow(Context context, float x_point) {
        super(context);

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

        this.x_point=x_point;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas_width = canvas.getWidth();
        canvas_height = canvas.getHeight();


        canvas.drawCircle(x_point,c_y+50,radius,painto);


    }


}
