package com.lgu.ccss.component;

import org.junit.Ignore;
import org.junit.Test;

public class AIAuthAgentTest {

	@Ignore
	@Test
	public void testCreateDeviceToken() {
		/*AuthAgent aiPlatformIf = new AuthAgent();
		RequestJSON aiRequestVo = new RequestJSON();
		AuthResponseJSON aiPlatformResVo = new AuthResponseJSON();
		
		aiRequestVo.getCommon().getLogData().setClientIp("123.123.123.123");
		aiRequestVo.getCommon().getLogData().setDevInfo("PAD");
		aiRequestVo.getCommon().getLogData().setOsInfo("android_5.5.5");
		aiRequestVo.getCommon().getLogData().setNwInfo("4G");
		aiRequestVo.getCommon().getLogData().setSvcName("");
		aiRequestVo.getCommon().getLogData().setDevModel("LE-E250");
		aiRequestVo.getCommon().getLogData().setCarrierType("L");
		aiRequestVo.getCommon().getLogData().setSvcType("SVC_003");
		aiRequestVo.getCommon().getLogData().setDevType("");
		aiRequestVo.getCommon().setApiId("SERVICE_LOGIN_AI");
		aiRequestVo.getCommon().setCcssToken("Temp_ccss_Token");
		aiRequestVo.getCommon().getDevice().setDeviceType("PND");
		aiRequestVo.getCommon().getDevice().setAppType("SERVICE_AGENT");
		aiRequestVo.getCommon().getDevice().setCarOem("");
		aiRequestVo.getParam().setCtn("01022335588");
		aiRequestVo.getParam().setSerial("temp_device_serial_number");
		
		String aiServerHost = "http://112.172.129.170";
		String aiServerPort = "9999";
		

		try {
			aiPlatformResVo = aiPlatformIf.createDeviceToken(aiRequestVo,aiRequestVo.getParam().getCtn(),aiRequestVo.getParam().getSerial());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	
		assertNotNull("AIPlatform Response is null!", aiPlatformResVo);
		assertNotNull("DeviceToken is null",aiPlatformResVo.getBody().getDeviceToken());
		assertNotEquals("SerialNumber is not Equals", aiRequestVo.getParam().getSerial(), aiPlatformResVo.getBody().getDeviceToken().getDeviceSN());*/
		
	}

	@Ignore
	@Test
	public void testCreatePlatformToken() {
		/*AuthAgent aiPlatformIf = new AuthAgent();
		RequestJSON aiRequestVo = new RequestJSON();
		AuthResponseJSON aiPlatformResVo = new AuthResponseJSON();
		
		aiRequestVo.getCommon().getLogData().setClientIp("123.123.123.123");
		aiRequestVo.getCommon().getLogData().setDevInfo("PAD");
		aiRequestVo.getCommon().getLogData().setOsInfo("android_5.5.5");
		aiRequestVo.getCommon().getLogData().setNwInfo("4G");
		aiRequestVo.getCommon().getLogData().setSvcName("");
		aiRequestVo.getCommon().getLogData().setDevModel("LE-E250");
		aiRequestVo.getCommon().getLogData().setCarrierType("L");
		aiRequestVo.getCommon().getLogData().setSvcType("SVC_003");
		aiRequestVo.getCommon().getLogData().setDevType("DEV_004");
		aiRequestVo.getCommon().setApiId("SERVICE_LOGIN_AI");
		aiRequestVo.getCommon().setCcssToken("Temp_ccss_Token");
		aiRequestVo.getCommon().getDevice().setDeviceType("PND");
		aiRequestVo.getCommon().getDevice().setAppType("SERVICE_AGENT");
		aiRequestVo.getCommon().getDevice().setCarOem("");
		aiRequestVo.getParam().setCtn("01022335588");
		aiRequestVo.getParam().setSerial("temp_device_serial_number");
		
		String aiServerHost = "http://112.172.129.170";
		String aiServerPort = "9999";
		
		try {
			aiPlatformResVo = aiPlatformIf.createPlatformToken(aiRequestVo,"deviceToken","01022335588", "temp_device_serial_number");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertNotNull("AIPlatform Response is null!", aiPlatformResVo);
		assertNotNull("DeviceToken is null",aiPlatformResVo.getBody().getDeviceToken());
		assertNotNull("PlatformToken is null",aiPlatformResVo.getBody().getPlatformToken());
		assertNotEquals("SerialNumber is not Equals", aiRequestVo.getParam().getSerial(), aiPlatformResVo.getBody().getDeviceToken().getDeviceSN());*/
		
	}

}
