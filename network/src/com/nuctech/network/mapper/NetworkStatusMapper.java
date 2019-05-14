/*
 * @(#)CommunicationStatusMapper.java 2019-4-1
 *
 * Copyright (c) 2010 by rayootech.com. All rights reserved.
 */
package com.nuctech.network.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nuctech.network.model.DeviceInfo;
import com.nuctech.network.model.ServerStatus;

/**
 * <p>
 * <br/>
 * 
 * @className CommunicationStatusMapper.java<br/>
 * @packageName com.nuctech.network.mapper<br/>
 * @date 2019-4-1 上午10:39:57<br/>
 *       </p>
 * 
 * @author Guo Yu Ting
 * @since 2019-4-1
 * @version v1.0.0
 */
@Mapper
public interface NetworkStatusMapper {

    public void updateByPrimaryKeySelective(DeviceInfo deviceInfo);

    List<DeviceInfo> getDevicesByStationId(@Param("stationId")String stationId, @Param("selectAllFields")int selectAllFields);
    DeviceInfo getDeviceInfoByDeviceType(@Param("stationId")String stationId, @Param("deviceType")String deviceType, @Param("selectAllFields")int selectAllFields);
    
    
    List<ServerStatus> getCenterData(@Param("stationId")String stationId);
    
}
