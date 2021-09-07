package com.bigkoo.pickerview.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.Log;

public class DensityUtil {
    private static final String TAG = "DensityUtil";

    /**
     * 将px值转换为sp值，保证文字大小不变
     */
    public static float px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return pxValue / fontScale + 0.5f;
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    public static float sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return spValue * fontScale + 0.5f;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static float dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return dpValue * scale + 0.5f;
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static float px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return pxValue / scale + 0.5f;
    }

    public static int getScreenWidth(Context mContext) {
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        return width;
    }

    public static int getScreenHeight(Context mContext) {
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        int height = dm.heightPixels;
        return height;
    }

    public static int getScaledValueX(Context context, int num) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        Configuration mConfiguration = context.getResources().getConfiguration();
        int ori = mConfiguration.orientation;
        if (ori == 2) {
            screenWidth = dm.heightPixels;
        } else if (ori == 1) {
            screenWidth = dm.widthPixels;
        }

        float scaleX = (float) ((double) ((float) screenWidth) / 320.0D);
        float numtemp = scaleX * (float) num;
        return (int) numtemp;
    }

    public static int defaultToScreen750(Context context, int value) {
        double resultD = (double) (value * 320) / 750.0D;
        long resultL = Math.round(resultD);
        return getScaledValueX(context, (int) resultL);
    }

    public static int getScaledValueHeight(int srcW, int srcH, int targetW) {
        float scale = (float) srcH * 1.0F / (float) srcW;
        return (int) (scale * (float) targetW);
    }

    public static int getScaledValueWidth(int srcW, int srcH, int targetH) {
        float scale = (float) srcH * 1.0F / (float) srcW;
        return (int) ((float) targetH / scale);
    }

    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            Log.e(TAG, "", e);
        }
        return statusHeight;
    }
}
