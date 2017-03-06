package com.xolo.weipulashi.ui.activity.Store;

import android.util.Log;
import android.widget.ListView;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.adapter.MdMembersJFListAdapter;
import com.xolo.weipulashi.base.BaseFragment;
import com.xolo.weipulashi.bean.get.MemberJFList;

import java.util.List;

import butterknife.BindView;

/**
 * Created by wei on 2017/2/16.
 */

public class MdMembersJFListFragment extends BaseFragment implements MemberJFListLitenter{
    @BindView(R.id.jf_list)
    ListView jfList;
    MdMembersJFListAdapter adapter;


    @Override
    public void initView() {
        ((MdMembersInfoActivity) getActivity()).setMemberJFListLitenter(this);

        adapter = new MdMembersJFListAdapter(mContext);
        jfList.setAdapter(adapter);

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_md_members_jf_list;
    }


    @Override
    public void SetMemberJFList(List<MemberJFList> memberJFList) {
        Log.e("test", "SetMemberJFList: "+memberJFList.toString());

        adapter.addAll(memberJFList);
    }

//    @Override
//    public void SetMemberJF(BaseBean<MemberJFList> memberJFList) {
//        List<MemberJFList> dt = memberJFList.getDt();
//        adapter.addAll(dt);
//    }


}
