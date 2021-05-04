package com.lyecdevelopers.facetracker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class FaceTracker extends View {
    private int circleCol, labelCol;
    private String circleText;
    private Paint circlePaint;
    private Paint rectPaint;


    public FaceTracker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FaceTracker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setUpAttributes(attrs);
        setupPaint();
    }

    // Setup paint with color and stroke styles
    private void setupPaint() {
        //paint object circlePaint
        circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setAntiAlias(true);
        circlePaint.setStrokeWidth(10);
        circlePaint.setColor(circleCol);

        // paint object rectPaint
        rectPaint = new Paint();
        rectPaint.setStyle(Paint.Style.FILL);
        rectPaint.setAntiAlias(true);
        rectPaint.setStrokeWidth(10);
        rectPaint.setColor(circleCol);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int viewWidthHalf = this.getMeasuredWidth() / 2;
        int viewHeightHalf = this.getMeasuredHeight() / 2;

        int radius = 0;
        if (viewWidthHalf > viewHeightHalf)
            radius = viewHeightHalf - 10;
        else
            radius = viewWidthHalf - 10;

        // draw circle
        canvas.drawCircle(viewWidthHalf, viewHeightHalf, radius, circlePaint);


    }

    private void setUpAttributes(AttributeSet attrs) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.FaceTracker, 0, 0);
        try {
            circleText = a.getString(R.styleable.FaceTracker_circleLabel);
            circleCol = a.getInteger(R.styleable.FaceTracker_circleColor, 0);//0 is default
            labelCol = a.getInteger(R.styleable.FaceTracker_labelColor, 0);
        } finally {
            a.recycle();
        }

    }

    // these are the view getters
    public int getCircleColor() {
        return circleCol;
    }

    // these are the view setters
    public void setCircleColor(int newColor) {
        //update the instance variable
        circleCol = newColor;
        //redraw the view
        invalidate();
        requestLayout();
    }

    public int getLabelColor() {
        return labelCol;
    }

    public void setLabelColor(int newColor) {
        //update the instance variable
        labelCol = newColor;
        //redraw the view
        invalidate();
        requestLayout();
    }

    public String getLabelText() {
        return circleText;
    }
}
