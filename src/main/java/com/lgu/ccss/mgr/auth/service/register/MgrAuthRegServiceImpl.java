package com.lgu.ccss.mgr.auth.service.register;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.DeviceDao;
import com.lgu.ccss.common.dao.DeviceSessDao;
import com.lgu.ccss.common.dao.MemberDao;
import com.lgu.ccss.common.dao.mgr.MgrAppDeviceDao;
import com.lgu.ccss.common.dao.mgr.MgrAppSessionDao;
import com.lgu.ccss.common.dao.mgr.MgrAppUserDao;
import com.lgu.ccss.common.model.ConnDeviceVO;
import com.lgu.ccss.common.model.DeviceSessVO;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.RequestCommonLogDataJSON;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.model.mgr.MgrAppDeviceVO;
import com.lgu.ccss.common.model.mgr.MgrAppSessVO;
import com.lgu.ccss.common.model.mgr.MgrAppUserVO;
import com.lgu.ccss.common.model.setInfo.JsonSetInfo;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.ccss.mgr.auth.model.ResultMgrAuthRegJSON;
import com.lgu.common.model.ResultCode;
import com.lgu.common.ncas.NCASConst;
import com.lgu.common.ncas.NCASQueryManager;
import com.lgu.common.ncas.NCASResultData;
import com.lgu.common.ncas.SubsInfo;
import com.lgu.common.util.AES256Util;
import com.lgu.common.util.DateUtils;
import com.lgu.common.util.JsonUtil;
import com.lgu.common.util.RandomKeyMakerUtil;
import com.lgu.common.util.keygenerator.KeyGenerator;

@Service("mgrAuthRegService")
public class MgrAuthRegServiceImpl implements MgrAuthRegService {

