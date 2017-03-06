package com.xolo.weipulashi.ui.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xolo.weipulashi.R;

/**
 * Created by Administrator on 2016/8/30.
 */
public class TitleView extends LinearLayout {
    private TextView tv;
    private View buttonLeft;
    private String title;
    private Button buttonRight;
    private ImageView imageView;
    private String title_right;
    private boolean is_show_left = true;

    public TitleView(Context context) {
        super(context);
        init(context);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TitleView);
        if (a.hasValue(R.styleable.TitleView_mtitle)) {
            title = a.getString(R.styleable.TitleView_mtitle);
        }
        if (a.hasValue(R.styleable.TitleView_titleRight)) {
            title_right = a.getString(R.styleable.TitleView_titleRight);
        }
        if (a.hasValue(R.styleable.TitleView_showLeft)) {
            is_show_left = a.getBoolean(R.styleable.TitleView_showLeft,true);
        }
        a.recycle();
        init(context);
    }

    private void init(final Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.merge_title, this);
        tv = ((TextView) inflate.findViewById(R.id.tv));
        tv.getPaint().setFakeBoldText(true);
        tv.setText(title);

        buttonLeft = findViewById(R.id.btn_left);
        buttonRight = (Button) findViewById(R.id.btn_right);
        imageView = (ImageView) findViewById(R.id.iv_right);
        buttonLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)context).finish();
            }
        });

        if (!is_show_left){
            buttonLeft.setVisibility(GONE);
        }

        if (!TextUtils.isEmpty(title_right)){
            buttonRight.setVisibility(VISIBLE);
            buttonRight.setText(title_right);
        }

    }

    public void setTitle(String title) {
        tv.setText(title);
    }

    public  void  setRightVisibility(int visibility){
        buttonRight.setVisibility(visibility);
    }

    public  void  setRightBg(int res){
        imageView.setVisibility(VISIBLE);
        imageView.setImageResource(res);
    }

    public  void  setRightClick(OnClickListener listener){
        buttonRight.setVisibility(VISIBLE);
        buttonRight.setOnClickListener(listener);
    }

    public  void  setRightIvClick(OnClickListener listener){
        imageView.setVisibility(VISIBLE);
        imageView.setOnClickListener(listener);
    }

    public  void setLeftGone(){
        buttonLeft.setVisibility(GONE);
    }

    public  void setRightText(String text){
        buttonRight.setVisibility(VISIBLE);
        buttonRight.setText(text);
    }

}
