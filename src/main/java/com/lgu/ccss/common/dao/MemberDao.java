package com.lgu.ccss.common.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lgu.ccss.common.model.MembVO;

import devonframe.dataaccess.CommonDao;

@Component
public class MemberDao {

	@Value("#{config['common.systemId']}")
	private String systemId;

	@Resource(name = "commonDao_oracle")
	private CommonDao commonDao_oracle;

	public int insertMember(MembVO memb) {
		
		memb.setRegId(systemId);
		memb.setUpdId(systemId);

		return commonDao_oracle.insert("Member.insertMember", memb);
	}

	public int updateMemberInfo(String membId, String transToken, int loginFailCnt, String productNm) {
		Date now = new Date();
		
		MembVO membVo = new MembVO();
		membVo.setMembId(membId);
		membVo.setTransToken(transToken);
		membVo.setLoginFailCnt(loginFailCnt);
		membVo.setLatestLoginDt(now);
		membVo.setProductNm(productNm);
		
		membVo.setUpdId(systemId);
		membVo.setUpdDt(now);
		
		return commonDao_oracle.update("Member.updateMemberInfo", membVo);
	}

	public int updateMemberStatus(String membId, String useStatus) {
		Date now = new Date();
		
		MembVO membVo = new MembVO();
		membVo.setMembId(membId);
		membVo.setUseStatus(useStatus);
		
		membVo.setUpdId(systemId);
		membVo.setUpdDt(now);
		
		return commonDao_oracle.update("Member.updateMemberStatus", membVo);
	}
	
	public int increaseLoginFailCnt(String membId) {
		Date now = new Date();
		
		MembVO memb = new MembVO();
		memb.setMembId(membId);
		
		memb.setUpdId(systemId);
		memb.setUpdDt(now);
		
		return commonDao_oracle.update("Member.increaseLoginFailCnt", memb);
	}
	
	public int clearLoginFailCnt(String membId) {
		Date now = new Date();
		
		MembVO memb = new MembVO();
		memb.setMembId(membId);
		
		memb.setUpdId(systemId);
		memb.setUpdDt(now);
		
		return commonDao_oracle.update("Member.clearLoginFailCnt", memb);
	}
	
	public MembVO selectMemberByID(String membId) {

		if (membId == null || membId == "") {
			throw new IllegalArgumentException();
		}
		
		MembVO memb = new MembVO();
		memb.setMembId(membId);

		return commonDao_oracle.select("Member.selectMemberbyID", memb);
	}
	
	public MembVO selectMemberByNO(String membNo) {

		if (membNo == null || membNo == "") {
			throw new IllegalArgumentException();
		}
		
		MembVO memb = new MembVO();
		memb.setMembNo(membNo);

		return commonDao_oracle.select("Member.selectMemberbyNO", memb);
	}
	
	public List<MembVO> selectMemberByCTN(String ctn) {

		if (ctn == null || ctn.length() == 0) {
			throw new IllegalArgumentException();
		}

		MembVO memb = new MembVO();
		memb.setMembCtn(ctn);
		
		return commonDao_oracle.selectList("Member.selectMemberbyCTN", memb);
	}
	
	public MembVO selectMemberForActiveStatus(String ctn) {

		if (ctn == null || ctn.length() == 0) {
			throw new IllegalArgumentException();
		}

		MembVO memb = new MembVO();
		memb.setMembCtn(ctn);
		memb.setUseStatus(MembVO.USE_STATUS_FIRE);
		
		return commonDao_oracle.select("Member.selectMemberForActiveStatus", memb);
	}
	
	
	public int ckeckUserstatus(MembVO membVo){
		return commonDao_oracle.select("Member.ckeckUserstatus", membVo);
	}
	
	// 주기기 사용 여부
	public int ckeckMainUseYn(MembVO membVo){
		return commonDao_oracle.select("Member.ckeckMainUseYn", membVo);
	}
	
	
	public List<MembVO> getMemberInfoList(MembVO membVo){
		return commonDao_oracle.selectList("Member.getMemberInfoList", membVo);
	}
	
	public int updateMemberPayResv(String membId, String payResvYn) {
		
		MembVO membVo = new MembVO();
		membVo.setMembId(membId);
		membVo.setPayResvYn(payResvYn);
		
		return commonDao_oracle.update("Member.updateMemberPayResv", membVo);
	}
	
	public MembVO selectMemberGuestmodeAgrYn(String membId) {

		if (membId == null || membId == "") {
			throw new IllegalArgumentException();
		}
		
		MembVO memb = new MembVO();
		memb.setMembId(membId);

		return commonDao_oracle.select("Member.selectMemberGuestmodeAgrYn", memb);
	}
	
	public int updateMemberGuestmodeAgrYn(String membId, String guestmodeAgrYn) {
		Date now = new Date();
		
		MembVO membVo = new MembVO();
		membVo.setMembId(membId);
		membVo.setGuestmodeAgrYn(guestmodeAgrYn);
		membVo.setLatestLoginDt(now);
		
		membVo.setUpdId(systemId);
		membVo.setUpdDt(now);
		
		return commonDao_oracle.update("Member.updateMemberGuestmodeAgrYn", membVo);
	}
}
