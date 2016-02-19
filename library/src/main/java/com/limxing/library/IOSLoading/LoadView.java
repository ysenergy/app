package com.limxing.library.IOSLoading;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.limxing.library.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by limxing on 16/1/7.
 */
public class LoadView extends ImageView {
    private int[] degrees = {0};
    private Matrix max;
    private int width;
    private int height;
    private Bitmap bitmap;

    public LoadView(Context context) {
        super(context);
        init();
    }

    public LoadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            degrees[0] += 30;
            max.setRotate(degrees[0], width, height);
            setImageMatrix(max);
        }
    };

    private void init() {
        setScaleType(ScaleType.MATRIX);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.loading_00);
        setImageBitmap(bitmap);
        max = new Matrix();

        width = bitmap.getWidth() / 2;
        height = bitmap.getHeight() / 2;
        Timer time=new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        },0,80);
    }


}
