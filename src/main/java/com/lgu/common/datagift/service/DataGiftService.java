package com.lgu.common.datagift.service;

import com.lgu.common.datagift.domain.DataGiftCtnCheck;
import com.lgu.common.datagift.domain.DataGiftList;
import com.lgu.common.datagift.domain.DataGiftOwnList;
import com.lgu.common.datagift.domain.DataGiftReg;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DataGiftService {

	/**
	 * 상품권 조회 (DataGiftList)
	 * 
	 * @param accessKey
	 * @return
	 */
	@Headers({"Api-Id: DataGiftList", "Content-type: application/x-www-form-urlencoded"})
//	@Headers({"Api-Id: DataGiftList", "Content-type: application/json; charset=utf-8"})
//	@FormUrlEncoded
	@POST("api/list")
	Call<DataGiftList> dataGiftList(@Header("Service-Id") String serviceId,
			@Header("Access-Key") String accessKey);

//	/**
//	 * 상품권 발행 (DataGiftIssue)
//	 * 
//	 * @param accessKey
//	 * @param issue_no
//	 * @param ctn
//	 * @return
//	 */
//	@Headers({"Api-Id: DataGiftIssue",
//			"Content-type: application/json; charset=utf-8",
//			"Content-type: application/json; charset=utf-8"})
//	@FormUrlEncoded
//	@PUT("api/issue/{issue_no}/{ctn}")
//	Call<DataGiftIssue> dataGiftIssue(@Header("Service-Id") String serviceId,
//			@Header("Access-Key") String accessKey,
//			@Path("issue_no") String issue_no, @Path("ctn") String ctn);

//	/**
//	 * 상품권 발행 취소 (DataGiftIssueCancel)
//	 * 
//	 * @param accessKey
//	 * @param gift_no
//	 * @param ctn
//	 * @return
//	 */
//	@Headers({"Api-Id: DataGiftIssueCancel",
//			"Content-type: application/json; charset=utf-8"})
//	@FormUrlEncoded
//	@DELETE("api/issuecancel/{gift_no}/{ctn}")
//	Call<DataGiftIssueCancel> dataGiftIssueCancel(
//			@Header("Service-Id") String serviceId,
//			@Header("Access-Key") String accessKey,
//			@Path("gift_no") String gift_no, @Path("ctn") String ctn);

	/**
	 * 상품권 발행등록 (DataGiftReg)
	 * 
	 * @param serviceId
	 * @param accessKey
	 * @param issue_no
	 * @param ctn
	 * @return
	 */
	@Headers({"Api-Id: DataGiftReg", "Content-type: application/x-www-form-urlencoded"})
//	@Headers({"Api-Id: DataGiftReg", "Content-type: application/json; charset=utf-8"})
//	@FormUrlEncoded
	@PUT("api/reg/{issue_no}/{ctn}")
	Call<DataGiftReg> dataGiftReg(@Header("Service-Id") String serviceId,
			@Header("Access-Key") String accessKey,
			@Path("issue_no") String issue_no, 
			@Path("ctn") String ctn);
	
	

//	/**
//	 * 상품권 PIN번호 인증 (DataGiftPINCheck)
//	 * 
//	 * @param accessKey
//	 * @param ctn
//	 * @param gift_no
//	 * @return
//	 */
//	@Headers({"Api-Id: DataGiftPINCheck",
//			"Content-type: application/json; charset=utf-8"})
//	@FormUrlEncoded
//	@POST("realtime/pin/check")
//	Call<DataGiftPINCheck> dataGiftPINCheck(
//			@Header("Service-Id") String serviceId,
//			@Header("Access-Key") String accessKey, @Field("ctn") String ctn,
//			@Field("gift_no") String gift_no);

//	/**
//	 * 상품권 PIN번호 등록(DataGiftPINRegist)
//	 * 
//	 * @param accessKey
//	 * @param ctn
//	 * @param gift_no
//	 * @return
//	 */
//	@Headers({"Api-Id: DataGiftPINRegist",
//			"Content-type: application/json; charset=utf-8"})
//	@FormUrlEncoded
//	@POST("realtime/pin/regist")
//	Call<DataGiftPINRegist> dataGiftPINRegist(
//			@Header("Service-Id") String serviceId,
//			@Header("Access-Key") String accessKey, @Field("ctn") String ctn,
//			@Field("gift_no") String gift_no);

//	/**
//	 * 상품권 사용 여부 조회 (DataGiftPinUsedCheck)
//	 * 
//	 * @param accessKey
//	 * @return
//	 */
//	@Headers({"Api-Id: DataGiftPinUsedCheck",
//			"Content-type: application/json; charset=utf-8"})
//	@FormUrlEncoded
//	@POST("gift/pin/usedCheck")
//	Call<DataGiftPinUsedCheck> dataGiftPinUsedCheck(
//			@Header("Service-Id") String serviceId,
//			@Header("Access-Key") String accessKey,
//			@Field("gift_no") String gift_no);

//	/**
//	 * 상품권 리스트 조회 (DataGiftOwnList)
//	 * 
//	 * @param accessKey
//	 * @param ctn
//	 * @param reg_type
//	 * @param start_date
//	 * @param end_date
//	 * @return
//	 */
//	@Headers({"Api-Id: DataGiftOwnList",
//			"Content-type: application/json; charset=utf-8"})
//	@FormUrlEncoded
//	@POST("/gift/own/list")
//	Call<DataGiftOwnList> dataGiftOwnList(
//			@Header("Service-Id") String serviceId,
//			@Header("Access-Key") String accessKey, 
//			@Field("ctn") String ctn,
//			@Field("reg_type") String reg_type,
//			@Field("start_date") String start_date,
//			@Field("end_date") String end_date);
	
	
	/**
	 * 상품권 리스트 조회 (DataGiftOwnList)
	 * 
	 * @param accessKey
	 * @param ctn
	 * @param reg_type
	 * @param start_date
	 * @param end_date
	 * @return
	 */
//	@Headers({"Api-Id: DataGiftOwnList", "Content-type: application/json; charset=utf-8"})
	@Headers({"Api-Id: DataGiftOwnList", "Content-type: application/x-www-form-urlencoded; charset=utf-8"})	
	@FormUrlEncoded
	@POST("gift/own/list")
	Call<DataGiftOwnList> dataGiftOwnList(
			@Header("Service-Id") String serviceId,
			@Header("Access-Key") String accessKey, 
			@Field("ctn") String ctn,
			@Field("reg_type") String reg_type,
			@Field("start_date") String start_date,
			@Field("end_date") String end_date
//			@Body DataGiftOwnListRequest requestBody
			);	
	

	/**
	 * 조회기간 없는(No Period)  상품권 리스트 조회 (DataGiftOwnList)
	 * 
	 * @param accessKey
	 * @param ctn
	 * @param reg_type
	 * @return
	 */
	@Headers({"Api-Id: DataGiftOwnList", "Content-type: application/x-www-form-urlencoded; charset=utf-8"})	
	@FormUrlEncoded
	@POST("gift/own/list")
	Call<DataGiftOwnList> dataGiftOwnListNP(
			@Header("Service-Id") String serviceId,
			@Header("Access-Key") String accessKey, 
			@Field("ctn") String ctn,
			@Field("reg_type") String reg_type
			);		
	

	/**
	 * 고객 CTN 인증 (DataGiftCtnCheck)
	 * 
	 * @param accessKey
	 * @param ctn
	 * @return
	 */
	@Headers({"Api-Id: DataGiftCtnCheck", "Content-type: application/x-www-form-urlencoded; charset=utf-8"})
	@FormUrlEncoded
	@POST("user/ctn/check")
	Call<DataGiftCtnCheck> dataGiftCtnCheck(
			@Header("Service-Id") String serviceId,
			@Header("Access-Key") String accessKey, @Field("ctn") String ctn);
	
	
//	/**
//	 * 고객 CTN 인증 (DataGiftCtnCheck)
//	 * 
//	 * @param accessKey
//	 * @param ctn
//	 * @return
//	 */
//	@Headers({"Api-Id: DataGiftCtnCheck",
//			"Content-type: application/json; charset=utf-8"})
//	@POST("user/ctn/check")
//	Call<DataGiftCtnCheck> dataGiftCtnCheck(
//			@Header("Service-Id") String serviceId,
//			@Header("Access-Key") String accessKey, @Body DataGiftCtnCheckRequest requestBody);
	
	

//	/**
//	 * 상품권 MMS 재발송(DataGiftPinResend)
//	 * 
//	 * @param accessKey
//	 * @param issue_date
//	 * @param gift_no
//	 * @return
//	 */
//	@Headers({"Api-Id: DataGiftPinResend",
//			"Content-type: application/json; charset=utf-8"})
//	@FormUrlEncoded
//	@POST("realtime/pin/resend")
//	Call<DataGiftPinResend> dataGiftPinResend(
//			@Header("Service-Id") String serviceId,
//			@Header("Access-Key") String accessKey,
//			@Field("issue_date") String issue_date,
//			@Field("gift_no") String gift_no);

//	/**
//	 * 상품권 취소(DataGiftPinCancelGift)
//	 * 
//	 * @param accessKey
//	 * @param issue_date
//	 * @param gift_no
//	 * @return
//	 */
//	@Headers({"Api-Id: DataGiftPinCancelGift",
//			"Content-type: application/json; charset=utf-8"})
//	@FormUrlEncoded
//	@POST("realtime/pin/cancelgift")
//	Call<DataGiftPinCancelGift> dataGiftPinCancelGift(
//			@Header("Service-Id") String serviceId,
//			@Header("Access-Key") String accessKey,
//			@Field("issue_date") String issue_date,
//			@Field("gift_no") String gift_no);

//	/**
//	 * 데이터 충전하기 [PG결제] (DataGiftChgDataPgpay)
//	 * 
//	 * @param accessKey
//	 * @param cst_platform
//	 * @param cst_mid
//	 * @param lgd_paykey
//	 * @param lgd_custom_processtimeout
//	 * @param lgd_amount
//	 * @param lgd_oid
//	 * @param pay_type
//	 * @param isAgree
//	 * @param ctn
//	 * @param issue_nos
//	 * @return
//	 */
//	@Headers({"Api-Id: DataGiftChgDataPgpay",
//			"Content-type: application/json; charset=utf-8"})
//	@FormUrlEncoded
//	@POST("api/chgDataPgpay")
//	Call<DataGiftChgDataPgpay> dataGiftChgDataPgpay(
//			@Header("Service-Id") String serviceId,
//			@Header("Access-Key") String accessKey,
//			@Field("cst_platform") String cst_platform,
//			@Field("cst_mid") String cst_mid,
//			@Field("lgd_paykey") String lgd_paykey,
//			@Field("lgd_custom_processtimeout") String lgd_custom_processtimeout,
//			@Field("lgd_amount") String lgd_amount,
//			@Field("lgd_oid") String lgd_oid,
//			@Field("pay_type") String pay_type,
//			@Field("isAgree") String isAgree, @Field("ctn") String ctn,
//			@Field("issue_no") String issue_nos);

//	/**
//	 * 데이터 선물하기 [PG결제-바로충전] (DataGiftGiveDataPgpay)
//	 * 
//	 * @param accessKey
//	 * @param cst_platform
//	 * @param cst_mid
//	 * @param lgd_paykey
//	 * @param lgd_custom_processtimeout
//	 * @param lgd_amount
//	 * @param lgd_oid
//	 * @param pay_type
//	 * @param isAgree
//	 * @param ctn
//	 * @param gift_ctn
//	 * @param issue_nos
//	 * @param mms_context
//	 * @return
//	 */
//	@Headers({"Api-Id: DataGiftGiveDataPgpay",
//			"Content-type: application/json; charset=utf-8"})
//	@FormUrlEncoded
//	@POST("api/givePinPgpay")
//	Call<DataGiftGiveDataPgpay> dataGiftGiveDataPgpay(
//			@Header("Service-Id") String serviceId,
//			@Header("Access-Key") String accessKey,
//			@Field("cst_platform") String cst_platform,
//			@Field("cst_mid") String cst_mid,
//			@Field("lgd_paykey") String lgd_paykey,
//			@Field("lgd_custom_processtimeout") String lgd_custom_processtimeout,
//			@Field("lgd_amount") String lgd_amount,
//			@Field("lgd_oid") String lgd_oid,
//			@Field("pay_type") String pay_type,
//			@Field("isAgree") String isAgree, @Field("ctn") String ctn,
//			@Field("gift_ctn") String gift_ctn,
//			@Field("issue_no") String issue_nos,
//			@Field("mms_context") String mms_context);

//	/**
//	 * 데이터 선물하기 [PG결제-상품권번호전송] (DataGiftGivePinPgpay)
//	 *
//	 * @param accessKey
//	 * @param cst_platform
//	 * @param cst_mid
//	 * @param lgd_paykey
//	 * @param lgd_custom_processtimeout
//	 * @param lgd_amount
//	 * @param lgd_oid
//	 * @param pay_type
//	 * @param isAgree
//	 * @param ctn
//	 * @param gift_ctn
//	 * @param issue_no
//	 * @param mms_type
//	 * @param mms_reserveDate
//	 * @param mms_context
//	 * @return
//	 */
//	@Headers({"Api-Id: DataGiftGivePinPgpay",
//			"Content-type: application/json; charset=utf-8"})
//	@FormUrlEncoded
//	@POST("api/givePinPgpay")
//	Call<DataGiftGivePinPgpay> dataGiftGivePinPgpay(
//			@Header("Service-Id") String serviceId,
//			@Header("Access-Key") String accessKey,
//			@Field("cst_platform") String cst_platform,
//			@Field("cst_mid") String cst_mid,
//			@Field("lgd_paykey") String lgd_paykey,
//			@Field("lgd_custom_processtimeout") String lgd_custom_processtimeout,
//			@Field("lgd_amount") String lgd_amount,
//			@Field("lgd_oid") String lgd_oid,
//			@Field("pay_type") String pay_type,
//			@Field("isAgree") String isAgree, @Field("ctn") String ctn,
//			@Field("gift_ctn") String gift_ctn,
//			@Field("issue_no") String issue_no,
//			@Field("mms_type") String mms_type,
//			@Field("mms_reserveDate") String mms_reserveDate,
//			@Field("mms_context") String mms_context);

}
