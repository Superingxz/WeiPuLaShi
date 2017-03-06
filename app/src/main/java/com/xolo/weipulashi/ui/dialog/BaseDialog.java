package com.xolo.weipulashi.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.xolo.weipulashi.R;

/**
 * Created by Administrator on 2017/2/6.
 */

public class BaseDialog extends Dialog implements View.OnClickListener{
    private TextView tvTitle;

    private String title;
    protected Context mContext;
    private View.OnClickListener  onClickListener;
    protected FrameLayout fl_contanin;

    public BaseDialog(Context context) {
        super(context,R.style.dialog_no_title);
    }

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BaseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public BaseDialog(Context context, String title, View.OnClickListener  onClickListener) {
        super(context,R.style.dialog_no_title);
        this.title = title;
        this.onClickListener = onClickListener;
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_base);
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.5f;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(layoutParams);

        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        View btnClose = findViewById(R.id.btn_close);
        View btnCancel = findViewById(R.id.btn_cancel);
        View btnConfirm = findViewById(R.id.btn_confirm);
         tvTitle = (TextView) findViewById(R.id.tv_title);

        fl_contanin = ((FrameLayout) findViewById(R.id.fl_contanin));

        tvTitle.setText(title);

        btnClose.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnConfirm.setOnClickListener(onClickListener);
    }

    protected void addContain(View view){
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        fl_contanin.addView(view,params);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_close:
                this.dismiss();
                break;
            case R.id.btn_cancel:
                this.dismiss();
                break;
        }
    }

}
