package com.daichao.service;

import com.daichao.bean.output.ResultOutput;

public interface DcAppVersionService {

    ResultOutput getVersion(String deviceType, String packageType);
    
    ResultOutput getVersionStatus(String deviceType, String packageType,String version);
}
