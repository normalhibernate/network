/*
 * @(#)WsMethod.java 2018-8-9
 *
 * Copyright (c) 2010 by rayootech.com. All rights reserved.
 */
package com.nuctech.network.bean;

/**<p>设备底层WebService接口方法枚举类<br/>
 * @className WsMethod.java<br/>
 * @packageName com.nuctech.device.ws.enums<br/>
 * @date 2018-8-9 上午8:59:24<br/>
 * </p>
 * 
 * @author Li Lu
 * @since 2018-8-9
 * @version v1.0.0
 */
public enum WsMethod {
	/** 获取设备状态查询周期 **/
	GetDeviceStatusCircle,
	/** 获取设备工作状态 **/
	@Deprecated
	GetDeviceWorkStatus,
	/** 获取历史数据 **/
	GetHistoryData,
	/** 获取控制参数 **/
	GetParams,
	/** 获取实时数据 **/
	GetRealData,
	/** 获取状态数据 **/
	GetStatus,
	/** 设置设备状态查询周期 **/
	SetDeviceStatusCircle,
	/** 设置控制参数 **/
	SetParams,
	/** 设置显示文本命令 **/
	SetString,
	/** 控制采样命令 **/
	Ctrl,
	/** n42文件下载命令 **/
	DownLoadFile,
	/** 威视属性配置/超大流量气溶胶采集与分析系统通讯协议-配置类 **/
	config,
	/** 威视设备状态 **/
	status,
	/** 超大流量气溶胶采集与分析系统通讯协议-控制类 **/
	control,
	/** 超大流量气溶胶采集与分析系统通讯协议-UPS状态下发指令 **/
	powertype,
	/** 超大流量气溶胶采集与分析系统通讯协议-查询类 **/
	query,
	/** 超大流量气溶胶采集与分析系统通讯协议-状态类 **/
	state,
	/** 超大流量气溶胶采集与分析系统通讯协议-查询采样结果 **/
	sampleRecord, 
	/** 超大流量气溶胶采集与分析系统通讯协议-日志 **/
	log,
	/** 超大流量气溶胶采集与分析系统通讯协议-系统维护命令 **/
	DeviceMaintain,
	/** 超大流量气溶胶采集与分析系统通讯协议-系统维护参数 **/
	DeviceMaintainSettings,
	/** 工控机重启接口**/
	RebootComputer,
	;
}
