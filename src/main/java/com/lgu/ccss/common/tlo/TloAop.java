package com.lgu.ccss.common.tlo;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.lgu.common.ai.auth.AuthErrorCode;
import com.lgu.common.ai.auth.model.AuthResponseJSON;
import com.lgu.common.ai.coif.COIFErrorCode;
import com.lgu.common.ai.coif.model.COIFResponseJSON;
import com.lgu.common.aisv.auth.AisvAuthErrorCode;
import com.lgu.common.aisv.auth.model.AisvAuthResponseJSON;
import com.lgu.common.cekAi.auth.CekAuthErrorCode;
import com.lgu.common.cekAi.auth.model.CekAuthResponseJSON;
import com.lgu.common.clova.auth.CicErrorCode;
import com.lgu.common.clova.auth.ClovaErrorCode;
import com.lgu.common.clova.auth.model.ClovaAuthResponseBodyAccessTokenJSON;
import com.lgu.common.clova.auth.model.ClovaAuthResponseBodyAuthCodeJSON;
import com.lgu.common.clova.auth.model.ClovaAuthResponseBodyDeleteTokenJSON;
import com.lgu.common.clova.auth.model.ClovaAuthResponseDiscoveryJSON;
import com.lgu.common.map.MapErrorCode;
import com.lgu.common.map.model.ResRevgeocodingSearchJSON;
import com.lgu.common.map.model.ResRouthSearchJSON;
import com.lgu.common.map.model.ResTotalSearchJSON;
import com.lgu.common.map.model.findStatRoute.ResFindStatRouthSearchJSON;
import com.lgu.common.ncas.NCASErrorCode;
import com.lgu.common.ncas.NCASResultData;
import com.lgu.common.datagift.DataGiftErrorCode;
import com.lgu.common.datagift.domain.DataGiftCtnCheck;
import com.lgu.common.datagift.domain.DataGiftList;
import com.lgu.common.datagift.domain.DataGiftOwnList;
import com.lgu.common.datagift.domain.DataGiftReg;

@Aspect
public class TloAop {
	
	@Pointcut("execution(* com.lgu.common.ncas.NCASQueryManager.query(..))")
	private void pointCutNcas() {
		
	}
	
