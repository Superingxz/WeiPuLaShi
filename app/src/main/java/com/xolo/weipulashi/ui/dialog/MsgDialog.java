package com.xolo.weipulashi.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.xolo.weipulashi.R;

/**
 * Created by Administrator on 2017/2/9.
 */

public class MsgDialog extends BaseDialog{
    private TextView tvMsg;

    private String msg;



    public MsgDialog(Context context, String title, View.OnClickListener  onClickListener) {
        super(context, title, onClickListener);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!TextUtils.isEmpty(msg)){
            tvMsg= new TextView(mContext);
            tvMsg.setTextColor(ContextCompat.getColor(mContext,R.color.red));
            tvMsg.setPadding(30,30,30,30);

            addContain(tvMsg);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!TextUtils.isEmpty(msg)){
            tvMsg.setText(msg);
        }
    }

    public String getMsg() {
        return msg;
    }

    public Dialog setMsg(String msg) {
        this.msg = msg;
        return this;
    }

}
