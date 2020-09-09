package com.parting_soul.vlayout_demo;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author parting_soul
 * @date 2020-09-09
 */
public class TaoBaoHomeActivity extends AppCompatActivity {
    private RecyclerView mRv;
    private List<MenuItem> menuItems;

    {
        menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("天猫", R.mipmap.ic_tian_mao));
        menuItems.add(new MenuItem("聚划算", R.mipmap.ic_ju_hua_suan));
        menuItems.add(new MenuItem("外卖", R.mipmap.ic_waimai));
        menuItems.add(new MenuItem("天猫国际", R.mipmap.ic_tian_mao_guoji));
        menuItems.add(new MenuItem("天猫超市", R.mipmap.ic_chaoshi));
        menuItems.add(new MenuItem("充值中心", R.mipmap.ic_voucher_center));
        menuItems.add(new MenuItem("飞猪旅行", R.mipmap.ic_travel));
        menuItems.add(new MenuItem("领金币", R.mipmap.ic_tao_gold));
        menuItems.add(new MenuItem("拍卖", R.mipmap.ic_auction));
        menuItems.add(new MenuItem("分类", R.mipmap.ic_classify));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_taobao_home);

        initRecyclerView();
    }

    private void initRecyclerView() {
        mRv = findViewById(R.id.rv);

        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        DelegateAdapter delegateAdapter = new DelegateAdapter(manager);
        mRv.setLayoutManager(manager);

        BaseSubAdapter bannerAdapter = new BaseSubAdapter(this, new SingleLayoutHelper(),
                R.layout.adapter_banner, 1) {
            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
                Banner banner = holder.getView(R.id.banner);

                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add("http://img.partingsoul.cn/image-20200629075038537.png");
                arrayList.add("http://img.partingsoul.cn/image-20200629074440429.png");
                arrayList.add("http://img.partingsoul.cn/image-20200629075339478.png");


                banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                banner.setImageLoader(new GlideImageLoader());
                banner.setImages(arrayList);
                banner.setBannerAnimation(Transformer.DepthPage);
                banner.isAutoPlay(true);
                banner.setDelayTime(3000);
                banner.setIndicatorGravity(BannerConfig.CENTER);
                banner.start();
            }
        };
        delegateAdapter.addAdapter(bannerAdapter);


        // 菜单
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(5);
        gridLayoutHelper.setMarginTop(30);
        gridLayoutHelper.setVGap(20);
        BaseSubAdapter menuAdapter = new BaseSubAdapter(this, gridLayoutHelper,
                R.layout.adapter_taobao_menu, menuItems.size()) {
            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
                MenuItem menuItem = menuItems.get(position);
                holder.setText(R.id.tv_menu_title_home, menuItem.title)
                        .setImageResource(R.id.iv_menu_home, menuItem.imgResId);
            }
        };
        delegateAdapter.addAdapter(menuAdapter);

        // 新闻
        BaseSubAdapter newsAdapter = new BaseSubAdapter(this, new SingleLayoutHelper(),
                R.layout.adapter_marqueeview, 1) {
            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
                MarqueeView marqueeView1 = holder.getView(R.id.marqueeview1);
                MarqueeView marqueeView2 = holder.getView(R.id.marqueeview2);

                marqueeView1.startWithList(Arrays.asList("新闻1", "新闻2"),
                        R.anim.anim_bottom_in, R.anim.anim_top_out);
                marqueeView2.startWithList(Arrays.asList("xxx新闻1", "xxxx新闻2"),
                        R.anim.anim_bottom_in, R.anim.anim_top_out);
            }
        };
        delegateAdapter.addAdapter(newsAdapter);

        // 图片
        BaseSubAdapter imagePlaceAdapter1 = new BaseSubAdapter(this, new SingleLayoutHelper(),
                R.layout.adapter_image, 1) {
            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {

            }
        };
        delegateAdapter.addAdapter(imagePlaceAdapter1);


        // 一拖三
        final List<Integer> images = Arrays.asList(
                R.mipmap.home1,
                R.mipmap.home2,
                R.mipmap.home4
        );
        BaseSubAdapter imagePlaceAdapter2 = new BaseSubAdapter(this, new OnePlusNLayoutHelper(),
                R.layout.adapter_image, images.size()) {
            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
                ImageView iv = holder.getView(R.id.iv);
                iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
                iv.setImageResource(images.get(position));
                iv.setBackgroundColor(Color.WHITE);
            }
        };
        delegateAdapter.addAdapter(imagePlaceAdapter2);


        // 内容
        GridLayoutHelper contentHepler = new GridLayoutHelper(2);
        contentHepler.setVGap(20);
        contentHepler.setHGap(20);
        contentHepler.setMarginLeft(30);
        contentHepler.setMarginRight(30);
        BaseSubAdapter contentAdapter = new BaseSubAdapter(this, contentHepler,
                R.layout.adapter_taobao_content, 30) {
            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
                holder.setImageResource(R.id.iv, R.mipmap.ic_recipe1);
            }
        };
        delegateAdapter.addAdapter(contentAdapter);

        mRv.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRv.setAdapter(delegateAdapter);
    }

    static class MenuItem {
        String title;
        int imgResId;

        public MenuItem(String title, int imgResId) {
            this.title = title;
            this.imgResId = imgResId;
        }
    }

}
