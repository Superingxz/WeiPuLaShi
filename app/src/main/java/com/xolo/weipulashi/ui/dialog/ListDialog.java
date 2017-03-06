package com.xolo.weipulashi.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.utils.ToastUtil;

import static com.xolo.weipulashi.R.id.rv;

/**
 * Created by Administrator on 2017/2/6.
 */

public class ListDialog extends BaseDialog {
    private ListView lv;

    private BaseAdapter mAdapter;

    public ListDialog(Context context, String title,BaseAdapter adapter, View.OnClickListener  onClickListener) {
        super(context, title, onClickListener);
        mAdapter = adapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lv = new ListView(mContext);
        lv.setDivider(null);
//        lv.setLayoutManager(new LinearLayoutManager(mContext));
        lv.setAdapter(mAdapter);
        lv.setOnItemClickListener((AdapterView.OnItemClickListener) mAdapter);

        addContain(lv);
    }


}