	@Around("pointCutNcas()")
	public Object tloAopNcas(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.NCAS_SVC_CLASS, TloConst.NC01);
		tlo.put(TloData.NCAS_REQ_TIME, TloData.getNowDate());
		
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {

			if (obj == null) {
				tlo.put(TloData.NCAS_RESULT_CODE, NCASErrorCode.ERRORCODE_NCAS_CONN);
				
			} else {
				NCASResultData ncasData = (NCASResultData) obj;
				tlo.put(TloData.NCAS_RESULT_CODE, ncasData.getRespCode());
			}
			
			tlo.put(TloData.NCAS_RES_TIME, TloData.getNowDate());
			TloUtil.setTloData(tlo);
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	@Pointcut("execution(* com.lgu.common.ai.auth.AuthAgent.createDeviceToken(..))")
	private void pointCutAIAuthCreateDeviceToken() {
		
	}
	
	@Around("pointCutAIAuthCreateDeviceToken()")
	public Object tloAopAIAuthCreateDeviceToken(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.AI_AUTH_SVC_CLASS, TloConst.AA01);
		tlo.put(TloData.AI_AUTH_REQ_TIME, TloData.getNowDate());
		
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {

			if (obj == null) {
				tlo.put(TloData.AI_AUTH_RESULT_CODE, AuthErrorCode.RC_60000000.getCode());
			
			} else {
				AuthResponseJSON result = (AuthResponseJSON) obj;
				tlo.put(TloData.AI_AUTH_RESULT_CODE, result.getCommon().getResultCode());
			}
			
			tlo.put(TloData.AI_AUTH_RES_TIME, TloData.getNowDate());
			TloUtil.setTloData(tlo);
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	@Pointcut("execution(* com.lgu.common.ai.auth.AuthAgent.createPlatformToken(..))")
	private void pointCutAIAuthCreatePlatformToken() {
		
	}
	
	@Around("pointCutAIAuthCreatePlatformToken()")
	public Object tloAopAIAuthCreatePlatformToken(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.AI_AUTH_SVC_CLASS, TloConst.AA02);
		tlo.put(TloData.AI_AUTH_REQ_TIME, TloData.getNowDate());
		
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {

			if (obj == null) {
				tlo.put(TloData.AI_AUTH_RESULT_CODE, AuthErrorCode.RC_60000000.getCode());
				
			} else {
				AuthResponseJSON result = (AuthResponseJSON) obj;
				tlo.put(TloData.AI_AUTH_RESULT_CODE, result.getCommon().getResultCode());
			}
			
			tlo.put(TloData.AI_AUTH_RES_TIME, TloData.getNowDate());
			TloUtil.setTloData(tlo);
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	@Pointcut("execution(* com.lgu.common.ai.auth.AuthAgent.saveSvcAuthInfo(..))")
	private void pointCutAIAuthSaveSvcAuthInfo() {
		
	}
	
	@Around("pointCutAIAuthSaveSvcAuthInfo()")
	public Object tloAopAIAuthSaveSvcAuthInfo(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.AI_AUTH_SVC_CLASS, TloConst.AA05);
		tlo.put(TloData.AI_AUTH_REQ_TIME, TloData.getNowDate());
		
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {

			if (obj == null) {
				tlo.put(TloData.AI_AUTH_RESULT_CODE, AuthErrorCode.RC_60000000.getCode());
				
			}
			else {
				AuthResponseJSON result = (AuthResponseJSON) obj;
				tlo.put(TloData.AI_AUTH_RESULT_CODE, result.getCommon().getResultCode());
			}
			
			tlo.put(TloData.AI_AUTH_RES_TIME, TloData.getNowDate());
			TloUtil.setTloData(tlo);
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////
		
	@Pointcut("execution(* com.lgu.common.ai.auth.AuthAgent.logoutSvc(..))")
	private void pointCutAIAuthLogoutSvc() {
	
	}
	
	@Around("pointCutAIAuthLogoutSvc()")
	public Object tloAopAIAuthLogoutSvc(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.AI_AUTH_SVC_CLASS, TloConst.AA06);
		tlo.put(TloData.AI_AUTH_REQ_TIME, TloData.getNowDate());
		
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {

			if (obj == null) {
				tlo.put(TloData.AI_AUTH_RESULT_CODE, AuthErrorCode.RC_60000000.getCode());
				
			}
			else {
				AuthResponseJSON result = (AuthResponseJSON) obj;
				tlo.put(TloData.AI_AUTH_RESULT_CODE, result.getCommon().getResultCode());
			}
			
			tlo.put(TloData.AI_AUTH_RES_TIME, TloData.getNowDate());
			TloUtil.setTloData(tlo);
		}
	}
		
	/////////////////////////////////////////////////////////////////////////////////////
	
	@Pointcut("execution(* com.lgu.common.ai.coif.COIFAgent.requestWeather(..))")
	private void pointCutAICOIFRequestWeather() {
		
	}
	
	@Around("pointCutAICOIFRequestWeather()")
	public Object tloAopAICOIFRequestWeather(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.AI_COIF_SVC_CLASS, TloConst.AC01);
		tlo.put(TloData.AI_COIF_REQ_TIME, TloData.getNowDate());
		
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {

			if (obj == null) {
				tlo.put(TloData.AI_COIF_RESULT_CODE, COIFErrorCode.RC_60000000.getCode());
				
			}
			else {
				COIFResponseJSON result = (COIFResponseJSON) obj;
				tlo.put(TloData.AI_COIF_RESULT_CODE, result.getCommon().getResultCode());
			}
			
			tlo.put(TloData.AI_COIF_RES_TIME, TloData.getNowDate());
			TloUtil.setTloData(tlo);
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	@Pointcut("execution(* com.lgu.common.esb.ESBManager.get*(..))")
	private void pointCutESBManagerGet() {
		
	}
	
	@Around("pointCutESBManagerGet()")
	public Object tloAopESBManagerGet(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.ESB_SVC_CLASS, TloConst.ES01);
		tlo.put(TloData.ESB_REQ_TIME, TloData.getNowDate());
		
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {

			if (obj == null) {
				tlo.put(TloData.ESB_RESULT_CODE, TloConst.ESB_FAIL);
			}
			else {
				tlo.put(TloData.ESB_RESULT_CODE, TloConst.ESB_SUCCESS);
			}
			
			tlo.put(TloData.ESB_RES_TIME, TloData.getNowDate());
			TloUtil.setTloData(tlo);
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	@Pointcut("execution(* com.lgu.common.esb.ESBManager.update*(..))")
	private void pointCutESBManagerUpdate() {
		
	}
	
	@Around("pointCutESBManagerUpdate()")
	public Object tloAopESBManagerUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.ESB_SVC_CLASS, TloConst.ES02);
		tlo.put(TloData.ESB_REQ_TIME, TloData.getNowDate());
		
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {

			if (obj == null) {
				tlo.put(TloData.ESB_RESULT_CODE, TloConst.ESB_FAIL);
			}
			else {
				tlo.put(TloData.ESB_RESULT_CODE, TloConst.ESB_SUCCESS);
			}
			
			tlo.put(TloData.ESB_RES_TIME, TloData.getNowDate());
			TloUtil.setTloData(tlo);
		}
	}
	
	/*/////////////////////////////////////////////////////////////////////////////////////

	@Pointcut("execution(* com.lgu.ccss.common.gcalendar.GoogleCalendarService.request*(..))")
	private void pointCutGoogleCalendarRequest() {

	}

	@Around("pointCutGoogleCalendarRequest()")
	public Object tloAoppointCutGoogleCalendarRequest(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.R1, TloConst.GO01);
		tlo.put(TloData.R3, TloData.getNowDate());

		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;

		} finally {

			if (obj == null) {
				tlo.put(TloData.R2, TloConst.GCALENDAR_FAIL);

			} else {
				tlo.put(TloData.R2, TloConst.GCALENDAR_SUCCESS);
			}

			tlo.put(TloData.R4, TloData.getNowDate());
			TloUtil.setTloData(tlo);
		}
	}*/
	
	
	/////////////////////////////////////////////////////////////////////////////////////
	//Tlo To Do
	
	/*@Pointcut("execution(* com.lgu.common.cekAi.auth.CekAuthAgent.addUser(..))")
	private void pointCutCekAIAuthAddUser() {
	
	}
		
	@Around("pointCutCekAIAuthAddUser()")
	public Object tloAopCekAIAuthAddUser(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.CEK_SVC_CLASS, TloConst.CA01);
		tlo.put(TloData.CEK_REQ_TIME, TloData.getNowDate());
		
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {

			if (obj == null) {
				tlo.put(TloData.CEK_RESULT_CODE, CekAuthErrorCode.RC_60000000.getCode());
				
			}
			else {
				CekAuthResponseJSON result = (CekAuthResponseJSON) obj;
				tlo.put(TloData.CEK_RESULT_CODE, result.getCommon().getResultCode());
			}
			
			tlo.put(TloData.CEK_RES_TIME, TloData.getNowDate());
			TloUtil.setTloData(tlo);
		}
	}
	
	
	@Pointcut("execution(* com.lgu.common.cekAi.auth.CekAuthAgent.addDevice(..))")
	private void pointCutCekAIAuthAddDevice() {
	
	}
		
	@Around("pointCutCekAIAuthAddDevice()")
	public Object tloAopCekAIAuthAddDevice(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.CEK_SVC_CLASS, TloConst.CA02);
		tlo.put(TloData.CEK_REQ_TIME, TloData.getNowDate());
		
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {

			if (obj == null) {
				tlo.put(TloData.CEK_RESULT_CODE, CekAuthErrorCode.RC_60000000.getCode());
				
			}
			else {
				CekAuthResponseJSON result = (CekAuthResponseJSON) obj;
				tlo.put(TloData.CEK_RESULT_CODE, result.getCommon().getResultCode());
			}
			
			tlo.put(TloData.CEK_RES_TIME, TloData.getNowDate());
			TloUtil.setTloData(tlo);
		}
	}
	*/
	
	@Pointcut("execution(* com.lgu.common.map.MapAgent.findStatRouthSearch(..))")
	private void pointCutFindStatRouthSearch() {
	
	}
	
	@Around("pointCutFindStatRouthSearch()")
	public Object tloAopFindStatRouthSearch(ProceedingJoinPoint joinPoint) throws Throwable {

		Map<String, String> tlo = new HashMap<String, String>();
		String reqTime = TloData.getNowDate();
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {
			if (obj == null) {
				tlo.put(TloData.MAP_BM_RESULT_CODE, MapErrorCode.RC_52001000.getCode());
			}
			else {
				ResFindStatRouthSearchJSON result = (ResFindStatRouthSearchJSON) obj;
				if(result!=null && result.getDeviceType()!=null && result.getDeviceType().equals("BM")) {
					tlo.put(TloData.MAP_BM_SVC_CLASS, TloConst.MB01);
					tlo.put(TloData.MAP_BM_REQ_TIME, reqTime);					
				}else{
					tlo.put(TloData.MAP_AM_SVC_CLASS, TloConst.MB01);
					tlo.put(TloData.MAP_AM_REQ_TIME, reqTime);					
				}
				
				if(result!=null && result.getDeviceType()!=null && result.getDeviceType().equals("BM")) {
					tlo.put(TloData.MAP_BM_RESULT_CODE, result.getTlo_code());
					tlo.put(TloData.MAP_BM_RES_TIME, TloData.getNowDate());
				}else {
					tlo.put(TloData.MAP_AM_RESULT_CODE, result.getTlo_code());
					tlo.put(TloData.MAP_AM_RES_TIME, TloData.getNowDate());
				}
			}			

			TloUtil.setTloData(tlo);
		}
	}
	
	@Pointcut("execution(* com.lgu.common.map.MapAgent.findStatRouthSearchDiff(..))")
	private void pointCutFindStatRouthSearchDiff() {
	
	}
	
	@Around("pointCutFindStatRouthSearchDiff()")
	public Object tloAopFindStatRouthSearchDiff(ProceedingJoinPoint joinPoint) throws Throwable {

		Map<String, String> tlo = new HashMap<String, String>();
		String reqTime = TloData.getNowDate();
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {
			if (obj == null) {
				tlo.put(TloData.MAP_BM_RESULT_CODE, MapErrorCode.RC_52001000.getCode());
			}
			else {
				ResFindStatRouthSearchJSON result = (ResFindStatRouthSearchJSON) obj;
				if(result!=null && result.getDeviceType()!=null && result.getDeviceType().equals("BM")) {
					tlo.put(TloData.MAP_BM_SVC_CLASS, TloConst.MB01);
					tlo.put(TloData.MAP_BM_REQ_TIME, reqTime);					
				}else{
					tlo.put(TloData.MAP_AM_SVC_CLASS, TloConst.MB01);
					tlo.put(TloData.MAP_AM_REQ_TIME, reqTime);					
				}
				
				if(result!=null && result.getDeviceType()!=null && result.getDeviceType().equals("BM")) {
					tlo.put(TloData.MAP_BM_RESULT_CODE, result.getTlo_code());
					tlo.put(TloData.MAP_BM_RES_TIME, TloData.getNowDate());
				}else {
					tlo.put(TloData.MAP_AM_RESULT_CODE, result.getTlo_code());
					tlo.put(TloData.MAP_AM_RES_TIME, TloData.getNowDate());
				}
			}			

			TloUtil.setTloData(tlo);
		}
	}
	
	@Pointcut("execution(* com.lgu.common.map.MapAgent.totalSearch(..))")
	private void pointCutTotalSearch() {
	
	}
	
	@Around("pointCutTotalSearch()")
	public Object tloAopTotalSearch(ProceedingJoinPoint joinPoint) throws Throwable {
		
		Map<String, String> tlo = new HashMap<String, String>();
		String reqTime = TloData.getNowDate();
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {
			if (obj == null) {
				tlo.put(TloData.MAP_BM_RESULT_CODE, MapErrorCode.RC_52001000.getCode());
			}
			else {
				ResTotalSearchJSON result = (ResTotalSearchJSON) obj;
				System.out.println("result.getDeviceType() :::" + result.getDeviceType());
				if(result!=null && result.getDeviceType()!=null && result.getDeviceType().equals("BM")) {
					tlo.put(TloData.MAP_BM_SVC_CLASS, TloConst.MB02);
					tlo.put(TloData.MAP_BM_REQ_TIME, reqTime);					
				}else {
					tlo.put(TloData.MAP_AM_SVC_CLASS, TloConst.MB02);
					tlo.put(TloData.MAP_AM_REQ_TIME, reqTime);					
				}
				if(result!=null && result.getDeviceType()!=null && result.getDeviceType().equals("BM")) {
					tlo.put(TloData.MAP_BM_RESULT_CODE, result.getTlo_code());
					tlo.put(TloData.MAP_BM_RES_TIME, TloData.getNowDate());
				}else {
					tlo.put(TloData.MAP_AM_RESULT_CODE, result.getTlo_code());
					tlo.put(TloData.MAP_AM_RES_TIME, TloData.getNowDate());
				}
			}			

			TloUtil.setTloData(tlo);
		}
	}
	
	@Pointcut("execution(* com.lgu.common.map.MapAgent.routeSearch(..))")
	private void pointCutRouteSearch() {
	
	}
	
	@Around("pointCutRouteSearch()")
	public Object tloAopRouteSearch(ProceedingJoinPoint joinPoint) throws Throwable {
		
		Map<String, String> tlo = new HashMap<String, String>();
		String reqTime = TloData.getNowDate();
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {
			if (obj == null) {
				tlo.put(TloData.MAP_BM_RESULT_CODE, MapErrorCode.RC_52001000.getCode());
			}
			else {
				ResRouthSearchJSON result = (ResRouthSearchJSON) obj;
				System.out.println("result.getDeviceType() :::" + result.getDeviceType());
				if(result!=null && result.getDeviceType()!=null && result.getDeviceType().equals("BM")) {
					tlo.put(TloData.MAP_BM_SVC_CLASS, TloConst.MB03);
					tlo.put(TloData.MAP_BM_REQ_TIME, reqTime);					
				}else {
					tlo.put(TloData.MAP_AM_SVC_CLASS, TloConst.MB03);
					tlo.put(TloData.MAP_AM_REQ_TIME, reqTime);					
				}
				if(result!=null && result.getDeviceType()!=null && result.getDeviceType().equals("BM")) {
					tlo.put(TloData.MAP_BM_RESULT_CODE, result.getTlo_code());
					tlo.put(TloData.MAP_BM_RES_TIME, TloData.getNowDate());
				}else {
					tlo.put(TloData.MAP_AM_RESULT_CODE, result.getTlo_code());
					tlo.put(TloData.MAP_AM_RES_TIME, TloData.getNowDate());
				}
			}			

			TloUtil.setTloData(tlo);
		}
	}
	
	@Pointcut("execution(* com.lgu.common.map.MapAgent.revgeocodingSearch(..))")
	private void pointCutRevgeocodingSearch() {
	
	}
	
	@Around("pointCutRevgeocodingSearch()")
	public Object RevgeocodingSearch(ProceedingJoinPoint joinPoint) throws Throwable {
		
		Map<String, String> tlo = new HashMap<String, String>();
		String reqTime = TloData.getNowDate();
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {
			if (obj == null) {
				tlo.put(TloData.MAP_BM_RESULT_CODE, MapErrorCode.RC_52001000.getCode());
			}
			else {
				ResRevgeocodingSearchJSON result = (ResRevgeocodingSearchJSON) obj;
				System.out.println("result.getDeviceType() :::" + result.getDeviceType());
				if(result!=null && result.getDeviceType()!=null && result.getDeviceType().equals("BM")) {
					tlo.put(TloData.MAP_BM_SVC_CLASS, TloConst.MB04);
					tlo.put(TloData.MAP_BM_REQ_TIME, reqTime);					
				}else {
					tlo.put(TloData.MAP_AM_SVC_CLASS, TloConst.MB04);
					tlo.put(TloData.MAP_AM_REQ_TIME, reqTime);					
				}
				if(result!=null && result.getDeviceType()!=null && result.getDeviceType().equals("BM")) {
					tlo.put(TloData.MAP_BM_RESULT_CODE, result.getTlo_code());
					tlo.put(TloData.MAP_BM_RES_TIME, TloData.getNowDate());
				}else {
					tlo.put(TloData.MAP_AM_RESULT_CODE, result.getTlo_code());
					tlo.put(TloData.MAP_AM_RES_TIME, TloData.getNowDate());
				}
			}			

			TloUtil.setTloData(tlo);
		}
	}
	
	@Pointcut("execution(* com.lgu.common.cekAi.auth.CekAuthAgent.saveSvcAuthInfo(..))")
	private void pointCutCekAIAuthSaveSvcAuthInfo() {
	
	}
		
	@Around("pointCutCekAIAuthSaveSvcAuthInfo()")
	public Object tloAopCekAIAuthSaveSvcAuthInfo(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.CEK_SVC_CLASS, TloConst.CA02);
		tlo.put(TloData.CEK_REQ_TIME, TloData.getNowDate());
		
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {

			if (obj == null) {
				tlo.put(TloData.CEK_RESULT_CODE, CekAuthErrorCode.RC_60000000.getCode());
				
			}
			else {
				CekAuthResponseJSON result = (CekAuthResponseJSON) obj;
				tlo.put(TloData.CEK_RESULT_CODE, result.getCommon().getResultCode());
			}
			
			tlo.put(TloData.CEK_RES_TIME, TloData.getNowDate());
			TloUtil.setTloData(tlo);
		}
	}
	/*
	@Pointcut("execution(* com.lgu.common.cekAi.auth.CekAuthAgent.logOutSvc(..))")
	private void pointCutCekAIAuthLogOutSvc() {
	
	}
		
	@Around("pointCutCekAIAuthLogOutSvc()")
	public Object tloAopCekAIAuthLogOutSvc(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.R5, TloConst.CA04);
		tlo.put(TloData.R7, TloData.getNowDate());
		
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {

			if (obj == null) {
				tlo.put(TloData.R6, CekAuthErrorCode.RC_60000000.getCode());
				
			}
			else {
				CekAuthResponseJSON result = (CekAuthResponseJSON) obj;
				tlo.put(TloData.R6, result.getCommon().getResultCode());
			}
			
			tlo.put(TloData.R8, TloData.getNowDate());
			TloUtil.setTloData(tlo);
		}
	}
	*/
	
	@Pointcut("execution(* com.lgu.common.cekAi.auth.CekAuthAgent.getAuthorizationCode(..))")
	private void pointCutCekAIAuthGetAuthorizationCode() {
	
	}
		
	@Around("pointCutCekAIAuthGetAuthorizationCode()")
	public Object tloAopCekAIAuthGetAuthorizationCode(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.CEK_SVC_CLASS, TloConst.CA01);
		tlo.put(TloData.CEK_REQ_TIME, TloData.getNowDate());
		
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {

			if (obj == null) {
				tlo.put(TloData.CEK_RESULT_CODE, CekAuthErrorCode.RC_60000000.getCode());
				
			}
			else {
				CekAuthResponseJSON result = (CekAuthResponseJSON) obj;
				tlo.put(TloData.CEK_RESULT_CODE, result.getCommon().getResultCode());
			}
			
			tlo.put(TloData.CEK_RES_TIME, TloData.getNowDate());
			TloUtil.setTloData(tlo);
		}
	}
	
/*	
	@Pointcut("execution(* com.lgu.common.cekAi.auth.CekAuthAgent.resetUserInfo(..))")
	private void pointCutCekAIAuthResetUserInfo() {
	
	}
		
	@Around("pointCutCekAIAuthResetUserInfo()")
	public Object tloAopCekAIAuthResetUserInfo(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.R5, TloConst.CA06);
		tlo.put(TloData.R7, TloData.getNowDate());
		
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {

			if (obj == null) {
				tlo.put(TloData.R6, CekAuthErrorCode.RC_60000000.getCode());
				
			}
			else {
				CekAuthResponseJSON result = (CekAuthResponseJSON) obj;
				tlo.put(TloData.R6, result.getCommon().getResultCode());
			}
			
			tlo.put(TloData.R8, TloData.getNowDate());
			TloUtil.setTloData(tlo);
		}
	}
	
	*/
	@Pointcut("execution(* com.lgu.common.cekAi.auth.CekAuthAgent.getSvcAuthInf*(..))")
	private void pointCutCekAIAuthGetSvcAuthInfo() {
	
	}
		
	@Around("pointCutCekAIAuthGetSvcAuthInfo()")
	public Object tloAopCekAIAuthGetSvcAuthInfo(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.CEK_SVC_CLASS, TloConst.CA03);
		tlo.put(TloData.CEK_REQ_TIME, TloData.getNowDate());
		
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {

			if (obj == null) {
				tlo.put(TloData.CEK_RESULT_CODE, CekAuthErrorCode.RC_60000000.getCode());
				
			}
			else {
				CekAuthResponseJSON result = (CekAuthResponseJSON) obj;
				tlo.put(TloData.CEK_RESULT_CODE, result.getCommon().getResultCode());
			}
			
			tlo.put(TloData.CEK_RES_TIME, TloData.getNowDate());
			TloUtil.setTloData(tlo);
		}
	}
	
