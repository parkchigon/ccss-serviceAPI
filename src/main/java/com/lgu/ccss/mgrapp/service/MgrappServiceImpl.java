package com.lgu.ccss.mgrapp.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;

import com.lgu.ccss.common.dao.mgr.MgrAppCallbackDao;
import com.lgu.ccss.common.dao.mgr.MgrAppOneIdAuthInfo;
import com.lgu.ccss.common.model.CallBackInfoVO;
import com.lgu.ccss.common.model.OneIdAuthInfoVO;
import com.lgu.ccss.mgr.cekInfotainment.service.searchOneId.CekSearchOndIdService;
import com.lgu.common.ai.auth.AuthConst;
import com.lgu.common.util.JsonUtil;

@Service("mgrappService")
public class MgrappServiceImpl implements MgrappService {

	private static final Logger logger = LoggerFactory.getLogger(MgrappServiceImpl.class);
	
	private static final String IOT_CTN_RETURN_TYPE ="020";
	
	private static final String DEF_Y = "Y";
	
	private static final String DEF_N = "N";
	
	@Resource(name = "cekSearchOndIdService")
	private  CekSearchOndIdService cekSearchOndIdService;
	
	@Autowired
	private MgrAppOneIdAuthInfo mgrAppOneIdAuthInfo;
	
	/*public static String test = "{\"returnType\":\"1\",\"rt\":\"2\",\"rtMsg\":\"3\",\"ssoKey\":\"4\",\"loginType\":\"5\",\"oneidEmailAddr\":\"6\",\"serviceKey\":\"7\",\"name\":\"8\",\"lgtType\":\"9\",\"pwUpdateDt\":\"10\",\"tosServiceCd\":\"11\",\"idType\":\"12\",\"oneIdMbrNo\":\"13\",\"mgrappId\":\"114366988186288137\"}";

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		Object obj = new Object();
		obj= JsonUtil.unmarshallingJson(test, Object.class);
		
				
		System.out.println(obj.toString());
		
		
	} */
	@Autowired
	private MgrAppCallbackDao mgrAppCallbackDao;
	
	/**
	 *  Home IOT 로그인 > 결과 
	 */
	@Override
	public void saveLoginHomeIOTCallback(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model, MultiValueMap<String, String> requstBody) {
		try {
			
			String mgrappId = requstBody.getFirst("mgrappId");
			String returnData = JsonUtil.marshallingJson(requstBody);
			
	
			CallBackInfoVO callBackInfoVO  = new CallBackInfoVO();
			callBackInfoVO.setMgrappId(mgrappId); 
			callBackInfoVO.setServiceCode(AuthConst.SERVICE_CODE_IOT);
			callBackInfoVO.setReturnData(returnData.replaceAll("\\[", "").replaceAll("\\]",""));
			
			boolean regFlag = mgrAppCallbackDao.insertTbCallBackInfo(callBackInfoVO);
			
			if(regFlag){
				
				logger.info("Home IOT Login Call Back Info Save Success.  mgrappId({})  returnData({})",mgrappId,returnData);
			}else{
				
				logger.error("Home IOT Login Call Back Info Save Fail.  mgrappId({})  returnData({})",mgrappId,returnData);
			}
			
		} catch (Exception e) {
			logger.error("returnData Json Parsing Error.",e);
		
		}
		
	}
	
