package com.lgu.ccss.common.dao;


import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lgu.ccss.common.model.Send2CarVO;

import devonframe.dataaccess.CommonDao;

@Component
public class Send2CarDao {
	
	@Resource(name = "commonDao_altibase")
	private CommonDao commonDao_altibase;
	
	@Resource(name = "commonDao_oracle")
	private CommonDao commonDao_oracle;
	
	public boolean insertTargetSend(Send2CarVO send2CarVO){
		if (send2CarVO == null ) {
			throw new IllegalArgumentException();
		}
		
		int count = commonDao_oracle.insert("Send2Car.insertTargetSend", send2CarVO);
		return (count > 0 )? true:false;
	}
	
	public boolean checkDupleTargetSendList(Send2CarVO send2CarVO){
		if (send2CarVO == null ) {
			throw new IllegalArgumentException();
		}
		
		int count = commonDao_oracle.select("Send2Car.checkDupleTargetSendList", send2CarVO);
		return (count > 0 )? true:false;
	}
	
	public List<Send2CarVO> selectMgrAppTargetHis (Send2CarVO send2CarVO) {

		if (send2CarVO == null ) {
			throw new IllegalArgumentException();
		}
		
		return commonDao_oracle.selectList("Send2Car.selectMgrAppTargetHis", send2CarVO);
	}
	
	public int deleteTargetHis (Send2CarVO send2CarVO) {

		if (send2CarVO == null ) {
			throw new IllegalArgumentException();
		}
		
		return commonDao_oracle.update("Send2Car.deleteTargetHis", send2CarVO);
	}
	
	public int updateTargetStatus (Send2CarVO send2CarVO) {

		if (send2CarVO == null ) {
			throw new IllegalArgumentException();
		}
		
		return commonDao_oracle.update("Send2Car.updateTargetStatus", send2CarVO);
	}
	
	public List<Send2CarVO> selectTargetList (Send2CarVO send2CarVO) {

		if (send2CarVO == null ) {
			throw new IllegalArgumentException();
		}
		
		return commonDao_oracle.selectList("Send2Car.selectTargetList", send2CarVO);
	}
	
	public int deleteScheduleTarget (Send2CarVO send2CarVO) {

		if (send2CarVO == null ) {
			throw new IllegalArgumentException();
		}
		
		return commonDao_oracle.delete("Send2Car.deleteScheduleTarget", send2CarVO);
	}
	
	public int updateTenDay (Send2CarVO send2CarVO) {

		if (send2CarVO == null ) {
			throw new IllegalArgumentException();
		}
		
		return commonDao_oracle.update("Send2Car.updateTenDay", send2CarVO);
	}
	
	public int updateOneHundred (Send2CarVO send2CarVO) {

		if (send2CarVO == null ) {
			throw new IllegalArgumentException();
		}
		
		return commonDao_oracle.update("Send2Car.updateOneHundred", send2CarVO);
	}

}
