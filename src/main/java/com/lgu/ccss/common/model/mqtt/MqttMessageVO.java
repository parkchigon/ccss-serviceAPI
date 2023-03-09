package com.lgu.ccss.common.model.mqtt;

import java.util.Calendar;

import com.lgu.common.util.DateUtils;
import com.lgu.common.util.JsonUtil;
import com.lgu.common.util.keygenerator.KeyGenerator;

public class MqttMessageVO {
	
	public static final String USE_Y = "Y";
	
	public static final String DEF_TRUE = "1";
	public static final String DEF_FALSE = "0";
	
	public static final String PND = "PND";
	public static final String AVN = "AVN";
	public static final String AM= "AM";
	public static final String BM = "BM";
	
	public static final String MASSAGE_TYPE_SINGLE = "0001"; 
	
	public static final String MASSAGE_TYPE_NOTICE = "0002"; 
	
	public static final String SEND_TYPE_EMERGENCY = "0"; 
	
	public static final String SEND_TYPE_NOMAL = "1"; 
	
	
	public static final String MSG_STATUS_READY="0000";
	//목적지
	public static final String TARGET_PUSH_CODE = "S001";
	//예약 목적지
	public static final String RESERVE_TARGET_PUSH_CODE = "S101";
	//구글 일정 
	public static final String GOOGLE_SCHEDULE_PUSH_CODE = "S002";
	//출발알림
	public static final String SCHEDULE_NOTI_PUSH_CODE = "S003";
	//카카오톡 위치 공유
	public static final String KAKAO_SHARE_LOC_PUSH_CODE = "S004";
	//타 지도앱 위치 공유
	public static final String OTHER_SHARE_LOC_PUSH_CODE = "S006";
	
	//Music 로그인
	public static final String MUSIC_LOGIN_LOGOUT_PUSH_CODE = "I001";
	//Music 로그아웃
	//public static final String MUSIC_LOGOUT_PUSH_CODE = "I011";
	
	//팟캐스트 로그인
	public static final String PODCAST_LOGIN_LOGOUT_PUSH_CODE = "I002";
	//팟캐스트 로그아웃
	//public static final String PODCAST_LOGOUT_PUSH_CODE = "I012";
	
	//IOT 로그인
	public static final String IOT_LOGIN_LOGOUT_PUSH_CODE = "I003";
	//IOT 로그아웃
	//public static final String IOT_LOGOUT_PUSH_CODE = "I013";
	
	//GOOGLE 로그인
	public static final String GOOGLE_LOGIN_LOGOUT_PUSH_CODE = "I004";
	//GOOGLE 로그아웃
	//public static final String GOOGLE_LOGOUT_PUSH_CODE = "I014";
	
	//네이버뮤직 연결하기
	public static final String NAVERMUSIC_LOGIN_LOGOUT_PUSH_CODE = "I005";
	
	//CLOVA 로그인
	public static final String CLOVA_LOGIN_LOGOUT_PUSH_CODE = "C001";
		
	
	//Cek 
	public static final String CEK_IOT_LOGIN_LOGOUT_PUSH_CODE = "CI01";
	
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
	private String expDt;
	private String rcvReport;
	private String readReport;
	private String sendTryCnt;
	private String deviceType;
	
	public MqttMessageVO(){
		rcvReport= MqttMessageVO.DEF_TRUE;
		readReport = MqttMessageVO.DEF_TRUE;
		sendDt = DateUtils.getCurrentTime(DateUtils.DATE_FORMAT_YMDHMS);
		expDt = DateUtils.getCurrentDate(Calendar.DATE, 1 ,DateUtils.DATE_FORMAT_YMDHMS);
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
	public String getExpDt() {
		return expDt;
	}
	public void setExpDt(String expDt) {
		this.expDt = expDt;
	}
	public String getRcvReport() {
		return rcvReport;
	}
	public void setRcvReport(String rcvReport) {
		this.rcvReport = rcvReport;
	}
	public String getReadReport() {
		return readReport;
	}
	public void setReadReport(String readReport) {
		this.readReport = readReport;
	}
	public String getSendTryCnt() {
		return sendTryCnt;
	}
	public void setSendTryCnt(String sendTryCnt) {
		this.sendTryCnt = sendTryCnt;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
	
	public static MqttMessageVO makeMqttMessage(String marketType, String deviceCtn ,String sendType,String msgType,String code,String msgTitle,String contentMsg,String sendDt, String expDt,MqttMessageContentVO mqttMessageContentVO) throws Exception{
		
		MqttMessageVO mqttMessageVO = new MqttMessageVO();
		//22자리 KeyGeneration
		mqttMessageVO.setMsgId(KeyGenerator.createCommonShardKey());
		//0000 (전송대기)
		mqttMessageVO.setMsgStatus(MqttMessageVO.MSG_STATUS_READY);
		//S001,S002,S003,.....
		mqttMessageVO.setCode(code);
		//제목
		mqttMessageVO.setMsgTitle(msgTitle);
		
		//JSON Payload
		mqttMessageVO.setMsgCont(JsonUtil.marshallingJson(mqttMessageContentVO.getContent()));
		//single / notice
		mqttMessageVO.setMsgType(msgType);
		//수신자번호
		mqttMessageVO.setRecvPhoneNo(deviceCtn);
		//0 / 1 (0 : 긴급푸쉬메시지 / 1 : 일반푸쉬 메시지)
		mqttMessageVO.setSendType(sendType);
		
		//String deviceType = connDeviceVO.getDeviceType();
		
		if(marketType.equals(MqttMessageVO.AM)){
			mqttMessageVO.setDeviceType(MqttMessageVO.AM);
		}else{
			mqttMessageVO.setDeviceType(MqttMessageVO.BM);
		}
		if(sendDt !=null){
			mqttMessageVO.setSendDt(sendDt);
		}
		if(expDt !=null){
			mqttMessageVO.setExpDt(expDt);
		}
		return mqttMessageVO;
	}

	@Override
	public String toString() {
		return "MqttMessageVO [msgId=" + msgId + ", msgStatus=" + msgStatus
				+ ", code=" + code + ", msgTitle=" + msgTitle + ", msgCont="
				+ msgCont + ", msgType=" + msgType + ", recvPhoneNo="
				+ recvPhoneNo + ", sendType=" + sendType + ", svrId=" + svrId
				+ ", orgNo=" + orgNo + ", callbackNo=" + callbackNo
				+ ", sendDt=" + sendDt + ", regId=" + regId + ", regDt="
				+ regDt + ", updId=" + updId + ", updDt=" + updDt + ", expDt="
				+ expDt + ", rcvReport=" + rcvReport + ", readReport="
				+ readReport + ", sendTryCnt=" + sendTryCnt + ", deviceType="
				+ deviceType + "]";
	}

	
	
}