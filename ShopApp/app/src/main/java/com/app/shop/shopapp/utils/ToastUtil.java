package com.app.shop.shopapp.utils;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.shop.shopapp.App;


public class ToastUtil extends Toast {
	public ToastUtil(Context context) {
		super(context);
	}

	/**
	 * toast 提示消息
	 * 
	 * @param msg
	 * @return
	 * @return
	 */
	public static void showToast(String msg) {
		final Toast toast = Toast.makeText(App.getInstance(), msg,
			  Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setText(msg);
		toast.show();
	}

	public static void show(Context context, String info) {
		Toast.makeText(context, info, Toast.LENGTH_LONG).show();
	}

	public static void show(Context context, int info) {
		Toast.makeText(context, info, Toast.LENGTH_LONG).show();
	}
	/**
	 * 
	 * @param msg
	 *
	 */
	public static void showToastLong(String msg) {
		final Toast toast = Toast.makeText(App.getInstance(), msg,
			  Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setText(msg);
		toast.show();
	}
    /**
     *
     * @param id
     *
     */
    public static void showToastLong(int id) {
	  final Toast toast = Toast.makeText(App.getInstance(), id,
		    Toast.LENGTH_SHORT);
	  toast.setGravity(Gravity.CENTER, 0, 0);
	  toast.setText(id);
	  toast.show();
    }
	/**
	 * toast 提示消息
	 * 
	 * @return
	 * @return
	 */
	public static void showNotNetToast() {
		final Toast toast = Toast.makeText(App.getInstance(), "网络连接错误，请检查网络",
			  Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setDuration(100);
		toast.setText("网络连接错误，请检查网络");
		toast.show();
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				toast.cancel();
			}
		}, 700);
	}
	public static void showCtrlSuccessful() {
		Toast.makeText(App.getInstance(), "操作成功",
			  Toast.LENGTH_SHORT).show();
	}

	public static void showCtrlFailed() {
		Toast.makeText(App.getInstance(), "操作失败",
			  Toast.LENGTH_SHORT).show();
	}

	/**
	 * 
	 * @param msg
	 *            商品管理提示类，自定义Toast
	 */
	public static void showNotNetToast(String msg) {
		Context context =App.getInstance();
		final ToastUtil toast = new ToastUtil(context);
		LayoutInflater inflate = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		TextView tv = new TextView(context);
		tv.setMinWidth(dm.widthPixels);
		params.leftMargin = 5;
		params.rightMargin = 5;
		tv.setMinHeight(dm.heightPixels / 23);
		tv.setText(msg);
		tv.setGravity(Gravity.CENTER);
		tv.setTextColor(Color.WHITE);
		tv.getBackground().setAlpha(220);
		toast.setDuration(100);
		toast.setView(tv);
		toast.setGravity(Gravity.TOP, 0, (int) (dm.density * 85 + 10));
		toast.show();
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				toast.cancel();
			}
		}, 700);
	}

	/**
	 * 
	 * @param msg
	 *            商品管理提示类，自定义Toast
	 */
	public static void showNewDayToast(String msg) {
		Context context = App.getInstance();
		ToastUtil toast = new ToastUtil(context);
		LayoutInflater inflate = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		TextView tv = new TextView(context);
		tv.setMinWidth(dm.widthPixels);
		params.leftMargin = 5;
		params.rightMargin = 5;
		tv.setMinHeight(dm.heightPixels / 23);
		tv.setText(msg);
		tv.setGravity(Gravity.CENTER);
		tv.setTextColor(Color.WHITE);
		tv.getBackground().setAlpha(220);
		toast.setDuration(100);
		toast.setView(tv);
		toast.setGravity(Gravity.TOP, 0, (int) (dm.density * 85 + 10));
		toast.show();
	}
	/**
	 * 
	 * @param msg
	 *            商品管理提示类，自定义Toast
	 */
	public static void showMainToast(String msg,int height) {
		Context context =App.getInstance();
		ToastUtil toast = new ToastUtil(context);
		LayoutInflater inflate = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		TextView tv = new TextView(context);
		tv.setMinWidth(dm.widthPixels);
		params.leftMargin = 5;
		params.rightMargin = 5;
		tv.setMinHeight(dm.heightPixels / 23);
		tv.setText(msg);
		tv.setGravity(Gravity.CENTER);
		tv.setTextColor(Color.WHITE);
		tv.getBackground().setAlpha(220);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(tv);
		toast.setGravity(Gravity.BOTTOM, dm.widthPixels/4+(dm.widthPixels/4)/2, height);
		toast.show();
	}

}
