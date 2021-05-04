package com.lyecdevelopers.facetracker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class ShapeSelectorView extends View {

    private final int shapeWidth = 250;
    private final int shapeHeight = 250;
    private final int textYOffset = 30;
    private final String[] shapeValues = {"square", "circle", "triangle"};
    private final Path trianglePath;
    private int textXOffset = 0;
    private Paint paintShape;
    private int shapeColor;
    private boolean displayShapeName;
    private int currentShapeIndex = 0;


    public ShapeSelectorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setupAttributes(attrs);
        setupPaint();

        trianglePath = new Path();

    }

    @Override
    public Parcelable onSaveInstanceState() {
        // Construct bundle
        Bundle bundle = new Bundle();
        // Store base view state
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        // Save our custom view state to bundle
        bundle.putInt("currentShapeIndex", this.currentShapeIndex);
        // ... store any other custom state here ...
        // Return the bundle
        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        // Checks if the state is the bundle we saved
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            // Load back our custom view state
            this.currentShapeIndex = bundle.getInt("currentShapeIndex");
            // ... load any other custom state here ...
            // Load base view state back
            state = bundle.getParcelable("instanceState");
        }
        // Pass base view state on to super
        super.onRestoreInstanceState(state);
    }

    private void setupAttributes(AttributeSet attrs) {
        // Obtain a typed array of attributes
        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.ShapeSelectorView, 0, 0);
        // Extract custom attributes into member variables
        try {
            shapeColor = a.getColor(R.styleable.ShapeSelectorView_shapeColor, Color.RED);
            displayShapeName = a.getBoolean(R.styleable.ShapeSelectorView_displayShapeName, false);
        } finally {
            // TypedArray objects are shared and must be recycled.
            a.recycle();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            currentShapeIndex = (currentShapeIndex++) % shapeValues.length;
            postInvalidate();
            return true;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String shapeSelected = shapeValues[currentShapeIndex];
        if (shapeSelected.equals("square")) {
            canvas.drawRect(0, 0, shapeWidth, shapeHeight, paintShape);
            textXOffset = 0;
        } else if (shapeSelected.equals("circle")) {
            canvas.drawCircle(shapeWidth / 2, shapeHeight / 2, shapeWidth / 2, paintShape);
            textXOffset = 12;
        } else if (shapeSelected.equals("triangle")) {
            canvas.drawPath(getTrianglePath(), paintShape);
            textXOffset = 0;
        }
        if (displayShapeName) {
            canvas.drawText(shapeSelected, 0 + textXOffset, shapeHeight + textYOffset, paintShape);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // Defines the extra padding for the shape name text
        int textPadding = 10;
        int contentWidth = shapeWidth;

        // Resolve the width based on our minimum and the measure spec
        int minw = contentWidth + getPaddingLeft() + getPaddingRight();
        int w = resolveSizeAndState(minw, widthMeasureSpec, 0);

        // Ask for a height that would let the view get as big as it can
        int minh = shapeHeight + getPaddingBottom() + getPaddingTop();
        if (displayShapeName) {
            minh += textYOffset + textPadding;
        }
        int h = resolveSizeAndState(minh, heightMeasureSpec, 0);

        // Calling this method determines the measured width and height
        // Retrieve with getMeasuredWidth or getMeasuredHeight methods later
        setMeasuredDimension(w, h);
    }

    private void setupPaint() {
        paintShape = new Paint();
        paintShape.setStyle(Paint.Style.FILL);
        paintShape.setColor(shapeColor);
        paintShape.setTextSize(30);
    }

    protected Path getTrianglePath() {
        trianglePath.moveTo(0, shapeHeight);
        trianglePath.lineTo(shapeWidth, shapeHeight);
        trianglePath.lineTo(shapeWidth / 2, 0);
        return trianglePath;
    }

    public boolean isDisplayingShapeName() {
        return displayShapeName;
    }

    public void setDisplayingShapeName(boolean state) {
        this.displayShapeName = state;
        invalidate();
        requestLayout();
    }

    public int getShapeColor() {
        return shapeColor;
    }

    public void setShapeColor(int color) {
        this.shapeColor = color;
        invalidate();
        requestLayout();
    }

    public String getSelectedShape() {
        return shapeValues[currentShapeIndex];
    }
}
