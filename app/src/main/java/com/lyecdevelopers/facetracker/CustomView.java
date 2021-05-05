package com.lyecdevelopers.facetracker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View {
    public CustomView(Context context, AttributeSet attr) {
        super(context);
    }

    @Override
    public void onDraw(Canvas canvas) {

        canvas.drawColor(Color.rgb(32, 32, 32));

        Paint redPaint = new Paint();
        redPaint.setColor(Color.rgb(255, 0, 0));

        Paint greenPaint = new Paint();
        greenPaint.setColor(Color.rgb(0, 255, 0));

        Paint bluePaint = new Paint();
        bluePaint.setColor(Color.rgb(0, 0, 255));

        canvas.drawCircle(getWidth() * .75f, getHeight() * .2f, 200, redPaint);
        canvas.drawCircle(getWidth() * .25f, getHeight() * .3f, 300, greenPaint);
        canvas.drawCircle(getWidth() * .5f, getHeight() * .70f, 400, bluePaint);
    }
}