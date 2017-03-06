package com.xolo.weipulashi.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xolo.weipulashi.R;


/**
 * Created by ye on 2017/1/8.
 */
public class LoadingDialog extends Dialog {
    private Context mContext;
    private ImageView imageView;

    AnimationDrawable animation;

    /**
     * LoadDialog
     */
    private static LoadingDialog loadDialog;

    public LoadingDialog(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getContext().setTheme(android.R.style.Theme_InputMethod);
        setContentView(R.layout.dialog_loading);

        imageView = (ImageView) findViewById(R.id.iv);

        Window window = getWindow();

        WindowManager.LayoutParams attributesParams = window.getAttributes();
        attributesParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        attributesParams.dimAmount = 0.5f;
        window.setAttributes(attributesParams);

        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }


    @Override
    protected void onStart() {
        super.onStart();
        animation= (AnimationDrawable) imageView.getBackground();
        animation.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        animation.stop();
    }

    public static  void show(Context context) {
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        if (loadDialog != null && loadDialog.isShowing()) {
            return;
        }
        loadDialog = new LoadingDialog(context);
        loadDialog.show();
    }


    /**
     * dismiss the mDialogTextView
     */
    public static void dismiss(Context context) {
        try {
            if (context instanceof Activity) {
                if (((Activity) context).isFinishing()) {
                    loadDialog = null;
                    return;
                }
            }

            if (loadDialog != null && loadDialog.isShowing()) {
                Context loadContext = loadDialog.getContext();
                if (loadContext != null && loadContext instanceof Activity) {
                    if (((Activity) loadContext).isFinishing()) {
                        loadDialog = null;
                        return;
                    }
                }
                loadDialog.dismiss();
                loadDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            loadDialog = null;
        }
    }

}
