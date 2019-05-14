/*
 * @(#)Tabs.java 2018-7-11
 *
 * Copyright (c) 2010 by rayootech.com. All rights reserved.
 */
package com.nuctech.network.bean;

import java.util.HashMap;
import java.util.Map;

/**<p>页签枚举类<br/>
 * @className Tabs.java<br/>
 * @packageName com.nuctech.station.bean<br/>
 * @date 2018-7-11 下午3:40:52<br/>
 * </p>
 * 
 * @author Zhao Xian Long
 * @since 2018-7-11
 * @version v1.0.0
 */
public enum Tabs {
	/** 辐射剂量率 */
	DoseRate(
		"doseRate", 
		"辐射剂量率", 
		"biz_hpicrecord,biz_naidoseraterecord",
		"biz_hpicrecord,biz_naidoseraterecord"
	),
	/** 高压电离室辐射剂量率 */
	HpicDoseRate(
		"hpicDoseRate", 
		"高压电离室辐射剂量率", 
		"biz_hpicrecord",
		"biz_hpicrecord"
	),
			
	/**碘化钠谱仪数据*/
	NaiSpere(
		"naiSpere", 
		"碘化钠谱仪数据", 
		"biz_naisperecord", 
		""
	),
	
	/**超大流量气溶胶采样*/
	AutoELAA(
		"autoELAA", 
		"超大流量气溶胶采样数据", 
		"biz_autoela", 
		"biz_autoela"
	),
	
	/**超大流量气溶胶瞬时采样流量*/
	AutoELAARealtimeFlow(
		"autoELAARealtimeFlow", 
		"超大流量气溶胶瞬时采样流量", 
		"biz_autoela_realtime_flow", 
		"biz_autoela_realtime_flow"
	),
	
	/**超大流量气溶胶采样器*/
	ExtraLargeas(
		"extraLargeas", 
		"超大流量气溶胶采样器", 
		"biz_extralargeas", 
		"biz_extralargeas"
	),
	
	/**大流量气溶胶采样器*/
	Largeas(
		"largeas", 
		"大流量气溶胶采样器", 
		"biz_largeas", 
		"biz_largeas"
	),
	
	/**碘瞬时采流量*/
	IodineSampler(
		"iodineSampler", 
		"碘瞬时采样流量", 
		"biz_iodinesampler", 
		"biz_iodinesampler"
	),
	
	/**氚瞬时采流量*/
	TritiumSampler(
		"tritiumSampler", 
		"氚瞬时采样流量", 
		"biz_tritiumsampler", 
		"biz_tritiumsampler"
	),
	
	/**碳14瞬时采流量*/
	CarbonSampler(
		"carbonSampler", 
		"碳14瞬时采样流量", 
		"biz_carbonsampler", 
		"biz_carbonsampler"
	),
	
	/**自动气象站*/
	Ws(
		"ws", 
		"自动气象站", 
		"biz_wsrecord", 
		"biz_wsrecord"
	),
	
	/**自动气象站*/
	WsES(
		"wsES", 
		"自动气象站", 
		"biz_wsrecord", 
		"biz_wsrecord"
	),
	
	/**用电量(三相电表6) */
	ElectricMeter(
		"electricMeter",
		"用电量",
		"biz_electricmeter",
		"biz_electricMeter"
	),
	
	/**降雨次数(干湿沉降采样器) */
	NumberOfRains(
		"numberOfRains",
		"降雨次数",
		"biz_drywetdeposition",
		"biz_drywetdeposition"
	),
	;
	
	private String key;
	private String name;
	private String historyTable;
	private String statTable;
	
	private Tabs(String key, String name, String historyTable, String statTable) {
		this.key = key;
		this.name = name;
		this.historyTable = historyTable;
		this.statTable = statTable;
	}
	
	private static Map<String, Tabs> map = 
			new HashMap<String, Tabs>(Tabs.values().length);
	
	static {
		for (Tabs tab : Tabs.values()) {
			map.put(tab.getKey(), tab);
		}
	}
	
	public static Tabs fromOf(String key) {
		return map.get(key);
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHistoryTable() {
		return historyTable;
	}
	public void setHistoryTable(String historyTable) {
		this.historyTable = historyTable;
	}
	public String getStatTable() {
		return statTable;
	}
	public void setStatTable(String statTable) {
		this.statTable = statTable;
	}
}
