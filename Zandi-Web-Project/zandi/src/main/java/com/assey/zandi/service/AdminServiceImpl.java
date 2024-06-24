package com.assey.zandi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.assey.zandi.project.ProjectVO;
import com.assey.zandi.mapper.AdminMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class AdminServiceImpl implements AdminService {
    private static final Logger log = LogManager.getLogger(AdminServiceImpl.class);

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void projRegi(ProjectVO proj) {
        log.info("(service)addProject........");
        adminMapper.projRegi(proj);
    }
}