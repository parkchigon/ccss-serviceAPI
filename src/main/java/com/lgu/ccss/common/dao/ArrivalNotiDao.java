package com.lgu.ccss.common.dao;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lgu.ccss.common.model.ArrivalAlarmRcptVO;
import com.lgu.ccss.common.model.ArrivalAlarmSetVO;
import com.lgu.ccss.mgr.arrival.model.rcpt.search.ResultArrivalSearchVO;

import devonframe.dataaccess.CommonDao;

@Component
public class ArrivalNotiDao {
	
	@Resource(name = "commonDao_altibase")
	private CommonDao commonDao_altibase;
	
	@Resource(name = "commonDao_oracle")
	private CommonDao commonDao_oracle;

	
	public List<ResultArrivalSearchVO> selectArrivalAlarmInfo(ArrivalAlarmSetVO arrivalAlarmSetVO){
		if (arrivalAlarmSetVO == null ) {
			throw new IllegalArgumentException();
		}
		return  commonDao_oracle.selectList("Arrival.selectArrivalAlarmInfo", arrivalAlarmSetVO);
	}
	
	public List<ArrivalAlarmSetVO> selectUsedArrivalAlarmInfo(ArrivalAlarmSetVO arrivalAlarmSetVO){
		if (arrivalAlarmSetVO == null ) {
			throw new IllegalArgumentException();
		}
		return  commonDao_oracle.selectList("Arrival.selectUsedArrivalAlarmInfo", arrivalAlarmSetVO);
	}
	
	public List<ResultArrivalSearchVO> selectArrivalRcptInfo(ArrivalAlarmSetVO arrivalAlarmSetVO){
		if (arrivalAlarmSetVO == null ) {
			throw new IllegalArgumentException();
		}
		return  commonDao_oracle.selectList("Arrival.selectArrivalRcptInfo", arrivalAlarmSetVO);
	}
	
	public boolean insertArrivalAlarmInfo(ArrivalAlarmSetVO arrivalAlarmSetVO){
		if (arrivalAlarmSetVO == null ) {
			throw new IllegalArgumentException();
		}
		
		int count = commonDao_oracle.insert("Arrival.insertArrivalAlarmInfo", arrivalAlarmSetVO);
		return (count > 0 )? true:false;
	}
	
	
	public boolean updateArrivalAlarmInfo(ArrivalAlarmSetVO arrivalAlarmSetVO){
		if (arrivalAlarmSetVO == null ) {
			throw new IllegalArgumentException();
		}
		
		int count = commonDao_oracle.update("Arrival.updateArrivalAlarmInfo", arrivalAlarmSetVO);
		return (count > 0 )? true:false;
	}
	
	
	@Transactional
	public boolean deleteArrivalAlarmInfo(ArrivalAlarmSetVO arrivalAlarmSetVO){
		if (arrivalAlarmSetVO == null ) {
			throw new IllegalArgumentException();
		}
		
		ArrivalAlarmRcptVO arrivalAlarmRcptVO = new ArrivalAlarmRcptVO();
		arrivalAlarmRcptVO.setMembId(arrivalAlarmSetVO.getMembId());
		arrivalAlarmRcptVO.setArrivalAlarmId(arrivalAlarmSetVO.getArrivalAlarmId());
		
		deleteArrivalAlarmRcpt(arrivalAlarmRcptVO);
		
		int count = commonDao_oracle.delete("Arrival.deleteArrivalAlarmInfo", arrivalAlarmSetVO);
		return (count > 0 )? true:false;
	}
	
	
	public boolean deleteArrivalAlarmRcpt(ArrivalAlarmRcptVO arrivalAlarmRcptVO){
		if (arrivalAlarmRcptVO == null ) {
			throw new IllegalArgumentException();
		}
		int count = commonDao_oracle.delete("Arrival.deleteArrivalAlarmRcpt", arrivalAlarmRcptVO);
		return (count > 0 )? true:false;
	}
	
	
	public boolean insertArrivalAlarmRcpt(ArrivalAlarmRcptVO arrivalAlarmRcptVO){
		if (arrivalAlarmRcptVO == null ) {
			throw new IllegalArgumentException();
		}
		
		int count = commonDao_oracle.insert("Arrival.insertArrivalAlarmRcpt", arrivalAlarmRcptVO);
		return (count > 0 )? true:false;
	}
	
}
