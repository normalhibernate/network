/*
 * @(#)NetworkStatusAdaptor.java 2019-4-4
 *
 * Copyright (c) 2010 by rayootech.com. All rights reserved.
 */
package com.nuctech.network.adptor;

import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.google.common.collect.Lists;
import com.nuctech.network.bean.NetworkReq;
import com.nuctech.network.bean.NetworkResp;
import com.nuctech.network.bean.WsControl;
import com.nuctech.network.bean.WsMethod;


/**<p><br/>
 * @className NetworkStatusAdaptor.java<br/>
 * @packageName com.nuctech.network.adptor<br/>
 * @date 2019-4-4 下午4:52:26<br/>
 * </p>
 * 
 * @author Guo Yu Ting
 * @since 2019-4-4
 * @version v1.0.0
 */
public class NetworkStatusAdaptor {
    private final Logger        logger               = LoggerFactory.getLogger(getClass());
    // private final static String CMD_QUERY = "query";
    private final static String CMD_SET              = "set";
    private final static String SUB_CMD1              = "params";
    private final static String SUB_CMD2             = "reboot";
    private final static String PAIR_KEY             = "strJson";
    private final static String REMOTEWS_NAMESPACE_3 = "http://127.0.0.1/";
    private final static String DEVICE_URL1          = "http://127.0.0.1:8080/PCTRL02.asmx";
    private final static String DEVICE_URL2          = "http://127.0.0.1:8080/GeneralCmd.asmx";
    public NetworkResp<?> controlParamSetterVPN(WsControl control,String deviceId) {
        NetworkResp<?> resp = null;
        try {
            String params = new NetworkReq<List<WsControl>>(CMD_SET, SUB_CMD1, deviceId).setParameter(Lists.newArrayList(control)).toJSON();
            logger.info("下发交流电源控制器设备参数：{}", params);
            String result = this.invokeRemoteWs(DEVICE_URL1, WsMethod.SetParams.name(), params,0, REMOTEWS_NAMESPACE_3);
            logger.info("下发交流电源控制器设备结果：{}", result);
            if (StringUtils.isNotBlank(result)) {
                resp = NetworkStatusAdaptor.fromJSON(result, NetworkResp.class);
            }
        } catch (Exception e) {
            logger.error("下发交流电源控制器设备参数失败：{}", e.getMessage());
        }
        return resp;
    }
    @SuppressWarnings("rawtypes")
    public NetworkResp controlParamSetterComputer(String deviceId) {
        NetworkResp<?> resp = null;
        try {
            String params = new NetworkReq(CMD_SET, SUB_CMD2, deviceId).toJSON();
            logger.info("下发重启工控机命令：{}", params);
            String result = this.invokeRemoteWs(DEVICE_URL2, WsMethod.RebootComputer.name(), params,0, REMOTEWS_NAMESPACE_3);
            logger.info("下发重启工控机命令结果：{}", result);
            if (StringUtils.isNotBlank(result)) {
                resp = NetworkStatusAdaptor.fromJSON(result, NetworkResp.class);
            }
        } catch (Exception e) {
            logger.error("下发重启工控机命令失败：{}", e.getMessage());
        }
        return resp;
        

    }
    /**
     * <p>发送WebSocket请求<br/>
     * @title invokeRemoteWs<br/>
     * @date 2019-4-2 上午9:42:11<br/>
     * @author Guo Yu Ting<br/>
     * @since 2019-4-2
     * @version v1.0.0
     * </p>
     * @param url
     * @param method
     * @param param
     * @param timeoutOfSecond
     * @param namespace
     * @return
     */
    protected String invokeRemoteWs(String url, String method, String param, int timeoutOfSecond, String namespace) {
        String result = null;

        try {
            // Action路径
            String actionUri = method;
            // 构建调用对象
            Call call = (Call) new Service().createCall();
            call.setTargetEndpointAddress(new URL(url));
            call.setUseSOAPAction(true);
            // 设置超时时间
            call.setTimeout(timeoutOfSecond);
            // Action URI  
            call.setSOAPActionURI(namespace + actionUri);
            // 设置调用方法名  
            call.setOperationName(new QName(namespace, method));
            // 设置请求参数及类型
            call.addParameter(new QName(namespace, PAIR_KEY), XMLType.XSD_STRING, ParameterMode.IN);
            call.setReturnType(XMLType.XSD_STRING);// 设置结果返回类型

            // 方法执行后的返回值  
            result = (String) call.invoke(new Object[] { param });
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }

        return result;
    }
    
    
    static com.fasterxml.jackson.databind.ObjectMapper mapper = null;
    static {
        mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }
    public static <T> T fromJSON(String json, Class<T> valueType) {
        try {
            return mapper.readValue(json, valueType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
