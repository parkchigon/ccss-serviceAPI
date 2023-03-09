package com.lgu.ccss.mgr.auth.service.login;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.MemberDao;
import com.lgu.ccss.common.dao.ModelDao;
import com.lgu.ccss.common.dao.mgr.MgrAppDeviceDao;
import com.lgu.ccss.common.dao.mgr.MgrAppSessionDao;
import com.lgu.ccss.common.dao.mgr.MgrAppUserDao;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.OemVO;
import com.lgu.ccss.common.model.RequestCommonLogDataJSON;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.model.mgr.MgrAppDeviceVO;
import com.lgu.ccss.common.model.mgr.MgrAppSessVO;
import com.lgu.ccss.common.model.mgr.MgrAppUserVO;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.ccss.mgr.auth.model.LogServerInfoJSON;
import com.lgu.ccss.mgr.auth.model.ResultMgrAuthLoginJSON;
import com.lgu.common.model.ResultCode;
import com.lgu.common.pushgw.PushConst;
import com.lgu.common.pushgw.model.PushVO;
import com.lgu.common.pushgw.service.PushServiceAgent;
import com.lgu.common.util.DateUtils;
import com.lgu.common.util.ExceptionUtil;
import com.lgu.common.util.RandomKeyMakerUtil;
import com.lgu.common.util.StringUtils;

@Service("mgrAuthLoginService")
public class MgrAuthLoginServiceImpl implements MgrAuthLoginService {

   private static final Logger logger = LoggerFactory.getLogger(MgrAuthLoginServiceImpl.class);
   private static final List<String> mandatoryList = new ArrayList<String>(
         Arrays.asList(RequestJSON.PARAM_UUID,RequestJSON.PARAM_MNGR_LOGIN_ID,RequestJSON.PARAM_VERSION
               /*,RequestJSON.PARAM_PUSH_ID*/));
   
   
   @Autowired
   private MgrAppUserDao mgrAppUserDao;
   
   @Autowired
   private MgrAppDeviceDao mgrAppDeviceDao;
   
   @Autowired
   private MemberDao memberDao;
   
   @Autowired
   private ModelDao modelDao;
   
   @Autowired
   private MgrAppSessionDao mgrAppSessionDao;
   
   @Value("#{config['session.timeoutMin']}")
   private String sessionTimeoutMin;
   
   @Value("#{config['log.server.url']}")
   private String logServerUrl;

   @Value("#{config['log.maxSize']}")
   private String logMaxSize;
   
   @Autowired
   private PushServiceAgent pushServiceAgent;

