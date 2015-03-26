package com.example.ogatafumitoshi.notificationlistenerservicedemo;

/**
 * Created by ogata.fumitoshi on 2015/03/20.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

import static android.graphics.PixelFormat.TRANSLUCENT;
import static android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED;
import static android.view.WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR;
import static android.view.WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
import static android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
import static android.view.WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
import static android.view.WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
import static android.view.WindowManager.LayoutParams.TYPE_TOAST;
import static android.view.WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
import static android.view.WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
import android.widget.ImageView;
import android.widget.LinearLayout;

/*
 *  OverlayView
 */
@SuppressLint("ViewConstructor")
final class OverlayView extends FrameLayout {

    private Context ctx;

    static OverlayView create(Context context, Listener listener) {
        return new OverlayView(context, listener);
    }

    static WindowManager.LayoutParams createLayoutParams(Context context) {
        Resources res = context.getResources();
        //画面の上部に表示
        int width  = 2000;
        int height = 2000;
        final WindowManager.LayoutParams params =
                new WindowManager.LayoutParams(
                        width, height,
                        TYPE_SYSTEM_ALERT,
                        FLAG_NOT_FOCUSABLE
                                | FLAG_NOT_TOUCHABLE
                                | FLAG_LAYOUT_NO_LIMITS
                                | FLAG_LAYOUT_INSET_DECOR
                                | FLAG_LAYOUT_IN_SCREEN
                                | FLAG_HARDWARE_ACCELERATED,
                        TRANSLUCENT
                );
        params.gravity = Gravity.LEFT | Gravity.TOP;
        return params;
    }

    interface Listener {
    }

    private final Listener listener;

    private OverlayView(Context context, Listener listener) {
        super(context);
        this.listener = listener;
        this.ctx = context;
    }

    public void addComment(String txt){
        //add view
        TextView comment = new TextView(ctx);
        comment.setText(txt);
        comment.setLayoutParams(new LayoutParams(1000, 1000));
        comment.setTextSize(18);
        comment.setTextColor(Color.RED);
        this.addView(comment);

        //Animation
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f,0.0f,2000.0f);
        translateAnimation.setDuration(10000);
        translateAnimation.setFillAfter(true);
        comment.startAnimation(translateAnimation);
    }

    public void addStamp(){
        //add view
        ImageView stampImg = new ImageView(ctx);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(1000,1000);
        stampImg.setLayoutParams(lp);
        stampImg.setImageResource(R.drawable.cat_photo);
        this.addView(stampImg);

        //Animation
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f,0.0f,2000.0f);
        translateAnimation.setDuration(10000);
        translateAnimation.setFillAfter(true);
        stampImg.startAnimation(translateAnimation);
    }
    private LinearLayout.LayoutParams createParam(int w, int h){
        return new LinearLayout.LayoutParams(w, h);
    }
}