package com.lyecdevelopers.facetracker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class FaceTracker extends View {
    private int circleCol, labelCol;
    private String circleText;
    private Paint circlePaint;
    private Paint bluePaint;
    private Paint greenPaint;
    private Paint redPaint;
    private Paint eraser;
    private Paint textPaint;
    private Paint semiCirclePaint;
    private RectF rectangle;
    private Float margin;

    private Paint paint;


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

        // eraser
        eraser = new Paint();
        eraser.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        eraser.setAntiAlias(true);


        //paint object circlePaint
        circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setAntiAlias(true);
        circlePaint.setStrokeWidth(10);
        circlePaint.setColor(circleCol);


        // paint object bluePaint
        bluePaint = new Paint();
        bluePaint.setColor(Color.rgb(0, 0, 255));
        bluePaint.setAntiAlias(true);
        bluePaint.setStrokeWidth(10);
        bluePaint.setStyle(Paint.Style.STROKE);
        bluePaint.setColor(circleCol);


        // paint for green
        greenPaint = new Paint();
        greenPaint.setColor(Color.rgb(0, 255, 0));
        greenPaint.setAntiAlias(true);
        greenPaint.setStyle(Paint.Style.STROKE);
        greenPaint.setStrokeWidth(10);
        greenPaint.setColor(circleCol);


        // paint for red
        redPaint = new Paint();
        redPaint.setColor(Color.rgb(255, 0, 0));
        redPaint.setAntiAlias(true);
        redPaint.setStyle(Paint.Style.STROKE);
        redPaint.setStrokeWidth(10);
        redPaint.setColor(circleCol);

        // paint for text
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(circleCol);
        textPaint.setTextSize(50);

        // semi circle paint
        semiCirclePaint = new Paint();
        semiCirclePaint.setAntiAlias(true);
        semiCirclePaint.setColor(Color.RED);
        semiCirclePaint.setStyle(Paint.Style.STROKE);
        semiCirclePaint.setStrokeWidth(10);


        margin = 10f;


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
        canvas.drawCircle(viewWidthHalf, viewHeightHalf, 350, circlePaint);

        // body circle
        canvas.drawCircle(getWidth() * .5f, getHeight() * .96f, 550, bluePaint);

        // left ear
        //canvas.drawCircle(getWidth() * .18f, getHeight() * .5f, 100, greenPaint);

        // right ear
        //canvas.drawCircle(getWidth() * .82f, getHeight() * .5f, 100, redPaint);

        // text
        canvas.drawText(circleText, getWidth() * .45f, getHeight() * .4f, textPaint);


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

    public void setLabelText(String newText) {
        //update the instance variable
        circleText = newText;
        //redraw the view
        invalidate();
        requestLayout();
    }

    public String getLabelText() {
        return circleText;
    }
}
