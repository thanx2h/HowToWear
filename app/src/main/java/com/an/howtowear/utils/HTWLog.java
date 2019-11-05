package com.an.howtowear.utils;

public class HTWLog {

    private String HTW_PREFIX = "HTW";
    private HTWLog htwLog;

    private HTWLog() {
    }

    public synchronized HTWLog getInstance() {
        if (htwLog == null) {
            htwLog = new HTWLog();
        }

        return htwLog;
    }

    private String printLog(String message) {
        String fullClassName = Thread.currentThread().getStackTrace()[4].getClassName();
        String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
        String methodName = Thread.currentThread().getStackTrace()[4].getMethodName();

        StringBuilder sb = new StringBuilder();
        sb.append("[" + className + "]");
        sb.append("[" + methodName + "] : ");
        sb.append(message);

        return sb.toString();
    }

    private String getTag() {
        return HTW_PREFIX + DeviceInfoUtil.getInstance().getApplicationVersion();
    }


}
