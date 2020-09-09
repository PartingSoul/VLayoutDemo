package com.parting_soul.vlayout_demo;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;

/**
 * @author parting_soul
 * @date 2020-09-09
 */
public abstract class BaseSubAdapter extends DelegateAdapter.Adapter<BaseViewHolder> {
    private LayoutHelper mLayoutHelper;
    protected Context context;
    private int layoutId;
    private int itemCount;


    public BaseSubAdapter(Context context, LayoutHelper helper, @LayoutRes int layoutId, int itemCount) {
        this.context = context;
        this.mLayoutHelper = helper;
        this.layoutId = layoutId;
        this.itemCount = itemCount;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseViewHolder(context, layoutId, parent);
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }
}