	private static final Logger logger = LoggerFactory.getLogger(MgrAuthRegServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MNGR_LOGIN_ID,RequestJSON.PARAM_SERIAL
					,RequestJSON.PARAM_CTN,	RequestJSON.PARAM_PUSH_ID, 
					/*RequestJSON.PARAM_DEVICE_NM,*/ RequestJSON.PARAM_USER_NM,	RequestJSON.PARAM_MGR_APP_VER
					,RequestJSON.PARAM_MNGR_LOGIN_TYPE/*,RequestJSON.PARAM_CCSS_TOKEN*/
			));
	
	@Autowired
	private MgrAppUserDao mgrAppUserDao;
	@Autowired
	private MgrAppDeviceDao mgrAppDeviceDao;
	@Autowired
	private DeviceDao deviceDao;
	@Autowired
	private DeviceSessDao deviceSessDao;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private NCASQueryManager nCASQueryManager;
	@Value("#{config['max.user.cnt']}")
	private int maxUserCnt;
	@Value("#{config['mgrapp.deviceInfo.encrypt']}")
	private String mgrappDeviceInfoEncrypt;
	@Value("#{config['session.timeoutMin']}")
	private String sessionTimeoutMin;
	
	@Autowired
	private MgrAppSessionDao mgrAppSessionDao;
	
	@Override
	@Transactional
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		
		String api = reqJson.getCommon().getApiId();
		String mgrappLoginId = (String) reqJson.getParam().get(RequestJSON.PARAM_MNGR_LOGIN_ID);
		String encDeviceCtn = (String) reqJson.getParam().get(RequestJSON.PARAM_CTN);
		String encDeviceSerial = (String) reqJson.getParam().get(RequestJSON.PARAM_SERIAL);
		
		String decDeviceCtn ="";
		String decDeviceSerial ="";
		String ccssToken = (String) reqJson.getParam().get(RequestJSON.PARAM_CCSS_TOKEN);
		String appType =  (String) reqJson.getCommon().getDevice().getAppType(); 
		String loginType = (String) reqJson.getParam().get(RequestJSON.PARAM_MNGR_LOGIN_TYPE);
		String uuid = (String) reqJson.getParam().get(RequestJSON.PARAM_UUID);
		String deviceNm = (String) reqJson.getParam().get(RequestJSON.PARAM_DEVICE_NM);
		String randomKey = CCSSUtil.getMgrRandomKey();
		String connip = CCSSUtil.getMgrIp();
		
		ResponseJSON response = null;
		CheckResultData result = ValidationChecker.checkValidation(null,reqJson,CCSSConst.MANAGER_AUTH_REGISTER,mandatoryList);
		ResultMgrAuthRegJSON resultData = null;
		if (result.isStatus() == false) {
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
			return response;
		}else{
			
			try {
				//AM Reg
				if(ccssToken !=null && ccssToken.length() > 0){
					
					DeviceSessVO deviceSessVO = deviceSessDao.selectDeviceSess(ccssToken);
					
					if(deviceSessVO !=null){
						 //membId로 TB_MEMB trancToken 조회 
						MembVO membVO  = memberDao.selectMemberByID(deviceSessVO.getMembId());
						
						if(membVO !=null){
							
							try {
								
								decDeviceCtn = AES256Util.AESDecode(membVO.getTransToken(), (String) reqJson.getParam().get(RequestJSON.PARAM_CTN));
								decDeviceSerial = AES256Util.AESDecode(membVO.getTransToken(), (String) reqJson.getParam().get(RequestJSON.PARAM_SERIAL));
							
							}catch(BadPaddingException be){
								resultCode = ResultCodeUtil.RC_3C004006;
								logger.error("Failed to check Device Reg. deviceCtn({})  UICCID({}) mgrappLoginId({})  ccssToken({}) Exception({})",
										decDeviceCtn, decDeviceSerial,mgrappLoginId,ccssToken,be);
								 return ResultCodeUtil.createResultMsg(resultCode, api);
							
							}catch(IllegalBlockSizeException ibse){
								resultCode = ResultCodeUtil.RC_3C004006;
								logger.error("Failed to check Device Reg. deviceCtn({})  UICCID({}) mgrappLoginId({})  ccssToken({}) Exception({})",
										decDeviceCtn, decDeviceSerial,mgrappLoginId,ccssToken,ibse);
								 return ResultCodeUtil.createResultMsg(resultCode, api);
							} catch (Exception e) {
								resultCode = ResultCodeUtil.RC_4C005000;
								logger.error("Failed to check Device Reg. deviceCtn({})  UICCID({}) mgrappLoginId({})  ccssToken({}) Exception({})",
										decDeviceCtn, decDeviceSerial,mgrappLoginId,ccssToken,e);
								 return ResultCodeUtil.createResultMsg(resultCode, api);
							} 
							
							
							//1.Query Device Information(TB_CONN_DEVICE)
							ConnDeviceVO connDevice = new ConnDeviceVO();
							connDevice.setUiccId(decDeviceSerial);
							connDevice.setDeviceCtn(decDeviceCtn);
							connDevice.setUseYn(ConnDeviceVO.USE_Y);
							connDevice = deviceDao.getDeviceInfo(connDevice);
							
							if(connDevice == null) { //Unregistered device information
								resultCode = ResultCodeUtil.RC_3C002006;
								logger.error("Failed to check Device Reg. deviceCtn({})  UICCID({}) mgrappLoginId({})  ccssToken({})",
										decDeviceCtn, decDeviceSerial,mgrappLoginId,ccssToken);
								
							}else{
								
								logger.debug("connDeviceInfo({}) deviceCtn({}) UICCID({}) mgrappLoginId({}) ccssToken({})"
										, connDevice ,decDeviceCtn, decDeviceSerial,mgrappLoginId,ccssToken);
								//Set Tlo Data - Memb Id
								setTloData(connDevice);
								
								//AM & BM AppType / Device Validation Check.
								String marketType = membVO.getMarketType();
								if(appType.indexOf(marketType)  < 0){
									resultCode = ResultCodeUtil.RC_3C002007; 
									logger.error("APP & Device Type unmatched. appType({}) marketType({}) deviceCtn({})  UICCID({}) mgrappLoginId({})  ccssToken({})",
											appType,marketType,decDeviceCtn, decDeviceSerial,mgrappLoginId,ccssToken); 
								}else{
									
									//Query NCAS
									if(checkNcasStatus(decDeviceCtn,decDeviceSerial)){
										
										//2-1 Query DB(TB_MGRAPP_USER)
										MgrAppUserVO mgrAppUserVO = new MgrAppUserVO();
										//mgrAppUserVO.setMembId(connDevice.getMembId());
										mgrAppUserVO.setMgrappLoginId(mgrappLoginId);
										mgrAppUserVO.setUuid(uuid);
										mgrAppUserVO.setDeviceType((String) reqJson.getCommon().getDevice().getDeviceType());
										mgrAppUserVO.setLoginType(loginType);
										
										mgrAppUserVO = mgrAppUserDao.selectMgrUserInfo(mgrAppUserVO);
										
										if(mgrAppUserVO !=null && mgrAppUserVO.getUuid().equals(uuid)){ 
											
												//1개의 Memb ID에 대해 5개이상 등록 되어 있을 경우 실패 응답
												if(checkExceedAppUser(connDevice.getMembId(),maxUserCnt,mgrAppUserVO.getMgrappId())){
													resultCode = ResultCodeUtil.RC_6C000007;
													logger.error("Exceeded Number Of Device Users.  REQ_UUID({}) deviceCtn({}) UICCID({}) mgrappLoginId({}) ccssToken({})"
															,uuid, decDeviceCtn, decDeviceSerial,mgrappLoginId,ccssToken);
													response = ResultCodeUtil.createResultMsg(resultCode, api);
												}else{
													
													List<ConnDeviceVO> deviceList = deviceDao.getDeviceInfoListByMgrappId(mgrAppUserVO.getMgrappId());
													if(deviceList != null && deviceList.size() > 0) {
														boolean isMainY = false;
														for(int i = 0 ; i <deviceList.size(); i++) {
															if(deviceList.get(i).getMainUseYn().equalsIgnoreCase("Y")) {
																isMainY = true;
																break;
															}
														}
														if(isMainY) {
															MgrAppDeviceVO mgrAppDeviceVO = new MgrAppDeviceVO();
															mgrAppDeviceVO.setMembId(connDevice.getMembId());
															mgrAppDeviceVO.setMgrappId(mgrAppUserVO.getMgrappId());
															mgrAppDeviceVO.setDeviceNm(deviceNm);
															mgrAppDeviceVO.setMainUseYn("N");
															mgrAppDeviceDao.insertTbMgrAppDevice(mgrAppDeviceVO);
														}else {
															MgrAppDeviceVO mgrAppDeviceVO = new MgrAppDeviceVO();
															mgrAppDeviceVO.setMembId(connDevice.getMembId());
															mgrAppDeviceVO.setMgrappId(mgrAppUserVO.getMgrappId());
															mgrAppDeviceVO.setDeviceNm(deviceNm);
															mgrAppDeviceVO.setMainUseYn("Y");
															mgrAppDeviceDao.insertTbMgrAppDevice(mgrAppDeviceVO);
														}
													}else {
														MgrAppDeviceVO mgrAppDeviceVO = new MgrAppDeviceVO();
														mgrAppDeviceVO.setMembId(connDevice.getMembId());
														mgrAppDeviceVO.setMgrappId(mgrAppUserVO.getMgrappId());
														mgrAppDeviceVO.setDeviceNm(deviceNm);
														mgrAppDeviceVO.setMainUseYn("Y");
														mgrAppDeviceDao.insertTbMgrAppDevice(mgrAppDeviceVO);
													}
													
													logger.info("Device Reg Success  REQ_UUID({}) deviceCtn({}) UICCID({}) mgrappLoginId({}) ccssToken({})"
															,uuid, decDeviceCtn, decDeviceSerial,mgrappLoginId,ccssToken);
												}
												
											//}
										}else{ 
											// Reg Mgrapp User Info
											String mgrappId = KeyGenerator.createCommonShardKey();
											mgrAppUserVO = makeMgrAppUserInfo(reqJson,mgrappId);
											
											setOsTyep(mgrAppUserVO,(String) reqJson.getCommon().getLogData().getOsInfo());
											
											//기존 Deivce 연결 고리 삭제
											int removeCount =mgrAppDeviceDao.removeTbMgrAppDevice(mgrAppUserVO);
											logger.info("remove TB_MGRAPP_DEVICE Count({}) REQ_UUID({}) deviceCtn({}) UICCID({}) mgrappLoginId({}) ccssToken({})"
													,removeCount,uuid, decDeviceCtn, decDeviceSerial,mgrappLoginId,ccssToken);
											
											//1개의 Memb ID에 대해 5개이상 등록 되어 있을 경우 실패 응답
											if(checkExceedAppUser(connDevice.getMembId(),maxUserCnt,mgrAppUserVO.getMgrappId())){
												resultCode = ResultCodeUtil.RC_6C000007;
												logger.error("Exceeded Number Of Device Users.  REQ_UUID({}) deviceCtn({}) UICCID({}) mgrappLoginId({}) ccssToken({})"
														,uuid, decDeviceCtn, decDeviceSerial,mgrappLoginId,ccssToken);
												response = ResultCodeUtil.createResultMsg(resultCode, api);
											}else{
												int regCount = mgrAppUserDao.insertTbMgrAppUser(mgrAppUserVO);
												
												if(regCount > 0){
													logger.info("UserInfo  Reg Success  REQ_UUID({}) deviceCtn({}) UICCID({}) mgrappLoginId({})",uuid, decDeviceCtn, decDeviceSerial,mgrappLoginId);
												}else{
													logger.error("UserInfo Reg Fail  REQ_UUID({}) deviceCtn({}) UICCID({}) mgrappLoginId({})",uuid, decDeviceCtn, decDeviceSerial,mgrappLoginId);
												}
												//Reg Mgrapp Device(BM)
												
												String mainuseYn = "Y";
											
												int deviceRegCnt = regDevice(mgrAppUserVO.getMgrappId(),connDevice.getMembId(),deviceNm,mainuseYn);
												if(deviceRegCnt > 0){
													logger.info("Device  Reg Success  REQ_UUID({}) deviceCtn({}) UICCID({}) mgrappLoginId({})",uuid, decDeviceCtn, decDeviceSerial,mgrappLoginId);
												}else{
													logger.error("Device Reg Fail  REQ_UUID({}) deviceCtn({}) UICCID({}) mgrappLoginId({})",uuid, decDeviceCtn, decDeviceSerial,mgrappLoginId);
												}
											}
										}
										//20190117세션 넣기
										MgrAppUserVO mgrAppUser =   getMgrAppUserInfo(reqJson);
										if(mgrAppUser !=null){  
											//1-1 UUID 체크
								               if(uuid.equals(mgrAppUserVO.getUuid())){
								                  String mgrappId = mgrAppUserVO.getMgrappId();
								                  
								                  //2. MEMB_ID 리스트 획득  - TB_MGRAPP_DEVICE 조회(mgrappId) 
								                  MgrAppDeviceVO mgrAppDeviceVO= new MgrAppDeviceVO();
								                  mgrAppDeviceVO.setMgrappId(mgrappId);
								                  
								                  List<MgrAppDeviceVO> resultList = mgrAppDeviceDao.selectMgrDeviceInfoList(mgrAppDeviceVO);
								                  
								                  if(resultList !=null && resultList.size() > 0){ //Check성 조회로 null이 아니면 Pass

								                     String[] checkMembIdArr =makeMembIdArr(resultList);
								                        
								                     //2-1.MEMB_ID 리스트 상태 체크
								                     if(checkUserStatus(checkMembIdArr)){
					
								                        // Create Session Id
								                        int sessionTimeout = Integer.parseInt(sessionTimeoutMin);
								                        String sessionId = RandomKeyMakerUtil.sessionIdGen(mgrappLoginId,uuid);
								                        String sessionExpiration = DateUtils.getCurrentDate(Calendar.MINUTE, sessionTimeout,
								                              DateUtils.DATE_FORMAT_YMDHMS);

								                        //기존 세션 정보 삭제
								                        mgrAppSessionDao.deleteMgrSessByMgrappId(mgrappId);
								                        //Insert Session Id(TB_MGRAPP_SESS)
								                        MgrAppSessVO mgrAppSessVO = new MgrAppSessVO();
								                        mgrAppSessVO.setMgrappId(mgrappId);
								                        mgrAppSessVO.setMgrappSessionId(sessionId);
								                        mgrAppSessVO.setDeviceType(reqJson.getCommon().getDevice().getDeviceType());
								                        mgrAppSessVO.setConnIp(connip);
								                        mgrAppSessVO.setSessionExpDt(sessionExpiration);
								                        mgrAppSessVO.setRandomKey(randomKey);
								                        mgrAppSessVO.setMgrappLoginId(mgrAppUserVO.getMgrappLoginId());
								                        mgrAppSessVO.setOsType(mgrAppUserVO.getOsType());
								                        mgrAppSessVO.setUuid(mgrAppUserVO.getUuid());
								                        mgrAppSessionDao.insertMgrSess(mgrAppSessVO);
								                        resultData = new ResultMgrAuthRegJSON();
								                        resultData.setSessionId(sessionId);
								                        resultData.setMgrappId(mgrappId);
								                     }else{
								                        //사용자 상태 비정상(일시정지,해지)
								                        logger.error("Not Abnomal User Status. UUID({}) mgrappLoginId({})", uuid,mgrappLoginId);
								                        resultCode = ResultCodeUtil.RC_6C000004;
								                     }
								                    
								                  }else{
								                     //사용자 Device 정보 없음
								                     logger.error("Not Exist User DeviceInfo. UUID({}) mgrappLoginId({})", uuid,mgrappLoginId);
								                     resultCode = ResultCodeUtil.RC_6C000003;
								                  }
								               }else{
								                  //UUID 불일치
								                  logger.error("Mismatch MgrAppUser UUID. UUID({}) mgrappLoginId({})", uuid,mgrappLoginId);
								                  resultCode = ResultCodeUtil.RC_6C000005;
								               }
										}else{
											//사용자 정보 없음.
											logger.error("Not Exist MgrApp UserInfo. UUID({}) mgrappLoginId({})", uuid,mgrappLoginId);
											resultCode = ResultCodeUtil.RC_6C000002;
										}
										
									}else{ 
										//Ncas Search Fail & Search Result Empty
										resultCode = ResultCodeUtil.RC_5N000001;
										logger.error("Ncas Search Fail & Search Result Empty mgrappLoginId({}) membId({}) ccssToken({})",mgrappLoginId, deviceSessVO.getMembId() ,ccssToken);
									}
								}
							}
						}else{
							resultCode = ResultCodeUtil.RC_3C002004;
							logger.error("Not Exist Member Info mgrappLoginId({}) membId({}) ccssToken({})",mgrappLoginId, deviceSessVO.getMembId() ,ccssToken);
						}
					}else{
						resultCode = ResultCodeUtil.RC_3C003004;
						logger.error("Invalid ccssToken. deviceCtn({})  UICCID({}) mgrappLoginId({}) ccssToken({})", encDeviceCtn, encDeviceSerial,mgrappLoginId ,ccssToken);
					}
					response = ResultCodeUtil.createResultMsg(resultCode,resultData, api);
					return response;
				}else{
					
					//BM(쌍용) ccssToken 없음.
					String deviceCtn = (String) reqJson.getParam().get(RequestJSON.PARAM_CTN);
					String deviceSerial = (String) reqJson.getParam().get(RequestJSON.PARAM_SERIAL);

					ConnDeviceVO connDevice =  getDeviceInfo(reqJson);
					
					if (logger.isDebugEnabled()) {
						logger.debug("connDeviceInfo({}) deviceCtn({}) UICCID({}) mgrappLoginId({})", connDevice ,deviceCtn, deviceSerial,mgrappLoginId);
					}

					
					if(connDevice == null) { //Unregistered device information
						resultCode = ResultCodeUtil.RC_3C002006;
						logger.error("Failed to check Device Reg Info. deviceCtn({})  UICCID({}) mgrappLoginId({})", deviceCtn, deviceSerial,mgrappLoginId);
					}else{
						
						//Set Tlo Data - Memb Id
						setTloData(connDevice);
						
						MembVO membVO  = memberDao.selectMemberByID(connDevice.getMembId());
						if(membVO !=null){
							
							//Check AppType & Market Type
							String marketType = membVO.getMarketType();
							if(appType.indexOf(marketType)  < 0){
								resultCode = ResultCodeUtil.RC_3C002007; 
								logger.error("APP & Device Type unmatched. appType({}) marketType({}) deviceCtn({})  UICCID({}) mgrappLoginId({})  ccssToken({})",
										appType,marketType,decDeviceCtn, decDeviceSerial,mgrappLoginId,ccssToken); 
							}else{
								//2.Query NCAS
								if(checkNcasStatus(deviceCtn,deviceSerial)){
									MgrAppUserVO mgrAppUserVO = new MgrAppUserVO();
									//mgrAppUserVO.setMembId(connDevice.getMembId());
									mgrAppUserVO.setMgrappLoginId(mgrappLoginId);
									mgrAppUserVO.setUuid(uuid);
									mgrAppUserVO.setLoginType(loginType);
									
									mgrAppUserVO = mgrAppUserDao.selectMgrUserInfo(mgrAppUserVO);
									if(mgrAppUserVO !=null){ 
										// Compare UUID
										if(!mgrAppUserVO.getUuid().equals(uuid)){
											
											resultCode = ResultCodeUtil.RC_6C000000;
											logger.error("Not equal UUID. REQ_UUID({}) DB_UUID({}) deviceCtn({}) UICCID({}) mgrappLoginId({})",uuid,mgrAppUserVO.getUuid(), deviceCtn, deviceSerial,mgrappLoginId);
										}else{
											
											//1개의 Memb ID에 대해 5개이상 등록 되어 있을 경우 실패 응답
											/*if(checkExceedAppUser(connDevice.getMembId(),maxUserCnt,mgrAppUserVO.getMgrappId())){
												resultCode = ResultCodeUtil.RC_6C000007;
												logger.error("Exceeded Number Of Device Users.  REQ_UUID({}) deviceCtn({}) UICCID({}) mgrappLoginId({})",uuid, deviceCtn, deviceSerial,mgrappLoginId);
											
											}else{*/
											//Reg Mgrapp Device
											int deviceRegCnt = regDevice(mgrAppUserVO.getMgrappId(),connDevice.getMembId(),deviceNm, "N");
											if(deviceRegCnt > 0){
												logger.info("Device  Reg Success  REQ_UUID({}) deviceCtn({}) UICCID({}) mgrappLoginId({})",uuid, deviceCtn, deviceSerial,mgrappLoginId);
											}else{
												logger.error("Device Reg Fail  REQ_UUID({}) deviceCtn({}) UICCID({}) mgrappLoginId({})",uuid, deviceCtn, deviceSerial,mgrappLoginId);
											}
											//}
										}
										
									}else{ 
										// Reg Mgrapp User Info
										String mgrappId = KeyGenerator.createCommonShardKey();
										mgrAppUserVO = makeMgrAppUserInfo(reqJson,mgrappId);
										
										setOsTyep(mgrAppUserVO, (String) reqJson.getCommon().getLogData().getOsInfo());
										
										/*if(checkExceedAppUser(connDevice.getMembId(),maxUserCnt,mgrAppUserVO.getMgrappId())){
											resultCode = ResultCodeUtil.RC_6C000007;
											logger.error("Exceeded Number Of Device Users.  REQ_UUID({}) deviceCtn({}) UICCID({}) mgrappLoginId({})",uuid, deviceCtn, deviceSerial,mgrappLoginId);
										
										}else{*/
										//TB_MBRAPP_USER REG
										int regCount = mgrAppUserDao.insertTbMgrAppUser(mgrAppUserVO);
										if(regCount > 0){
											logger.info("UserInfo  Reg Success  REQ_UUID({}) deviceCtn({}) UICCID({}) mgrappLoginId({})",uuid, deviceCtn, deviceSerial,mgrappLoginId);
										}else{
											logger.error("UserInfo Reg Fail  REQ_UUID({}) deviceCtn({}) UICCID({}) mgrappLoginId({})",uuid, deviceCtn, deviceSerial,mgrappLoginId);
										}
										
										//Reg Mgrapp Device
										int deviceRegCnt = regDevice(mgrappId,connDevice.getMembId(),deviceNm, "N");
										
										if(deviceRegCnt > 0){
											logger.info("Device  Reg Success  REQ_UUID({}) deviceCtn({}) UICCID({}) mgrappLoginId({})",uuid, deviceCtn, deviceSerial,mgrappLoginId);
										}else{
											logger.error("Device Reg Fail  REQ_UUID({}) deviceCtn({}) UICCID({}) mgrappLoginId({})",uuid, deviceCtn, deviceSerial,mgrappLoginId);
										}
										//}
									}
									
								}else{ 
									resultCode = ResultCodeUtil.RC_5N000001;
									logger.error("Ncas Search Fail & Search Result Empty mgrappLoginId({}) membId({}) ccssToken({})",mgrappLoginId, connDevice.getMembId() ,ccssToken);
								}
							}
						}else{
							resultCode = ResultCodeUtil.RC_3C002004;
							logger.error("Not Exist Member Info mgrappLoginId({}) membId({}) ccssToken({})",mgrappLoginId, connDevice.getMembId() ,ccssToken);
						}
					}
				}
				
			} catch (Exception e) {
				resultCode = ResultCodeUtil.RC_4C005000;
				logger.error("mgrappLoginId({}) encDeviceCtn({}) encUICCID({}) decDeviceCtn({}) decUICCID({}) ccssToken({}) Exception({})", 
						mgrappLoginId,encDeviceCtn,encDeviceSerial,decDeviceCtn,decDeviceSerial,ccssToken,e);
			}
		}
		response = ResultCodeUtil.createResultMsg(resultCode, api);
		return response;
	}
	
	private boolean checkNcasStatus(String deviceCtn ,String deviceSerial){
		boolean result = false;
		
		NCASResultData ncasData = nCASQueryManager.query(deviceCtn);
		
		if (ncasData == null) {
			logger.error("Failed to get Ncas data. deviceCtn({}) UICCID({})", deviceCtn ,deviceSerial);
		}else{
			
			SubsInfo subsInfo = ncasData.getSubsInfo();
			
			if (subsInfo == null) {
				logger.error("Failed to get SubsInfo data. deviceCtn({}) UICCID({})", deviceCtn,deviceSerial);
			}else{
				
				if (logger.isDebugEnabled()) {
					logger.debug("SUBS_INFO({}) deviceCtn({}) UICCID({})", subsInfo,deviceCtn,deviceSerial);
				}
				
				if (subsInfo.getCtn_stus_code().equals(NCASConst.CTN_STUS_CODE_SUSPEND)) {
					logger.error("Joinstatus is Pause. deviceCtn({}) UICCID({}) ", deviceCtn,deviceSerial);
				} else if (subsInfo.getCtn_stus_code().equals(NCASConst.CTN_STUS_CODE_ACTIVE)) {
					logger.debug("JoinStatus is Active. deviceCtn({}) UICCID({}) ", deviceCtn,deviceSerial);
					result = true;
				}
			}
		}
		return result;
	}
	
	/*public ConnDeviceVO getDeviceInfo(RequestJSON reqJson) throws Exception {
		String deviceCtn = (String) reqJson.getParam().get(RequestJSON.PARAM_CTN);
		String uiccId = (String) reqJson.getParam().get(RequestJSON.PARAM_SERIAL);

		ConnDeviceVO connDevice = new ConnDeviceVO();
		connDevice.setUiccId(uiccId);
		connDevice.setDeviceCtn(deviceCtn);
		connDevice.setUseYn(ConnDeviceVO.USE_Y);
		
		return deviceDao.getDeviceInfo(connDevice);
	}
	*/
	
	private MgrAppUserVO makeMgrAppUserInfo(RequestJSON reqJson, String mgrappId) throws Exception {
		MgrAppUserVO mgrAppUserVO = new MgrAppUserVO();
		
		mgrAppUserVO.setMgrappId(mgrappId);
		mgrAppUserVO.setMgrappLoginId(CCSSUtil.getMgrUserLoginId());
		mgrAppUserVO.setDeviceType(reqJson.getCommon().getDevice().getDeviceType());
		mgrAppUserVO.setPushId((String) reqJson.getParam().get(RequestJSON.PARAM_PUSH_ID));
		//mgrAppUserVO.setMembId(membId);
		mgrAppUserVO.setMgrappVer((String) reqJson.getParam().get(RequestJSON.PARAM_MGR_APP_VER));
		JsonSetInfo jsonSetInfo = new JsonSetInfo();
		mgrAppUserVO.setJsonSetInfo(JsonUtil.marshallingJson(jsonSetInfo));
		mgrAppUserVO.setUuid((String) reqJson.getParam().get(RequestJSON.PARAM_UUID));
		mgrAppUserVO.setUserNm((String) reqJson.getParam().get(RequestJSON.PARAM_USER_NM));
		mgrAppUserVO.setLoginType((String) reqJson.getParam().get(RequestJSON.PARAM_LOGIN_TYPE));
		
		//mgrAppUserVO.setMgrappStatus();
		//mgrAppUserVO.setVerType((String) reqJson.getParam().get(RequestJSON.PARAM_MGR_VER_TYPE));
		
		return mgrAppUserVO;
	}
	
	//1개의 Memb ID에 대해 5개이상 등록 되어 있을 경우 실패 응답
	public boolean checkExceedAppUser(String membId,int maxUserCnt,String mgrappId){
		
		boolean flag  = false;
		
		MgrAppUserVO searchMgrAppUserVO = new MgrAppUserVO();
		searchMgrAppUserVO.setMembId(membId);
		
		List<MgrAppUserVO> searchAppUserList =  mgrAppUserDao.selectMgrAppUserInfoList(searchMgrAppUserVO); 

		if(searchAppUserList !=null && searchAppUserList.size() >= maxUserCnt){
			
			flag = true;
			
			for( MgrAppUserVO tempVO : searchAppUserList){
				String userId = tempVO.getMgrappId();
				if(userId.equals(mgrappId)){
					flag= false;
					break;
				}
			}
		}else {
			//Nothing
		}
		
		return flag;
	}
	
	public ConnDeviceVO getDeviceInfo(RequestJSON reqJson) throws Exception {
		String deviceCtn = (String) reqJson.getParam().get(RequestJSON.PARAM_CTN);
		String uiccId = (String) reqJson.getParam().get(RequestJSON.PARAM_SERIAL);

		ConnDeviceVO connDevice = new ConnDeviceVO();
		connDevice.setUiccId(uiccId);
		connDevice.setDeviceCtn(deviceCtn);
		connDevice.setUseYn(ConnDeviceVO.USE_Y);
		
		return deviceDao.getDeviceInfo(connDevice);
	}
	
	private void setOsTyep(MgrAppUserVO mgrAppUserVO,String osInfo){
		if(osInfo.indexOf(RequestCommonLogDataJSON.OS_INFO_ANDROID) > -1){
			mgrAppUserVO.setOsType(RequestCommonLogDataJSON.OS_INFO_ANDROID);
		}else if(osInfo.indexOf(RequestCommonLogDataJSON.OS_INFO_IOS) > -1){
			mgrAppUserVO.setOsType(RequestCommonLogDataJSON.OS_INFO_IOS);
		}else{
			mgrAppUserVO.setOsType(RequestCommonLogDataJSON.OS_INFO_OTHER);
		}
	}
		
	private int regDevice(String mgrappId, String membId, String deviceNm, String mainuseYn){
		int cnt  =0 ;
		MgrAppDeviceVO mgrAppDeviceVO = new MgrAppDeviceVO();
		mgrAppDeviceVO.setMgrappId(mgrappId);
		mgrAppDeviceVO.setMembId(membId);
		mgrAppDeviceVO.setDeviceNm(deviceNm);
		mgrAppDeviceVO.setMainUseYn(mainuseYn);
		
		cnt = mgrAppDeviceDao.insertTbMgrAppDevice(mgrAppDeviceVO);
		
		return cnt;
	}
	private void setTloData(ConnDeviceVO connDeviceVO) {
		if (connDeviceVO == null) {
			return;
		}
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.MEMB_ID, connDeviceVO.getMembId());
		//tlo.put(TloData.MEMB_NO, memb.getMembNo());
		TloUtil.setTloData(tlo);
	}
	
	private MgrAppUserVO getMgrAppUserInfo(RequestJSON reqJson){
	      MgrAppUserVO mgrAppUserVO = new MgrAppUserVO();
	      mgrAppUserVO.setMgrappLoginId((String) reqJson.getParam().get(RequestJSON.PARAM_MNGR_LOGIN_ID));
	      mgrAppUserVO.setUuid((String) reqJson.getParam().get(RequestJSON.PARAM_UUID));
	      mgrAppUserVO.setDeviceType((String) reqJson.getCommon().getDevice().getDeviceType());
	      return mgrAppUserDao.selectMgrUserInfo(mgrAppUserVO);
	   }
	
	public String[] makeMembIdArr(List<MgrAppDeviceVO> resultList){
	      List<String> searchIdList = new ArrayList<String>();
	      for(MgrAppDeviceVO tempVO : resultList){
	         searchIdList.add(tempVO.getMembId());
	      }
	      String[] searchIdArr = new String[resultList.size()];
	      searchIdArr = searchIdList.toArray(searchIdArr);
	      return searchIdArr;
	   }
	
	private boolean checkUserStatus(String[] checkMembIdArr){
	      int count =0;
	      MembVO membVO = new MembVO();
	      membVO.setMembIdArr(checkMembIdArr);
	      count = memberDao.ckeckUserstatus(membVO);

	      return (count > 0 )?true:false;
	   }
}
