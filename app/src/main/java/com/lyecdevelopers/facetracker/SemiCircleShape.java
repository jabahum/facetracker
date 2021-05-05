package com.lyecdevelopers.facetracker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class SemiCircleShape extends View {

    private final Paint paint = new Paint();
    private final Paint backgroundPaint = new Paint();
    private final Float arcProportion = 0f;
    private RectF rectangle;
    private Float margin;

    public SemiCircleShape(Context context) {
        super(context);
        setUpPaint();
    }

    public SemiCircleShape(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpPaint();
    }

    private void setUpPaint() {
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        margin = 10f;
        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setColor(Color.BLACK);
        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setStrokeWidth(10);
        margin = 10f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (rectangle == null) {
            rectangle = new RectF(0f + margin, 0f + margin, getWidth() - margin, getHeight() - margin);
        }
        canvas.drawArc(rectangle, 90f, 180f, false, paint);

        // This 2nd arc completes the circle. Remove it if you don't want it
        canvas.drawArc(rectangle, -90f + arcProportion * 360, (1 - arcProportion) * 360, false, backgroundPaint);
    }
}
