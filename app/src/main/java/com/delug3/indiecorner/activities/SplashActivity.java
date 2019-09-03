package com.delug3.indiecorner.activities;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.os.Handler;
import com.delug3.indiecorner.R;

import butterknife.BindView;

public class SplashActivity extends AppCompatActivity {

    public ImageView container;
    public AnimationDrawable animationDrawable;

    //@BindView(R.id.app_icons)
    //ImageView mImageIcons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        container = findViewById(R.id.app_icons);
       // container = mImageIcons;
        container.setBackgroundResource(R.drawable.splash_animation);

        animationDrawable = (AnimationDrawable) container.getBackground();

       // Intent intent = new Intent(this, MainActivity.class);
       // startActivity(intent);

    }


    @Override
    protected void onResume() {
        super.onResume();

        animationDrawable.start();

        checkAnimationStatus(50, animationDrawable);


    }

    /**
     * check the animation status recursively, keep the animation until it reach the last frame. (2 images)
     *
     * @param time period of animation
     * @param animationDrawable animation list
    */

    private void checkAnimationStatus(final int time, final AnimationDrawable animationDrawable) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (animationDrawable.getCurrent() != animationDrawable.getFrame(animationDrawable.getNumberOfFrames() - 1))
                    checkAnimationStatus(time, animationDrawable);
                else
                {
                    finish();
                    Intent mainIntent = new Intent().setClass(SplashActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                }

            }
        }, time);

    }


}
