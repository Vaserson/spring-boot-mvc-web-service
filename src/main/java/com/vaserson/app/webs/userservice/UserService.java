package com.vaserson.app.webs.userservice;

import com.vaserson.app.webs.ui.model.request.UserDetailsRequestModel;
import com.vaserson.app.webs.ui.model.response.UserResp;

public interface UserService {
	UserResp createUser(UserDetailsRequestModel userDetails);
}
