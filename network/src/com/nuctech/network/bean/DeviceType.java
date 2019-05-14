/*
 * @(#)DeviceType.java 2018-2-2
 *
 * Copyright (c) 2010 by rayootech.com. All rights reserved.
 */
package com.nuctech.network.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**<p>设备类型枚举类<br/>
 * @className DeviceType.java<br/>
 * @packageName com.nuctech.device.bean<br/>
 * @date 2018-2-2 上午9:33:09<br/>
 * </p>
 * 
 * @author Li Lu
 * @since 2018-2-2
 * @version v1.0.0
 */
public enum DeviceType {
	
	/** 碘采样器 **/
	IodineSamplerAdaptor(
		"IodineSamplerAdaptor",
		"碘采样器",
		new Tabs[]{Tabs.IodineSampler}, 
		new Tabs[]{Tabs.IodineSampler}
	),
	/** 氚采样器 **/
	TritiumSamplerAdaptor(
		"TritiumSamplerAdaptor",
		"氚采样器",
		new Tabs[]{Tabs.TritiumSampler}, 
		new Tabs[]{Tabs.TritiumSampler}
	),
	/** 自动气象站 **/
	WsAdaptor(
		"WsAdaptor",
		"自动气象站",
		new Tabs[]{Tabs.Ws}, 
		new Tabs[]{Tabs.Ws}
	),
	/** 干湿沉降采样器 **/
	DWDAdaptor(
		"DWDAdaptor",
		"干湿沉降采样器",
		new Tabs[]{Tabs.NumberOfRains}, 
		new Tabs[]{}
	),
	/** 碳采样器 **/
	CarbonSamplerAdaptor(
		"CarbonSamplerAdaptor",
		"碳14采样器",
		new Tabs[]{Tabs.CarbonSampler}, 
		new Tabs[]{Tabs.CarbonSampler}
	),
	/** 高压电离室 (访问设备接口Adaptor一样)**/
	HpicAdaptor(
		"HpicAdaptor", 
		"高压电离室", 
		new Tabs[]{Tabs.HpicDoseRate}, 
		new Tabs[]{Tabs.HpicDoseRate}
	),
	/** RSS高压电离室 (访问设备接口Adaptor一样) **/
	RSSHpicAdaptor(
		"RSSHpicAdaptor", 
		"高压电离室", 
		new Tabs[]{Tabs.HpicDoseRate}, 
		new Tabs[]{Tabs.HpicDoseRate}
	),
	/** ZJWK高压电离室 (ZJWK) **/
	ZJWKHpicAdaptor(
		"ZJWKHpicAdaptor", 
		"高压电离室", 
		new Tabs[]{Tabs.HpicDoseRate}, 
		new Tabs[]{Tabs.HpicDoseRate}
	),
	/** 固定碘化钠(DataLogger) **/
	NaIAdaptor(
		"NaIAdaptor",
		"碘化钠谱仪",
		new Tabs[]{Tabs.NaiSpere}, 
		new Tabs[]{}
	),
	/** 固定碘化钠(JMH-加迈华) **/
	NaIJMHAdaptor(
		"NaIJMHAdaptor",
		"碘化钠谱仪",
		new Tabs[]{Tabs.NaiSpere}, 
		new Tabs[]{}
	),
	/** 固定碘化钠(Nuctech) **/
	NaINuctechAdaptor(
		"NaINuctechAdaptor",
		"碘化钠谱仪",
		new Tabs[]{ Tabs.NaiSpere}, 
		new Tabs[]{}
	),
	/** UPS **/
	UpsAdaptor(
		"UpsAdaptor",
		"UPS",
		new Tabs[]{}, 
		new Tabs[]{}
	),
	/** 超大流量气溶胶采集与分析系统采样器 **/
	AutoELAAdaptor(
		"AutoELAAdaptor",
		"超大流量气溶胶采集与分析系统",
		new Tabs[]{Tabs.AutoELAA, Tabs.AutoELAARealtimeFlow}, 
		new Tabs[]{}
	),
	/** 超大流量气溶胶 **/
	ExtraLargeAsAdaptor(
		"ExtraLargeAsAdaptor",
		"超大流量气溶胶采样器",
		new Tabs[]{Tabs.ExtraLargeas}, 
		new Tabs[]{Tabs.ExtraLargeas}
	),
	/** 大流量气溶胶 **/
	LargeAsAdaptor(
		"LargeAsAdaptor",
		"大流量气溶胶采样器",
		new Tabs[]{Tabs.Largeas}, 
		new Tabs[]{Tabs.Largeas}
	),
	/** 站房状态=开关量采集器 **/
	D86Adaptor(
		"D86Adaptor",
		"站房状态",
		new Tabs[]{}, 
		new Tabs[]{}
	),
	/** 空调控制器 **/
	AirConditionerAdaptor(
		"AirConditionerAdaptor",
		"空调控制器",
		new Tabs[]{}, 
		new Tabs[]{}
	),
	/** LED **/
	LEDAdaptor(
		"LEDAdaptor", 
		"LED",
		new Tabs[]{}, 
		new Tabs[]{}
	),
	/** 中环温湿度 **/
	TJQXTRAdaptor(
		"TJQXTRAdaptor", 
		"中环温湿度",
		new Tabs[]{}, 
		new Tabs[]{}
	),
	/** 中环温电离室 **/
	TJQXHPICAdaptor(
		"TJQXHPICAdaptor", 
		"中环温电离室",
		new Tabs[]{}, 
		new Tabs[]{}
	),
	/** 中环电池 **/
	TJQXBATAdaptor(
		"TJQXBATAdaptor", 
		"中环电池",
		new Tabs[]{}, 
		new Tabs[]{}
	),
	/** 三相电表 (DTS2626)**/
	ElectricMeterAdaptor(
		"ElectricMeterAdaptor",
		"三相电表",
		new Tabs[]{Tabs.ElectricMeter}, 
		new Tabs[]{}
	),
	/** 太阳能充电器 (VS4548BN)**/
	SolarPowerAdaptor(
		"SolarPowerAdaptor",
		"太阳能充电器",
		new Tabs[]{}, 
		new Tabs[]{}
	),
	/** 交流电源控制器 (PCTRL02)**/
	ACControlAdaptor(
		"ACControlAdaptor",
		"交流电源控制器",
		new Tabs[]{}, 
		new Tabs[]{}
	),
	/** 直流电源控制器 (ZLAN6002)**/
	DCControlAdaptor(
		"DCControlAdaptor",
		"直流电源控制器",
		new Tabs[]{}, 
		new Tabs[]{}
	),
	/** 空调控制器 (YDLMAC)**/
	AirConditionerEXAdaptor(
		"AirConditionerEXAdaptor",
		"空调控制器",
		new Tabs[]{}, 
		new Tabs[]{}
	),
	/** 站房状态=开关量采集器 (SWD86)**/
	SWD86Adaptor(
		"SWD86Adaptor",
		"站房状态",
		new Tabs[]{}, 
		new Tabs[]{}
	),
	/** 自动气象站 **/
	WsESAdaptor(
		"WsESAdaptor",
		"自动气象站",
		new Tabs[]{Tabs.WsES}, 
		new Tabs[]{Tabs.WsES}
	),
	;
	
	private String key;
	private String showName;
	private Tabs[] historyTabs;
	private Tabs[] statTabs;
	
	private DeviceType(String key, String showName, Tabs[] historyTabs, Tabs[] statTabs) {
		this.key = key;
		this.showName = showName;
		this.historyTabs = historyTabs;
		this.statTabs = statTabs;
	}
	
	private static Map<String, DeviceType> map = 
			new HashMap<String, DeviceType>(DeviceType.values().length);
	private static List<DeviceType> list = 
			new ArrayList<DeviceType>(DeviceType.values().length);
	
	static {
		for (DeviceType type : DeviceType.values()) {
			map.put(type.key, type);
			list.add(type);
		}
	}
	
	public static DeviceType fromOf(String key) {
		return map.get(key);
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	public Tabs[] getHistoryTabs() {
		return historyTabs;
	}
	public void setHistoryTabs(Tabs[] historyTabs) {
		this.historyTabs = historyTabs;
	}
	public Tabs[] getStatTabs() {
		return statTabs;
	}
	public void setStatTabs(Tabs[] statTabs) {
		this.statTabs = statTabs;
	}
}
