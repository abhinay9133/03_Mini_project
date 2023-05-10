package com.abhi.service;

import com.abhi.binding.LoginForm;
import com.abhi.binding.RegistrationForm;

public interface UserService {

	String registerUser(RegistrationForm form);
	
	boolean loginUser(LoginForm form);
	
}
