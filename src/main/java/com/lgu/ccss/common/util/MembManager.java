package com.lgu.ccss.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lgu.ccss.common.dao.DeviceDao;
import com.lgu.ccss.common.dao.MemberDao;
import com.lgu.ccss.common.model.ConnDeviceVO;
import com.lgu.ccss.common.model.MembData;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.common.model.ResultCode;
import com.lgu.common.ncas.NCASConst;
import com.lgu.common.ncas.NCASErrorCode;
import com.lgu.common.ncas.NCASQueryManager;
import com.lgu.common.ncas.NCASResultData;
import com.lgu.common.ncas.SubsInfo;

@Component
public class MembManager {
	private static final Logger logger = LoggerFactory.getLogger(MembManager.class);

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private DeviceDao connDeviceDao;

	@Autowired
	private NCASQueryManager nCASQueryManager;

	public ResultCode getNcasSubsInfo(String deviceCtn, MembData membData) {

		NCASResultData ncasData = nCASQueryManager.query(deviceCtn);
		if (ncasData == null) {
			logger.error("failed to query NCAS data. deviceCtn({})", deviceCtn);
			return ResultCodeUtil.RC_5N000001;
		}

		if (NCASErrorCode.ERRORCODE_NO_LGT.equals(ncasData.getRespCode())) {
			logger.error("No subscriber. deviceCtn({}) respCode({})", deviceCtn, ncasData.getRespCode());
			return ResultCodeUtil.RC_3C002004;
		}

		if (!NCASErrorCode.ERRORCODE_SUCCESS.equals(ncasData.getRespCode())) {
			logger.error("Abnormal subscriber. deviceCtn({}) respCode({})", deviceCtn, ncasData.getRespCode());
			return ResultCodeUtil.RC_5N000000;
		}

		SubsInfo subsInfo = ncasData.getSubsInfo();
		if (subsInfo == null) {
			logger.error("failed to get SubsInfo data. deviceCtn({})", deviceCtn);
			return ResultCodeUtil.RC_5N000000;
		}

		if (subsInfo.getCtn_stus_code().equals(NCASConst.CTN_STUS_CODE_SUSPEND)) {
			logger.error("Subscriber is suspended. deviceCtn({})", deviceCtn);
			return ResultCodeUtil.RC_3C002005;
		}
		
		//20201109 LPZ0000248일때 가개통 처리
		if (subsInfo.getFee_type().equals(NCASConst.FEE_TYPE_OPEN)) {
			logger.error("Before Opened. deviceCtn({})", deviceCtn);
			return ResultCodeUtil.RC_5N000000;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("deviceCtn({}) SUBS_INFO({})", deviceCtn, subsInfo);
		}

		membData.setSubsInfo(subsInfo);

		return ResultCodeUtil.RC_2S000000;
	}

	public ResultCode getSubsInfo(String deviceCtn, MembData membData) {

		ResultCode resultCode = getNcasSubsInfo(deviceCtn, membData);
		if (ResultCodeUtil.RC_2S000000.equals(resultCode) || ResultCodeUtil.RC_3C002004.equals(resultCode)) {

			SubsInfo subsInfo = membData.getSubsInfo();

			// select member table
			MembVO memb = memberDao.selectMemberForActiveStatus(deviceCtn);
			if (memb == null) {
				logger.error("failed to select Member data. deviceCtn({})", deviceCtn);
				resultCode = ResultCodeUtil.RC_3C002004;
				return resultCode;
			}

			if (logger.isDebugEnabled()) {
				logger.debug("deviceCtn({}) MEMBER({})", deviceCtn, memb);
			}

			updateMemberUseStatus(memb, subsInfo, resultCode);

			membData.setMemb(memb);
		}

		return resultCode;
	}

	public void updateMemberUseStatus(MembVO memb, SubsInfo subsInfo, ResultCode resultCode) {

		if (memb == null) {
			logger.error("member is null.");
			return;
		}

		String useStatus = null;
		String connDeviceUseYn = null;

		if (ResultCodeUtil.RC_3C002004.equals(resultCode)) {
			useStatus = MembVO.USE_STATUS_FIRE;

		} else {
			if (subsInfo == null) {
				logger.error("subsInfo is null. deviceCtn({})", memb.getMembCtn());
				return;
			}

			String ncasStatus = subsInfo.getCtn_stus_code();

			if (NCASConst.CTN_STUS_CODE_ACTIVE.equals(ncasStatus)) {
				useStatus = MembVO.USE_STATUS_JOIN;
				
			} else if (NCASConst.CTN_STUS_CODE_SUSPEND.equals(ncasStatus)) {
				useStatus = MembVO.USE_STATUS_SUSPEND;

			} else {
				useStatus = MembVO.USE_STATUS_FIRE;
				connDeviceUseYn = ConnDeviceVO.USE_N;
			}
		}
	
		if ((useStatus != null) && (!memb.getUseStatus().equals(useStatus))) {
			if (memberDao.updateMemberStatus(memb.getMembId(), useStatus) == 0) {
				logger.error("failed to update Member status. deviceCtn({}) useStatus({})", memb.getMembCtn(), useStatus);
			}
		}
		
		if (connDeviceUseYn != null) {
			if (connDeviceDao.updateUseYn(memb.getMembId(), connDeviceUseYn) == 0) {
				logger.error("failed to update ConnDevice status. deviceCtn({}) useYn({})", memb.getMembCtn(),
						connDeviceUseYn);
			}
		}
	}
}
