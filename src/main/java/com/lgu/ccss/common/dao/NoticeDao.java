package com.lgu.ccss.common.dao;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lgu.ccss.common.model.NoticeVO;

import devonframe.dataaccess.CommonDao;

@Component
public class NoticeDao {
	
	@Resource(name = "commonDao_altibase")
	private CommonDao commonDao_altibase;
	
	@Resource(name = "commonDao_oracle")
	private CommonDao commonDao_oracle;

	public List<NoticeVO> selectNoticeList(NoticeVO noticeVO){
		if (noticeVO == null ) {
			throw new IllegalArgumentException();
		}
		return  commonDao_oracle.selectList("Notice.selectNoticeList", noticeVO);
	}
	
	public List<NoticeVO> selectCarddeckNotice(String serviceCategory, String firmVer, String serviceExposure){
		if (serviceCategory == null ) {
			throw new IllegalArgumentException();
		}
		
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setServiceCategory(serviceCategory);
		noticeVO.setFirmVer(firmVer);
		noticeVO.setServiceExposure(serviceExposure);
		
		return  commonDao_oracle.selectList("Notice.selectCarddeckNotice", noticeVO);
	}
}
