package com.lgu.common.ncas;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubsInfo {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private String memberNo 				= null;
	private String ctn 						= null;
	private String fee_type 				= null;
	private String ban_unpid_yn_code 		= null;
	private String unit_loss_yn_code 		= null;
	private String ctn_stus_code 			= null;
	private String unit_mdl 				= null;
	private String regDate 					= null;
	private String imsi 					= null;
	private String sub_no 					= null;
	private String aceNo 					= null;
	private String prod_cd 					= null;
	private String real_birth_pers_id 		= null;
	private String sub_birth_pers_id 		= null;
	private String real_sex_pers_id 		= null;
	private String sub_sex_pers_id 			= null;
	private String frst_entr_dttm 			= null;
	private String usim_iccid_no 			= null;
	private String imei 					= null;
	
	private String real_pers_id_out			= null;
	private String sub_pers_id_out			= null;
	
	private String svc_auth			= null;
	private String svc_auth_dt			= null;
	
	
	public SubsInfo(Map<String, String> resultMap)
	{
		/*
		 * public static String ncas_field_memberNo 			= "MEMBERNO";
	public static String ncas_field_ctn 				= "CTN";
	public static String ncas_field_fee_type 			= "FEE_TYPE";
	public static String ncas_field_ban_unpid_yn_code 	= "BAN_UNPID_YN_CODE";
	public static String ncas_field_unit_loss_yn_code 	= "UNIT_LOSS_YN_CODE";
	public static String ncas_field_ctn_stus_code 		= "CTN_STUS_CODE";
	public static String ncas_field_unit_mdl 			= "UNIT_MDL";
	public static String ncas_field_regDate 			= "REGDATE";
	public static String ncas_field_imsi 				= "IMSI";
	public static String ncas_field_sub_no 			= "SUB_NO";
	public static String ncas_field_aceNo 				= "ACENO";
	public static String ncas_field_prod_cd 			= "PROD_CD";
	public static String ncas_field_real_birth_pres_id = "REAL_BIRTH_PRES_ID";
	public static String ncas_field_sub_birth_pres_id 	= "SUB_BIRTH_PRES_ID";
	public static String ncas_field_real_sex_pres_id 	= "REAL_SEX_PRES_ID";
	public static String ncas_field_sub_sex_pres_id 	= "SUB_SEX_PRES_ID";
	public static String ncas_field_frst_entr_dttm 	= "FRST_ENTR_DTTM";
	public static String ncas_field_usim_iccid_no 		= "USIM_ICCID_NO";
	public static String ncas_field_imei 				= "IMEI";
		 */
		try
		{
			if( resultMap.containsKey(NCASConst.NCAS_FIELD_MEMBERNO) )
				memberNo = (String)resultMap.get(NCASConst.NCAS_FIELD_MEMBERNO);
			if( resultMap.containsKey(NCASConst.NCAS_FIELD_CTN) )
				ctn = (String)resultMap.get(NCASConst.NCAS_FIELD_CTN);
			if( resultMap.containsKey(NCASConst.NCAS_FIELD_FEE_TYPE) )
				fee_type = (String)resultMap.get(NCASConst.NCAS_FIELD_FEE_TYPE);
			if( resultMap.containsKey(NCASConst.NCAS_FIELD_BAN_UNPID_YN_CODE) )
				ban_unpid_yn_code = (String)resultMap.get(NCASConst.NCAS_FIELD_BAN_UNPID_YN_CODE);
			if( resultMap.containsKey(NCASConst.NCAS_FIELD_UNIT_LOSS_YN_CODE) )
				unit_loss_yn_code = (String)resultMap.get(NCASConst.NCAS_FIELD_UNIT_LOSS_YN_CODE);
			if( resultMap.containsKey(NCASConst.NCAS_FIELD_CTN_STUS_CODE) )
				ctn_stus_code = (String)resultMap.get(NCASConst.NCAS_FIELD_CTN_STUS_CODE);
			if( resultMap.containsKey(NCASConst.NCAS_FIELD_UNIT_MDL) )
				unit_mdl = (String)resultMap.get(NCASConst.NCAS_FIELD_UNIT_MDL);
			if( resultMap.containsKey(NCASConst.NCAS_FIELD_REGDATE) )
				regDate = (String)resultMap.get(NCASConst.NCAS_FIELD_REGDATE);
			if( resultMap.containsKey(NCASConst.NCAS_FIELD_IMSI) )
				imsi = (String)resultMap.get(NCASConst.NCAS_FIELD_IMSI);
			if( resultMap.containsKey(NCASConst.NCAS_FIELD_SUB_NO) )
				sub_no = (String)resultMap.get(NCASConst.NCAS_FIELD_SUB_NO);
			if( resultMap.containsKey(NCASConst.NCAS_FIELD_ACENO) )
				aceNo = (String)resultMap.get(NCASConst.NCAS_FIELD_ACENO);
			if( resultMap.containsKey(NCASConst.NCAS_FIELD_PROD_CD) )
				prod_cd = (String)resultMap.get(NCASConst.NCAS_FIELD_PROD_CD);
			
			if( resultMap.containsKey(NCASConst.NCAS_FIELD_REAL_BIRTH_PERS_ID) )
				real_birth_pers_id = (String)resultMap.get(NCASConst.NCAS_FIELD_REAL_BIRTH_PERS_ID);
			if( resultMap.containsKey(NCASConst.NCAS_FIELD_SUB_BIRTH_PERS_ID) )
				sub_birth_pers_id = (String)resultMap.get(NCASConst.NCAS_FIELD_SUB_BIRTH_PERS_ID);
			
			if( resultMap.containsKey(NCASConst.NCAS_FIELD_REAL_SEX_PERS_ID) )
				real_sex_pers_id = (String)resultMap.get(NCASConst.NCAS_FIELD_REAL_SEX_PERS_ID);
			if( resultMap.containsKey(NCASConst.NCAS_FIELD_SUB_SEX_PERS_ID) )
				sub_sex_pers_id = (String)resultMap.get(NCASConst.NCAS_FIELD_SUB_SEX_PERS_ID );
			
			if( resultMap.containsKey(NCASConst.NCAS_FIELD_FRST_ENTR_DTTM) )
				frst_entr_dttm = (String)resultMap.get(NCASConst.NCAS_FIELD_FRST_ENTR_DTTM);
			if( resultMap.containsKey(NCASConst.NCAS_FIELD_USIM_ICCID_NO) )
				usim_iccid_no = (String)resultMap.get(NCASConst.NCAS_FIELD_USIM_ICCID_NO );
			if( resultMap.containsKey(NCASConst.NCAS_FIELD_IMEI) )
				imei = (String)resultMap.get(NCASConst.NCAS_FIELD_IMEI);
			if( resultMap.containsKey(NCASConst.NCAS_FIELD_REAL_PERS_ID_OUT) )
				real_pers_id_out = (String)resultMap.get(NCASConst.NCAS_FIELD_REAL_PERS_ID_OUT);
			if( resultMap.containsKey(NCASConst.NCAS_FIELD_SUB_PERS_ID_OUT) )
				sub_pers_id_out = (String)resultMap.get(NCASConst.NCAS_FIELD_SUB_PERS_ID_OUT);
			
			if( resultMap.containsKey(NCASConst.NCAS_FIELD_SVC_AUTH) )
				svc_auth = (String)resultMap.get(NCASConst.NCAS_FIELD_SVC_AUTH);
			
			if( resultMap.containsKey(NCASConst.NCAS_FIELD_SVC_AUTH_DT) )
				svc_auth_dt = (String)resultMap.get(NCASConst.NCAS_FIELD_SVC_AUTH_DT);
			
		} catch(Exception ex )
		{
			logger.error("SubsInfo Exception", ex);
		}
		
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getCtn() {
		return ctn;
	}
	public void setCtn(String ctn) {
		this.ctn = ctn;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public String getBan_unpid_yn_code() {
		return ban_unpid_yn_code;
	}
	public void setBan_unpid_yn_code(String ban_unpid_yn_code) {
		this.ban_unpid_yn_code = ban_unpid_yn_code;
	}
	public String getUnit_loss_yn_code() {
		return unit_loss_yn_code;
	}
	public void setUnit_loss_yn_code(String unit_loss_yn_code) {
		this.unit_loss_yn_code = unit_loss_yn_code;
	}
	public String getCtn_stus_code() {
		return ctn_stus_code;
	}
	public void setCtn_stus_code(String ctn_stus_code) {
		this.ctn_stus_code = ctn_stus_code;
	}
	public String getUnit_mdl() {
		return unit_mdl;
	}
	public void setUnit_mdl(String unit_mdl) {
		this.unit_mdl = unit_mdl;
	}
	public String getRegDate() {
		if (regDate == null || regDate.length() < 10) {
			return null;
		}
		
		return regDate.substring(0, 10);
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getSub_no() {
		return sub_no;
	}
	public void setSub_no(String sub_no) {
		this.sub_no = sub_no;
	}
	public String getAceNo() {
		return aceNo;
	}
	public void setAceNo(String aceNo) {
		this.aceNo = aceNo;
	}
	public String getProd_cd() {
		return prod_cd;
	}
	public void setProd_cd(String prod_cd) {
		this.prod_cd = prod_cd;
	}
	public String getReal_birth_pers_id() {
		return real_birth_pers_id;
	}
	public void setReal_birth_pers_id(String real_birth_pers_id) {
		this.real_birth_pers_id = real_birth_pers_id;
	}
	public String getSub_birth_pers_id() {
		return sub_birth_pers_id;
	}
	public void setSub_birth_pers_id(String sub_birth_pers_id) {
		this.sub_birth_pers_id = sub_birth_pers_id;
	}
	public String getReal_sex_pers_id() {
		return real_sex_pers_id;
	}
	public void setReal_sex_pers_id(String real_sex_pers_id) {
		this.real_sex_pers_id = real_sex_pers_id;
	}
	public String getSub_sex_pers_id() {
		return sub_sex_pers_id;
	}
	public void setSub_sex_pers_id(String sub_sex_pers_id) {
		this.sub_sex_pers_id = sub_sex_pers_id;
	}
	public String getFrst_entr_dttm() {
		return frst_entr_dttm;
	}
	public void setFrst_entr_dttm(String frst_entr_dttm) {
		this.frst_entr_dttm = frst_entr_dttm;
	}
	public String getUsim_iccid_no() {
		return usim_iccid_no;
	}
	public void setUsim_iccid_no(String usim_iccid_no) {
		this.usim_iccid_no = usim_iccid_no;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getReal_pers_id_out() {
		return real_pers_id_out;
	}
	public void setReal_pers_id_out(String real_pers_id_out) {
		this.real_pers_id_out = real_pers_id_out;
	}
	public String getSub_pers_id_out() {
		return sub_pers_id_out;
	}
	public void setSub_pers_id_out(String sub_pers_id_out) {
		this.sub_pers_id_out = sub_pers_id_out;
	}
	public String getSvc_auth() {
		return svc_auth;
	}
	public void setSvc_auth(String svc_auth) {
		this.svc_auth = svc_auth;
	}
	public String getSvc_auth_dt() {
		return svc_auth_dt;
	}
	public void setSvc_auth_dt(String svc_auth_dt) {
		this.svc_auth_dt = svc_auth_dt;
	}
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		
	    sb.append("memberNo            :").append(memberNo).append("\r\n");
	    sb.append("ctn                 :").append(ctn).append("\r\n");
	    sb.append("fee_type            :").append(fee_type).append("\r\n");
	    sb.append("ban_unpid_yn_code   :").append(ban_unpid_yn_code).append("\r\n");
	    sb.append("unit_loss_yn_code   :").append(unit_loss_yn_code).append("\r\n");
	    sb.append("ctn_stus_code       :").append(ctn_stus_code).append("\r\n");
	    sb.append("unit_mdl            :").append(unit_mdl).append("\r\n");
	    sb.append("regDate             :").append(regDate).append("\r\n");
	    sb.append("imsi                :").append(imsi).append("\r\n");
	    sb.append("sub_no              :").append(sub_no).append("\r\n");
	    sb.append("aceNo               :").append(aceNo).append("\r\n");
	    sb.append("prod_cd             :").append(prod_cd).append("\r\n");
	    sb.append("real_birth_pres_id  :").append(real_birth_pers_id).append("\r\n");
	    sb.append("sub_birth_pres_id   :").append(sub_birth_pers_id).append("\r\n");
	    sb.append("real_sex_pres_id    :").append(real_sex_pers_id).append("\r\n");
	    sb.append("sub_sex_pres_id     :").append(sub_sex_pers_id).append("\r\n");
	    sb.append("frst_entr_dttm      :").append(frst_entr_dttm).append("\r\n");
	    sb.append("usim_iccid_no       :").append(usim_iccid_no).append("\r\n");
	    sb.append("imei                :").append(imei).append("\r\n");
	    sb.append("real_pers_id_out    :").append(real_pers_id_out).append("\r\n");
	    sb.append("sub_pers_id_out     :").append(sub_pers_id_out).append("\r\n");
	    sb.append("svc_auth            :").append(svc_auth).append("\r\n");
	    sb.append("svc_auth_dt         :").append(svc_auth_dt).append("\r\n");
		
		return sb.toString();
				
	}
}
