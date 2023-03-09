package com.lgu.ccss.common.dao;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lgu.ccss.common.model.ScheduleSetVO;
import devonframe.dataaccess.CommonDao;

@Component
public class ScheduleDao {
	
	@Resource(name = "commonDao_altibase")
	private CommonDao commonDao_altibase;
	
	@Resource(name = "commonDao_oracle")
	private CommonDao commonDao_oracle;

	
	public List<ScheduleSetVO> selectSchedulList(ScheduleSetVO scheduleSetVO){
		if (scheduleSetVO == null ) {
			throw new IllegalArgumentException();
		}
		return  commonDao_oracle.selectList("Schedule.selectSchedulList", scheduleSetVO);
	}
	
	public boolean insertSecheuleInfo(ScheduleSetVO scheduleSetVO){
		if (scheduleSetVO == null ) {
			throw new IllegalArgumentException();
		}
		
		int count = commonDao_oracle.insert("Schedule.insertSecheuleInfo", scheduleSetVO);
		return (count > 0 )? true:false;
	}
	
	
	public boolean updateSecheuleInfo(ScheduleSetVO scheduleSetVO){
		if (scheduleSetVO == null ) {
			throw new IllegalArgumentException();
		}
		
		int count = commonDao_oracle.update("Schedule.updateSecheuleInfo", scheduleSetVO);
		return (count > 0 )? true:false;
	}
	
	public boolean deleteSecheuleInfo(ScheduleSetVO scheduleSetVO){
		if (scheduleSetVO == null ) {
			throw new IllegalArgumentException();
		}
		
		int count = commonDao_oracle.update("Schedule.deleteSecheuleInfo", scheduleSetVO);
		return (count > 0 )? true:false;
	}
	
}
