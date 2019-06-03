package com.daichao.bean.app;

public class DcAppVersion {
    private Integer id;

    private Integer versioncode;

    private String versionname;

    private Integer forcecheck;

    private String devicetype;

    private String appid;

    private String packagetype;

    private String remark;

    private Integer status;
    private String downloadUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersioncode() {
        return versioncode;
    }

    public void setVersioncode(Integer versioncode) {
        this.versioncode = versioncode;
    }

    public String getVersionname() {
        return versionname;
    }

    public void setVersionname(String versionname) {
        this.versionname = versionname == null ? null : versionname.trim();
    }

    public Integer getForcecheck() {
        return forcecheck;
    }

    public void setForcecheck(Integer forcecheck) {
        this.forcecheck = forcecheck;
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype == null ? null : devicetype.trim();
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    public String getPackagetype() {
        return packagetype;
    }

    public void setPackagetype(String packagetype) {
        this.packagetype = packagetype == null ? null : packagetype.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
    
}