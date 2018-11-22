package org.evoke.product.model;

import java.io.Serializable;
import java.util.Map;

public class BaseRequest implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String source;
	private String appVersion;
	private String deviceId;
	private String appOS;
	
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getAppOS() {
		return appOS;
	}

	public void setAppOS(String appOS) {
		this.appOS = appOS;
	}

	
}
