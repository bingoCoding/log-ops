package com.bingo.logops.common.log;

import com.alibaba.fastjson.JSON;
import com.bingo.logops.common.constant.LogTopicConstant;
import com.bingo.logops.common.constant.OpsConstant;
import com.bingo.logops.service.impl.ExceptionLogService;
import com.bingo.logops.entity.ExceptionLogModel;

/**
 * 异常日志处理
 */
public class ExceptionLogHandler extends AbstractLogHandler{

    @Override
    public boolean saveLog(String topic, Object value) {
        if (topic.equals(LogTopicConstant.TOPIC_EXCEPTION_LOG)) {

            ExceptionLogModel model = null;
            try {
                model = JSON.parseObject(value.toString(), ExceptionLogModel.class);
                // 无值或消息收到的时间比系统时间晚10分钟，不做处理
                if (model == null || (System.currentTimeMillis() - model.getDate().getTime() > OpsConstant.MSG_FILTER_TIME)) {
                    return false;
                }

                // 保存
                getLogHandler(ExceptionLogService.class).saveExceptionLog(model);

            } catch (Exception e) {
                return false;
            }

            return true;
        } else {
            return super.saveLog(topic, value);
        }
    }
}
