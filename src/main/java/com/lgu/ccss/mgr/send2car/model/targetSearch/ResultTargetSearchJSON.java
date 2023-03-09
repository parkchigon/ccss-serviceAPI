package com.lgu.ccss.mgr.send2car.model.targetSearch;

import java.util.List;

import com.lgu.ccss.mgr.send2car.model.totalSearch.AdmListJSON;
import com.lgu.ccss.mgr.send2car.model.totalSearch.PoiListJSON;

public class ResultTargetSearchJSON {	
	
	private int totalCount;
	private int count;
	
	private int admtotalcount;
	private int poitotalcount;
	//private int tel_poitotalcount;
	//private int reftotalcount;
	
	private int admcount;
	private int poicount;
	//private int tel_poicount;
	//private int refcount;
	
	//private int ucp_poitotalcount;
	//private int ucp_poicount;
	
	private List<AdmListJSON> admList;
	
	private List<PoiListJSON> poiList;
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public int getAdmtotalcount() {
		return admtotalcount;
	}
	public void setAdmtotalcount(int admtotalcount) {
		this.admtotalcount = admtotalcount;
	}
	public int getPoitotalcount() {
		return poitotalcount;
	}
	public void setPoitotalcount(int poitotalcount) {
		this.poitotalcount = poitotalcount;
	}
	
	public int getAdmcount() {
		return admcount;
	}
	public void setAdmcount(int admcount) {
		this.admcount = admcount;
	}
	
	public int getPoicount() {
		return poicount;
	}
	public void setPoicount(int poicount) {
		this.poicount = poicount;
	}
	
	public List<AdmListJSON> getAdmList() {
		return admList;
	}
	public void setAdmList(List<AdmListJSON> admList) {
		this.admList = admList;
	}
	public List<PoiListJSON> getPoiList() {
		return poiList;
	}
	public void setPoiList(List<PoiListJSON> poiList) {
		this.poiList = poiList;
	}
	
	
	
	
}
