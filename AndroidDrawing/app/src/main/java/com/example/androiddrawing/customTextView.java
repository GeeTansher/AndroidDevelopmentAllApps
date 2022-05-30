package com.example.androiddrawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class customTextView extends androidx.appcompat.widget.AppCompatTextView {
    private Paint paint;

    public customTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(120f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawText("Hello Everyone",
                getMeasuredWidth()/2,getMeasuredHeight()/2, paint);
        canvas.save();
        super.onDraw(canvas);
    }
}
