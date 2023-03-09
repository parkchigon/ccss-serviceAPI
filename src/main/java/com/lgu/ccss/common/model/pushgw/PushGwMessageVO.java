package com.lgu.ccss.common.model.pushgw;

import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.lgu.ccss.common.model.mgr.MgrAppUserVO;
import com.lgu.common.util.DateUtils;
import com.lgu.common.util.JsonUtil;
import com.lgu.common.util.keygenerator.KeyGenerator;

public class PushGwMessageVO {
	

	public static final String USE_Y = "Y";
	
	public static final String DEF_TRUE = "1";
	public static final String DEF_FALSE = "0";
	
	public static final String AM= "AM";
	public static final String BM = "BM";
	
	public static final String MASSAGE_TYPE_SINGLE = "0001"; 
	
	public static final String MASSAGE_TYPE_NOTICE = "0002"; 
	
	public static final String REQ_PART_SP = "SP";
	
	public static final String REQ_PART_WEB = "WEB";
	
	public static final String MSG_STATUS_READY="0000";
	
	//SEND2CAR
	public static final String SEND2CAR_PUSH_CODE = "S001";
	//일정
	public static final String SCHEDULE_PUSH_CODE = "S002";
	//도착 알림
	public static final String ARRIVAL_NOTI_PUSH_CODE = "S003";
	//EV 완충메시지
	public static final String EV_CHARGINGMSG_PUSH_CODE = "S004";
	//요금제 종료 예고
	public static final String PLAN_END_NOTICE_PUSH_CODE = "M001";
	
	//공지 사항
	public static final String NOTICE_PUSH_CODE = "N001";
	
	//
	public static final String DEVICE_TYPE_ANDROID = "A";
	public static final String DEVICE_TYPE_IOS = "I";
	
	public static final String ANDROID="android";
	public static final String IOS="ios";
	
	public static final String THINKWARE="thinkware";
	public static final String SY="SY";
	public static final String NS="NS";
	
	
	private String msgId; 
	private String msgStatus; 
	private String code; 
	private String msgTitle;
	//private MqttMessageContentVO msgCont; 
	private String msgCont; 
	private String msgType; 
	private String recvPhoneNo;
	private String sendType;
	private String svrId = System.getProperty("SERVER_ID");
	private String orgNo;
	private String callbackNo;
	private String sendDt;
	private String regId;
	private String regDt;
	private String updId;
	private String updDt;
	private String reqPart;
	private String deviceType;
	private String carOem;
	
