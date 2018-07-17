package com.bingo.logops.common.log;

public abstract class AbstractLogHandler implements ILogHandler {
    /** 处理器 */
    private ILogHandler nextHandler;

    /**
     * 保存日志
     *
     */
    public boolean saveLog(String topic, Object value) {

        if (nextHandler == null) {
            return false;
        }

        return nextHandler.saveLog(topic, value);
    }

    /**
     * 获取handler
     */
    public ILogHandler getNextHandler() {
        return this.nextHandler;
    }

    /**
     * 设置处理器
     *
     * @param nextHandler		处理器
     */
    public void setNextHandler(ILogHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
