package com.lgu.ccss.common.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lgu.ccss.common.model.MembAgrVO;
import com.lgu.ccss.common.model.MembCommAgrVO;
import com.lgu.ccss.common.model.TermsVO;

import devonframe.dataaccess.CommonDao;

@Component
public class TermsDao {
	private static final Logger logger = LoggerFactory.getLogger(TermsDao.class);
	
	@Value("#{config['common.systemId']}")
	private String systemId;
	
	@Resource(name = "commonDao_oracle")
	private CommonDao commonDao_oracle;
	
	public int insertMembAgr(MembAgrVO membAgr) {
		Date now = new Date();

		membAgr.setRegId(systemId);
		membAgr.setRegDt(now);
		membAgr.setUpdId(systemId);
		membAgr.setUpdDt(now);
		
		return commonDao_oracle.insert("Terms.insertMembAgr", membAgr);
	}
	
	public int insertMembCommAgr(MembCommAgrVO membCommAgr) {
		Date now = new Date();

		membCommAgr.setRegId(systemId);
		membCommAgr.setRegDt(now);
		membCommAgr.setUpdId(systemId);
		membCommAgr.setUpdDt(now);
		
		if (logger.isDebugEnabled()) {
			logger.debug("insertMembCommAgr({})", membCommAgr.toString());
		}
		
		return commonDao_oracle.insert("Terms.insertMembCommAgr", membCommAgr);	
	}
	
	public List<MembCommAgrVO> selectMembCommAgr(String uiccId) {
		
		MembCommAgrVO membCommAgr = new MembCommAgrVO();
		membCommAgr.setUiccId(uiccId);
		
		return commonDao_oracle.selectList("Terms.selectMembCommAgr", membCommAgr);
	}
	
	public TermsVO selectTerms(String termsNo) {
		TermsVO terms = new TermsVO();
		terms.setTermsNo(termsNo);
		
		return commonDao_oracle.select("Terms.selectTermsByNo", terms);
	}
}
