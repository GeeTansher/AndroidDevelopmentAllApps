package com.example.androiddrawing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.view.View;

import androidx.compose.ui.graphics.TileMode;


public class Drawing extends View {
    private Paint brush;
    private Paint redBrush;
    private LinearGradient linearGradient;
    private RadialGradient radialGradient;
    private SweepGradient sweepGradient;
    Bitmap bitmap;

    public Drawing(Context context) {
        super(context);
        init();
    }

    public void init(){
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.picture_anime);
        radialGradient = new RadialGradient(0,0,20,Color.GREEN,Color.BLUE,Shader.TileMode.MIRROR);
        linearGradient = new LinearGradient(0,0,200,200,Color.RED,Color.BLACK, Shader.TileMode.REPEAT);
        brush = new Paint(Paint.ANTI_ALIAS_FLAG);
        brush.setColor(Color.parseColor("green"));
//        brush.setShader(linearGradient);
        brush.setStrokeWidth(23f);
        brush.setShader(radialGradient);
        redBrush = new Paint(Paint.ANTI_ALIAS_FLAG);
        redBrush.setColor(Color.parseColor("red"));
        redBrush.setStrokeWidth(15f);

    }

    @Override
    protected void onDraw(Canvas canvas) {

//        canvas.drawCircle(getMeasuredWidth()/2,getMeasuredHeight()/2,98f,brush);
//        canvas.drawLine(0,0,getMeasuredWidth()/2,getMeasuredHeight()/2,redBrush);

//        canvas.drawBitmap(bitmap,10,10,null);
        canvas.drawBitmap(bitmap,(getMeasuredWidth()/2)-(bitmap.getWidth()/2),(getMeasuredHeight()/2)-(bitmap.getHeight()/2),null);
        canvas.save();
        super.onDraw(canvas);
    }
}
