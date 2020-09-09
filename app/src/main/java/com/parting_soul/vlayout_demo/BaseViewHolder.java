package com.parting_soul.vlayout_demo;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author parting_soul
 * @date 2020-09-09
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mSpareArray;

    public BaseViewHolder(Context context, int layoutId, ViewGroup parent) {
        super(LayoutInflater.from(context).inflate(layoutId, parent, false));
        mSpareArray = new SparseArray<>();
    }


    public BaseViewHolder setText(int id, String text) {
        TextView tv = getView(id);
        tv.setText(text);
        return this;
    }

    public BaseViewHolder setImageResource(int id, int resId) {
        ImageView iv = getView(id);
        iv.setImageResource(resId);
        return this;
    }

    public <T extends View> T getView(int id) {
        View view = mSpareArray.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            mSpareArray.put(id, view);
        }
        return (T) view;
    }

}
