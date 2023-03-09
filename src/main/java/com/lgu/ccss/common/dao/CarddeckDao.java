package com.lgu.ccss.common.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lgu.ccss.common.model.CardVO;
import com.lgu.ccss.common.model.EventCardVO;

import devonframe.dataaccess.CommonDao;

@Component
public class CarddeckDao {
	@Value("#{config['common.systemId']}")
	private String systemId;

	@Resource(name = "commonDao_oracle")
	private CommonDao commonDao_oracle;
	
	public List<CardVO> selectCarddeckList(String serviceCategory) {

		if (serviceCategory == null || serviceCategory.length() == 0) {
			throw new IllegalArgumentException();
		}
		
		CardVO cardVO = new CardVO();
		cardVO.setServiceCategory(serviceCategory);

		return commonDao_oracle.selectList("Carddeck.selectCarddeckList", cardVO);
	}
	
	public List<EventCardVO> selectCarddeckEvent(String serviceCategory) {

		if (serviceCategory == null || serviceCategory.length() == 0) {
			throw new IllegalArgumentException();
		}
		
		EventCardVO eventCardVO = new EventCardVO();
		eventCardVO.setServiceCategory(serviceCategory);

		return commonDao_oracle.selectList("Carddeck.selectCarddeckEvent", eventCardVO);
	}
}