	/**
	 *  Home IOT 로그인 > 결과 
	 */
	@Override
	public String getAuthHomeIOTResult(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model,MultiValueMap<String, String> requstBody) {
		String returnURL = "";
		try {
			
			
			//String returnURL = "ccss://resultData";
			//String returnURL = "ccss://resultData?/";
			
			String returnType =requstBody.getFirst("returnType");
			String rt = requstBody.getFirst("rt");
			String rtMsg =requstBody.getFirst("rtMsg");
			String ssoKey =requstBody.getFirst("ssoKey");
			String loginType =requstBody.getFirst("loginType");
			String oneidEmailAddr =requstBody.getFirst("oneidEmailAddr");
			String serviceKey =requstBody.getFirst("serviceKey");
			String name =requstBody.getFirst("name");
			String lgtType =requstBody.getFirst("lgtType");
			String pwUpdateDt =requstBody.getFirst("pwUpdateDt");
			String tosServiceCd =requstBody.getFirst("tosServiceCd");
			String idType =requstBody.getFirst("idType");
			String oneIdMbrNo = requstBody.getFirst("oneIdMbrNo");
			String vtidYn = requstBody.getFirst("vtidYn");
			String vtidRqstRsnCd = requstBody.getFirst("vtidRqstRsnCd");
			
			String reqLoginCtn = requstBody.getFirst("reqLoginCtn");
			
			returnURL+="ccss://homeIOT?returnType=" + returnType
				+"&"	+"rt=" + rt
				+"&"	+"rtMsg=" + rtMsg
				+"&"	+"ssoKey=" + ssoKey
				+"&"	+"loginType=" + loginType
				+"&"	+"oneidEmailAddr=" + oneidEmailAddr
				+"&"	+"serviceKey=" + serviceKey
				+"&"	+"name=" + name
				+"&"	+"lgtType=" + lgtType
				+"&"	+"pwUpdateDt=" + pwUpdateDt
				+"&"	+"tosServiceCd=" + tosServiceCd
				+"&"	+"idType=" + idType
				+"&"	+"oneIdMbrNo=" + oneIdMbrNo
				+"&"	+"vtidYn=" + vtidYn
				+"&"	+"vtidRqstRsnCd=" + vtidRqstRsnCd;
			
			if(returnType.equals(IOT_CTN_RETURN_TYPE)){
				returnURL+= "&"	+"reqLoginCtn=" + reqLoginCtn;
			}
					
			model.addAttribute("returnUrl",returnURL);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnURL;
	}
	
	/**
	 *  지니 로그인 > 결과 ( 인증요청 , 토큰 요청 두가지 CASE 결과 Redirect) 
	 */
	@Override
	public String getAuthGenieResult(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model,MultiValueMap<String, String> requstBody) {
		
		String returnURL = "ccss://genie?";
		
		try {
			
			String access_token = requstBody.getFirst("access_token");
			String token_type = requstBody.getFirst("token_type");
			String refresh_token = requstBody.getFirst("refresh_token");
			String expires_in = requstBody.getFirst("expires_in");
			String scope = requstBody.getFirst("scope");
			
			returnURL+="access_token=" + access_token
					+"&"	+"token_type=" +token_type
					+"&"	+"refresh_token=" + refresh_token
					+"&"	+"expires_in=" + expires_in
					+"&"	+"scope=" + scope;
		
			model.addAttribute("returnUrl",returnURL);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnURL;
	}
	
	
	@Override
	public String getAuthCekOneIdResult(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model,MultiValueMap<String, String> requstBody) {
		String returnURL = "ccss://CekHomeIOT";
		
		try {
			
			String state =requstBody.getFirst("state");
			String client_id = requstBody.getFirst("client_id");
			String scope =requstBody.getFirst("scope");
			String response_type =requstBody.getFirst("response_type");
			String redirect_uri =requstBody.getFirst("redirect_uri");
			String membId =requstBody.getFirst("membId");
			
			String oneId="";
			String ssoKey="";
			String oneIdMemNo="";
			String loginStatus="";
			
			/*
			 * 1. membId로 TB_ ONEID_AUTHINFO 조회 
			 * */
			OneIdAuthInfoVO oneIdAuthInfoVO  = new OneIdAuthInfoVO();
			oneIdAuthInfoVO.setMembId(membId);
			oneIdAuthInfoVO = mgrAppOneIdAuthInfo.selectCekOneIdAuthInfo(oneIdAuthInfoVO);
		
			if(oneIdAuthInfoVO !=null){
				
				oneId = oneIdAuthInfoVO.getOneId();
				ssoKey = oneIdAuthInfoVO.getSsoKey();
				oneIdMemNo = oneIdAuthInfoVO.getOneIdMembNo();
				loginStatus = oneIdAuthInfoVO.getLoginStatus();
				
				returnURL+="?state=" + state
					+"&"	+"client_id=" + client_id
					+"&"	+"scope=" + scope
					+"&"	+"response_type=" + response_type
					+"&"	+"redirect_uri=" + redirect_uri
					+"&"	+"oneId=" + oneId
					+"&"	+"ssoKey=" + ssoKey
					+"&"	+"oneIdMemNo=" + oneIdMemNo
					+"&"	+"loginStatus=" + loginStatus
				    +"&"	+"existYn=" + DEF_Y;

			}else{
				returnURL+="?state=" + state
						+"&"	+"client_id=" + client_id
						+"&"	+"scope=" + scope
						+"&"	+"response_type=" + response_type
						+"&"	+"redirect_uri=" + redirect_uri
					    +"&"	+"existYn=" + DEF_N;
			} 
			
					
			model.addAttribute("returnUrl",returnURL);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnURL;
	}
	
}