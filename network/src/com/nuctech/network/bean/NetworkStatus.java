/*
 * @(#)WsStatus.java 2018-8-8
 *
 * Copyright (c) 2010 by rayootech.com. All rights reserved.
 */
package com.nuctech.network.bean;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;

/**<p>工作状态返回封装类<br/>
 * @className WsStatus.java<br/>
 * @packageName com.nuctech.device.ws.bean.vo<br/>
 * @date 2018-8-8 上午10:24:35<br/>
 * </p>
 * 
 * @author Li Lu
 * @since 2018-8-8
 * @version v1.0.0
 */
@JsonInclude(Include.NON_NULL)
public class NetworkStatus implements Serializable{
	private static final long	serialVersionUID	= 6651478044991169531L;
	
	private String Status;

	@JsonProperty("Status")
	public String getStatus() {
		return Status;
	}
	@JsonProperty("Status")
	public void setStatus(String status) {
		Status = status;
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
    /**
     * <p>通用命令nport状态枚举<br/>
     * @className NPortStatus.java<br/>
     * @packageName com.nuctech.network.bean<br/>
     * @date 2019-4-2 上午9:27:29<br/>
     * </p>
     * 
     * @author Guo Yu Ting
     * @since 2019-4-2
     * @version v1.0.0
     */
    public enum NPortEnum {
        // 初始化为0 ，正常为1， 断开为2，故障为3,
        init("0","normal"), normal("1","normal"), disconnect("2","error"), error("3","error");
        
        private String status;
        private String nportstatus;
        
        NPortEnum(String nportstatus,String status) {
            this.nportstatus = nportstatus;
            this.status = status;
        }
        
        private static Map<String, NPortEnum> map = Maps.newHashMap();
        
        static {
            for (NPortEnum u : NPortEnum.values()) {
                map.put(u.getNportstatus(), u);
            }
        }
        
        public static NPortEnum fromOf(String nportstatus) {
            return map.get(nportstatus);
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getNportstatus() {
            return nportstatus;
        }

        public void setNportstatus(String nportstatus) {
            this.nportstatus = nportstatus;
        }
        
    }
	
}
