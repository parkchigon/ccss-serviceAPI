package com.lgu.ccss.common.dao.datagift;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lgu.ccss.common.model.datagift.DataGiftVO;

import devonframe.dataaccess.CommonDao;

@Component
public class DatagiftDao {

	@Resource(name = "commonDao_oracle")
	private CommonDao commonDao_oracle;
	
	
	public int insertDataGift(DataGiftVO datagift)
	{
		return commonDao_oracle.insert("DataGift.insertDataGift", datagift);
	}
	
	public DataGiftVO selectDataGiftByOid(String lgdOid) {

		if (lgdOid == null || lgdOid == "") {
			throw new IllegalArgumentException();
		}
		
		DataGiftVO datagift = new DataGiftVO();
		datagift.setLgdOid(lgdOid);

		return commonDao_oracle.select("DataGift.selectDataGiftByOid", datagift);
	}	

	public DataGiftVO selectDataGiftByGiftNo(String giftNo) {

		if (giftNo == null || giftNo == "") {
			throw new IllegalArgumentException();
		}
		
		DataGiftVO datagift = new DataGiftVO();
		datagift.setGiftNo(giftNo);

		return commonDao_oracle.select("DataGift.selectDataGiftByGiftNo", datagift);
	}	

	public List<DataGiftVO> selectUnRegDataGift(String memberId) {

		if (memberId == null || memberId == "") {
			throw new IllegalArgumentException();
		}
		
		DataGiftVO datagift = new DataGiftVO();
		datagift.setMemberId(memberId);

		return commonDao_oracle.selectList("DataGift.selectUnRegDataGift", datagift);
	}
	
	public String selectSellAmountByGiftNo(String giftNo) {

		if (giftNo == null || giftNo == "") {
			throw new IllegalArgumentException();
		}
		
		DataGiftVO datagift = new DataGiftVO();
		datagift.setGiftNo(giftNo);

		return commonDao_oracle.select("DataGift.selectSellAmountByGiftNo", datagift);
	}
	
	public int updateDataGift(String lgdOid, String giftNo, String issueRegKup) {
		
		DataGiftVO datagift = new DataGiftVO();
		datagift.setLgdOid(lgdOid);
		datagift.setGiftNo(giftNo);
		datagift.setIssueRegKup(issueRegKup);
		
		return commonDao_oracle.update("DataGift.updateDataGift", datagift);
	}
	
	public int deleteDataGiftByOid(String lgdOid) {
		
		DataGiftVO datagift = new DataGiftVO();
		datagift.setLgdOid(lgdOid);
		
		return commonDao_oracle.update("DataGift.deleteDataGiftByOid", datagift);
	}	

	public int deleteDataGiftByTid(String lgdTid) {
		
		DataGiftVO datagift = new DataGiftVO();
		datagift.setLgdTid(lgdTid);
		
		return commonDao_oracle.update("DataGift.deleteDataGiftByTid", datagift);
	}	
}
