package com.lgu.common.map.model;

import java.util.List;

import com.lgu.ccss.mgr.send2car.model.totalSearch.AdmJSON;
import com.lgu.common.map.model.poi.POIDataJSON;
public class ResTotalSearchJSON extends ResErrorJSON{	
	
	private int admtotalcount;
	private int admcount;
	
	private List<AdmJSON> adm;
	private List<POIDataJSON> poi;
	private List<POIDataJSON> tel;
	private List<POIDataJSON> ucp;
	
	private int totalcount;
	private int poitotalcount;
	private int tel_poitotalcount;
	private int reftotalcount;
	
	private int tel_poicount;
	private int refcount;
	private int poicount;
	private int ucp_poitotalcount;
	private int ucp_poicount;
	
	private String deviceType;
	
	
	public int getAdmtotalcount() {
		return admtotalcount;
	}
	public void setAdmtotalcount(int admtotalcount) {
		this.admtotalcount = admtotalcount;
	}
	public int getAdmcount() {
		return admcount;
	}
	public void setAdmcount(int admcount) {
		this.admcount = admcount;
	}
	public List<AdmJSON> getAdm() {
		return adm;
	}
	public void setAdm(List<AdmJSON> adm) {
		this.adm = adm;
	}
	public List<POIDataJSON> getPoi() {
		return poi;
	}
	public void setPoi(List<POIDataJSON> poi) {
		this.poi = poi;
	}
	
	public List<POIDataJSON> getTel() {
		return tel;
	}
	public void setTel(List<POIDataJSON> tel) {
		this.tel = tel;
	}
	public List<POIDataJSON> getUcp() {
		return ucp;
	}
	public void setUcp(List<POIDataJSON> ucp) {
		this.ucp = ucp;
	}
	public int getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
	public int getPoitotalcount() {
		return poitotalcount;
	}
	public void setPoitotalcount(int poitotalcount) {
		this.poitotalcount = poitotalcount;
	}
	public int getTel_poitotalcount() {
		return tel_poitotalcount;
	}
	public void setTel_poitotalcount(int tel_poitotalcount) {
		this.tel_poitotalcount = tel_poitotalcount;
	}
	public int getReftotalcount() {
		return reftotalcount;
	}
	public void setReftotalcount(int reftotalcount) {
		this.reftotalcount = reftotalcount;
	}
	public int getTel_poicount() {
		return tel_poicount;
	}
	public void setTel_poicount(int tel_poicount) {
		this.tel_poicount = tel_poicount;
	}
	public int getRefcount() {
		return refcount;
	}
	public void setRefcount(int refcount) {
		this.refcount = refcount;
	}
	public int getPoicount() {
		return poicount;
	}
	public void setPoicount(int poicount) {
		this.poicount = poicount;
	}
	public int getUcp_poitotalcount() {
		return ucp_poitotalcount;
	}
	public void setUcp_poitotalcount(int ucp_poitotalcount) {
		this.ucp_poitotalcount = ucp_poitotalcount;
	}
	public int getUcp_poicount() {
		return ucp_poicount;
	}
	public void setUcp_poicount(int ucp_poicount) {
		this.ucp_poicount = ucp_poicount;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
}
