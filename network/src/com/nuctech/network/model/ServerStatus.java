package com.nuctech.network.model;

import java.io.Serializable;
/**
 * <p>上层服务器实体类<br/>
 * @className ServerStatus.java<br/>
 * @packageName com.nuctech.station.model<br/>
 * @date 2018-7-2 下午4:45:08<br/>
 * </p>
 * 
 * @author Li Lu
 * @since 2018-7-2
 * @version v1.0.0
 */
public class ServerStatus implements Serializable{
	private static final long	serialVersionUID	= -8590669750486491763L;
	
	public static final String SERVER_STATUS_ONLINE  = "已连接";
	public static final String SERVER_STATUS_OFFLINE = "未连接";
	
	private String				recordId;
	private String				stationId;
	private String				name;
	private String				type;
	private String				ip;
	private String				hostPort;
	private String				status;
	private Long				statusUpdateAt;
	private Integer				priority;
  
    public ServerStatus() {
	}
	public ServerStatus(String name, String ip, String status) {
		this.name = name;
		this.ip = ip;
		this.status = status;
	}


	public String getRecordId() {
        return recordId;
    }
    public void setRecordId(String recordId) {
        this.recordId = recordId == null ? null : recordId.trim();
    }
    public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }
    public String getHostPort() {
		return hostPort;
	}
	public void setHostPort(String hostPort) {
		this.hostPort = hostPort;
	}
	public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
        this.statusUpdateAt = System.currentTimeMillis();
    }
    public Integer getPriority() {
        return priority;
    }
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getStatusUpdateAt() {
		return statusUpdateAt;
	}
	public void setStatusUpdateAt(Long statusUpdateAt) {
		this.statusUpdateAt = statusUpdateAt;
	}
}