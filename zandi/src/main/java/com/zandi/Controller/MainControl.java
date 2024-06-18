package com.zandi.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainControl {
    private static final Logger logger = LoggerFactory.getLogger(MainControl.class);

    @RequestMapping(value = "/MainPage", method = RequestMethod.GET)
    public void mainPageGET() {
        logger.info("메인 페이지 진입");
    }
}
