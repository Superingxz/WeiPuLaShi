package com.xolo.weipulashi.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xolo.weipulashi.R;
import com.xolo.weipulashi.configuration.IntentConfig;
import com.xolo.weipulashi.ui.activity.AddressManagerActivity;

import static com.xolo.weipulashi.R.id.fl_contanin;
import static com.xolo.weipulashi.R.id.tv;

/**
 * Created by Administrator on 2017/2/6.
 */

public class PrizeDialog extends BaseDialog {
    private String imgPath;
    private String name;
    private double integralValue;

    private TextView tvaddress;
    private String address;

    private int qty = 1;

    public PrizeDialog(Context context, String imgPath, String name, String integralValue, View.OnClickListener  listener) {
        super(context,"兑换奖品",listener);
        this.imgPath = imgPath;
        this.name = name;
        this.integralValue = Double.parseDouble(integralValue);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_prize, null);
        addContain(inflate);

        ImageView iv = (ImageView) inflate.findViewById(R.id.iv);
        final TextView tvQty = (TextView) inflate.findViewById(R.id.tv_qty);
        View btnAdd = inflate.findViewById(R.id.btn_add);
        View btnReduce = inflate.findViewById(R.id.btn_reduce);
        TextView tvProduceName = (TextView) inflate.findViewById(R.id.tv_ProductName);
        final TextView tvJf = (TextView) inflate.findViewById(R.id.tv_jf);
         tvaddress = (TextView) inflate.findViewById(R.id.tv_address);

        Picasso.with(mContext).load(imgPath).into(iv);
        tvProduceName.setText(name);
        tvJf.setText(integralValue+"积分");

        tvaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(mContext, AddressManagerActivity.class);
                    intent.putExtra(IntentConfig.MENBER_ID,"3932");

                    ((Activity) mContext). startActivityForResult(intent,99);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty++;
                tvQty.setText(qty+"");
                tvJf.setText(integralValue*qty+"积分");
            }
        });
        btnReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qty > 1){
                    qty--;
                    tvQty.setText(qty+"");
                    tvJf.setText(integralValue*qty+"积分");
                }
            }
        });
    }

    public int getQty() {
        return qty;
    }

    public void setAddress(String address) {
        this.address = address;
        tvaddress.setText(address);
    }

    public String getAddress() {
        return address;
    }
}
