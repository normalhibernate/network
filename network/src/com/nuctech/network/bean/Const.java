/*
 * @(#)Const.java 2017年1月14日
 *
 * Copyright (c) 2016 by emerican.cn All rights reserved.
 */
package com.nuctech.network.bean;

/**
 * <p>
 * 公共常量类<br/>
 * 
 * @className Const.java<br/>
 * @packageName com.nuctech.support<br/>
 * @date 2017年1月14日 下午12:36:26<br/>
 *       </p>
 * 
 * @author Li Lu
 * @since 2017年1月14日
 * @version v1.0.0
 */
public interface Const {
	/**
	 * 默认编码方式
	 */
	public final static String				Default_Encoding				= "UTF-8";
	/**
	 * 员工默认密码
	 */
	public final static String				Muser_Default_Password			= "123456";
	/**
	 * 空格字符串
	 */
	public static final String				STR_EMPTY						= " ";
	/**
	 * 男孩-默认头像
	 */
	public static final String				Default_HI_Boys					= "/static/adminlte/dist/img/user2-160x160.jpg";
	/**
	 * 女孩-默认头像
	 */
	public static final String				Default_HI_Girls				= "/static/adminlte/dist/img/user3-128x128.jpg";
	/**
	 * 否/NO/未选中
	 */
	public static final int					NO								= 0;
	/**
	 * 是/YES/选中
	 */
	public static final int					YES								= 1;
	/**
	 * 系统默认保留小数位, 默认保留两位
	 */
	public static final int					System_Default_Scale			= 2;
	/**
	 * 高值报警阈值
	 */
	public static final Double				Default_HighValueAlarm			= 1D;
	/**
	 * 累积谱图查询周期
	 */
	public static final int					Default_SpeInterval				= 300;
	/**
	 * 剂量率查询周期
	 */
	public static final int					Default_DoseRateInterval		= 60;
	/**
	 * 默认报警上传周期(秒)
	 */
	public static final int					Default_Alarm_Interval			= 30;
	/**
	 * 默认报警上传周期为延迟(秒)
	 */
	public static final int					Default_Alarm_Interval_Delay	= 5;
	/**
	 * 移动站点状态 normal-正常
	 */
	public static final String				Station_Status_Normal			= "normal";
	/**
	 * 移动站点状态 error-异常
	 */
	public static final String				Station_Status_Error			= "error";

	/**
	 * 定时任务_系统默认定时任务组
	 */
	public static final String				QZ_Group_Name_Default			= "scs_default_group";
	/**
	 * 定时任务_设备数据访问定时任务组
	 */
	public static final String				QZ_Group_Name_Ws_Adaptor		= "scs_ws_adaptor_group";
	/**
	 * 设备颜色数组
	 */
	public static final String[]			Device_Colors					= {"blue", "orange", "green", "cyan"};
	/**
	 * 无数据标致
	 */
	public static final String				No_Data_Flag					= "--";
	/**
	 * 查询条件全部标志
	 */
	public static final String				ALL_Flag						= "ALL";
	/**
	 * 无设备数据时提示信息
	 */
	public static final String				No_Device_Data_Tips				= "暂无数据";
	/**
	 * Controller错误提示语
	 */
	public static final String				Controller_Error_Tips			= "网络异常，请稍后再试！";
	/**
	 * 表示升序
	 */
	public static final String				WS_DEVICE_ORDER_ASC				= "0";
	/**
	 * 表示降序
	 */
	public static final String				WS_DEVICE_ORDER_DESC			= "1";
	/**
	 * 访问设备数据时正常响应码
	 */
	public static final String				ErrCode_OK						= "0";
	/**
	 * 访问设备数据时正常响应消息
	 */
	public static final String				ErrMsg_OK						= "ok";
	/**
	 * 超大流量气溶胶采集与分析系统日志文件扩展名
	 */
	public static final String				AutoELALogExt					= ".zip";
	/**
	 * 超大采样与分析系统_设备无法访问状态_全小写
	 */
	public static final String				Disconnected_L					= "disconnected";
	/**
	 * 超大采样与分析系统_设备无法访问状态_首字母大写
	 */
	public static final String				Disconnected_FU					= "Disconnected";
	/**
	 * 同步加密盐
	 */
	public static final String 				SyncSalt						= "5f21ae7dfe0444af92f9a4d3614909c8";
}
