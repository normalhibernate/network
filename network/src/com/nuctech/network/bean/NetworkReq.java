/*
 * @(#)WsReq.java 2018-8-7
 *
 * Copyright (c) 2010 by rayootech.com. All rights reserved.
 */
package com.nuctech.network.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**<p>请求基础实体类<br/>
 * @className WsReq.java<br/>
 * @packageName com.nuctech.device.ws.bean<br/>
 * @date 2018-8-7 下午2:08:13<br/>
 * </p>
 * 
 * @author Li Lu
 * @since 2018-8-7
 * @version v1.0.0
 */
@JsonInclude(Include.NON_NULL)
public class NetworkReq<T> implements Serializable{
	private static final long	serialVersionUID	= 8183395689583860941L;
	
	private String				Command;									// 命令字:见命令字列表
	private String				SubCommand;			 						// 子命令字:如果没有子命令字，则填0，见子命令字列表
	private String				DeviceID;									// 设备ID:如果填保留字串”11111111”，则表示针对所有设备
	private T					Params;										// 附带参数
	private T					Parameter;									// 附带参数
	
	public NetworkReq() {
	}
	public NetworkReq(String command) {
		Command = command;
		SubCommand = "0";
		DeviceID = "11111111";
	}
	public NetworkReq(String command, String subCommand) {
		Command = command;
		SubCommand = subCommand;
		DeviceID = "11111111";
	}
	public NetworkReq(String command, String subCommand, String deviceID) {
		Command = command;
		SubCommand = subCommand;
		DeviceID = deviceID;
	}
	
	@JsonProperty("Command")
	public String getCommand() {
		return Command;
	}
	@JsonProperty("Command")
	public void setCommand(String command) {
		Command = command;
	}
	@JsonProperty("SubCommand")
	public String getSubCommand() {
		return SubCommand;
	}
	@JsonProperty("SubCommand")
	public void setSubCommand(String subCommand) {
		SubCommand = subCommand;
	}
	@JsonProperty("DeviceID")
	public String getDeviceID() {
		return DeviceID;
	}
	@JsonProperty("DeviceID")
	public void setDeviceID(String deviceID) {
		DeviceID = deviceID;
	}
	@JsonProperty("Params")
	public T getParams() {
		return Params;
	}
	@JsonProperty("Params")
	public NetworkReq<T> setParams(T params) {
		Params = params;
		return this;
	}
	@JsonProperty("Parameter")
	public T getParameter() {
		return Parameter;
	}
	@JsonProperty("Parameter")
	public NetworkReq<T> setParameter(T parameter) {
		Parameter = parameter;
		return this;
	}
	/**
	 * <p>转换为JSON字符串<br/>
	 * @title toJSON<br/>
	 * @date 2018-2-2 下午1:54:08<br/>
	 * @author Li Lu<br/>
	 * @since 2018-2-2
	 * @version v1.0.0
	 * </p>
	 * @return
	 */
	public String toJSON() {

		try {
			return new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(this);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
