package com.parting_soul.vlayout_demo;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;

/**
 * 菜谱首页
 *
 * @author parting_soul
 * @date 2020-09-09
 */
public class RecipeHomeActivity extends AppCompatActivity {
    private RecyclerView mRv;
    private DelegateAdapter mDelegateAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_recipe_home);
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRv = findViewById(R.id.rv);

        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        // 相同类型item若位于不同的子适配器，公用一个type
        mDelegateAdapter = new DelegateAdapter(layoutManager, false);
        mRv.setLayoutManager(layoutManager);

        // 菜单
        BaseSubAdapter menuAdapter = new BaseSubAdapter(this, new SingleLayoutHelper(),
                R.layout.adapter_menu, 1) {
            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
            }

        };
        mDelegateAdapter.addAdapter(menuAdapter);

        // 标题
        TitleSubAdapter titleSubAdapter = new TitleSubAdapter(this, "热门菜谱");
        mDelegateAdapter.addAdapter(titleSubAdapter);

        // 热门菜谱
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2);
        gridLayoutHelper.setPaddingLeft(dp2px(this, 20));
        gridLayoutHelper.setPaddingRight(dp2px(this, 20));
        gridLayoutHelper.setPaddingTop(dp2px(this, 8));
        gridLayoutHelper.setHGap(dp2px(this, 15));
        gridLayoutHelper.setVGap(dp2px(this, 24));
        BaseSubAdapter hotRecipeAdapter = new BaseSubAdapter(this, gridLayoutHelper,
                R.layout.adapter_home_hot_recipe, 4) {
            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
                ImageView ivCover = holder.getView(R.id.iv_img);
                ivCover.setImageResource(R.mipmap.ic_recipe1);
            }
        };
        mDelegateAdapter.addAdapter(hotRecipeAdapter);

        // 精选专辑
        TitleSubAdapter albumTitleSubAdapter = new TitleSubAdapter(this, "精选专辑");
        mDelegateAdapter.addAdapter(albumTitleSubAdapter);

        BaseSubAdapter albumSubAdapter = new BaseSubAdapter(this, new LinearLayoutHelper(),
                R.layout.adapter_home_choiceness_album, 20) {
            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
                ImageView ivCover = holder.getView(R.id.iv_img);
                ivCover.setImageResource(R.mipmap.ic_recipe1);
            }
        };
        mDelegateAdapter.addAdapter(albumSubAdapter);

        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        mRv.setRecycledViewPool(recycledViewPool);

        mRv.setAdapter(mDelegateAdapter);

    }


    static class TitleSubAdapter extends BaseSubAdapter {
        private String title;

        public TitleSubAdapter(Context context, String title) {
            super(context, new SingleLayoutHelper(), R.layout.adapter_title, 1);
            this.title = title;
        }

        @Override
        public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
            holder.setText(R.id.tv_title, title);
        }

    }


    /**
     * dip转px
     */
    public static int dp2px(Context context, int dp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics == null ? 0 : (int) (metrics.density * dp + 0.5f);
    }

}
