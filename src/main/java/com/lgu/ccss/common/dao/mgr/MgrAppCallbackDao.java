package com.lgu.ccss.common.dao.mgr;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lgu.ccss.common.model.CallBackInfoVO;

import devonframe.dataaccess.CommonDao;

@Component
public class MgrAppCallbackDao {
	@Resource(name = "commonDao_oracle")
	private CommonDao commonDao_oracle;
	
	
	public CallBackInfoVO selectTbCallBackInfo(CallBackInfoVO callBackInfoVO){
		if (callBackInfoVO.getMgrappId() == null) {
			throw new IllegalArgumentException();
		}
		return commonDao_oracle.select("MgrappCallback.selectTbCallBackInfo",callBackInfoVO);
	}
	
	
	public boolean insertTbCallBackInfo(CallBackInfoVO callBackInfoVO){
		if (callBackInfoVO.getMgrappId() == null) {
			throw new IllegalArgumentException();
		}
		int cnt=commonDao_oracle.update("MgrappCallback.insertTbCallBackInfo",callBackInfoVO);
		return (cnt > 0 )? true:false;	
	}
	
	
	public boolean deleteTbCallBackInfo(CallBackInfoVO callBackInfoVO) {
		if (callBackInfoVO.getMgrappId() == null) {
			throw new IllegalArgumentException();
		}
		int cnt=commonDao_oracle.delete("MgrappCallback.deleteTbCallBackInfo",callBackInfoVO);
		return (cnt > 0 )? true:false;	
	}
}
