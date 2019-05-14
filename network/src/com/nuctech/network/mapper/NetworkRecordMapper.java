package com.nuctech.network.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nuctech.network.model.NetworkRecord;
@Mapper
public interface NetworkRecordMapper {
    int deleteByPrimaryKey(String recordId);

    int insert(NetworkRecord record);

    int insertSelective(NetworkRecord record);

    NetworkRecord selectByPrimaryKey(String recordId);
    
    NetworkRecord selectLastRecord();
    
    List<NetworkRecord> selectLastGroupRecord();
    
    NetworkRecord selectLastByCounter(Integer c);
    
    int selectCount();
    
    List<NetworkRecord> selectByPage(@Param("begin")Integer begin, @Param("pageSize")Integer pageSize);

    int updateByPrimaryKeySelective(NetworkRecord record);

    int updateByPrimaryKey(NetworkRecord record);
}