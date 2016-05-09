package com.example.shakabreaux.corotanfinalproject;


import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

public class PinView extends SubsamplingScaleImageView {

    private PointF sPin;
    private Bitmap pin;
    private List<PointF> sPoints;
    private int strokeWidth;

    public PinView(Context context) {
        this(context, null);
    }

    public PinView(Context context, AttributeSet attr) {
        super(context, attr);
        pin = BitmapFactory.decodeResource(this.getResources(), R.drawable.pushpin_blue);
        initialise();
    }

    public void setPin(String lastname, PointF sPin) {
        this.sPin = sPin;
        switch (lastname){
            case "Ahmed":
                pin = BitmapFactory.decodeResource(this.getResources(), R.drawable.ahmed);
                break;
            case "Bover":
                pin = BitmapFactory.decodeResource(this.getResources(), R.drawable.bover);
                break;
            case "Clauson":
                pin = BitmapFactory.decodeResource(this.getResources(), R.drawable.clauson);
                break;
            case "Fizzano":
                pin = BitmapFactory.decodeResource(this.getResources(), R.drawable.fizzano);
                break;
            case "Hearne":
                pin = BitmapFactory.decodeResource(this.getResources(), R.drawable.hearne);
                break;
            case "Hutchinson":
                pin = BitmapFactory.decodeResource(this.getResources(), R.drawable.hutchinson);
                break;
            case "Jagodzinski":
                pin = BitmapFactory.decodeResource(this.getResources(), R.drawable.jagodzinski);
                break;
            case "Johnson":
                pin = BitmapFactory.decodeResource(this.getResources(), R.drawable.johnson);
                break;
            case "Kumaar":
                pin = BitmapFactory.decodeResource(this.getResources(), R.drawable.kumaar);
                break;
            case "Liu":
                pin = BitmapFactory.decodeResource(this.getResources(), R.drawable.liu);
                break;
            case "Matthews":
                pin = BitmapFactory.decodeResource(this.getResources(), R.drawable.matthews);
                break;
            case "Meehan":
                pin = BitmapFactory.decodeResource(this.getResources(), R.drawable.meehan);
                break;
            case "Nelson":
                pin = BitmapFactory.decodeResource(this.getResources(), R.drawable.nelson);
                break;
            case "Palzer":
                pin = BitmapFactory.decodeResource(this.getResources(), R.drawable.palzer);
                break;
            case "Potts":
                pin = BitmapFactory.decodeResource(this.getResources(), R.drawable.potts);
                break;
            case "Reedy":
                pin = BitmapFactory.decodeResource(this.getResources(), R.drawable.reedy);
                break;
            case "Rrushi":
                pin = BitmapFactory.decodeResource(this.getResources(), R.drawable.rrushi);
                break;
            case "Sharmin":
                pin = BitmapFactory.decodeResource(this.getResources(), R.drawable.sharmin);
                break;
            case "Zhang":
                pin = BitmapFactory.decodeResource(this.getResources(), R.drawable.zhang);
                break;
            default:
                pin = BitmapFactory.decodeResource(this.getResources(), R.drawable.ahmed);
        }
        initialise();
        invalidate();
    }

    public PointF getPin() {
        return sPin;
    }

    public void removePin(){
        sPin = null;
        invalidate();
    }

    private void initialise() {
        float density = getResources().getDisplayMetrics().densityDpi;
        //pin = BitmapFactory.decodeResource(this.getResources(), R.drawable.pushpin_blue);
        float w = (density/420f) * pin.getWidth();
        float h = (density/420f) * pin.getHeight();
        pin = Bitmap.createScaledBitmap(pin, (int)w, (int)h, true);
        strokeWidth = (int) (density / 60f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Don't draw pin before image is ready so it doesn't move around during setup.
        if (!isReady()) {
            return;
        }

        Paint paint = new Paint();
        paint.setAntiAlias(true);

        if (sPin != null && pin != null) {
            PointF vPin = sourceToViewCoord(sPin);
            float vX = vPin.x - (pin.getWidth()/2);
            float vY = vPin.y - pin.getHeight();
            canvas.drawBitmap(pin, vX, vY, paint);
        }

        Paint paint2 = new Paint();
        paint2.setAntiAlias(true);

        if (sPoints != null && sPoints.size() >= 2) {
            Path vPath = new Path();
            PointF vPrev = sourceToViewCoord(sPoints.get(0).x, sPoints.get(0).y);
            vPath.moveTo(vPrev.x, vPrev.y);
            for (int i = 1; i < sPoints.size(); i++) {
                PointF vPoint = sourceToViewCoord(sPoints.get(i).x, sPoints.get(i).y);
                vPath.quadTo(vPrev.x, vPrev.y, (vPoint.x + vPrev.x) / 2, (vPoint.y + vPrev.y) / 2);
                vPrev = vPoint;
            }
            paint2.setStyle(Paint.Style.STROKE);
            paint2.setStrokeCap(Paint.Cap.ROUND);
            paint2.setStrokeWidth(strokeWidth * 2);
            paint2.setColor(Color.BLACK);
            canvas.drawPath(vPath, paint);
            paint2.setStrokeWidth(strokeWidth);
            paint2.setColor(Color.argb(255, 51, 181, 229));
            canvas.drawPath(vPath, paint2);
        }
    }

    public void reset() {
        this.sPoints = null;
        invalidate();
    }

    public void drawPath(){
        sPoints = new ArrayList<PointF>();
        sPoints.add(new PointF(100,200));
        sPoints.add(new PointF(1000,2000));
        invalidate();
    }

}
