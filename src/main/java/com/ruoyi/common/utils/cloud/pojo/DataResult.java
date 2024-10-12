package com.ruoyi.common.utils.cloud.pojo;

import com.ruoyi.common.utils.cloud.util.MessageUtil;
import org.springframework.util.StringUtils;

/**
 * @describe 格式化返回结果
 * @author shenkai
 * @date 2022/10/13
 */
public class DataResult {

    private Exception exception;

    private boolean success;

    private Object data;

    private String message;

    private String errorCode;

    public static DataResult success() {
        return success("");
    }

    public static DataResult success(Object data) {
        DataResult result = new DataResult();
        result.setSuccess(true);
        result.setData(data);
        result.setErrorCode(ErrorCode.NONE);
        return result;
    }

    public static DataResult fail(String msg) {
        DataResult result = new DataResult();
        result.setSuccess(false);
        result.setMessage(msg);
        result.setErrorCode(ErrorCode.BUSINESS);
        return result;
    }

    public static DataResult error(String msg) {
        DataResult result = new DataResult();
        result.setSuccess(false);
        result.setMessage(msg);
        result.setErrorCode(ErrorCode.EXCEPTION);
        return result;
    }

    public static DataResult error(Exception e) {
        String message = e.getMessage();
        if(StringUtils.hasText(message)) {
            return DataResult.error(message);
        } else {
            return DataResult.error(e.getStackTrace()[0].toString());
        }
    }

    public static DataResult localeError(String code, String... args) {
        String message = MessageUtil.get(code, args);
        return error(message);
    }

    public static DataResult noLander() {
        DataResult result = new DataResult();
        result.setSuccess(false);
        result.setErrorCode(ErrorCode.NOTLOGIN);
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
