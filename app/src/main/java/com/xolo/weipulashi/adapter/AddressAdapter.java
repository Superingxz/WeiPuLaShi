package com.xolo.weipulashi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.bean.get.Address;
import com.xolo.weipulashi.ui.activity.LoginActivity;
import com.xolo.weipulashi.ui.dialog.MsgDialog;
import com.xolo.weipulashi.utils.adapterUtils.CommenAdapter;
import com.xolo.weipulashi.utils.adapterUtils.ViewHolder;

import static android.media.CamcorderProfile.get;

/**
 * Created by Administrator on 2017/2/24.
 */

public class AddressAdapter extends CommenAdapter<Address> {

    AddressAdapterInterface  anInterface ;

    public AddressAdapter(Context context) {
        super(context);
        anInterface = (AddressAdapterInterface) context;
    }

    private boolean isEdit;

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    public boolean isEdit() {
        return isEdit;
    }

    @Override
    public void setView(ViewHolder holder, int position, final Address address) {
        holder.setText(R.id.tv_consignee, "收货人：" + address.getReceiver() + "   " + address.getTel());
        holder.setText(R.id.tv_location, address.getAddress());

        if (isEdit) {
            holder.setVisibility(R.id.rl_hidden, View.VISIBLE);
            if (address.isIsDefault()) {
                holder.setCheck(R.id.cb_default, true);
                holder.setClickable(R.id.cb_default, false);
            } else {
                holder.setCheck(R.id.cb_default, false);
                holder.setOnClicklistener(R.id.cb_default, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        anInterface.setTakeAddressDefault(String.valueOf(address.getID()));
                    }
                });
            }

            holder.setOnClicklistener(R.id.btn_delect, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new MsgDialog(context, "提示", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            anInterface.deleteMemberTakeAddress(String.valueOf(address.getID()));
                        }
                    }).setMsg("是否删除这个地址？").show();

                }
            });
            holder.setOnClicklistener(R.id.btn_edit, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(mContext, EditAddressActivity.class);
//                    intent.putExtra(IntentConstant.ADDRESS, address);
//                    startActivityForResult(intent, REQUEST_CODE);
                }
            });
        } else
            holder.setVisibility(R.id.rl_hidden, View.GONE);

        if (address.isIsDefault()) {
            holder.setVisibility(R.id.tv_default, View.VISIBLE);
        } else
            holder.setVisibility(R.id.tv_default, View.GONE);
    }

    @Override
    public int getLayoutID() {
        return R.layout.item_address;
    }

    public  interface   AddressAdapterInterface{
        void   deleteMemberTakeAddress(String takeId);
         void  setTakeAddressDefault(String takeId);
    }
}
