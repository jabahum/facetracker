package com.lyecdevelopers.facetracker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class MyView extends View {

    private Paint paint;
    private Path path;

    public MyView(Context context) {
        super(context);
        init();
    }

    public void init() {

        path = new Path();

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.TRANSPARENT);
        paint.setShader(null);

        float width = getWidth();
        float height = getHeight();

        path.moveTo(0, 0);

        path.lineTo(0, height);

        path.lineTo(width, height);

        path.lineTo(width, 0);

        path.cubicTo(4 * width / 6, 3 * height / 4, 2 * width / 6, 3 * height / 4, 0, 0);

        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, paint);

    }

}