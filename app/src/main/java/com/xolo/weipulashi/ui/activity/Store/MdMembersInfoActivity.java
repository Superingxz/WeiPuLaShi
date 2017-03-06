package com.xolo.weipulashi.ui.activity.Store;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xolo.weipulashi.R;
import com.xolo.weipulashi.base.BaseActivity;
import com.xolo.weipulashi.base.BaseBean;
import com.xolo.weipulashi.bean.get.MemberInfo;
import com.xolo.weipulashi.bean.get.MemberJFList;
import com.xolo.weipulashi.bean.json.JsGetMemberID;
import com.xolo.weipulashi.ui.view.CircleTransform;
import com.xolo.weipulashi.utils.ToastUtil;
import com.xolo.weipulashi.utils.http.ExSubscriber;
import com.xolo.weipulashi.utils.http.RetrofitManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MdMembersInfoActivity extends BaseActivity {

    @BindView(R.id.HeadImage)
    ImageView HeadImage;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_jf)
    TextView tvJf;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.activity_md_members_info)
    LinearLayout activityMdMembersInfo;

    private String[] titles = new String[]
            {"详细信息", "积分记录"};

    private List<Fragment> fragments = new ArrayList<>();
    private MdMembersInfoFragment mdMembersInfoFragment;
    private MdMembersJFListFragment mdMembersJFListFragment;

    private int MemberId;
    private MemberInfo memberInfo;

    private MemberInfoLitenter memberInfoLitenter;
    public void setMemberInfoLitenter(MemberInfoLitenter memberInfoLitenter) {
        this.memberInfoLitenter = memberInfoLitenter;
    }

    private MemberJFListLitenter memberJFListLitenter;
    public void setMemberJFListLitenter(MemberJFListLitenter memberJFListLitenter) {
        this.memberJFListLitenter = memberJFListLitenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_md_members_info;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        MemberId = intent.getIntExtra("ID", -1);

        initViewPager();
        load();
    }

    public void load() {
        /**
         *3.8.2.会员信息接口
         */
        RetrofitManager.getApiInstant()
                .getMemberInfo("GetMemberInfo",mGson.toJson(new JsGetMemberID(MemberId)))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean<MemberInfo>>(mContext) {
                    @Override
                    public void onSuccess(BaseBean<MemberInfo> memberInfoBaseBean) {
                        List<MemberInfo> dt = memberInfoBaseBean.getDt();
                        if (dt != null && dt.size() > 0) {
                            memberInfo = dt.get(0);

                            //设置信息
                            String headImageUrl = memberInfo.getHeadImage();
                            if (headImageUrl!=null){
                                //加载圆形图片
                                Picasso.with(mContext).load(headImageUrl).transform(new CircleTransform()).into(HeadImage);
                            }

                            String mum_nickName = memberInfo.getMum_nickName();
                            if (mum_nickName!=null){
                                tvName.setText(mum_nickName);
                            }
                            String phone = memberInfo.getPhone();
                            if (phone!=null){
                                tvPhone.setText(phone);
                            }

                            int integralValue = memberInfo.getIntegralValue();
                            tvJf.setText(integralValue+"");
                            if (memberInfoLitenter != null) {
                                memberInfoLitenter.SetMemberInfo(memberInfo);
                            }

                        }
                    }

                    @Override
                    public void onError(BaseBean<MemberInfo> memberInfoBaseBean) {
                        ToastUtil.showLong(mContext,memberInfoBaseBean.getMsg());
                    }
                });


        /**
         *3.8.3.会员积分信息列表接口
         */
        RetrofitManager.getApiInstant()
                .getMemberJFList("GetMemberJFList",mGson.toJson(new JsGetMemberID(MemberId)))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean<MemberJFList>>(mContext) {
                    @Override
                    public void onSuccess(BaseBean<MemberJFList> memberJFList) {
                        List<MemberJFList> dt = memberJFList.getDt();

                        if (memberJFListLitenter != null) {
                            memberJFListLitenter.SetMemberJFList(dt);
//                            memberJFListLitenter.SetMemberJF(memberJFList);
                        }
                    }

                    @Override
                    public void onError(BaseBean<MemberJFList> memberInfoBaseBean) {
                        ToastUtil.showLong(mContext,memberInfoBaseBean.getMsg());
                    }
                });


    }


    private void initViewPager() {
        mdMembersInfoFragment = new MdMembersInfoFragment();
        mdMembersJFListFragment = new MdMembersJFListFragment();

        fragments.add(mdMembersInfoFragment);
        fragments.add(mdMembersJFListFragment);


        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });

        tabLayout.setupWithViewPager(vp);//让TabLayout与VP联动
    }

}

interface MemberInfoLitenter {
    public void SetMemberInfo(MemberInfo memberInfo);
}

interface MemberJFListLitenter {
    public void SetMemberJFList(List<MemberJFList> memberJFListlist);
//    public void SetMemberJF(BaseBean<MemberJFList> memberJFList);
}