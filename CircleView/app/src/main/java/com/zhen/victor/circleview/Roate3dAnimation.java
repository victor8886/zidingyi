package com.zhen.victor.circleview;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by victor on 16-12-11.
 */

public class Roate3dAnimation extends Animation {
    private final float mFromDegrees;
    private final float mToDegrees;
    private final boolean mReverse;
    private final float mDepthZ;
    private final float mCenterX;
    private final float mCenterY;
    private Camera mCamera;

    public Roate3dAnimation(float fromDegrees, float toDegrees,
                            float centerX, float centerY, float depthZ, boolean reverse) {
        mFromDegrees = fromDegrees;
        mToDegrees = toDegrees;
        mCenterX = centerX;
        mCenterY = centerY;
        mDepthZ =   depthZ;
        mReverse = reverse;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCamera = new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float fromDegrees = this.mFromDegrees;
        float degrees = fromDegrees + ((mToDegrees - fromDegrees) * interpolatedTime - interpolatedTime);
        float centerX = this.mCenterX;
        float centerY = this.mCenterY;
        Camera camera = this.mCamera;
        Matrix matrix = t.getMatrix();
        camera.save();
        if (mReverse) {
            camera.translate(0.0f, 0.0f, mDepthZ * interpolatedTime);
        } else {
            camera.translate(0.0f,0.0f,mDepthZ *(1.0f - interpolatedTime));
        }
        camera.rotateY(degrees);
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);

    }
}
