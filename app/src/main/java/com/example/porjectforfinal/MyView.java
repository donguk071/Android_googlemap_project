package com.example.porjectforfinal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Location;
import android.view.View;

public class MyView extends View {
    MainActivity a = new MainActivity();
    Location b  = a.location;

    public MyView(Context context) {
        super(context);
        setBackgroundColor(Color.YELLOW);
    }

    protected void onDraw(Canvas canvas){
        Paint paint = new Paint();
        paint.setTextSize(50);
        canvas.drawCircle((float) b.getLatitude(),(float) b.getAltitude(),30,paint);
        //canvas.drawText(b.getLatitude()+","+ b.getAltitude());
    }

}
