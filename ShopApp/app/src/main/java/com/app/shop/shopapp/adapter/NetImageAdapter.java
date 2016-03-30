package com.app.shop.shopapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.app.shop.shopapp.utils.UiUtil;

import java.util.Collection;

public class NetImageAdapter extends ArrayAdapter<String> implements View.OnClickListener{

    public NetImageAdapter(Context context) {
        super(context, 0);
    }

    public NetImageAdapter(Context context, String[] objects) {
        super(context, 0, objects);
    }

    public NetImageAdapter(Context context, Collection<String> objects) {
        super(context, 0, objects.toArray(new String[objects.size()]));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView draweeView = new ImageView(getContext());
        draweeView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        UiUtil.setImageBig(draweeView,getItem(position));
        draweeView.setTag(position);
        draweeView.setOnClickListener(this);
        return draweeView;
    }

    @Override
    public void onClick(View v) {
    }
}