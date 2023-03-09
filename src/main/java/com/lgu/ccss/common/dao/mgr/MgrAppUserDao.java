package com.lgu.ccss.common.dao.mgr;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import devonframe.dataaccess.CommonDao;
import com.lgu.ccss.common.model.mgr.MgrAppUserVO;

@Component
public class MgrAppUserDao {

	@Resource(name = "commonDao_oracle")
	private CommonDao commonDao_oracle;

	public int insertTbMgrAppUser(MgrAppUserVO mgrAppUserVO) {
		return commonDao_oracle.insert("MgrAppUser.insertTbMgrAppUser",mgrAppUserVO);
	}
	
	public MgrAppUserVO selectMgrUserInfo(MgrAppUserVO mgrAppUserVO){
		return commonDao_oracle.select("MgrAppUser.selectMgrUserInfo",mgrAppUserVO);
	}
	
	public boolean updateMgrAppUuid(MgrAppUserVO mgrAppUserVO){
		int cnt=commonDao_oracle.update("MgrAppUser.updateMgrAppUuid",mgrAppUserVO);
		return (cnt > 0 )? true:false;	
	}
	
	public List<MgrAppUserVO> selectMgrAppUserInfoList(MgrAppUserVO mgrAppUserVO){
		return commonDao_oracle.selectList("MgrAppUser.selectMgrUserInfoList",mgrAppUserVO);
	}
	
	public int updateMgrAppUserInfo(MgrAppUserVO mgrAppUserVO){
		return commonDao_oracle.update("MgrAppUser.updateMgrAppUserInfo",mgrAppUserVO);
	}
	
	public int updateMgrConStatus(MgrAppUserVO mgrAppUserVO){
		return commonDao_oracle.update("MgrAppUser.updateMgrConStatus",mgrAppUserVO);
	}
	
	public int deleteUser(MgrAppUserVO mgrAppUserVO){
		return commonDao_oracle.update("MgrAppUser.deleteUser",mgrAppUserVO);
	}
	
	public int deleteUserByMgrAppId(MgrAppUserVO mgrAppUserVO){
		return commonDao_oracle.update("MgrAppUser.deleteUserByMgrAppId",mgrAppUserVO);
	}
	
	public List<MgrAppUserVO> selectMgrAppPusthTargetUserList(MgrAppUserVO mgrAppUserVO){
		return commonDao_oracle.selectList("MgrAppUser.selectMgrAppPusthTargetUserList",mgrAppUserVO);
	}
	
	public boolean updateMgrUserPushId(MgrAppUserVO mgrAppUserVO){
		int cnt=commonDao_oracle.update("MgrAppUser.updateMgrUserPushId",mgrAppUserVO);
		return (cnt > 0 )? true:false;	
	}

	public List<MgrAppUserVO> selectMgrappDevice(String membId){
		return commonDao_oracle.selectList("MgrAppUser.selectMgrappDevice",membId);
	}

	public List<MgrAppUserVO> selectMgrappUser(MgrAppUserVO mgrAppUserVO) {
		return commonDao_oracle.selectList("MgrAppUser.selectMgrappUser", mgrAppUserVO);
	}
	
	public List<MgrAppUserVO> selectEvMgrAppPusthTargetUserList(MgrAppUserVO mgrAppUserVO){
		return commonDao_oracle.selectList("MgrAppUser.selectEvMgrAppPusthTargetUserList",mgrAppUserVO);
	}

	public String selectDeviceModelId(String mgrappLoginId) {
		return commonDao_oracle.select("MgrAppUser.selectDeviceModelId",mgrappLoginId);
	}
}
