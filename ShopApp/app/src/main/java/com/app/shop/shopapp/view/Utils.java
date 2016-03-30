package com.app.shop.shopapp.view;

import android.content.Context;

/**
 */
class Utils {
    public static int dip2px(Context ctx,float dpValue) {
        float scale = ctx.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5F);
    }

    public static int px2dip(Context ctx,float pxValue) {
        float scale = ctx.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5F);
    }

}
