package com.dao;

import com.dao.entity.SignupEntity;

public interface SignupDao {

	SignupEntity authUser(String username, String password);

	int signup(String username, String password, String email, String name, String salutation);

}