	//#Clova
	
	@Pointcut("execution(* com.lgu.common.clova.auth.ClovaAuthAgent.createAuthorizationCode(..))")
	private void pointCutClovaAuthCreateAuthorizationCode() {
	
	}
		
	@Around("pointCutClovaAuthCreateAuthorizationCode()")
	public Object tloAopClovaAuthCreateAuthorizationCode(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.CLOVA_SVC_CLASS, TloConst.CS01);
		tlo.put(TloData.CLOVA_REQ_TIME, TloData.getNowDate());
		
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {

			if (obj == null) {
				tlo.put(TloData.CLOVA_RESULT_CODE, ClovaErrorCode.RC_60000000.getCode());
				
			}
			else {
				ClovaAuthResponseBodyAuthCodeJSON result = (ClovaAuthResponseBodyAuthCodeJSON) obj;
				if(result.getCode() !=null && result.getCode().length() > 0){
					tlo.put(TloData.CLOVA_RESULT_CODE,ClovaErrorCode.RC_20000000.getCode());
				}else{
					tlo.put(TloData.CLOVA_RESULT_CODE,ClovaErrorCode.RC_60000000.getCode());
				}
			
			}
			
			tlo.put(TloData.CLOVA_RES_TIME, TloData.getNowDate());
			TloUtil.setTloData(tlo);
		}
	}
	
	
	@Pointcut("execution(* com.lgu.common.clova.auth.ClovaAuthAgent.createClovaAccessToken(..))")
	private void pointCutClovaAuthCreateClovaAccessToken() {
	
	}
		
	@Around("pointCutClovaAuthCreateClovaAccessToken()")
	public Object tloAopClovaAuthCreateClovaAccessToken(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.CLOVA_SVC_CLASS, TloConst.CS02);
		tlo.put(TloData.CLOVA_REQ_TIME, TloData.getNowDate());
		
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {

			if (obj == null) {
				tlo.put(TloData.CLOVA_RESULT_CODE, ClovaErrorCode.RC_60000000.getCode());
				
			}
			else {
				
				ClovaAuthResponseBodyAccessTokenJSON result = (ClovaAuthResponseBodyAccessTokenJSON) obj;
				if(result.getAccess_token() !=null && result.getAccess_token().length() > 0){
					tlo.put(TloData.CLOVA_RESULT_CODE,ClovaErrorCode.RC_20000000.getCode());
				}else{
					tlo.put(TloData.CLOVA_RESULT_CODE,ClovaErrorCode.RC_60000000.getCode());
				}
			}
			
			tlo.put(TloData.CLOVA_RES_TIME, TloData.getNowDate());
			TloUtil.setTloData(tlo);
		}
	}
	
	

	@Pointcut("execution(* com.lgu.common.clova.auth.ClovaAuthAgent.deleteClovaAccessToken(..))")
	private void pointCutClovaAuthDeleteClovaAccessToken() {
	
	}
		
	@Around("pointCutClovaAuthDeleteClovaAccessToken()")
	public Object tloAopClovaAuthDeleteClovaAccessToken(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.CLOVA_SVC_CLASS, TloConst.CS03);
		tlo.put(TloData.CLOVA_REQ_TIME, TloData.getNowDate());
		
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {

			if (obj == null) {
				tlo.put(TloData.CLOVA_RESULT_CODE, ClovaErrorCode.RC_60000000.getCode());
				
			}
			else {
				ClovaAuthResponseBodyDeleteTokenJSON result = (ClovaAuthResponseBodyDeleteTokenJSON) obj;
				if(result.getAccess_token() !=null && result.getAccess_token().length() > 0 ){
					tlo.put(TloData.CLOVA_RESULT_CODE,ClovaErrorCode.RC_20000000.getCode());
				}else{
					tlo.put(TloData.CLOVA_RESULT_CODE,ClovaErrorCode.RC_60000000.getCode());
				}
			}
			
			tlo.put(TloData.CLOVA_RES_TIME, TloData.getNowDate());
			TloUtil.setTloData(tlo);
		}
	}
	
	
	@Pointcut("execution(* com.lgu.common.clova.auth.ClovaAuthAgent.refreshClovaAccessToken(..))")
	private void pointCutClovaAuthRefreshClovaAccessToken() {
	
	}
		
	@Around("pointCutClovaAuthRefreshClovaAccessToken()")
	public Object tloAopClovaAuthRefreshClovaAccessToken(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.CLOVA_SVC_CLASS, TloConst.CS04);
		tlo.put(TloData.CLOVA_REQ_TIME, TloData.getNowDate());
		
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {

			if (obj == null) {
				tlo.put(TloData.CLOVA_RESULT_CODE, ClovaErrorCode.RC_60000000.getCode());
				
			}
			else {
				ClovaAuthResponseBodyAccessTokenJSON result = (ClovaAuthResponseBodyAccessTokenJSON) obj;
				if(result.getAccess_token() !=null && result.getAccess_token().length() > 0){
					tlo.put(TloData.CLOVA_RESULT_CODE,ClovaErrorCode.RC_20000000.getCode());
				}else{
					tlo.put(TloData.CLOVA_RESULT_CODE,ClovaErrorCode.RC_60000000.getCode());
				}
			}
			
			tlo.put(TloData.CLOVA_RES_TIME, TloData.getNowDate());
			TloUtil.setTloData(tlo);
		}
	}
	
	@Pointcut("execution(* com.lgu.common.aisv.auth.AisvAuthAgent.getNidInf*(..))")
	private void pointCutAisvAuthGetNidInfo2() {
	
	}
		
	@Around("pointCutAisvAuthGetNidInfo2()")
	public Object pointCutAisvAuthGetNidInfo2(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.AISV_SVC_CLASS, TloConst.AI01);
		tlo.put(TloData.AISV_REQ_TIME, TloData.getNowDate());
		
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {

			if (obj == null) {
				tlo.put(TloData.AISV_RESULT_CODE, AisvAuthErrorCode.RC_60000000.getCode());
				
			}
			else {
				AisvAuthResponseJSON result = (AisvAuthResponseJSON) obj;
				tlo.put(TloData.AISV_RESULT_CODE	, result.getCommon().getResultCode());
			}
			
			tlo.put(TloData.AISV_RES_TIME, TloData.getNowDate());
			TloUtil.setTloData(tlo);
		}
	}
	
	@Pointcut("execution(* com.lgu.common.aisv.auth.AisvAuthAgent.tempIdRegis*(..))")
	private void pointCutAisvAuthTempIdRegist2() {
	
	}
		
	@Around("pointCutAisvAuthTempIdRegist2()")
	public Object pointCutAisvAuthTempIdRegist2(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.AISV_SVC_CLASS, TloConst.AI02);
		tlo.put(TloData.AISV_REQ_TIME, TloData.getNowDate());
		
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {

			if (obj == null) {
				tlo.put(TloData.AISV_RESULT_CODE, AisvAuthErrorCode.RC_60000000.getCode());
				
			}
			else {
				AisvAuthResponseJSON result = (AisvAuthResponseJSON) obj;
				tlo.put(TloData.AISV_RESULT_CODE	, result.getCommon().getResultCode());
			}
			
			tlo.put(TloData.AISV_RES_TIME, TloData.getNowDate());
			TloUtil.setTloData(tlo);
		}
	}
	
	@Pointcut("execution(* com.lgu.common.clova.auth.ClovaAuthAgent.discoveryClova(..))")
	private void pointCutDiscoveryClova() {
	
	}
		
	@Around("pointCutDiscoveryClova()")
	public Object tloAopCutDiscoveryClova(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.CIC_SVC_CLASS, TloConst.CI01);
		tlo.put(TloData.CIC_REQ_TIME, TloData.getNowDate());
		
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {

			if (obj == null) {
				tlo.put(TloData.CIC_RESULT_CODE, CicErrorCode.RC_60000000.getCode());
				
			}
			else {
				ClovaAuthResponseDiscoveryJSON result = (ClovaAuthResponseDiscoveryJSON) obj;
				if(result.getResult() !=null ){
					tlo.put(TloData.CIC_RESULT_CODE,CicErrorCode.RC_20000000.getCode());
				}else{
					tlo.put(TloData.CIC_RESULT_CODE,CicErrorCode.RC_30000000.getCode());
				}
			}
			
			tlo.put(TloData.CIC_RES_TIME, TloData.getNowDate());
			TloUtil.setTloData(tlo);
		}
	}
	
	@Pointcut("execution(* com.lgu.common.clova.auth.ClovaAuthAgent.connectClova(..))")
	private void pointCutConnectClova() {
	
	}
		
	@Around("pointCutConnectClova()")
	public Object tloAopCutConnectClova(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.CIC_SVC_CLASS, TloConst.CI02);
		tlo.put(TloData.CIC_REQ_TIME, TloData.getNowDate());
		
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {

			if (obj == null) {
				tlo.put(TloData.CIC_RESULT_CODE, CicErrorCode.RC_60000000.getCode());
				
			}
			else {
				ClovaAuthResponseDiscoveryJSON result = (ClovaAuthResponseDiscoveryJSON) obj;
				if(result.getResult() !=null ){
					tlo.put(TloData.CIC_RESULT_CODE,CicErrorCode.RC_20000000.getCode());
				}else{
					tlo.put(TloData.CIC_RESULT_CODE,CicErrorCode.RC_30000000.getCode());
				}
			}
			
			tlo.put(TloData.CIC_RES_TIME, TloData.getNowDate());
			TloUtil.setTloData(tlo);
		}
	}
	
	@Pointcut("execution(* com.lgu.common.clova.auth.ClovaAuthAgent.disConnectClova(..))")
	private void pointCutDisConnectClova() {
	
	}
		
	@Around("pointCutDisConnectClova()")
	public Object tloAopCutDisConnectClova(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.CIC_SVC_CLASS, TloConst.CI03);
		tlo.put(TloData.CIC_REQ_TIME, TloData.getNowDate());
		
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {

			if (obj == null) {
				tlo.put(TloData.CIC_RESULT_CODE, CicErrorCode.RC_60000000.getCode());
				
			}
			else {
				ClovaAuthResponseDiscoveryJSON result = (ClovaAuthResponseDiscoveryJSON) obj;
				if(result.getResult() !=null ){
					tlo.put(TloData.CIC_RESULT_CODE,CicErrorCode.RC_20000000.getCode());
				}else{
					tlo.put(TloData.CIC_RESULT_CODE,CicErrorCode.RC_30000000.getCode());
				}
			}
			
			tlo.put(TloData.CIC_RES_TIME, TloData.getNowDate());
			TloUtil.setTloData(tlo);
		}
	}
	
	@Pointcut("execution(* com.lgu.common.datagift.DataGiftManager.getDataGiftList(..))")
	private void pointCutDataGiftList() {
	
	}
		
	@Around("pointCutDataGiftList()")
	public Object tloAopCutDataGiftList(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.DG_SVC_CLASS, TloConst.DG01);
		tlo.put(TloData.DG_REQ_TIME, TloData.getNowDate());
		
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {

			if (obj == null) {
				tlo.put(TloData.DG_RESULT_CODE, DataGiftErrorCode.RC_60000000.getCode());
			}
			else {
				DataGiftList result = (DataGiftList) obj;
				if(result.getResult().equalsIgnoreCase("success")){
					tlo.put(TloData.DG_RESULT_CODE,DataGiftErrorCode.RC_20000000.getCode());
				}else{
					tlo.put(TloData.DG_RESULT_CODE,DataGiftErrorCode.RC_30000000.getCode());
				}
			}
			
			tlo.put(TloData.DG_RES_TIME, TloData.getNowDate());
			TloUtil.setTloData(tlo);
		}
	}
	
	
	@Pointcut("execution(* com.lgu.common.datagift.DataGiftManager.getDataGiftReg(..))")
	private void pointCutDataGiftReg() {
	
	}
		
	@Around("pointCutDataGiftReg()")
	public Object tloAopCutDataGiftReg(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.DG_SVC_CLASS, TloConst.DG02);
		tlo.put(TloData.DG_REQ_TIME, TloData.getNowDate());
		
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {

			if (obj == null) {
				tlo.put(TloData.DG_RESULT_CODE, DataGiftErrorCode.RC_60000000.getCode());
			}
			else {
				DataGiftReg result = (DataGiftReg) obj;
				if(result.getResult().equalsIgnoreCase("success")){
					tlo.put(TloData.DG_RESULT_CODE,DataGiftErrorCode.RC_20000000.getCode());
				}else{
					tlo.put(TloData.DG_RESULT_CODE,DataGiftErrorCode.RC_30000000.getCode());
				}
			}
			
			tlo.put(TloData.DG_RES_TIME, TloData.getNowDate());
			TloUtil.setTloData(tlo);
		}
	}
	
	@Pointcut("execution(* com.lgu.common.datagift.DataGiftManager.getDataGiftOwnList(..))")
	private void pointCutDataGiftOwnList() {
	
	}
		
	@Around("pointCutDataGiftOwnList()")
	public Object tloAopCutDataGiftOwnList(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.DG_SVC_CLASS, TloConst.DG03);
		tlo.put(TloData.DG_REQ_TIME, TloData.getNowDate());
		
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {

			if (obj == null) {
				tlo.put(TloData.DG_RESULT_CODE, DataGiftErrorCode.RC_60000000.getCode());
			}
			else {
				DataGiftOwnList result = (DataGiftOwnList) obj;
				if(result.getResult().equalsIgnoreCase("success")){
					tlo.put(TloData.DG_RESULT_CODE,DataGiftErrorCode.RC_20000000.getCode());
				}else{
					tlo.put(TloData.DG_RESULT_CODE,DataGiftErrorCode.RC_30000000.getCode());
				}
			}
			
			tlo.put(TloData.DG_RES_TIME, TloData.getNowDate());
			TloUtil.setTloData(tlo);
		}
	}
	
	@Pointcut("execution(* com.lgu.common.datagift.DataGiftManager.getDataGiftCtnCheck(..))")
	private void pointCutDataGiftCheckCtn() {
	
	}
		
	@Around("pointCutDataGiftCheckCtn()")
	public Object tloAopCutDataGiftCheckCtn(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.DG_SVC_CLASS, TloConst.DG04);
		tlo.put(TloData.DG_REQ_TIME, TloData.getNowDate());
		
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			return obj;
			
		} finally {

			if (obj == null) {
				tlo.put(TloData.DG_RESULT_CODE, DataGiftErrorCode.RC_60000000.getCode());
			}
			else {
				DataGiftCtnCheck result = (DataGiftCtnCheck) obj;
				if(result.getResult().equalsIgnoreCase("success")){
					tlo.put(TloData.DG_RESULT_CODE,DataGiftErrorCode.RC_20000000.getCode());
				}else{
					tlo.put(TloData.DG_RESULT_CODE,DataGiftErrorCode.RC_30000000.getCode());
				}
			}
			
			tlo.put(TloData.DG_RES_TIME, TloData.getNowDate());
			TloUtil.setTloData(tlo);
		}
	}
	

}