	public PushGwMessageVO(){
		sendDt = DateUtils.getCurrentTime(DateUtils.DATE_FORMAT_YMDHMS);
	}
	
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getMsgStatus() {
		return msgStatus;
	}
	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsgTitle() {
		return msgTitle;
	}
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}
	
	public String getMsgCont() {
		return msgCont;
	}
	public void setMsgCont(String msgCont) {
		this.msgCont = msgCont;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getRecvPhoneNo() {
		return recvPhoneNo;
	}
	public void setRecvPhoneNo(String recvPhoneNo) {
		this.recvPhoneNo = recvPhoneNo;
	}
	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	public String getSvrId() {
		return svrId;
	}
	public void setSvrId(String svrId) {
		this.svrId = svrId;
	}
	public String getOrgNo() {
		return orgNo;
	}
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}
	public String getCallbackNo() {
		return callbackNo;
	}
	public void setCallbackNo(String callbackNo) {
		this.callbackNo = callbackNo;
	}
	public String getSendDt() {
		return sendDt;
	}
	public void setSendDt(String sendDt) {
		this.sendDt = sendDt;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getUpdId() {
		return updId;
	}
	public void setUpdId(String updId) {
		this.updId = updId;
	}
	public String getUpdDt() {
		return updDt;
	}
	public void setUpdDt(String updDt) {
		this.updDt = updDt;
	}
	
	public String getReqPart() {
		return reqPart;
	}

	public void setReqPart(String reqPart) {
		this.reqPart = reqPart;
	}

	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getCarOem() {
		return carOem;
	}

	public void setCarOem(String carOem) {
		this.carOem = carOem;
	}

	public static PushGwMessageVO makePushMessage(MgrAppUserVO mgrAppUserVO,String sendType,String msgType,String code,String msgTitle,String contentMsg, String sendDt, String expDt, String carOem) throws Exception{
		
		PushGwMessageVO pushMessageVO = new PushGwMessageVO();
		//22자리 KeyGeneration
		pushMessageVO.setMsgId(KeyGenerator.createCommonShardKey());
		//0000 (전송대기)
		pushMessageVO.setMsgStatus(PushGwMessageVO.MSG_STATUS_READY);
		
		//S001,S002,S003,.....
		pushMessageVO.setCode(code);
		//제목
		pushMessageVO.setMsgTitle(msgTitle);
		
		//single / notice
		pushMessageVO.setMsgType(msgType);
		//수신자 서비스 키
		
		//pushMessageVO.setRecvPhoneNo(mgrAppUserVO.getMgrappCtn());
		pushMessageVO.setRecvPhoneNo(mgrAppUserVO.getPushId());
		
		//쌍용/thinkware 구분을 위한 구분자
		pushMessageVO.setCarOem(carOem);
		
		//0 / 1 (0 : 긴급푸쉬메시지 / 1 : 일반푸쉬 메시지)
		//pushMessageVO.setSendType(sendType); 미사용
		
		String deviceType = mgrAppUserVO.getOsType();
		
		if(deviceType.equals(PushGwMessageVO.ANDROID)){
			pushMessageVO.setDeviceType(PushGwMessageVO.DEVICE_TYPE_ANDROID);
			
			HashMap<String,String> pushGwMessageAndroidContentMap = new HashMap<String,String>();
			pushGwMessageAndroidContentMap.put("code", code);
			pushGwMessageAndroidContentMap.put("title", msgTitle);
			pushGwMessageAndroidContentMap.put("content", contentMsg);
			
			//JSON Payload
			pushMessageVO.setMsgCont(JsonUtil.marshallingJson(pushGwMessageAndroidContentMap));
		}else{
			pushMessageVO.setDeviceType(PushGwMessageVO.DEVICE_TYPE_IOS);
			
			HashMap<String,Object> alert = new HashMap<String,Object>();
			alert.put("code", code);
			
			if (code.equals(ARRIVAL_NOTI_PUSH_CODE)) {
				alert.put("title", msgTitle);
				alert.put("content", contentMsg);
			} else if (code.equals(EV_CHARGINGMSG_PUSH_CODE)) {
				alert.put("title", msgTitle);
				alert.put("body", contentMsg);
				alert.put("content", contentMsg);
			} else {
				alert.put("title", contentMsg);
				alert.put("content", "");
			}
			
			PushGwMessageIosContentVO pushGwMessageContentVO = new PushGwMessageIosContentVO();
			HashMap<String,Object> aps = new HashMap<String,Object>();
			aps.put("alert", alert);
			aps.put("sound", "ring");
			
			pushGwMessageContentVO.setAps(aps);

		
			
			//JSON Payload
			pushMessageVO.setMsgCont(JsonUtil.marshallingJson(pushGwMessageContentVO));
		}
		
		pushMessageVO.setReqPart(PushGwMessageVO.REQ_PART_SP);
		
		pushMessageVO.setSendType(sendType);
		
		if(sendDt !=null){
			pushMessageVO.setSendDt(sendDt);
		}
		
		return pushMessageVO;
	}

	@Override
	public String toString() {
		return "PushGwMessageVO [msgId=" + msgId + ", msgStatus=" + msgStatus
				+ ", code=" + code + ", msgTitle=" + msgTitle + ", msgCont="
				+ msgCont + ", msgType=" + msgType + ", recvPhoneNo="
				+ recvPhoneNo + ", sendType=" + sendType + ", svrId=" + svrId
				+ ", orgNo=" + orgNo + ", callbackNo=" + callbackNo
				+ ", sendDt=" + sendDt + ", regId=" + regId + ", regDt="
				+ regDt + ", updId=" + updId + ", updDt=" + updDt
				+ ", reqPart=" + reqPart + ", deviceType=" + deviceType + "]";
	}

	
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		
	
		
		HashMap<String,Object> alert = new HashMap<String,Object>();
		alert.put("code", "code");
		alert.put("title", "msgTitle");
		alert.put("content", "contentMsg");
		
		PushGwMessageIosContentVO pushGwMessageContentVO = new PushGwMessageIosContentVO();
		HashMap<String,Object> aps = new HashMap<String,Object>();
		aps.put("alert", alert);
		
		pushGwMessageContentVO.setAps(aps);
		
		System.out.println("IOS:" + JsonUtil.marshallingJson(pushGwMessageContentVO));
		
		HashMap<String,String> pushGwMessageAndroidContentMap = new HashMap<String,String>();
		pushGwMessageAndroidContentMap.put("MSG1", "MESSAGE");
		pushGwMessageAndroidContentMap.put("MSG2", "MESSAGE");
		pushGwMessageAndroidContentMap.put("PushCtrl", "MESSAGE");
		
		System.out.println("ANDROID:" +JsonUtil.marshallingJson(pushGwMessageAndroidContentMap));
		
	
		
	}
	
	
}