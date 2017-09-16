package com.cjw.demo.doc.address.entity;

import java.io.Serializable;


/**
 * DUBBO服务结果类
 *
 * @author 709166
 *
 * @param <T>
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String requestId;

    /**
     * 操作是否成功
     */
    private boolean success = false;
    /**
     * 业务编码
     */
    private Integer business;
    /**
     * 错误提示码
     */
    private String errorCode;
    /**
     * 错误提示信息
     */
    private String errorMessage;
    /**
     * 服务器時間
     */
    private String date;
    /**
     * 服务器版本
     */
    private String version;

    /**
     * 结果对象
     */
    private T obj;

    public Result() {
    }

    public Result(boolean success) {
        this.success = success;
    }

    /**
     * @param requestId
     * @param success
     */
    public Result(String requestId, boolean success) {
        super();
        this.requestId = requestId;
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getBusiness() {
        return business;
    }

    public void setBusiness(Integer business) {
        this.business = business;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        setSuccess(false);

        if (errorCode != null && !"".equals(errorCode)) {
        }
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 服务器版本号
     * @return
     */
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
