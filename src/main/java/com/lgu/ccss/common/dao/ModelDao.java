package com.lgu.ccss.common.dao;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lgu.ccss.common.model.DeviceModelVO;
import com.lgu.ccss.common.model.OemVO;
import com.lgu.ccss.common.model.VehicleModelVO;

import devonframe.dataaccess.CommonDao;

@Component
public class ModelDao {
	@Value("#{config['common.systemId']}")
	private String systemId;

	@Resource(name = "commonDao_oracle")
	private CommonDao commonDao_oracle;
	
	public OemVO selectOem(String oemId) {

		if (oemId == null || oemId.length() == 0) {
			throw new IllegalArgumentException();
		}
		
		OemVO oem = new OemVO();
		oem.setOemId(oemId);

		return commonDao_oracle.select("Oem.selectOem", oem);
	}
	
	public VehicleModelVO selectVehicleModel(String vehicleModelId) {

		if (vehicleModelId == null || vehicleModelId.length() == 0) {
			throw new IllegalArgumentException();
		}
		
		VehicleModelVO model = new VehicleModelVO();
		model.setVehicleModelId(vehicleModelId);

		return commonDao_oracle.select("VehicleModel.selectVehicleModel", model);
	}
	
	public DeviceModelVO selectDeviceModelByName(String deviceModelNm, String deviceType) {

		if (deviceModelNm == null || deviceModelNm.length() == 0) {
			throw new IllegalArgumentException();
		}
		
		if (deviceType == null || deviceType.length() == 0) {
			throw new IllegalArgumentException();
		}
		
		DeviceModelVO model = new DeviceModelVO();
		model.setDeviceModelNm(deviceModelNm);
		model.setDeviceType(deviceType);

		return commonDao_oracle.select("DeviceModel.selectDeviceModelByName", model);
	}
	
	public DeviceModelVO selectClovaClientInfo(String deviceModelNm) {

		if (deviceModelNm == null || deviceModelNm.length() == 0) {
			throw new IllegalArgumentException();
		}
		
		DeviceModelVO deviceModelVO = new DeviceModelVO();
		deviceModelVO.setDeviceModelNm(deviceModelNm);

		return commonDao_oracle.select("DeviceModel.selectClovaClientInfo", deviceModelVO);
	}
	
	public DeviceModelVO selectClovaClientInfoByModelId(String deviceModelId) {

		if (deviceModelId == null || deviceModelId.length() == 0) {
			throw new IllegalArgumentException();
		}
		
		DeviceModelVO deviceModelVO = new DeviceModelVO();
		deviceModelVO.setDeviceModelId(deviceModelId);

		return commonDao_oracle.select("DeviceModel.selectClovaClientInfoByModelId", deviceModelVO);
	}
	
	public OemVO selectPushId(String oemId) {

		if (oemId == null || oemId.length() == 0) {
			throw new IllegalArgumentException();
		}
		
		OemVO oem = new OemVO();
		oem.setOemId(oemId);

		return commonDao_oracle.select("Oem.selectPushId", oem);
	}
}
