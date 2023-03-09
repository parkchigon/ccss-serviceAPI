package com.lgu.ccss.mypage.ev.dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lgu.ccss.mypage.ev.model.EVVO;

@Component
public class EVDao {

	@Resource(name = "sqlSession_oracle")
	private SqlSession sqlSession_oralce;

	@Resource(name = "sqlSession_altibase")
	private SqlSession sqlSession_altibase;

/**
	 * 등록된 서비스 약관
	 * 
	 * @return
	 */
	public EVVO selectEVChrgInfo(EVVO eVVO) {
		return sqlSession_oralce.selectOne("Mypageev.selectEVChrgInfo",eVVO);
	}

	
}
