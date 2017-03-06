package com.xolo.weipulashi.ui.activity.Store;

import android.widget.TextView;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.base.BaseFragment;
import com.xolo.weipulashi.bean.get.MemberInfo;

import butterknife.BindView;

import static com.xolo.weipulashi.R.id.Baby_nickName;

/**
 * Created by wei on 2017/2/16.
 */

public class MdMembersInfoFragment extends BaseFragment implements MemberInfoLitenter {


    @BindView(Baby_nickName)
    TextView BabyNickName;
    @BindView(R.id.Baby_Sex)
    TextView BabySex;
    @BindView(R.id.LevelName)
    TextView LevelName;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.BranchName)
    TextView BranchName;
    @BindView(R.id.IntegralValue)
    TextView IntegralValue;
    @BindView(R.id.RegTime)
    TextView RegTime;
    @BindView(R.id.Time)
    TextView Time;
    private String baby_nickName;
    private String baby_sex;
    private String levelName;
    private String areaName;
    private String branchName;
    private String cityName;
    private int integralValue;
    private String provinceName;
    private String regTime;

    @Override
    public void initView() {
        ((MdMembersInfoActivity) getActivity()).setMemberInfoLitenter(this);
    }


    @Override
    public void SetMemberInfo(MemberInfo memberInfo) {
        baby_nickName = memberInfo.getBaby_nickName();
        baby_sex = memberInfo.getBaby_Sex();
        levelName = memberInfo.getLevelName();
        branchName = memberInfo.getBranchName();
        provinceName = memberInfo.getProvinceName();
        cityName = memberInfo.getCityName();
        areaName = memberInfo.getAreaName();
        regTime = memberInfo.getRegTime();
        integralValue = memberInfo.getIntegralValue();

        BabyNickName.setText(baby_nickName);
        BabySex.setText(baby_sex);
        LevelName.setText(levelName);
        if(provinceName!=null|cityName!=null|areaName!=null){
            address.setText(provinceName+cityName+areaName);
        }
        BranchName.setText(branchName);
        IntegralValue.setText(String.valueOf(integralValue));
        RegTime.setText(regTime);
        Time.setText(regTime);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_md_members_info;
    }

}
