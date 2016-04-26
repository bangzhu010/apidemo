package com.haihong.testdemo.view.errorview;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by admin on 2016/2/29.
 */
public class ShakeAnimation extends Animation {

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        t.getMatrix().setTranslate((float)Math.sin(interpolatedTime*50)*20,0);
        super.applyTransformation(interpolatedTime, t);
    }
}
