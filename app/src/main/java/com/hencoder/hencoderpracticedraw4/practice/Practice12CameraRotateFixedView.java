package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice12CameraRotateFixedView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);
    Camera mCamera = new Camera();
    Matrix mMatrix = new Matrix();

    public Practice12CameraRotateFixedView(Context context) {
        super(context);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();

        mCamera.save();
        mCamera.rotateX(30);
        int pX1 = bitmapWidth / 2 + point1.x;
        int pY1 = bitmapHeight / 2 + point1.y;
        mCamera.getMatrix(mMatrix);
        mMatrix.preTranslate(-pX1, -pY1);
        mMatrix.postTranslate(pX1, pY1);
        mCamera.restore();
        canvas.save();
        canvas.concat(mMatrix);
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();

        int pX2 = bitmapWidth / 2 + point2.x;
        int pY2 = bitmapHeight / 2 + point2.y;
        mMatrix.reset();
        mCamera.rotateY(30);
        mCamera.getMatrix(mMatrix);
        mMatrix.preTranslate(-pX2, -pY2);
        mMatrix.postTranslate(pX2, pY2);
        mCamera.restore();
        canvas.save();
        canvas.concat(mMatrix);
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();
    }
}
