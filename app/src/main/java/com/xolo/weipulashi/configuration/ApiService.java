package com.xolo.weipulashi.configuration;


import com.xolo.weipulashi.base.BaseBean;
import com.xolo.weipulashi.base.BaseCommenBean;
import com.xolo.weipulashi.bean.get.Address;
import com.xolo.weipulashi.bean.get.Area;
import com.xolo.weipulashi.bean.get.City;
import com.xolo.weipulashi.bean.get.DuiJiangRecord;
import com.xolo.weipulashi.bean.get.Duijiang;
import com.xolo.weipulashi.bean.get.MemberInfo;
import com.xolo.weipulashi.bean.get.MemberJFList;
import com.xolo.weipulashi.bean.get.Menbers;
import com.xolo.weipulashi.bean.get.Order;
import com.xolo.weipulashi.bean.get.OrderInput;
import com.xolo.weipulashi.bean.get.OutOrderInfo;
import com.xolo.weipulashi.bean.get.Produce;
import com.xolo.weipulashi.bean.get.Province;
import com.xolo.weipulashi.bean.get.ReturnInfo;
import com.xolo.weipulashi.bean.get.SignCheck;
import com.xolo.weipulashi.bean.get.StockNum;
import com.xolo.weipulashi.bean.get.SyInfo;
import com.xolo.weipulashi.bean.get.TrackInfo;
import com.xolo.weipulashi.bean.get.User;
import com.xolo.weipulashi.bean.get.WareHouse;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/9.
 */

public interface ApiService {
     /**
      * 3.1.1.登录接口
     */
     @FormUrlEncoded
     @POST("HSLInserFace.aspx")
     Observable<BaseBean<User>> getUser(@Field("action") String action, @Field("jsonData") String jsonData);

     /**
      * 3.2.2.仓库信息列表接口
      */
     @FormUrlEncoded
     @POST("HSLInserFace.aspx")
     Observable<BaseBean<WareHouse>> getWareHouse(@Field("action") String action, @Field("jsonData") String jsonData);

     /**
      * 3.3.1.需要签收的出库单号列表（签收）信息接口
      * 3.4.1.获取出库单单号接口
     */
     @FormUrlEncoded
     @POST("HSLInserFace.aspx")
     Observable<BaseBean<Order>> getOrder(@Field("action") String action, @Field("jsonData") String jsonData);

     /**
      * 3.3.2.获取签收信息列表接口
     */
     @FormUrlEncoded
     @POST("HSLInserFace.aspx")
     Observable<BaseBean<SignCheck>> getSignCheck(@Field("action") String action, @Field("jsonData") String jsonData);

     /**
      * 3.3.3.一键签收接口
     */
     @FormUrlEncoded
     @POST("HSLInserFace.aspx")
     Observable<BaseBean> getBaseBean(@Field("action") String action, @Field("jsonData") String jsonData);

     /**
      * 3.3.4.扫码签收接口（扫一个码签收整张单）
      */
     @FormUrlEncoded
     @POST("HSLInserFace.aspx")
     Observable<BaseBean> qSScanCheckAll(@Field("action") String action, @Field("jsonData") String jsonData);

     /**
      * 3.4.3.出库单详情接口
      * 3.4.2.出库信息列表接口
     */
     @FormUrlEncoded
     @POST("HSLInserFace.aspx")
     Observable<BaseBean<OutOrderInfo>> getOutOrederInfo(@Field("action") String action, @Field("jsonData") String jsonData);

     /**
      * 3.4.4.出库单产品明细列表接口
      * 3.4.5.出库扫码接口
     */
     @FormUrlEncoded
     @POST("HSLInserFace.aspx")
     Observable<BaseBean<Produce>> getProduce(@Field("action") String action, @Field("jsonData") String jsonData);

     /**
      * 3.5.2.退货信息列表接口
     */
     @FormUrlEncoded
     @POST("HSLInserFace.aspx")
     Observable<BaseBean<ReturnInfo>> getReturnInfo(@Field("action") String action, @Field("jsonData") String jsonData);

     /**
      * 3.9.1.库存接口
     */
     @FormUrlEncoded
     @POST("HSLInserFace.aspx")
     Observable<BaseBean<StockNum>> getStockNum(@Field("action") String action, @Field("jsonData") String jsonData);

     /**
      * 3.8.1.门店会员列表接口
     */
     @FormUrlEncoded
     @POST("HSLInserFace.aspx")
     Observable<BaseBean<Menbers>> getMenbers(@Field("action") String action, @Field("jsonData") String jsonData);

    /**
     *3.8.2.会员信息接口
     */
     @FormUrlEncoded
     @POST("HSLInserFace.aspx")
     Observable<BaseBean<MemberInfo>> getMemberInfo(@Field("action") String action, @Field("jsonData") String jsonData);

     /**
      *3.8.3.会员积分信息列表接口
      */
     @FormUrlEncoded
     @POST("HSLInserFace.aspx")
     Observable<BaseBean<MemberJFList>> getMemberJFList(@Field("action") String action, @Field("jsonData") String jsonData);

     /**
      * 3.9.2.协助会员兑换奖品信息列表接口
     */
     @FormUrlEncoded
     @POST("HSLInserFace.aspx")
     Observable<BaseCommenBean<Duijiang>> getDuijiang(@Field("action") String action, @Field("jsonData") String jsonData);

     /**
      * 3.9.8.溯源信息接口
     */
     @FormUrlEncoded
     @POST("HSLInserFace.aspx")
     Observable<BaseBean<SyInfo>> getSyInfo(@Field("action") String action, @Field("jsonData") String jsonData);

     /**
      * 3.7.1.入库信息列表接口
     */
     @FormUrlEncoded
     @POST("HSLInserFace.aspx")
     Observable<BaseBean<OrderInput>> getOrderInput(@Field("action") String action, @Field("jsonData") String jsonData);

     /**
      *3.9.5.协助兑换订单记录列表接口
     */
     @FormUrlEncoded
     @POST("HSLInserFace.aspx")
     Observable<BaseBean<DuiJiangRecord>> getDuiJiangRecord(@Field("action") String action, @Field("jsonData") String jsonData);

     /**
      *3.9.9.防窜货查询接口
     */
     @FormUrlEncoded
     @POST("HSLInserFace.aspx")
     Observable<BaseCommenBean<TrackInfo>> getTrackInfo(@Field("action") String action, @Field("jsonData") String jsonData);

     /**
      *3.9.10.获取省份接口
     */
     @FormUrlEncoded
     @POST("HSLInserFace.aspx")
     Observable<BaseBean<Province>> getProvince(@Field("action") String action, @Field("jsonData") String jsonData);

     /**
      *3.9.11.获取市接口
     */
     @FormUrlEncoded
     @POST("HSLInserFace.aspx")
     Observable<BaseBean<City>> getCity(@Field("action") String action, @Field("jsonData") String jsonData);

     /**
      *3.9.12.获取区/县接口
     */
     @FormUrlEncoded
     @POST("HSLInserFace.aspx")
     Observable<BaseBean<Area>> getArea(@Field("action") String action, @Field("jsonData") String jsonData);

     /**
      *3.9.13.获取会员收货地址接口
     */
     @FormUrlEncoded
     @POST("HSLInserFace.aspx")
     Observable<BaseBean<Address>> getAddress(@Field("action") String action, @Field("jsonData") String jsonData);
}
