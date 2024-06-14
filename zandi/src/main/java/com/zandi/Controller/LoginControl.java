package com.zandi.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/member")
public class LoginControl {

	private static final Logger logger = LoggerFactory.getLogger(LoginControl.class);
	
	//ȸ������ ������ �̵�
	@RequestMapping(value = "join", method = RequestMethod.GET)
	public void joinGET() {
		
		logger.info("ȸ������ ������ ����");
		
	}
	
	//�α��� ������ �̵�
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public void loginGET() {
		
		logger.info("�α��� ������ ����");
		
	}
	
}