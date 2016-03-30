package com.app.shop.shopapp.utils;

import android.content.Context;

import com.app.shop.shopapp.App;
import com.app.shop.shopapp.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * <请描述这个类是干什么的>
 *
 * @author lwli
 * @data: 2016/3/23 20:13
 * @version: V1.0
 */
public class TimeUtil {
    public static String getDistanceTime(String time) {
        Context context = App.getInstance();
        long day = 0;// 天数
        long hour = 0;// 小时
        long min = 0;// 分钟
        long sec = 0;// 秒
        long time_date = Long.parseLong(time) * 1000l;
        long current_time = System.currentTimeMillis();
        long diff = current_time - time_date;
        day = current_time / (24 * 60 * 60 * 1000) - time_date
                / (24 * 60 * 60 * 1000);
        hour = (diff / (60 * 60 * 1000));
        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String rs = "";
        if (min == 0) {
            rs = sec + context.getResources().getString(R.string.seconds_ago);
            return rs;
        }
        if (hour == 0) {
            rs = min + context.getResources().getString(R.string.minute_ago);
            return rs;
        }
        if (day == 0) {
            if (hour <= 4) {
                rs = hour + context.getResources().getString(R.string.hour_ago);

                return rs;
            } else {
                DateFormat df2 = new SimpleDateFormat("HH:mm");
                rs = context.getResources().getString(R.string.today)
                        + df2.format(time_date);
                return rs;
            }
        } else if (day == 1) {
            DateFormat df2 = new SimpleDateFormat("HH:mm");
            rs = context.getResources().getString(R.string.yesterday)
                    + df2.format(time_date);
            return rs;
        } else {
            DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            rs = df2.format(time_date);
            return rs;
        }
    }
}
