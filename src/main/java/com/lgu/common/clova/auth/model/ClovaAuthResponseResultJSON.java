package com.lgu.common.clova.auth.model;

public class ClovaAuthResponseResultJSON {
	String extensionId;
	String name;
	String isLinked;
	String largeIconImage;
	String smallIconImage;
	String mainImage;
	String deviceCount;
	String capability;
	String ExtensionEndpoint;
	String IsOnDeviceNameChangedSupported;
	public String getExtensionId() {
		return extensionId;
	}
	public void setExtensionId(String extensionId) {
		this.extensionId = extensionId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsLinked() {
		return isLinked;
	}
	public void setIsLinked(String isLinked) {
		this.isLinked = isLinked;
	}
	public String getLargeIconImage() {
		return largeIconImage;
	}
	public void setLargeIconImage(String largeIconImage) {
		this.largeIconImage = largeIconImage;
	}
	public String getSmallIconImage() {
		return smallIconImage;
	}
	public void setSmallIconImage(String smallIconImage) {
		this.smallIconImage = smallIconImage;
	}
	public String getMainImage() {
		return mainImage;
	}
	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}
	public String getDeviceCount() {
		return deviceCount;
	}
	public void setDeviceCount(String deviceCount) {
		this.deviceCount = deviceCount;
	}
	public String getCapability() {
		return capability;
	}
	public void setCapability(String capability) {
		this.capability = capability;
	}
	public String getExtensionEndpoint() {
		return ExtensionEndpoint;
	}
	public void setExtensionEndpoint(String extensionEndpoint) {
		ExtensionEndpoint = extensionEndpoint;
	}
	public String getIsOnDeviceNameChangedSupported() {
		return IsOnDeviceNameChangedSupported;
	}
	public void setIsOnDeviceNameChangedSupported(String isOnDeviceNameChangedSupported) {
		IsOnDeviceNameChangedSupported = isOnDeviceNameChangedSupported;
	}
	@Override
	public String toString() {
		return "ClovaAuthResponseResultJSON [extensionId=" + extensionId + ", name=" + name + ", isLinked=" + isLinked
				+ ", largeIconImage=" + largeIconImage + ", smallIconImage=" + smallIconImage + ", mainImage="
				+ mainImage + ", deviceCount=" + deviceCount + ", capability=" + capability + ", ExtensionEndpoint="
				+ ExtensionEndpoint + ", IsOnDeviceNameChangedSupported=" + IsOnDeviceNameChangedSupported + "]";
	}
	
	
}
