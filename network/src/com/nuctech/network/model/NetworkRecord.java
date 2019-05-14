package com.nuctech.network.model;

import java.util.UUID;

import org.joda.time.DateTime;

public class NetworkRecord {
    private String  recordId;
    private String  groupId;
    private String  stationId;
    private String  state;
    private Integer counter;
    private Integer restartCounter;
    private Integer restartFlag;
    private Long    at;
    private String  content;
    public NetworkRecord() {

    }
    public NetworkRecord(String stationId) {
        this.recordId = randomUUID();
        this.groupId = this.recordId;
        this.stationId = stationId;
        this.state = "0,0,0";
        this.counter = 0;
        this.restartCounter = 0;
        this.restartFlag = 0;
        this.at = new DateTime().getMillis();
    }
    public NetworkRecord rebuildPK(){
        this.recordId = randomUUID();
        this.at = new DateTime().getMillis();
        return this;
    }
    public String getRecordId() {
        return recordId;
    }
    public void setRecordId(String recordId) {
        this.recordId = recordId == null ? null : recordId.trim();
    }
    public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId == null ? null : groupId.trim();
	}
	public String getStationId() {
        return stationId;
    }
    public void setStationId(String stationId) {
        this.stationId = stationId == null ? null : stationId.trim();
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
    public Integer getCounter() {
        return counter;
    }
    public void setCounter(Integer counter) {
        this.counter = counter;
    }
    public Integer getRestartCounter() {
        return restartCounter;
    }
    public void setRestartCounter(Integer restartCounter) {
        this.restartCounter = restartCounter;
    }
    public Long getAt() {
        return at;
    }
    public void setAt(Long at) {
        this.at = at;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Integer getRestartFlag() {
		return restartFlag;
	}
	public void setRestartFlag(Integer restartFlag) {
		this.restartFlag = restartFlag;
	}
	/**
     * <p>计数器累加<br/>
     * @title counterAddOne<br/>
     * @date 2019-4-3 下午5:32:33<br/>
     * @author Guo Yu Ting<br/>
     * @since 2019-4-3
     * @version v1.0.0
     * </p>
     * @return
     */
    public Integer counterAdd() {
        this.counter += 1;
        return this.counter;
    }
    /**
     * <p>计数器开始<br/>
     * @title counterStart<br/>
     * @date 2019-4-3 下午5:32:23<br/>
     * @author Guo Yu Ting<br/>
     * @since 2019-4-3
     * @version v1.0.0
     * </p>
     */
    public void counterStart() {
        this.counter = 1;
    }
    /**
     * 计数器重置<p><br/>
     * @title counterReset<br/>
     * @date 2019-4-3 下午5:32:42<br/>
     * @author Guo Yu Ting<br/>
     * @since 2019-4-3
     * @version v1.0.0
     * </p>
     */
    public void counterReset() {
        this.counter = 0;
    }
    /**
     * <p><br/>
     * @title restartCounterReset<br/>
     * @date 2019-4-4 上午10:00:58<br/>
     * @author Guo Yu Ting<br/>
     * @since 2019-4-4
     * @version v1.0.0
     * </p>
     */
    public void restartCounterReset(){
        this.restartCounter = 0;
    }
    public Integer restartCounterAdd() {
        this.restartCounter += 1;
        return this.restartCounter;
    }
    public static String randomUUID() {
		synchronized (NetworkRecord.class) {
			return UUID.randomUUID().toString().replaceAll("-", "");
		}
	}
}