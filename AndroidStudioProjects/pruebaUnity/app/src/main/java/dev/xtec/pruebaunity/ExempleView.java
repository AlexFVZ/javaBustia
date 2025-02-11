package dev.xtec.pruebaunity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

public class ExempleView extends View {
    public ExempleView(Context context){
        super(context);
    }

    public ExempleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        Paint pincel = new Paint();
        pincel.setColor(Color.BLUE);
        pincel.setStrokeWidth(8);
        pincel.setStyle(Paint.Style.STROKE);

        canvas.drawCircle(100,100,50,pincel);
    }
}
