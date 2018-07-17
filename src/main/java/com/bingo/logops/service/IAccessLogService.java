package com.bingo.logops.service;

import com.bingo.logops.Pojo.AccessLogVO;
import com.bingo.logops.Pojo.Pagination;
import com.bingo.logops.entity.AccessLogModel;

public interface IAccessLogService {

    public Pagination<AccessLogModel> getAccessLogList(AccessLogVO vo);
}
