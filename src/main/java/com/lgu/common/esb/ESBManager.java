package com.lgu.common.esb;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.mypage.common.ApimFallbackProc;
import com.lgu.common.esb.exception.ESBException;
import com.lgu.common.esb.model.DcntDlstBltxResponseJSON;
import com.lgu.common.esb.model.MmlyRmndUsagOverResponseJSON;
import com.lgu.common.esb.model.PayDlstResponseJSON;
import com.lgu.common.esb.oAuth.oAuthAgent;
import com.lgu.common.esb.oAuth.model.ResponseOAuth2AccessTokenDto;
import com.lgu.common.esb.vo.AmountDataVO;
import com.lgu.common.esb.vo.BillReturnInfoVO;
import com.lgu.common.esb.vo.BillTypeResultVO;
import com.lgu.common.esb.vo.BillTypeVO;
import com.lgu.common.esb.vo.DiscountInfoResultVO;
import com.lgu.common.esb.vo.DiscountInfoVO;
import com.lgu.common.esb.vo.PauseHistory;
import com.lgu.common.esb.vo.PauseHistoryCollection;
import com.lgu.common.esb.vo.PauseHistoryCollectionVO;
import com.lgu.common.esb.vo.PauseHistoryVO;
import com.lgu.common.esb.vo.PauseLiftHistory;
import com.lgu.common.esb.vo.PauseLiftHistoryCollection;
import com.lgu.common.esb.vo.PauseResultVO;
import com.lgu.common.esb.vo.PayDetailVO;
import com.lgu.common.esb.vo.PayInfoVO;
import com.lgu.common.esb.vo.PayMethodAuthVO;
import com.lgu.common.esb.vo.PayMethodResultVO;
import com.lgu.common.esb.vo.PayMethodVO;
import com.lgu.common.esb.vo.SubsInfoVO;
import com.lgu.common.esb.vo.UnpauseResultVO;
import com.lgu.common.trace.TraceConst;
import com.lgu.common.trace.TraceWriter;

import lguplus.u3.webservice.arc239.SaveArPymIntServiceStub;
import lguplus.u3.webservice.arc472.RetrieveVtAcntServiceStub;
import lguplus.u3.webservice.bil417.RetrieveDscntNblTxtServiceStub;
import lguplus.u3.webservice.bil454.RetrieveBlPaymentInfoEsbServiceStub;
import lguplus.u3.webservice.cm016.RetrieveCustInfoSvcServiceStub;
import lguplus.u3.webservice.cm019.RetrieveBillAcntForEntrSvcServiceStub;
import lguplus.u3.webservice.cm020.RetrieveBlDcEmailCdForBltxtSvcServiceStub;
import lguplus.u3.webservice.cm043.SaveBltxtKdCdSvcServiceStub;
import lguplus.u3.webservice.cm061.RetrieveAythOrNameSvcServiceStub;
import lguplus.u3.webservice.cm078.SavePymAcntSvcServiceStub;
import lguplus.u3.webservice.cm118.RetrieveBillRetnInfoServiceStub;
import lguplus.u3.webservice.mps121.RetrieveCallAppSmryServiceStub;
import lguplus.u3.webservice.sb068.RetrieveSuspendHistoryHomepgServiceStub;
import lguplus.u3.webservice.sb638.RetrieveUseSvcListServiceStub;
import lguplus.u3.webservice.sb639.CreateRsvSuspendOrRsSuspendInfoServiceStub;
import lguplus.u3.webservice.sb640.UpdateSttsChgRsvExpServiceStub;
import lguplus.u3.webservice.sb641.RetrieveSusSvcListServiceStub;
import lguplus.u3.webservice.sb695.SaveEntrSuspendHomepgServiceStub;
import lguplus.u3.webservice.sb915.RetrieveCustSvcEntrInfoBDServiceStub;
import lguplus.u3.webservice.sm546.RetrieveDscntMgmtServiceStub;

@Component
public class ESBManager {
	private static final Logger logger = LoggerFactory.getLogger(ESBManager.class);

	// [ARC239] 인터넷수납 - saveArPymInt();(XXX)
	// [ARC472] 고객전용 입금계좌조회 - retrieveVtAcnt();(XXX)
	// [BIL417] 청구상세내역_할인내역 및 청구서 조회 - retrieveDscntNblTxt();
	// [BIL454] 청구내역_납부내역_정보조회 - retrieveBlPaymentInfoEsb();
	// [CM016] 고객정보조회 - retrieveCustInfoSvc();
	// [CM019] 청구계정정보조회 - retrieveBillAcntForEntrSvc();
	// [CM020] 청구서유형조회 - retrieveBlDcEmailCdForBltxtSvc();(XXX)
	// [CM043] 청구유형변경 - saveBltxtKdCdSvc();
	// [CM061] 계좌 및 카드 인증 및 이름 인증(SAP) - retrieveAythOrNameSvc();(XXX)
	// [CM078] 납부방법변경 - savePymAcntSvc();
	// [CM118] 청구서반송내역조회 - retrieveBillRetnInfo();
	// [MPS121] 모바일APP 무료잔여량조회/월별사용량 조회 항목 전송 - retrieveCallAppSmry();
	// [SB068] 일시정지 이력조회 - retrieveSuspendHistoryHomepg();
	// [SB638] 일시정지내역조회 - retrieveUseSvcList();(XXX)
	// [SB639] 일시정지예약신청 - createRsvSuspendOrRsSuspendInfo();
	// [SB640] 예약정지취소신청 - updateSttsChgRsvService();
	// [SB641] 일시정지해제내역조회  - retrieveSusSvcList();
	// [SB689] CSSI가입정보조회마스터 - retrieveUzoneServiceEntrInfo();(XXX)	
	// [SB695] 일시정지해제신청  - saveEntrSuspendHomepgService();
	// [SB915] 서비스 가입 정보 조회 - retrieveCustSvcEntrInfoBD();
	// [SM066] 서비스 예약 등록 - saveSvcRsv();(XXX)
	// [SM546] 가입별할인관리 - retrieveDscntMgmt()

	@Value("#{config['esb.real']}")
	private String real; // 상용인지 여부 - ESB 상용과 개발이 너무 다름
	@Value("#{config['esb.url']}")
	private String URL;
	@Value("#{config['esb.system.id']}")
	private String SYSTEM_ID;
	
	@Value("#{config['esb.file.dir']}")
	private String fileDir;
	
	@Value("#{config['esb.file.name']}")
	private String fileName;
	
	@Value("#{config['esb.apim.open.20220201']}")
	private String apimOpen1st;
	
	@Value("#{config['esb.apim.open.20220221']}")
	private String apimOpen2nd;

	@Autowired
	private TraceWriter traceWriter;
	
	@Autowired
	private oAuthAgent oAuthAgent;
	
	@Autowired
	private ESBAgent esbAgent;
	
	@Resource(name = "apimFallbackProc")
    private ApimFallbackProc apimFallbackProc;


	/**
	 * @throws :
	 * @throws :
	 * @Part :
	 * @Author :
	 * @Component :
	 * @Primitive :
	 * @type :
	 * @Description:[CM016] 고객정보조회
	 */
	private RetrieveCustInfoSvcServiceStub.ResponseBody retrieveCustInfoSvc(
			RetrieveCustInfoSvcServiceStub.DsReqInVO dsReqIn) throws ESBException {
		// entrNo 가입번호 VARCHAR2 10 Y 단수
		// mode 모드 VARCHAR2 1 N 단수 "E : 가입번호 P : 전화번호 """" : 가입번호,이력번호"
		// prodNo 전화번호 VARCHAR2 20 Y 단수
		// nextOperatorId 처리자ID NUMBER 15 N 단수

		dsReqIn.setNextOperatorId(createNextOperatorId());

		// RetrieveCustInfoSvcServiceStub.DsReqInVO dsReqIn = new
		// RetrieveCustInfoSvcServiceStub.DsReqInVO();
		// dsReqIn.setEntrNo(entrNo);
		// dsReqIn.setMode(mode);
		// dsReqIn.setProdNo(prodNo);
		// dsReqIn.setNextOperatorId(nextOperatorId);

		//
		RetrieveCustInfoSvcServiceStub.RequestBody reqBody = new RetrieveCustInfoSvcServiceStub.RequestBody();
		reqBody.setDsReqInVO(dsReqIn);

		//
		RetrieveCustInfoSvcServiceStub.ESBHeader reqHeader = new RetrieveCustInfoSvcServiceStub.ESBHeader();
		reqHeader.setServiceID("CM016");
		reqHeader.setTransactionID(createTxId());
		reqHeader.setSystemID(SYSTEM_ID);
		reqHeader.setErrCode("");
		reqHeader.setErrMsg("");
		reqHeader.setReserved("");

		//
		RetrieveCustInfoSvcServiceStub.RequestRecord reqRecord = new RetrieveCustInfoSvcServiceStub.RequestRecord();
		reqRecord.setRequestBody(reqBody);
		reqRecord.setESBHeader(reqHeader);

		//
		RetrieveCustInfoSvcServiceStub.RetrieveCustInfoSvc reqIn = new RetrieveCustInfoSvcServiceStub.RetrieveCustInfoSvc();
		reqIn.setRequestRecord(reqRecord);

		//
		String host = URL;
		String path = "/CSSI/CM/RetrieveCustInfoSvc";
		String targetEndpoint = host + path;

		RetrieveCustInfoSvcServiceStub stub = null;
		RetrieveCustInfoSvcServiceStub.RetrieveCustInfoSvcResponse res = null;

		try {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(printFields(dsReqIn));

				traceRequest(stringBuffer.toString());
			}
			stub = new RetrieveCustInfoSvcServiceStub(targetEndpoint);
			res = stub.retrieveCustInfoSvc(reqIn);
		} catch (RemoteException e) {
			e.printStackTrace();
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(e.getMessage());
				
				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(reqHeader.getErrCode(), reqHeader.getErrMsg());
		}

		RetrieveCustInfoSvcServiceStub.ResponseRecord resRecord = res.getResponseRecord();
		RetrieveCustInfoSvcServiceStub.ESBHeader resHeader = resRecord.getESBHeader();
		if (!(resHeader.getErrCode() == null || "".equals(resHeader.getErrCode()))) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(resHeader.getErrCode(), resHeader.getErrMsg());
		}

		RetrieveCustInfoSvcServiceStub.BusinessHeader bizHeader = resRecord.getBusinessHeader();
		if (bizHeader.getResultCode() == null || !"N0000".equals(bizHeader.getResultCode())) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));
				stringBuffer.append(printFields(bizHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(resHeader.getErrCode(), resHeader.getErrMsg());
		}

		RetrieveCustInfoSvcServiceStub.ResponseBody resBody = resRecord.getResponseBody();
		{
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
			stringBuffer.append(printFields(resHeader));
			stringBuffer.append(printFields(bizHeader));
			if (resBody != null) {
				RetrieveCustInfoSvcServiceStub.DsConfldsOutVO dsConfldsOut = resBody.getDsConfldsOutVO();
				if (dsConfldsOut != null) {
					stringBuffer.append(printFields(dsConfldsOut));
				}
				RetrieveCustInfoSvcServiceStub.DsCustInfoOutVO dsCustInfoOut = resBody.getDsCustInfoOutVO();
				if (dsCustInfoOut != null) {
					stringBuffer.append(printFields(dsCustInfoOut));
				}
				RetrieveCustInfoSvcServiceStub.DsDevInfoOutVO[] dsDevInfoOut = resBody.getDsDevInfoOutVO();
				if (dsDevInfoOut != null) {
					for (int i = 0; i < dsDevInfoOut.length; i++) {
						stringBuffer.append(printFields(dsDevInfoOut[i]));
					}
				}
				RetrieveCustInfoSvcServiceStub.DsSvcInfoOutVO[] dsSvcInfoOut = resBody.getDsSvcInfoOutVO();
				if (dsSvcInfoOut != null) {
					for (int i = 0; i < dsSvcInfoOut.length; i++) {
						stringBuffer.append(printFields(dsSvcInfoOut[i]));
					}
				}
			}

			traceResponse(stringBuffer.toString());
		}

		return resBody;
		// RetrieveCustInfoSvcServiceStub.DsConfldsOutVO dsConfldsOut =
		// resBody.getDsConfldsOutVO();
		// if (dsConfldsOut != null) {
		// // dsConflds
		// // 사용자아이디
		// dsConfldsOut.getUserId();
		// // 사용자아이디명
		// dsConfldsOut.getUserNm();
		// // 사용자조직명
		// dsConfldsOut.getUserOrgCd();
		// // 사용자대리점
		// dsConfldsOut.getUserDlrCd();
		// // 사용자대리점명
		// dsConfldsOut.getUserDlrNm();
		// // 오퍼레이터아이디
		// dsConfldsOut.getOperatorId();
		// // 락모드
		// dsConfldsOut.getLockMode();
		// // 씨엔아이디
		// dsConfldsOut.getCnId();
		// // 지시
		// dsConfldsOut.getDirective();
		// // 트랜잭션모드
		// dsConfldsOut.getTransactionMode();
		// // 사용자대리점코드
		// dsConfldsOut.getUserWorkDlrCd();
		// // 사용자대리점명
		// dsConfldsOut.getUserWorkDlrNm();
		// // 실행날짜
		// dsConfldsOut.getRunDate();
		// // 실행날짜
		// dsConfldsOut.getRunDateDtm();
		// // 가입번호
		// dsConfldsOut.getEntrNo();
		// // 가입업데이트 날짜
		// dsConfldsOut.getEntrSysUpdateDate();
		// // 가입업데이트스탬프
		// dsConfldsOut.getEntrDlUpdateStamp();
		// // 계약번호
		// dsConfldsOut.getAceno();
		// // 가입계약업데이트날짜
		// dsConfldsOut.getCntcSysUpdateDate();
		// // 가입계약업데이트스탬프
		// dsConfldsOut.getCntcDlUpdateStamp();
		// // 청구계정번호
		// dsConfldsOut.getBillAcntNo();
		// // 청구업데이트날짜
		// dsConfldsOut.getBillSysUpdateDate();
		// // 청구업데이트스탬프
		// dsConfldsOut.getBillDlUpdateStamp();
		// }
		//
		// RetrieveCustInfoSvcServiceStub.DsCustInfoOutVO dsCustInfoOut =
		// resBody.getDsCustInfoOutVO();
		// if (dsCustInfoOut != null) {
		// // dsCustInfo
		// // 고객번호
		// dsCustInfoOut.getCustNo();
		// // 고객명
		// dsCustInfoOut.getCustNm();
		// // 주민번호
		// dsCustInfoOut.getCustrnmNo();
		// // 고객구분
		// dsCustInfoOut.getCustDvCd();
		// // 고객구분명
		// dsCustInfoOut.getCustDvNm();
		// // 고객유형
		// dsCustInfoOut.getCustKdCd();
		// // 고객유형명
		// dsCustInfoOut.getCustKdNm();
		// // 사업자번호
		// dsCustInfoOut.getBsRegNo();
		// // 가입번호
		// dsCustInfoOut.getEntrNo();
		// // 가입이력번호
		// dsCustInfoOut.getValdEndDttm();
		// // 상품구분
		// dsCustInfoOut.getProdCd();
		// // 상품번호
		// dsCustInfoOut.getProdNo();
		// // 통합상품코드
		// dsCustInfoOut.getIntgProdCd();
		// // 통합상품가입구분번호
		// dsCustInfoOut.getIntgProdEntrDvNo();
		// // 가입계약번호
		// dsCustInfoOut.getAceno();
		// // 청구계정번호
		// dsCustInfoOut.getBillAcntNo();
		// // 납부계정번호
		// dsCustInfoOut.getPymAcntNo();
		// // 마켓코드
		// dsCustInfoOut.getMrktCd();
		// // 별정유무
		// dsCustInfoOut.getMic2();
		// // 가입구분
		// dsCustInfoOut.getEntrKdCd();
		// // 가입대리점
		// dsCustInfoOut.getEntrBizPlcyCd();
		// // 최종대리점
		// dsCustInfoOut.getLastBizPlcyCd();
		// // 최종대리점변경일
		// dsCustInfoOut.getLastBizPlcyChngDttm();
		// // 가입상태
		// dsCustInfoOut.getEntrSttsCd();
		// // 가입상태변경사유코드
		// dsCustInfoOut.getEntrSttsChngRsnCd();
		// // 가입상태변경상세사유코드
		// dsCustInfoOut.getEntrSttsChngRsnDtlCd();
		// // 가입대리점코드
		// dsCustInfoOut.getEntrDlrCd();
		// // 최초가입일
		// dsCustInfoOut.getFrstEntrDttm();
		// // 가입상태변경일
		// dsCustInfoOut.getEntrSttsChngDttm();
		// // 연체상태코드
		// dsCustInfoOut.getLtpymSttsCd();
		// // 연체상태변경일자
		// dsCustInfoOut.getLtpymSttsChngDt();
		// // 연체활동경로코드
		// dsCustInfoOut.getLtpymActPathCd();
		// // 연체활동다음단계번호
		// dsCustInfoOut.getLtpymActNextStgeNo();
		// // 연체활동다음단계예정일자
		// dsCustInfoOut.getLtpymActNextStgeScdlDt();
		// // 계약상태등급코드
		// dsCustInfoOut.getCntcSttsGrdCd();
		// // 상품명
		// dsCustInfoOut.getProdNm();
		// // 가입상태명
		// dsCustInfoOut.getEntrSttsNm();
		// // 가입상태변경사유명
		// dsCustInfoOut.getEntrSttsChngRsnNm();
		// // 가입상태변경상세사유명
		// dsCustInfoOut.getEntrSttsChngRsnDtlNm();
		// // 실사용자고객번호
		// dsCustInfoOut.getRlusrCustNo();
		// // 실사용자고객이름
		// dsCustInfoOut.getRlusrCustNm();
		// // 실사용자주민번호
		// dsCustInfoOut.getRlusrCustrnmNo();
		// // 실사용자고객구분
		// dsCustInfoOut.getRlusrCustDvCd();
		// // 실사용자고객구분명
		// dsCustInfoOut.getRlusrCustDvNm();
		// // 실사용자고객유형
		// dsCustInfoOut.getRlusrCustKdCd();
		// // 실사용자고객유형명
		// dsCustInfoOut.getRlusrCustKdNm();
		// // 실사용자사업자번호
		// dsCustInfoOut.getRlusrBsRegNo();
		// // 실사용자이메일
		// dsCustInfoOut.getEmailAddr();
		// // 고객등급
		// dsCustInfoOut.getCustGrdCd();
		// // 고객등급명
		// dsCustInfoOut.getCustGrdNm();
		// // LTV등급코드
		// dsCustInfoOut.getLtvGrdCd();
		// // LTV등급명
		// dsCustInfoOut.getLtvGrdNm();
		// // 기변등급코드
		// dsCustInfoOut.getDevchGrdCd();
		// // 기변등급명
		// dsCustInfoOut.getDevchGrdNm();
		// // 해지가능성등급코드
		// dsCustInfoOut.getExpryGrdCd();
		// // 해지가능성등급명
		// dsCustInfoOut.getExpryGrdNm();
		// // 임대폰대여여부
		// dsCustInfoOut.getSelected();
		// // 청구주소일련번호
		// dsCustInfoOut.getBillAddrSeqno();
		// // 청구연락처일련번호
		// dsCustInfoOut.getCntctPntSeqno();
		// // 후순위납부계정번호
		// dsCustInfoOut.getAftRankPymAcntNo();
		// // 청구이메일
		// dsCustInfoOut.getBillEmailAddr();
		// // 청구계정상태
		// dsCustInfoOut.getBillAcntSttsCd();
		// // 청구계정상태명
		// dsCustInfoOut.getBillAcntSttsNm();
		// // 청구우편번호
		// dsCustInfoOut.getCustAddrZip();
		// // 청구유편번호상위3자리
		// dsCustInfoOut.getCustAddrZip1();
		// // 청구유편번호하위3자리
		// dsCustInfoOut.getCustAddrZip2();
		// // 청구주소상위
		// dsCustInfoOut.getCustVilgAbvAddr();
		// // 청구주소하위
		// dsCustInfoOut.getCustVilgBlwAddr();
		// // 청구주소
		// dsCustInfoOut.getCustVilgAddr();
		// // 청구별정구분
		// dsCustInfoOut.getBlMic2();
		// // 청구선불구분
		// dsCustInfoOut.getPpayAcntYn();
		// // 납부고객번호
		// dsCustInfoOut.getPymManCustNo();
		// // 납부소유자번호
		// dsCustInfoOut.getAcntOwnrNo();
		// // 납부방법
		// dsCustInfoOut.getPymMthdCd();
		// // 납부방법명
		// dsCustInfoOut.getPymMthdNm();
		// // 할인구분코드
		// dsCustInfoOut.getIsDcNo();
		// // 할인구분코드
		// dsCustInfoOut.getIsAgmtDc();
		// // 할인구분코드
		// dsCustInfoOut.getIsPntDc();
		// // 가족사랑여부
		// dsCustInfoOut.getIsFmlyLovDc();
		// // 가족사랑대표여부
		// dsCustInfoOut.getIsFmlyRept();
		// // 청구할인여부
		// dsCustInfoOut.getIsBillDc();
		// // 할인구분코드
		// dsCustInfoOut.getIsEcoDc();
		// // 적용개월
		// dsCustInfoOut.getAplyMnth();
		// // 잔여개월
		// dsCustInfoOut.getRmndMnth();
		// // 임시번호
		// dsCustInfoOut.getImsiNo();
		// }
		//
		// RetrieveCustInfoSvcServiceStub.DsDevInfoOutVO[] dsDevInfoOut =
		// resBody.getDsDevInfoOutVO();
		// if (dsDevInfoOut != null) {
		// for (int i = 0; i < dsDevInfoOut.length; i++) {
		// // dsDevInfo
		// // 가입별단말기누적일련번호
		// dsDevInfoOut[i].getDevByEntrSeqno();
		// // 모델ID
		// dsDevInfoOut[i].getItemId();
		// // 모델일련번호
		// dsDevInfoOut[i].getManfSerialNo();
		// // 단말기긴급구분코드
		// dsDevInfoOut[i].getEmergencyIndicator();
		// // 단말기등록일자
		// dsDevInfoOut[i].getDevRgstDt();
		// // 단말기유효종료일자
		// dsDevInfoOut[i].getDevValdEndDt();
		// // 단말기상태코드
		// dsDevInfoOut[i].getItemStatus();
		// // sid
		// dsDevInfoOut[i].getDevChngRsnCd();
		// // 단말기변경사유코드
		// dsDevInfoOut[i].getDevChngRsnNm();
		// // chip id
		// dsDevInfoOut[i].getChipId();
		// // chip 상태
		// dsDevInfoOut[i].getChipStatus();
		// }
		// }
		//
		// RetrieveCustInfoSvcServiceStub.DsSvcInfoOutVO[] dsSvcInfoOut =
		// resBody.getDsSvcInfoOutVO();
		// if (dsSvcInfoOut != null) {
		// for (int i = 0; i < dsSvcInfoOut.length; i++) {
		// // dsSvcInfo
		// // 가입번호
		// dsSvcInfoOut[i].getEntrNo();
		// // 상품번호
		// dsSvcInfoOut[i].getProdNo();
		// // 상품구분
		// dsSvcInfoOut[i].getProdCd();
		// // 상품구분명
		// dsSvcInfoOut[i].getProdNm();
		// // 서비스코드
		// dsSvcInfoOut[i].getSvcCd();
		// // 서비스코드명
		// dsSvcInfoOut[i].getSvcNm();
		// // 가입서비스번호
		// dsSvcInfoOut[i].getEntrSvcSeqno();
		// // 상위서비스코드
		// dsSvcInfoOut[i].getHposSvcCd();
		// // 상위서비스일련번호
		// dsSvcInfoOut[i].getHposEntrSvcSeqno();
		// // 서비스유형코드
		// dsSvcInfoOut[i].getSvcKdCd();
		// // 서비스유형명
		// dsSvcInfoOut[i].getSvcKdNm();
		// // 서비스적용레벨코드
		// dsSvcInfoOut[i].getSvcAplyLvlCd();
		// // 서비스적용레벨명
		// dsSvcInfoOut[i].getSvcAplyLvlNm();
		// // 서비스상태코드
		// dsSvcInfoOut[i].getSvcSttsCd();
		// // 서비스상태변경일자
		// dsSvcInfoOut[i].getSvcSttsChngDttm();
		// // 서비스최초시작일자
		// dsSvcInfoOut[i].getSvcFrstStrtDttm();
		// // 서비스시작등록대리점코드
		// dsSvcInfoOut[i].getSvcStrtRgstDlrCd();
		// // 서비스시작등록일자
		// dsSvcInfoOut[i].getSvcStrtRgstDttm();
		// // 서비스시작사유코드
		// dsSvcInfoOut[i].getSvcStrtRsnCd();
		// // 서비스시작일자
		// dsSvcInfoOut[i].getSvcStrtDttm();
		// // 서비스종료일자
		// dsSvcInfoOut[i].getSvcEndDttm();
		// // 서비스종료사유코드
		// dsSvcInfoOut[i].getSvcEndRsnCd();
		// // 서비스종료등록일자
		// dsSvcInfoOut[i].getSvcEndRgstDttm();
		// // 서비스의무사용시작일자
		// dsSvcInfoOut[i].getSvcDutyUseStrtDt();
		// // 서비스의무사용종료일자
		// dsSvcInfoOut[i].getSvcDutyUseEndDt();
		// // 의무사용개월수
		// dsSvcInfoOut[i].getSvcDutyUseMnthCnt();
		// // 의무사용구분코드
		// dsSvcInfoOut[i].getSvcDutyUseDvCd();
		// // 요금(기본료)
		// dsSvcInfoOut[i].getRate();
		// // 정지상태의 요금
		// dsSvcInfoOut[i].getSusRate();
		// // 시간단위구분코드
		// dsSvcInfoOut[i].getTermUnitDvCd();
		// // 서비스 관계구분코드
		// dsSvcInfoOut[i].getSvcRelsDvCd();
		// }
		// }
	}

	/**
	 * @throws:
	 * @Part :
	 * @Author :
	 * @Component :
	 * @Primitive :
	 * @type :
	 * @Description:[SM546] 가입별할인관리
	 */
	private RetrieveDscntMgmtServiceStub.ResponseBody retrieveDscntMgmt(
			RetrieveDscntMgmtServiceStub.DsSrchInfoInVO[] dsSrchInfoIn) throws ESBException {
		// mode 작업구분 VARCHAR2 14 N 복수 (LIST:조회,SAVE_ACT:할인등록,SAVE_EXP:할인만기)
		// entrNo 가입번호 VARCHAR2 14 Y 복수 상품번호 존재시 NULL가능
		// prodNo 상품번호 VARCHAR2 14 Y 복수 가입번호 존재시 NULL가능
		// entrByDscntSeqno entrByDscntSeqno VARCHAR2 14 Y 복수 SAVE_EXP:할인만기시 필수
		// svcCd 상품코드 VARCHAR2 14 Y 복수 종속할인적용시 필수
		// svcDpndRelsCd 상품종속관계코드 VARCHAR2 14 Y 복수 미존재시 SMD/PMD
		// dscntSvcCd 할인코드 VARCHAR2 14 Y 복수 할인코드 존재시 상품코드 무시
		// entrDscntKdCd 할인유형코드 VARCHAR2 14 Y 복수 미존재시 GEN
		// dscntSttsKdCd 할인상태코드 VARCHAR2 14 Y 복수 (미입력시,등록-ACT,만기-EXP)
		// dscntStrtDttm 할인시작일자 VARCHAR2 14 Y 복수 dscntStrtKdCd 미입력시당일시작
		// dscntEndDttm 할인종료일자 VARCHAR2 14 Y 복수 (SAVE_ACT:dscntEndKdCd
		// 미입력시99991231,SAVE_EXP:할인만기시 필수)
		// dscntStrtKdCd 할인시작구분 VARCHAR2 14 Y 복수 (A:즉시(당일),B:익월1일,M1:1개월후부터
		// 시작,M3:3개월후부터 시작)
		// dscntEndKdCd 할인종료구분 VARCHAR2 14 Y 복수 (A:무제한,M3:3개월간 적용,D30:30일간 적용)
		// agmtStrtDttm 약정시작일자 VARCHAR2 14 Y 복수
		// agmtEndDttm 약정종료일자 VARCHAR2 14 Y 복수
		// agmtMnbr 약정개월 VARCHAR2 14 Y 복수

		dsSrchInfoIn[0].setNextOperatorId(createNextOperatorId());

		// RetrieveDscntMgmtServiceStub.DsSrchInfoInVO[] dsSrchInfoIn = new
		// RetrieveDscntMgmtServiceStub.DsSrchInfoInVO[1];
		// dsSrchInfoIn[0] = new RetrieveDscntMgmtServiceStub.DsSrchInfoInVO();
		// dsSrchInfoIn[0].setMode(mode);
		// dsSrchInfoIn[0].setEntrNo(entrNo);
		// dsSrchInfoIn[0].setProdNo(prodNo);
		// dsSrchInfoIn[0].setEntrByDscntSeqno(entrByDscntSeqno);
		// dsSrchInfoIn[0].setSvcCd(svcCd);
		// dsSrchInfoIn[0].setSvcDpndRelsCd(svcDpndRelsCd);
		// dsSrchInfoIn[0].setDscntSvcCd(dscntSvcCd);
		// dsSrchInfoIn[0].setEntrDscntKdCd(entrDscntKdCd);
		// dsSrchInfoIn[0].setDscntSttsKdCd(dscntSttsKdCd);
		// dsSrchInfoIn[0].setDscntStrtDttm(dscntStrtDttm);
		// dsSrchInfoIn[0].setDscntEndDttm(dscntEndDttm);
		// dsSrchInfoIn[0].setDscntStrtKdCd(dscntStrtKdCd);
		// dsSrchInfoIn[0].setDscntEndKdCd(dscntEndKdCd);
		// dsSrchInfoIn[0].setAgmtStrtDttm(agmtStrtDttm);
		// dsSrchInfoIn[0].setAgmtEndDttm(agmtEndDttm);
		// dsSrchInfoIn[0].setAgmtMnbr(agmtMnbr);
		// dsSrchInfoIn[0].setNextOperatorId(nextOperatorId);

		//
		RetrieveDscntMgmtServiceStub.RequestBody reqBody = new RetrieveDscntMgmtServiceStub.RequestBody();
		reqBody.setDsSrchInfoInVO(dsSrchInfoIn);

		//
		RetrieveDscntMgmtServiceStub.ESBHeader reqHeader = new RetrieveDscntMgmtServiceStub.ESBHeader();
		reqHeader.setServiceID("SM546");
		reqHeader.setTransactionID(createTxId());
		reqHeader.setSystemID(SYSTEM_ID);
		reqHeader.setErrCode("");
		reqHeader.setErrMsg("");
		reqHeader.setReserved("");

		//
		RetrieveDscntMgmtServiceStub.RequestRecord reqRecord = new RetrieveDscntMgmtServiceStub.RequestRecord();
		reqRecord.setRequestBody(reqBody);
		reqRecord.setESBHeader(reqHeader);

		//
		RetrieveDscntMgmtServiceStub.RetrieveDscntMgmt reqIn = new RetrieveDscntMgmtServiceStub.RetrieveDscntMgmt();
		reqIn.setRequestRecord(reqRecord);

		//
		String host = URL;
		String path = "/CSSI/SM/RetrieveDscntMgmt";
		String targetEndpoint = host + path;

		RetrieveDscntMgmtServiceStub stub = null;
		RetrieveDscntMgmtServiceStub.RetrieveDscntMgmtResponse res = null;

		try {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(printFields(dsSrchInfoIn[0]));

				traceRequest(stringBuffer.toString());
			}

			stub = new RetrieveDscntMgmtServiceStub(targetEndpoint);
			res = stub.retrieveDscntMgmt(reqIn);
		} catch (RemoteException e) {
			e.printStackTrace();
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(e.getMessage());

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(reqHeader.getErrCode(), reqHeader.getErrMsg());
		}

		RetrieveDscntMgmtServiceStub.ResponseRecord resRecord = res.getResponseRecord();
		RetrieveDscntMgmtServiceStub.ESBHeader resHeader = resRecord.getESBHeader();
		if (!(resHeader.getErrCode() == null || "".equals(resHeader.getErrCode()))) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(resHeader.getErrCode(), resHeader.getErrMsg());
		}

		RetrieveDscntMgmtServiceStub.BusinessHeader bizHeader = resRecord.getBusinessHeader();
		if (bizHeader.getResultCode() == null || !"N0000".equals(bizHeader.getResultCode())) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));
				stringBuffer.append(printFields(bizHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(bizHeader.getResultCode(), bizHeader.getResultMessage());
		}

		RetrieveDscntMgmtServiceStub.ResponseBody resBody = resRecord.getResponseBody();
		{
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
			stringBuffer.append(printFields(resHeader));
			stringBuffer.append(printFields(bizHeader));
			if (resBody != null) {
				RetrieveDscntMgmtServiceStub.DsDscntListOutVO[] dsDscntListOut = resBody.getDsDscntListOutVO();
				if (dsDscntListOut != null) {
					for (int i = 0; i < dsDscntListOut.length; i++) {
						stringBuffer.append(printFields(dsDscntListOut[i]));
					}
				}
				RetrieveDscntMgmtServiceStub.DsRqstRsltOutVO[] dsRqstRsltOut = resBody.getDsRqstRsltOutVO();
				if (dsRqstRsltOut != null) {
					for (int i = 0; i < dsRqstRsltOut.length; i++) {
						stringBuffer.append(printFields(dsRqstRsltOut[i]));
					}
				}
			}

			traceResponse(stringBuffer.toString());
		}

		return resBody;

		// RetrieveDscntMgmtServiceStub.DsRqstRsltOutVO[] dsRqstRsltOut =
		// resBody.getDsRqstRsltOutVO();
		// for (int i = 0; i < dsRqstRsltOut.length; i++) {
		// //작업여부
		// dsRqstRsltOut[i].getPrssYn();
		// //불가메시지
		// dsRqstRsltOut[i].getErrMsg();
		// //불가코드
		// dsRqstRsltOut[i].getErrCode();
		// }
		//
		// RetrieveDscntMgmtServiceStub.DsDscntListOutVO[] dsDscntListOut =
		// resBody.getDsDscntListOutVO();
		// if (dsDscntListOut != null) {
		// for (int i = 0; i < dsDscntListOut.length; i++) {
		// // 가입번호
		// dsDscntListOut[i].getEntrNo();
		// // 할인시퀀스
		// dsDscntListOut[i].getEntrByDscntSeqno();
		// // 서비스코드
		// dsDscntListOut[i].getProdCd();
		// // 할인코드
		// dsDscntListOut[i].getDscntSvcCd();
		// // 할인명
		// dsDscntListOut[i].getDscntSvcNm();
		// // 할인유형코드
		// dsDscntListOut[i].getEntrDscntKdCd();
		// // 할인상태코드
		// dsDscntListOut[i].getDscntSttsKdCd();
		// // 할인최초신청일자
		// dsDscntListOut[i].getDscntFrstRqstDttm();
		// // 할인시작일자
		// dsDscntListOut[i].getDscntStrtDttm();
		// // 할인종료일자
		// dsDscntListOut[i].getDscntEndDttm();
		// // 약정시작일자
		// dsDscntListOut[i].getAgmtStrtDttm();
		// // 약정종료일자
		// dsDscntListOut[i].getAgmtEndDttm();
		// // 약정기간
		// dsDscntListOut[i].getAgmtTerm();
		// // 청구계정번호
		// dsDscntListOut[i].getBillAcntNo();
		// // 가입계약번호
		// dsDscntListOut[i].getAceno();
		// // 그룹시퀀스
		// dsDscntListOut[i].getSvcGrpSeqno();
		// }
		// }
	}

	/**
	 * @throws :
	 * @Part :
	 * @Author :
	 * @Component :
	 * @Primitive :
	 * @type :
	 * @Description : [ARC239] 인터넷수납
	 */
	@SuppressWarnings("unused")
	private SaveArPymIntServiceStub.ResponseBody saveArPymInt(SaveArPymIntServiceStub.DsReqInVO dsReqIn)
			throws ESBException {
		// prodNo 상품번호 VARCHAR2 20 N 단수 -
		// ccrdAprvRqstAmt 금액 NUMBER 13 N 단수 Default : 0
		// cardNo 카드번호 VARCHAR2 16 Y 단수 -
		// ccrdCompCd 카드사코드 VARCHAR2 2 Y 단수 -
		// ccrdValdEndDt 카드유효종료일자 VARCHAR2 6 Y 단수 -
		// ccrdIndvCoDvCd 개인/법인 VARCHAR2 1 Y 개인: 0 법인: 1 Default : 0
		// ccrdOwnrPersNo 고객주민번호 VARCHAR2 13 N -
		// ccrdStlmInsttMms 카드할부개월수 NUMBER 13 Y Default : 0
		// ccrdStlmAprvRsltCd 카드승인결과코드 VARCHAR2 2 Y -
		// operatingDealer 처리자 VARCHAR2 6 Y -
		// sourceType 구분코드 VARCHAR2 1 N 카드결제:C 계좌이체:R
		// custBankCode 은행코드 VARCHAR2 3 Y -
		// bankAcctNo 계좌번호 VARCHAR2 16 Y -
		// pymCustNm 납부자명 VARCHAR2 100 Y -
		// nextOperatorId 처리자 VARCHAR2 15 Y

		dsReqIn.setNextOperatorId(createNextOperatorId());

		// SaveArPymIntServiceStub.DsReqInVO dsReqIn = new
		// SaveArPymIntServiceStub.DsReqInVO();
		// dsReqIn.setProdNo(prodNo);
		// dsReqIn.setCcrdAprvRqstAmt(ccrdAprvRqstAmt);
		// dsReqIn.setCardNo(cardNo);
		// dsReqIn.setCcrdCompCd(ccrdCompCd);
		// dsReqIn.setCcrdValdEndDt(ccrdValdEndDt);
		// dsReqIn.setCcrdIndvCoDvCd(ccrdIndvCoDvCd);
		// dsReqIn.setCcrdOwnrPersNo(ccrdOwnrPersNo);
		// dsReqIn.setCcrdStlmInsttMms(ccrdStlmInsttMms);
		// dsReqIn.setCcrdStlmAprvRsltCd(ccrdStlmAprvRsltCd);
		// dsReqIn.setOperatingDealer(operatingDealer);
		// dsReqIn.setSourceType(sourceType);
		// dsReqIn.setCustBankCode(custBankCode);
		// dsReqIn.setBankAcctNo(bankAcctNo);
		// dsReqIn.setPymCustNm(pymCustNm);
		// dsReqIn.setNextOperatorId(nextOperatorId);

		//
		SaveArPymIntServiceStub.RequestBody reqBody = new SaveArPymIntServiceStub.RequestBody();
		reqBody.setDsReqInVO(dsReqIn);

		//
		SaveArPymIntServiceStub.ESBHeader reqHeader = new SaveArPymIntServiceStub.ESBHeader();
		reqHeader.setServiceID("ARC239");
		reqHeader.setTransactionID(createTxId());
		reqHeader.setSystemID(SYSTEM_ID);
		reqHeader.setErrCode("");
		reqHeader.setErrMsg("");
		reqHeader.setReserved("");

		//
		SaveArPymIntServiceStub.RequestRecord reqRecord = new SaveArPymIntServiceStub.RequestRecord();
		reqRecord.setRequestBody(reqBody);
		reqRecord.setESBHeader(reqHeader);

		//
		SaveArPymIntServiceStub.SaveArPymInt reqIn = new SaveArPymIntServiceStub.SaveArPymInt();
		reqIn.setRequestRecord(reqRecord);

		//
		String host = URL;
		String path = "/CSSI/ARC/SaveArPymInt";
		String targetEndpoint = host + path;

		SaveArPymIntServiceStub stub = null;
		SaveArPymIntServiceStub.SaveArPymIntResponse res = null;

		try {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(printFields(dsReqIn));

				traceRequest(stringBuffer.toString());
			}
			stub = new SaveArPymIntServiceStub(targetEndpoint);
			res = stub.saveArPymInt(reqIn);
		} catch (RemoteException e) {
			e.printStackTrace();
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(e.getMessage());

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(reqHeader.getErrCode(), reqHeader.getErrMsg());
		}

		SaveArPymIntServiceStub.ResponseRecord resRecord = res.getResponseRecord();
		SaveArPymIntServiceStub.ESBHeader resHeader = resRecord.getESBHeader();
		if (!(resHeader.getErrCode() == null || "".equals(resHeader.getErrCode()))) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(resHeader.getErrCode(), resHeader.getErrMsg());
		}

		SaveArPymIntServiceStub.BusinessHeader bizHeader = resRecord.getBusinessHeader();
		if (bizHeader.getResultCode() == null || !"N0000".equals(bizHeader.getResultCode())) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));
				stringBuffer.append(printFields(bizHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(bizHeader.getResultCode(), bizHeader.getResultMessage());
		}

		SaveArPymIntServiceStub.ResponseBody resBody = resRecord.getResponseBody();
		{
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
			stringBuffer.append(printFields(resHeader));
			stringBuffer.append(printFields(bizHeader));
			if (resBody != null) {
				SaveArPymIntServiceStub.DsResOutVO dsResOut = resBody.getDsResOutVO();
				if (dsResOut != null) {
					stringBuffer.append(printFields(dsResOut));
				}
			}

			traceResponse(stringBuffer.toString());
		}

		return resBody;
		// SaveArPymIntServiceStub.DsResOutVO dsResOut =
		// resBody.getDsResOutVO();
		// //
		// if (dsResOut != null) {
		// // 카드번호
		// dsResOut.getCardNo();
		// // 금액
		// dsResOut.getCcrdAprvRqstAmt();
		// // 카드사코드
		// dsResOut.getCcrdCompCd();
		// // 개인/법인
		// dsResOut.getCcrdIndvCoDvCd();
		// // 고객주민번호
		// dsResOut.getCcrdOwnrPersNo();
		// // 카드승인결과코드
		// dsResOut.getCcrdStlmAprvRsltCd();
		// // 카드할부개월수
		// dsResOut.getCcrdStlmInsttMms();
		// // 카드유효종료일자
		// dsResOut.getCcrdValdEndDt();
		// // 처리자
		// dsResOut.getOperatingDealer();
		// // 상품번호
		// dsResOut.getProdNo();
		// // 결과코드
		// dsResOut.getResultCode();
		// }
	}

	/**
	 * @throws :
	 * @throws :
	 * @Part :
	 * @Author :
	 * @Component :
	 * @Primitive :
	 * @type :
	 * @Description : [ARC472] 고객전용 입금계좌조회
	 */
	@SuppressWarnings("unused")
	private RetrieveVtAcntServiceStub.ResponseBody retrieveVtAcnt(RetrieveVtAcntServiceStub.DsReqInVO dsReqIn)
			throws ESBException {
		// custMgmtNo 고객관리번호 VARCHAR2 12 N 단수 청구계정번호/고객번호
		// entrNo 가입번호 NUMBER 12 Y 단수
		// prodNo 전화번호 NUMBER 12 Y 단수

		dsReqIn.setNextOperatorId(createNextOperatorId());

		// RetrieveVtAcntServiceStub.DsReqInVO dsReqIn = new
		// RetrieveVtAcntServiceStub.DsReqInVO();
		// dsReqIn.setCustMgmtNo(custMgmtNo);
		// dsReqIn.setEntrNo(entrNo);
		// dsReqIn.setProdNo(prodNo);
		// dsReqIn.setNextOperatorId(nextOperatorId);

		//
		RetrieveVtAcntServiceStub.RequestBody reqBody = new RetrieveVtAcntServiceStub.RequestBody();
		reqBody.setDsReqInVO(dsReqIn);

		//
		RetrieveVtAcntServiceStub.ESBHeader reqHeader = new RetrieveVtAcntServiceStub.ESBHeader();
		reqHeader.setServiceID("ARC472");
		reqHeader.setTransactionID(createTxId());
		reqHeader.setSystemID(SYSTEM_ID);
		reqHeader.setErrCode("");
		reqHeader.setErrMsg("");
		reqHeader.setReserved("");

		//
		RetrieveVtAcntServiceStub.RequestRecord reqRecord = new RetrieveVtAcntServiceStub.RequestRecord();
		reqRecord.setRequestBody(reqBody);
		reqRecord.setESBHeader(reqHeader);

		//
		RetrieveVtAcntServiceStub.RetrieveVtAcnt reqIn = new RetrieveVtAcntServiceStub.RetrieveVtAcnt();
		reqIn.setRequestRecord(reqRecord);

		//
		String host = URL;
		String path = "/CSSI/ARC/RetrieveVtAcnt";
		String targetEndpoint = host + path;

		RetrieveVtAcntServiceStub stub = null;
		RetrieveVtAcntServiceStub.RetrieveVtAcntResponse res = null;

		try {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(printFields(dsReqIn));

				traceRequest(stringBuffer.toString());
			}
			stub = new RetrieveVtAcntServiceStub(targetEndpoint);
			res = stub.retrieveVtAcnt(reqIn);
		} catch (RemoteException e) {
			e.printStackTrace();
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(e.getMessage());

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(reqHeader.getErrCode(), reqHeader.getErrMsg());
		}

		RetrieveVtAcntServiceStub.ResponseRecord resRecord = res.getResponseRecord();
		RetrieveVtAcntServiceStub.ESBHeader resHeader = resRecord.getESBHeader();
		if (!(resHeader.getErrCode() == null || "".equals(resHeader.getErrCode()))) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(resHeader.getErrCode(), resHeader.getErrMsg());
		}

		RetrieveVtAcntServiceStub.BusinessHeader bizHeader = resRecord.getBusinessHeader();
		if (bizHeader.getResultCode() == null || !"N0000".equals(bizHeader.getResultCode())) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));
				stringBuffer.append(printFields(bizHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(bizHeader.getResultCode(), bizHeader.getResultMessage());
		}

		RetrieveVtAcntServiceStub.ResponseBody resBody = resRecord.getResponseBody();
		{
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
			stringBuffer.append(printFields(resHeader));
			stringBuffer.append(printFields(bizHeader));
			if (resBody != null) {
				RetrieveVtAcntServiceStub.DsResOutVO[] dsResOut = resBody.getDsResOutVO();
				if (dsResOut != null) {
					for (int i = 0; i < dsResOut.length; i++) {
						stringBuffer.append(printFields(dsResOut[i]));
					}
				}
			}

			traceResponse(stringBuffer.toString());
		}

		return resBody;
		// RetrieveVtAcntServiceStub.DsResOutVO[] dsResOut =
		// resBody.getDsResOutVO();
		// if(dsResOut!=null){
		// for (int i = 0; i < dsResOut.length; i++) {
		// // 은행명
		// dsResOut[i].getBankNm();
		// // 입금전용계좌
		// dsResOut[i].getVtAcntNo();
		// // 은행코드
		// dsResOut[i].getBankCd();
		// }
		// }
	}

	/**
	 * @throws :
	 * @Part :
	 * @Author :
	 * @Component :
	 * @Primitive :
	 * @type :
	 * @Description : [BIL417] 청구상세내역_할인내역 및 청구서 조회
	 */
	private RetrieveDscntNblTxtServiceStub.ResponseBody retrieveDscntNblTxt(
			RetrieveDscntNblTxtServiceStub.DeReqInVO deReqIn) throws ESBException {
		// billAcntNo 청구계정번호 NUMBER 12 N 단수
		// billTrgtYymm 청구대상년월 VARCHAR2 6 N 단수
		// aceno 가입계약번호 NUMBER 12 Y 단수
		// entrNo 가입번호 NUMBER 12 Y 단수
		// prodNo 상품번호 VARCHAR2 20 Y 단수
		// prodCd 상품코드 VARCHAR2 10 Y 단수
		// fromPage 시작페이지 NUMBER 10 Y 단수
		// toPage 종료페이지 NUMBER 10 Y 단수
		// nextOperatorId 처리자ID NUMBER 15 N 단수

		deReqIn.setNextOperatorId(createNextOperatorId());

		// RetrieveDscntNblTxtServiceStub.DeReqInVO deReqIn = new
		// RetrieveDscntNblTxtServiceStub.DeReqInVO();
		// deReqIn.setBillAcntNo(billAcntNo);
		// deReqIn.setBillTrgtYymm(billTrgtYymm);
		// deReqIn.setAceno(aceno);
		// deReqIn.setEntrNo(entrNo);
		// deReqIn.setProdNo(prodNo);
		// deReqIn.setProdCd(prodCd);
		// deReqIn.setFromPage(fromPage);
		// deReqIn.setToPage(toPage);
		// deReqIn.setNextOperatorId(nextOperatorId);

		//
		RetrieveDscntNblTxtServiceStub.RequestBody reqBody = new RetrieveDscntNblTxtServiceStub.RequestBody();
		reqBody.setDeReqInVO(deReqIn);

		//
		RetrieveDscntNblTxtServiceStub.ESBHeader reqHeader = new RetrieveDscntNblTxtServiceStub.ESBHeader();
		reqHeader.setServiceID("BIL417");
		reqHeader.setTransactionID(createTxId());
		reqHeader.setSystemID(SYSTEM_ID);
		reqHeader.setErrCode("");
		reqHeader.setErrMsg("");
		reqHeader.setReserved("");

		//
		RetrieveDscntNblTxtServiceStub.RequestRecord reqRecord = new RetrieveDscntNblTxtServiceStub.RequestRecord();
		reqRecord.setRequestBody(reqBody);
		reqRecord.setESBHeader(reqHeader);

		//
		RetrieveDscntNblTxtServiceStub.RetrieveDscntNblTxt reqIn = new RetrieveDscntNblTxtServiceStub.RetrieveDscntNblTxt();
		reqIn.setRequestRecord(reqRecord);

		//
		String host = URL;
		String path = "/CSSI/BIL/RetrieveDscntNblTxt";
		String targetEndpoint = host + path;

		RetrieveDscntNblTxtServiceStub stub = null;
		RetrieveDscntNblTxtServiceStub.RetrieveDscntNblTxtResponse res = null;

		try {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(printFields(deReqIn));

				traceRequest(stringBuffer.toString());
			}
			stub = new RetrieveDscntNblTxtServiceStub(targetEndpoint);
			res = stub.retrieveDscntNblTxt(reqIn);
		} catch (RemoteException e) {
			e.printStackTrace();
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(e.getMessage());

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(reqHeader.getErrCode(), reqHeader.getErrMsg());
		}

		RetrieveDscntNblTxtServiceStub.ResponseRecord resRecord = res.getResponseRecord();
		RetrieveDscntNblTxtServiceStub.ESBHeader resHeader = resRecord.getESBHeader();
		if (!(resHeader.getErrCode() == null || "".equals(resHeader.getErrCode()))) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(resHeader.getErrCode(), resHeader.getErrMsg());
		}

		RetrieveDscntNblTxtServiceStub.BusinessHeader bizHeader = resRecord.getBusinessHeader();
		if (bizHeader.getResultCode() == null || !"N0000".equals(bizHeader.getResultCode())) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));
				stringBuffer.append(printFields(bizHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(bizHeader.getResultCode(), bizHeader.getResultMessage());
		}

		RetrieveDscntNblTxtServiceStub.ResponseBody resBody = resRecord.getResponseBody();
		{
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
			stringBuffer.append(printFields(resHeader));
			stringBuffer.append(printFields(bizHeader));
			if (resBody != null) {
				RetrieveDscntNblTxtServiceStub.DsDscntAcltnAmtOutVO[] DsDscntAcltnAmtOut = resBody
						.getDsDscntAcltnAmtOutVO();
				if (DsDscntAcltnAmtOut != null) {
					for (int i = 0; i < DsDscntAcltnAmtOut.length; i++) {
						stringBuffer.append(printFields(DsDscntAcltnAmtOut[i]));
					}
				}
				RetrieveDscntNblTxtServiceStub.DsBillMnthOutVO[] dsBillMnthOut = resBody.getDsBillMnthOutVO();
				if (dsBillMnthOut != null) {
					for (int i = 0; i < dsBillMnthOut.length; i++) {
						stringBuffer.append(printFields(dsBillMnthOut[i]));
					}
				}
				RetrieveDscntNblTxtServiceStub.DsPymRshtOutVO[] dsPymRshtOut = resBody.getDsPymRshtOutVO();
				if (dsPymRshtOut != null) {
					for (int i = 0; i < dsPymRshtOut.length; i++) {
						stringBuffer.append(printFields(dsPymRshtOut[i]));
					}
				}
				RetrieveDscntNblTxtServiceStub.DsFmldExamtOutVO[] dsFmldExamtOut = resBody.getDsFmldExamtOutVO();
				if (dsFmldExamtOut != null) {
					for (int i = 0; i < dsFmldExamtOut.length; i++) {
						stringBuffer.append(printFields(dsFmldExamtOut[i]));
					}
				}
				RetrieveDscntNblTxtServiceStub.DsOilingDsCntDtlOutVO[] dsOilingDsCntDtlOut = resBody
						.getDsOilingDsCntDtlOutVO();
				if (dsOilingDsCntDtlOut != null) {
					for (int i = 0; i < dsOilingDsCntDtlOut.length; i++) {
						stringBuffer.append(printFields(dsOilingDsCntDtlOut[i]));
					}
				}
				RetrieveDscntNblTxtServiceStub.DsLzoneOutVO[] dsLzoneOut = resBody.getDsLzoneOutVO();
				if (dsLzoneOut != null) {
					for (int i = 0; i < dsLzoneOut.length; i++) {
						stringBuffer.append(printFields(dsLzoneOut[i]));
					}
				}
				RetrieveDscntNblTxtServiceStub.DsDscntPnltDtlOutVO[] dsDscntPnltDtlOut = resBody
						.getDsDscntPnltDtlOutVO();
				if (dsDscntPnltDtlOut != null) {
					for (int i = 0; i < dsDscntPnltDtlOut.length; i++) {
						stringBuffer.append(printFields(dsDscntPnltDtlOut[i]));
					}
				}
				RetrieveDscntNblTxtServiceStub.DsBillsOutVO[] dsBillsOut = resBody.getDsBillsOutVO();
				if (dsBillsOut != null) {
					for (int i = 0; i < dsBillsOut.length; i++) {
						stringBuffer.append(printFields(dsBillsOut[i]));
					}
				}
				RetrieveDscntNblTxtServiceStub.DsDscntDtlOutVO[] dsDscntDtlOut = resBody.getDsDscntDtlOutVO();
				if (dsDscntDtlOut != null) {
					for (int i = 0; i < dsDscntDtlOut.length; i++) {
						stringBuffer.append(printFields(dsDscntDtlOut[i]));
					}
				}
			}

			traceResponse(stringBuffer.toString());
		}

		return resBody;
		// RetrieveDscntNblTxtServiceStub.DsDscntAcltnAmtOutVO[]
		// dsDscntAcltnAmtOut =
		// resBody
		// .getDsDscntAcltnAmtOutVO();
		// if (dsDscntAcltnAmtOut != null) {
		// for (int i = 0; i < dsDscntAcltnAmtOut.length; i++) {
		// // 할인구분
		// dsDscntAcltnAmtOut[i].getDscntDv();
		// // 상품번호
		// dsDscntAcltnAmtOut[i].getProdNo();
		// // 할인개월
		// dsDscntAcltnAmtOut[i].getDscntMonth();
		// // 누적할인액
		// dsDscntAcltnAmtOut[i].getAcltnDscntAmt();
		// // 잔여개월수
		// dsDscntAcltnAmtOut[i].getRmndMnthCnt();
		// // 총 할인 예상액
		// dsDscntAcltnAmtOut[i].getRmndDscntExptAmt();
		// // 총약정할인개월수
		// dsDscntAcltnAmtOut[i].getTotDscntExptAmt();
		// // 총건수
		// dsDscntAcltnAmtOut[i].getTotCnt();
		// }
		// }
		//
		// RetrieveDscntNblTxtServiceStub.DsBillMnthOutVO[] dsBillMnthOut =
		// resBody.getDsBillMnthOutVO();
		// if (dsBillMnthOut != null) {
		// for (int i = 0; i < dsBillMnthOut.length; i++) {
		// // 청구월
		// dsBillMnthOut[i].getBillYymm();
		// // 영수금액
		// dsBillMnthOut[i].getAmt();
		// // 총영수금액
		// dsBillMnthOut[i].getTotAmt();
		// // 납부 방법
		// dsBillMnthOut[i].getPymMthdNm();
		// }
		// }
		//
		// RetrieveDscntNblTxtServiceStub.DsPymRshtOutVO[] dsPymRshtOut =
		// resBody.getDsPymRshtOutVO();
		// if (dsPymRshtOut != null) {
		// for (int i = 0; i < dsPymRshtOut.length; i++) {
		// // 청구대상년월
		// dsPymRshtOut[i].getBillTrgtYymm();
		// // 고객명
		// dsPymRshtOut[i].getCustNm();
		// // 고객번호
		// dsPymRshtOut[i].getCustNo();
		// // 금융기관
		// dsPymRshtOut[i].getFninsNm();
		// // 예금주명
		// dsPymRshtOut[i].getDepoNm();
		// // 계좌번호
		// dsPymRshtOut[i].getAcctNo();
		// // 카드번호
		// dsPymRshtOut[i].getCardNo();
		// // 카드소유주명
		// dsPymRshtOut[i].getCardOwnrnm();
		// // 이체일
		// dsPymRshtOut[i].getUseStatDt();
		// // 공급자 등록번호
		// dsPymRshtOut[i].getSplrRegno();
		// }
		// }
		//
		// RetrieveDscntNblTxtServiceStub.DsFmldExamtOutVO[] dsFmldExamtOut =
		// resBody.getDsFmldExamtOutVO();
		// if (dsFmldExamtOut != null) {
		// for (int i = 0; i < dsFmldExamtOut.length; i++) {
		// // 상품번호
		// dsFmldExamtOut[i].getProdNo();
		// // 1차 월사용금액
		// dsFmldExamtOut[i].getFordmUseAmt();
		// // 2차 월사용금액
		// dsFmldExamtOut[i].getOdm2UseAmt();
		// // 3차 월사용금액
		// dsFmldExamtOut[i].getOdm3UseAmt();
		// // 4차 월사용금액
		// dsFmldExamtOut[i].getOrd4UseAmt();
		// // 5차 월사용금액
		// dsFmldExamtOut[i].getOdm5UseAmt();
		// // 1차 청구년월
		// dsFmldExamtOut[i].getBillYymm1();
		// // 2차 청구년월
		// dsFmldExamtOut[i].getBillYymm2();
		// // 3차 청구년월
		// dsFmldExamtOut[i].getBillYymm3();
		// // 4차 청구년월
		// dsFmldExamtOut[i].getBillYymm4();
		// // 5차 청구년월
		// dsFmldExamtOut[i].getBillYymm5();
		// // 총건수
		// dsFmldExamtOut[i].getTotCnt();
		// // 할인예상금액
		// dsFmldExamtOut[i].getDscntExptAmt();
		// }
		// }
		//
		// RetrieveDscntNblTxtServiceStub.DsOilingDsCntDtlOutVO[]
		// dsOilingDsCntDtlOut =
		// resBody
		// .getDsOilingDsCntDtlOutVO();
		// if (dsOilingDsCntDtlOut != null) {
		// for (int i = 0; i < dsOilingDsCntDtlOut.length; i++) {
		// // 상품번호
		// dsOilingDsCntDtlOut[i].getProdNo();
		// // 혜택월
		// dsOilingDsCntDtlOut[i].getBnftMnth();
		// // 주유한도
		// dsOilingDsCntDtlOut[i].getOilingLmt();
		// // 리터당할인액
		// dsOilingDsCntDtlOut[i].getLitUnitDscntAmt();
		// // 사용리터
		// dsOilingDsCntDtlOut[i].getUseLiter();
		// // 총할인액
		// dsOilingDsCntDtlOut[i].getTotDscntAmt();
		// // 총건수
		// dsOilingDsCntDtlOut[i].getTotCnt();
		// // 이월리터
		// dsOilingDsCntDtlOut[i].getXferLiter();
		// }
		// }
		//
		// RetrieveDscntNblTxtServiceStub.DsLzoneOutVO[] dsLzoneOut =
		// resBody.getDsLzoneOutVO();
		// if (dsLzoneOut != null) {
		// for (int i = 0; i < dsLzoneOut.length; i++) {
		// // 적용여부
		// dsLzoneOut[i].getLzoneAplyYn();
		// // 사용시간
		// dsLzoneOut[i].getUseTm();
		// // 총건수
		// dsLzoneOut[i].getTotCnt();
		// // 사용요금
		// dsLzoneOut[i].getUseChrg();
		// }
		// }
		//
		// RetrieveDscntNblTxtServiceStub.DsDscntPnltDtlOutVO[]
		// dsDscntPnltDtlOut =
		// resBody
		// .getDsDscntPnltDtlOutVO();
		// if (dsDscntPnltDtlOut != null) {
		// for (int i = 0; i < dsDscntPnltDtlOut.length; i++) {
		// // 가입계약번호
		// dsDscntPnltDtlOut[i].getAceno();
		// // 가입번호
		// dsDscntPnltDtlOut[i].getEntrNo();
		// // 서비스코드
		// dsDscntPnltDtlOut[i].getSvcCd();
		// // 서비스명
		// dsDscntPnltDtlOut[i].getSvcNm();
		// // 상품코드
		// dsDscntPnltDtlOut[i].getProdCd();
		// // 상품명(요금제)
		// dsDscntPnltDtlOut[i].getProdNm();
		// // 항목코드
		// dsDscntPnltDtlOut[i].getBlitemCd();
		// // 항목명
		// dsDscntPnltDtlOut[i].getBlitemNm();
		// // 상품번호
		// dsDscntPnltDtlOut[i].getProdNo();
		// // 가입약정기간
		// dsDscntPnltDtlOut[i].getEntrAgmtTerm();
		// // 이용일수
		// dsDscntPnltDtlOut[i].getTadvDds();
		// // 총할인받은금액(A)
		// dsDscntPnltDtlOut[i].getTotDscntAmt();
		// // 실사용할인금액(B)
		// dsDscntPnltDtlOut[i].getRlusDscntAmt();
		// // 출력레벨수
		// dsDscntPnltDtlOut[i].getPrntLvlCont();
		// // 리프노드여부
		// dsDscntPnltDtlOut[i].getLeafNodeYn();
		// // 총건수
		// dsDscntPnltDtlOut[i].getTotCnt();
		// // 할인반환금액(A-B)
		// dsDscntPnltDtlOut[i].getDscntReturnAmt();
		// }
		// }
		//
		// RetrieveDscntNblTxtServiceStub.DsBillsOutVO[] dsBillsOut =
		// resBody.getDsBillsOutVO();
		// if (dsBillsOut != null) {
		// for (int i = 0; i < dsBillsOut.length; i++) {
		// // 청구대상년월
		// dsBillsOut[i].getBillTrgtYymm();
		// // 고객명
		// dsBillsOut[i].getCustNm();
		// // 고객번호
		// dsBillsOut[i].getCustNo();
		// // 이용시작일
		// dsBillsOut[i].getUseStatDt();
		// // 이용종료일
		// dsBillsOut[i].getUseEndDt();
		// // 납기일
		// dsBillsOut[i].getDueDt();
		// // 출금일
		// dsBillsOut[i].getWdrwDt();
		// // 금융기관
		// dsBillsOut[i].getFninsNm();
		// // 예금주명
		// dsBillsOut[i].getDepoNm();
		// // 계좌번호
		// dsBillsOut[i].getAcctNo();
		// // 카드번호
		// dsBillsOut[i].getCardNo();
		// // 카드소유주명
		// dsBillsOut[i].getCardOwnrnm();
		// // 이번달요금(1)
		// dsBillsOut[i].getBillAmt();
		// // 미납요금(2)
		// dsBillsOut[i].getUpaidChrg();
		// // 총건수
		// dsBillsOut[i].getTotCnt();
		// // 이번달 납부금액(1+2)
		// dsBillsOut[i].getTotPymScdlAmt();
		// // 작성일자
		// dsBillsOut[i].getBillDt();
		// // 공급받는자사업자번호
		// dsBillsOut[i].getSrcvrBsRegNo();
		// // 공급가액
		// dsBillsOut[i].getSpramt();
		// // 세액
		// dsBillsOut[i].getTxamt();
		// // 공급자 등록번호
		// dsBillsOut[i].getSplrRegno();
		// }
		// }
		//
		// RetrieveDscntNblTxtServiceStub.DsDscntDtlOutVO[] dsDscntDtlOut =
		// resBody.getDsDscntDtlOutVO();
		// if (dsDscntDtlOut != null) {
		// for (int i = 0; i < dsDscntDtlOut.length; i++) {
		// // 상품번호
		// dsDscntDtlOut[i].getProdNo();
		// // 할인내역
		// dsDscntDtlOut[i].getDscntDtl();
		// // 총건수
		// dsDscntDtlOut[i].getTotCnt();
		// // 할인금액
		// dsDscntDtlOut[i].getDscntAmt();
		// }
		// }
	}

	/**
	 * @throws :
	 * @Part :
	 * @Author :
	 * @Component :
	 * @Primitive :
	 * @type :
	 * @Description : [BIL454] 청구내역_납부내역_정보조회
	 */
	private RetrieveBlPaymentInfoEsbServiceStub.ResponseBody retrieveBlPaymentInfoEsb(
			RetrieveBlPaymentInfoEsbServiceStub.DeReqInVO deReqIn) throws ESBException {
		// billAcntNo 청구계정번호 VARCHAR2 12 N 단수
		// billTrgtYymm 청구대상년월 VARCHAR2 6 N 단수
		// nextOperatorId 처리자ID VARCHAR2 15 N 단수 H

		deReqIn.setNextOperatorId(createNextOperatorId());

		// RetrieveBlPaymentInfoEsbServiceStub.DeReqInVO deReqIn = new
		// RetrieveBlPaymentInfoEsbServiceStub.DeReqInVO();
		// deReqIn.setBillAcntNo(billAcntNo);
		// deReqIn.setBillTrgtYymm(billTrgtYymm);
		// deReqIn.setNextOperatorId(nextOperatorId);

		//
		RetrieveBlPaymentInfoEsbServiceStub.RequestBody reqBody = new RetrieveBlPaymentInfoEsbServiceStub.RequestBody();
		reqBody.setDeReqInVO(deReqIn);

		//
		RetrieveBlPaymentInfoEsbServiceStub.ESBHeader reqHeader = new RetrieveBlPaymentInfoEsbServiceStub.ESBHeader();
		reqHeader.setServiceID("BIL454");
		reqHeader.setTransactionID(createTxId());
		reqHeader.setSystemID(SYSTEM_ID);
		reqHeader.setErrCode("");
		reqHeader.setErrMsg("");
		reqHeader.setReserved("");

		//
		RetrieveBlPaymentInfoEsbServiceStub.RequestRecord reqRecord = new RetrieveBlPaymentInfoEsbServiceStub.RequestRecord();
		reqRecord.setRequestBody(reqBody);
		reqRecord.setESBHeader(reqHeader);

		//
		RetrieveBlPaymentInfoEsbServiceStub.RetrieveBlPaymentInfoEsb reqIn = new RetrieveBlPaymentInfoEsbServiceStub.RetrieveBlPaymentInfoEsb();
		reqIn.setRequestRecord(reqRecord);

		//
		String host = URL;
		String path = "/CSSI/BIL/RetrieveBlPaymentInfoEsb";
		String targetEndpoint = host + path;

		RetrieveBlPaymentInfoEsbServiceStub stub = null;
		RetrieveBlPaymentInfoEsbServiceStub.RetrieveBlPaymentInfoEsbResponse res = null;

		try {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(printFields(deReqIn));

				traceRequest(stringBuffer.toString());
			}
			stub = new RetrieveBlPaymentInfoEsbServiceStub(targetEndpoint);
			res = stub.retrieveBlPaymentInfoEsb(reqIn);
		} catch (RemoteException e) {
			e.printStackTrace();
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(e.getMessage());

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(reqHeader.getErrCode(), reqHeader.getErrMsg());
		}

		RetrieveBlPaymentInfoEsbServiceStub.ResponseRecord resRecord = res.getResponseRecord();
		RetrieveBlPaymentInfoEsbServiceStub.ESBHeader resHeader = resRecord.getESBHeader();
		if (!(resHeader.getErrCode() == null || "".equals(resHeader.getErrCode()))) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(resHeader.getErrCode(), resHeader.getErrMsg());
		}

		RetrieveBlPaymentInfoEsbServiceStub.BusinessHeader bizHeader = resRecord.getBusinessHeader();
		if (bizHeader.getResultCode() == null || !"N0000".equals(bizHeader.getResultCode())) {
			// 현재 무조건 에러난다.
			// {
			// StringBuffer stringBuffer = new StringBuffer();
			// stringBuffer.append(String.format("[%32s] %s\n",
			// "targetEndpoint",
			// targetEndpoint));
			// stringBuffer.append(printFields(resHeader));
			// stringBuffer.append(printFields(bizHeader));
			//
			// traceWriter.trace(CCSSUtil.getCtn(), TraceConst.NODE_ID_ESB,
			// TraceConst.NODE_ID_WAS,
			// TraceConst.PROTOCOL_SOAP, stringBuffer.toString());
			// }
			// throw new ESBException(bizHeader.getResultCode(),
			// bizHeader.getResultMessage());
			return null;
		}

		RetrieveBlPaymentInfoEsbServiceStub.ResponseBody resBody = resRecord.getResponseBody();

		{
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
			stringBuffer.append(printFields(resHeader));
			stringBuffer.append(printFields(bizHeader));
			if (resBody != null) {
				RetrieveBlPaymentInfoEsbServiceStub.DsGiroVtAcctOutVO[] dsGiroVtAcctOut = resBody
						.getDsGiroVtAcctOutVO();
				if (dsGiroVtAcctOut != null) {
					for (int i = 0; i < dsGiroVtAcctOut.length; i++) {
						stringBuffer.append(printFields(dsGiroVtAcctOut));
					}
				}
				RetrieveBlPaymentInfoEsbServiceStub.DsLastPaymentRcptOutVO[] dsLastPaymentRcptOut = resBody
						.getDsLastPaymentRcptOutVO();
				if (dsLastPaymentRcptOut != null) {
					for (int i = 0; i < dsLastPaymentRcptOut.length; i++) {
						stringBuffer.append(printFields(dsLastPaymentRcptOut));
					}
				}
				RetrieveBlPaymentInfoEsbServiceStub.DsPaymentInfoOutVO[] dsPaymentInfoOut = resBody
						.getDsPaymentInfoOutVO();
				if (dsPaymentInfoOut != null) {
					for (int i = 0; i < dsPaymentInfoOut.length; i++) {
						stringBuffer.append(printFields(dsPaymentInfoOut));
					}
				}
			}

			traceResponse(stringBuffer.toString());
		}

		return resBody;
		// RetrieveBlPaymentInfoEsbServiceStub.DsGiroVtAcctOutVO[]
		// dsGiroVtAcctOut =
		// resBody
		// .getDsGiroVtAcctOutVO();
		// if (dsGiroVtAcctOut != null) {
		// for (int i = 0; i < dsGiroVtAcctOut.length; i++) {
		// // 청구계정번호(전자납부번호)
		// dsGiroVtAcctOut[i].getBillAcntNo();
		// // 청구년월
		// dsGiroVtAcctOut[i].getBlngThmn();
		// // 청구서발행기준코드
		// dsGiroVtAcctOut[i].getBltxtIsueBaseCd();
		// // 지로번호
		// dsGiroVtAcctOut[i].getGiroNo();
		// // 가상계좌은행명1
		// dsGiroVtAcctOut[i].getVtAcctBankNm1();
		// // 가상계좌번호1
		// dsGiroVtAcctOut[i].getVtAcctNo1();
		// // 가상계좌은행명2
		// dsGiroVtAcctOut[i].getVtAcctBankNm2();
		// // 가상계좌번호2
		// dsGiroVtAcctOut[i].getVtAcctNo2();
		// // 가상계좌은행명3
		// dsGiroVtAcctOut[i].getVtAcctBankNm3();
		// // 가상계좌번호3
		// dsGiroVtAcctOut[i].getVtAcctNo3();
		// // 가상계좌은행명4
		// dsGiroVtAcctOut[i].getVtAcctBankNm4();
		// // 가상계좌번호4
		// dsGiroVtAcctOut[i].getVtAcctNo4();
		// }
		// }
		//
		// RetrieveBlPaymentInfoEsbServiceStub.DsLastPaymentRcptOutVO[]
		// dsLastPaymentRcptOut = resBody
		// .getDsLastPaymentRcptOutVO();
		// if (dsLastPaymentRcptOut != null) {
		// for (int i = 0; i < dsLastPaymentRcptOut.length; i++) {
		// // 고객번호
		// dsLastPaymentRcptOut[i].getBillAcntNo();
		// // 청구년월
		// dsLastPaymentRcptOut[i].getBlngThmn();
		// // 청구서발행기준코드
		// dsLastPaymentRcptOut[i].getBltxtIsueBaseCd();
		// // 고객명
		// dsLastPaymentRcptOut[i].getCustNm();
		// // 서비스번호
		// dsLastPaymentRcptOut[i].getRepProdNo();
		// // 금융기관
		// dsLastPaymentRcptOut[i].getPmthCdcmpBankNm();
		// // 예금(카드)주명
		// dsLastPaymentRcptOut[i].getPmthCardDepoNm();
		// // 계좌/카드번호
		// dsLastPaymentRcptOut[i].getPmthCardNoAcctNo();
		// // 납부일자
		// dsLastPaymentRcptOut[i].getPymDt1();
		// // 영수금액
		// dsLastPaymentRcptOut[i].getPymAmt1();
		// // 납부방법
		// dsLastPaymentRcptOut[i].getPymMthdNm1();
		// }
		// }
		//
		// RetrieveBlPaymentInfoEsbServiceStub.DsPaymentInfoOutVO[]
		// dsPaymentInfoOut =
		// resBody
		// .getDsPaymentInfoOutVO();
		// if (dsPaymentInfoOut != null) {
		// for (int i = 0; i < dsPaymentInfoOut.length; i++) {
		// // 고객번호
		// dsPaymentInfoOut[i].getBillAcntNo();
		// // 청구년월
		// dsPaymentInfoOut[i].getBlngThmn();
		// // 청구서발행기준코드
		// dsPaymentInfoOut[i].getBltxtIsueBaseCd();
		// // 서비스번호
		// dsPaymentInfoOut[i].getMskingPrssBfrRepProdNo();
		// // 이용기간(시작)
		// dsPaymentInfoOut[i].getUseStrtDt();
		// // 이용기간(마지막)
		// dsPaymentInfoOut[i].getUseEndDt();
		// // 납기일
		// dsPaymentInfoOut[i].getDuedDt();
		// // 출금일
		// dsPaymentInfoOut[i].getAddWdrwTrgtCntnt();
		// // 은행명
		// dsPaymentInfoOut[i].getCdcmpBankNm();
		// // 예금(카드)주명
		// dsPaymentInfoOut[i].getCardDepoNm();
		// // 계좌번호
		// dsPaymentInfoOut[i].getCardNoAcctNo();
		// // 납부방법
		// dsPaymentInfoOut[i].getPymMthdNm();
		// }
		// }
	}

	/**
	 * @throws :
	 * @Part :
	 * @Author :
	 * @Component :
	 * @Primitive :
	 * @type :
	 * @Description : [CM043] 청구유형변경
	 */
	private SaveBltxtKdCdSvcServiceStub.ResponseBody saveBltxtKdCdSvc(
			SaveBltxtKdCdSvcServiceStub.DsConfldsInVO dsConfldsIn, SaveBltxtKdCdSvcServiceStub.DsReqInVO dsReqIn)
			throws ESBException {
		// dsConfldsIn
		// userId 사용자ID VARCHAR2 100 Y 단수
		// userNm 사용자명 VARCHAR2 500 Y 단수
		// mrktCd 마켓코드 VARCHAR2 10 Y 단수
		// userOrgCd 사용자기관코드 VARCHAR2 10 Y 단수
		// userDlrCd 사용자대리점코드 VARCHAR2 10 Y 단수
		// userWorkDlrCd 작업대리점코드 VARCHAR2 10 Y 단수
		// userDlrNm 사용자대리점명 VARCHAR2 100 Y 단수
		// lockMode lockMode VARCHAR2 10 N 단수 B - 고정
		// cnId cnId VARCHAR2 10 Y 단수
		// directive directive VARCHAR2 10 Y 단수
		// runDate runDate VARCHAR2 8 Y 단수
		// runDateDtm runDateDtm VARCHAR2 16 Y 단수
		// transactionMode transactionMode VARCHAR2 10 Y 단수
		// userWorkDlrNm 작업대리점명 VARCHAR2 100 Y 단수
		// entrNo 가입번호 VARCHAR2 15 Y 단수
		// entrSysUpdateDate entrSysUpdateDate VARCHAR2 16 Y 단수
		// entrDlUpdateStamp entrDlUpdateStamp VARCHAR2 16 Y 단수
		// aceno 가입계약번호 VARCHAR2 15 Y 단수
		// cntcSysUpdateDate cntcSysUpdateDate VARCHAR2 16 Y 단수
		// cntcDlUpdateStamp cntcDlUpdateStamp VARCHAR2 16 Y 단수
		// billAcntNo 청구계정번호 VARCHAR2 15 N 단수
		// billSysUpdateDate billSysUpdateDate VARCHAR2 16 N 단수
		// billDlUpdateStamp billDlUpdateStamp VARCHAR2 16 N 단수
		// nextOperatorId 처리자ID NUMBER 15 N 단수

		// dsReqIn
		// billAcntNo 청구계정번호 VARCHAR2 9 N 단수
		// bltxtKdCdNm 청구서유형코드명 VARCHAR2 80 N 단수
		// dscntSvcCd 할인서비스코드 VARCHAR2 10 Y 단수
		// dscntSvcNm 할인서비스명 VARCHAR2 240 Y 단수
		// bltxtKdValdStrtDt 청구서유형유효시작일자 VARCHAR2 14 Y 단수
		// bltxtKdValdEndDt 청구서유형유효종료일자 VARCHAR2 14 Y 단수
		// scurMailRcpYn 보안메일수신여부 VARCHAR2 1 Y 단수
		// bltxtRcpProdNo 청구서수신상품번호 VARCHAR2 20 Y 단수
		// billEmailAddr 청구이메일주소 VARCHAR2 50 Y 단수
		// copyReal copyReal VARCHAR2 1 Y 단수
		// prodNo 상품번호 VARCHAR2 20 Y 단수
		// emailCopyCustYn emailCopyCustYn VARCHAR2 1 Y 단수
		// chgBlAddrYn chgBlAddrYn VARCHAR2 1 Y 단수
		// billAddrSeqno 청구주소누적번호 VARCHAR2 15 Y 단수
		// custAddrZip 고객주소우편번호 VARCHAR2 7 Y 단수
		// custVilgAbvAddr 고객동이상주소 VARCHAR2 500 Y 단수
		// custVilgBlwAddr 고객동이하주소 VARCHAR2 500 Y 단수
		// imsi01 imsi01 VARCHAR2 500 Y 단수
		// imsi02 imsi02 VARCHAR2 500 Y 단수
		// imsi03 imsi03 VARCHAR2 500 Y 단수
		// bltxtChrgrId am직배 담당자 ID VARCHAR2 100 Y 단수 청구서 유형이 am 직배(G)인경우 set
		// nextOperatorId 처리자ID NUMBER 15 N 단수

		String nextOperatorId = createNextOperatorId();

		dsConfldsIn.setNextOperatorId(nextOperatorId);
		dsReqIn.setNextOperatorId(nextOperatorId);

		// SaveBltxtKdCdSvcServiceStub.DsConfldsInVO dsConfldsIn = new
		// SaveBltxtKdCdSvcServiceStub.DsConfldsInVO();
		// dsConfldsIn.setUserId(userId_);
		// dsConfldsIn.setUserNm(userNm_);
		// dsConfldsIn.setMrktCd(mrktCd_);
		// dsConfldsIn.setUserOrgCd(userOrgCd_);
		// dsConfldsIn.setUserDlrCd(userDlrCd_);
		// dsConfldsIn.setUserWorkDlrCd(userWorkDlrCd_);
		// dsConfldsIn.setUserDlrNm(userDlrNm_);
		// dsConfldsIn.setLockMode(lockMode_);
		// dsConfldsIn.setCnId(cnId_);
		// dsConfldsIn.setDirective(directive_);
		// dsConfldsIn.setRunDate(runDate_);
		// dsConfldsIn.setRunDateDtm(runDateDtm_);
		// dsConfldsIn.setTransactionMode(transactionMode_);
		// dsConfldsIn.setUserWorkDlrNm(userWorkDlrNm_);
		// dsConfldsIn.setEntrNo(entrNo_);
		// dsConfldsIn.setEntrSysUpdateDate(entrSysUpdateDate_);
		// dsConfldsIn.setEntrDlUpdateStamp(entrDlUpdateStamp_);
		// dsConfldsIn.setAceno(aceno_);
		// dsConfldsIn.setCntcSysUpdateDate(cntcSysUpdateDate_);
		// dsConfldsIn.setCntcDlUpdateStamp(cntcDlUpdateStamp_);
		// dsConfldsIn.setBillAcntNo(billAcntNo_);
		// dsConfldsIn.setBillSysUpdateDate(billSysUpdateDate_);
		// dsConfldsIn.setBillDlUpdateStamp(billDlUpdateStamp_);
		// dsConfldsIn.setNextOperatorId(nextOperatorId_);

		// SaveBltxtKdCdSvcServiceStub.DsReqInVO dsReqIn = new
		// SaveBltxtKdCdSvcServiceStub.DsReqInVO();
		// dsReqIn.setBillAcntNo(billAcntNo__);
		// dsReqIn.setBltxtKdCdNm(bltxtKdCdNm__);
		// dsReqIn.setDscntSvcCd(dscntSvcCd__);
		// dsReqIn.setDscntSvcNm(dscntSvcNm__);
		// dsReqIn.setBltxtKdValdStrtDt(bltxtKdValdStrtDt__);
		// dsReqIn.setBltxtKdValdEndDt(bltxtKdValdEndDt__);
		// dsReqIn.setScurMailRcpYn(scurMailRcpYn__);
		// dsReqIn.setBltxtRcpProdNo(bltxtRcpProdNo__);
		// dsReqIn.setBillEmailAddr(billEmailAddr__);
		// dsReqIn.setCopyReal(copyReal__);
		// dsReqIn.setProdNo(prodNo__);
		// dsReqIn.setEmailCopyCustYn(emailCopyCustYn__);
		// dsReqIn.setChgBlAddrYn(chgBlAddrYn__);
		// dsReqIn.setBillAddrSeqno(billAddrSeqno__);
		// dsReqIn.setCustAddrZip(custAddrZip__);
		// dsReqIn.setCustVilgAbvAddr(custVilgAbvAddr__);
		// dsReqIn.setCustVilgBlwAddr(custVilgBlwAddr__);
		// dsReqIn.setImsi01(imsi01__);
		// dsReqIn.setImsi02(imsi02__);
		// dsReqIn.setImsi03(imsi03__);
		// dsReqIn.setBltxtChrgrId(bltxtChrgrId__);
		// dsReqIn.setNextOperatorId(nextOperatorId__);

		//
		SaveBltxtKdCdSvcServiceStub.RequestBody reqBody = new SaveBltxtKdCdSvcServiceStub.RequestBody();
		reqBody.setDsConfldsInVO(dsConfldsIn);
		reqBody.setDsReqInVO(dsReqIn);

		//
		SaveBltxtKdCdSvcServiceStub.ESBHeader reqHeader = new SaveBltxtKdCdSvcServiceStub.ESBHeader();
		reqHeader.setServiceID("CM043");
		reqHeader.setTransactionID(createTxId());
		reqHeader.setSystemID(SYSTEM_ID);
		reqHeader.setErrCode("");
		reqHeader.setErrMsg("");
		reqHeader.setReserved("");

		//
		SaveBltxtKdCdSvcServiceStub.RequestRecord reqRecord = new SaveBltxtKdCdSvcServiceStub.RequestRecord();
		reqRecord.setRequestBody(reqBody);
		reqRecord.setESBHeader(reqHeader);

		//
		SaveBltxtKdCdSvcServiceStub.SaveBltxtKdCdSvc reqIn = new SaveBltxtKdCdSvcServiceStub.SaveBltxtKdCdSvc();
		reqIn.setRequestRecord(reqRecord);

		//
		String host = URL;
		String path = "/CSSI/CM/SaveBltxtKdCdSvc";
		String targetEndpoint = host + path;

		SaveBltxtKdCdSvcServiceStub stub = null;
		SaveBltxtKdCdSvcServiceStub.SaveBltxtKdCdSvcResponse res = null;

		try {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(printFields(dsConfldsIn));
				stringBuffer.append(printFields(dsReqIn));

				traceRequest(stringBuffer.toString());
			}
			stub = new SaveBltxtKdCdSvcServiceStub(targetEndpoint);
			res = stub.saveBltxtKdCdSvc(reqIn);
		} catch (RemoteException e) {
			e.printStackTrace();
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(e.getMessage());

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(reqHeader.getErrCode(), reqHeader.getErrMsg());
		}

		SaveBltxtKdCdSvcServiceStub.ResponseRecord resRecord = res.getResponseRecord();
		SaveBltxtKdCdSvcServiceStub.ESBHeader resHeader = resRecord.getESBHeader();
		if (!(resHeader.getErrCode() == null || "".equals(resHeader.getErrCode()))) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(resHeader.getErrCode(), resHeader.getErrMsg());
		}

		SaveBltxtKdCdSvcServiceStub.BusinessHeader bizHeader = resRecord.getBusinessHeader();
		if (bizHeader.getResultCode() == null || !"N0000".equals(bizHeader.getResultCode())) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));
				stringBuffer.append(printFields(bizHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(bizHeader.getResultCode(), bizHeader.getResultMessage());
		}

		SaveBltxtKdCdSvcServiceStub.ResponseBody resBody = resRecord.getResponseBody();
		{
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
			stringBuffer.append(printFields(resHeader));
			stringBuffer.append(printFields(bizHeader));
			if (resBody != null) {
				SaveBltxtKdCdSvcServiceStub.DsResOutVO dsResOut = resBody.getDsResOutVO();
				if (dsResOut != null) {
					stringBuffer.append(printFields(dsResOut));
				}
			}

			traceResponse(stringBuffer.toString());
		}

		return resBody;
		// SaveBltxtKdCdSvcServiceStub.DsResOutVO dsResOut =
		// resBody.getDsResOutVO();
		// if (dsResOut != null) {
		// // 메시지코드
		// dsResOut.getMsgCode();
		// // 메시지
		// dsResOut.getMsgText();
		// }
	}

	/**
	 * @throws :
	 * @Part :
	 * @Author :
	 * @Component :
	 * @Primitive :
	 * @type :
	 * @Description : [CM061] 계좌 및 카드 인증 및 이름 인증(SAP)
	 */
	private RetrieveAythOrNameSvcServiceStub.ResponseBody retrieveAythOrNameSvc(
			RetrieveAythOrNameSvcServiceStub.RqstDataInVO rqstDataIn) throws ESBException {
		// acntOwnrNo 소유자번호 VARCHAR2 13 N 단수 주민번호,사업자번호,외국인번호,외국인거소번호등
		// custDvCd 고객구분 VARCHAR2 10 N 단수 "개인:I,법인:G"
		// custKdCd 고객유형 VARCHAR2 2 N 단수 VW_CS_HB_CUST_KD_01 참조
		// persIdind 외국인여부 VARCHAR2 10 Y 단수 외국인:Y
		// pymMthdCd 납부방법 VARCHAR2 10 Y 단수 "자동이체:CM,신용카드:CC,지로:GR"
		// bankCd 은행코드 VARCHAR2 10 Y 단수 자동이체시 필수
		// bankAcntNo 은행계좌번호 VARCHAR2 21 Y 단수 자동이체시 필수
		// cdcmpCd 카드타입 VARCHAR2 10 Y 단수 신용카드시 필수
		// cardNo 카드번호 VARCHAR2 44 Y 단수 신용카드시 필수
		// cardValdEndYymm 카드유효기간 VARCHAR2 10 Y 단수 "신용카드시 필수YYYYMM 형식"
		// acntOwnrNm 소유자명 VARCHAR2 250 N 단수 소유주명
		// mode 소유자명 VARCHAR2 60 N 단수 "N : 인증시 계좌/카드가 납부자 소유인지 체크한다. (환불계좌
		// 인증시사용)
		// P : 인증시 계좌/카드와 납부자 소유를 체크하지 않는다. (납부계정 등록시 사용)"
		// nextOperatorId 처리자ID NUMBER 15 N 단수

		rqstDataIn.setNextOperatorId(createNextOperatorId());

		// RetrieveAythOrNameSvcServiceStub.RqstDataInVO rqstDataIn = new
		// RetrieveAythOrNameSvcServiceStub.RqstDataInVO();
		// rqstDataIn.setAcntOwnrNm(acntOwnrNo);
		// rqstDataIn.setCustDvCd(custDvCd);
		// rqstDataIn.setCustKdCd(custKdCd);
		// rqstDataIn.setPersIdind(persIdind);
		// rqstDataIn.setPymMthdCd(pymMthdCd);
		// rqstDataIn.setBankCd(bankCd);
		// rqstDataIn.setBankAcntNo(bankAcntNo);
		// rqstDataIn.setCdcmpCd(cdcmpCd);
		// rqstDataIn.setCardNo(cardNo);
		// rqstDataIn.setCardValdEndYymm(cardValdEndYymm);
		// rqstDataIn.setAcntOwnrNm(acntOwnrNm);
		// rqstDataIn.setMode(mode);
		// rqstDataIn.setNextOperatorId(nextOperatorId);

		//
		RetrieveAythOrNameSvcServiceStub.RequestBody reqBody = new RetrieveAythOrNameSvcServiceStub.RequestBody();
		reqBody.setRqstDataInVO(rqstDataIn);

		//
		RetrieveAythOrNameSvcServiceStub.ESBHeader reqHeader = new RetrieveAythOrNameSvcServiceStub.ESBHeader();
		reqHeader.setServiceID("CM061");
		reqHeader.setTransactionID(createTxId());
		reqHeader.setSystemID(SYSTEM_ID);
		reqHeader.setErrCode("");
		reqHeader.setErrMsg("");
		reqHeader.setReserved("");

		//
		RetrieveAythOrNameSvcServiceStub.RequestRecord reqRecord = new RetrieveAythOrNameSvcServiceStub.RequestRecord();
		reqRecord.setRequestBody(reqBody);
		reqRecord.setESBHeader(reqHeader);

		//
		RetrieveAythOrNameSvcServiceStub.RetrieveAythOrNameSvc reqIn = new RetrieveAythOrNameSvcServiceStub.RetrieveAythOrNameSvc();
		reqIn.setRequestRecord(reqRecord);

		//
		String host = URL;
		String path = "/CSSI/SB/RetrieveAythOrNameSvc";
		String targetEndpoint = host + path;

		RetrieveAythOrNameSvcServiceStub stub = null;
		RetrieveAythOrNameSvcServiceStub.RetrieveAythOrNameSvcResponse res = null;

		try {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(printFields(rqstDataIn));

				traceRequest(stringBuffer.toString());
			}

			stub = new RetrieveAythOrNameSvcServiceStub(targetEndpoint);
			res = stub.retrieveAythOrNameSvc(reqIn);
		} catch (RemoteException e) {
			e.printStackTrace();
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(e.getMessage());

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(reqHeader.getErrCode(), reqHeader.getErrMsg());
		}

		RetrieveAythOrNameSvcServiceStub.ResponseRecord resRecord = res.getResponseRecord();
		RetrieveAythOrNameSvcServiceStub.ESBHeader resHeader = resRecord.getESBHeader();
		if (!(resHeader.getErrCode() == null || "".equals(resHeader.getErrCode()))) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(resHeader.getErrCode(), resHeader.getErrMsg());
		}

		RetrieveAythOrNameSvcServiceStub.BusinessHeader bizHeader = resRecord.getBusinessHeader();
		if (bizHeader.getResultCode() == null || !"N0000".equals(bizHeader.getResultCode())) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));
				stringBuffer.append(printFields(bizHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(bizHeader.getResultCode(), bizHeader.getResultMessage());
		}

		RetrieveAythOrNameSvcServiceStub.ResponseBody resBody = resRecord.getResponseBody();
		{
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
			stringBuffer.append(printFields(resHeader));
			stringBuffer.append(printFields(bizHeader));
			if (resBody != null) {
				RetrieveAythOrNameSvcServiceStub.ResultInfoOutVO resultInfoOut = resBody.getResultInfoOutVO();
				if (resultInfoOut != null) {
					stringBuffer.append(printFields(resultInfoOut));
				}
			}

			traceResponse(stringBuffer.toString());
		}

		return resBody;
		// RetrieveAythOrNameSvcServiceStub.ResultInfoOutVO resultInfoOut =
		// resBody.getResultInfoOutVO();
		// if (resultInfoOut != null) {
		// // 결과코드
		// resultInfoOut.getMsgCode();
		// // 결과메시지
		// resultInfoOut.getMsgText();
		// // cms결과코드
		// resultInfoOut.getCmsResultCode();
		// }
	}

	/**
	 * @throws :
	 * @Part :
	 * @Author :
	 * @Component :
	 * @Primitive :
	 * @type :
	 * @Description : [CM078] 납부방법변경
	 */
	private SavePymAcntSvcServiceStub.ResponseBody savePymAcntSvc(SavePymAcntSvcServiceStub.DsReqInVO dsReqIn)
			throws ESBException {
		// aceno 가입계약번호 VARCHAR2 12 Y 단수
		// acntOwnrNo 소유자번호 VARCHAR2 13 N 단수 주민번호,사업자번호,외국인번호,외국인거소번호등
		// actInd actInd VARCHAR2 1 Y 단수 null(후순위만 Y이나 후순위 삭제함)
		// bankAcntNo 은행계좌번호 VARCHAR2 16 Y 단수 자동이체인 경우 필수
		// bankCd 은행코드 VARCHAR2 3 Y 단수 자동이체인 경우 필수
		// bankNm 은행명 VARCHAR2 60 Y 단수
		// billAcntNo 청구계정번호 VARCHAR2 12 N 단수 납부계정을 변경하려는 청구계정번호
		// bltxtKdCd 청구유형 VARCHAR2 1 N 단수 고객유형이 GG(LG그룹사_관계사)가 아닌 경우 청구서유형이
		// Shot-mail(S)/SMS(M)/모바일(W) 면 지로로 변경 불가
		// cardNo 카드번호 VARCHAR2 16 Y 단수 신용카드인 경우 필수
		// cardValdEndYymm 유효일자 VARCHAR2 6 Y 단수 "YYYYMM
		// 신용카드인 경우 필수"
		// cdcmpCd 카드사코드 VARCHAR2 2 Y 단수 신용카드인 경우 필수
		// cdcmpNm 카드사 VARCHAR2 360 Y 단수
		// custDvCd 납부자고객구분 VARCHAR2 240 N 단수 개인:I,법인:G
		// custKdCd 납부자고객유형 VARCHAR2 240 N 단수 VW_CS_HB_CUST_KD_01 참조
		// entrNo 가입번호 VARCHAR2 12 N 단수 가족사랑,해피투게더의 경우 대표가입자만이 납부계정을 변경할 수 있어
		// 필수항목임
		// newInd newInd VARCHAR2 1 Y 단수 null(향후 사용위해 남겨둔 필드)
		// persIdind 외국인 여부 VARCHAR2 1 Y 단수 외국인:Y
		// ppayAcntYn 선불계정 구분 VARCHAR2 1 Y 단수 선불:Y
		// pymAcntNo 납부계정번호 VARCHAR2 12 Y 단수 없으면 NULL
		// pymCustNm 납부자 고객 명 VARCHAR2 360 N 단수 외국인 영어명 가능
		// pymManCustNo 납부자 고객 번호 VARCHAR2 15 Y 단수 사용안함
		// pymMthdCd 납부방법 VARCHAR2 2 N 단수 "자동이체:CM,신용카드:CC,지로:GR
		// 납부정보변경(CM->CM,CC->CC):즉시변경
		// 납부방법변경(그외):예약변경"
		// pymMthdNm 납부방법명 VARCHAR2 30 Y 단수 자동이체,신용카드,지로
		// nextOperatorId 처리자ID NUMBER 15 N 단수

		dsReqIn.setNextOperatorId(createNextOperatorId());

		// SavePymAcntSvcServiceStub.DsReqInVO dsReqIn = new
		// SavePymAcntSvcServiceStub.DsReqInVO();
		// dsReqIn.setAceno(aceno);
		// dsReqIn.setAcntOwnrNo(acntOwnrNo);
		// dsReqIn.setActInd(actInd);
		// dsReqIn.setBankAcntNo(bankAcntNo);
		// dsReqIn.setBankCd(bankCd);
		// dsReqIn.setBankNm(bankNm);
		// dsReqIn.setBillAcntNo(billAcntNo);
		// dsReqIn.setBltxtKdCd(bltxtKdCd);
		// dsReqIn.setCardNo(cardNo);
		// dsReqIn.setCardValdEndYymm(cardValdEndYymm);
		// dsReqIn.setCdcmpCd(cdcmpCd);
		// dsReqIn.setCdcmpNm(cdcmpNm);
		// dsReqIn.setCustDvCd(custDvCd);
		// dsReqIn.setCustKdCd(custKdCd);
		// dsReqIn.setEntrNo(entrNo);
		// dsReqIn.setNewInd(newInd);
		// dsReqIn.setPersIdind(persIdind);
		// dsReqIn.setPpayAcntYn(ppayAcntYn);
		// dsReqIn.setPymAcntNo(pymAcntNo);
		// dsReqIn.setPymCustNm(pymCustNm);
		// dsReqIn.setPymManCustNo(pymManCustNo);
		// dsReqIn.setPymMthdCd(pymMthdCd);
		// dsReqIn.setPymMthdNm(pymMthdNm);
		// dsReqIn.setNextOperatorId(nextOperatorId);

		//
		SavePymAcntSvcServiceStub.RequestBody reqBody = new SavePymAcntSvcServiceStub.RequestBody();
		reqBody.setDsReqInVO(dsReqIn);

		//
		SavePymAcntSvcServiceStub.ESBHeader reqHeader = new SavePymAcntSvcServiceStub.ESBHeader();
		reqHeader.setServiceID("CM078");
		reqHeader.setTransactionID(createTxId());
		reqHeader.setSystemID(SYSTEM_ID);
		reqHeader.setErrCode("");
		reqHeader.setErrMsg("");
		reqHeader.setReserved("");

		//
		SavePymAcntSvcServiceStub.RequestRecord reqRecord = new SavePymAcntSvcServiceStub.RequestRecord();
		reqRecord.setRequestBody(reqBody);
		reqRecord.setESBHeader(reqHeader);

		//
		SavePymAcntSvcServiceStub.SavePymAcntSvc reqIn = new SavePymAcntSvcServiceStub.SavePymAcntSvc();
		reqIn.setRequestRecord(reqRecord);

		//
		String host = URL;
		String path = "/CSSI/CM/SavePymAcntSvc";
		String targetEndpoint = host + path;

		SavePymAcntSvcServiceStub stub = null;
		SavePymAcntSvcServiceStub.SavePymAcntSvcResponse res = null;

		try {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(printFields(dsReqIn));

				traceRequest(stringBuffer.toString());
			}
			stub = new SavePymAcntSvcServiceStub(targetEndpoint);
			res = stub.savePymAcntSvc(reqIn);
		} catch (RemoteException e) {
			e.printStackTrace();
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(e.getMessage());

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(reqHeader.getErrCode(), reqHeader.getErrMsg());
		}

		SavePymAcntSvcServiceStub.ResponseRecord resRecord = res.getResponseRecord();
		SavePymAcntSvcServiceStub.ESBHeader resHeader = resRecord.getESBHeader();
		if (!(resHeader.getErrCode() == null || "".equals(resHeader.getErrCode()))) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(resHeader.getErrCode(), resHeader.getErrMsg());
		}

		SavePymAcntSvcServiceStub.BusinessHeader bizHeader = resRecord.getBusinessHeader();
		if (bizHeader.getResultCode() == null || !"N0000".equals(bizHeader.getResultCode())) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));
				stringBuffer.append(printFields(bizHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(resHeader.getErrCode(), resHeader.getErrMsg());
		}

		SavePymAcntSvcServiceStub.ResponseBody resBody = resRecord.getResponseBody();
		{
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
			stringBuffer.append(printFields(resHeader));
			stringBuffer.append(printFields(bizHeader));
			if (resBody != null) {
				SavePymAcntSvcServiceStub.DsResOutVO dsResOut = resBody.getDsResOutVO();
				if (dsResOut != null) {
					stringBuffer.append(printFields(dsResOut));
				}
			}

			traceResponse(stringBuffer.toString());
		}

		return resBody;
		// SavePymAcntSvcServiceStub.DsResOutVO dsResOut =
		// resBody.getDsResOutVO();
		// if (dsResOut != null) {
		// // 결과코드
		// dsResOut.getMsgCode();
		// // 결과메시지
		// dsResOut.getMsgText();
		// }
	}

	/**
	 * @throws :
	 * @Part :
	 * @Author :
	 * @Component :
	 * @Primitive :
	 * @type :
	 * @Description : [MPS121] 모바일APP 무료잔여량조회/월별사용량 조회 항목 전송
	 */
	private RetrieveCallAppSmryServiceStub.ResponseBody retrieveCallAppSmry(
			RetrieveCallAppSmryServiceStub.PutEntrCustInVO putEntrCustIn) throws ESBException {
		// callYm 통화년월 VARCHAR2 20 Y N 단수
		// prodNo 상품번호 NUMBER 12 Y N 단수
		// aceNo 가입계약번호 NUMBER 12 Y N 단수
		// entrNo 가입번호 NUMBER 30 Y N 단수 default 0
		// billAcctNo 청구계정번호 NUMBER 15 Y N 단수 default 0
		// wrkTypCd 업무구분코드 VARCHAR2 1 N 단수 1. 무료잔여량, 2. 월별사용량
		// nextOperatorId 처리자ID NUMBER 15 N 단수

		putEntrCustIn.setNextOperatorId(createNextOperatorId());

		// RetrieveCallAppSmryServiceStub.PutEntrCustInVO putEntrCustIn = new
		// RetrieveCallAppSmryServiceStub.PutEntrCustInVO();
		// putEntrCustIn.setCallYm(callYm);
		// putEntrCustIn.setProdNo(prodNo);
		// putEntrCustIn.setAceNo(aceNo);
		// putEntrCustIn.setEntrNo(entrNo);
		// putEntrCustIn.setBillAcctNo(billAcctNo);
		// putEntrCustIn.setWrkTypCd(wrkTypCd);
		// putEntrCustIn.setNextOperatorId(nextOperatorId);

		//
		RetrieveCallAppSmryServiceStub.RequestBody reqBody = new RetrieveCallAppSmryServiceStub.RequestBody();
		reqBody.setPutEntrCustInVO(putEntrCustIn);

		//
		RetrieveCallAppSmryServiceStub.ESBHeader reqHeader = new RetrieveCallAppSmryServiceStub.ESBHeader();
		reqHeader.setServiceID("MPS121");
		reqHeader.setTransactionID(createTxId());
		reqHeader.setSystemID(SYSTEM_ID);
		reqHeader.setErrCode("");
		reqHeader.setErrMsg("");
		reqHeader.setReserved("");

		//
		RetrieveCallAppSmryServiceStub.RequestRecord reqRecord = new RetrieveCallAppSmryServiceStub.RequestRecord();
		reqRecord.setRequestBody(reqBody);
		reqRecord.setESBHeader(reqHeader);

		//
		RetrieveCallAppSmryServiceStub.RetrieveCallAppSmry reqIn = new RetrieveCallAppSmryServiceStub.RetrieveCallAppSmry();
		reqIn.setRequestRecord(reqRecord);

		//
		String host = URL;
		String path = "/CSSI/MPS/RetrieveCallAppSmry";
		String targetEndpoint = host + path;

		RetrieveCallAppSmryServiceStub stub = null;
		RetrieveCallAppSmryServiceStub.RetrieveCallAppSmryResponse res = null;

		try {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(printFields(putEntrCustIn));

				traceRequest(stringBuffer.toString());
			}
			stub = new RetrieveCallAppSmryServiceStub(targetEndpoint);
			res = stub.retrieveCallAppSmry(reqIn);
		} catch (RemoteException e) {
			e.printStackTrace();
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(e.getMessage());

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(reqHeader.getErrCode(), reqHeader.getErrMsg());
		}

		RetrieveCallAppSmryServiceStub.ResponseRecord resRecord = res.getResponseRecord();
		RetrieveCallAppSmryServiceStub.ESBHeader resHeader = resRecord.getESBHeader();
		if (!(resHeader.getErrCode() == null || "".equals(resHeader.getErrCode()))) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(resHeader.getErrCode(), resHeader.getErrMsg());
		}

		RetrieveCallAppSmryServiceStub.BusinessHeader bizHeader = resRecord.getBusinessHeader();
		if (bizHeader.getResultCode() == null || !"N0000".equals(bizHeader.getResultCode())) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));
				stringBuffer.append(printFields(bizHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(bizHeader.getResultCode(), bizHeader.getResultMessage());
		}

		RetrieveCallAppSmryServiceStub.ResponseBody resBody = resRecord.getResponseBody();
		{
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
			stringBuffer.append(printFields(resHeader));
			stringBuffer.append(printFields(bizHeader));
			if (resBody != null) {
				RetrieveCallAppSmryServiceStub.DsGetEntrSvcSmryOutVO[] dsGetEntrSvcSmryOut = resBody
						.getDsGetEntrSvcSmryOutVO();
				if (dsGetEntrSvcSmryOut != null) {
					for (int i = 0; i < dsGetEntrSvcSmryOut.length; i++) {
						stringBuffer.append(printFields(dsGetEntrSvcSmryOut[i]));
					}
				}
				RetrieveCallAppSmryServiceStub.DsGetEntrMonthAlowsmryOutVO[] dsGetEntrMonthAlowsmryOut = resBody
						.getDsGetEntrMonthAlowsmryOutVO();
				if (dsGetEntrMonthAlowsmryOut != null) {
					for (int i = 0; i < dsGetEntrMonthAlowsmryOut.length; i++) {
						stringBuffer.append(printFields(dsGetEntrMonthAlowsmryOut[i]));
					}
				}
			}

			traceResponse(stringBuffer.toString());
		}

		return resBody;
		// RetrieveCallAppSmryServiceStub.DsGetEntrSvcSmryOutVO[]
		// dsGetEntrSvcSmryOut =
		// resBody
		// .getDsGetEntrSvcSmryOutVO();
		// if (dsGetEntrSvcSmryOut != null) {
		// for (int i = 0; i < dsGetEntrSvcSmryOut.length; i++) {
		// // 서비스코드
		// dsGetEntrSvcSmryOut[i].getSvcCd();
		// // 서비스명
		// dsGetEntrSvcSmryOut[i].getSvcNm();
		// // 서비스유형코드
		// dsGetEntrSvcSmryOut[i].getSvcTypCd();
		// // 서비스사용단위코드
		// dsGetEntrSvcSmryOut[i].getSvcUnitCd();
		// // 공제범위코드
		// dsGetEntrSvcSmryOut[i].getAlloRngCd();
		// // 공제범위값FROM
		// dsGetEntrSvcSmryOut[i].getAlloFrVal();
		// // 공제범위값TO
		// dsGetEntrSvcSmryOut[i].getAlloToVal();
		// // 제공량
		// dsGetEntrSvcSmryOut[i].getAlloValue();
		// // 사용량
		// dsGetEntrSvcSmryOut[i].getUseValue();
		// // 상품유형코드
		// dsGetEntrSvcSmryOut[i].getProdTypeCd();
		// // 고객채널코드
		// dsGetEntrSvcSmryOut[i].getCustChnlCd();
		// // 최종사용일시
		// dsGetEntrSvcSmryOut[i].getLastUseDttm();
		// }
		// }
		//
		// RetrieveCallAppSmryServiceStub.DsGetEntrMonthAlowsmryOutVO[]
		// dsGetEntrMonthAlowsmryOut = resBody
		// .getDsGetEntrMonthAlowsmryOutVO();
		// if (dsGetEntrMonthAlowsmryOut != null) {
		// for (int i = 0; i < dsGetEntrMonthAlowsmryOut.length; i++) {
		// // 서비스유형코드
		// dsGetEntrMonthAlowsmryOut[i].getSvcTypCd();
		// // 서비스사용단위코드
		// dsGetEntrMonthAlowsmryOut[i].getSvcUnitCd();
		// // 총사용량
		// dsGetEntrMonthAlowsmryOut[i].getAlloValue();
		// // 부과금액
		// dsGetEntrMonthAlowsmryOut[i].getUseValue();
		// // 최종사용일시
		// dsGetEntrMonthAlowsmryOut[i].getLastUseDttm();
		// }
		// }
	}

	/**
	 * @throws :
	 * @Part :
	 * @Author :
	 * @Component :
	 * @Primitive :
	 * @type :
	 * @Description:[SB068] 일시정지 이력조회
	 */
	private RetrieveSuspendHistoryHomepgServiceStub.ResponseBody retrieveSuspendHistoryHomepg(
			RetrieveSuspendHistoryHomepgServiceStub.DataInVO dataIn) throws Exception {
		// entrNo 가입번호 VARCHAR2 12 N 단수
		// nextOperatorId 처리자ID VARCHAR2 15 N 단수

		dataIn.setNextOperatorId(createNextOperatorId());

		// RetrieveSuspendHistoryHomepgServiceStub.DataInVO dataIn = new
		// RetrieveSuspendHistoryHomepgServiceStub.DataInVO();
		// dataIn.setEntrNo(entrNo);
		// dataIn.setNextOperatorId(nextOperatorId);

		//
		RetrieveSuspendHistoryHomepgServiceStub.RequestBody reqBody = new RetrieveSuspendHistoryHomepgServiceStub.RequestBody();
		reqBody.setDataInVO(dataIn);

		//
		RetrieveSuspendHistoryHomepgServiceStub.ESBHeader reqHeader = new RetrieveSuspendHistoryHomepgServiceStub.ESBHeader();
		reqHeader.setServiceID("SB068");
		reqHeader.setTransactionID(createTxId());
		reqHeader.setSystemID(SYSTEM_ID);
		reqHeader.setErrCode("");
		reqHeader.setErrMsg("");
		reqHeader.setReserved("");

		//
		RetrieveSuspendHistoryHomepgServiceStub.RequestRecord reqRecord = new RetrieveSuspendHistoryHomepgServiceStub.RequestRecord();
		reqRecord.setRequestBody(reqBody);
		reqRecord.setESBHeader(reqHeader);

		//
		RetrieveSuspendHistoryHomepgServiceStub.RetrieveSuspendHistoryHomepg reqIn = new RetrieveSuspendHistoryHomepgServiceStub.RetrieveSuspendHistoryHomepg();
		reqIn.setRequestRecord(reqRecord);

		//
		String host = URL;
		String path = "/CSSI/SB/RetrieveSuspendHistoryHomepg";
		String targetEndpoint = host + path;

		RetrieveSuspendHistoryHomepgServiceStub stub = null;
		RetrieveSuspendHistoryHomepgServiceStub.RetrieveSuspendHistoryHomepgResponse res = null;

		try {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(printFields(dataIn));

				traceRequest(stringBuffer.toString());
			}

			stub = new RetrieveSuspendHistoryHomepgServiceStub(targetEndpoint);
			res = stub.retrieveSuspendHistoryHomepg(reqIn);
		} catch (RemoteException e) {
			e.printStackTrace();
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(e.getMessage());

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(reqHeader.getErrCode(), reqHeader.getErrMsg());
		}

		RetrieveSuspendHistoryHomepgServiceStub.ResponseRecord resRecord = res.getResponseRecord();
		RetrieveSuspendHistoryHomepgServiceStub.ESBHeader resHeader = resRecord.getESBHeader();
		if (!(resHeader.getErrCode() == null || "".equals(resHeader.getErrCode()))) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(resHeader.getErrCode(), resHeader.getErrMsg());
		}

		RetrieveSuspendHistoryHomepgServiceStub.BusinessHeader bizHeader = resRecord.getBusinessHeader();
		if (bizHeader.getResultCode() == null || !"N0000".equals(bizHeader.getResultCode())) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));
				stringBuffer.append(printFields(bizHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(resHeader.getErrCode(), resHeader.getErrMsg());
		}

		RetrieveSuspendHistoryHomepgServiceStub.ResponseBody resBody = resRecord.getResponseBody();
		{
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
			stringBuffer.append(printFields(resHeader));
			stringBuffer.append(printFields(bizHeader));
			if (resBody != null) {
				RetrieveSuspendHistoryHomepgServiceStub.DsSusHistListOutVO[] dsSusHistListOut = resBody
						.getDsSusHistListOutVO();
				if (dsSusHistListOut != null) {
					for (int i = 0; i < dsSusHistListOut.length; i++) {
						stringBuffer.append(printFields(dsSusHistListOut[i]));
					}
				}
			}

			traceResponse(stringBuffer.toString());
		}

		return resBody;
		// RetrieveSuspendHistoryHomepgServiceStub.DsSusHistListOutVO[]
		// dsSusHistListOut = resBody.getDsSusHistListOutVO();
		// if (dsSusHistListOut != null) {
		// for (int i = 0; i < dsSusHistListOut.length; i++) {
		// // 상품번호
		// dsSusHistListOut[i].getProdNo();
		// // 서비스코드
		// dsSusHistListOut[i].getProdCd();
		// // 가입번호
		// dsSusHistListOut[i].getEntrNo();
		// // 가입계약번호
		// dsSusHistListOut[i].getAceno();
		// // 청구계정번호
		// dsSusHistListOut[i].getBillAcntNo();
		// // 서비스명
		// dsSusHistListOut[i].getProdNm();
		// // 일시정지일자
		// dsSusHistListOut[i].getSusDate();
		// // 정지해제일자 혹은 정지해제예약일자
		// dsSusHistListOut[i].getRspDate();
		// // 일시정지상세사유코드
		// dsSusHistListOut[i].getSusRsnCd();
		// // 일시정지상세사유명
		// dsSusHistListOut[i].getSusRsnNm();
		// // 정지해제상세사유코드
		// dsSusHistListOut[i].getRspRsnCd();
		// // 정지해제상세사유명
		// dsSusHistListOut[i].getRspRsnNm();
		// // 요청구분명
		// dsSusHistListOut[i].getRsnNm();
		// // 일시정지일수
		// dsSusHistListOut[i].getSusDays();
		// // 발신정지여부
		// dsSusHistListOut[i].getSendPhbYn();
		// // 수신정지여부
		// dsSusHistListOut[i].getIcallPhbYn();
		//
		// }
		// }
	}

	/**
	 * @throws :
	 * @Part :
	 * @Author :
	 * @Component :
	 * @Primitive :
	 * @type :
	 * @Description : [SB638] 일시정지내역조회
	 */
	private RetrieveUseSvcListServiceStub.ResponseBody retrieveUseSvcList(
			RetrieveUseSvcListServiceStub.DsInputInVO dsInputIn) throws Exception {
		// sSearchCb 검색구분 VARCHAR2 2 N 단수 구분: 01
		// sSrchValue 검색조건 VARCHAR2 12 N 단수 가입번호
		// nextOperatorId 처리자ID VARCHAR2 15 N 단수

		dsInputIn.setNextOperatorId(createNextOperatorId());

		// RetrieveUseSvcListServiceStub.DsInputInVO dsInputIn = new
		// RetrieveUseSvcListServiceStub.DsInputInVO();
		// dsInputIn.setSSearchCb(sSearchCb);
		// dsInputIn.setSSrchValue(sSrchValue);
		// dsInputIn.setNextOperatorId(nextOperatorId);

		//
		RetrieveUseSvcListServiceStub.RequestBody reqBody = new RetrieveUseSvcListServiceStub.RequestBody();
		reqBody.setDsInputInVO(dsInputIn);

		//
		RetrieveUseSvcListServiceStub.ESBHeader reqHeader = new RetrieveUseSvcListServiceStub.ESBHeader();
		reqHeader.setServiceID("SB638");
		reqHeader.setTransactionID(createTxId());
		reqHeader.setSystemID(SYSTEM_ID);
		reqHeader.setErrCode("");
		reqHeader.setErrMsg("");
		reqHeader.setReserved("");

		//
		RetrieveUseSvcListServiceStub.RequestRecord reqRecord = new RetrieveUseSvcListServiceStub.RequestRecord();
		reqRecord.setRequestBody(reqBody);
		reqRecord.setESBHeader(reqHeader);

		//
		RetrieveUseSvcListServiceStub.RetrieveUseSvcList reqIn = new RetrieveUseSvcListServiceStub.RetrieveUseSvcList();
		reqIn.setRequestRecord(reqRecord);

		//
		String host = URL;
		String path = "/CSSI/SB/RetrieveUseSvcList";
		String targetEndpoint = host + path;

		RetrieveUseSvcListServiceStub stub = null;
		RetrieveUseSvcListServiceStub.RetrieveUseSvcListResponse res = null;

		try {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(printFields(dsInputIn));

				traceRequest(stringBuffer.toString());
			}

			stub = new RetrieveUseSvcListServiceStub(targetEndpoint);
			res = stub.retrieveUseSvcList(reqIn);
		} catch (RemoteException e) {
			e.printStackTrace();
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(e.getMessage());

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(reqHeader.getErrCode(), reqHeader.getErrMsg());
		}

		RetrieveUseSvcListServiceStub.ResponseRecord resRecord = res.getResponseRecord();
		RetrieveUseSvcListServiceStub.ESBHeader resHeader = resRecord.getESBHeader();
		if (!(resHeader.getErrCode() == null || "".equals(resHeader.getErrCode()))) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(resHeader.getErrCode(), resHeader.getErrMsg());
		}

		RetrieveUseSvcListServiceStub.BusinessHeader bizHeader = resRecord.getBusinessHeader();
		if (bizHeader.getResultCode() == null || !"N0000".equals(bizHeader.getResultCode())) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));
				stringBuffer.append(printFields(bizHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(bizHeader.getResultCode(), bizHeader.getResultMessage());
		}

		RetrieveUseSvcListServiceStub.ResponseBody resBody = resRecord.getResponseBody();
		{
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
			stringBuffer.append(printFields(resHeader));
			stringBuffer.append(printFields(bizHeader));
			if (resBody != null) {
				RetrieveUseSvcListServiceStub.DsUseSvcListOutVO[] dsUseSvcListOut = resBody.getDsUseSvcListOutVO();
				if (dsUseSvcListOut != null) {
					for (int i = 0; i < dsUseSvcListOut.length; i++) {
						stringBuffer.append(printFields(dsUseSvcListOut[i]));
					}
				}
			}

			traceResponse(stringBuffer.toString());
		}

		return resBody;
		// RetrieveUseSvcListServiceStub.DsUseSvcListOutVO[] dsUseSvcListOut =
		// resBody.getDsUseSvcListOutVO();
		// if(dsResOut!=null){
		// for (int i = 0; i < dsUseSvcListOut.length; i++) {
		// // 가입번호
		// dsUseSvcListOut[i].getEntrNo();
		// // 가입상태명
		// dsUseSvcListOut[i].getEntrSttsNm();
		// // 상품번호
		// dsUseSvcListOut[i].getProdNo();
		// // 가입계약번호
		// dsUseSvcListOut[i].getAceno();
		// // 서비스코드
		// dsUseSvcListOut[i].getProdCd();
		// // 청구계정번호
		// dsUseSvcListOut[i].getBillAcntNo();
		// // 실명고객번호
		// dsUseSvcListOut[i].getHldrCustNo();
		// // 실사용자고객번호
		// dsUseSvcListOut[i].getRlusrCustNo();
		// // 서비스그룹코드
		// dsUseSvcListOut[i].getSvcgrpCd();
		// // 상품유형코드
		// dsUseSvcListOut[i].getSvcKdCd();
		// // 상품코드
		// dsUseSvcListOut[i].getSvcCd();
		// // 가상번호
		// dsUseSvcListOut[i].getVtNo();
		// // 연체상태코드
		// dsUseSvcListOut[i].getLtpymSttsCd();
		// // 가입상태변경일시
		// dsUseSvcListOut[i].getEntrSttsChngDttm();
		// // 최초개통일시
		// dsUseSvcListOut[i].getFrstEntrDttm();
		// // 유선PC수
		// dsUseSvcListOut[i].getCablPcCont();
		// // 정지유선PC수
		// dsUseSvcListOut[i].getStopCablPcCont();
		// // 무선PC수
		// dsUseSvcListOut[i].getWrlsPcCont();
		// // 정지무선PC수
		// dsUseSvcListOut[i].getStopWrlsPcCont();
		// // 결합유형코드
		// dsUseSvcListOut[i].getCnvgKdCd();
		// // 결합유형코드명
		// dsUseSvcListOut[i].getCnvgKdNm();
		// // 결합번호
		// dsUseSvcListOut[i].getCnvgNo();
		// // 발신금지여부
		// dsUseSvcListOut[i].getSendPhbYn();
		// // 착신금지여부
		// dsUseSvcListOut[i].getIcallPhbYn();
		// // 개통대리점코드
		// dsUseSvcListOut[i].getSbgnDlrCd();
		// // 가입상태변경누적번호 - 정지예약
		// dsUseSvcListOut[i].getEntrSttsChngSeqno();
		// // 가입상태변경누적번호 - 해제예약
		// dsUseSvcListOut[i].getRspEntrSttsChngSeqno();
		// // 가입상태유효시작일자(정지희망일자)
		// dsUseSvcListOut[i].getEntrSttsValdStrtDt();
		// // 가입상태유효종료일자
		// dsUseSvcListOut[i].getEntrSttsValdEndDt();
		// // 상태변경사유코드
		// dsUseSvcListOut[i].getEntrSttsChngRsnCd();
		// // 상태변경상세사유코드 - 정지예약
		// dsUseSvcListOut[i].getEntrSttsChngRsnDtlCd();
		// // 상태변경상세사유코드 - 정지해제예약
		// dsUseSvcListOut[i].getRspRsnCd();
		// // 상태변경상세사유코드명
		// dsUseSvcListOut[i].getEntrSttsChngRsnDtlNm();
		// // 예약메모
		// dsUseSvcListOut[i].getMemo();
		// // 단말모델코드
		// dsUseSvcListOut[i].getItemId();
		// // 단말번호
		// dsUseSvcListOut[i].getManfSerialNo();
		// // 서비스명
		// dsUseSvcListOut[i].getProdNm();
		// }
		// }
	}

	/**
	 * @throws :
	 * @Part :
	 * @Author :
	 * @Component :
	 * @Primitive :
	 * @type :
	 * @Description : [SB641] 일시정지해제내역조회
	 */
	private RetrieveSusSvcListServiceStub.ResponseBody retrieveSusSvcList(
			RetrieveSusSvcListServiceStub.DsInputInVO dsInputIn) throws Exception {
		// sSearchCb 검색구분 VARCHAR2 2 N 단수 구분: 01
		// sSrchValue 검색조건 VARCHAR2 12 N 단수 가입번호
		// nextOperatorId 처리자ID VARCHAR2 15 N 단수

		dsInputIn.setNextOperatorId(createNextOperatorId());

		// RetrieveUseSvcListServiceStub.DsInputInVO dsInputIn = new
		// RetrieveUseSvcListServiceStub.DsInputInVO();
		// dsInputIn.setSSearchCb(sSearchCb);
		// dsInputIn.setSSrchValue(sSrchValue);
		// dsInputIn.setNextOperatorId(nextOperatorId);

		//
		RetrieveSusSvcListServiceStub.RequestBody reqBody = new RetrieveSusSvcListServiceStub.RequestBody();
		reqBody.setDsInputInVO(dsInputIn);

		//
		RetrieveSusSvcListServiceStub.ESBHeader reqHeader = new RetrieveSusSvcListServiceStub.ESBHeader();
		reqHeader.setServiceID("SB641");
		reqHeader.setTransactionID(createTxId());
		reqHeader.setSystemID(SYSTEM_ID);
		reqHeader.setErrCode("");
		reqHeader.setErrMsg("");
		reqHeader.setReserved("");

		//
		RetrieveSusSvcListServiceStub.RequestRecord reqRecord = new RetrieveSusSvcListServiceStub.RequestRecord();
		reqRecord.setRequestBody(reqBody);
		reqRecord.setESBHeader(reqHeader);

		//
		RetrieveSusSvcListServiceStub.RetrieveSusSvcList reqIn = new RetrieveSusSvcListServiceStub.RetrieveSusSvcList();
		reqIn.setRequestRecord(reqRecord);

		//
		String host = URL;
		String path = "/CSSI/SB/RetrieveSusSvcList";
		String targetEndpoint = host + path;

		RetrieveSusSvcListServiceStub stub = null;
		RetrieveSusSvcListServiceStub.RetrieveSusSvcListResponse res = null;

		try {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(printFields(dsInputIn));

				traceRequest(stringBuffer.toString());
			}

			stub = new RetrieveSusSvcListServiceStub(targetEndpoint);
			res = stub.retrieveSusSvcList(reqIn);
		} catch (RemoteException e) {
			e.printStackTrace();
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(e.getMessage());

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(reqHeader.getErrCode(), reqHeader.getErrMsg());
		}

		RetrieveSusSvcListServiceStub.ResponseRecord resRecord = res.getResponseRecord();
		RetrieveSusSvcListServiceStub.ESBHeader resHeader = resRecord.getESBHeader();
		if (!(resHeader.getErrCode() == null || "".equals(resHeader.getErrCode()))) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(resHeader.getErrCode(), resHeader.getErrMsg());
		}

		RetrieveSusSvcListServiceStub.BusinessHeader bizHeader = resRecord.getBusinessHeader();
		if (bizHeader.getResultCode() == null || !"N0000".equals(bizHeader.getResultCode())) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));
				stringBuffer.append(printFields(bizHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(bizHeader.getResultCode(), bizHeader.getResultMessage());
		}

		RetrieveSusSvcListServiceStub.ResponseBody resBody = resRecord.getResponseBody();
		{
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
			stringBuffer.append(printFields(resHeader));
			stringBuffer.append(printFields(bizHeader));
			if (resBody != null) {
				RetrieveSusSvcListServiceStub.DsOutputOutVO[] dsSusSvcListOut = resBody.getDsOutputOutVO();
				if (dsSusSvcListOut != null) {
					for (int i = 0; i < dsSusSvcListOut.length; i++) {
						stringBuffer.append(printFields(dsSusSvcListOut[i]));
					}
				}
			}

			traceResponse(stringBuffer.toString());
		}

		return resBody;
		// RetrieveUseSvcListServiceStub.DsUseSvcListOutVO[] dsUseSvcListOut =
		// resBody.getDsUseSvcListOutVO();
		// if(dsResOut!=null){
		// for (int i = 0; i < dsUseSvcListOut.length; i++) {
		// // 가입번호
		// dsUseSvcListOut[i].getEntrNo();
		// // 가입상태명
		// dsUseSvcListOut[i].getEntrSttsNm();
		// // 상품번호
		// dsUseSvcListOut[i].getProdNo();
		// // 가입계약번호
		// dsUseSvcListOut[i].getAceno();
		// // 서비스코드
		// dsUseSvcListOut[i].getProdCd();
		// // 청구계정번호
		// dsUseSvcListOut[i].getBillAcntNo();
		// // 실명고객번호
		// dsUseSvcListOut[i].getHldrCustNo();
		// // 실사용자고객번호
		// dsUseSvcListOut[i].getRlusrCustNo();
		// // 서비스그룹코드
		// dsUseSvcListOut[i].getSvcgrpCd();
		// // 상품유형코드
		// dsUseSvcListOut[i].getSvcKdCd();
		// // 상품코드
		// dsUseSvcListOut[i].getSvcCd();
		// // 가상번호
		// dsUseSvcListOut[i].getVtNo();
		// // 연체상태코드
		// dsUseSvcListOut[i].getLtpymSttsCd();
		// // 가입상태변경일시
		// dsUseSvcListOut[i].getEntrSttsChngDttm();
		// // 최초개통일시
		// dsUseSvcListOut[i].getFrstEntrDttm();
		// // 유선PC수
		// dsUseSvcListOut[i].getCablPcCont();
		// // 정지유선PC수
		// dsUseSvcListOut[i].getStopCablPcCont();
		// // 무선PC수
		// dsUseSvcListOut[i].getWrlsPcCont();
		// // 정지무선PC수
		// dsUseSvcListOut[i].getStopWrlsPcCont();
		// // 결합유형코드
		// dsUseSvcListOut[i].getCnvgKdCd();
		// // 결합유형코드명
		// dsUseSvcListOut[i].getCnvgKdNm();
		// // 결합번호
		// dsUseSvcListOut[i].getCnvgNo();
		// // 발신금지여부
		// dsUseSvcListOut[i].getSendPhbYn();
		// // 착신금지여부
		// dsUseSvcListOut[i].getIcallPhbYn();
		// // 개통대리점코드
		// dsUseSvcListOut[i].getSbgnDlrCd();
		// // 가입상태변경누적번호 - 정지예약
		// dsUseSvcListOut[i].getEntrSttsChngSeqno();
		// // 가입상태변경누적번호 - 해제예약
		// dsUseSvcListOut[i].getRspEntrSttsChngSeqno();
		// // 가입상태유효시작일자(정지희망일자)
		// dsUseSvcListOut[i].getEntrSttsValdStrtDt();
		// // 가입상태유효종료일자
		// dsUseSvcListOut[i].getEntrSttsValdEndDt();
		// // 상태변경사유코드
		// dsUseSvcListOut[i].getEntrSttsChngRsnCd();
		// // 상태변경상세사유코드 - 정지예약
		// dsUseSvcListOut[i].getEntrSttsChngRsnDtlCd();
		// // 상태변경상세사유코드 - 정지해제예약
		// dsUseSvcListOut[i].getRspRsnCd();
		// // 상태변경상세사유코드명
		// dsUseSvcListOut[i].getEntrSttsChngRsnDtlNm();
		// // 예약메모
		// dsUseSvcListOut[i].getMemo();
		// // 단말모델코드
		// dsUseSvcListOut[i].getItemId();
		// // 단말번호
		// dsUseSvcListOut[i].getManfSerialNo();
		// // 서비스명
		// dsUseSvcListOut[i].getProdNm();
		// }
		// }
	}
	
	/**
	 * @throws :
	 * @Part :
	 * @Author :
	 * @Component :
	 * @Primitive :
	 * @type :
	 * @Description : [SB639] 일시정지예약신청
	 */
	private CreateRsvSuspendOrRsSuspendInfoServiceStub.ResponseBody createRsvSuspendOrRsSuspendInfo(
			CreateRsvSuspendOrRsSuspendInfoServiceStub.DsSttsRsvInVO[] dsSttsRsvIn) throws ESBException {
		// entrNo 가입번호 NUMBER 12 N 복수
		// entrSttsChngSeqno 가입변경일련번호 VARCHAR2 10 복수 변경일경우 존재
		// custNo 고객번호 VARCHAR2 15 N 복수 실명고객번호
		// prodNo 상품번호 VARCHAR2 20 N 복수
		// billAcntNo 청구계정번호 VARCHAR2 10 N 복수
		// entrSttsValdStrtDt 처리일자 VARCHAR2 8 N 복수
		// entrSttsValdEndDt 일시정지희망일자 VARCHAR2 8 N 복수
		// rspEntrSttsValdEndDt 정지해제희망일자 VARCHAR2 8 복수 HS서비스는 필수
		// rspEntrSttsChngRsnDtlCd 정지해제상세사유코드 VARCHAR2 10 복수 정지예약과 함께 해제예약할경우
		// 사유를 지정하여 넘김PR(정상적절차)
		// rgstDt 처리일자 VARCHAR2 8 N 복수 오늘일자
		// entrSttsChngRsnCd 가입상태변경사유코드 VARCHAR2 10 N 복수
		// entrSttsChngRsnDtlCd 가입상태변경상세사유코드 VARCHAR2 10 N 복수
		// prssYn 처리여부 VARCHAR2 1 N 복수 Y
		// prssDlrCd 처리대리점코드 VARCHAR2 8 N 복수 100000
		// aplyLvlCd 적용레벨 VARCHAR2 1 N 복수 C'-가입번호별 서비스 적용 레벨
		// msgParmCnt 파라미터갯수 VARCHAR2 1 N 복수 신규:3
		// memoCrteDlrCd 대리점코드 VARCHAR2 8 N 복수
		// rsnCd 사유코드 VARCHAR2 3 N 복수 신규:E19
		// rsnDtlCd 사유상세코드 VARCHAR2 4 N 복수 신규:4039
		// sendPhbYn 발신금지 여부 VARCHAR2 1 N 복수 Y/N
		// icallPhbYn 착신금지여부 VARCHAR2 1 N 복수 Y/N
		// msgParm1 파라미터1 VARCHAR2 30 N 복수 "일시정지/정지해제"
		// msgParm2 파라미터2 VARCHAR2 30 N 복수 일시정지예약일/일시정지해제예약일
		// msgParm3 파라미터3 VARCHAR2 30 N 복수 일시정지사유코드명/정지해제사유코드명
		// userMemo 사용자메모 VARCHAR2 800 복수
		// nextOperatorId 처리자ID VARCHAR2 15 N 복수

		dsSttsRsvIn[0].setNextOperatorId(createNextOperatorId());

		// CreateRsvSuspendOrRsSuspendInfoServiceStub.DsSttsRsvInVO[]
		// dsSttsRsvIn =
		// new CreateRsvSuspendOrRsSuspendInfoServiceStub.DsSttsRsvInVO[1];
		// dsSttsRsvIn[0] = new
		// CreateRsvSuspendOrRsSuspendInfoServiceStub.DsSttsRsvInVO();
		// dsSttsRsvIn[0].setEntrNo(entrNo);
		// dsSttsRsvIn[0].setEntrSttsChngSeqno(entrSttsChngSeqno);
		// dsSttsRsvIn[0].setCustNo(custNo);
		// dsSttsRsvIn[0].setProdNo(prodNo);
		// dsSttsRsvIn[0].setBillAcntNo(billAcntNo);
		// dsSttsRsvIn[0].setEntrSttsValdStrtDt(entrSttsValdStrtDt);
		// dsSttsRsvIn[0].setEntrSttsValdEndDt(entrSttsValdEndDt);
		// dsSttsRsvIn[0].setRspEntrSttsValdEndDt(rspEntrSttsValdEndDt);
		// dsSttsRsvIn[0].setRspEntrSttsChngRsnDtlCd(rspEntrSttsChngRsnDtlCd);
		// dsSttsRsvIn[0].setRgstDt(rgstDt);
		// dsSttsRsvIn[0].setEntrSttsChngRsnCd(entrSttsChngRsnCd);
		// dsSttsRsvIn[0].setEntrSttsChngRsnDtlCd(entrSttsChngRsnDtlCd);
		// dsSttsRsvIn[0].setPrssYn(prssYn);
		// dsSttsRsvIn[0].setPrssDlrCd(prssDlrCd);
		// dsSttsRsvIn[0].setAplyLvlCd(aplyLvlCd);
		// dsSttsRsvIn[0].setMsgParmCnt(msgParmCnt);
		// dsSttsRsvIn[0].setMemoCrteDlrCd(memoCrteDlrCd);
		// dsSttsRsvIn[0].setRsnCd(rsnCd);
		// dsSttsRsvIn[0].setRsnDtlCd(rsnDtlCd);
		// dsSttsRsvIn[0].setSendPhbYn(sendPhbYn);
		// dsSttsRsvIn[0].setIcallPhbYn(icallPhbYn);
		// dsSttsRsvIn[0].setMsgParm1(msgParm1);
		// dsSttsRsvIn[0].setMsgParm2(msgParm2);
		// dsSttsRsvIn[0].setMsgParm3(msgParm3);
		// dsSttsRsvIn[0].setUserMemo(userMemo);
		// dsSttsRsvIn[0].setNextOperatorId(nextOperatorId);

		//
		CreateRsvSuspendOrRsSuspendInfoServiceStub.RequestBody reqBody = new CreateRsvSuspendOrRsSuspendInfoServiceStub.RequestBody();
		reqBody.setDsSttsRsvInVO(dsSttsRsvIn);

		//
		CreateRsvSuspendOrRsSuspendInfoServiceStub.ESBHeader reqHeader = new CreateRsvSuspendOrRsSuspendInfoServiceStub.ESBHeader();
		reqHeader.setServiceID("SB639");
		reqHeader.setTransactionID(createTxId());
		reqHeader.setSystemID(SYSTEM_ID);
		reqHeader.setErrCode("");
		reqHeader.setErrMsg("");
		reqHeader.setReserved("");

		//
		CreateRsvSuspendOrRsSuspendInfoServiceStub.RequestRecord reqRecord = new CreateRsvSuspendOrRsSuspendInfoServiceStub.RequestRecord();
		reqRecord.setRequestBody(reqBody);
		reqRecord.setESBHeader(reqHeader);

		//
		CreateRsvSuspendOrRsSuspendInfoServiceStub.CreateRsvSuspendOrRsSuspendInfo reqIn = new CreateRsvSuspendOrRsSuspendInfoServiceStub.CreateRsvSuspendOrRsSuspendInfo();
		reqIn.setRequestRecord(reqRecord);

		//
		String host = URL;
		String path = "/CSSI/SB/CreateRsvSuspendOrRsSuspendInfo";
		String targetEndpoint = host + path;

		CreateRsvSuspendOrRsSuspendInfoServiceStub stub = null;
		CreateRsvSuspendOrRsSuspendInfoServiceStub.CreateRsvSuspendOrRsSuspendInfoResponse res = null;

		try {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(printFields(dsSttsRsvIn[0]));

				traceRequest(stringBuffer.toString());
			}
			stub = new CreateRsvSuspendOrRsSuspendInfoServiceStub(targetEndpoint);
			res = stub.createRsvSuspendOrRsSuspendInfo(reqIn);
		} catch (RemoteException e) {
			e.printStackTrace();
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(e.getMessage());

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(reqHeader.getErrCode(), reqHeader.getErrMsg());
		}

		CreateRsvSuspendOrRsSuspendInfoServiceStub.ResponseRecord resRecord = res.getResponseRecord();
		CreateRsvSuspendOrRsSuspendInfoServiceStub.ESBHeader resHeader = resRecord.getESBHeader();
		if (!(resHeader.getErrCode() == null || "".equals(resHeader.getErrCode()))) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(resHeader.getErrCode(), resHeader.getErrMsg());
		}

		CreateRsvSuspendOrRsSuspendInfoServiceStub.BusinessHeader bizHeader = resRecord.getBusinessHeader();
		if (bizHeader.getResultCode() == null || !"N0000".equals(bizHeader.getResultCode())) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));
				stringBuffer.append(printFields(bizHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(bizHeader.getResultCode(), bizHeader.getResultMessage());
		}

		CreateRsvSuspendOrRsSuspendInfoServiceStub.ResponseBody resBody = resRecord.getResponseBody();
		{
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
			stringBuffer.append(printFields(resHeader));
			stringBuffer.append(printFields(bizHeader));
			if (resBody != null) {
				CreateRsvSuspendOrRsSuspendInfoServiceStub.ResultOutVO resultOut = resBody.getResultOutVO();
				if (resultOut != null) {
					stringBuffer.append(printFields(resultOut));
				}
			}

			traceResponse(stringBuffer.toString());
		}

		return resBody;
		// CreateRsvSuspendOrRsSuspendInfoServiceStub.ResultOutVO resultOut =
		// resBody.getResultOutVO();
		// if (resultOut != null) {
		// // 성공여부
		// resultOut.getResult();
		// }
	}

	/**
	 * @throws :
	 * @Part :
	 * @Author :
	 * @Component :
	 * @Primitive :
	 * @type :
	 * @Description : [SB640] 예약정지취소신청
	 */
	private UpdateSttsChgRsvExpServiceStub.ResponseBody updateSttsChgRsvService(
			UpdateSttsChgRsvExpServiceStub.DsRsvExpInVO[] dsRsvExpIn) throws ESBException {

		dsRsvExpIn[0].setNextOperatorId(createNextOperatorId());

		//
		UpdateSttsChgRsvExpServiceStub.RequestBody reqBody = new UpdateSttsChgRsvExpServiceStub.RequestBody();
		reqBody.setDsRsvExpInVO(dsRsvExpIn);

		//
		UpdateSttsChgRsvExpServiceStub.ESBHeader reqHeader = new UpdateSttsChgRsvExpServiceStub.ESBHeader();
		reqHeader.setServiceID("SB640");
		reqHeader.setTransactionID(createTxId());
		reqHeader.setSystemID(SYSTEM_ID);
		reqHeader.setErrCode("");
		reqHeader.setErrMsg("");
		reqHeader.setReserved("");

		//
		UpdateSttsChgRsvExpServiceStub.RequestRecord reqRecord = new UpdateSttsChgRsvExpServiceStub.RequestRecord();
		reqRecord.setRequestBody(reqBody);
		reqRecord.setESBHeader(reqHeader);

		//
		UpdateSttsChgRsvExpServiceStub.UpdateSttsChgRsvExp reqIn = new UpdateSttsChgRsvExpServiceStub.UpdateSttsChgRsvExp();
		reqIn.setRequestRecord(reqRecord);

		//
		String host = URL;
		String path = "/CSSI/SB/UpdateSttsChgRsvExp";
		String targetEndpoint = host + path;

		UpdateSttsChgRsvExpServiceStub stub = null;
		UpdateSttsChgRsvExpServiceStub.UpdateSttsChgRsvExpResponse res = null;

		try {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(printFields(dsRsvExpIn[0]));

				traceRequest(stringBuffer.toString());
			}
			stub = new UpdateSttsChgRsvExpServiceStub(targetEndpoint);
			res = stub.updateSttsChgRsvExp(reqIn);
		} catch (RemoteException e) {
			e.printStackTrace();
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(e.getMessage());

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(reqHeader.getErrCode(), reqHeader.getErrMsg());
		}

		UpdateSttsChgRsvExpServiceStub.ResponseRecord resRecord = res.getResponseRecord();
		UpdateSttsChgRsvExpServiceStub.ESBHeader resHeader = resRecord.getESBHeader();
		if (!(resHeader.getErrCode() == null || "".equals(resHeader.getErrCode()))) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(resHeader.getErrCode(), resHeader.getErrMsg());
		}

		UpdateSttsChgRsvExpServiceStub.BusinessHeader bizHeader = resRecord.getBusinessHeader();
		if (bizHeader.getResultCode() == null || !"N0000".equals(bizHeader.getResultCode())) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));
				stringBuffer.append(printFields(bizHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(bizHeader.getResultCode(), bizHeader.getResultMessage());
		}

		UpdateSttsChgRsvExpServiceStub.ResponseBody resBody = resRecord.getResponseBody();
		{
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
			stringBuffer.append(printFields(resHeader));
			stringBuffer.append(printFields(bizHeader));
			if (resBody != null) {
				UpdateSttsChgRsvExpServiceStub.DsSusResultOutVO resultOut = resBody.getDsSusResultOutVO();
				if (resultOut != null) {
					stringBuffer.append(printFields(resultOut));
				}
			}

			traceResponse(stringBuffer.toString());
		}

		return resBody;
	}
	
	/**
	 * @throws :
	 * @Part :
	 * @Author :
	 * @Component :
	 * @Primitive :
	 * @type :
	 * @Description : [SB695] 일시정지해제신청
	 */
	private SaveEntrSuspendHomepgServiceStub.ResponseBody saveEntrSuspendHomepgService(
			SaveEntrSuspendHomepgServiceStub.DsSuspendInfoInVO[] dsSuspendInfoIn) throws ESBException {

		dsSuspendInfoIn[0].setNextOperatorId(createNextOperatorId());

		//
		SaveEntrSuspendHomepgServiceStub.RequestBody reqBody = new SaveEntrSuspendHomepgServiceStub.RequestBody();
		reqBody.setDsSuspendInfoInVO(dsSuspendInfoIn);

		//
		SaveEntrSuspendHomepgServiceStub.ESBHeader reqHeader = new SaveEntrSuspendHomepgServiceStub.ESBHeader();
		reqHeader.setServiceID("SB695");
		reqHeader.setTransactionID(createTxId());
		reqHeader.setSystemID(SYSTEM_ID);
		reqHeader.setErrCode("");
		reqHeader.setErrMsg("");
		reqHeader.setReserved("");

		//
		SaveEntrSuspendHomepgServiceStub.RequestRecord reqRecord = new SaveEntrSuspendHomepgServiceStub.RequestRecord();
		reqRecord.setRequestBody(reqBody);
		reqRecord.setESBHeader(reqHeader);

		//
		SaveEntrSuspendHomepgServiceStub.SaveEntrSuspendHomepg reqIn = new SaveEntrSuspendHomepgServiceStub.SaveEntrSuspendHomepg();
		reqIn.setRequestRecord(reqRecord);

		//
		String host = URL;
		String path = "/CSSI/SB/SaveEntrSuspendHomepg";
		String targetEndpoint = host + path;

		SaveEntrSuspendHomepgServiceStub stub = null;
		SaveEntrSuspendHomepgServiceStub.SaveEntrSuspendHomepgResponse res = null;

		try {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(printFields(dsSuspendInfoIn[0]));

				traceRequest(stringBuffer.toString());
			}
			stub = new SaveEntrSuspendHomepgServiceStub(targetEndpoint);
			res = stub.saveEntrSuspendHomepg(reqIn);
		} catch (RemoteException e) {
			e.printStackTrace();
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(e.getMessage());

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(reqHeader.getErrCode(), reqHeader.getErrMsg());
		}

		SaveEntrSuspendHomepgServiceStub.ResponseRecord resRecord = res.getResponseRecord();
		SaveEntrSuspendHomepgServiceStub.ESBHeader resHeader = resRecord.getESBHeader();
		if (!(resHeader.getErrCode() == null || "".equals(resHeader.getErrCode()))) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(resHeader.getErrCode(), resHeader.getErrMsg());
		}

		SaveEntrSuspendHomepgServiceStub.BusinessHeader bizHeader = resRecord.getBusinessHeader();
		if (bizHeader.getResultCode() == null || !"N0000".equals(bizHeader.getResultCode())) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));
				stringBuffer.append(printFields(bizHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(bizHeader.getResultCode(), bizHeader.getResultMessage());
		}

		SaveEntrSuspendHomepgServiceStub.ResponseBody resBody = resRecord.getResponseBody();
		{
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
			stringBuffer.append(printFields(resHeader));
			stringBuffer.append(printFields(bizHeader));
			if (resBody != null) {
				SaveEntrSuspendHomepgServiceStub.DsSusResultOutVO resultOut = resBody.getDsSusResultOutVO();
				if (resultOut != null) {
					stringBuffer.append(printFields(resultOut));
				}
			}

			traceResponse(stringBuffer.toString());
		}

		return resBody;
	}
	
	// /**
	// * @throws :
	// * @Part :
	// * @Author :
	// * @Component :
	// * @Primitive :
	// * @type :
	// * @Description : [SB689] CSSI가입정보조회마스터
	// */
	// private RetrieveUzoneServiceEntrInfoServiceStub.ResponseBody
	// retrieveUzoneServiceEntrInfo(
	// RetrieveUzoneServiceEntrInfoServiceStub.DsInputInVO dsInputIn) throws
	// ESBException {
	// // custrnmNo 고객주민번호 VARCHAR2 13 단수
	// // ipinCi 아이핀번호 VARCHAR2 150 단수
	// // ctn 전화번호 VARCHAR2 12 단수
	// // prodDvCd 서비스구분코드 VARCHAR2 50 단수
	// // nextOperatorId 처리자ID VARCHAR2 15 N 단수
	//
	// dsInputIn.setNextOperatorId(createNextOperatorId());
	//
	// // RetrieveUzoneServiceEntrInfoServiceStub.DsInputInVO dsInputIn = new
	// // RetrieveUzoneServiceEntrInfoServiceStub.DsInputInVO();
	// // dsInputIn.setCustrnmNo(custrnmNo);
	// // dsInputIn.setIpinCi(ipinCi);
	// // dsInputIn.setCtn(ctn);
	// // dsInputIn.setProdDvCd(prodDvCd);
	// // dsInputIn.setNextOperatorId(nextOperatorId);
	//
	// //
	// RetrieveUzoneServiceEntrInfoServiceStub.RequestBody reqBody = new
	// RetrieveUzoneServiceEntrInfoServiceStub.RequestBody();
	// reqBody.setDsInputInVO(dsInputIn);
	//
	// //
	// RetrieveUzoneServiceEntrInfoServiceStub.ESBHeader reqHeader = new
	// RetrieveUzoneServiceEntrInfoServiceStub.ESBHeader();
	// reqHeader.setServiceID("SB689");
	// reqHeader.setTransactionID(createTxId());
	// reqHeader.setSystemID(SYSTEM_ID);
	// reqHeader.setErrCode("");
	// reqHeader.setErrMsg("");
	// reqHeader.setReserved("");
	//
	// //
	// RetrieveUzoneServiceEntrInfoServiceStub.RequestRecord reqRecord = new
	// RetrieveUzoneServiceEntrInfoServiceStub.RequestRecord();
	// reqRecord.setRequestBody(reqBody);
	// reqRecord.setESBHeader(reqHeader);
	//
	// //
	// RetrieveUzoneServiceEntrInfoServiceStub.RetrieveUzoneServiceEntrInfo
	// reqIn =
	// new
	// RetrieveUzoneServiceEntrInfoServiceStub.RetrieveUzoneServiceEntrInfo();
	// reqIn.setRequestRecord(reqRecord);
	//
	// //
	// String host = URL;
	// String path = "/CSSI/SB/RetrieveUzoneServiceEntrInfo";
	// String targetEndpoint = host + path;
	//
	// RetrieveUzoneServiceEntrInfoServiceStub stub = null;
	// RetrieveUzoneServiceEntrInfoServiceStub.RetrieveUzoneServiceEntrInfoResponse
	// res = null;
	//
	// try {
	// {
	// StringBuffer stringBuffer = new StringBuffer();
	// stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint",
	// targetEndpoint));
	// stringBuffer.append(printFields(reqHeader));
	// stringBuffer.append(printFields(dsInputIn));
	//
	// traceWriter.trace(CCSSUtil.getCtn(), TraceConst.NODE_ID_WAS,
	// TraceConst.NODE_ID_ESB,
	// TraceConst.PROTOCOL_SOAP, stringBuffer.toString());
	// }
	// stub = new RetrieveUzoneServiceEntrInfoServiceStub(targetEndpoint);
	// res = stub.retrieveUzoneServiceEntrInfo(reqIn);
	// } catch (RemoteException e) {
	// e.printStackTrace();
	// {
	// StringBuffer stringBuffer = new StringBuffer();
	// stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint",
	// targetEndpoint));
	// stringBuffer.append(printFields(reqHeader));
	// stringBuffer.append(e.getMessage());
	//
	// traceWriter.trace(CCSSUtil.getCtn(), TraceConst.NODE_ID_ESB,
	// TraceConst.NODE_ID_WAS,
	// TraceConst.PROTOCOL_SOAP, stringBuffer.toString());
	// }
	// throw new ESBException(reqHeader.getErrCode(), reqHeader.getErrMsg());
	// }
	//
	// RetrieveUzoneServiceEntrInfoServiceStub.ResponseRecord resRecord =
	// res.getResponseRecord();
	// RetrieveUzoneServiceEntrInfoServiceStub.ESBHeader resHeader =
	// resRecord.getESBHeader();
	// if (!(resHeader.getErrCode() == null ||
	// "".equals(resHeader.getErrCode()))) {
	// {
	// StringBuffer stringBuffer = new StringBuffer();
	// stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint",
	// targetEndpoint));
	// stringBuffer.append(printFields(resHeader));
	//
	// traceWriter.trace(CCSSUtil.getCtn(), TraceConst.NODE_ID_ESB,
	// TraceConst.NODE_ID_WAS,
	// TraceConst.PROTOCOL_SOAP, stringBuffer.toString());
	// }
	// throw new ESBException(resHeader.getErrCode(), resHeader.getErrMsg());
	// }
	//
	// RetrieveUzoneServiceEntrInfoServiceStub.BusinessHeader bizHeader =
	// resRecord.getBusinessHeader();
	// if (bizHeader.getResultCode() == null ||
	// !"N0000".equals(bizHeader.getResultCode())) {
	// {
	// StringBuffer stringBuffer = new StringBuffer();
	// stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint",
	// targetEndpoint));
	// stringBuffer.append(printFields(resHeader));
	// stringBuffer.append(printFields(bizHeader));
	//
	// traceWriter.trace(CCSSUtil.getCtn(), TraceConst.NODE_ID_ESB,
	// TraceConst.NODE_ID_WAS,
	// TraceConst.PROTOCOL_SOAP, stringBuffer.toString());
	// }
	// throw new ESBException(bizHeader.getResultCode(),
	// bizHeader.getResultMessage());
	// }
	//
	// RetrieveUzoneServiceEntrInfoServiceStub.ResponseBody resBody =
	// resRecord.getResponseBody();
	// {
	// StringBuffer stringBuffer = new StringBuffer();
	// stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint",
	// targetEndpoint));
	// stringBuffer.append(printFields(resHeader));
	// stringBuffer.append(printFields(bizHeader));
	// if (resBody != null) {
	// RetrieveUzoneServiceEntrInfoServiceStub.DsOutputOutVO[] dsOutputOut =
	// resBody.getDsOutputOutVO();
	// if (dsOutputOut != null) {
	// for (int i = 0; i < dsOutputOut.length; i++) {
	// stringBuffer.append(printFields(dsOutputOut[i]));
	// }
	// }
	// }
	//
	// traceWriter.trace(CCSSUtil.getCtn(), TraceConst.NODE_ID_ESB,
	// TraceConst.NODE_ID_WAS,
	// TraceConst.PROTOCOL_SOAP, stringBuffer.toString());
	// }
	//
	// return resBody;
	// // RetrieveUzoneServiceEntrInfoServiceStub.DsOutputOutVO[] dsOutputOut =
	// // resBody.getDsOutputOutVO();
	// // if (dsOutputOut != null) {
	// // for (int i = 0; i < dsOutputOut.length; i++) {
	// // // 결합번
	// // dsOutputOut[i].getCnvgNo();
	// // // 결합유형코드
	// // dsOutputOut[i].getCnvgKdCd();
	// // // 결합유형명
	// // dsOutputOut[i].getCnvgKdNm();
	// // // 홈코드번호
	// // dsOutputOut[i].getHomecdNo();
	// // // 가입번호
	// // dsOutputOut[i].getEntrNo();
	// // // 고객명
	// // dsOutputOut[i].getCustNm();
	// // // 고객번호
	// // dsOutputOut[i].getCustNo();
	// // // 가입상태
	// // dsOutputOut[i].getEntrSttsCd();
	// // // 상품번호
	// // dsOutputOut[i].getProdNo();
	// // // 서비스군코드
	// // dsOutputOut[i].getSvcgrpCd();
	// // // 서비스군명
	// // dsOutputOut[i].getSvcgrpNm();
	// // // 서비스코드
	// // dsOutputOut[i].getProdCd();
	// // // 서비스명
	// // dsOutputOut[i].getProdNm();
	// // // 상품(요금제)코드
	// // dsOutputOut[i].getSvcCd();
	// // // 상품(요금제)명
	// // dsOutputOut[i].getSvcNm();
	// // // 전체주소
	// // dsOutputOut[i].getEstbAddr();
	// // // 가입자정보EMAIL
	// // dsOutputOut[i].getEmailAddr();
	// // // 가입자 휴대폰번호
	// // dsOutputOut[i].getMobileTelNo();
	// // // 가입자 자택번호
	// // dsOutputOut[i].getHomeTelNo();
	// // // 가입자 직장번
	// // dsOutputOut[i].getOfficeTelNo();
	// // // 가입자 VOIP번호
	// // dsOutputOut[i].getVoipTelNo();
	// // // 연결가입번호
	// // dsOutputOut[i].getLinkEntrNo();
	// // // 예금주명
	// // dsOutputOut[i].getPymCustNm();
	// // // 납부방법구분
	// // dsOutputOut[i].getPymMthdCd();
	// // // 카드사/은행
	// // dsOutputOut[i].getCardNm();
	// // // 카드/계좌번호
	// // dsOutputOut[i].getAcctNo();
	// // // 청구구분
	// // dsOutputOut[i].getBltxtKdCd();
	// // // 청구구분명
	// // dsOutputOut[i].getBltxtKdNm();
	// // // EMAIL
	// // dsOutputOut[i].getBltxtEmailAddr();
	// // // AP동의여부
	// // dsOutputOut[i].getApPuseAgrmtYn();
	// // // UZONE ID
	// // dsOutputOut[i].getAcntId();
	// // // 청구계정번호
	// // dsOutputOut[i].getBillAcntNo();
	// // // 카드/은행코드
	// // dsOutputOut[i].getCompCd();
	// // // 계정비밀번호
	// // dsOutputOut[i].getAcntPswd();
	// // // 핸드폰번호
	// // dsOutputOut[i].getAcntHpno();
	// // // 가상아이디여부
	// // dsOutputOut[i].getVtIdYn();
	// // // UZONE사용여부
	// // dsOutputOut[i].getAcntSttsNm();
	// // // 서비스구분코드
	// // dsOutputOut[i].getProdDvCd();
	// // // UZONE무료가입대상여부
	// // dsOutputOut[i].getFreeUzoneYn();
	// // // 시작페이지동의여부
	// // dsOutputOut[i].getStrtPageAgrmtYn();
	// // // 변동요소1값
	// // dsOutputOut[i].getVarFctr1Vlue();
	// // // 변동요소2값
	// // dsOutputOut[i].getVarFctr2Vlue();
	// // // 변동요소3값
	// // dsOutputOut[i].getVarFctr3Vlue();
	// // }
	// // }
	// }

	/**
	 * @throws :
	 * @Part :
	 * @Author :
	 * @Component :
	 * @Primitive :
	 * @type :
	 * @Description : [SB915] 서비스 가입 정보 조회
	 */
	private RetrieveCustSvcEntrInfoBDServiceStub.ResponseBody retrieveCustSvcEntrInfoBD(
			RetrieveCustSvcEntrInfoBDServiceStub.DsInputInVO dsInputIn) throws ESBException {
		// type 구분 VARCHAR2 1 N 단수 "A:가입번호,B:결합번호,C:청구계정번호,D:상품번호,E:홈코드번호"
		// no 번호 VARCHAR2 20 N 단수
		// nextOperatorId 처리자ID VARCHAR2 15 N 단수

		dsInputIn.setNextOperatorId(createNextOperatorId());

		// RetrieveCustSvcEntrInfoBDServiceStub.DsInputInVO dsInputIn = new
		// RetrieveCustSvcEntrInfoBDServiceStub.DsInputInVO();
		// dsInputIn.setType(type);
		// dsInputIn.setNo(no);
		// dsInputIn.setNextOperatorId(nextOperatorId);

		//
		RetrieveCustSvcEntrInfoBDServiceStub.RequestBody reqBody = new RetrieveCustSvcEntrInfoBDServiceStub.RequestBody();
		reqBody.setDsInputInVO(dsInputIn);

		//
		RetrieveCustSvcEntrInfoBDServiceStub.ESBHeader reqHeader = new RetrieveCustSvcEntrInfoBDServiceStub.ESBHeader();
		reqHeader.setServiceID("SB915");
		reqHeader.setTransactionID(createTxId());
		reqHeader.setSystemID(SYSTEM_ID);
		reqHeader.setErrCode("");
		reqHeader.setErrMsg("");
		reqHeader.setReserved("");

		//
		RetrieveCustSvcEntrInfoBDServiceStub.RequestRecord reqRecord = new RetrieveCustSvcEntrInfoBDServiceStub.RequestRecord();
		reqRecord.setRequestBody(reqBody);
		reqRecord.setESBHeader(reqHeader);

		//
		RetrieveCustSvcEntrInfoBDServiceStub.RetrieveCustSvcEntrInfoBD reqIn = new RetrieveCustSvcEntrInfoBDServiceStub.RetrieveCustSvcEntrInfoBD();
		reqIn.setRequestRecord(reqRecord);

		//
		String host = URL;
		String path = "/CSSI/SB/RetrieveCustSvcEntrInfoBD";
		String targetEndpoint = host + path;

		RetrieveCustSvcEntrInfoBDServiceStub stub = null;
		RetrieveCustSvcEntrInfoBDServiceStub.RetrieveCustSvcEntrInfoBDResponse res = null;

		try {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(printFields(dsInputIn));

				traceRequest(stringBuffer.toString());
			}
			stub = new RetrieveCustSvcEntrInfoBDServiceStub(targetEndpoint);
			res = stub.retrieveCustSvcEntrInfoBD(reqIn);
		} catch (RemoteException e) {
			e.printStackTrace();
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(e.getMessage());

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(reqHeader.getErrCode(), reqHeader.getErrMsg());
		}

		RetrieveCustSvcEntrInfoBDServiceStub.ResponseRecord resRecord = res.getResponseRecord();
		RetrieveCustSvcEntrInfoBDServiceStub.ESBHeader resHeader = resRecord.getESBHeader();
		if (!(resHeader.getErrCode() == null || "".equals(resHeader.getErrCode()))) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(resHeader.getErrCode(), resHeader.getErrMsg());
		}

		RetrieveCustSvcEntrInfoBDServiceStub.BusinessHeader bizHeader = resRecord.getBusinessHeader();
		if (bizHeader.getResultCode() == null || !"N0000".equals(bizHeader.getResultCode())) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));
				stringBuffer.append(printFields(bizHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(bizHeader.getResultCode(), bizHeader.getResultMessage());
		}

		RetrieveCustSvcEntrInfoBDServiceStub.ResponseBody resBody = resRecord.getResponseBody();
		{
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
			stringBuffer.append(printFields(resHeader));
			stringBuffer.append(printFields(bizHeader));
			if (resBody != null) {
				RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO[] dsOutputOut = resBody.getDsOutputOutVO();
				if (dsOutputOut != null) {
					for (int i = 0; i < dsOutputOut.length; i++) {
						stringBuffer.append(printFields(dsOutputOut[i]));
					}
				}
			}

			traceResponse(stringBuffer.toString());
		}

		return resBody;
		// RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO[] dsOutputOut =
		// resBody.getDsOutputOutVO();
		// for (int i = 0; i < dsResOut.length; i++) {
		// // 결합번호
		// dsResOut[i].getCnvgNo();
		// // 결합유형코드
		// dsResOut[i].getCnvgKdCd();
		// // 결합유형명
		// dsResOut[i].getCnvgKdNm();
		// // 가입번호
		// dsResOut[i].getEntrNo();
		// // 가입계약번호
		// dsResOut[i].getAceno();
		// // 고객번호
		// dsResOut[i].getCustNo();
		// // 고객생년월일(성별)
		// dsResOut[i].getCustrnmBday();
		// // 고객CI
		// dsResOut[i].getIpinCi();
		// // 법인/외국인번호
		// dsResOut[i].getCustrnmNo();
		// // 고객명
		// dsResOut[i].getCustNm();
		// // 실사용자고객번호
		// dsResOut[i].getRlusrCustNo();
		// // 실사용자생년월일(성별)
		// dsResOut[i].getRlusrCustrnmBday();
		// // 실사용고객CI
		// dsResOut[i].getRlusrIpinCi();
		// // 실사용자법인/외국인번호
		// dsResOut[i].getRlusrCustrnmNo();
		// // 실사용자고객명
		// dsResOut[i].getRlusrCustNm();
		// // 고객구분코드
		// dsResOut[i].getCustDvCd();
		// // 고객구분명
		// dsResOut[i].getCustDvNm();
		// // 고객일반전화번호
		// dsResOut[i].getHomeTelNo();
		// // 고객직장전화번호
		// dsResOut[i].getOfficeTelNo();
		// // 고객이동전화번호
		// dsResOut[i].getMobileTelNo();
		// // 고객이메일
		// dsResOut[i].getEmailAddr();
		// // 청구계정번호
		// dsResOut[i].getBillAcntNo();
		// // 가입상태코드
		// dsResOut[i].getEntrSttsCd();
		// // 가입상태명
		// dsResOut[i].getEntrSttsNm();
		// // 가입대리점코드
		// dsResOut[i].getEntrDlrCd();
		// // 가입대리점명
		// dsResOut[i].getEntrDlrNm();
		// // 개통대리점코드
		// dsResOut[i].getSbgnDlrCd();
		// // 개통대리점명
		// dsResOut[i].getSbgnDlrNm();
		// // 마켓코드
		// dsResOut[i].getMrktCd();
		// // 홈코드번호
		// dsResOut[i].getHomecdNo();
		// // 상품번호
		// dsResOut[i].getProdNo();
		// // 서비스군코드
		// dsResOut[i].getSvcgrpCd();
		// // 서비스군명
		// dsResOut[i].getSvcgrpNm();
		// // 서비스코드
		// dsResOut[i].getProdCd();
		// // 서비스명
		// dsResOut[i].getProdNm();
		// // 상품(요금제)코드
		// dsResOut[i].getSvcCd();
		// // 상품(요금제)명
		// dsResOut[i].getSvcNm();
		// // 약정기간
		// dsResOut[i].getFxedFctr1();
		// // 개통일자
		// dsResOut[i].getSvcFrstStrtDttm();
		// // 해지일자
		// dsResOut[i].getSvcEndDttm();
		// // 가입상태변경일시
		// dsResOut[i].getEntrSttsChngDttm();
		// // 설치주소
		// dsResOut[i].getEstbAddr();
		// // 설치우편번호
		// dsResOut[i].getEstbZip();
		// // 신규설치비감면여부
		// dsResOut[i].getBeginEbcstExmpYn();
		// // 가입유형코드
		// dsResOut[i].getEntrKdCd();
		// // 가입영업정책코드
		// dsResOut[i].getEntrBizPlcyCd();
		// // MIN번호
		// dsResOut[i].getMinNo();
		// // MIC번호
		// dsResOut[i].getMicNo();
		// // CAS연동KEY번호
		// dsResOut[i].getCasKeyNo();
		// // 요금청구구분
		// dsResOut[i].getBltxtKdCd();
		// // 요금청구구분명
		// dsResOut[i].getBltxtKdNm();
		// // 요금청구주소
		// dsResOut[i].getBltxtAddr();
		// // 요금청구우편번호
		// dsResOut[i].getBltxtZip();
		// // 청구전화번호구분
		// dsResOut[i].getBltxtTelNoCd();
		// // 청구전화번호
		// dsResOut[i].getBltxtRcpProdNo();
		// // 청구이메일
		// dsResOut[i].getBltxtEmailAddr();
		// // 소유주생년월일
		// dsResOut[i].getBtday();
		// // 계좌소유주명
		// dsResOut[i].getPymCustNm();
		// // 납부방법구분
		// dsResOut[i].getPymMthdCd();
		// // 납부방법구분명
		// dsResOut[i].getPymMthdNm();
		// // 은행명
		// dsResOut[i].getBankNm();
		// // 계좌번호
		// dsResOut[i].getAcctNo();
		// // 카드회사명
		// dsResOut[i].getCardNm();
		// // 카드번호
		// dsResOut[i].getCardNo();
		// // 카드유효기간
		// dsResOut[i].getCardValdEndYymm();
		// // 가상계좌번호1
		// dsResOut[i].getVrAcntNo1();
		// // 가상계좌은행명1
		// dsResOut[i].getVrBankNm1();
		// // 가상계좌번호2
		// dsResOut[i].getVrAcntNo2();
		// // 가상계좌은행명2
		// dsResOut[i].getVrBankNm2();
		// // 가상계좌번호3
		// dsResOut[i].getVrAcntNo3();
		// // 가상계좌은행명3
		// dsResOut[i].getVrBankNm3();
		// // 최종가입예약
		// dsResOut[i].getLastEntrRqstNo();
		// // 착신금지여부
		// dsResOut[i].getIcallPhbYn();
		// // 가입상품누적번호
		// dsResOut[i].getEntrSvcSeqno();
		// // 상품유형코드
		// dsResOut[i].getSvcKdCd();
		// // 가입상태변경사유상세코드
		// dsResOut[i].getEntrSttsChngRsnDtlCd();
		// // MVNO가입여부
		// dsResOut[i].getMvnoEntrYn();
		// }
	}

	/**
	 * @throws :
	 * @Part :
	 * @Author :
	 * @Component :
	 * @Primitive :
	 * @type :
	 * @Description : [CM118] 청구서반송내역조회
	 */
	public RetrieveBillRetnInfoServiceStub.ResponseBody retrieveBillRetnInfo(
			RetrieveBillRetnInfoServiceStub.DsReqInVO dsReqIn) throws Exception {
		// billAcntNo 청구계정번호 NUMBER 12 N 단수
		// billTrgtYymmFrom FROM청구년월 VARCHAR2 6 Y 단수 조회기간시작 청구년월(YYYYMM)
		// billTrgtYymmTo TO청구년월 VARCHAR2 6 Y 단수 조회기간종료 청구년월(YYYYMM)
		// nextOperatorId 처리자ID VARCHAR2 15 N 단수

		dsReqIn.setNextOperatorId(createNextOperatorId());

		// RetrieveBillRetnInfoServiceStub.DsReqInVO dsReqIn = new
		// RetrieveBillRetnInfoServiceStub.DsReqInVO();
		// dsReqIn.setBillAcntNo(billAcntNo);
		// dsReqIn.setBillTrgtYymmFrom(billTrgtYymmFrom);
		// dsReqIn.setBillTrgtYymmTo(billTrgtYymmTo);
		// dsReqIn.setNextOperatorId(nextOperatorId);

		//
		RetrieveBillRetnInfoServiceStub.RequestBody reqBody = new RetrieveBillRetnInfoServiceStub.RequestBody();
		reqBody.setDsReqInVO(dsReqIn);

		//
		RetrieveBillRetnInfoServiceStub.ESBHeader reqHeader = new RetrieveBillRetnInfoServiceStub.ESBHeader();
		reqHeader.setServiceID("CM118");
		reqHeader.setTransactionID(createTxId());
		reqHeader.setSystemID(SYSTEM_ID);
		reqHeader.setErrCode("");
		reqHeader.setErrMsg("");
		reqHeader.setReserved("");

		//
		RetrieveBillRetnInfoServiceStub.RequestRecord reqRecord = new RetrieveBillRetnInfoServiceStub.RequestRecord();
		reqRecord.setRequestBody(reqBody);
		reqRecord.setESBHeader(reqHeader);

		//
		RetrieveBillRetnInfoServiceStub.RetrieveBillRetnInfo reqIn = new RetrieveBillRetnInfoServiceStub.RetrieveBillRetnInfo();
		reqIn.setRequestRecord(reqRecord);

		//
		String host = URL;
		String path = "/CSSI/CM/RetrieveBillRetnInfo";
		String targetEndpoint = host + path;

		RetrieveBillRetnInfoServiceStub stub = null;
		RetrieveBillRetnInfoServiceStub.RetrieveBillRetnInfoResponse res = null;

		try {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(printFields(dsReqIn));

				traceRequest(stringBuffer.toString());
			}
			stub = new RetrieveBillRetnInfoServiceStub(targetEndpoint);
			res = stub.retrieveBillRetnInfo(reqIn);
		} catch (RemoteException e) {
			e.printStackTrace();
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(e.getMessage());

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(reqHeader.getErrCode(), reqHeader.getErrMsg());
		}

		RetrieveBillRetnInfoServiceStub.ResponseRecord resRecord = res.getResponseRecord();
		RetrieveBillRetnInfoServiceStub.ESBHeader resHeader = resRecord.getESBHeader();
		if (!(resHeader.getErrCode() == null || "".equals(resHeader.getErrCode()))) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(resHeader.getErrCode(), resHeader.getErrMsg());
		}

		RetrieveBillRetnInfoServiceStub.BusinessHeader bizHeader = resRecord.getBusinessHeader();
		if (bizHeader.getResultCode() == null || !"N0000".equals(bizHeader.getResultCode())) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));
				stringBuffer.append(printFields(bizHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(bizHeader.getResultCode(), bizHeader.getResultMessage());
		}

		RetrieveBillRetnInfoServiceStub.ResponseBody resBody = resRecord.getResponseBody();
		{
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
			stringBuffer.append(printFields(resHeader));
			stringBuffer.append(printFields(bizHeader));
			if (resBody != null) {
				RetrieveBillRetnInfoServiceStub.DsResOutVO[] dsResOut = resBody.getDsResOutVO();
				if (dsResOut != null) {
					for (int i = 0; i < dsResOut.length; i++) {
						stringBuffer.append(printFields(dsResOut[i]));
					}
				}
			}

			traceResponse(stringBuffer.toString());
		}

		return resBody;
		// RetrieveBillRetnInfoServiceStub.DsResOutVO[] dsResOut =
		// resBody.getDsResOutVO();
		// if(dsResOut!=null){
		// for (int i = 0; i < dsResOut.length; i++) {
		// // 청구계정번호
		// dsResOut[i].getBillAcntNo();
		// // 청구년월
		// dsResOut[i].getBillTrgtYymm();
		// // 청구서종류
		// dsResOut[i].getRetnDvNm();
		// // 반송일
		// dsResOut[i].getDlvDt();
		// // 반송사유
		// dsResOut[i].getRetnRsnNm();
		// }
		// }
	}

	/**
	 * @throws :
	 * @Part :
	 * @Author :
	 * @Component :
	 * @Primitive :
	 * @type :
	 * @Description:[CM019] 청구계정정보조회
	 */
	private RetrieveBillAcntForEntrSvcServiceStub.ResponseBody retrieveBillAcntForEntrSvc(
			RetrieveBillAcntForEntrSvcServiceStub.DsReqInVO dsReqIn) throws Exception {

		// entrNo 가입번호 VARCHAR2 10 Y 단수
		// prodNo 상품번호 VARCHAR2 12 Y 단수
		// billAcntNo 청구계정번호 VARCHAR2 40 Y 단수
		// valdEndDttm 가입이력일자 VARCHAR2 12 Y 단수
		// mode 구분 VARCHAR2 1 N 단수 "E : 가입번호 P : 전화번호 B : 청구계정번호"
		// nextOperatorId 처리자ID NUMBER 15 N 단수

		dsReqIn.setNextOperatorId(createNextOperatorId());

		// RetrieveBillAcntForEntrSvcServiceStub.DsReqInVO dsReqIn = new
		// RetrieveBillAcntForEntrSvcServiceStub.DsReqInVO();
		// dsReqIn.setEntrNo(entrNo);
		// dsReqIn.setProdNo(prodNo);
		// dsReqIn.setBillAcntNo(billAcntNo);
		// dsReqIn.setValdEndDttm(valdEndDttm);
		// dsReqIn.setMode(mode);
		// dsReqIn.setNextOperatorId(nextOperatorId);

		//
		RetrieveBillAcntForEntrSvcServiceStub.RequestBody reqBody = new RetrieveBillAcntForEntrSvcServiceStub.RequestBody();
		reqBody.setDsReqInVO(dsReqIn);

		//
		RetrieveBillAcntForEntrSvcServiceStub.ESBHeader reqHeader = new RetrieveBillAcntForEntrSvcServiceStub.ESBHeader();
		reqHeader.setServiceID("CM019");
		reqHeader.setTransactionID(createTxId());
		reqHeader.setSystemID(SYSTEM_ID);
		reqHeader.setErrCode("");
		reqHeader.setErrMsg("");
		reqHeader.setReserved("");

		//
		RetrieveBillAcntForEntrSvcServiceStub.RequestRecord reqRecord = new RetrieveBillAcntForEntrSvcServiceStub.RequestRecord();
		reqRecord.setRequestBody(reqBody);
		reqRecord.setESBHeader(reqHeader);

		//
		RetrieveBillAcntForEntrSvcServiceStub.RetrieveBillAcntForEntrSvc reqIn = new RetrieveBillAcntForEntrSvcServiceStub.RetrieveBillAcntForEntrSvc();
		reqIn.setRequestRecord(reqRecord);

		//
		String host = URL;
		String path = "/CSSI/CM/RetrieveBillAcntForEntrSvc";
		String targetEndpoint = host + path;

		RetrieveBillAcntForEntrSvcServiceStub stub = null;
		RetrieveBillAcntForEntrSvcServiceStub.RetrieveBillAcntForEntrSvcResponse res = null;

		try {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(printFields(dsReqIn));

				traceRequest(stringBuffer.toString());
			}
			stub = new RetrieveBillAcntForEntrSvcServiceStub(targetEndpoint);
			res = stub.retrieveBillAcntForEntrSvc(reqIn);
		} catch (RemoteException e) {
			e.printStackTrace();
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(e.getMessage());

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(reqHeader.getErrCode(), reqHeader.getErrMsg());
		}

		RetrieveBillAcntForEntrSvcServiceStub.ResponseRecord resRecord = res.getResponseRecord();
		RetrieveBillAcntForEntrSvcServiceStub.ESBHeader resHeader = resRecord.getESBHeader();
		if (!(resHeader.getErrCode() == null || "".equals(resHeader.getErrCode()))) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(resHeader.getErrCode(), resHeader.getErrMsg());
		}

		RetrieveBillAcntForEntrSvcServiceStub.BusinessHeader bizHeader = resRecord.getBusinessHeader();
		if (bizHeader.getResultCode() == null || !"N0000".equals(bizHeader.getResultCode())) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));
				stringBuffer.append(printFields(bizHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(resHeader.getErrCode(), resHeader.getErrMsg());
		}

		RetrieveBillAcntForEntrSvcServiceStub.ResponseBody resBody = resRecord.getResponseBody();
		{
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
			stringBuffer.append(printFields(resHeader));
			stringBuffer.append(printFields(bizHeader));
			if (resBody != null) {
				RetrieveBillAcntForEntrSvcServiceStub.DsResOutVO dsResOut = resBody.getDsResOutVO();
				if (dsResOut != null) {
					stringBuffer.append(printFields(dsResOut));
				}
			}

			traceResponse(stringBuffer.toString());
		}
		return resBody;

		// RetrieveBillAcntForEntrSvcServiceStub.DsResOutVO dsResOut =
		// resBody.getDsResOutVO();
		// if (dsResOut != null) {
		// //청구계정번호 (*)
		// dsResOut.getBillAcntNo();
		// // 청구주소누적번호
		// dsResOut.getBillAddrSeqno();
		// // 컨택포인트누적번호
		// dsResOut.getCntctPntSeqno();
		// // 납부계정번호
		// dsResOut.getPymAcntNo();
		// // 납부계정변경일자
		// dsResOut.getPymAcntChngDt();
		// // 마켓코드
		// dsResOut.getMrktCd();
		// // 청구계정최초생성일자
		// dsResOut.getBillAcntFrstCrteDttm();
		// // 청구서수령인명
		// dsResOut.getBltxtRcptManNm();
		// // 청구서유형코드 (*)
		// dsResOut.getBltxtKdCd();
		// // 청구서유형명 (*)
		// dsResOut.getBltxtKdNm();
		// // 청구서유형유효시작일자 (*)
		// dsResOut.getBltxtKdValdStrtDt();
		// // 청구서유형유효종료일자
		// dsResOut.getBltxtKdValdEndDt();
		// // 청구서수신상품번호
		// dsResOut.getBltxtRcpProdNo();
		// // 보안메일수신여부 (*)
		// dsResOut.getScurMailRcpYn();
		// // 청구이메일주소 (*)
		// dsResOut.getBillEmailAddr();
		// // 청구계정상태코드
		// dsResOut.getBillAcntSttsCd();
		// // 청구계정상태명
		// dsResOut.getBillAcntSttsNm();
		// // 청구계정상태변경일시
		// dsResOut.getBillAcntSttsChngDttm();
		// // 청구계정상태변경사유코드
		// dsResOut.getBillAcntSttsChngRsnCd();
		// // 청구계정상태변경사유명
		// dsResOut.getBillAcntSttsChngRsnNm();
		// // 청구계정상태변경사유상세코드
		// dsResOut.getBillAcntSttsChngRdtlCd();
		// // 청구계정상태변경사유상세명
		// dsResOut.getBillAcntSttsChngDtlNm();
		// // 계정잔고
		// dsResOut.getAcntBlnc();
		// // 선불계정여부
		// dsResOut.getPpayAcntYn();
		// // 청구계정그룹번호
		// dsResOut.getBillAcntGrpNo();
		// // 합봉청구허용여부
		// dsResOut.getMrgrBillPrmsYn();
		// // 청구처리제외여부
		// dsResOut.getBillPrssXclnYn();
		// // 세금계산서발행여부
		// dsResOut.getTaxBillIsueYn();
		// // 상태변경일자
		// dsResOut.getSttsChngDt();
		// // 청구처리일련번호
		// dsResOut.getBlprocSrlno();
		// // 최종청구처리일련번호
		// dsResOut.getLastBlprocSrlno();
		// // VIP청구서코드
		// dsResOut.getVipBltxtCd();
		// // 부달여부
		// dsResOut.getBilnavYn();
		// // 청구서보류사유코드
		// dsResOut.getBltxtRsvRsnCd();
		// // 청구서보류여부
		// dsResOut.getBltxtRsvRsnYn();
		// // 청구서보류유효시작일시
		// dsResOut.getBltxtRsvValdStrtDt();
		// // 청구서보류유효종료일시
		// dsResOut.getBltxtRsvValdEndDt();
		// // 청구서보류접수자
		// dsResOut.getBltxtRsvRecv();
		// // 청구서보류처리자
		// dsResOut.getBltxtRsvOprtr();
		// // 청구서중복발송여부
		// dsResOut.getBltxtDupSendYn();
		// // 대표가입번호
		// dsResOut.getRepEntrNo();
		// // 합봉청구번호
		// dsResOut.getMrgrBillNo();
		// // 청구주소고객번호
		// dsResOut.getAddrCustNo();
		// // 청구주소우편번호
		// dsResOut.getCustAddrZip();
		// // 우편번호1
		// dsResOut.getCustAddrZip1();
		// // 우편번호2
		// dsResOut.getCustAddrZip2();
		// // 청구주소주소1
		// dsResOut.getCustVilgAbvAddr();
		// // 청구주소주소2
		// dsResOut.getCustVilgBlwAddr();
		// // 주소
		// dsResOut.getCustVilgAddr();
		// // 연락처고객번호
		// dsResOut.getCntCustNo();
		// // 전화번호
		// dsResOut.getTelno();
		// }
	}

	/**
	 * @throws :
	 * @Part :
	 * @Author :
	 * @Component :
	 * @Primitive :
	 * @type :
	 * @Description : [CM020] 청구서유형조회
	 */
	@SuppressWarnings("unused")
	private RetrieveBlDcEmailCdForBltxtSvcServiceStub.ResponseBody retrieveBlDcEmailCdForBltxtSvc(
			RetrieveBlDcEmailCdForBltxtSvcServiceStub.DsReqInVO dsReqIn) throws Exception {
		// entrNo 가입번호 VARCHAR2 10 Y 단수
		// billAcntNo 청구계정번호 VARCHAR2 9 Y 단수
		// nextOperatorId 처리자ID NUMBER 15 Y 단수

		dsReqIn.setNextOperatorId(createNextOperatorId());

		// RetrieveBlDcEmailCdForBltxtSvcServiceStub.DsReqInVO dsReqIn = new
		// RetrieveBlDcEmailCdForBltxtSvcServiceStub.DsReqInVO();
		// dsReqIn.setEntrNo(entrNo);
		// dsReqIn.setBillAcntNo(billAcntNo);
		// dsReqIn.setNextOperatorId(nextOperatorId);

		//
		RetrieveBlDcEmailCdForBltxtSvcServiceStub.RequestBody reqBody = new RetrieveBlDcEmailCdForBltxtSvcServiceStub.RequestBody();
		reqBody.setDsReqInVO(dsReqIn);

		//
		RetrieveBlDcEmailCdForBltxtSvcServiceStub.ESBHeader reqHeader = new RetrieveBlDcEmailCdForBltxtSvcServiceStub.ESBHeader();
		reqHeader.setServiceID("CM020");
		reqHeader.setTransactionID(createTxId());
		reqHeader.setSystemID(SYSTEM_ID);
		reqHeader.setErrCode("");
		reqHeader.setErrMsg("");
		reqHeader.setReserved("");

		//
		RetrieveBlDcEmailCdForBltxtSvcServiceStub.RequestRecord reqRecord = new RetrieveBlDcEmailCdForBltxtSvcServiceStub.RequestRecord();
		reqRecord.setRequestBody(reqBody);
		reqRecord.setESBHeader(reqHeader);

		//
		RetrieveBlDcEmailCdForBltxtSvcServiceStub.RetrieveBlDcEmailCdForBltxtSvc reqIn = new RetrieveBlDcEmailCdForBltxtSvcServiceStub.RetrieveBlDcEmailCdForBltxtSvc();
		reqIn.setRequestRecord(reqRecord);

		//
		String host = URL;
		String path = "/CSSI/CM/RetrieveBlDcEmailCdForBltxtSvc";
		String targetEndpoint = host + path;

		RetrieveBlDcEmailCdForBltxtSvcServiceStub stub = null;
		RetrieveBlDcEmailCdForBltxtSvcServiceStub.RetrieveBlDcEmailCdForBltxtSvcResponse res = null;

		try {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(printFields(dsReqIn));

				traceRequest(stringBuffer.toString());
			}
			stub = new RetrieveBlDcEmailCdForBltxtSvcServiceStub(targetEndpoint);
			res = stub.retrieveBlDcEmailCdForBltxtSvc(reqIn);
		} catch (RemoteException e) {
			e.printStackTrace();
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(reqHeader));
				stringBuffer.append(e.getMessage());

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(reqHeader.getErrCode(), reqHeader.getErrMsg());
		}

		RetrieveBlDcEmailCdForBltxtSvcServiceStub.ResponseRecord resRecord = res.getResponseRecord();
		RetrieveBlDcEmailCdForBltxtSvcServiceStub.ESBHeader resHeader = resRecord.getESBHeader();
		if (!(resHeader.getErrCode() == null || "".equals(resHeader.getErrCode()))) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(resHeader.getErrCode(), resHeader.getErrMsg());
		}

		RetrieveBlDcEmailCdForBltxtSvcServiceStub.BusinessHeader bizHeader = resRecord.getBusinessHeader();
		if (bizHeader.getResultCode() == null || !"N0000".equals(bizHeader.getResultCode())) {
			{
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
				stringBuffer.append(printFields(resHeader));
				stringBuffer.append(printFields(bizHeader));

				traceResponse(stringBuffer.toString());
			}
			throw new ESBException(bizHeader.getResultCode(), bizHeader.getResultMessage());
		}

		RetrieveBlDcEmailCdForBltxtSvcServiceStub.ResponseBody resBody = resRecord.getResponseBody();
		{
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint", targetEndpoint));
			stringBuffer.append(printFields(resHeader));
			stringBuffer.append(printFields(bizHeader));
			if (resBody != null) {
				RetrieveBlDcEmailCdForBltxtSvcServiceStub.DsOldBillEmailOutVO dsOldBillEmailOut = resBody
						.getDsOldBillEmailOutVO();
				if (dsOldBillEmailOut != null) {
					stringBuffer.append(printFields(dsOldBillEmailOut));
				}
				RetrieveBlDcEmailCdForBltxtSvcServiceStub.DsEmailOutVO[] dsEmailOut = resBody.getDsEmailOutVO();
				if (dsEmailOut != null) {
					for (int i = 0; i < dsEmailOut.length; i++) {
						stringBuffer.append(printFields(dsEmailOut[i]));
					}
				}
				RetrieveBlDcEmailCdForBltxtSvcServiceStub.DsConfldsOutVO dsConfldsOut = resBody.getDsConfldsOutVO();
				if (dsConfldsOut != null) {
					stringBuffer.append(printFields(dsConfldsOut));
				}
			}

			traceResponse(stringBuffer.toString());
		}
		return resBody;
		// RetrieveBlDcEmailCdForBltxtSvcServiceStub.DsOldBillEmailOutVO
		// dsOldBillEmailOut = resBody
		// .getDsOldBillEmailOutVO();
		// if (dsOldBillEmailOut != null) {
		// // 고객명
		// dsOldBillEmailOut.getCustNm();
		// // 고객번호
		// dsOldBillEmailOut.getCustNo();
		// // 고객주민번호
		// dsOldBillEmailOut.getCustrnmNo();
		// // 고객구분코드
		// dsOldBillEmailOut.getCustDvCd();
		// // 고객구분명
		// dsOldBillEmailOut.getCustDvNm();
		// // 고객유형코드
		// dsOldBillEmailOut.getCustKdCd();
		// // 고객유형명
		// dsOldBillEmailOut.getCustKdNm();
		// // 사업자등록번호
		// dsOldBillEmailOut.getBsRegNo();
		// // 가입번호
		// dsOldBillEmailOut.getEntrNo();
		// // 상품번호
		// dsOldBillEmailOut.getProdNo();
		// // 통합상품코드
		// dsOldBillEmailOut.getIntgProdCd();
		// // 통합상품가입구분번호
		// dsOldBillEmailOut.getIntgProdEntrDvNo();
		// // 가입계약번호
		// dsOldBillEmailOut.getAceno();
		// // 명의자고객번호
		// dsOldBillEmailOut.getHldrCustNo();
		// // 실사용자고객번호
		// dsOldBillEmailOut.getRlusrCustNo();
		// // 청구계정번호
		// dsOldBillEmailOut.getBillAcntNo();
		// // 마켓코드
		// dsOldBillEmailOut.getMrktCd();
		// // 가입상태코드
		// dsOldBillEmailOut.getEntrSttsCd();
		// // 청구서수령인명
		// dsOldBillEmailOut.getBltxtRcptManNm();
		// // 청구서유형코드
		// dsOldBillEmailOut.getBltxtKdCd();
		// // 청구서이메일주소
		// dsOldBillEmailOut.getBillEmailAddr();
		// // 이메일청구서해지신청구분코드
		// dsOldBillEmailOut.getEmailBltxtExpryRqstDvCd();
		// // 청구서유형코드명
		// dsOldBillEmailOut.getBltxtKdCdNm();
		// // 청구서유형유효시작일자
		// dsOldBillEmailOut.getBltxtKdValdStrtDt();
		// // 청구서유형유효종료일자
		// dsOldBillEmailOut.getBltxtKdValdEndDt();
		// // 청구서수신상품번호
		// dsOldBillEmailOut.getBltxtRcpProdNo();
		// // 청구서수신상품번호
		// dsOldBillEmailOut.getBltxtRcpProdNoTm();
		// // 청구계정상태코드
		// dsOldBillEmailOut.getBillAcntSttsCd();
		// // 청구계정상태변경일시
		// dsOldBillEmailOut.getBillAcntSttsChngDttm();
		// // 청구계정상태변경사유코드
		// dsOldBillEmailOut.getBillAcntSttsChngRsnCd();
		// // 청구계정상태변경사유상세코드
		// dsOldBillEmailOut.getBillAcntSttschngRdtlCd();
		// // 계정잔고
		// dsOldBillEmailOut.getAcntBlnc();
		// // 별정업체유무
		// dsOldBillEmailOut.getMic2();
		// // 가입 선불 유무
		// dsOldBillEmailOut.getPpayEntrDvCd();
		// // 계정 선불 유무
		// dsOldBillEmailOut.getPpayAcntYn();
		// // 마켓코드
		// dsOldBillEmailOut.getBlMrktCd();
		// // 보안메일수신여부
		// dsOldBillEmailOut.getScurMailRcpYn();
		// // 청구서보류사유코드
		// dsOldBillEmailOut.getBltxtRsvRsnCd();
		// // 합봉청구번호
		// dsOldBillEmailOut.getMrgrBillNo();
		// // 이메일주소
		// dsOldBillEmailOut.getEmailAddr();
		// // 납부방법코드
		// dsOldBillEmailOut.getPymMthdCd();
		// // 할인단위 B:청구계정단위
		// dsOldBillEmailOut.getDcKd();
		// // 할인서비스코드
		// dsOldBillEmailOut.getDscntSvcCd();
		// // 할인서비스코드
		// dsOldBillEmailOut.getDscntSvcNm();
		// // 서비스코드
		// dsOldBillEmailOut.getSvcCd();
		// // 서비스명
		// dsOldBillEmailOut.getSvcNm();
		// // 고객채널코드
		// dsOldBillEmailOut.getCustChnlCd();
		// // 요금제 여부
		// dsOldBillEmailOut.getChnl200Yn();
		// // 폰할부납입여부
		// dsOldBillEmailOut.getLsInstallmentAmtInd();
		// //
		// dsOldBillEmailOut.getEmailAddrYn();
		// //
		// dsOldBillEmailOut.getRejectYn();
		// // 생년월일성별
		// dsOldBillEmailOut.getCustrnmBday();
		// // IPIN_CI
		// dsOldBillEmailOut.getIpinCi();
		// }
		//
		// RetrieveBlDcEmailCdForBltxtSvcServiceStub.DsEmailOutVO[] dsEmailOut =
		// resBody.getDsEmailOutVO();
		// if (dsEmailOut != null) {
		// for (int i = 0; i < dsEmailOut.length; i++) {
		// // 도메인
		// dsEmailOut[i].getEmail();
		// // 도메인설명
		// dsEmailOut[i].getEmailDesc();
		// }
		// }
		//
		// RetrieveBlDcEmailCdForBltxtSvcServiceStub.DsConfldsOutVO dsConfldsOut
		// = resBody.getDsConfldsOutVO();
		// if (dsConfldsOut != null) {
		// // 락모드
		// dsConfldsOut.getLockMode();
		// // 씨엔아이디
		// dsConfldsOut.getCnId();
		// // 지시
		// dsConfldsOut.getDirective();
		// // 트랜잭션모드
		// dsConfldsOut.getTransactionMode();
		// // 사용자대리점코드
		// dsConfldsOut.getUserWorkDlrCd();
		// // 사용자대리점명
		// dsConfldsOut.getUserWorkDlrNm();
		// // 실행날짜
		// dsConfldsOut.getRunDate();
		// // 실행날짜
		// dsConfldsOut.getRunDateDtm();
		// // 가입번호
		// dsConfldsOut.getEntrNo();
		// // 가입업데이트 날짜
		// dsConfldsOut.getEntrSysUpdateDate();
		// // 가입업데이트스탬프
		// dsConfldsOut.getEntrDlUpdateStamp();
		// // 계약번호
		// dsConfldsOut.getAceno();
		// // 가입계약업데이트날짜
		// dsConfldsOut.getCntcSysUpdateDate();
		// // 가입계약업데이트스탬프
		// dsConfldsOut.getCntcDlUpdateStamp();
		// // 청구계정번호
		// dsConfldsOut.getBillAcntNo();
		// // 청구업데이트날짜
		// dsConfldsOut.getBillSysUpdateDate();
		// // 청구업데이트스탬프
		// dsConfldsOut.getBillDlUpdateStamp();
		// }
	}

	// /**
	// * @throws :
	// * @Part :
	// * @Author :
	// * @Component :
	// * @Primitive :
	// * @type :
	// * @Description : [SM066] 서비스 예약 등록
	// */
	// private SaveSvcRsvServiceStub.ResponseBody
	// saveSvcRsv(SaveSvcRsvServiceStub.DsAsgnNoListInVO[] dsAsgnNoListIn,
	// SaveSvcRsvServiceStub.DsChkFtrInVO[] dsChkFtrIn,
	// SaveSvcRsvServiceStub.DsChkItemInVO[] dsChkItemIn,
	// SaveSvcRsvServiceStub.DsChkSvcRsvInVO[] dsChkSvcRsvIn,
	// SaveSvcRsvServiceStub.DsConfldsInVO[] dsConfldsIn,
	// SaveSvcRsvServiceStub.DsSaveSvcInVO[] dsSaveSvcIn) throws ESBException {
	// // dsAsgnNoList
	// // entrNo 가입번호 VARCHAR2 10 Y 복수 -
	// // dscntStrtDttm 시작일시 VARCHAR2 14 Y 복수 -
	// // asgnNoSeqno 지정번호일련번호 NUMBER 10 Y 복수 -
	// // asgnDscntTelno 지정대상번호 VARCHAR2 16 Y 복수 -
	// // dscntEndDttm 할인종료일시 VARCHAR2 14 Y 복수 -
	// // dscntKdDtlCd 할인유형코드 VARCHAR2 3 Y 복수 -
	// // dscntSvcCd 할인서비스코드 VARCHAR2 10 Y 복수 -
	// // hposSvcCd 할인서비스상위코드 VARCHAR2 10 Y 복수 -
	// // ctn 지정상품번호 VARCHAR2 20 Y 복수 -
	// // svcGrpSeqno 서비스그룹 일련번호 NUMBER 10 Y 복수 -
	// // svcRsvSttsCd 서비스 예약 상태 코드 VARCHAR2 3 Y 복수 -
	// // hposSvcNm 상위서비스명 VARCHAR2 50 Y 복수 -
	// // _rowStatus 처리 모드 구분 VARCHAR2 1 Y 복수 -
	// // nextOperatorId 처리자ID STRING 15 N 복수
	//
	// // dsChkFtr
	// // svcCd 서비스코드 VARCHAR2 10 Y 복수 사용자입력 서비스 요소에 한해 입력받는다
	// // ftrCd 서비스속성코드 VARCHAR2 10 Y 복수
	// // ftrNm 속성명 VARCHAR2 30 Y 복수
	// // ftrValdStrtDt 속성시작일자 VARCHAR2 8 Y 복수
	// // ftrValdEndDt 속성종료일자 VARCHAR2 8 Y 복수
	// // varParam 속성파람value VARCHAR2 200 Y 복수
	// // ftrVarDtlSeqno 속성일련번호 NUMBER 10 Y 복수
	// // entrSvcSeqno 서비스일련번호 NUMBER 10 Y 복수
	// // ftrMode 처리모드 VARCHAR2 1 Y 복수 I(등록),U(만기),N(미변경)
	// // ftrSubMode 처리세부모드 VARCHAR2 1 Y 복수 I(등록),U(만기),E(파람벨류변경),N(미변경)
	// // nextOperatorId 처리자ID STRING 15 N 복수
	//
	// // dsChkItem
	// // devMode 등록,만기 여부 구분 VARCHAR2 1 Y 복수 I:등록, U:만기
	// // itemId 단말기모델코드 VARCHAR2 15 Y 복수 -
	// // manfSerialNo 단말기모델일련번호 VARCHAR2 20 Y 복수 -
	// // devChngRsnCd 단말기 변경사유 코드 VARCHAR2 3 Y 복수 신규 : PC
	// // eventCode 작업처리구분 VARCHAR2 3 Y 복수 -
	// // itemStatus 단말기상태코드 VARCHAR2 3 Y 복수 -
	// // casNo DMB CAS NO VARCHAR2 0 Y 복수 -
	// // newTrxCd event_code_sims(as-is) VARCHAR2 5 Y 복수 -
	// // chipUseYn chipUseYn VARCHAR2 1 Y 복수 -
	// // nextOperatorId 처리자ID STRING 15 N 복수
	//
	// // dsChkSvcRsv
	// // svcCd 서비스코드 VARCHAR2 10 Y
	// // svcNm 서비스명 VARCHAR2 30 Y
	// // entrSvcSeqno 서비스일련번호 NUMBER 10 Y
	// // hposSvcCd 상위서비스코드 VARCHAR2 10 Y
	// // hposEntrSvcSeqno 상위서비스일련번호 NUMBER 10 Y
	// // svcKdCd 서비스유형 VARCHAR2 3 Y
	// // svcAplyLvlCd 서비스레벨코드 VARCHAR2 3 Y
	// // svcFrstStrtDttm 가입자 서비스시작일시 VARCHAR2 14 Y
	// // svcStrtRgstDlrCd 등록대리점 VARCHAR2 7 Y
	// // svcStrtDttm 서비스시작일시 VARCHAR2 14 Y
	// // svcEndDttm 서비스종료일시 VARCHAR2 14 Y
	// // svcDutyUseMnthCnt 의무사용개월수 NUMBER 3 Y
	// // svcDutyUseDvCd 의무사용구분코드 VARCHAR2 3 Y
	// // svcDutyUseStrtDt 의무사용시작일자 VARCHAR2 8 Y
	// // svcDutyUseEndDt 의무사용종료일자 VARCHAR2 8 Y
	// // saleEmpno 판매사번 VARCHAR2 8 Y
	// // svcRelsDvCd 서비스 관련 코드 VARCHAR2 3 Y
	// // ndblCvrtSvcCd NDBL 서비스 관련 코드 VARCHAR2 3 Y
	// // inventoryItemId pdh상품구조상의 코드 NUMBER 15 Y
	// // organizationId pdh상품구조상의 조직코드 NUMBER 15 Y
	// // revisionId pdh상품구조상의 버전 코드 NUMBER 15 Y
	// // svcMode 처리모드 VARCHAR2 3 Y
	// // svcSubMode 처리세부모드 VARCHAR2 3 Y
	// // _rowStatus 처리 모드 구분 VARCHAR2 1 Y
	// // rsvOprtr 예약처리자 VARCHAR2 8 Y
	// // susAftRsvYn 일시정지후예약여부 VARCHAR2 1 Y
	// // rsvRcptDvCd 예약접수구분코드 VARCHAR2 3 Y
	// // rsvSvcChngDvCd 예약서비스변경구분코드 VARCHAR2 3 Y
	// // bfrPpCd 이전요금제코드 VARCHAR2 10 Y
	// // rsvDttm 예약일시 VARCHAR2 14 Y
	// // rsvDlrCd 예약대리점 VARCHAR2 8 Y
	// // svcRsvSttsCd 서비스예약상태코드 VARCHAR2 3 Y
	// // rmks rmks VARCHAR2 30 Y
	// // nextOperatorId 처리자ID STRING 15 N
	//
	// // dsConflds
	// // directive 디렉티브 VARCHAR2 1 Y 복수 -
	// // runDate 작업일자 VARCHAR2 8 Y 복수 -
	// // runDateDtm 작업일시 VARCHAR2 14 Y 복수 -
	// // transactionMode 트랙잰션모드 VARCHAR2 1 Y 복수 -
	// // entrNo 가입번호 VARCHAR2 10 Y 복수 -
	// // entrDlUpdateStamp 가입변경stamp VARCHAR2 4 Y 복수 -
	// // entrSysUpdateDate 가입변경date VARCHAR2 14 Y 복수 -
	// // aceno 가입계약번호 VARCHAR2 10 Y 복수 -
	// // cntcDlUpdateStamp 가입계약변경stamp VARCHAR2 4 Y 복수 -
	// // cntcSysUpdateDate 가입계약변경date VARCHAR2 14 Y 복수 -
	// // billAcntNo 청구계정번호 VARCHAR2 10 Y 복수 -
	// // billDlUpdateStamp 청구계정변경stamp VARCHAR2 4 Y 복수
	// // billSysUpdateDate 청구계정변경date VARCHAR2 14 Y 복수
	// // cnId callCtn VARCHAR2 12 Y 복수
	// // lockMode Lock모드 VARCHAR2 1 Y 복수
	// // userWorkDlrCd 작업대리점 VARCHAR2 6 Y 복수
	// // userWorkDlrNm 작업대리점명 VARCHAR2 30 Y 복수
	// // nextOperatorId 처리자ID STRING 15 N 복수
	//
	// // dsSaveSvc
	// // billAcntNo 청구계정번호 NUMBER 10 N 복수
	// // entrNo 가입번호 NUMBER 10 N 복수
	// // prodNo 상품번호 VARCHAR2 20 N 복수
	// // hldrCustNo 고객번호 VARCHAR2 20 N 복수
	// // saleEmpno 판매사번 VARCHAR2 8 Y 복수
	// // svcDutyUseMnthCnt 의무사용개월수 NUMBER 3 Y 복수
	// // svcDutyUseDvCd 의무사용구분코드 VARCHAR2 3 Y 복수
	// // svcDutyUseStrtDt 의무사용시작일자 VARCHAR2 8 Y 복수
	// // svcDutyUseEndDt 의무사용종료일자 VARCHAR2 8 Y 복수
	// // rgstDlrCd 작업대리점 VARCHAR2 8 N 복수
	// // rjnDt 재가입일 VARCHAR2 8 Y 복수
	// // runDttm 작업일 VARCHAR2 14 N 복수
	// // noGuidPrcType 번호안내작업구분 VARCHAR2 3 Y 복수
	// // prcType 작업구분 VARCHAR2 3 N 복수
	// // prcSubType 작업 세부 구분 VARCHAR2 3 Y 복수
	// // prcMode 프로세스구분 VARCHAR2 3 Y 복수
	// // prcSubMode 프로세스세부구분 VARCHAR2 3 N 복수
	// // posCd 개통pos VARCHAR2 7 N 복수
	// // rsalePosCd 실판매pos VARCHAR2 7 Y 복수
	// // newTrxYn TRX IND VARCHAR2 1 Y 복수
	// // itemTrx itemTrx(기변시 사용) VARCHAR2 3 Y 복수
	// // svcCd 서비스코드 VARCHAR2 10 Y 복수
	// // svcNm 서비스명 VARCHAR2 30 Y 복수
	// // kongUppChrgAmt 콩상한금액 NUMBER 13 Y 복수
	// // userMemo 사용자 메모 VARCHAR2 300 Y 복수
	// // entrSttsCd 가입자상태코드 VARCHAR2 1 Y 복수
	// // nextOperatorId 처리자ID STRING 15 N 복수
	//
	// String nextOperatorId = createNextOperatorId();
	//
	// dsAsgnNoListIn[0].setNextOperatorId(nextOperatorId);
	// dsChkFtrIn[0].setNextOperatorId(nextOperatorId);
	// dsChkItemIn[0].setNextOperatorId(nextOperatorId);
	// dsChkSvcRsvIn[0].setNextOperatorId(nextOperatorId);
	// dsConfldsIn[0].setNextOperatorId(nextOperatorId);
	// dsChkSvcRsvIn[0].setNextOperatorId(nextOperatorId);
	// dsSaveSvcIn[0].setNextOperatorId(nextOperatorId);
	//
	// // SaveSvcRsvServiceStub.DsAsgnNoListInVO[] dsAsgnNoListIn = new
	// // SaveSvcRsvServiceStub.DsAsgnNoListInVO[1];
	// // dsAsgnNoListIn[0] = new SaveSvcRsvServiceStub.DsAsgnNoListInVO();
	// // dsAsgnNoListIn[0].setEntrNo(entrNo_);
	// // dsAsgnNoListIn[0].setDscntStrtDttm(dscntStrtDttm_);
	// // dsAsgnNoListIn[0].setAsgnNoSeqno(asgnNoSeqno_);
	// // dsAsgnNoListIn[0].setAsgnDscntTelno(asgnDscntTelno_);
	// // dsAsgnNoListIn[0].setDscntEndDttm(dscntEndDttm_);
	// // dsAsgnNoListIn[0].setDscntKdDtlCd(dscntKdDtlCd_);
	// // dsAsgnNoListIn[0].setDscntSvcCd(dscntSvcCd_);
	// // dsAsgnNoListIn[0].setHposSvcCd(hposSvcCd_);
	// // dsAsgnNoListIn[0].setCtn(ctn_);
	// // dsAsgnNoListIn[0].setSvcGrpSeqno(svcGrpSeqno_);
	// // dsAsgnNoListIn[0].setSvcRsvSttsCd(svcRsvSttsCd_);
	// // dsAsgnNoListIn[0].setHposSvcNm(hposSvcNm_);
	// // dsAsgnNoListIn[0].set_rowStatus(_rowStatus_);
	// // dsAsgnNoListIn[0].setNextOperatorId(nextOperatorId_);
	//
	// // SaveSvcRsvServiceStub.DsChkFtrInVO[] dsChkFtrIn = new
	// // SaveSvcRsvServiceStub.DsChkFtrInVO[1];
	// // dsChkFtrIn[0] = new SaveSvcRsvServiceStub.DsChkFtrInVO();
	// // dsChkFtrIn[0].setSvcCd(svcCd__);
	// // dsChkFtrIn[0].setFtrCd(ftrCd__);
	// // dsChkFtrIn[0].setFtrNm(ftrNm__);
	// // dsChkFtrIn[0].setFtrValdStrtDt(ftrValdStrtDt__);
	// // dsChkFtrIn[0].setFtrValdEndDt(ftrValdEndDt__);
	// // dsChkFtrIn[0].setVarParam(varParam__);
	// // dsChkFtrIn[0].setFtrVarDtlSeqno(ftrVarDtlSeqno__);
	// // dsChkFtrIn[0].setEntrSvcSeqno(entrSvcSeqno__);
	// // dsChkFtrIn[0].setFtrMode(ftrMode__);
	// // dsChkFtrIn[0].setFtrSubMode(ftrSubMode__);
	// // dsChkFtrIn[0].setNextOperatorId(nextOperatorId__);
	//
	// // SaveSvcRsvServiceStub.DsChkItemInVO[] dsChkItemIn = new
	// // SaveSvcRsvServiceStub.DsChkItemInVO[1];
	// // dsChkItemIn[0] = new SaveSvcRsvServiceStub.DsChkItemInVO();
	// // dsChkItemIn[0].setDevMode(devMode___);
	// // dsChkItemIn[0].setItemId(itemId___);
	// // dsChkItemIn[0].setManfSerialNo(manfSerialNo___);
	// // dsChkItemIn[0].setDevChngRsnCd(devChngRsnCd___);
	// // dsChkItemIn[0].setEventCode(eventCode___);
	// // dsChkItemIn[0].setItemStatus(itemStatus___);
	// // dsChkItemIn[0].setCasNo(casNo___);
	// // dsChkItemIn[0].setNewTrxCd(newTrxCd___);
	// // dsChkItemIn[0].setChipUseYn(chipUseYn___);
	// // dsChkItemIn[0].setNextOperatorId(nextOperatorId___);
	//
	// // SaveSvcRsvServiceStub.DsChkSvcRsvInVO[] dsChkSvcRsvIn = new
	// // SaveSvcRsvServiceStub.DsChkSvcRsvInVO[1];
	// // dsChkSvcRsvIn[0] = new SaveSvcRsvServiceStub.DsChkSvcRsvInVO();
	// // dsChkSvcRsvIn[0].setSvcCd(svcCd____);
	// // dsChkSvcRsvIn[0].setSvcNm(svcNm____);
	// // dsChkSvcRsvIn[0].setEntrSvcSeqno(entrSvcSeqno____);
	// // dsChkSvcRsvIn[0].setHposSvcCd(hposSvcCd____);
	// // dsChkSvcRsvIn[0].setHposEntrSvcSeqno(hposEntrSvcSeqno____);
	// // dsChkSvcRsvIn[0].setSvcKdCd(svcKdCd____);
	// // dsChkSvcRsvIn[0].setSvcAplyLvlCd(svcAplyLvlCd____);
	// // dsChkSvcRsvIn[0].setSvcFrstStrtDttm(svcFrstStrtDttm____);
	// // dsChkSvcRsvIn[0].setSvcStrtRgstDlrCd(svcStrtRgstDlrCd____);
	// // dsChkSvcRsvIn[0].setSvcStrtDttm(svcStrtDttm____);
	// // dsChkSvcRsvIn[0].setSvcEndDttm(svcEndDttm____);
	// // dsChkSvcRsvIn[0].setSvcDutyUseMnthCnt(svcDutyUseMnthCnt____);
	// // dsChkSvcRsvIn[0].setSvcDutyUseDvCd(svcDutyUseDvCd____);
	// // dsChkSvcRsvIn[0].setSvcDutyUseStrtDt(svcDutyUseStrtDt____);
	// // dsChkSvcRsvIn[0].setSvcDutyUseEndDt(svcDutyUseEndDt____);
	// // dsChkSvcRsvIn[0].setSaleEmpno(saleEmpno____);
	// // dsChkSvcRsvIn[0].setSvcRelsDvCd(svcRelsDvCd____);
	// // dsChkSvcRsvIn[0].setNdblCvrtSvcCd(ndblCvrtSvcCd____);
	// // dsChkSvcRsvIn[0].setInventoryItemId(inventoryItemId____);
	// // dsChkSvcRsvIn[0].setOrganizationId(organizationId____);
	// // dsChkSvcRsvIn[0].setRevisionId(revisionId____);
	// // dsChkSvcRsvIn[0].setSvcMode(svcMode____);
	// // dsChkSvcRsvIn[0].setSvcSubMode(svcSubMode____);
	// // dsChkSvcRsvIn[0].set_rowStatus(_rowStatus____);
	// // dsChkSvcRsvIn[0].setRsvOprtr(rsvOprtr____);
	// // dsChkSvcRsvIn[0].setSusAftRsvYn(susAftRsvYn____);
	// // dsChkSvcRsvIn[0].setRsvRcptDvCd(rsvRcptDvCd____);
	// // dsChkSvcRsvIn[0].setRsvSvcChngDvCd(rsvSvcChngDvCd____);
	// // dsChkSvcRsvIn[0].setBfrPpCd(bfrPpCd____);
	// // dsChkSvcRsvIn[0].setRsvDttm(rsvDttm____);
	// // dsChkSvcRsvIn[0].setRsvDlrCd(rsvDlrCd____);
	// // dsChkSvcRsvIn[0].setSvcRsvSttsCd(svcRsvSttsCd____);
	// // dsChkSvcRsvIn[0].setRmks(rmks____);
	// // dsChkSvcRsvIn[0].setNextOperatorId(nextOperatorId____);
	//
	// // SaveSvcRsvServiceStub.DsConfldsInVO[] dsConfldsIn = new
	// // SaveSvcRsvServiceStub.DsConfldsInVO[1];
	// // dsConfldsIn[0] = new SaveSvcRsvServiceStub.DsConfldsInVO();
	// // dsConfldsIn[0].setDirective(directive_____);
	// // dsConfldsIn[0].setRunDate(runDate_____);
	// // dsConfldsIn[0].setRunDateDtm(runDateDtm_____);
	// // dsConfldsIn[0].setTransactionMode(transactionMode_____);
	// // dsConfldsIn[0].setEntrNo(entrNo_____);
	// // dsConfldsIn[0].setEntrDlUpdateStamp(entrDlUpdateStamp_____);
	// // dsConfldsIn[0].setEntrSysUpdateDate(entrSysUpdateDate_____);
	// // dsConfldsIn[0].setAceno(aceno_____);
	// // dsConfldsIn[0].setCntcDlUpdateStamp(cntcDlUpdateStamp_____);
	// // dsConfldsIn[0].setCntcSysUpdateDate(cntcSysUpdateDate_____);
	// // dsConfldsIn[0].setBillAcntNo(billAcntNo_____);
	// // dsConfldsIn[0].setBillDlUpdateStamp(billDlUpdateStamp_____);
	// // dsConfldsIn[0].setBillSysUpdateDate(billSysUpdateDate_____);
	// // dsConfldsIn[0].setCnId(cnId_____);
	// // dsConfldsIn[0].setLockMode(lockMode_____);
	// // dsConfldsIn[0].setUserWorkDlrCd(userWorkDlrCd_____);
	// // dsConfldsIn[0].setUserWorkDlrNm(userWorkDlrNm_____);
	// // dsConfldsIn[0].setNextOperatorId(nextOperatorId_____);
	//
	// // SaveSvcRsvServiceStub.DsSaveSvcInVO[] dsSaveSvcIn = new
	// // SaveSvcRsvServiceStub.DsSaveSvcInVO[1];
	// // dsSaveSvcIn[0] = new SaveSvcRsvServiceStub.DsSaveSvcInVO();
	// // dsSaveSvcIn[0].setBillAcntNo(billAcntNo______);
	// // dsSaveSvcIn[0].setEntrNo(entrNo______);
	// // dsSaveSvcIn[0].setProdNo(prodNo______);
	// // dsSaveSvcIn[0].setHldrCustNo(hldrCustNo______);
	// // dsSaveSvcIn[0].setSaleEmpno(saleEmpno______);
	// // dsSaveSvcIn[0].setSvcDutyUseMnthCnt(svcDutyUseMnthCnt______);
	// // dsSaveSvcIn[0].setSvcDutyUseDvCd(svcDutyUseDvCd______);
	// // dsSaveSvcIn[0].setSvcDutyUseStrtDt(svcDutyUseStrtDt______);
	// // dsSaveSvcIn[0].setSvcDutyUseEndDt(svcDutyUseEndDt______);
	// // dsSaveSvcIn[0].setRgstDlrCd(rgstDlrCd______);
	// // dsSaveSvcIn[0].setRjnDt(rjnDt______);
	// // dsSaveSvcIn[0].setRunDttm(runDttm______);
	// // dsSaveSvcIn[0].setNoGuidPrcType(noGuidPrcType______);
	// // dsSaveSvcIn[0].setPrcType(prcType______);
	// // dsSaveSvcIn[0].setPrcSubType(prcSubType______);
	// // dsSaveSvcIn[0].setPrcMode(prcMode______);
	// // dsSaveSvcIn[0].setPrcSubMode(prcSubMode______);
	// // dsSaveSvcIn[0].setPosCd(posCd______);
	// // dsSaveSvcIn[0].setRsalePosCd(rsalePosCd______);
	// // dsSaveSvcIn[0].setNewTrxYn(newTrxYn______);
	// // dsSaveSvcIn[0].setItemTrx(itemTrx______);
	// // dsSaveSvcIn[0].setSvcCd(svcCd______);
	// // dsSaveSvcIn[0].setSvcNm(svcNm______);
	// // dsSaveSvcIn[0].setKongUppChrgAmt(kongUppChrgAmt______);
	// // dsSaveSvcIn[0].setUserMemo(userMemo______);
	// // dsSaveSvcIn[0].setEntrSttsCd(entrSttsCd______);
	// // dsSaveSvcIn[0].setNextOperatorId(nextOperatorId______);
	//
	// //
	// SaveSvcRsvServiceStub.RequestBody reqBody = new
	// SaveSvcRsvServiceStub.RequestBody();
	// reqBody.setDsAsgnNoListInVO(dsAsgnNoListIn);
	// reqBody.setDsChkFtrInVO(dsChkFtrIn);
	// reqBody.setDsChkItemInVO(dsChkItemIn);
	// reqBody.setDsChkSvcRsvInVO(dsChkSvcRsvIn);
	// reqBody.setDsConfldsInVO(dsConfldsIn);
	// reqBody.setDsSaveSvcInVO(dsSaveSvcIn);
	//
	// //
	// SaveSvcRsvServiceStub.ESBHeader reqHeader = new
	// SaveSvcRsvServiceStub.ESBHeader();
	// reqHeader.setServiceID("SM066");
	// reqHeader.setTransactionID(createTxId());
	// reqHeader.setSystemID(SYSTEM_ID);
	// reqHeader.setErrCode("");
	// reqHeader.setErrMsg("");
	// reqHeader.setReserved("");
	//
	// //
	// SaveSvcRsvServiceStub.RequestRecord reqRecord = new
	// SaveSvcRsvServiceStub.RequestRecord();
	// reqRecord.setRequestBody(reqBody);
	// reqRecord.setESBHeader(reqHeader);
	//
	// //
	// SaveSvcRsvServiceStub.SaveSvcRsv reqIn = new
	// SaveSvcRsvServiceStub.SaveSvcRsv();
	// reqIn.setRequestRecord(reqRecord);
	//
	// //
	// String host = URL;
	// String path = "/CSSI/SM/SaveSvcRsv";
	// String targetEndpoint = host + path;
	//
	// SaveSvcRsvServiceStub stub = null;
	// SaveSvcRsvServiceStub.SaveSvcRsvResponse res = null;
	//
	// try {
	// {
	// StringBuffer stringBuffer = new StringBuffer();
	// stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint",
	// targetEndpoint));
	// stringBuffer.append(printFields(reqHeader));
	//
	// stringBuffer.append(printFields(dsAsgnNoListIn[0]));
	// stringBuffer.append(printFields(dsChkFtrIn[0]));
	// stringBuffer.append(printFields(dsChkItemIn[0]));
	// stringBuffer.append(printFields(dsChkSvcRsvIn[0]));
	// stringBuffer.append(printFields(dsConfldsIn[0]));
	// stringBuffer.append(printFields(dsChkSvcRsvIn[0]));
	// stringBuffer.append(printFields(dsSaveSvcIn[0]));
	//
	// traceWriter.trace(CCSSUtil.getCtn(), TraceConst.NODE_ID_WAS,
	// TraceConst.NODE_ID_ESB,
	// TraceConst.PROTOCOL_SOAP, stringBuffer.toString());
	// }
	// stub = new SaveSvcRsvServiceStub(targetEndpoint);
	// res = stub.saveSvcRsv(reqIn);
	// } catch (RemoteException e) {
	// e.printStackTrace();
	// {
	// StringBuffer stringBuffer = new StringBuffer();
	// stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint",
	// targetEndpoint));
	// stringBuffer.append(printFields(reqHeader));
	// stringBuffer.append(e.getMessage());
	//
	// traceWriter.trace(CCSSUtil.getCtn(), TraceConst.NODE_ID_ESB,
	// TraceConst.NODE_ID_WAS,
	// TraceConst.PROTOCOL_SOAP, stringBuffer.toString());
	// }
	// throw new ESBException(reqHeader.getErrCode(), reqHeader.getErrMsg());
	// }
	//
	// SaveSvcRsvServiceStub.ResponseRecord resRecord = res.getResponseRecord();
	// SaveSvcRsvServiceStub.ESBHeader resHeader = resRecord.getESBHeader();
	// if (!(resHeader.getErrCode() == null ||
	// "".equals(resHeader.getErrCode()))) {
	// {
	// StringBuffer stringBuffer = new StringBuffer();
	// stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint",
	// targetEndpoint));
	// stringBuffer.append(printFields(resHeader));
	//
	// traceWriter.trace(CCSSUtil.getCtn(), TraceConst.NODE_ID_ESB,
	// TraceConst.NODE_ID_WAS,
	// TraceConst.PROTOCOL_SOAP, stringBuffer.toString());
	// }
	// throw new ESBException(resHeader.getErrCode(), resHeader.getErrMsg());
	// }
	//
	// SaveSvcRsvServiceStub.BusinessHeader bizHeader =
	// resRecord.getBusinessHeader();
	// if (bizHeader.getResultCode() == null ||
	// !"N0000".equals(bizHeader.getResultCode())) {
	// {
	// StringBuffer stringBuffer = new StringBuffer();
	// stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint",
	// targetEndpoint));
	// stringBuffer.append(printFields(resHeader));
	// stringBuffer.append(printFields(bizHeader));
	//
	// traceWriter.trace(CCSSUtil.getCtn(), TraceConst.NODE_ID_ESB,
	// TraceConst.NODE_ID_WAS,
	// TraceConst.PROTOCOL_SOAP, stringBuffer.toString());
	// }
	// throw new ESBException(bizHeader.getResultCode(),
	// bizHeader.getResultMessage());
	// }
	//
	// SaveSvcRsvServiceStub.ResponseBody resBody = resRecord.getResponseBody();
	// {
	// StringBuffer stringBuffer = new StringBuffer();
	// stringBuffer.append(String.format("[%32s] %s\n", "targetEndpoint",
	// targetEndpoint));
	// stringBuffer.append(printFields(resHeader));
	// stringBuffer.append(printFields(bizHeader));
	// if (resBody != null) {
	// SaveSvcRsvServiceStub.LDataRsltOutVO dsResOut =
	// resBody.getLDataRsltOutVO();
	// if (dsResOut != null) {
	// stringBuffer.append(printFields(dsResOut));
	// }
	// }
	//
	// traceWriter.trace(CCSSUtil.getCtn(), TraceConst.NODE_ID_ESB,
	// TraceConst.NODE_ID_WAS,
	// TraceConst.PROTOCOL_SOAP, stringBuffer.toString());
	// }
	// return resBody;
	// // SaveSvcRsvServiceStub.LDataRsltOutVO dsResOut =
	// // resBody.getLDataRsltOutVO();
	// // if (dsResOut != null) {
	// // //
	// // dsResOut.getResults();
	// // }
	// }

	/**
	 * @Part :
	 * @Author :
	 * @Component :
	 * @Primitive :
	 * @type :
	 * @Description : generate transaction id - 20110305090101999(17자리) +
	 *              1234567(7자리)
	 */
	public static String createTxId() {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String time = simpleDateFormat.format(date);
		String random = String.format("%07d", (new Random().nextInt(9999999)));
		return time + random;
	}

	/**
	 * @Part :
	 * @Author :
	 * @Component :
	 * @Primitive :
	 * @type :
	 * @Description :
	 */
	private String createNextOperatorId() {
		return String.valueOf(System.currentTimeMillis());
	}

	//

	/**
	 * 가입정보 - 상품명, 가입일자, 상품 유효기간
	 * 
	 * 예) 상품(요금제)명 - LG U+ 업무용 (LTE), 약정기간 - 무약정, 개통일자 - 20151124, 해지일자 -
	 * 99991231
	 * 
	 * @param no
	 * @param svcCd
	 * @return
	 */
	@SuppressWarnings("unused")
	public SubsInfoVO getSubsInfo(String no, String svcCd) {
		try {
			
			// type 구분 VARCHAR2 1 N 단수 "A:가입번호,B:결합번호,C:청구계정번호,D:상품번호,E:홈코드번호"
			// no 번호 VARCHAR2 20 N 단수
			RetrieveCustSvcEntrInfoBDServiceStub.DsInputInVO dsReqIn = new RetrieveCustSvcEntrInfoBDServiceStub.DsInputInVO();
			dsReqIn.setType("A");
			dsReqIn.setNo(no);
			
			RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO found = null;
			RetrieveCustSvcEntrInfoBDServiceStub.ResponseBody resBody = retrieveCustSvcEntrInfoBD(dsReqIn);
			RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO[] dsOutputOut = resBody.getDsOutputOutVO();

			for (int i = 0; i < dsOutputOut.length; i++) {
				// if (svcCd.equals(dsOutputOut[i].getSvcCd())) {
				found = dsOutputOut[i];

				logger.debug("retrieveCustSvcEntrInfoBD in getSubsInfo({}, {}) \n{}", no, svcCd,
						printFields(dsOutputOut[i]));
				break;
				// }
			}

			if (found == null) {
				logger.debug("retrieveCustSvcEntrInfoBD in getSubsInfo({}, {}) not found", no, svcCd);
				return null;
			}
			
			SubsInfoVO subsInfoVO = new SubsInfoVO();
			subsInfoVO.setSvcNm(found.getSvcNm());// 상품(요금제)명
			subsInfoVO.setFxedFctr1(found.getFxedFctr1());// 약정기간
			subsInfoVO.setSvcFrstStrtDttm(found.getSvcFrstStrtDttm());// 개통일자
			subsInfoVO.setSvcEndDttm(found.getSvcEndDttm());// 해지일자
			subsInfoVO.setPymMthdCd(found.getPymMthdCd());// 납부구분코드
			subsInfoVO.setSvcCd(found.getSvcCd());//요금제코드
			subsInfoVO.setMinNo(found.getMinNo());//전화번호
			subsInfoVO.setEntrSttsCd(found.getEntrSttsCd()); //가입상태코드

			logger.debug("getSubsInfo({}, {}) is \n{}", no, svcCd, subsInfoVO.toString());

			return subsInfoVO;
		} catch (ESBException e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 가입정보 - 총 데이터, 잔여 데이터
	 * 
	 * 예) 제공량 - "", 사용량 - ""
	 * 
	 * @param no
	 * @param svcCd
	 * @param callYm
	 * @return
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@SuppressWarnings("unused")
	public AmountDataVO getAmountData(String no, String svcCd, String callYm, String ip) throws IOException, ParseException {
		try {
			// type 구분 VARCHAR2 1 N 단수 "A:가입번호,B:결합번호,C:청구계정번호,D:상품번호,E:홈코드번호"
			// no 번호 VARCHAR2 20 N 단수
			RetrieveCustSvcEntrInfoBDServiceStub.DsInputInVO dsReqIn = new RetrieveCustSvcEntrInfoBDServiceStub.DsInputInVO();
			dsReqIn.setType("A");
			dsReqIn.setNo(no);

			RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO found = null;
			RetrieveCustSvcEntrInfoBDServiceStub.ResponseBody resBody = retrieveCustSvcEntrInfoBD(dsReqIn);
			RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO[] dsOutputOut = resBody.getDsOutputOutVO();

			for (int i = 0; i < dsOutputOut.length; i++) {
				// if (svcCd.equals(dsOutputOut[i].getSvcCd())) {
				found = dsOutputOut[i];

				logger.debug("retrieveCustSvcEntrInfoBD in getAmountData({}, {}, {}) \n{}", no, svcCd, callYm,
						printFields(dsOutputOut[i]));
				break;
				// }
			}

			if (found == null) {
				logger.debug("getAmountData({}, {}, {}) not found", no, svcCd, callYm);
				return null;
			}
			
			// callYm 통화년월 VARCHAR2 20 Y N 단수
			// prodNo 상품번호 NUMBER 12 Y N 단수
			// aceNo 가입계약번호 NUMBER 12 Y N 단수
			// entrNo 가입번호 NUMBER 30 Y N 단수 default 0
			// billAcctNo 청구계정번호 NUMBER 15 Y N 단수 default 0
			// wrkTypCd 업무구분코드 VARCHAR2 1 N 단수 1. 무료잔여량, 2. 월별사용량
			// 2차 ucube 수정
			
			// billAcntNo 청구계정번호 VARCHAR2 12 N 단수
			// billTrgtYymm 청구대상년월 VARCHAR2 6 N 단수
			AmountDataVO amountDataVO = new AmountDataVO();
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String formatedNow = dateFormat.format(now);

			Date systemNow = dateFormat.parse(formatedNow);
			Date configNow = dateFormat.parse(apimOpen1st);
			
			//if ("Y".equals(apimOpen1st)) {
			if (systemNow.after(configNow)) {
				String url;
				url = apimFallbackProc.getApimgwUrl();
				//AccessToken 파일 체크 메소드
				String fileAccessToken = getFileAccessToken();
				//logger.info("fileAccessToken:::{()}",fileAccessToken);
				String oAuthAccessToken;
				if (fileAccessToken == null) {
					//logger.info("fileAccessToken1:::{()}",fileAccessToken);
					oAuthAccessToken = getoAuthAccessToken(url);
					//logger.info("oAuthAccessToken:::{()}",oAuthAccessToken);
					if (oAuthAccessToken == null) {
						apimFallbackProc.setFailCount();
						url = apimFallbackProc.getApimgwSubUrl();
						//logger.info("url:::{()}",url);
						oAuthAccessToken = getoAuthAccessToken(url);
						if (oAuthAccessToken == null) {
							logger.error("###APIM is die.###");
							return null;
						}
					}else {
						apimFallbackProc.setSuccessUrl(url);
					}
				}else {
					oAuthAccessToken = fileAccessToken;
					//logger.info("oAuthAccessToken3:::{()}",oAuthAccessToken);
				}
		
				//logger.info("oAuthAccessToken4:::",oAuthAccessToken);
				MmlyRmndUsagOverResponseJSON mmlyRmndUsagOverResBody = esbAgent.requestMmlyRmndUsagOver(url, callYm, found.getProdNo(),found.getAceno(), found.getEntrNo(), found.getBillAcntNo(), oAuthAccessToken, ip);
				if (mmlyRmndUsagOverResBody.getHttpCode() == 0) {
					apimFallbackProc.setSuccessUrl(url);
				}else if (mmlyRmndUsagOverResBody.getHttpCode() == 401)
				{
					oAuthAccessToken = getoAuthAccessToken(url);
					mmlyRmndUsagOverResBody = esbAgent.requestMmlyRmndUsagOver(url, callYm, found.getProdNo(),found.getAceno(), found.getEntrNo(), found.getBillAcntNo(), oAuthAccessToken, ip);
				}
				else
				{
					apimFallbackProc.setFailCount();
					url = apimFallbackProc.getApimgwSubUrl();
					mmlyRmndUsagOverResBody = esbAgent.requestMmlyRmndUsagOver(url, callYm, found.getProdNo(),found.getAceno(), found.getEntrNo(), found.getBillAcntNo(), oAuthAccessToken, ip);
				}
				
				/*RetrieveCallAppSmryServiceStub.PutEntrCustInVO putEntrCustIn = new RetrieveCallAppSmryServiceStub.PutEntrCustInVO();
				putEntrCustIn.setCallYm(callYm);
				putEntrCustIn.setProdNo(found.getProdNo());
				putEntrCustIn.setAceNo(found.getAceno());
				putEntrCustIn.setEntrNo(found.getEntrNo());
				putEntrCustIn.setBillAcctNo(found.getBillAcntNo());
				putEntrCustIn.setWrkTypCd("1");*/
				//RetrieveCallAppSmryServiceStub.DsGetEntrSvcSmryOutVO found_ = null;
			/*	RetrieveCallAppSmryServiceStub.ResponseBody resBody_ = retrieveCallAppSmry(putEntrCustIn);
				RetrieveCallAppSmryServiceStub.DsGetEntrSvcSmryOutVO[] dsGetEntrSvcSmryOut = resBody_
						.getDsGetEntrSvcSmryOutVO();
				*/
				//RetrieveCallAppSmryServiceStub.DsGetEntrSvcSmryOutVO found_ = null;
				
				if (mmlyRmndUsagOverResBody.getDsGetEntrSvcSmry() != null) {
					for (int i = 0; i < mmlyRmndUsagOverResBody.getDsGetEntrSvcSmry().length; i++) {
						// if (svcCd.equals(dsGetEntrSvcSmryOut[i].getSvcCd())) {
						amountDataVO.setAlloValue(mmlyRmndUsagOverResBody.getDsGetEntrSvcSmry()[i].getAlloValue());
						amountDataVO.setUseValue(mmlyRmndUsagOverResBody.getDsGetEntrSvcSmry()[i].getUseValue());
						logger.debug("requestMmlyRmndUsagOver in getAmountData({}, {}, {}) \n{}", no, svcCd, callYm,
								printFields(mmlyRmndUsagOverResBody.getDsGetEntrSvcSmry()[i]));
						break;
						// }
					}
				}else {
					logger.debug("requestMmlyRmndUsagOver in getAmountData({}, {}, {}) not found", no, svcCd, callYm);
					return null;
				}
			} else{
				RetrieveCallAppSmryServiceStub.PutEntrCustInVO putEntrCustIn = new RetrieveCallAppSmryServiceStub.PutEntrCustInVO();
				putEntrCustIn.setCallYm(callYm);
				putEntrCustIn.setProdNo(found.getProdNo());
				putEntrCustIn.setAceNo(found.getAceno());
				putEntrCustIn.setEntrNo(found.getEntrNo());
				putEntrCustIn.setBillAcctNo(found.getBillAcntNo());
				putEntrCustIn.setWrkTypCd("1");

				RetrieveCallAppSmryServiceStub.DsGetEntrSvcSmryOutVO found_ = null;
				RetrieveCallAppSmryServiceStub.ResponseBody resBody_ = retrieveCallAppSmry(putEntrCustIn);
				RetrieveCallAppSmryServiceStub.DsGetEntrSvcSmryOutVO[] dsGetEntrSvcSmryOut = resBody_
						.getDsGetEntrSvcSmryOutVO();
				if (dsGetEntrSvcSmryOut != null) {
					for (int i = 0; i < dsGetEntrSvcSmryOut.length; i++) {
						// if (svcCd.equals(dsGetEntrSvcSmryOut[i].getSvcCd())) {
						found_ = dsGetEntrSvcSmryOut[i];

						logger.debug("retrieveCallAppSmry in getAmountData({}, {}, {}) \n{}", no, svcCd, callYm,
								printFields(dsGetEntrSvcSmryOut[i]));
						break;
						// }
					}
				}

				if (found_ == null) {
					logger.debug("retrieveCallAppSmry in getAmountData({}, {}, {}) not found", no, svcCd, callYm);
					return null;
				}
				amountDataVO.setAlloValue(found_.getAlloValue());// 제공량
				amountDataVO.setUseValue(found_.getUseValue());// 사용량

				logger.debug("getAmountData({}, {}, {}) is \n{}", no, svcCd, callYm, amountDataVO.toString());
				
			}
		/*	if (found_ == null) {
				logger.debug("retrieveCallAppSmry in getAmountData({}, {}, {}) not found", no, svcCd, callYm);
				return null;
			}*/

			logger.debug("getAmountData({}, {}, {}) is \n{}", no, svcCd, callYm, amountDataVO.toString());

			return amountDataVO;
		} catch (ESBException e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 요금납부정보 - 상품명, 카드주명, 납부방법, 납기일/출금일, 청구서유형
	 * 
	 * 예) 상품(요금제)명 - "", 요금청구구분 - "", 요금청구구분명 - "", 납부방법구분 - "", 납부방법구분명 - "",
	 * 은행명 - "", 카드회사명 - "", 납기일 - "", 출금일 - "", 예금(카드)주명 - "",
	 * 
	 * @param no
	 * @param svcCd
	 * @param billTrgtYymm
	 * @return
	 */
	@SuppressWarnings("unused")
	public PayInfoVO getPayInfo(String no, String svcCd, String billTrgtYymm) {
		try {
			// type 구분 VARCHAR2 1 N 단수 "A:가입번호,B:결합번호,C:청구계정번호,D:상품번호,E:홈코드번호"
			// no 번호 VARCHAR2 20 N 단수
			RetrieveCustSvcEntrInfoBDServiceStub.DsInputInVO dsReqIn = new RetrieveCustSvcEntrInfoBDServiceStub.DsInputInVO();
			dsReqIn.setType("A");
			dsReqIn.setNo(no);

			RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO found = null;
			RetrieveCustSvcEntrInfoBDServiceStub.ResponseBody resBody = retrieveCustSvcEntrInfoBD(dsReqIn);
			RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO[] dsOutputOut = resBody.getDsOutputOutVO();
			for (int i = 0; i < dsOutputOut.length; i++) {
				// if (svcCd.equals(dsOutputOut[i].getSvcCd())) {
				found = dsOutputOut[i];

				logger.debug("retrieveCustSvcEntrInfoBD in getPayInfo({}, {}, {}) \n{}", no, svcCd, billTrgtYymm,
						printFields(dsOutputOut[i]));
				break;
				// }
			}

			if (found == null) {
				logger.debug("retrieveCustSvcEntrInfoBD in getPayInfo({}, {}, {}) not found", no, svcCd, billTrgtYymm);
				return null;
			}

			PayInfoVO payInfoVO = new PayInfoVO();
			payInfoVO.setBillAcntNo(found.getBillAcntNo());// 청구계정번호
			payInfoVO.setSvcNm(found.getSvcNm());// 상품(요금제)명
			payInfoVO.setBltxtKdCd(found.getBltxtKdCd());// 요금청구구분
			payInfoVO.setBltxtKdNm(found.getBltxtKdNm());// 요금청구구분명
			payInfoVO.setPymMthdCd(found.getPymMthdCd());// 납부방법구분
			payInfoVO.setPymMthdNm(found.getPymMthdNm());// 납부방법구분명
			payInfoVO.setBankNm(found.getBankNm());// 은행명
			payInfoVO.setCardNm(found.getCardNm());// 카드회사명

			// billAcntNo 청구계정번호 VARCHAR2 12 N 단수
			// billTrgtYymm 청구대상년월 VARCHAR2 6 N 단수
			RetrieveBlPaymentInfoEsbServiceStub.DeReqInVO deReqIn = new RetrieveBlPaymentInfoEsbServiceStub.DeReqInVO();
			deReqIn.setBillAcntNo(found.getBillAcntNo());
			deReqIn.setBillTrgtYymm(billTrgtYymm);

			RetrieveBlPaymentInfoEsbServiceStub.ResponseBody resBody_ = retrieveBlPaymentInfoEsb(deReqIn);
			if (resBody_ != null) {
				RetrieveBlPaymentInfoEsbServiceStub.DsPaymentInfoOutVO[] dsPaymentInfoOut = resBody_
						.getDsPaymentInfoOutVO();
				if (dsPaymentInfoOut != null) {
					for (int i = 0; i < dsPaymentInfoOut.length; i++) {
						payInfoVO.setDuedDt(dsPaymentInfoOut[i].getDuedDt());// 납기일
						payInfoVO.setAddWdrwTrgtCntnt(dsPaymentInfoOut[i].getAddWdrwTrgtCntnt());// 출금일
						payInfoVO.setCardDepoNm(dsPaymentInfoOut[i].getCardDepoNm());// 예금(카드)주명

						logger.debug("retrieveBlPaymentInfoEsb in getPayInfo({}, {}, {}) \n{}", no, svcCd, billTrgtYymm,
								printFields(dsPaymentInfoOut[i]));
					}
				}
			} else {
				// 이 ESB 또 무조건 에러난다.
				payInfoVO.setDuedDt("");// 납기일
				payInfoVO.setAddWdrwTrgtCntnt("");// 출금일
				payInfoVO.setCardDepoNm("");// 예금(카드)주명
			}

			logger.debug("getPayInfo({}, {}, {}) is \n{}", no, svcCd, billTrgtYymm, payInfoVO.toString());

			return payInfoVO;
		} catch (ESBException e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 요금납부정보 > 납부방법변경 - 상품명, 고객명, 납부방법, 예금(카드)주
	 * 
	 * 예) 상품(요금제)명 - "", 고객명 - "", 요금청구구분 - "", 요금청구구분명 - "", 납부방법구분 - "",
	 * 납부방법구분명 - "", 은행명 - "", 카드회사명 - "", 납기일 - "", 출금일 - "", 예금(카드)주명 - "",
	 * 
	 * @param no
	 * @param svcCd
	 * @param billTrgtYymm
	 * @return
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@SuppressWarnings("unused")
	public PayMethodVO getPayMethod(String no, String svcCd, String billTrgtYymm, String ip) throws IOException, ParseException{
		try {
			// type 구분 VARCHAR2 1 N 단수 "A:가입번호,B:결합번호,C:청구계정번호,D:상품번호,E:홈코드번호"
			// no 번호 VARCHAR2 20 N 단수
			RetrieveCustSvcEntrInfoBDServiceStub.DsInputInVO dsReqIn = new RetrieveCustSvcEntrInfoBDServiceStub.DsInputInVO();
			dsReqIn.setType("A");
			dsReqIn.setNo(no);

			RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO found = null;
			RetrieveCustSvcEntrInfoBDServiceStub.ResponseBody resBody = retrieveCustSvcEntrInfoBD(dsReqIn);
			RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO[] dsOutputOut = resBody.getDsOutputOutVO();
			for (int i = 0; i < dsOutputOut.length; i++) {
				// if (svcCd.equals(dsOutputOut[i].getSvcCd())) {
				found = dsOutputOut[i];

				logger.debug("retrieveCustSvcEntrInfoBD in getPayMethod({}, {}, {}) \n{}", no, svcCd, billTrgtYymm,
						printFields(dsOutputOut[i]));
				break;
				// }
			}

			if (found == null) {
				logger.debug("retrieveCustSvcEntrInfoBD in getPayMethod({}, {}, {}) not found", no, svcCd,
						billTrgtYymm);
				return null;
			}

			PayMethodVO payMethodVO = new PayMethodVO();
			payMethodVO.setBillAcntNo(found.getBillAcntNo());// 청구계정번호
			payMethodVO.setSvcNm(found.getSvcNm());// 상품(요금제)명
			payMethodVO.setCustNm(found.getCustNm());// 고객명
			payMethodVO.setBltxtKdCd(found.getBltxtKdCd());// 요금청구구분
			payMethodVO.setBltxtKdNm(found.getBltxtKdNm());// 요금청구구분명
			payMethodVO.setPymMthdCd(found.getPymMthdCd());// 납부방법구분
			payMethodVO.setPymMthdNm(found.getPymMthdNm());// 납부방법구분명
			payMethodVO.setBankNm(found.getBankNm());// 은행명
			payMethodVO.setCardNm(found.getCardNm());// 카드회사명
			

			// billAcntNo 청구계정번호 VARCHAR2 12 N 단수
			// billTrgtYymm 청구대상년월 VARCHAR2 6 N 단수
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String formatedNow = dateFormat.format(now);

			Date systemNow = dateFormat.parse(formatedNow);
			Date configNow = dateFormat.parse(apimOpen2nd);
			
			//if ("Y".equals(apimOpen2nd)) {
			if (systemNow.after(configNow)) {
				String url;
				url = apimFallbackProc.getApimgwUrl();
				String fileAccessToken = getFileAccessToken();
				//logger.info("fileAccessToken:::{()}",fileAccessToken);
				String oAuthAccessToken;
				if (fileAccessToken == null) {
					//logger.info("fileAccessToken1:::{()}",fileAccessToken);
					oAuthAccessToken = getoAuthAccessToken(url);
					//logger.info("oAuthAccessToken:::{()}",oAuthAccessToken);
					if (oAuthAccessToken == null) {
						apimFallbackProc.setFailCount();
						url = apimFallbackProc.getApimgwSubUrl();
						//logger.info("url:::{()}",url);
						oAuthAccessToken = getoAuthAccessToken(url);
						if (oAuthAccessToken == null) {
							logger.error("###APIM is die.###");
							return null;
						}
					}else {
						apimFallbackProc.setSuccessUrl(url);
					}
				}else {
					oAuthAccessToken = fileAccessToken;
					//logger.info("oAuthAccessToken3:::{()}",oAuthAccessToken);
				}
				/*
				RetrieveBlPaymentInfoEsbServiceStub.DeReqInVO dsReqIn_ = new RetrieveBlPaymentInfoEsbServiceStub.DeReqInVO();
				dsReqIn_.setBillAcntNo(found.getBillAcntNo());
				dsReqIn_.setBillTrgtYymm(billTrgtYymm);
	
				RetrieveBlPaymentInfoEsbServiceStub.ResponseBody resBody_ = retrieveBlPaymentInfoEsb(dsReqIn_);
				*/
				PayDlstResponseJSON paymDlstResBody = esbAgent.requestPaymDlst(url, found.getBillAcntNo(), billTrgtYymm, oAuthAccessToken, ip);
				if (paymDlstResBody.getHttpCode() == 0 ) {
					apimFallbackProc.setSuccessUrl(url);
				}else if (paymDlstResBody.getHttpCode() == 401){
					oAuthAccessToken = getoAuthAccessToken(url);
					paymDlstResBody = esbAgent.requestPaymDlst(url, found.getBillAcntNo(), billTrgtYymm, oAuthAccessToken, ip);
				}
				else
				{
					apimFallbackProc.setFailCount();
					url = apimFallbackProc.getApimgwSubUrl();
					paymDlstResBody = esbAgent.requestPaymDlst(url, found.getBillAcntNo(), billTrgtYymm, oAuthAccessToken, ip);
				}
				if (paymDlstResBody != null) {
					/*
					RetrieveBlPaymentInfoEsbServiceStub.DsPaymentInfoOutVO[] dsPaymentInfoOut = resBody_
							.getDsPaymentInfoOutVO();
							*/
					
					if (paymDlstResBody.getDsPaymentInfo() != null) {
						for(int i = 0; i < paymDlstResBody.getDsPaymentInfo().length; i++) {
							payMethodVO.setDuedDt(paymDlstResBody.getDsPaymentInfo()[i].getDuedDt());
							payMethodVO.setAddWdrwTrgtCntnt(paymDlstResBody.getDsPaymentInfo()[i].getAddWdrwTrgtCntnt());
							payMethodVO.setCardDepoNm(paymDlstResBody.getDsPaymentInfo()[i].getCardDepoNm());
							
							logger.debug("retrieveBlPaymentInfoAPIM in getPayMethod({}, {}, {}) \n{}", no, svcCd,
									billTrgtYymm, printFields(paymDlstResBody.getDsPaymentInfo()[i]));
						}
						/*
						for (int i = 0; i < dsPaymentInfoOut.length; i++) {
							payMethodVO.setDuedDt(dsPaymentInfoOut[i].getDuedDt());// 납기일
							payMethodVO.setAddWdrwTrgtCntnt(dsPaymentInfoOut[i].getAddWdrwTrgtCntnt());// 출금일
							payMethodVO.setCardDepoNm(dsPaymentInfoOut[i].getCardDepoNm());// 예금(카드)주명
	
							logger.debug("retrieveBlPaymentInfoEsb in getPayMethod({}, {}, {}) \n{}", no, svcCd,
									billTrgtYymm, printFields(dsPaymentInfoOut[i]));
						}
						*/
					}
				} else {
					// 이 ESB 또 무조건 에러난다.
					payMethodVO.setDuedDt("");// 납기일
					payMethodVO.setAddWdrwTrgtCntnt("");// 출금일
					payMethodVO.setCardDepoNm("");// 예금(카드)주명
				}
			} else {
				RetrieveBlPaymentInfoEsbServiceStub.DeReqInVO dsReqIn_ = new RetrieveBlPaymentInfoEsbServiceStub.DeReqInVO();
				dsReqIn_.setBillAcntNo(found.getBillAcntNo());
				dsReqIn_.setBillTrgtYymm(billTrgtYymm);

				RetrieveBlPaymentInfoEsbServiceStub.ResponseBody resBody_ = retrieveBlPaymentInfoEsb(dsReqIn_);
				if (resBody_ != null) {
					RetrieveBlPaymentInfoEsbServiceStub.DsPaymentInfoOutVO[] dsPaymentInfoOut = resBody_
							.getDsPaymentInfoOutVO();
					if (dsPaymentInfoOut != null) {
						for (int i = 0; i < dsPaymentInfoOut.length; i++) {
							payMethodVO.setDuedDt(dsPaymentInfoOut[i].getDuedDt());// 납기일
							payMethodVO.setAddWdrwTrgtCntnt(dsPaymentInfoOut[i].getAddWdrwTrgtCntnt());// 출금일
							payMethodVO.setCardDepoNm(dsPaymentInfoOut[i].getCardDepoNm());// 예금(카드)주명

							logger.debug("retrieveBlPaymentInfoEsb in getPayMethod({}, {}, {}) \n{}", no, svcCd,
									billTrgtYymm, printFields(dsPaymentInfoOut[i]));
						}
					}
				} else {
					// 이 ESB 또 무조건 에러난다.
					payMethodVO.setDuedDt("");// 납기일
					payMethodVO.setAddWdrwTrgtCntnt("");// 출금일
					payMethodVO.setCardDepoNm("");// 예금(카드)주명
				}
			}

			logger.debug("getPayMethod({}, {}, {}) is \n{}", no, svcCd, billTrgtYymm, payMethodVO.toString());

			return payMethodVO;
			} catch (ESBException e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return null;
	}

	private String getoAuthAccessToken(String url) throws IOException {
		ResponseOAuth2AccessTokenDto cekAuthResponseJSON = oAuthAgent.requestAccessToken(url);
		String acessToken ;
		if (cekAuthResponseJSON.getCode() == 502) {
			acessToken = null;
		}else {
			acessToken = cekAuthResponseJSON.getToken_type()+" "+cekAuthResponseJSON.getAccess_token();
			BufferedOutputStream bs = null;
			try {
				bs = new BufferedOutputStream(new FileOutputStream(fileDir+fileName));
				bs.write(acessToken.getBytes()); //Byte형으로만 넣을 수 있음
			} catch (Exception e) {
				logger.debug("{}", e.getMessage());

				e.printStackTrace();
			} finally {
				bs.close();
			}
		}
		
		return acessToken;
	}

	private String getFileAccessToken() throws IOException {
		//파일경로에서 파일 체크
		File f = new File(fileDir+fileName);
		String str = null;
		if(f.exists()) {
			BufferedReader reader = new BufferedReader(new FileReader(fileDir+fileName));
			if ((str = reader.readLine()) != null) {
				logger.info("file contents{{}}", str);
			}
			reader.close();
			logger.info("file contents{{}}", str);
			return str;
		}else {
			logger.debug("file not exist");
		}
		return str;
	}

	/**
	 * 요금납부정보 > 청구유형변경 - 청구서유형, 신청일, 청구서 반송내역(청구계정번호, 청구년월, 청구서종류, 반송일, 반송사유)
	 * 
	 * 예) 청구서유형, 신청일, 청구서 반송내역(청구계정번호, 청구년월, 청구서종류, 반송일, 반송사유)
	 * 
	 * @param no
	 * @param svcCd
	 * @return
	 */
	@SuppressWarnings("unused")
	public BillTypeVO getBillType(String no, String svcCd) {
		try {
			// type 구분 VARCHAR2 1 N 단수 "A:가입번호,B:결합번호,C:청구계정번호,D:상품번호,E:홈코드번호"
			// no 번호 VARCHAR2 20 N 단수
			RetrieveCustSvcEntrInfoBDServiceStub.DsInputInVO dsReqIn = new RetrieveCustSvcEntrInfoBDServiceStub.DsInputInVO();
			dsReqIn.setType("A");
			dsReqIn.setNo(no);

			RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO found = null;
			RetrieveCustSvcEntrInfoBDServiceStub.ResponseBody resBody = retrieveCustSvcEntrInfoBD(dsReqIn);
			RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO[] dsOutputOut = resBody.getDsOutputOutVO();
			for (int i = 0; i < dsOutputOut.length; i++) {
				// if (svcCd.equals(dsOutputOut[i].getSvcCd())) {
				found = dsOutputOut[i];

				logger.debug("retrieveCustSvcEntrInfoBD in getBillType({}, {}) \n{}", no, svcCd,
						printFields(dsOutputOut[i]));
				break;
				// }
			}

			if (found == null) {
				logger.debug("retrieveCustSvcEntrInfoBD in getBillType({}, {}) not found", no, svcCd);
				return null;
			}

			BillTypeVO billTypeVO = new BillTypeVO();
			billTypeVO.setBltxtKdNm(found.getBltxtKdNm());
			billTypeVO.setBillAcntNo(found.getBillAcntNo());

			// entrNo 가입번호 VARCHAR2 10 Y 단수
			// prodNo 상품번호 VARCHAR2 12 Y 단수
			// billAcntNo 청구계정번호 VARCHAR2 40 Y 단수
			// valdEndDttm 가입이력일자 VARCHAR2 12 Y 단수
			// mode 구분 VARCHAR2 1 N 단수 "E : 가입번호 P : 전화번호 B : 청구계정번호"
			RetrieveBillAcntForEntrSvcServiceStub.DsReqInVO dsReqIn_ = new RetrieveBillAcntForEntrSvcServiceStub.DsReqInVO();
			dsReqIn_.setBillAcntNo(found.getBillAcntNo());
			dsReqIn_.setMode("B");

			RetrieveBillAcntForEntrSvcServiceStub.ResponseBody resBody_ = retrieveBillAcntForEntrSvc(dsReqIn_);
			RetrieveBillAcntForEntrSvcServiceStub.DsResOutVO dsResOut = resBody_.getDsResOutVO();
			if (dsResOut != null) {
				// if (svcCd.equals(dsOldBillEmailOut.getSvcCd())) {
				billTypeVO.setBltxtKdCd(dsResOut.getBltxtKdCd());// 청구서유형코드
				billTypeVO.setBillEmailAddr(dsResOut.getBillEmailAddr());// 청구서이메일주소
				billTypeVO.setBltxtKdValdStrtDt(dsResOut.getBltxtKdValdStrtDt());// 청구서유형유효시작일자
				billTypeVO.setScurMailRcpYn(dsResOut.getScurMailRcpYn());// 보안메일수신여부

				logger.debug("retrieveBillAcntForEntrSvc in getBillType({}, {}) \n{}", no, svcCd,
						printFields(dsResOut));
				// }
			}

			// // entrNo 가입번호 VARCHAR2 10 Y 단수
			// // billAcntNo 청구계정번호 VARCHAR2 9 Y 단수
			// RetrieveBlDcEmailCdForBltxtSvcServiceStub.DsReqInVO dsReqIn_ =
			// new
			// RetrieveBlDcEmailCdForBltxtSvcServiceStub.DsReqInVO();
			// dsReqIn_.setEntrNo(no);
			// dsReqIn_.setBillAcntNo(found.getBillAcntNo());
			//
			// RetrieveBlDcEmailCdForBltxtSvcServiceStub.ResponseBody resBody_ =
			// retrieveBlDcEmailCdForBltxtSvc(dsReqIn_);
			// RetrieveBlDcEmailCdForBltxtSvcServiceStub.DsOldBillEmailOutVO
			// dsOldBillEmailOut = resBody_
			// .getDsOldBillEmailOutVO();
			// if (dsOldBillEmailOut != null) {
			// // if (svcCd.equals(dsOldBillEmailOut.getSvcCd())) {
			// billTypeVO.setBltxtKdCd(dsOldBillEmailOut.getBltxtKdCd());//
			// 청구서유형코드
			// billTypeVO.setBltxtKdCdNm(dsOldBillEmailOut.getBltxtKdCdNm());//
			// 청구서유형코드명
			// billTypeVO.setBillEmailAddr(dsOldBillEmailOut.getBillEmailAddr());//
			// 청구서이메일주소
			// billTypeVO.setBltxtKdValdStrtDt(dsOldBillEmailOut.getBltxtKdValdStrtDt());//
			// 청구서유형유효시작일자
			// billTypeVO.setScurMailRcpYn(dsOldBillEmailOut.getScurMailRcpYn());//
			// 보안메일수신여부
			//
			// logger.debug("retrieveBlDcEmailCdForBltxtSvc in getBillType({},
			// {}) \n{}",
			// no, svcCd,
			// printFields(dsOldBillEmailOut));
			// // }
			// }

			List<BillReturnInfoVO> list = new ArrayList<BillReturnInfoVO>();

			// billAcntNo 청구계정번호 NUMBER 12 N 단수
			// billTrgtYymmFrom FROM청구년월 VARCHAR2 6 Y 단수 조회기간시작 청구년월(YYYYMM)
			// billTrgtYymmTo TO청구년월 VARCHAR2 6 Y 단수 조회기간종료 청구년월(YYYYMM)
			RetrieveBillRetnInfoServiceStub.DsReqInVO dsReqIn__ = new RetrieveBillRetnInfoServiceStub.DsReqInVO();
			dsReqIn__.setBillAcntNo(found.getBillAcntNo());
//			if (StringUtils.equals("Y", real)) {
//				dsReqIn__.setBillAcntNo(found.getBillAcntNo());
//			} else {
//				dsReqIn__.setBillAcntNo("511508913586"); // 고정
//			}

			RetrieveBillRetnInfoServiceStub.ResponseBody resBody__ = retrieveBillRetnInfo(dsReqIn__);
			RetrieveBillRetnInfoServiceStub.DsResOutVO[] dsResOut__ = resBody__.getDsResOutVO();
			if (dsResOut__ != null) {
				for (int i = 0; i < dsResOut__.length; i++) {
					BillReturnInfoVO billReturnInfoVO = new BillReturnInfoVO();
					billReturnInfoVO.setBillAcntNo(dsResOut__[i].getBillAcntNo());// 청구계정번호
					billReturnInfoVO.setBillTrgtYymm(dsResOut__[i].getBillTrgtYymm());// 청구년월
					billReturnInfoVO.setRetnDvNm(dsResOut__[i].getRetnDvNm());// 청구서종류
					billReturnInfoVO.setDlvDt(dsResOut__[i].getDlvDt());// 반송일
					billReturnInfoVO.setRetnRsnNm(dsResOut__[i].getRetnRsnNm());// 반송사유

					list.add(billReturnInfoVO);

					logger.debug("retrieveBillRetnInfo in getBillType({}, {}) \n{}", no, svcCd,
							printFields(dsResOut__[i]));
				}
			}

			billTypeVO.setList(list);

			logger.debug("getBillType({}, {}) is \n{}", no, svcCd, billTypeVO.toString());

			return billTypeVO;
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 요금납부정보 > 상세내역조회 - 이달요금 계(A), 미납요금 계(B), 총납부하실 금액(A+B), 월정액, 할인요금, 할인상세,
	 * 부가가치세
	 * 
	 * 예) 이번달요금(1) - "", 미납요금(2) - "", 이번달 납부금액(1+2) - "", 공급가액 - "", 세액 - "",
	 * 할인내역 - "", 할인금액 - "",
	 * 
	 * @param no
	 * @param svcCd
	 * @param billTrgtYymm
	 * @return
	 */
	@SuppressWarnings("unused")
	public PayDetailVO getPayDetail(String no, String svcCd, String billTrgtYymm, String ip) {

		try {
			// type 구분 VARCHAR2 1 N 단수 "A:가입번호,B:결합번호,C:청구계정번호,D:상품번호,E:홈코드번호"
			// no 번호 VARCHAR2 20 N 단수
			RetrieveCustSvcEntrInfoBDServiceStub.DsInputInVO dsReqIn = new RetrieveCustSvcEntrInfoBDServiceStub.DsInputInVO();
			dsReqIn.setType("A");
			dsReqIn.setNo(no);

			RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO found = null;
			RetrieveCustSvcEntrInfoBDServiceStub.ResponseBody resBody = retrieveCustSvcEntrInfoBD(dsReqIn);
			RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO[] dsOutputOut = resBody.getDsOutputOutVO();
			for (int i = 0; i < dsOutputOut.length; i++) {
				// if (svcCd.equals(dsOutputOut[i].getSvcCd())) {
				found = dsOutputOut[i];

				logger.debug("retrieveCustSvcEntrInfoBD in getPayDetail({}, {}, {}) \n{}", no, svcCd, billTrgtYymm,
						printFields(dsOutputOut[i]));
				break;
				// }
			}

			if (found == null) {
				logger.debug("retrieveCustSvcEntrInfoBD in getPayDetail({}, {}, {}) not found", no, svcCd,
						billTrgtYymm);
				return null;
			}


			PayDetailVO payDetailVO = new PayDetailVO();
			payDetailVO.setBillAcntNo(found.getBillAcntNo());
			List<DiscountInfoVO> list = new ArrayList<DiscountInfoVO>();
			
			// billAcntNo 청구계정번호 NUMBER 12 N 단수
			// billTrgtYymm 청구대상년월 VARCHAR2 6 N 단수
			// aceno 가입계약번호 NUMBER 12 Y 단수
			// entrNo 가입번호 NUMBER 12 Y 단수
			// prodNo 상품번호 VARCHAR2 20 Y 단수
			// prodCd 상품코드 VARCHAR2 10 Y 단수
			// fromPage 시작페이지 NUMBER 10 Y 단수
			// toPage 종료페이지 NUMBER 10 Y 단수
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String formatedNow = dateFormat.format(now);

			Date systemNow = dateFormat.parse(formatedNow);
			Date configNow = dateFormat.parse(apimOpen2nd);
			
			boolean beforeDate = systemNow.before(configNow);
			//if("Y".equals(apimOpen2nd)) {
			if(systemNow.after(configNow)) {
				String url;
				url = apimFallbackProc.getApimgwUrl();				
				String fileAccessToken = getFileAccessToken();
				String oAuthAccessToken;
				if (fileAccessToken == null) {
					oAuthAccessToken = getoAuthAccessToken(url);
					if (oAuthAccessToken == null) {
						apimFallbackProc.setFailCount();
						url = apimFallbackProc.getApimgwSubUrl();
						oAuthAccessToken = getoAuthAccessToken(url);
					}else {
						apimFallbackProc.setSuccessUrl(url);
					}
				}else {
					oAuthAccessToken = fileAccessToken;
				}
	
				DcntDlstBltxResponseJSON dcntDlstBltxResBody = esbAgent.requestDcntDlstBltx(url, found.getBillAcntNo(), billTrgtYymm, oAuthAccessToken, ip);
				if (dcntDlstBltxResBody.getHttpCode() == 0 ) {
					apimFallbackProc.setSuccessUrl(url);					
				}else if (dcntDlstBltxResBody.getHttpCode() == 401){
					oAuthAccessToken = getoAuthAccessToken(url);
					dcntDlstBltxResBody = esbAgent.requestDcntDlstBltx(url, found.getBillAcntNo(), billTrgtYymm, oAuthAccessToken, ip);
				}
				else
				{
					apimFallbackProc.setFailCount();
					url = apimFallbackProc.getApimgwSubUrl();
					dcntDlstBltxResBody = esbAgent.requestDcntDlstBltx(url, found.getBillAcntNo(), billTrgtYymm, oAuthAccessToken, ip);
				}

				/*
				RetrieveDscntNblTxtServiceStub.DeReqInVO dsReqIn_ = new RetrieveDscntNblTxtServiceStub.DeReqInVO();
				dsReqIn_.setBillAcntNo(found.getBillAcntNo());
				dsReqIn_.setBillTrgtYymm(billTrgtYymm);
	
				RetrieveDscntNblTxtServiceStub.ResponseBody resBody_ = retrieveDscntNblTxt(dsReqIn_);
				RetrieveDscntNblTxtServiceStub.DsBillsOutVO[] dsBillsOut = resBody_.getDsBillsOutVO();
				*/
				if (dcntDlstBltxResBody.getDsBills() != null) {
					for (int i = 0; i < dcntDlstBltxResBody.getDsBills().length; i++) {
						payDetailVO.setBillAmt(dcntDlstBltxResBody.getDsBills()[i].getBillAmt());// 이번달요금(1)
						payDetailVO.setUpaidChrg(dcntDlstBltxResBody.getDsBills()[i].getUpaidChrg());// 미납요금(2)
						payDetailVO.setTotPymScdlAmt(dcntDlstBltxResBody.getDsBills()[i].getTotPymScdlAmt());// 이번달납부금액(1+2)
						payDetailVO.setSpramt(dcntDlstBltxResBody.getDsBills()[i].getSpramt());// 공급가액
						payDetailVO.setTxamt(dcntDlstBltxResBody.getDsBills()[i].getTxamt());// 세액
	
						logger.debug("retrieveDscntNblTxt in getPayDetail({}, {}, {}) \n{}", no, svcCd, billTrgtYymm,
								printFields(dcntDlstBltxResBody.getDsBills()[i]));
					}
				}
				
				//RetrieveDscntNblTxtServiceStub.DsDscntDtlOutVO[] dsDscntDtlOut = resBody_.getDsDscntDtlOutVO();
				if (dcntDlstBltxResBody.getDsDscntDtl() != null) {
					for (int i = 0; i < dcntDlstBltxResBody.getDsDscntDtl().length; i++) {
						DiscountInfoVO discountInfoVO = new DiscountInfoVO();
						discountInfoVO.setDscntDtl(dcntDlstBltxResBody.getDsDscntDtl()[i].getDscntDtl());// 할인내역
						discountInfoVO.setDscntAmt(dcntDlstBltxResBody.getDsDscntDtl()[i].getDscntAmt()); // 할인금액
	
						list.add(discountInfoVO);
	
						logger.debug("retrieveDscntNblTxt in getPayDetail({}, {}, {}) \n{}", no, svcCd, billTrgtYymm,
								printFields(dcntDlstBltxResBody.getDsDscntDtl()[i]));
					}
				}
			} else {
				RetrieveDscntNblTxtServiceStub.DeReqInVO dsReqIn_ = new RetrieveDscntNblTxtServiceStub.DeReqInVO();
				dsReqIn_.setBillAcntNo(found.getBillAcntNo());
				dsReqIn_.setBillTrgtYymm(billTrgtYymm);

				RetrieveDscntNblTxtServiceStub.ResponseBody resBody_ = retrieveDscntNblTxt(dsReqIn_);
				RetrieveDscntNblTxtServiceStub.DsBillsOutVO[] dsBillsOut = resBody_.getDsBillsOutVO();
				if (dsBillsOut != null) {
					for (int i = 0; i < dsBillsOut.length; i++) {
						payDetailVO.setBillAmt(dsBillsOut[i].getBillAmt());// 이번달요금(1)
						payDetailVO.setUpaidChrg(dsBillsOut[i].getUpaidChrg());// 미납요금(2)
						payDetailVO.setTotPymScdlAmt(dsBillsOut[i].getTotPymScdlAmt());// 이번달납부금액(1+2)
						payDetailVO.setSpramt(dsBillsOut[i].getSpramt());// 공급가액
						payDetailVO.setTxamt(dsBillsOut[i].getTxamt());// 세액

						logger.debug("retrieveDscntNblTxt in getPayDetail({}, {}, {}) \n{}", no, svcCd, billTrgtYymm,
								printFields(dsBillsOut[i]));
					}
				}

				RetrieveDscntNblTxtServiceStub.DsDscntDtlOutVO[] dsDscntDtlOut = resBody_.getDsDscntDtlOutVO();
				if (dsDscntDtlOut != null) {
					for (int i = 0; i < dsDscntDtlOut.length; i++) {
						DiscountInfoVO discountInfoVO = new DiscountInfoVO();
						discountInfoVO.setDscntDtl(dsDscntDtlOut[i].getDscntDtl());// 할인내역
						discountInfoVO.setDscntAmt(dsDscntDtlOut[i].getDscntAmt()); // 할인금액

						list.add(discountInfoVO);

						logger.debug("retrieveDscntNblTxt in getPayDetail({}, {}, {}) \n{}", no, svcCd, billTrgtYymm,
								printFields(dsDscntDtlOut[i]));
					}
				}
			}
			payDetailVO.setList(list);

			logger.debug("getPayDetail({}, {}, {}) is \n{}", no, svcCd, billTrgtYymm, payDetailVO.toString());

			return payDetailVO;
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 서비스일시정지 > 일시정지 내역(서비스명, 상태, 정지 사유, 정지 기간, 정지 일 수)
	 * 
	 * 예) 가입상태유효시작일자(정지희망일자) - "", 가입상태유효종료일자 - "", 상태변경상세사유코드명 - "", 서비스명 - ""
	 * 
	 * @param no
	 * @param svcCd
	 * @return
	 */
	public PauseHistoryCollectionVO getPauseHistory(String no, String svcCd) {

		try {
			List<PauseHistoryVO> list = new ArrayList<PauseHistoryVO>();

			PauseHistoryCollectionVO pauseHistoryCollectionVO = new PauseHistoryCollectionVO();

			// sSearchCb 검색구분 VARCHAR2 2 N 단수 구분: 01
			// sSrchValue 검색조건 VARCHAR2 12 N 단수 가입번호
			// RetrieveUseSvcListServiceStub.DsInputInVO dsReqIn = new
			// RetrieveUseSvcListServiceStub.DsInputInVO();
			// dsReqIn.setSSearchCb("01");
			// dsReqIn.setSSrchValue(no);
			//
			// RetrieveUseSvcListServiceStub.ResponseBody resBody =
			// retrieveUseSvcList(dsReqIn);
			// RetrieveUseSvcListServiceStub.DsUseSvcListOutVO[] dsUseSvcListOut
			// =
			// resBody.getDsUseSvcListOutVO();
			// if (dsUseSvcListOut != null) {
			// for (int i = 0; i < dsUseSvcListOut.length; i++) {
			// if (svcCd.equals(dsUseSvcListOut[i].getSvcCd())) {
			// PauseHistoryVO pauseHistoryVO = new PauseHistoryVO();
			// pauseHistoryVO.setEntrSttsValdStrtDt(dsUseSvcListOut[i].getEntrSttsValdStrtDt());//
			// 가입상태유효시작일자(정지희망일자)
			// pauseHistoryVO.setEntrSttsValdEndDt(dsUseSvcListOut[i].getEntrSttsValdEndDt());
			// // 가입상태유효종료일자
			// pauseHistoryVO.setEntrSttsNm(dsUseSvcListOut[i].getEntrSttsNm());//
			// 상태변경상세사유코드명
			// pauseHistoryVO.setProdNm(dsUseSvcListOut[i].getProdNm());// 서비스명
			// pauseHistoryVO.setMemo(dsUseSvcListOut[i].getMemo());// 예약메모
			//
			// list.add(pauseHistoryVO);
			//
			// logger.debug("retrieveUseSvcList in getPauseHistory({}, {})
			// \n{}", no, svcCd,
			// printFields(dsUseSvcListOut[i]));
			// }
			// }
			// }

			// type 구분 VARCHAR2 1 N 단수 "A:가입번호,B:결합번호,C:청구계정번호,D:상품번호,E:홈코드번호"
			// no 번호 VARCHAR2 20 N 단수
			//
			// RetrieveCustSvcEntrInfoBDServiceStub.DsInputInVO dsReqIn = new
			// RetrieveCustSvcEntrInfoBDServiceStub.DsInputInVO();
			// dsReqIn.setType("A");
			// dsReqIn.setNo(no);
			//
			// RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO found = null;
			// RetrieveCustSvcEntrInfoBDServiceStub.ResponseBody resBody =
			// retrieveCustSvcEntrInfoBD(dsReqIn);
			// RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO[] dsOutputOut
			// =
			// resBody.getDsOutputOutVO();
			// for (int i = 0; i < dsOutputOut.length; i++) {
			// // if (svcCd.equals(dsOutputOut[i].getSvcCd())) {
			// found = dsOutputOut[i];
			//
			// logger.debug("retrieveCustSvcEntrInfoBD in getPauseHistory({},
			// {}) \n{}", no,
			// svcCd,
			// printFields(dsOutputOut[i]));
			// break;
			// // }
			// }
			//
			// if (found == null) {
			// logger.debug("retrieveCustSvcEntrInfoBD in getPauseHistory({},
			// {}) not
			// found", no, svcCd);
			// return null;
			// }

			RetrieveSuspendHistoryHomepgServiceStub.DataInVO dataIn = new RetrieveSuspendHistoryHomepgServiceStub.DataInVO();
			dataIn.setEntrNo(no);

			RetrieveSuspendHistoryHomepgServiceStub.ResponseBody resBody_ = retrieveSuspendHistoryHomepg(dataIn);
			RetrieveSuspendHistoryHomepgServiceStub.DsSusHistListOutVO[] dsSusHistListOut = resBody_
					.getDsSusHistListOutVO();
			if (dsSusHistListOut != null) {
				for (int i = 0; i < dsSusHistListOut.length; i++) {
					// if
					// (found.getProdNo().equals(dsSusHistListOut[i].getProdNo()))
					// {
					PauseHistoryVO pauseHistoryVO = new PauseHistoryVO();
					pauseHistoryVO.setSusDate(dsSusHistListOut[i].getSusDate());// 일시정지일자
					pauseHistoryVO.setRspDate(dsSusHistListOut[i].getRspDate());// 정지해제일자
																				// 혹은
																				// 정지해제예약일자
					pauseHistoryVO.setRsnNm(dsSusHistListOut[i].getRsnNm()); // 요청구분명
					pauseHistoryVO.setProdNm(dsSusHistListOut[i].getProdNm());// 서비스명
					pauseHistoryVO.setSusRsnNm(dsSusHistListOut[i].getSusRsnNm());// 일시정지상세사유명
					pauseHistoryVO.setSusDays(dsSusHistListOut[i].getSusDays());// 일시정지일수

					list.add(pauseHistoryVO);

					logger.debug("retrieveUseSvcList in getPauseHistory({}, {}) \n{}", no, svcCd,
							printFields(dsSusHistListOut[i]));
					// }
				}
			}

			pauseHistoryCollectionVO.setList(list);

			logger.debug("getPauseHistory({}, {}) is \n{}", no, svcCd, pauseHistoryCollectionVO.toString());

			return pauseHistoryCollectionVO;
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 서비스일시정지 > 일시정지 내역조회(서비스명, 상태, 정지 사유, 정지 기간, 정지 일 수)
	 * 
	 * 예) 가입상태유효시작일자(정지희망일자) - "", 가입상태유효종료일자 - "", 상태변경상세사유코드명 - "", 서비스명 - ""
	 * 
	 * @param no
	 * @param svcCd
	 * @return
	 */
	public PauseHistoryCollection getPauseHistorys(String no, String svcCd) {

		try {
			List<PauseHistory> list = new ArrayList<PauseHistory>();

			PauseHistoryCollection pauseHistoryCollection = new PauseHistoryCollection();

			RetrieveUseSvcListServiceStub.DsInputInVO dataIn = new RetrieveUseSvcListServiceStub.DsInputInVO();
			dataIn.setSSearchCb("01");
			dataIn.setSSrchValue(no);

			RetrieveUseSvcListServiceStub.ResponseBody resBody_ = retrieveUseSvcList(dataIn);
			RetrieveUseSvcListServiceStub.DsUseSvcListOutVO[] dsUseSvcListOut = resBody_
					.getDsUseSvcListOutVO();
			if (dsUseSvcListOut != null) {
				for (int i = 0; i < dsUseSvcListOut.length; i++) {
					// if
					// (found.getProdNo().equals(dsSusHistListOut[i].getProdNo()))
					// {
					PauseHistory pauseHistory = new PauseHistory();
					pauseHistory.setEntrSttsValdStrtDt(dsUseSvcListOut[i].getEntrSttsValdStrtDt()); // 정지희망일자
					pauseHistory.setEntrSttsValdEndDt(dsUseSvcListOut[i].getEntrSttsValdEndDt()); //가입상태종료일자
					pauseHistory.setEntrSttsChngRsnDtlNm(dsUseSvcListOut[i].getEntrSttsChngRsnDtlNm());
					pauseHistory.setProdNm(dsUseSvcListOut[i].getProdNm());
					pauseHistory.setProdNo(dsUseSvcListOut[i].getProdNo());
					pauseHistory.setEntrSttsNm(dsUseSvcListOut[i].getEntrSttsNm());
					pauseHistory.setEntrSttsChngSeqno(dsUseSvcListOut[i].getEntrSttsChngSeqno());

					list.add(pauseHistory);

					logger.debug("retrieveUseSvcList in getPauseHistory({}, {}) \n{}", no, svcCd,
							printFields(dsUseSvcListOut[i]));
					// }
				}
			}

			pauseHistoryCollection.setList(list);

			logger.debug("getPauseHistory({}, {}) is \n{}", no, svcCd, pauseHistoryCollection.toString());

			return pauseHistoryCollection;
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 서비스일시정지 > 일시정지 내역조회(서비스명, 상태, 정지 사유, 정지 기간, 정지 일 수)
	 * 
	 * 예) 가입상태유효시작일자(정지희망일자) - "", 가입상태유효종료일자 - "", 상태변경상세사유코드명 - "", 서비스명 - ""
	 * 
	 * @param no
	 * @param svcCd
	 * @return
	 */
	public PauseLiftHistoryCollection getPauseLiftHistory(String no, String svcCd) {

		try {
			List<PauseLiftHistory> list = new ArrayList<PauseLiftHistory>();

			PauseLiftHistoryCollection pauseLiftHistoryCollection = new PauseLiftHistoryCollection();

			RetrieveSusSvcListServiceStub.DsInputInVO dataIn = new RetrieveSusSvcListServiceStub.DsInputInVO();
			dataIn.setSSearchCb("01");
			dataIn.setSSrchValue(no);

			RetrieveSusSvcListServiceStub.ResponseBody resBody_ = retrieveSusSvcList(dataIn);
			RetrieveSusSvcListServiceStub.DsOutputOutVO[] dsSusSvcListOut = resBody_.getDsOutputOutVO();
			if (dsSusSvcListOut != null) {
				for (int i = 0; i < dsSusSvcListOut.length; i++) {
					// if
					// (found.getProdNo().equals(dsSusHistListOut[i].getProdNo()))
					// {
					PauseLiftHistory pauseLiftHistory = new PauseLiftHistory();
					pauseLiftHistory.setEntrSttsValdStrtDt(dsSusSvcListOut[i].getEntrSttsValdStrtDt()); // 정지희망일자
					pauseLiftHistory.setEntrSttsValdEndDt(dsSusSvcListOut[i].getEntrSttsValdEndDt()); //가입상태종료일자
					pauseLiftHistory.setEntrSttsChngRsnDtlNm(dsSusSvcListOut[i].getEntrSttsChngRsnDtlNm());
					pauseLiftHistory.setProdNm(dsSusSvcListOut[i].getProdNm());
					pauseLiftHistory.setProdNo(dsSusSvcListOut[i].getProdNo());
					pauseLiftHistory.setEntrSttsNm(dsSusSvcListOut[i].getEntrSttsNm());

					list.add(pauseLiftHistory);

					logger.debug("retrieveSusSvcList in getPauseHistory({}, {}) \n{}", no, svcCd,
							printFields(dsSusSvcListOut[i]));
					// }
				}
			}

			pauseLiftHistoryCollection.setList(list);

			logger.debug("getPauseHistory({}, {}) is \n{}", no, svcCd, pauseLiftHistoryCollection.toString());

			return pauseLiftHistoryCollection;
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return null;
	}
	
	// /**
	// * 고객전용입금계좌
	// *
	// * 예)
	// *
	// * @param no
	// * @param svcCd
	// * @return
	// */
	// public DepositAccountCollectionVO getDepositAccount(String no, String
	// svcCd)
	// {
	//
	// try {
	// // type 구분 VARCHAR2 1 N 단수 "A:가입번호,B:결합번호,C:청구계정번호,D:상품번호,E:홈코드번호"
	// // no 번호 VARCHAR2 20 N 단수
	//
	// RetrieveCustSvcEntrInfoBDServiceStub.DsInputInVO dsReqIn = new
	// RetrieveCustSvcEntrInfoBDServiceStub.DsInputInVO();
	// dsReqIn.setType("A");
	// dsReqIn.setNo(no);
	//
	// RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO found = null;
	// RetrieveCustSvcEntrInfoBDServiceStub.ResponseBody resBody =
	// retrieveCustSvcEntrInfoBD(dsReqIn);
	// RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO[] dsOutputOut =
	// resBody.getDsOutputOutVO();
	// for (int i = 0; i < dsOutputOut.length; i++) {
	// // if (svcCd.equals(dsOutputOut[i].getSvcCd())) {
	// found = dsOutputOut[i];
	//
	// logger.debug("retrieveCustSvcEntrInfoBD in getDepositAccount({}, {})
	// \n{}",
	// no, svcCd,
	// printFields(dsOutputOut[i]));
	// break;
	// // }
	// }
	//
	// if (found == null) {
	// logger.debug("retrieveCustSvcEntrInfoBD in getDepositAccount({}, {}) not
	// found", no, svcCd);
	// return null;
	// }
	//
	// List<DepositAccountVO> list = new ArrayList<DepositAccountVO>();
	//
	// DepositAccountCollectionVO depositAccountCollectionVO = new
	// DepositAccountCollectionVO();
	// depositAccountCollectionVO.setBillAcntNo(found.getBillAcntNo());
	//
	// // custMgmtNo 고객관리번호 VARCHAR2 12 N 단수 청구계정번호/고객번호
	// // entrNo 가입번호 NUMBER 12 Y 단수
	// // prodNo 전화번호 NUMBER 12 Y 단수
	// RetrieveVtAcntServiceStub.DsReqInVO dsReqIn_ = new
	// RetrieveVtAcntServiceStub.DsReqInVO();
	// dsReqIn_.setCustMgmtNo(found.getBillAcntNo());
	//
	// RetrieveVtAcntServiceStub.ResponseBody resBody_ =
	// retrieveVtAcnt(dsReqIn_);
	// RetrieveVtAcntServiceStub.DsResOutVO[] dsResOut_ =
	// resBody_.getDsResOutVO();
	// if (dsResOut_ != null) {
	// for (int i = 0; i < dsResOut_.length; i++) {
	// DepositAccountVO depositAccountVO = new DepositAccountVO();
	// depositAccountVO.setBankNm(dsResOut_[i].getBankNm());// 은행명
	// depositAccountVO.setVtAcntNo(dsResOut_[i].getVtAcntNo()); // 입금전용계좌
	// depositAccountVO.setBankCd(dsResOut_[i].getBankCd()); // 은행코드
	//
	// list.add(depositAccountVO);
	//
	// logger.debug("retrieveVtAcnt in getDepositAccount({}, {}, {}) \n{}", no,
	// svcCd,
	// printFields(dsResOut_[i]));
	// }
	// }
	//
	// depositAccountCollectionVO.setList(list);
	//
	// logger.debug("getDepositAccount({}, {}) is \n{}", no, svcCd,
	// depositAccountCollectionVO.toString());
	//
	// return depositAccountCollectionVO;
	// } catch (Exception e) {
	// logger.debug("{}", e.getMessage());
	//
	// e.printStackTrace();
	// }
	//
	// return null;
	// }

	/**
	 * 고객정보조회
	 * 
	 * 예)
	 * 
	 * @param no
	 * @param svcCd
	 * @return
	 */
	@SuppressWarnings("unused")
	public RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO getCustomerInfo(String no, String svcCd) {

		try {
			// type 구분 VARCHAR2 1 N 단수 "A:가입번호,B:결합번호,C:청구계정번호,D:상품번호,E:홈코드번호"
			// no 번호 VARCHAR2 20 N 단수

			RetrieveCustSvcEntrInfoBDServiceStub.DsInputInVO dsReqIn = new RetrieveCustSvcEntrInfoBDServiceStub.DsInputInVO();
			dsReqIn.setType("A");
			dsReqIn.setNo(no);

			RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO found = null;
			RetrieveCustSvcEntrInfoBDServiceStub.ResponseBody resBody = retrieveCustSvcEntrInfoBD(dsReqIn);
			RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO[] dsOutputOut = resBody.getDsOutputOutVO();
			for (int i = 0; i < dsOutputOut.length; i++) {
				// if (svcCd.equals(dsOutputOut[i].getSvcCd())) {
				found = dsOutputOut[i];

				logger.debug("retrieveCustSvcEntrInfoBD in getPayInfo({}, {}) \n{}", no, svcCd,
						printFields(dsOutputOut[i]));
				break;
				// }
			}

			logger.debug("getCustomerInfo({}, {}) is \n{}", no, svcCd, printFields(found));

			return found;
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 고객정보조회
	 * 
	 * 예)
	 * 
	 * @param no
	 * @return
	 */
	public RetrieveCustInfoSvcServiceStub.DsConfldsOutVO getBillInfo(String no) {

		try {
			// entrNo 가입번호 VARCHAR2 10 Y 단수
			// mode 모드 VARCHAR2 1 N 단수 "E : 가입번호 P : 전화번호 """" : 가입번호,이력번호"
			// prodNo 전화번호 VARCHAR2 20 Y 단수

			RetrieveCustInfoSvcServiceStub.DsReqInVO dsReqIn = new RetrieveCustInfoSvcServiceStub.DsReqInVO();
			dsReqIn.setEntrNo(no);
			dsReqIn.setMode("E");

			RetrieveCustInfoSvcServiceStub.ResponseBody resBody = retrieveCustInfoSvc(dsReqIn);
			RetrieveCustInfoSvcServiceStub.DsConfldsOutVO dsConfldsOut = resBody.getDsConfldsOutVO();

			return dsConfldsOut;
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 고객정보조회(상태)
	 * 
	 * 예)
	 * 
	 * @param no
	 * @return
	 */

	public RetrieveCustInfoSvcServiceStub.DsCustInfoOutVO getStateInfo(String no) {
		try {
			// entrNo 가입번호 VARCHAR2 10 Y 단수
			// mode 모드 VARCHAR2 1 N 단수 "E : 가입번호 P : 전화번호 """" : 가입번호,이력번호"
			// prodNo 전화번호 VARCHAR2 20 Y 단수

			RetrieveCustInfoSvcServiceStub.DsReqInVO dsReqIn = new RetrieveCustInfoSvcServiceStub.DsReqInVO();
			dsReqIn.setEntrNo(no);
			dsReqIn.setMode("E");

			RetrieveCustInfoSvcServiceStub.ResponseBody resBody = retrieveCustInfoSvc(dsReqIn);
			RetrieveCustInfoSvcServiceStub.DsCustInfoOutVO dsCustInfoOut = resBody.getDsCustInfoOutVO();

			return dsCustInfoOut;
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 할인정보조회(요금제변경)
	 * 
	 * 예)
	 * 
	 * @param no
	 * @return
	 */
	public RetrieveDscntMgmtServiceStub.DsDscntListOutVO[] getDiscountInfo(String no) {

		try {
			RetrieveDscntMgmtServiceStub.DsSrchInfoInVO[] dsReqIn = new RetrieveDscntMgmtServiceStub.DsSrchInfoInVO[1];
			dsReqIn[0] = new RetrieveDscntMgmtServiceStub.DsSrchInfoInVO();
			dsReqIn[0].setMode("LIST");
			dsReqIn[0].setEntrNo(no);

			RetrieveDscntMgmtServiceStub.ResponseBody resBody = retrieveDscntMgmt(dsReqIn);
			RetrieveDscntMgmtServiceStub.DsDscntListOutVO[] dsDscntListOut = resBody.getDsDscntListOutVO();
			for (int i = 0; i < dsDscntListOut.length; i++) {
				// if (no.equals(dsDscntListOut[i].getEntrNo())) {
				logger.debug("getDiscountInfo({}) is \n{}", no, printFields(dsDscntListOut[i]));
			}

			return dsDscntListOut;
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 요금제변경(할인만료)
	 * 
	 * 예)
	 * 
	 * @param mode
	 * @param entrNo
	 * @param prodNo
	 * @param entrByDscntSeqno
	 * @param svcCd
	 * @param svcDpndRelsCd
	 * @param dscntSvcCd
	 * @param entrDscntKdCd
	 * @param dscntSttsKdCd
	 * @param dscntStrtDttm
	 * @param dscntEndDttm
	 * @param dscntStrtKdCd
	 * @param dscntEndKdCd
	 * @param agmtStrtDttm
	 * @param agmtEndDttm
	 * @param agmtMnbr
	 * @return
	 */
	public DiscountInfoResultVO updateDiscountInfo(String mode, String entrNo, String prodNo, String entrByDscntSeqno,
			String svcCd, String svcDpndRelsCd, String dscntSvcCd, String entrDscntKdCd, String dscntSttsKdCd,
			String dscntStrtDttm, String dscntEndDttm, String dscntStrtKdCd, String dscntEndKdCd, String agmtStrtDttm,
			String agmtEndDttm, String agmtMnbr) {

		try {
			DiscountInfoResultVO discountInfoResultVO = new DiscountInfoResultVO();

			// mode 작업구분 VARCHAR2 14 N 복수 (LIST:조회,SAVE_ACT:할인등록,SAVE_EXP:할인만기)
			// entrNo 가입번호 VARCHAR2 14 Y 복수 상품번호 존재시 NULL가능
			// prodNo 상품번호 VARCHAR2 14 Y 복수 가입번호 존재시 NULL가능
			// entrByDscntSeqno entrByDscntSeqno VARCHAR2 14 Y 복수 SAVE_EXP:할인만기시
			// 필수
			// svcCd 상품코드 VARCHAR2 14 Y 복수 종속할인적용시 필수
			// svcDpndRelsCd 상품종속관계코드 VARCHAR2 14 Y 복수 미존재시 SMD/PMD
			// dscntSvcCd 할인코드 VARCHAR2 14 Y 복수 할인코드 존재시 상품코드 무시
			// entrDscntKdCd 할인유형코드 VARCHAR2 14 Y 복수 미존재시 GEN
			// dscntSttsKdCd 할인상태코드 VARCHAR2 14 Y 복수 (미입력시,등록-ACT,만기-EXP)
			// dscntStrtDttm 할인시작일자 VARCHAR2 14 Y 복수 dscntStrtKdCd 미입력시당일시작
			// dscntEndDttm 할인종료일자 VARCHAR2 14 Y 복수 (SAVE_ACT:dscntEndKdCd
			// 미입력시99991231,SAVE_EXP:할인만기시 필수)
			// dscntStrtKdCd 할인시작구분 VARCHAR2 14 Y 복수 (A:즉시(당일),B:익월1일,M1:1개월후부터
			// 시작,M3:3개월후부터 시작)
			// dscntEndKdCd 할인종료구분 VARCHAR2 14 Y 복수 (A:무제한,M3:3개월간 적용,D30:30일간
			// 적용)
			// agmtStrtDttm 약정시작일자 VARCHAR2 14 Y 복수
			// agmtEndDttm 약정종료일자 VARCHAR2 14 Y 복수
			// agmtMnbr 할인종료구분 VARCHAR2 14 Y 복수

			RetrieveDscntMgmtServiceStub.DsSrchInfoInVO[] dsSrchInfoIn = new RetrieveDscntMgmtServiceStub.DsSrchInfoInVO[1];
			dsSrchInfoIn[0] = new RetrieveDscntMgmtServiceStub.DsSrchInfoInVO();
			dsSrchInfoIn[0].setMode(mode);
			dsSrchInfoIn[0].setEntrNo(entrNo);
			dsSrchInfoIn[0].setProdNo(prodNo);
			dsSrchInfoIn[0].setEntrByDscntSeqno(entrByDscntSeqno);
			dsSrchInfoIn[0].setSvcCd(svcCd);
			dsSrchInfoIn[0].setSvcDpndRelsCd(svcDpndRelsCd);
			dsSrchInfoIn[0].setDscntSvcCd(dscntSvcCd);
			dsSrchInfoIn[0].setEntrDscntKdCd(entrDscntKdCd);
			dsSrchInfoIn[0].setDscntSttsKdCd(dscntSttsKdCd);
			dsSrchInfoIn[0].setDscntStrtDttm(dscntStrtDttm);
			dsSrchInfoIn[0].setDscntEndDttm(dscntEndDttm);
			dsSrchInfoIn[0].setDscntStrtKdCd(dscntStrtKdCd);
			dsSrchInfoIn[0].setDscntEndKdCd(dscntEndKdCd);
			dsSrchInfoIn[0].setAgmtStrtDttm(agmtStrtDttm);
			dsSrchInfoIn[0].setAgmtEndDttm(agmtEndDttm);
			dsSrchInfoIn[0].setAgmtMnbr(agmtMnbr);

			RetrieveDscntMgmtServiceStub.ResponseBody resBody = retrieveDscntMgmt(dsSrchInfoIn);
			RetrieveDscntMgmtServiceStub.DsRqstRsltOutVO[] dsRqstRsltOut = resBody.getDsRqstRsltOutVO();
			discountInfoResultVO.setPrssYn(dsRqstRsltOut[0].getPrssYn());
			discountInfoResultVO.setErrCode(dsRqstRsltOut[0].getErrCode());
			discountInfoResultVO.setErrMsg(dsRqstRsltOut[0].getErrMsg());

			logger.debug("updateDiscountInfo({}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}) is {}",
					mode, entrNo, prodNo, entrByDscntSeqno, svcCd, svcDpndRelsCd, dscntSvcCd, entrDscntKdCd,
					dscntSttsKdCd, dscntStrtDttm, dscntEndDttm, dscntStrtKdCd, dscntEndKdCd, agmtStrtDttm, agmtEndDttm,
					agmtMnbr, discountInfoResultVO.toString());

			return discountInfoResultVO;
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 계좌 및 카드 인증 및 이름 인증
	 *
	 * 예)
	 *
	 * @param acntOwnrNo
	 * @param custDvCd
	 * @param custKdCd
	 * @param persIdind
	 * @param pymMthdCd
	 * @param bankCd
	 * @param bankAcntNo
	 * @param cdcmpCd
	 * @param cardNo
	 * @param cardValdEndYymm
	 * @param acntOwnrNm
	 * @param mode
	 * @return
	 */
	public PayMethodAuthVO getPayMethod(String acntOwnrNo, String custDvCd, String custKdCd, String persIdind,
			String pymMthdCd, String bankCd, String bankAcntNo, String cdcmpCd, String cardNo, String cardValdEndYymm,
			String acntOwnrNm, String mode) {

		try {
			PayMethodAuthVO payMethodAuthVO = new PayMethodAuthVO();

			// acntOwnrNo 소유자번호 VARCHAR2 13 N 단수 주민번호,사업자번호,외국인번호,외국인거소번호등
			// custDvCd 고객구분 VARCHAR2 10 N 단수 "개인:I,법인:G"
			// custKdCd 고객유형 VARCHAR2 2 N 단수 VW_CS_HB_CUST_KD_01 참조
			// persIdind 외국인여부 VARCHAR2 10 Y 단수 외국인:Y
			// pymMthdCd 납부방법 VARCHAR2 10 Y 단수 "자동이체:CM,신용카드:CC,지로:GR"
			// bankCd 은행코드 VARCHAR2 10 Y 단수 자동이체시 필수
			// bankAcntNo 은행계좌번호 VARCHAR2 21 Y 단수 자동이체시 필수
			// cdcmpCd 카드타입 VARCHAR2 10 Y 단수 신용카드시 필수
			// cardNo 카드번호 VARCHAR2 44 Y 단수 신용카드시 필수
			// cardValdEndYymm 카드유효기간 VARCHAR2 10 Y 단수 "신용카드시 필수YYYYMM 형식"
			// acntOwnrNm 소유자명 VARCHAR2 250 N 단수 소유주명
			// mode 소유자명 VARCHAR2 60 N 단수 "N : 인증시 계좌/카드가 납부자 소유인지 체크한다. (환불계좌
			// 인증시사용)
			// P : 인증시 계좌/카드와 납부자 소유를 체크하지 않는다. (납부계정 등록시 사용)"

			RetrieveAythOrNameSvcServiceStub.RqstDataInVO rqstDataIn = new RetrieveAythOrNameSvcServiceStub.RqstDataInVO();
			rqstDataIn.setAcntOwnrNm(acntOwnrNo);
			rqstDataIn.setCustDvCd(custDvCd);
			rqstDataIn.setCustKdCd(custKdCd);
			rqstDataIn.setPersIdind(persIdind);
			rqstDataIn.setPymMthdCd(pymMthdCd);
			rqstDataIn.setBankCd(bankCd);
			rqstDataIn.setBankAcntNo(bankAcntNo);
			rqstDataIn.setCdcmpCd(cdcmpCd);
			rqstDataIn.setCardNo(cardNo);
			rqstDataIn.setCardValdEndYymm(cardValdEndYymm);
			rqstDataIn.setAcntOwnrNm(acntOwnrNm);
			rqstDataIn.setMode(mode);

			RetrieveAythOrNameSvcServiceStub.ResponseBody resBody = retrieveAythOrNameSvc(rqstDataIn);
			RetrieveAythOrNameSvcServiceStub.ResultInfoOutVO resultInfoOut = resBody.getResultInfoOutVO();
			payMethodAuthVO.setMsgCode(resultInfoOut.getMsgCode());// 결과코드
			payMethodAuthVO.setMsgText(resultInfoOut.getMsgText());// 결과메시지
			payMethodAuthVO.setCmsResultCode(resultInfoOut.getCmsResultCode());// cms결과코드

			logger.debug("getPayInfo({}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}) is \n{}", acntOwnrNo, custDvCd,
					custKdCd, persIdind, pymMthdCd, bankCd, bankAcntNo, cdcmpCd, cardNo, cardValdEndYymm, acntOwnrNm,
					mode, payMethodAuthVO.toString());

			return payMethodAuthVO;
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 납부방법변경
	 * 
	 * 예)
	 * 
	 * @param aceno
	 * @param acntOwnrNo
	 * @param actInd
	 * @param bankAcntNo
	 * @param bankCd
	 * @param bankNm
	 * @param billAcntNo
	 * @param bltxtKdCd
	 * @param cardNo
	 * @param cardValdEndYymm
	 * @param cdcmpCd
	 * @param cdcmpNm
	 * @param custDvCd
	 * @param custKdCd
	 * @param entrNo
	 * @param newInd
	 * @param persIdind
	 * @param ppayAcntYn
	 * @param pymAcntNo
	 * @param pymCustNm
	 * @param pymManCustNo
	 * @param pymMthdCd
	 * @param pymMthdNm
	 * @return
	 */
	public PayMethodResultVO updatePayMethod(String aceno, String acntOwnrNo, String actInd, String bankAcntNo,
			String bankCd, String bankNm, String billAcntNo, String bltxtKdCd, String cardNo, String cardValdEndYymm,
			String cdcmpCd, String cdcmpNm, String custDvCd, String custKdCd, String entrNo, String newInd,
			String persIdind, String ppayAcntYn, String pymAcntNo, String pymCustNm, String pymManCustNo,
			String pymMthdCd, String pymMthdNm) {

		try {
			PayMethodResultVO payMethodResultVO = new PayMethodResultVO();

			// aceno 가입계약번호 VARCHAR2 12 Y 단수
			// acntOwnrNo 소유자번호 VARCHAR2 13 N 단수 주민번호,사업자번호,외국인번호,외국인거소번호등
			// actInd actInd VARCHAR2 1 Y 단수 null(후순위만 Y이나 후순위 삭제함)
			// bankAcntNo 은행계좌번호 VARCHAR2 16 Y 단수 자동이체인 경우 필수
			// bankCd 은행코드 VARCHAR2 3 Y 단수 자동이체인 경우 필수
			// bankNm 은행명 VARCHAR2 60 Y 단수
			// billAcntNo 청구계정번호 VARCHAR2 12 N 단수 납부계정을 변경하려는 청구계정번호
			// bltxtKdCd 청구유형 VARCHAR2 1 N 단수 고객유형이 GG(LG그룹사_관계사)가 아닌 경우 청구서유형이
			// Shot-mail(S)/SMS(M)/모바일(W) 면 지로로 변경 불가
			// cardNo 카드번호 VARCHAR2 16 Y 단수 신용카드인 경우 필수
			// cardValdEndYymm 유효일자 VARCHAR2 6 Y 단수 "YYYYMM신용카드인 경우 필수"
			// cdcmpCd 카드사코드 VARCHAR2 2 Y 단수 신용카드인 경우 필수
			// cdcmpNm 카드사 VARCHAR2 360 Y 단수
			// custDvCd 납부자고객구분 VARCHAR2 240 N 단수 개인:I,법인:G
			// custKdCd 납부자고객유형 VARCHAR2 240 N 단수 VW_CS_HB_CUST_KD_01 참조
			// entrNo 가입번호 VARCHAR2 12 N 단수 가족사랑,해피투게더의 경우 대표가입자만이
			// 납부계정을변경할수있어필수항목임
			// newInd newInd VARCHAR2 1 Y 단수 null(향후 사용위해 남겨둔 필드)
			// persIdind 외국인 여부 VARCHAR2 1 Y 단수 외국인:Y
			// ppayAcntYn 선불계정 구분 VARCHAR2 1 Y 단수 선불:Y
			// pymAcntNo 납부계정번호 VARCHAR2 12 Y 단수 없으면 NULL
			// pymCustNm 납부자 고객 명 VARCHAR2 360 N 단수 외국인 영어명 가능
			// pymManCustNo 납부자 고객 번호 VARCHAR2 15 Y 단수 사용안함
			// pymMthdCd 납부방법 VARCHAR2 2 N 단수
			// "자동이체:CM,신용카드:CC,지로:GR,납부정보변경(CM->CM,CC->CC):즉시변경
			// 납부방법변경(그외):예약변경"
			// pymMthdNm 납부방법명 VARCHAR2 30 Y 단수 자동이체,신용카드,지로

			SavePymAcntSvcServiceStub.DsReqInVO dsReqIn = new SavePymAcntSvcServiceStub.DsReqInVO();
			dsReqIn.setAceno(aceno);
			dsReqIn.setAcntOwnrNo(acntOwnrNo);
			dsReqIn.setActInd(actInd);
			dsReqIn.setBankAcntNo(bankAcntNo);
			dsReqIn.setBankCd(bankCd);
			dsReqIn.setBankNm(bankNm);
			dsReqIn.setBillAcntNo(billAcntNo);
			dsReqIn.setBltxtKdCd(bltxtKdCd);
			dsReqIn.setCardNo(cardNo);
			dsReqIn.setCardValdEndYymm(cardValdEndYymm);
			dsReqIn.setCdcmpCd(cdcmpCd);
			dsReqIn.setCdcmpNm(cdcmpNm);
			dsReqIn.setCustDvCd(custDvCd);
			dsReqIn.setCustKdCd(custKdCd);
			dsReqIn.setEntrNo(entrNo);
			dsReqIn.setNewInd(newInd);
			dsReqIn.setPersIdind(persIdind);
			dsReqIn.setPpayAcntYn(ppayAcntYn);
			dsReqIn.setPymAcntNo(pymAcntNo);
			dsReqIn.setPymCustNm(pymCustNm);
			dsReqIn.setPymManCustNo(pymManCustNo);
			dsReqIn.setPymMthdCd(pymMthdCd);
			dsReqIn.setPymMthdNm(pymMthdNm);

			SavePymAcntSvcServiceStub.ResponseBody resBody = savePymAcntSvc(dsReqIn);
			SavePymAcntSvcServiceStub.DsResOutVO dsResOut = resBody.getDsResOutVO();
			payMethodResultVO.setMsgCode(dsResOut.getMsgCode());// 결과코드
			payMethodResultVO.setMsgText(dsResOut.getMsgText());// 결과메시지

			logger.debug(
					"getPayInfo({}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}) is \n{}",
					aceno, acntOwnrNo, actInd, bankAcntNo, bankCd, bankNm, billAcntNo, bltxtKdCd, cardNo,
					cardValdEndYymm, cdcmpCd, cdcmpNm, custDvCd, custKdCd, entrNo, newInd, persIdind, ppayAcntYn,
					pymAcntNo, pymCustNm, pymManCustNo, pymMthdCd, pymMthdNm, payMethodResultVO.toString());

			return payMethodResultVO;
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return null;
	}

	// /**
	// * 인터넷수납
	// *
	// * 예)
	// *
	// * @param prodNo
	// * @param ccrdAprvRqstAmt
	// * @param cardNo
	// * @param ccrdCompCd
	// * @param ccrdValdEndDt
	// * @param ccrdIndvCoDvCd
	// * @param ccrdOwnrPersNo
	// * @param ccrdStlmInsttMms
	// * @param ccrdStlmAprvRsltCd
	// * @param operatingDealer
	// * @param sourceType
	// * @param custBankCode
	// * @param bankAcctNo
	// * @param pymCustNm
	// * @return
	// */
	// public PayResultVO updatePay(String prodNo, String ccrdAprvRqstAmt,
	// String
	// cardNo, String ccrdCompCd,
	// String ccrdValdEndDt, String ccrdIndvCoDvCd, String ccrdOwnrPersNo,
	// String
	// ccrdStlmInsttMms,
	// String ccrdStlmAprvRsltCd, String operatingDealer, String sourceType,
	// String
	// custBankCode,
	// String bankAcctNo, String pymCustNm) {
	//
	// try {
	// PayResultVO payResultVO = new PayResultVO();
	//
	// // prodNo 상품번호 VARCHAR2 20 N 단수 -
	// // ccrdAprvRqstAmt 금액 NUMBER 13 N 단수 Default : 0
	// // cardNo 카드번호 VARCHAR2 16 Y 단수 -
	// // ccrdCompCd 카드사코드 VARCHAR2 2 Y 단수 -
	// // ccrdValdEndDt 카드유효종료일자 VARCHAR2 6 Y 단수 -
	// // ccrdIndvCoDvCd 개인/법인 VARCHAR2 1 Y 개인: 0 법인: 1 Default : 0
	// // ccrdOwnrPersNo 고객주민번호 VARCHAR2 13 N -
	// // ccrdStlmInsttMms 카드할부개월수 NUMBER 13 Y Default : 0
	// // ccrdStlmAprvRsltCd 카드승인결과코드 VARCHAR2 2 Y -
	// // operatingDealer 처리자 VARCHAR2 6 Y -
	// // sourceType 구분코드 VARCHAR2 1 N 카드결제:C 계좌이체:R
	// // custBankCode 은행코드 VARCHAR2 3 Y -
	// // bankAcctNo 계좌번호 VARCHAR2 16 Y -
	// // pymCustNm 납부자명 VARCHAR2 100 Y -
	//
	// SaveArPymIntServiceStub.DsReqInVO dsReqIn = new
	// SaveArPymIntServiceStub.DsReqInVO();
	// dsReqIn.setProdNo(prodNo);
	// dsReqIn.setCcrdAprvRqstAmt(ccrdAprvRqstAmt);
	// dsReqIn.setCardNo(cardNo);
	// dsReqIn.setCcrdCompCd(ccrdCompCd);
	// dsReqIn.setCcrdValdEndDt(ccrdValdEndDt);
	// dsReqIn.setCcrdIndvCoDvCd(ccrdIndvCoDvCd);
	// dsReqIn.setCcrdOwnrPersNo(ccrdOwnrPersNo);
	// dsReqIn.setCcrdStlmInsttMms(ccrdStlmInsttMms);
	// dsReqIn.setCcrdStlmAprvRsltCd(ccrdStlmAprvRsltCd);
	// dsReqIn.setOperatingDealer(operatingDealer);
	// dsReqIn.setSourceType(sourceType);
	// dsReqIn.setCustBankCode(custBankCode);
	// dsReqIn.setBankAcctNo(bankAcctNo);
	// dsReqIn.setPymCustNm(pymCustNm);
	//
	// SaveArPymIntServiceStub.ResponseBody resBody = saveArPymInt(dsReqIn);
	// SaveArPymIntServiceStub.DsResOutVO dsResOut = resBody.getDsResOutVO();
	// payResultVO.setCardNo(dsResOut.getCardNo());// 카드번호
	// payResultVO.setCcrdAprvRqstAmt(dsResOut.getCcrdAprvRqstAmt());// 금액
	// payResultVO.setCcrdCompCd(dsResOut.getCcrdCompCd());// 카드사코드
	// payResultVO.setCcrdIndvCoDvCd(dsResOut.getCcrdIndvCoDvCd());// 개인/법인
	// payResultVO.setCcrdOwnrPersNo(dsResOut.getCcrdOwnrPersNo());// 고객주민번호
	// payResultVO.setCcrdStlmAprvRsltCd(dsResOut.getCcrdStlmAprvRsltCd());//
	// 카드승인결과코드
	// payResultVO.setCcrdStlmInsttMms(dsResOut.getCcrdStlmInsttMms());//
	// 카드할부개월수
	// payResultVO.setCcrdValdEndDt(dsResOut.getCcrdValdEndDt());// 카드유효종료일자
	// payResultVO.setOperatingDealer(dsResOut.getOperatingDealer());// 처리자
	// payResultVO.setProdNo(dsResOut.getProdNo());// 상품번호
	// payResultVO.setResultCode(dsResOut.getResultCode());// 결과코드
	//
	// logger.debug("updatePay({}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {},
	// {},
	// {}) is \n{}", prodNo,
	// ccrdAprvRqstAmt, cardNo, ccrdCompCd, ccrdValdEndDt, ccrdIndvCoDvCd,
	// ccrdOwnrPersNo,
	// ccrdStlmInsttMms, ccrdStlmAprvRsltCd, operatingDealer, sourceType,
	// custBankCode, bankAcctNo,
	// pymCustNm, payResultVO.toString());
	//
	// return payResultVO;
	// } catch (Exception e) {
	// logger.debug("{}", e.getMessage());
	//
	// e.printStackTrace();
	// }
	//
	// return null;
	// }

	/**
	 * 일시정지예약신청
	 * 
	 * 예)
	 * 
	 * @param entrNo
	 * @param entrSttsChngSeqno
	 * @param custNo
	 * @param prodNo
	 * @param billAcntNo
	 * @param entrSttsValdStrtDt
	 * @param entrSttsValdEndDt
	 * @param rspEntrSttsValdEndDt
	 * @param rspEntrSttsChngRsnDtlCd
	 * @param rgstDt
	 * @param entrSttsChngRsnCd
	 * @param entrSttsChngRsnDtlCd
	 * @param prssYn
	 * @param prssDlrCd
	 * @param aplyLvlCd
	 * @param msgParmCnt
	 * @param memoCrteDlrCd
	 * @param rsnCd
	 * @param rsnDtlCd
	 * @param sendPhbYn
	 * @param icallPhbYn
	 * @param msgParm1
	 * @param msgParm2
	 * @param msgParm3
	 * @param userMemo
	 * @return
	 */
	public PauseResultVO updatePause(String entrNo, String entrSttsChngSeqno, String custNo, String prodNo,
			String billAcntNo, String entrSttsValdStrtDt, String entrSttsValdEndDt, String rspEntrSttsValdEndDt,
			String rspEntrSttsChngRsnDtlCd, String rgstDt, String entrSttsChngRsnCd, String entrSttsChngRsnDtlCd,
			String prssYn, String prssDlrCd, String aplyLvlCd, String msgParmCnt, String memoCrteDlrCd, String rsnCd,
			String rsnDtlCd, String sendPhbYn, String icallPhbYn, String msgParm1, String msgParm2, String msgParm3,
			String userMemo) {

		try {
			PauseResultVO pauseResultVO = new PauseResultVO();

			// dsReqIn
			// entrNo 가입번호 NUMBER 12 N 복수
			// entrSttsChngSeqno 가입변경일련번호 VARCHAR2 10 복수 변경일경우 존재
			// custNo 고객번호 VARCHAR2 15 N 복수 실명고객번호
			// prodNo 상품번호 VARCHAR2 20 N 복수
			// billAcntNo 청구계정번호 VARCHAR2 10 N 복수
			// entrSttsValdStrtDt 처리일자 VARCHAR2 8 N 복수
			// entrSttsValdEndDt 일시정지희망일자 VARCHAR2 8 N 복수
			// rspEntrSttsValdEndDt 정지해제희망일자 VARCHAR2 8 복수 HS서비스는 필수
			// rspEntrSttsChngRsnDtlCd 정지해제상세사유코드 VARCHAR2 10 복수 정지예약과 함께
			// 해제예약할경우
			// 사유를 지정하여 넘김PR(정상적절차)
			// rgstDt 처리일자 VARCHAR2 8 N 복수 오늘일자
			// entrSttsChngRsnCd 가입상태변경사유코드 VARCHAR2 10 N 복수
			// entrSttsChngRsnDtlCd 가입상태변경상세사유코드 VARCHAR2 10 N 복수
			// prssYn 처리여부 VARCHAR2 1 N 복수 Y
			// prssDlrCd 처리대리점코드 VARCHAR2 8 N 복수 100000
			// aplyLvlCd 적용레벨 VARCHAR2 1 N 복수 C'-가입번호별 서비스 적용 레벨
			// msgParmCnt 파라미터갯수 VARCHAR2 1 N 복수 신규:3
			// memoCrteDlrCd 대리점코드 VARCHAR2 8 N 복수
			// rsnCd 사유코드 VARCHAR2 3 N 복수 신규:E19
			// rsnDtlCd 사유상세코드 VARCHAR2 4 N 복수 신규:4039
			// sendPhbYn 발신금지 여부 VARCHAR2 1 N 복수 Y/N
			// icallPhbYn 착신금지여부 VARCHAR2 1 N 복수 Y/N
			// msgParm1 파라미터1 VARCHAR2 30 N 복수 "일시정지/정지해제"
			// msgParm2 파라미터2 VARCHAR2 30 N 복수 일시정지예약일/일시정지해제예약일
			// msgParm3 파라미터3 VARCHAR2 30 N 복수 일시정지사유코드명/정지해제사유코드명
			// userMemo 사용자메모 VARCHAR2 800 복수

			CreateRsvSuspendOrRsSuspendInfoServiceStub.DsSttsRsvInVO[] dsSttsRsvIn = new CreateRsvSuspendOrRsSuspendInfoServiceStub.DsSttsRsvInVO[1];
			dsSttsRsvIn[0] = new CreateRsvSuspendOrRsSuspendInfoServiceStub.DsSttsRsvInVO();
			dsSttsRsvIn[0].setEntrNo(entrNo);
			dsSttsRsvIn[0].setEntrSttsChngSeqno(entrSttsChngSeqno);
			dsSttsRsvIn[0].setCustNo(custNo);
			dsSttsRsvIn[0].setProdNo(prodNo);
			dsSttsRsvIn[0].setBillAcntNo(billAcntNo);
			dsSttsRsvIn[0].setEntrSttsValdStrtDt(rgstDt);//entrSttsValdStrtDt --> rgstDt //규격에 맞추어 변경(처리 일자)
			dsSttsRsvIn[0].setEntrSttsValdEndDt(entrSttsValdStrtDt);//entrSttsValdEndDt --> entrSttsValdStrtDt //규격에 맞추어 변경(일시정지 시작 일자)
			dsSttsRsvIn[0].setRspEntrSttsValdEndDt(entrSttsValdStrtDt);//rspEntrSttsValdEndDt --> entrSttsValdEndDt //규격에 맞추어 변경(일시정지 종료 일자)
			dsSttsRsvIn[0].setRspEntrSttsChngRsnDtlCd(rspEntrSttsChngRsnDtlCd);
			dsSttsRsvIn[0].setRgstDt(rgstDt);
			dsSttsRsvIn[0].setEntrSttsChngRsnCd(entrSttsChngRsnCd);
			dsSttsRsvIn[0].setEntrSttsChngRsnDtlCd(entrSttsChngRsnDtlCd);
			dsSttsRsvIn[0].setPrssYn(prssYn);
			dsSttsRsvIn[0].setPrssDlrCd(prssDlrCd);
			dsSttsRsvIn[0].setAplyLvlCd(aplyLvlCd);
			dsSttsRsvIn[0].setMsgParmCnt(msgParmCnt);
			dsSttsRsvIn[0].setMemoCrteDlrCd(memoCrteDlrCd);
			dsSttsRsvIn[0].setRsnCd(rsnCd);
			dsSttsRsvIn[0].setRsnDtlCd(rsnDtlCd);
			dsSttsRsvIn[0].setSendPhbYn(sendPhbYn);
			dsSttsRsvIn[0].setIcallPhbYn(icallPhbYn);
			dsSttsRsvIn[0].setMsgParm1(msgParm1);
			dsSttsRsvIn[0].setMsgParm2(msgParm2);
			dsSttsRsvIn[0].setMsgParm3(msgParm3);
			dsSttsRsvIn[0].setUserMemo(userMemo);

			CreateRsvSuspendOrRsSuspendInfoServiceStub.ResponseBody resBody = createRsvSuspendOrRsSuspendInfo(
					dsSttsRsvIn);
			CreateRsvSuspendOrRsSuspendInfoServiceStub.ResultOutVO resultOut = resBody.getResultOutVO();
			pauseResultVO.setResult(resultOut.getResult());// 성공여부

			logger.debug(
					"updatePause({}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}) is \n{}",
					entrNo, entrSttsChngSeqno, custNo, prodNo, billAcntNo, entrSttsValdStrtDt, entrSttsValdEndDt,
					rspEntrSttsValdEndDt, rspEntrSttsChngRsnDtlCd, rgstDt, entrSttsChngRsnCd, entrSttsChngRsnDtlCd,
					prssYn, prssDlrCd, aplyLvlCd, msgParmCnt, memoCrteDlrCd, rsnCd, rsnDtlCd, sendPhbYn, icallPhbYn,
					msgParm1, msgParm2, msgParm3, userMemo, pauseResultVO.toString());

			return pauseResultVO;
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 일시정지예약신청
	 * 
	 * 예)
	 * 
	 * @param entrNo
	 * @param entrSttsChngSeqno
	 * @param custNo
	 * @param prodNo
	 * @param billAcntNo
	 * @param entrSttsValdStrtDt
	 * @param entrSttsValdEndDt
	 * @param rspEntrSttsValdEndDt
	 * @param rspEntrSttsChngRsnDtlCd
	 * @param rgstDt
	 * @param entrSttsChngRsnCd
	 * @param entrSttsChngRsnDtlCd
	 * @param prssYn
	 * @param prssDlrCd
	 * @param aplyLvlCd
	 * @param msgParmCnt
	 * @param memoCrteDlrCd
	 * @param rsnCd
	 * @param rsnDtlCd
	 * @param sendPhbYn
	 * @param icallPhbYn
	 * @param msgParm1
	 * @param msgParm2
	 * @param msgParm3
	 * @param userMemo
	 * @return
	 */
	public PauseResultVO updatePauseRsp(String entrNo, String entrSttsChngSeqno, String custNo, String prodNo,
			String billAcntNo, String entrSttsValdStrtDt, String entrSttsValdEndDt, String rspEntrSttsValdEndDt,
			String rspEntrSttsChngRsnDtlCd, String rgstDt, String entrSttsChngRsnCd, String entrSttsChngRsnDtlCd,
			String prssYn, String prssDlrCd, String aplyLvlCd, String msgParmCnt, String memoCrteDlrCd, String rsnCd,
			String rsnDtlCd, String sendPhbYn, String icallPhbYn, String msgParm1, String msgParm2, String msgParm3,
			String userMemo) {

		try {
			PauseResultVO pauseResultVO = new PauseResultVO();

			// dsReqIn
			// entrNo 가입번호 NUMBER 12 N 복수
			// entrSttsChngSeqno 가입변경일련번호 VARCHAR2 10 복수 변경일경우 존재
			// custNo 고객번호 VARCHAR2 15 N 복수 실명고객번호
			// prodNo 상품번호 VARCHAR2 20 N 복수
			// billAcntNo 청구계정번호 VARCHAR2 10 N 복수
			// entrSttsValdStrtDt 처리일자 VARCHAR2 8 N 복수
			// entrSttsValdEndDt 일시정지희망일자 VARCHAR2 8 N 복수
			// rspEntrSttsValdEndDt 정지해제희망일자 VARCHAR2 8 복수 HS서비스는 필수
			// rspEntrSttsChngRsnDtlCd 정지해제상세사유코드 VARCHAR2 10 복수 정지예약과 함께
			// 해제예약할경우
			// 사유를 지정하여 넘김PR(정상적절차)
			// rgstDt 처리일자 VARCHAR2 8 N 복수 오늘일자
			// entrSttsChngRsnCd 가입상태변경사유코드 VARCHAR2 10 N 복수
			// entrSttsChngRsnDtlCd 가입상태변경상세사유코드 VARCHAR2 10 N 복수
			// prssYn 처리여부 VARCHAR2 1 N 복수 Y
			// prssDlrCd 처리대리점코드 VARCHAR2 8 N 복수 100000
			// aplyLvlCd 적용레벨 VARCHAR2 1 N 복수 C'-가입번호별 서비스 적용 레벨
			// msgParmCnt 파라미터갯수 VARCHAR2 1 N 복수 신규:3
			// memoCrteDlrCd 대리점코드 VARCHAR2 8 N 복수
			// rsnCd 사유코드 VARCHAR2 3 N 복수 신규:E19
			// rsnDtlCd 사유상세코드 VARCHAR2 4 N 복수 신규:4039
			// sendPhbYn 발신금지 여부 VARCHAR2 1 N 복수 Y/N
			// icallPhbYn 착신금지여부 VARCHAR2 1 N 복수 Y/N
			// msgParm1 파라미터1 VARCHAR2 30 N 복수 "일시정지/정지해제"
			// msgParm2 파라미터2 VARCHAR2 30 N 복수 일시정지예약일/일시정지해제예약일
			// msgParm3 파라미터3 VARCHAR2 30 N 복수 일시정지사유코드명/정지해제사유코드명
			// userMemo 사용자메모 VARCHAR2 800 복수

			CreateRsvSuspendOrRsSuspendInfoServiceStub.DsSttsRsvInVO[] dsSttsRsvIn = new CreateRsvSuspendOrRsSuspendInfoServiceStub.DsSttsRsvInVO[1];
			dsSttsRsvIn[0] = new CreateRsvSuspendOrRsSuspendInfoServiceStub.DsSttsRsvInVO();
			dsSttsRsvIn[0].setEntrNo(entrNo);
			dsSttsRsvIn[0].setEntrSttsChngSeqno(entrSttsChngSeqno);
			dsSttsRsvIn[0].setCustNo(custNo);
			dsSttsRsvIn[0].setProdNo(prodNo);
			dsSttsRsvIn[0].setBillAcntNo(billAcntNo);
			dsSttsRsvIn[0].setEntrSttsValdStrtDt(rgstDt);//entrSttsValdStrtDt --> rgstDt //규격에 맞추어 변경(처리 일자)
			dsSttsRsvIn[0].setEntrSttsValdEndDt(entrSttsValdEndDt);//entrSttsValdEndDt --> entrSttsValdStrtDt //규격에 맞추어 변경(일시정지 시작 일자)
			dsSttsRsvIn[0].setRspEntrSttsValdEndDt(entrSttsValdEndDt);//rspEntrSttsValdEndDt --> entrSttsValdEndDt //규격에 맞추어 변경(일시정지 종료 일자)
			dsSttsRsvIn[0].setRspEntrSttsChngRsnDtlCd("PR");
			dsSttsRsvIn[0].setRgstDt(rgstDt);
			dsSttsRsvIn[0].setEntrSttsChngRsnCd("RSP");
			dsSttsRsvIn[0].setEntrSttsChngRsnDtlCd("CR");
			dsSttsRsvIn[0].setPrssYn(prssYn);
			dsSttsRsvIn[0].setPrssDlrCd(prssDlrCd);
			dsSttsRsvIn[0].setAplyLvlCd(aplyLvlCd);
			dsSttsRsvIn[0].setMsgParmCnt(msgParmCnt);
			dsSttsRsvIn[0].setMemoCrteDlrCd(memoCrteDlrCd);
			dsSttsRsvIn[0].setRsnCd(rsnCd);
			dsSttsRsvIn[0].setRsnDtlCd(rsnDtlCd);
			dsSttsRsvIn[0].setSendPhbYn(sendPhbYn);
			dsSttsRsvIn[0].setIcallPhbYn(icallPhbYn);
			dsSttsRsvIn[0].setMsgParm1(msgParm1);
			dsSttsRsvIn[0].setMsgParm2(msgParm2);
			dsSttsRsvIn[0].setMsgParm3(msgParm3);
			dsSttsRsvIn[0].setUserMemo(userMemo);

			CreateRsvSuspendOrRsSuspendInfoServiceStub.ResponseBody resBody = createRsvSuspendOrRsSuspendInfo(
					dsSttsRsvIn);
			CreateRsvSuspendOrRsSuspendInfoServiceStub.ResultOutVO resultOut = resBody.getResultOutVO();
			pauseResultVO.setResult(resultOut.getResult());// 성공여부

			logger.debug(
					"updatePause({}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}) is \n{}",
					entrNo, entrSttsChngSeqno, custNo, prodNo, billAcntNo, entrSttsValdStrtDt, entrSttsValdEndDt,
					rspEntrSttsValdEndDt, rspEntrSttsChngRsnDtlCd, rgstDt, entrSttsChngRsnCd, entrSttsChngRsnDtlCd,
					prssYn, prssDlrCd, aplyLvlCd, msgParmCnt, memoCrteDlrCd, rsnCd, rsnDtlCd, sendPhbYn, icallPhbYn,
					msgParm1, msgParm2, msgParm3, userMemo, pauseResultVO.toString());

			return pauseResultVO;
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 일시정지해제신청
	 * 
	 * 예)
	 * 
	 * @param 
	 * @return
	 */
	public UnpauseResultVO updateUnpause(String entrNo, String entrSttsChngRsnCd, String entrSttsChngRsnDtlCd,
			String entrSttsChngRsnDtlNm, String sendPhbYn, String icallPhbYn, String entrSttsValdStrtDt,
			String userMemo, String userDlrCd) {

		try {
			UnpauseResultVO unpauseResultVO = new UnpauseResultVO();

			// entrNo 가입번호 NUMBER 12 N 복수
			// entrSttsChngRsnCd 가입상태변경사유코드 VARCHAR2 10 N 복수
			// entrSttsChngRsnDtlCd 가입상태변경상세사유코드 VARCHAR2 10 N 복수
			// entrSttsChngRsnDtlNm
			// sendPhbYn 발신금지 여부 VARCHAR2 1 N 복수 Y/N
			// icallPhbYn 착신금지여부 VARCHAR2 1 N 복수 Y/N
			// entrSttsValdStrtDt 처리일자 VARCHAR2 8 N 복수
			// userMemo 사용자메모 VARCHAR2 800 복수

			SaveEntrSuspendHomepgServiceStub.DsSuspendInfoInVO[] dsSuspendInfoIn = new SaveEntrSuspendHomepgServiceStub.DsSuspendInfoInVO[1];
			dsSuspendInfoIn[0] = new SaveEntrSuspendHomepgServiceStub.DsSuspendInfoInVO();
			dsSuspendInfoIn[0].setEntrNo(entrNo);
			dsSuspendInfoIn[0].setEntrSttsChngRsnCd(entrSttsChngRsnCd);
			dsSuspendInfoIn[0].setEntrSttsChngRsnDtlCd(entrSttsChngRsnDtlCd);
			dsSuspendInfoIn[0].setEntrSttsChngRsnDtlNm(entrSttsChngRsnDtlNm);
			dsSuspendInfoIn[0].setSendPhbYn(sendPhbYn);
			dsSuspendInfoIn[0].setIcallPhbYn(icallPhbYn);
			dsSuspendInfoIn[0].setEntrSttsValdStrtDt(entrSttsValdStrtDt);
			dsSuspendInfoIn[0].setUserMemo(userMemo);
			dsSuspendInfoIn[0].setUserDlrCd(userDlrCd);

			SaveEntrSuspendHomepgServiceStub.ResponseBody resBody = saveEntrSuspendHomepgService(dsSuspendInfoIn);		  	   
			SaveEntrSuspendHomepgServiceStub.DsSusResultOutVO resultOut = resBody.getDsSusResultOutVO();
			unpauseResultVO.setResult(resultOut.getResult());
			
			logger.debug(
					"updateUnpause({}, {}, {}, {}, {}, {}, {}, {}, {}) is \n{}",
					entrNo,
					entrSttsChngRsnCd,
					entrSttsChngRsnDtlCd,
					entrSttsChngRsnDtlNm,
					sendPhbYn,
					icallPhbYn,
					entrSttsValdStrtDt,
					userMemo,
					userDlrCd, 
					unpauseResultVO.toString());

			return unpauseResultVO;
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 일시정지해제신청
	 * 
	 * 예)
	 * 
	 * @param 
	 * @return
	 */
	@SuppressWarnings("unused")
	public UnpauseResultVO updateUnpauseRsv(String entrNo, String entrSttsValdEndDt, String entrSttsChngSeqno,
			String custNo, String prodNo, String aplyLvIcd, String msgParamCnt,
			String memoCrteDlrCd, String rsnCd, String rsnDtlCd, String msgParam1, String msgParam2) {

		try {
			UnpauseResultVO unpauseResultVO = new UnpauseResultVO();

			// entrNo 가입번호 NUMBER 12 N 복수
			// entrSttsChngRsnCd 가입상태변경사유코드 VARCHAR2 10 N 복수
			// entrSttsChngRsnDtlCd 가입상태변경상세사유코드 VARCHAR2 10 N 복수
			// entrSttsChngRsnDtlNm
			// sendPhbYn 발신금지 여부 VARCHAR2 1 N 복수 Y/N
			// icallPhbYn 착신금지여부 VARCHAR2 1 N 복수 Y/N
			// entrSttsValdStrtDt 처리일자 VARCHAR2 8 N 복수
			// userMemo 사용자메모 VARCHAR2 800 복수

			UpdateSttsChgRsvExpServiceStub.DsRsvExpInVO[] dsRsvExpIn = new UpdateSttsChgRsvExpServiceStub.DsRsvExpInVO[1];
			dsRsvExpIn[0] = new UpdateSttsChgRsvExpServiceStub.DsRsvExpInVO();
			dsRsvExpIn[0].setEntrNo(entrNo);
			dsRsvExpIn[0].setEntrSttsValdEndDt(entrSttsValdEndDt);
			dsRsvExpIn[0].setEntrSttsChngSeqno(entrSttsChngSeqno);
			dsRsvExpIn[0].setCustNo(custNo);
			dsRsvExpIn[0].setProdNo(prodNo);
			dsRsvExpIn[0].setAplyLvlCd(aplyLvIcd);
			dsRsvExpIn[0].setMsgParmCnt(msgParamCnt);
			dsRsvExpIn[0].setMemoCrteDlrCd(memoCrteDlrCd);
			dsRsvExpIn[0].setRsnCd(rsnCd);
			dsRsvExpIn[0].setRsnDtlCd(rsnDtlCd);
			dsRsvExpIn[0].setMsgParm1(msgParam1);
			dsRsvExpIn[0].setMsgParm2(msgParam2);
			dsRsvExpIn[0].setMsgParm3(msgParam2);

			UpdateSttsChgRsvExpServiceStub.ResponseBody resBody = updateSttsChgRsvService(dsRsvExpIn);		  	   
			UpdateSttsChgRsvExpServiceStub.DsSusResultOutVO resultOut = resBody.getDsSusResultOutVO();
			unpauseResultVO.setResult("");
			
			logger.debug(
					"updateUnpause({}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}) is \n{}",
					entrNo,
					entrSttsValdEndDt,
					entrSttsChngSeqno,
					custNo,
					prodNo,
					aplyLvIcd,
					msgParamCnt,
					memoCrteDlrCd,
					rsnCd,
					rsnDtlCd,
					msgParam1,
					msgParam2,
					unpauseResultVO.toString());

			return unpauseResultVO;
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 일시정지해제신청
	 * 
	 * 예)
	 * 
	 * @param 
	 * @return
	 */
	/*public UnpauseResultVO updateUnpauseRsv2(String entrNo, String entrSttsValdEndDt, String entrSttsChngSeqno,
			String custNo, String prodNo, String aplyLvIcd, String msgParamCnt,
			String memoCrteDlrCd, String rsnCd, String rsnDtlCd, String msgParam1, String msgParam2) {

		try {
			UnpauseResultVO unpauseResultVO = new UnpauseResultVO();

			// entrNo 가입번호 NUMBER 12 N 복수
			// entrSttsChngRsnCd 가입상태변경사유코드 VARCHAR2 10 N 복수
			// entrSttsChngRsnDtlCd 가입상태변경상세사유코드 VARCHAR2 10 N 복수
			// entrSttsChngRsnDtlNm
			// sendPhbYn 발신금지 여부 VARCHAR2 1 N 복수 Y/N
			// icallPhbYn 착신금지여부 VARCHAR2 1 N 복수 Y/N
			// entrSttsValdStrtDt 처리일자 VARCHAR2 8 N 복수
			// userMemo 사용자메모 VARCHAR2 800 복수

			UpdateSttsChgRsvExpServiceStub.DsRsvExpInVO[] dsRsvExpIn = new UpdateSttsChgRsvExpServiceStub.DsRsvExpInVO[1];
			dsRsvExpIn[0] = new UpdateSttsChgRsvExpServiceStub.DsRsvExpInVO();
			dsRsvExpIn[0].setEntrNo(entrNo);
			dsRsvExpIn[0].setEntrSttsValdEndDt(entrSttsValdEndDt);
			dsRsvExpIn[0].setEntrSttsChngSeqno(entrSttsChngSeqno);
			dsRsvExpIn[0].setCustNo(custNo);
			dsRsvExpIn[0].setProdNo(prodNo);
			dsRsvExpIn[0].setAplyLvlCd(aplyLvIcd);
			dsRsvExpIn[0].setMsgParmCnt(msgParamCnt);
			dsRsvExpIn[0].setMemoCrteDlrCd(memoCrteDlrCd);
			dsRsvExpIn[0].setRsnCd(rsnCd);
			dsRsvExpIn[0].setRsnDtlCd(rsnDtlCd);
			dsRsvExpIn[0].setMsgParm1(msgParam1);
			dsRsvExpIn[0].setMsgParm2(msgParam2);
			dsRsvExpIn[0].setMsgParm3(msgParam2);

			UpdateSttsChgRsvExpServiceStub.ResponseBody resBody = updateSttsChgRsvService(dsRsvExpIn);		  	   
			UpdateSttsChgRsvExpServiceStub.DsSusResultOutVO resultOut = resBody.getDsSusResultOutVO();
			unpauseResultVO.setResult(resultOut.getResult());
			
			logger.debug(
					"updateUnpause({}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}) is \n{}",
					entrNo,
					entrSttsValdEndDt,
					entrSttsChngSeqno,
					custNo,
					prodNo,
					aplyLvIcd,
					msgParamCnt,
					memoCrteDlrCd,
					rsnCd,
					rsnDtlCd,
					msgParam1,
					msgParam2,
					unpauseResultVO.toString());

			return unpauseResultVO;
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return null;
	}*/
	
	/**
	 * 일시정지신청
	 * 
	 * 예)
	 * 
	 * @param 
	 * @return
	 */
	public UnpauseResultVO updatePauseSus(String entrNo, String entrSttsChngRsnCd, String entrSttsChngRsnDtlCd,
			String entrSttsChngRsnDtlNm, String sendPhbYn, String icallPhbYn, String entrSttsValdStrtDt,
			String userMemo, String userDlrCd) {

		try {
			UnpauseResultVO unpauseResultVO = new UnpauseResultVO();

			// entrNo 가입번호 NUMBER 12 N 복수
			// entrSttsChngRsnCd 가입상태변경사유코드 VARCHAR2 10 N 복수
			// entrSttsChngRsnDtlCd 가입상태변경상세사유코드 VARCHAR2 10 N 복수
			// entrSttsChngRsnDtlNm
			// sendPhbYn 발신금지 여부 VARCHAR2 1 N 복수 Y/N
			// icallPhbYn 착신금지여부 VARCHAR2 1 N 복수 Y/N
			// entrSttsValdStrtDt 처리일자 VARCHAR2 8 N 복수
			// userMemo 사용자메모 VARCHAR2 800 복수

			SaveEntrSuspendHomepgServiceStub.DsSuspendInfoInVO[] dsSuspendInfoIn = new SaveEntrSuspendHomepgServiceStub.DsSuspendInfoInVO[1];
			dsSuspendInfoIn[0] = new SaveEntrSuspendHomepgServiceStub.DsSuspendInfoInVO();
			dsSuspendInfoIn[0].setEntrNo(entrNo);
			dsSuspendInfoIn[0].setEntrSttsChngRsnCd(entrSttsChngRsnCd);
			dsSuspendInfoIn[0].setEntrSttsChngRsnDtlCd(entrSttsChngRsnDtlCd);
			dsSuspendInfoIn[0].setEntrSttsChngRsnDtlNm(entrSttsChngRsnDtlNm);
			dsSuspendInfoIn[0].setSendPhbYn(sendPhbYn);
			dsSuspendInfoIn[0].setIcallPhbYn(icallPhbYn);
			dsSuspendInfoIn[0].setEntrSttsValdStrtDt(entrSttsValdStrtDt);
			dsSuspendInfoIn[0].setUserMemo(userMemo);
			dsSuspendInfoIn[0].setUserDlrCd(userDlrCd);

			SaveEntrSuspendHomepgServiceStub.ResponseBody resBody = saveEntrSuspendHomepgService(dsSuspendInfoIn);		  	   
			SaveEntrSuspendHomepgServiceStub.DsSusResultOutVO resultOut = resBody.getDsSusResultOutVO();
			unpauseResultVO.setResult(resultOut.getResult());
			
			logger.debug(
					"updateUnpause({}, {}, {}, {}, {}, {}, {}, {}, {}) is \n{}",
					entrNo,
					entrSttsChngRsnCd,
					entrSttsChngRsnDtlCd,
					entrSttsChngRsnDtlNm,
					sendPhbYn,
					icallPhbYn,
					entrSttsValdStrtDt,
					userMemo,
					userDlrCd, 
					unpauseResultVO.toString());

			return unpauseResultVO;
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return null;
	}
	/**
	 * 청구유형변경
	 * 
	 * 예)
	 * 
	 * @param userId
	 * @param userNm
	 * @param mrktCd
	 * @param userOrgCd
	 * @param userDlrCd
	 * @param userWorkDlrCd
	 * @param userDlrNm
	 * @param lockMode
	 * @param cnId
	 * @param directive
	 * @param runDate
	 * @param runDateDtm
	 * @param transactionMode
	 * @param userWorkDlrNm
	 * @param entrNo
	 * @param entrSysUpdateDate
	 * @param entrDlUpdateStamp
	 * @param aceno
	 * @param cntcSysUpdateDate
	 * @param cntcDlUpdateStamp
	 * @param billAcntNo
	 * @param billSysUpdateDate
	 * @param billDlUpdateStamp
	 *            *
	 * @param billAcntNo_
	 * @param bltxtKdCdNm
	 * @param dscntSvcCd
	 * @param dscntSvcNm
	 * @param bltxtKdValdStrtDt
	 * @param bltxtKdValdEndDt
	 * @param scurMailRcpYn
	 * @param bltxtRcpProdNo
	 * @param billEmailAddr
	 * @param copyReal
	 * @param prodNo
	 * @param emailCopyCustYn
	 * @param chgBlAddrYn
	 * @param billAddrSeqno
	 * @param custAddrZip
	 * @param custVilgAbvAddr
	 * @param custVilgBlwAddr
	 * @param imsi01
	 * @param imsi02
	 * @param imsi03
	 * @param bltxtChrgrId
	 * @return
	 */
	public BillTypeResultVO updateBillType(String userId, String userNm, String mrktCd, String userOrgCd,
			String userDlrCd, String userWorkDlrCd, String userDlrNm, String lockMode, String cnId, String directive,
			String runDate, String runDateDtm, String transactionMode, String userWorkDlrNm, String entrNo,
			String entrSysUpdateDate, String entrDlUpdateStamp, String aceno, String cntcSysUpdateDate,
			String cntcDlUpdateStamp, String billAcntNo, String billSysUpdateDate, String billDlUpdateStamp,
			//
			String billAcntNo_, String bltxtKdCdNm, String dscntSvcCd, String dscntSvcNm, String bltxtKdValdStrtDt,
			String bltxtKdValdEndDt, String scurMailRcpYn, String bltxtRcpProdNo, String billEmailAddr, String copyReal,
			String prodNo, String emailCopyCustYn, String chgBlAddrYn, String billAddrSeqno, String custAddrZip,
			String custVilgAbvAddr, String custVilgBlwAddr, String imsi01, String imsi02, String imsi03,
			String bltxtChrgrId) {

		try {
			BillTypeResultVO billTypeResultVO = new BillTypeResultVO();

			// dsConfldsIn
			// userId 사용자ID VARCHAR2 100 Y 단수
			// userNm 사용자명 VARCHAR2 500 Y 단수
			// mrktCd 마켓코드 VARCHAR2 10 Y 단수
			// userOrgCd 사용자기관코드 VARCHAR2 10 Y 단수
			// userDlrCd 사용자대리점코드 VARCHAR2 10 Y 단수
			// userWorkDlrCd 작업대리점코드 VARCHAR2 10 Y 단수
			// userDlrNm 사용자대리점명 VARCHAR2 100 Y 단수
			// lockMode lockMode VARCHAR2 10 N 단수 B - 고정
			// cnId cnId VARCHAR2 10 Y 단수
			// directive directive VARCHAR2 10 Y 단수
			// runDate runDate VARCHAR2 8 Y 단수
			// runDateDtm runDateDtm VARCHAR2 16 Y 단수
			// transactionMode transactionMode VARCHAR2 10 Y 단수
			// userWorkDlrNm 작업대리점명 VARCHAR2 100 Y 단수
			// entrNo 가입번호 VARCHAR2 15 Y 단수
			// entrSysUpdateDate entrSysUpdateDate VARCHAR2 16 Y 단수
			// entrDlUpdateStamp entrDlUpdateStamp VARCHAR2 16 Y 단수
			// aceno 가입계약번호 VARCHAR2 15 Y 단수
			// cntcSysUpdateDate cntcSysUpdateDate VARCHAR2 16 Y 단수
			// cntcDlUpdateStamp cntcDlUpdateStamp VARCHAR2 16 Y 단수
			// billAcntNo 청구계정번호 VARCHAR2 15 N 단수
			// billSysUpdateDate billSysUpdateDate VARCHAR2 16 N 단수
			// billDlUpdateStamp billDlUpdateStamp VARCHAR2 16 N 단수

			SaveBltxtKdCdSvcServiceStub.DsConfldsInVO dsConfldsIn = new SaveBltxtKdCdSvcServiceStub.DsConfldsInVO();
			dsConfldsIn.setUserId(userId);
			dsConfldsIn.setUserNm(userNm);
			dsConfldsIn.setMrktCd(mrktCd);
			dsConfldsIn.setUserOrgCd(userOrgCd);
			dsConfldsIn.setUserDlrCd(userDlrCd);
			dsConfldsIn.setUserWorkDlrCd(userWorkDlrCd);
			dsConfldsIn.setUserDlrNm(userDlrNm);
			dsConfldsIn.setLockMode(lockMode);
			dsConfldsIn.setCnId(cnId);
			dsConfldsIn.setDirective(directive);
			dsConfldsIn.setRunDate(runDate);
			dsConfldsIn.setRunDateDtm(runDateDtm);
			dsConfldsIn.setTransactionMode(transactionMode);
			dsConfldsIn.setUserWorkDlrNm(userWorkDlrNm);
			dsConfldsIn.setEntrNo(entrNo);
			dsConfldsIn.setEntrSysUpdateDate(entrSysUpdateDate);
			dsConfldsIn.setEntrDlUpdateStamp(entrDlUpdateStamp);
			dsConfldsIn.setAceno(aceno);
			dsConfldsIn.setCntcSysUpdateDate(cntcSysUpdateDate);
			dsConfldsIn.setCntcDlUpdateStamp(cntcDlUpdateStamp);
			dsConfldsIn.setBillAcntNo(billAcntNo);
			dsConfldsIn.setBillSysUpdateDate(billSysUpdateDate);
			dsConfldsIn.setBillDlUpdateStamp(billDlUpdateStamp);

			// dsReqIn
			// billAcntNo 청구계정번호 VARCHAR2 9 N 단수
			// bltxtKdCdNm 청구서유형코드명 VARCHAR2 80 N 단수
			// dscntSvcCd 할인서비스코드 VARCHAR2 10 Y 단수
			// dscntSvcNm 할인서비스명 VARCHAR2 240 Y 단수
			// bltxtKdValdStrtDt 청구서유형유효시작일자 VARCHAR2 14 Y 단수
			// bltxtKdValdEndDt 청구서유형유효종료일자 VARCHAR2 14 Y 단수
			// scurMailRcpYn 보안메일수신여부 VARCHAR2 1 Y 단수
			// bltxtRcpProdNo 청구서수신상품번호 VARCHAR2 20 Y 단수
			// billEmailAddr 청구이메일주소 VARCHAR2 50 Y 단수
			// copyReal copyReal VARCHAR2 1 Y 단수
			// prodNo 상품번호 VARCHAR2 20 Y 단수
			// emailCopyCustYn emailCopyCustYn VARCHAR2 1 Y 단수
			// chgBlAddrYn chgBlAddrYn VARCHAR2 1 Y 단수
			// billAddrSeqno 청구주소누적번호 VARCHAR2 15 Y 단수
			// custAddrZip 고객주소우편번호 VARCHAR2 7 Y 단수
			// custVilgAbvAddr 고객동이상주소 VARCHAR2 500 Y 단수
			// custVilgBlwAddr 고객동이하주소 VARCHAR2 500 Y 단수
			// imsi01 imsi01 VARCHAR2 500 Y 단수
			// imsi02 imsi02 VARCHAR2 500 Y 단수
			// imsi03 imsi03 VARCHAR2 500 Y 단수
			// bltxtChrgrId am직배 담당자 ID VARCHAR2 100 Y 단수 청구서 유형이 am 직배(G)인경우
			// set

			SaveBltxtKdCdSvcServiceStub.DsReqInVO dsReqIn = new SaveBltxtKdCdSvcServiceStub.DsReqInVO();
			dsReqIn.setBillAcntNo(billAcntNo_);
			dsReqIn.setBltxtKdCdNm(bltxtKdCdNm);
			dsReqIn.setDscntSvcCd(dscntSvcCd);
			dsReqIn.setDscntSvcNm(dscntSvcNm);
			dsReqIn.setBltxtKdValdStrtDt(bltxtKdValdStrtDt);
			dsReqIn.setBltxtKdValdEndDt(bltxtKdValdEndDt);
			dsReqIn.setScurMailRcpYn(scurMailRcpYn);
			dsReqIn.setBltxtRcpProdNo(bltxtRcpProdNo);
			dsReqIn.setBillEmailAddr(billEmailAddr);
			dsReqIn.setCopyReal(copyReal);
			dsReqIn.setProdNo(prodNo);
			dsReqIn.setEmailCopyCustYn(emailCopyCustYn);
			dsReqIn.setChgBlAddrYn(chgBlAddrYn);
			dsReqIn.setBillAddrSeqno(billAddrSeqno);
			dsReqIn.setCustAddrZip(custAddrZip);
			dsReqIn.setCustVilgAbvAddr(custVilgAbvAddr);
			dsReqIn.setCustVilgBlwAddr(custVilgBlwAddr);
			dsReqIn.setImsi01(imsi01);
			dsReqIn.setImsi02(imsi02);
			dsReqIn.setImsi03(imsi03);
			dsReqIn.setBltxtChrgrId(bltxtChrgrId);

			SaveBltxtKdCdSvcServiceStub.ResponseBody resBody = saveBltxtKdCdSvc(dsConfldsIn, dsReqIn);
			SaveBltxtKdCdSvcServiceStub.DsResOutVO dsResOut = resBody.getDsResOutVO();
			billTypeResultVO.setMsgCode(dsResOut.getMsgCode());// 결과코드
			billTypeResultVO.setMsgText(dsResOut.getMsgText());// 결과메시지

			logger.debug(
					"updateBillType({}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}) is \n{}",
					userId, userNm, mrktCd, userOrgCd, userDlrCd, userWorkDlrCd, userDlrNm, lockMode, cnId, directive,
					runDate, runDateDtm, transactionMode, userWorkDlrNm, entrNo, entrSysUpdateDate, entrDlUpdateStamp,
					aceno, cntcSysUpdateDate, cntcDlUpdateStamp, billAcntNo, billSysUpdateDate, billDlUpdateStamp,
					//
					billAcntNo_, bltxtKdCdNm, dscntSvcCd, dscntSvcNm, bltxtKdValdStrtDt, bltxtKdValdEndDt,
					scurMailRcpYn, bltxtRcpProdNo, billEmailAddr, copyReal, prodNo, emailCopyCustYn, chgBlAddrYn,
					billAddrSeqno, custAddrZip, custVilgAbvAddr, custVilgBlwAddr, imsi01, imsi02, imsi03, bltxtChrgrId,
					billTypeResultVO.toString());

			return billTypeResultVO;
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return null;
	}

	// /**
	// *
	// * @return
	// */
	// public PlanResultVO changePlan() {
	//
	// try {
	// PlanResultVO planResultVO = new PlanResultVO();
	// // dsAsgnNoList
	// // entrNo 가입번호 VARCHAR2 10 Y 복수 -
	// // dscntStrtDttm 시작일시 VARCHAR2 14 Y 복수 -
	// // asgnNoSeqno 지정번호일련번호 NUMBER 10 Y 복수 -
	// // asgnDscntTelno 지정대상번호 VARCHAR2 16 Y 복수 -
	// // dscntEndDttm 할인종료일시 VARCHAR2 14 Y 복수 -
	// // dscntKdDtlCd 할인유형코드 VARCHAR2 3 Y 복수 -
	// // dscntSvcCd 할인서비스코드 VARCHAR2 10 Y 복수 -
	// // hposSvcCd 할인서비스상위코드 VARCHAR2 10 Y 복수 -
	// // ctn 지정상품번호 VARCHAR2 20 Y 복수 -
	// // svcGrpSeqno 서비스그룹 일련번호 NUMBER 10 Y 복수 -
	// // svcRsvSttsCd 서비스 예약 상태 코드 VARCHAR2 3 Y 복수 -
	// // hposSvcNm 상위서비스명 VARCHAR2 50 Y 복수 -
	// // _rowStatus 처리 모드 구분 VARCHAR2 1 Y 복수 -
	// // nextOperatorId 처리자ID STRING 15 N 복수
	//
	// // dsChkFtr
	// // svcCd 서비스코드 VARCHAR2 10 Y 복수 사용자입력 서비스 요소에 한해 입력받는다
	// // ftrCd 서비스속성코드 VARCHAR2 10 Y 복수
	// // ftrNm 속성명 VARCHAR2 30 Y 복수
	// // ftrValdStrtDt 속성시작일자 VARCHAR2 8 Y 복수
	// // ftrValdEndDt 속성종료일자 VARCHAR2 8 Y 복수
	// // varParam 속성파람value VARCHAR2 200 Y 복수
	// // ftrVarDtlSeqno 속성일련번호 NUMBER 10 Y 복수
	// // entrSvcSeqno 서비스일련번호 NUMBER 10 Y 복수
	// // ftrMode 처리모드 VARCHAR2 1 Y 복수 I(등록),U(만기),N(미변경)
	// // ftrSubMode 처리세부모드 VARCHAR2 1 Y 복수 I(등록),U(만기),E(파람벨류변경),N(미변경)
	// // nextOperatorId 처리자ID STRING 15 N 복수
	//
	// // dsChkItem
	// // devMode 등록,만기 여부 구분 VARCHAR2 1 Y 복수 I:등록, U:만기
	// // itemId 단말기모델코드 VARCHAR2 15 Y 복수 -
	// // manfSerialNo 단말기모델일련번호 VARCHAR2 20 Y 복수 -
	// // devChngRsnCd 단말기 변경사유 코드 VARCHAR2 3 Y 복수 신규 : PC
	// // eventCode 작업처리구분 VARCHAR2 3 Y 복수 -
	// // itemStatus 단말기상태코드 VARCHAR2 3 Y 복수 -
	// // casNo DMB CAS NO VARCHAR2 0 Y 복수 -
	// // newTrxCd event_code_sims(as-is) VARCHAR2 5 Y 복수 -
	// // chipUseYn chipUseYn VARCHAR2 1 Y 복수 -
	// // nextOperatorId 처리자ID STRING 15 N 복수
	//
	// // dsChkSvcRsv
	// // svcCd 서비스코드 VARCHAR2 10 Y
	// // svcNm 서비스명 VARCHAR2 30 Y
	// // entrSvcSeqno 서비스일련번호 NUMBER 10 Y
	// // hposSvcCd 상위서비스코드 VARCHAR2 10 Y
	// // hposEntrSvcSeqno 상위서비스일련번호 NUMBER 10 Y
	// // svcKdCd 서비스유형 VARCHAR2 3 Y
	// // svcAplyLvlCd 서비스레벨코드 VARCHAR2 3 Y
	// // svcFrstStrtDttm 가입자 서비스시작일시 VARCHAR2 14 Y
	// // svcStrtRgstDlrCd 등록대리점 VARCHAR2 7 Y
	// // svcStrtDttm 서비스시작일시 VARCHAR2 14 Y
	// // svcEndDttm 서비스종료일시 VARCHAR2 14 Y
	// // svcDutyUseMnthCnt 의무사용개월수 NUMBER 3 Y
	// // svcDutyUseDvCd 의무사용구분코드 VARCHAR2 3 Y
	// // svcDutyUseStrtDt 의무사용시작일자 VARCHAR2 8 Y
	// // svcDutyUseEndDt 의무사용종료일자 VARCHAR2 8 Y
	// // saleEmpno 판매사번 VARCHAR2 8 Y
	// // svcRelsDvCd 서비스 관련 코드 VARCHAR2 3 Y
	// // ndblCvrtSvcCd NDBL 서비스 관련 코드 VARCHAR2 3 Y
	// // inventoryItemId pdh상품구조상의 코드 NUMBER 15 Y
	// // organizationId pdh상품구조상의 조직코드 NUMBER 15 Y
	// // revisionId pdh상품구조상의 버전 코드 NUMBER 15 Y
	// // svcMode 처리모드 VARCHAR2 3 Y
	// // svcSubMode 처리세부모드 VARCHAR2 3 Y
	// // _rowStatus 처리 모드 구분 VARCHAR2 1 Y
	// // rsvOprtr 예약처리자 VARCHAR2 8 Y
	// // susAftRsvYn 일시정지후예약여부 VARCHAR2 1 Y
	// // rsvRcptDvCd 예약접수구분코드 VARCHAR2 3 Y
	// // rsvSvcChngDvCd 예약서비스변경구분코드 VARCHAR2 3 Y
	// // bfrPpCd 이전요금제코드 VARCHAR2 10 Y
	// // rsvDttm 예약일시 VARCHAR2 14 Y
	// // rsvDlrCd 예약대리점 VARCHAR2 8 Y
	// // svcRsvSttsCd 서비스예약상태코드 VARCHAR2 3 Y
	// // rmks rmks VARCHAR2 30 Y
	// // nextOperatorId 처리자ID STRING 15 N
	//
	// // dsConflds
	// // directive 디렉티브 VARCHAR2 1 Y 복수 -
	// // runDate 작업일자 VARCHAR2 8 Y 복수 -
	// // runDateDtm 작업일시 VARCHAR2 14 Y 복수 -
	// // transactionMode 트랙잰션모드 VARCHAR2 1 Y 복수 -
	// // entrNo 가입번호 VARCHAR2 10 Y 복수 -
	// // entrDlUpdateStamp 가입변경stamp VARCHAR2 4 Y 복수 -
	// // entrSysUpdateDate 가입변경date VARCHAR2 14 Y 복수 -
	// // aceno 가입계약번호 VARCHAR2 10 Y 복수 -
	// // cntcDlUpdateStamp 가입계약변경stamp VARCHAR2 4 Y 복수 -
	// // cntcSysUpdateDate 가입계약변경date VARCHAR2 14 Y 복수 -
	// // billAcntNo 청구계정번호 VARCHAR2 10 Y 복수 -
	// // billDlUpdateStamp 청구계정변경stamp VARCHAR2 4 Y 복수
	// // billSysUpdateDate 청구계정변경date VARCHAR2 14 Y 복수
	// // cnId callCtn VARCHAR2 12 Y 복수
	// // lockMode Lock모드 VARCHAR2 1 Y 복수
	// // userWorkDlrCd 작업대리점 VARCHAR2 6 Y 복수
	// // userWorkDlrNm 작업대리점명 VARCHAR2 30 Y 복수
	// // nextOperatorId 처리자ID STRING 15 N 복수
	//
	// // dsSaveSvc
	// // billAcntNo 청구계정번호 NUMBER 10 N 복수
	// // entrNo 가입번호 NUMBER 10 N 복수
	// // prodNo 상품번호 VARCHAR2 20 N 복수
	// // hldrCustNo 고객번호 VARCHAR2 20 N 복수
	// // saleEmpno 판매사번 VARCHAR2 8 Y 복수
	// // svcDutyUseMnthCnt 의무사용개월수 NUMBER 3 Y 복수
	// // svcDutyUseDvCd 의무사용구분코드 VARCHAR2 3 Y 복수
	// // svcDutyUseStrtDt 의무사용시작일자 VARCHAR2 8 Y 복수
	// // svcDutyUseEndDt 의무사용종료일자 VARCHAR2 8 Y 복수
	// // rgstDlrCd 작업대리점 VARCHAR2 8 N 복수
	// // rjnDt 재가입일 VARCHAR2 8 Y 복수
	// // runDttm 작업일 VARCHAR2 14 N 복수
	// // noGuidPrcType 번호안내작업구분 VARCHAR2 3 Y 복수
	// // prcType 작업구분 VARCHAR2 3 N 복수
	// // prcSubType 작업 세부 구분 VARCHAR2 3 Y 복수
	// // prcMode 프로세스구분 VARCHAR2 3 Y 복수
	// // prcSubMode 프로세스세부구분 VARCHAR2 3 N 복수
	// // posCd 개통pos VARCHAR2 7 N 복수
	// // rsalePosCd 실판매pos VARCHAR2 7 Y 복수
	// // newTrxYn TRX IND VARCHAR2 1 Y 복수
	// // itemTrx itemTrx(기변시 사용) VARCHAR2 3 Y 복수
	// // svcCd 서비스코드 VARCHAR2 10 Y 복수
	// // svcNm 서비스명 VARCHAR2 30 Y 복수
	// // kongUppChrgAmt 콩상한금액 NUMBER 13 Y 복수
	// // userMemo 사용자 메모 VARCHAR2 300 Y 복수
	// // entrSttsCd 가입자상태코드 VARCHAR2 1 Y 복수
	// // nextOperatorId 처리자ID STRING 15 N 복수
	//
	// SaveSvcRsvServiceStub.DsAsgnNoListInVO[] dsAsgnNoListIn = new
	// SaveSvcRsvServiceStub.DsAsgnNoListInVO[1];
	// // dsAsgnNoListIn[0] = new SaveSvcRsvServiceStub.DsAsgnNoListInVO();
	// // dsAsgnNoListIn[0].setEntrNo(entrNo_);
	// // dsAsgnNoListIn[0].setDscntStrtDttm(dscntStrtDttm_);
	// // dsAsgnNoListIn[0].setAsgnNoSeqno(asgnNoSeqno_);
	// // dsAsgnNoListIn[0].setAsgnDscntTelno(asgnDscntTelno_);
	// // dsAsgnNoListIn[0].setDscntEndDttm(dscntEndDttm_);
	// // dsAsgnNoListIn[0].setDscntKdDtlCd(dscntKdDtlCd_);
	// // dsAsgnNoListIn[0].setDscntSvcCd(dscntSvcCd_);
	// // dsAsgnNoListIn[0].setHposSvcCd(hposSvcCd_);
	// // dsAsgnNoListIn[0].setCtn(ctn_);
	// // dsAsgnNoListIn[0].setSvcGrpSeqno(svcGrpSeqno_);
	// // dsAsgnNoListIn[0].setSvcRsvSttsCd(svcRsvSttsCd_);
	// // dsAsgnNoListIn[0].setHposSvcNm(hposSvcNm_);
	// // dsAsgnNoListIn[0].set_rowStatus(_rowStatus_);
	// // dsAsgnNoListIn[0].setNextOperatorId(nextOperatorId_);
	//
	// SaveSvcRsvServiceStub.DsChkFtrInVO[] dsChkFtrIn = new
	// SaveSvcRsvServiceStub.DsChkFtrInVO[1];
	// // dsChkFtrIn[0] = new SaveSvcRsvServiceStub.DsChkFtrInVO();
	// // dsChkFtrIn[0].setSvcCd(svcCd__);
	// // dsChkFtrIn[0].setFtrCd(ftrCd__);
	// // dsChkFtrIn[0].setFtrNm(ftrNm__);
	// // dsChkFtrIn[0].setFtrValdStrtDt(ftrValdStrtDt__);
	// // dsChkFtrIn[0].setFtrValdEndDt(ftrValdEndDt__);
	// // dsChkFtrIn[0].setVarParam(varParam__);
	// // dsChkFtrIn[0].setFtrVarDtlSeqno(ftrVarDtlSeqno__);
	// // dsChkFtrIn[0].setEntrSvcSeqno(entrSvcSeqno__);
	// // dsChkFtrIn[0].setFtrMode(ftrMode__);
	// // dsChkFtrIn[0].setFtrSubMode(ftrSubMode__);
	// // dsChkFtrIn[0].setNextOperatorId(nextOperatorId__);
	//
	// SaveSvcRsvServiceStub.DsChkItemInVO[] dsChkItemIn = new
	// SaveSvcRsvServiceStub.DsChkItemInVO[1];
	// // dsChkItemIn[0] = new SaveSvcRsvServiceStub.DsChkItemInVO();
	// // dsChkItemIn[0].setDevMode(devMode___);
	// // dsChkItemIn[0].setItemId(itemId___);
	// // dsChkItemIn[0].setManfSerialNo(manfSerialNo___);
	// // dsChkItemIn[0].setDevChngRsnCd(devChngRsnCd___);
	// // dsChkItemIn[0].setEventCode(eventCode___);
	// // dsChkItemIn[0].setItemStatus(itemStatus___);
	// // dsChkItemIn[0].setCasNo(casNo___);
	// // dsChkItemIn[0].setNewTrxCd(newTrxCd___);
	// // dsChkItemIn[0].setChipUseYn(chipUseYn___);
	// // dsChkItemIn[0].setNextOperatorId(nextOperatorId___);
	//
	// SaveSvcRsvServiceStub.DsChkSvcRsvInVO[] dsChkSvcRsvIn = new
	// SaveSvcRsvServiceStub.DsChkSvcRsvInVO[1];
	// // dsChkSvcRsvIn[0] = new SaveSvcRsvServiceStub.DsChkSvcRsvInVO();
	// // dsChkSvcRsvIn[0].setSvcCd(svcCd____);
	// // dsChkSvcRsvIn[0].setSvcNm(svcNm____);
	// // dsChkSvcRsvIn[0].setEntrSvcSeqno(entrSvcSeqno____);
	// // dsChkSvcRsvIn[0].setHposSvcCd(hposSvcCd____);
	// // dsChkSvcRsvIn[0].setHposEntrSvcSeqno(hposEntrSvcSeqno____);
	// // dsChkSvcRsvIn[0].setSvcKdCd(svcKdCd____);
	// // dsChkSvcRsvIn[0].setSvcAplyLvlCd(svcAplyLvlCd____);
	// // dsChkSvcRsvIn[0].setSvcFrstStrtDttm(svcFrstStrtDttm____);
	// // dsChkSvcRsvIn[0].setSvcStrtRgstDlrCd(svcStrtRgstDlrCd____);
	// // dsChkSvcRsvIn[0].setSvcStrtDttm(svcStrtDttm____);
	// // dsChkSvcRsvIn[0].setSvcEndDttm(svcEndDttm____);
	// // dsChkSvcRsvIn[0].setSvcDutyUseMnthCnt(svcDutyUseMnthCnt____);
	// // dsChkSvcRsvIn[0].setSvcDutyUseDvCd(svcDutyUseDvCd____);
	// // dsChkSvcRsvIn[0].setSvcDutyUseStrtDt(svcDutyUseStrtDt____);
	// // dsChkSvcRsvIn[0].setSvcDutyUseEndDt(svcDutyUseEndDt____);
	// // dsChkSvcRsvIn[0].setSaleEmpno(saleEmpno____);
	// // dsChkSvcRsvIn[0].setSvcRelsDvCd(svcRelsDvCd____);
	// // dsChkSvcRsvIn[0].setNdblCvrtSvcCd(ndblCvrtSvcCd____);
	// // dsChkSvcRsvIn[0].setInventoryItemId(inventoryItemId____);
	// // dsChkSvcRsvIn[0].setOrganizationId(organizationId____);
	// // dsChkSvcRsvIn[0].setRevisionId(revisionId____);
	// // dsChkSvcRsvIn[0].setSvcMode(svcMode____);
	// // dsChkSvcRsvIn[0].setSvcSubMode(svcSubMode____);
	// // dsChkSvcRsvIn[0].set_rowStatus(_rowStatus____);
	// // dsChkSvcRsvIn[0].setRsvOprtr(rsvOprtr____);
	// // dsChkSvcRsvIn[0].setSusAftRsvYn(susAftRsvYn____);
	// // dsChkSvcRsvIn[0].setRsvRcptDvCd(rsvRcptDvCd____);
	// // dsChkSvcRsvIn[0].setRsvSvcChngDvCd(rsvSvcChngDvCd____);
	// // dsChkSvcRsvIn[0].setBfrPpCd(bfrPpCd____);
	// // dsChkSvcRsvIn[0].setRsvDttm(rsvDttm____);
	// // dsChkSvcRsvIn[0].setRsvDlrCd(rsvDlrCd____);
	// // dsChkSvcRsvIn[0].setSvcRsvSttsCd(svcRsvSttsCd____);
	// // dsChkSvcRsvIn[0].setRmks(rmks____);
	// // dsChkSvcRsvIn[0].setNextOperatorId(nextOperatorId____);
	//
	// SaveSvcRsvServiceStub.DsConfldsInVO[] dsConfldsIn = new
	// SaveSvcRsvServiceStub.DsConfldsInVO[1];
	// // dsConfldsIn[0] = new SaveSvcRsvServiceStub.DsConfldsInVO();
	// // dsConfldsIn[0].setDirective(directive_____);
	// // dsConfldsIn[0].setRunDate(runDate_____);
	// // dsConfldsIn[0].setRunDateDtm(runDateDtm_____);
	// // dsConfldsIn[0].setTransactionMode(transactionMode_____);
	// // dsConfldsIn[0].setEntrNo(entrNo_____);
	// // dsConfldsIn[0].setEntrDlUpdateStamp(entrDlUpdateStamp_____);
	// // dsConfldsIn[0].setEntrSysUpdateDate(entrSysUpdateDate_____);
	// // dsConfldsIn[0].setAceno(aceno_____);
	// // dsConfldsIn[0].setCntcDlUpdateStamp(cntcDlUpdateStamp_____);
	// // dsConfldsIn[0].setCntcSysUpdateDate(cntcSysUpdateDate_____);
	// // dsConfldsIn[0].setBillAcntNo(billAcntNo_____);
	// // dsConfldsIn[0].setBillDlUpdateStamp(billDlUpdateStamp_____);
	// // dsConfldsIn[0].setBillSysUpdateDate(billSysUpdateDate_____);
	// // dsConfldsIn[0].setCnId(cnId_____);
	// // dsConfldsIn[0].setLockMode(lockMode_____);
	// // dsConfldsIn[0].setUserWorkDlrCd(userWorkDlrCd_____);
	// // dsConfldsIn[0].setUserWorkDlrNm(userWorkDlrNm_____);
	// // dsConfldsIn[0].setNextOperatorId(nextOperatorId_____);
	//
	// SaveSvcRsvServiceStub.DsSaveSvcInVO[] dsSaveSvcIn = new
	// SaveSvcRsvServiceStub.DsSaveSvcInVO[1];
	// // dsSaveSvcIn[0] = new SaveSvcRsvServiceStub.DsSaveSvcInVO();
	// // dsSaveSvcIn[0].setBillAcntNo(billAcntNo______);
	// // dsSaveSvcIn[0].setEntrNo(entrNo______);
	// // dsSaveSvcIn[0].setProdNo(prodNo______);
	// // dsSaveSvcIn[0].setHldrCustNo(hldrCustNo______);
	// // dsSaveSvcIn[0].setSaleEmpno(saleEmpno______);
	// // dsSaveSvcIn[0].setSvcDutyUseMnthCnt(svcDutyUseMnthCnt______);
	// // dsSaveSvcIn[0].setSvcDutyUseDvCd(svcDutyUseDvCd______);
	// // dsSaveSvcIn[0].setSvcDutyUseStrtDt(svcDutyUseStrtDt______);
	// // dsSaveSvcIn[0].setSvcDutyUseEndDt(svcDutyUseEndDt______);
	// // dsSaveSvcIn[0].setRgstDlrCd(rgstDlrCd______);
	// // dsSaveSvcIn[0].setRjnDt(rjnDt______);
	// // dsSaveSvcIn[0].setRunDttm(runDttm______);
	// // dsSaveSvcIn[0].setNoGuidPrcType(noGuidPrcType______);
	// // dsSaveSvcIn[0].setPrcType(prcType______);
	// // dsSaveSvcIn[0].setPrcSubType(prcSubType______);
	// // dsSaveSvcIn[0].setPrcMode(prcMode______);
	// // dsSaveSvcIn[0].setPrcSubMode(prcSubMode______);
	// // dsSaveSvcIn[0].setPosCd(posCd______);
	// // dsSaveSvcIn[0].setRsalePosCd(rsalePosCd______);
	// // dsSaveSvcIn[0].setNewTrxYn(newTrxYn______);
	// // dsSaveSvcIn[0].setItemTrx(itemTrx______);
	// // dsSaveSvcIn[0].setSvcCd(svcCd______);
	// // dsSaveSvcIn[0].setSvcNm(svcNm______);
	// // dsSaveSvcIn[0].setKongUppChrgAmt(kongUppChrgAmt______);
	// // dsSaveSvcIn[0].setUserMemo(userMemo______);
	// // dsSaveSvcIn[0].setEntrSttsCd(entrSttsCd______);
	// // dsSaveSvcIn[0].setNextOperatorId(nextOperatorId______);
	// SaveSvcRsvServiceStub.ResponseBody resBody = saveSvcRsv(dsAsgnNoListIn,
	// dsChkFtrIn, dsChkItemIn,
	// dsChkSvcRsvIn, dsConfldsIn, dsSaveSvcIn);
	// SaveSvcRsvServiceStub.LDataRsltOutVO dsResOut =
	// resBody.getLDataRsltOutVO();
	// planResultVO.setResults(dsResOut.getResults());// 리턴값: 성공 인 경우
	// // success /실패 예외처리
	//
	// return planResultVO;
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// return null;
	// }

	/**
	 * 
	 * @param obj
	 */
	@SuppressWarnings("deprecation")
	public static String printFields(Object obj) {
		if (obj == null) {
			return "";
		}

		StringBuffer stringBuffer = new StringBuffer();

		Class<?> objClass = obj.getClass();

		try {
			Field[] fields = objClass.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				String name = field.getName();
				Object value = field.get(obj);

				if (!StringUtils.isEmpty(name) && !StringUtils.contains(name, "Tracker")) {
					name = StringUtils.uncapitalise(
							StringUtils.startsWith(name, "local") ? StringUtils.replaceOnce(name, "local", "") : name);
					stringBuffer.append(String.format("[%32s] %s\n", name, value != null ? value.toString() : ""));
				}
			}

			return stringBuffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}
	
	private void traceRequest(String msg) {
		String ctn = CCSSUtil.getCtn();
		if (ctn == null || ctn.length() == 0) {
			ctn = CCSSUtil.getMgrUserLoginId();
		}
		
		traceWriter.trace(ctn, TraceConst.NODE_ID_WAS, TraceConst.NODE_ID_ESB,
				TraceConst.PROTOCOL_SOAP, msg);
	}
	
	private void traceResponse(String msg) {
		String ctn = CCSSUtil.getCtn();
		if (ctn == null || ctn.length() == 0) {
			ctn = CCSSUtil.getMgrUserLoginId();
		}
		
		traceWriter.trace(ctn, TraceConst.NODE_ID_ESB, TraceConst.NODE_ID_WAS,
				TraceConst.PROTOCOL_SOAP, msg);
	}
}
