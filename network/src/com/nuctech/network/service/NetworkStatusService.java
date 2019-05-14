/*
 * @(#)NetworkStatusService.java 2019-4-15
 *
 * Copyright (c) 2010 by rayootech.com. All rights reserved.
 */
package com.nuctech.network.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuctech.network.mapper.NetworkRecordMapper;
import com.nuctech.network.model.NetworkRecord;

/**<p><br/>
 * @className NetworkStatusService.java<br/>
 * @packageName com.nuctech.network.service<br/>
 * @date 2019-4-15 下午1:49:14<br/>
 * </p>
 * 
 * @author Guo Yu Ting
 * @since 2019-4-15
 * @version v1.0.0
 */
@Service
public class NetworkStatusService {

//    Integer totalCount;
    Integer begin;
    Integer pageSize = 7;
    Integer totalPage;
	@Autowired
	private NetworkRecordMapper		networkRecordMapper;
	
	public List<NetworkRecord> selectLastGroupRecord() {
		return networkRecordMapper.selectLastGroupRecord();
	}
	public NetworkRecord selectLastRecord() {
	    return networkRecordMapper.selectLastRecord();
	}
	
	public int selectCount(){
	    return networkRecordMapper.selectCount();
	}
      
	public List<NetworkRecord> selectByPage(Integer pageIndex,Integer totalPage){
        if (pageIndex > totalPage) {
            return null;
        } else {
                begin = (pageIndex-1) * pageSize;
        }
        return networkRecordMapper.selectByPage(begin, pageSize);
	}
	
}
