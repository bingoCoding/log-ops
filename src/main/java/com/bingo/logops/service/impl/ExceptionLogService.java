package com.bingo.logops.service.impl;

import com.bingo.logops.dao.impl.ExceptionLogDao;
import com.bingo.logops.entity.ExceptionLogModel;
import com.bingo.logops.service.IExceptionLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ExceptionLogService implements IExceptionLogService{

    @Resource
    private ExceptionLogDao exceptionLogDao;

    public void saveExceptionLog(ExceptionLogModel model) {

    }
}
