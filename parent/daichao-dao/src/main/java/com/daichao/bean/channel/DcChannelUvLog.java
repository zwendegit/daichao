package com.daichao.bean.channel;

import java.util.Date;

public class DcChannelUvLog {
    private Integer id;

    private String channelCode;

    private String h5DeviceId;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode == null ? null : channelCode.trim();
    }

    public String getH5DeviceId() {
        return h5DeviceId;
    }

    public void setH5DeviceId(String h5DeviceId) {
        this.h5DeviceId = h5DeviceId == null ? null : h5DeviceId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}