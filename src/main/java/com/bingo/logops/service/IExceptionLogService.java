package com.bingo.logops.service;

import com.bingo.logops.entity.ExceptionLogModel;

public interface IExceptionLogService {
    void saveExceptionLog(ExceptionLogModel model);
}
