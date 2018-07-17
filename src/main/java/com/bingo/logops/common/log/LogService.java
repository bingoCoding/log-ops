package com.bingo.logops.common.log;

import com.bingo.logops.utils.StringUtil;

public class LogService {

    /** 日志处理器 */
    private static AbstractLogHandler logHandler;

    /**
     * 获取日志处理器
     */
    private static AbstractLogHandler getLogHandler() {
        if (logHandler == null) {
            synchronized (LogService.class) {
                if (logHandler == null) {
                    initLogHandler();
                }
            }
        }
        return logHandler;
    }

    /**
     * 初始化日志处理器
     */
    private static void initLogHandler() {
        // 访问日志处理
        logHandler = new AccessLogHandler();

        // 异常日志处理
        AbstractLogHandler exceptionLogHandler = new ExceptionLogHandler();
        logHandler.setNextHandler(exceptionLogHandler);

    }

    /**
     * 保存日志
     *
     * @param topic
     * @param value
     */
    public static boolean saveLog(String topic, Object value) {

        // 参数检测
        if (StringUtil.isEmpty(topic, value)) {
            return false;
        }

        return getLogHandler().saveLog(topic, value);
    }
}
