package com.xolo.weipulashi.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.bean.get.Prize;
import com.xolo.weipulashi.utils.DensityUtils;
import com.xolo.weipulashi.utils.ScreenUtils;
import com.xolo.weipulashi.utils.adapterUtils.CommenAdapter;
import com.xolo.weipulashi.utils.adapterUtils.ViewHolder;

/**
 * Created by Administrator on 2017/2/13.
 */

public class PrizeAdapter extends CommenAdapter<Prize> {

    private int measureHeigh;

    public PrizeAdapter(Context context) {
        super(context);
        measureHeigh = ScreenUtils.getScreenWidth(context) / 2 - DensityUtils.dp2px(context, 32);
    }

    @Override
    public void setView(ViewHolder holder, int position, Prize prize) {
        View view = holder.getView(R.id.iv);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = measureHeigh;
        view.setLayoutParams(layoutParams);

        holder.loadUrl(R.id.iv,prize.getImgPath());
                      holder.setText(R.id.tv_ProductName,prize.getPrizeName());
                      holder.setText(R.id.tv_IntegralValue,prize.getIntegralValue());
                      holder.setText(R.id.tv_LevelName,prize.getLevelName()+"积分：");
    }

    @Override
    public int getLayoutID() {
        return R.layout.item_prize;
    }



}
