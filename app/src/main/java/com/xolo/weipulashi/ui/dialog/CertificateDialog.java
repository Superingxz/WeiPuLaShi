package com.xolo.weipulashi.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.xolo.weipulashi.R;
import com.xolo.weipulashi.utils.LogUtil;
import com.xolo.weipulashi.utils.ToastUtil;

/**
 * Created by Administrator on 2017/2/14.
 */

public class CertificateDialog extends Dialog {
    private ImageView iv;
    private Context mContext;
    private String  src;

    private static final String HEX_STRING = "0123456789ABCDEF";

    public CertificateDialog(Context context) {
        super(context,R.style.dialog_no_bg_title);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.certificate_show);
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.5f;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(layoutParams);

        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        View btnClose = findViewById(R.id.btn_close);
         iv = (ImageView) findViewById(R.id.iv_certificate);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CertificateDialog.this.dismiss();
            }
        });

        setCanceledOnTouchOutside(true);
        LogUtil.e(src);
        Picasso.with(mContext).load(toBrowserCode(src)).into(iv);
    }

    @Override
    public void show() {
        if (!TextUtils.isEmpty(src)){
            super.show();
        }else
            ToastUtil.showShort(mContext,"请扫描产品上的条码获取溯源信息");
    }

    /**
     * 把中文字符转换为带百分号的浏览器编码
     *
     * @param word
     * @return
     */
    public static String toBrowserCode(String word) {
        byte[] bytes = word.getBytes();

        //不包含中文，不做处理
        if (bytes.length == word.length())
            return word;

        StringBuilder browserUrl = new StringBuilder();
        String tempStr = "";

        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);

            //不需要处理
            if ((int) currentChar <= 256) {

                if (tempStr.length() > 0) {
                    byte[] cBytes = tempStr.getBytes();

                    for (int j = 0; j < cBytes.length; j++) {
                        browserUrl.append('%');
                        browserUrl.append(HEX_STRING.charAt((cBytes[j] & 0xf0) >> 4));
                        browserUrl.append(HEX_STRING.charAt((cBytes[j] & 0x0f) >> 0));
                    }
                    tempStr = "";
                }

                browserUrl.append(currentChar);
            } else {
                //把要处理的字符，添加到队列中
                tempStr += currentChar;
            }
        }
        return browserUrl.toString();
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
