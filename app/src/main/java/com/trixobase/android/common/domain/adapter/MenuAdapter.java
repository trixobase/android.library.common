package com.trixobase.android.common.domain.adapter;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.trixobase.android.common.R;
import com.trixobase.android.common.constants.BaseName;

import java.util.ArrayList;
import java.util.List;

import com.trixobase.android.common.manager.Response;

/*
 * Powered by Trixobase Enterprise on 18/11/20.
 * updated on 07/03/21.
 */

public class MenuAdapter extends BaseAdapter {

    private class ViewHolder {
        LinearLayout ll_title;
        TextView tv_title;
        LinearLayout iv_menu;
    }

    private final Context context;
    private final List<ContentValues> objects;
    private final Response.onFrontResult action;
    private final int backgroundTitle;

    public MenuAdapter(Context context, int drawable, Response.onFrontResult action) {
        this.context = context;
        this.backgroundTitle = drawable;
        this.action = action;
        this.objects = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public ContentValues getItem(int i) {
        return objects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return objects.indexOf(objects.get(i));
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder;
        if (itemView == null) {
            holder = new ViewHolder();
            itemView = inflater.inflate(R.layout.listview_menu_item, null);

            holder.ll_title = itemView.getRootView().findViewById(R.id.ll_opaque);
            holder.tv_title = itemView.getRootView().findViewById(R.id.iv_title_id);
            holder.iv_menu = itemView.getRootView().findViewById(R.id.iv_picture_id);

            itemView.setTag(holder);
        } else holder = (ViewHolder) itemView.getTag();

        final ContentValues objectToDisplay = objects.get(position);

        holder.tv_title.setText(objectToDisplay.getAsString(BaseName.TITLE));
        holder.ll_title.setBackgroundResource(backgroundTitle);
        holder.iv_menu.setBackgroundResource(objectToDisplay.getAsInteger(BaseName.PICTURE));
        holder.iv_menu.setOnClickListener(view ->
                action.onMessage(objectToDisplay.getAsString(BaseName.TYPE)));
        return itemView;
    }

    public void add(ContentValues object) {
        objects.add(object);
        notifyDataSetChanged();
    }

    public void clear() {
        objects.clear();
        notifyDataSetChanged();
    }

}
