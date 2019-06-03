package com.daichao.admin.service;

import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.user.DcUser;

public interface DcUserService {
  
	ResultOutput saveUser(DcUser user);
}
