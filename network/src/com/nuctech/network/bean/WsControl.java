/*
 * @(#)WsControl.java 2018-8-8
 *
 * Copyright (c) 2010 by rayootech.com. All rights reserved.
 */
package com.nuctech.network.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**<p>控制参数实体<br/>
 * @className WsControl.java<br/>
 * @packageName com.nuctech.device.ws.bean.vo<br/>
 * @date 2018-8-8 上午9:34:41<br/>
 * </p>
 * 
 * @author Li Lu
 * @since 2018-8-8
 * @version v1.0.0
 */
@JsonInclude(Include.NON_NULL)
public class WsControl implements Serializable{
	private static final long	serialVersionUID	= 6328813063715326185L;
	//高压电离室
	private String LowThreshold;
	private String HighThreshold;
	//高压电离室/自动气象站/空调控制器YDLMAC
	private String CTime;
	//超大流量气溶胶采样器
	private String WorkFlow;		//采样流量
	private String WorkFlowLowValue;//流量报警值
	private String RunMode;			//设定运行模式, 定量采样 0/定时采样 1
	private String SamplingLength;	//采样时长
	private String SamplingVolume;	//采样体积
	
	//直流电源控制器——ZLAN6002
	private String DO1;	//直流-高压电离室/交流-设备接口模块 
	private String DO2;	//直流-碘化钠谱仪/交流-通讯传输模块及VPN
	private String DO3; //直流-自动气象站/交流-硬盘录相机、球机、半球机、电气柜风扇
	private String DO4; //直流-声光报警器
	
	//空调控制器YDLMAC
	private String HotTempValue;	//自动控温制冷参数
	private String ColdTempValue;	//自动控温制热参数
	private String HumiValue;		//自动控湿参数
	
	//UPS
	private String Shutdown; // 关机命令:1-关机
	
	public WsControl() {
	}
	
	
	@JsonProperty("LowThreshold")
	public String getLowThreshold() {
		return LowThreshold;
	}
	@JsonProperty("LowThreshold")
	public void setLowThreshold(String lowThreshold) {
		LowThreshold = lowThreshold;
	}
	@JsonProperty("HighThreshold")
	public String getHighThreshold() {
		return HighThreshold;
	}
	@JsonProperty("HighThreshold")
	public void setHighThreshold(String highThreshold) {
		HighThreshold = highThreshold;
	}
	@JsonProperty("CTime")
	public String getCTime() {
		return CTime;
	}
	@JsonProperty("CTime")
	public void setCTime(String cTime) {
		CTime = cTime;
	}
	@JsonProperty("WorkFlow")
	public String getWorkFlow() {
		return WorkFlow;
	}
	@JsonProperty("WorkFlow")
	public void setWorkFlow(String workFlow) {
		WorkFlow = workFlow;
	}
	@JsonProperty("WorkFlow-LowValue")
	public String getWorkFlowLowValue() {
		return WorkFlowLowValue;
	}
	@JsonProperty("WorkFlow-LowValue")
	public void setWorkFlowLowValue(String workFlowLowValue) {
		WorkFlowLowValue = workFlowLowValue;
	}
	@JsonProperty("RunMode")
	public String getRunMode() {
		return RunMode;
	}
	@JsonProperty("RunMode")
	public void setRunMode(String runMode) {
		RunMode = runMode;
	}
	@JsonProperty("SamplingLength")
	public String getSamplingLength() {
		return SamplingLength;
	}
	@JsonProperty("SamplingLength")
	public void setSamplingLength(String samplingLength) {
		SamplingLength = samplingLength;
	}
	@JsonProperty("SamplingVolume")
	public String getSamplingVolume() {
		return SamplingVolume;
	}
	@JsonProperty("SamplingVolume")
	public void setSamplingVolume(String samplingVolume) {
		SamplingVolume = samplingVolume;
	}
	@JsonProperty("DO1")
	public String getDO1() {
		return DO1;
	}
	@JsonProperty("DO1")
	public void setDO1(String dO1) {
		DO1 = dO1;
	}
	@JsonProperty("DO2")
	public String getDO2() {
		return DO2;
	}
	@JsonProperty("DO2")
	public void setDO2(String dO2) {
		DO2 = dO2;
	}
	@JsonProperty("DO3")
	public String getDO3() {
		return DO3;
	}
	@JsonProperty("DO3")
	public void setDO3(String dO3) {
		DO3 = dO3;
	}
	@JsonProperty("DO4")
	public String getDO4() {
		return DO4;
	}
	@JsonProperty("DO4")
	public void setDO4(String dO4) {
		DO4 = dO4;
	}
	@JsonProperty("HotTempValue")
	public String getHotTempValue() {
		return HotTempValue;
	}
	@JsonProperty("HotTempValue")
	public void setHotTempValue(String hotTempValue) {
		HotTempValue = hotTempValue;
	}
	@JsonProperty("ColdTempValue")
	public String getColdTempValue() {
		return ColdTempValue;
	}
	@JsonProperty("ColdTempValue")
	public void setColdTempValue(String coldTempValue) {
		ColdTempValue = coldTempValue;
	}
	@JsonProperty("HumiValue")
	public String getHumiValue() {
		return HumiValue;
	}
	@JsonProperty("HumiValue")
	public void setHumiValue(String humiValue) {
		HumiValue = humiValue;
	}
	@JsonProperty("Shutdown")
	public String getShutdown() {
		return Shutdown;
	}
	@JsonProperty("Shutdown")
	public void setShutdown(String shutdown) {
		Shutdown = shutdown;
	}
	
}
