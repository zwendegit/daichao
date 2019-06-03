package com.daichao.admin.service;

import java.util.List;

import com.daichao.admin.input.user.UserPageInput;
import com.daichao.bean.user.DcUser;

public interface UserService {

	List<DcUser> userList(UserPageInput input);
}
