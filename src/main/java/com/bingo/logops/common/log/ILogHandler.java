package com.bingo.logops.common.log;

import com.bingo.logops.utils.SpringContextHolder;

public interface ILogHandler {
    /**
     * 保存日志
     *
     * @param topic		日志Topic
     * @param value		日志内容
     */
    default boolean saveLog(String topic, Object value) {
        return false;
    }

    /**
     * 获取处理器
     */
    default <T> T getLogHandler(Class<T> clazz) {
        return SpringContextHolder.getBean(clazz);
    }
}
