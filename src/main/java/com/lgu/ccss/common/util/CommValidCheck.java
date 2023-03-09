package com.lgu.ccss.common.util;

import java.beans.Expression;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lgu.common.util.CommMessageUtil;
import com.lgu.common.util.StringUtils;


public class CommValidCheck {

	private static final Logger logger = LoggerFactory.getLogger(CommValidCheck.class);

	private CommValidCheck() {
	}

	private static class SingletonHolder {
		final static CommValidCheck single = new CommValidCheck();
	}

	public static CommValidCheck getInstance() {
		return SingletonHolder.single;
	}


	/*private static CommCodeService commCodeService;
	@Autowired
	public void setCommCodeService(CommCodeService _commCodeService) {
		commCodeService = _commCodeService;
	}

	public List<CommCodeBO> getCommCodeList() throws Exception{
		return commCodeService.getCommCodeList();
	}

	public List<CommCodeBO> getCommCode(String grpCd) throws Exception{
		return commCodeService.getCommCode(grpCd);
	}*/

	/**
	 * 인터페이스 ID 체크
	 * @param infDef : 정의된 인터페이스 아이디
	 * @param infReq : 요청이 들어온 인터페이스 아이디
	 * @return	 : boolean
	 * @throws Exception
	 */
	public boolean interfaceIdCheck(String infDef, String infReq, HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(!infDef.equals(infReq)){ //체크 실패
			res.setHeader("interface_id", infDef);
			//res.setHeader("result_status", CommMessageUtil.getMessage("err_inf_id_cd")); // 정의되지 않은 연동시스템 오류 0008
			res.setCharacterEncoding("UTF-8");
			res.setHeader("Cache-Control", "no-cache");
			//logger.error("result_msg : " + CommMessageUtil.getMessage("err_inf_id") + ", InterfaceID " + infReq);
			//MDC.put("RESULT_STATUS", CommMessageUtil.getMessage("err_inf_id_cd"));
			
			return false;
		}
		return true;
	}


	/**
	 * session ID 체크
	 * @param sessionId : session ID
	 * @return	 : boolean
	 * @throws Exception
	 */
	public boolean sessionIdCheck(String sessionId, HttpServletRequest req, HttpServletResponse res) throws Exception {
		logger.debug("class : CommValidCheck - method : sessionIdCheck");

		if(sessionId == null || sessionId.equals("")){ //세션 아이디가 없을 경우
			res.setHeader("interface_id", req.getHeader("interface_id"));
			res.setHeader("result_status", "err_request_not_valid_cd"); // 유효하지 않은 요청 0006
			logger.debug("result_msg : " + "err_request_not_valid");
			res.setCharacterEncoding("UTF-8");
			res.setHeader("Cache-Control", "no-cache");
			MDC.put("RESULT_STATUS", "err_request_not_valid_cd");
			
			res.getWriter().write(ResultCodeUtil.createResultMsgToJsonString(ResultCodeUtil.RC_3C003000, null));
			return false;
		}


		return true;
	}

	/**
	 * 필수 Parameter 체크
	 * @param Object : 체크 대상 BO
	 * @param String : 체크 parameter
	 * @return	 notParam : null or empty parameter name
	 * @throws Exception
	 */
	public String mandatoryParamCheck(Object obj, String args, HttpServletRequest req, HttpServletResponse res) throws Exception {
		logger.debug("class : CommValidCheck - method : mandatoryParamCheck");
		String[] params = args.split(",");
		Expression ex = null ;
		StringBuffer sb = new StringBuffer();
		
		String notParam = "";
		for(int i=0; i < params.length; i++){
			//getter method naming
			String param = params[i].trim();
			String getName = "get" + param.substring(0,1).toUpperCase() + param.substring(1) ;
			
			sb.append(getName).append(" : ").append(param).append(" ");
			
			ex = new Expression(obj, getName, new Object[] {});
			ex.execute();
			if(StringUtils.isEmpty((String)ex.getValue())){
				notParam = notParam.equals("") ? param : (notParam + ", " + param);
			}
		}
		logger.debug(sb.toString());
		
		if(!notParam.equals("")){  	
			res.setHeader("interface_id", req.getHeader("interface_id"));
			res.setHeader("result_status", CommMessageUtil.getMessage("err_mandatory_param_cd")); // 필수 parameter 누락 0005
			res.setCharacterEncoding("UTF-8");
			res.setHeader("Cache-Control", "no-cache");
			logger.error("result_msg : " + CommMessageUtil.getMessage("err_mandatory_param") + " param " + notParam);
			MDC.put("RESULT_STATUS", CommMessageUtil.getMessage("err_mandatory_param_cd"));
		}
		return notParam;
	}

}
