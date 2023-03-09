package com.lgu.ccss.mypage.pnd.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.lgu.ccss.mypage.pnd.model.TermsVO;

@Component
public class PNDDao {
	@Resource(name = "sqlSession_oracle")
	private SqlSession sqlSession_oralce;

	@Resource(name = "sqlSession_altibase")
	private SqlSession sqlSession_altibase;

	/**
	 * 등록된 서비스 약관 전체 리스트
	 * 
	 * @return
	 */
	public List<TermsVO> selectCurrentTerms() {
		return sqlSession_oralce.selectList("Mypagepnd.selectCurrentTerms");
	}

/**
	 * 등록된 서비스 약관
	 * 
	 * @return
	 */
	public TermsVO selectOneCurrentServiceTerms() {
		return sqlSession_oralce.selectOne("Mypagepnd.selectOneCurrentServiceTerms");
	}
	

	/*
	 * 매니저앱 이용약관
	 * 
	 * @return
	 */	
	public TermsVO  selectOneMobileServieTerms(TermsVO termsVO) {
		return sqlSession_oralce.selectOne("Mypagepnd.selectOneMobileServieTerms",termsVO);
	}
	
	/**
	 * 접속모델 조회 20190128 kangjin
	 * 
	 * @return
	 */
	public String getConnDeviceModel(String membId) {
		return sqlSession_oralce.selectOne("Mypagepnd.getConnDeviceModel", membId);
	}
	
}
