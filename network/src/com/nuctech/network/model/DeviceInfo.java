package com.nuctech.network.model;

import java.io.Serializable;

/**
 * <p>站点设备信息表<br/>
 * @className DeviceInfo.java<br/>
 * @packageName com.nuctech.station.model<br/>
 * @date 2018-6-28 下午4:14:45<br/>
 * </p>
 * 
 * @author Li Lu
 * @since 2018-6-28
 * @version v1.0.0
 */
public class DeviceInfo implements Serializable{
	private static final long	serialVersionUID	= 3063195954277191232L;
	
	private String				recordId;
	private String				deviceId;
	private String				eno;
	private String				stationId;
	private String				name;
	private String				deviceType;
	private String				deviceModel;
	private String				status;
	private String				states;
	private String				properties;
	private String				commands;
	private String				url;
	private Integer				intervals;
	private Integer				priority;
    
    public DeviceInfo() {
	}	

    public DeviceInfo(String recordId, String status) {
        this.recordId = recordId;
        this.status = status;
    }
	public String getRecordId() {
        return recordId;
    }
    public void setRecordId(String recordId) {
        this.recordId = recordId == null ? null : recordId.trim();
    }
    public String getDeviceId() {
        return deviceId;
    }
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }
    public String getEno() {
        return eno;
    }
    public void setEno(String eno) {
        this.eno = eno == null ? null : eno.trim();
    }
    public String getStationId() {
        return stationId;
    }
    public void setStationId(String stationId) {
        this.stationId = stationId == null ? null : stationId.trim();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
    public String getDeviceType() {
        return deviceType;
    }
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType == null ? null : deviceType.trim();
    }
    public String getDeviceModel() {
        return deviceModel;
    }
    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel == null ? null : deviceModel.trim();
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
    public String getStates() {
        return states;
    }
    public void setStates(String states) {
        this.states = states == null ? null : states.trim();
    }
    public String getProperties() {
        return properties;
    }
    public void setProperties(String properties) {
        this.properties = properties == null ? null : properties.trim();
    }
    public String getCommands() {
        return commands;
    }
    public void setCommands(String commands) {
        this.commands = commands == null ? null : commands.trim();
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
    public Integer getIntervals() {
        return intervals;
    }
    public void setIntervals(Integer intervals) {
        this.intervals = intervals;
    }
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
}