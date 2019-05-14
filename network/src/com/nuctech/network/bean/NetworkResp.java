/*
 * @(#)WsResp.java 2018-8-7
 *
 * Copyright (c) 2010 by rayootech.com. All rights reserved.
 */
package com.nuctech.network.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**<p>响应基础实体类<br/>
 * @className WsResp.java<br/>
 * @packageName com.nuctech.device.ws.bean<br/>
 * @date 2018-8-7 下午2:11:43<br/>
 * </p>
 * 
 * @author Li Lu
 * @since 2018-8-7
 * @version v1.0.0
 */
@JsonInclude(Include.NON_NULL)
public class NetworkResp<T> implements Serializable{
	private static final long	serialVersionUID	= 2146433077912263896L;
	
	private String				ErrCode;									// 错误代码:如果返回0表示没有错误
	private String				ErrMsg;										// 错误消息:如果返回ok表示成功，否则返回出错原因
	private String				total;										// 记录总行数
	private T					Lists;										// 记录列表，跟具体设备有关
	private String              Content;									// xml数据
	
	private String              totalItems;
	private T             		records;
	private T             		data;
	
	@JsonProperty("ErrCode")
	public String getErrCode() {
		return ErrCode;
	}
	@JsonProperty("ErrCode")
	public void setErrCode(String errCode) {
		ErrCode = errCode;
	}
	@JsonProperty("ErrMsg")
	public String getErrMsg() {
		return ErrMsg;
	}
	@JsonProperty("ErrMsg")
	public void setErrMsg(String errMsg) {
		ErrMsg = errMsg;
	}
	@JsonProperty("total")
	public String getTotal() {
		return total;
	}
	@JsonProperty("total")
	public void setTotal(String total) {
		this.total = total;
	}
	@JsonProperty("Lists")
	public T getLists() {
		return Lists;
	}
	@JsonProperty("Lists")
	public void setLists(T lists) {
		Lists = lists;
	}
	@JsonProperty("Content")
	public String getContent() {
		return Content;
	}
	@JsonProperty("Content")
	public void setContent(String content) {
		Content = content;
	}
	@JsonProperty("totalItems")
	public String getTotalItems() {
		return totalItems;
	}
	@JsonProperty("totalItems")
	public void setTotalItems(String totalItems) {
		this.totalItems = totalItems;
	}
	@JsonProperty("records")
	public T getRecords() {
		return records;
	}
	@JsonProperty("records")
	public void setRecords(T records) {
		this.records = records;
	}
	@JsonProperty("data")
	public T getData() {
		return data;
	}
	@JsonProperty("data")
	public void setData(T data) {
		this.data = data;
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
