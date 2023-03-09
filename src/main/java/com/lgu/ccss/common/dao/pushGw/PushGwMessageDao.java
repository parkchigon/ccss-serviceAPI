package com.lgu.ccss.common.dao.pushGw;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import com.lgu.ccss.common.model.pushgw.PushGwMessageVO;

import devonframe.dataaccess.CommonDao;

@Component
public class PushGwMessageDao {

	@Resource(name = "commonDao_altibase")
	private CommonDao commonDao_altibase;

	public boolean insertTbAppPushQueue(PushGwMessageVO pushGwMessageVO) {
		
		if (pushGwMessageVO == null ) {
			throw new IllegalArgumentException();
		}
		int count = commonDao_altibase.insert("PushGwMessage.insertTbAppPushQueue",pushGwMessageVO);
		return (count > 0 )? true:false;
	}
}