   @Override
   public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson, HttpServletRequest request) {
      ResultCode resultCode=ResultCodeUtil.RC_2S000000;
      
      String api = reqJson.getCommon().getApiId();
      String mgrappLoginId = CCSSUtil.getMgrUserLoginId();

      ResponseJSON response = null;
      ResultMgrAuthLoginJSON resultData = null;
      
      CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.MANAGER_AUTH_LOGIN,mandatoryList);
      
      if (result.isStatus() == false) {
         resultCode = ResultCodeUtil.RC_3C004000;
         response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
         return response;
         
      }else{
         
         String uuid =  (String) reqJson.getParam().get(RequestJSON.PARAM_UUID);
         String pushId =  (String) reqJson.getParam().get(RequestJSON.PARAM_PUSH_ID);
         String randomKey =  StringUtils.nvl(request.getHeader(RequestJSON.PARAM_RANDOM_KEY));
         String isAgreeStep =  (String) reqJson.getParam().get(RequestJSON.PARAM_ISAGREESTEP);
         // AM약관동의서는 deviceType이 "PND"일 경우에만 실행
         String appType = (String)reqJson.getCommon().getDevice().getAppType();
         
         try {
            //USER_CTN  -> TB_MGR_DEVICE 조회 - > mgrappId 획득.   
            //1.매니저 APP 사용자 정보조회 (TB_MGRAPP_USER : mgrappLoginId , uuid)
            MgrAppUserVO mgrAppUserVO =   getMgrAppUserInfo(reqJson);
            
            if(mgrAppUserVO !=null){   
               
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
                       
                        //2.Session-ID & Random Key & Push ID 관리.  
                     
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
                        String connIp = request.getRemoteAddr();
                        mgrAppSessVO.setConnIp(connIp);
                        mgrAppSessVO.setSessionExpDt(sessionExpiration);
                        mgrAppSessVO.setRandomKey(randomKey);
                        mgrAppSessVO.setMgrappLoginId(mgrAppUserVO.getMgrappLoginId());
                        mgrAppSessVO.setOsType(mgrAppUserVO.getOsType());
                        mgrAppSessVO.setUuid(mgrAppUserVO.getUuid());
                        mgrAppSessionDao.insertMgrSess(mgrAppSessVO);
                        
                        //Update PushId & MgrConStatus(TB_MGRAPP_USER)
                        MgrAppUserVO updateUserVO = new MgrAppUserVO();
                        updateUserVO.setMgrappId(mgrappId);
                        updateUserVO.setMgrappLoginId(mgrappLoginId);
                        updateUserVO.setPushId(pushId);
                        updateUserVO.setMgrConStatus(CCSSConst.DEF_YES); 
                        updateUserVO.setDeviceType(reqJson.getCommon().getDevice().getDeviceType());
                        updateUserVO.setMgrappVer((String) reqJson.getParam().get(RequestJSON.PARAM_VERSION));
                        mgrAppUserDao.updateMgrAppUserInfo(updateUserVO);
                        
                        String deviceModelId = mgrAppUserDao.selectDeviceModelId(mgrappLoginId);
                        
                        //20191101 로그인시 PSH GW에 PUSH 키 등록
                        if(pushId != null){
                           ResultCode resultCode1;
                           resultCode1 = registerPushGw(reqJson, pushId, mgrAppUserVO.getUuid(), mgrAppUserVO.getMgrappLoginId(), resultCode, response, api, mgrAppUserVO.getOsType() ,deviceModelId);
                           
                           if(!resultCode1.getCode().equals(resultCode.getCode())){
                              return response = ResultCodeUtil.createResultMsg(resultCode1, api);
                           }
                        }
                                                
                        resultData = new ResultMgrAuthLoginJSON();
                        resultData.setSessionId(sessionId);
                        String sessionIdExpiration = DateUtils.getCurrentDate(Calendar.MINUTE,30,"yyyy-MM-dd HH:mm:ss");
                        //resultData.setSessionIdExpiration(Integer.toString(sessionTimeout - TOKEN_TIMEOUT_DIFF_MIN));
                        resultData.setSessionIdExpiration(sessionIdExpiration);
                        resultData.setPushKeyRegYn(mgrAppUserVO.getPushKeyRegYn());
                        resultData.setMgrappId(mgrappId);
                        
                        LogServerInfoJSON logServerInfo = new LogServerInfoJSON();
                        logServerInfo.setServer(logServerUrl);
                        logServerInfo.setPushLogSize(logMaxSize);
                        resultData.setLogSvrInfo(logServerInfo);
                     
                         
                     }else{
                        //사용자 상태 비정상(일시정지,해지)
                        logger.error("Not Abnomal User Status. UUID({}) mgrappLoginId({})", uuid,mgrappLoginId);
                        resultCode = ResultCodeUtil.RC_6C000002;
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
               logger.debug("isAgreeStep ({})",isAgreeStep);
               if (((appType!=null && appType.equalsIgnoreCase(CCSSConst.DEF_MANAGER_APP_AM)) || (appType!=null && appType.equalsIgnoreCase(CCSSConst.DEF_AM_APP_NS)))               
                     && (isAgreeStep==null || isAgreeStep.isEmpty() || !isAgreeStep.equals("Y"))) {
                  //사용자 정보 없음 => 약관미동의
                  logger.error("Not Agreement MgrApp. UUID({}) mgrappLoginId({})", uuid,mgrappLoginId);
                  resultCode = ResultCodeUtil.RC_6C000008;
               }else {
                  //사용자 정보 없음.
                  logger.error("Not Exist MgrApp UserInfo. UUID({}) mgrappLoginId({})", uuid,mgrappLoginId);
                  resultCode = ResultCodeUtil.RC_6C000002;
               }
            }
         } catch (Exception e) {
            resultCode = ResultCodeUtil.RC_4C005000;
            logger.error("mgrappLoginId({})  UUID({}) Exception({})", mgrappLoginId,uuid,ExceptionUtil.getPrintStackTrace(e));
         }
      }
      response = ResultCodeUtil.createResultMsg(resultCode,resultData, api);
      return response;
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
   
   private MgrAppUserVO getMgrAppUserInfo(RequestJSON reqJson){
      MgrAppUserVO mgrAppUserVO = new MgrAppUserVO();
      mgrAppUserVO.setMgrappLoginId((String) reqJson.getParam().get(RequestJSON.PARAM_MNGR_LOGIN_ID));
      mgrAppUserVO.setUuid((String) reqJson.getParam().get(RequestJSON.PARAM_UUID));
      mgrAppUserVO.setDeviceType((String) reqJson.getCommon().getDevice().getDeviceType());
      return mgrAppUserDao.selectMgrUserInfo(mgrAppUserVO);
   }
   
   private ResultCode registerPushGw(RequestJSON reqJson, String pushId, String uuid, String mgrappLoginId, 
         ResultCode resultCode, ResponseJSON response, String api, String osType, String deviceModelId) throws Exception {
      
      PushVO pushVO = new PushVO();
      pushVO.setDeviceToken(pushId);
      pushVO.setPushId(PushServiceAgent.createPushId());
      pushVO.setDeviceId(uuid);
      pushVO.setServiceKey(mgrappLoginId);
      
      String carOem = "";

      //20181005 박치곤 carOem으로 SY,HK,AM인지 구분, 20190208 김범주 EL1 추가
      if (deviceModelId.equals("EL1")) {
    	carOem = "NS";
      } else {
          carOem =  (String) reqJson.getCommon().getDevice().getCarOem();
          if(carOem == null || carOem.length() == 0){
             carOem = "AM";
          }
      }

      //20181005 박치곤 ostype으로 안드로이드 iso인지 구분
      if(osType.indexOf(RequestCommonLogDataJSON.OS_INFO_ANDROID) > -1){
         osType = RequestCommonLogDataJSON.OS_INFO_ANDROID;
      }else if(osType.indexOf(RequestCommonLogDataJSON.OS_INFO_IOS) > -1){
         osType = RequestCommonLogDataJSON.OS_INFO_IOS;
      }
      //20181005 박치곤 디비에서 조회할 내용 조합
      String OemId = carOem +"_"+ osType;
      //20181005 박치곤 디비 조회
      OemVO oemVo = modelDao.selectPushId(OemId);
      if(oemVo == null){
    	 logger.error("registerPushGw OEM Not Found "+OemId);
         resultCode = ResultCodeUtil.RC_4C005001;
         return resultCode;
      }
      
      String pushServiceId = oemVo.getPushId();
      
      String appPushType;
      if(osType.equals("android")){
         appPushType = "GCM";
      } else{
         appPushType = "APNS";
      }
      
      if(appPushType.equals(PushConst.GCM)){
         pushServiceAgent.subscriberRegisterGcm(pushVO,mgrappLoginId, OemId, pushServiceId);
      }else{
         pushServiceAgent.subscriberRegisterApns(pushVO,mgrappLoginId, OemId, pushServiceId);
      }
      resultCode = ResultCodeUtil.RC_2S000000;
      return resultCode;
      
   }
}