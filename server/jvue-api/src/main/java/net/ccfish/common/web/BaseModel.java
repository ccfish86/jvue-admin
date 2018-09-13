/*
 * Copyright © 2013-2016 BLT, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.common.web;

import java.io.Serializable;

/**
 * 各消息/服务间用通用Model
 * 
 * @author 袁贵
 * @version 1.0
 * @since 1.0
 */
public class BaseModel<T> implements Serializable {
    
    private static final long serialVersionUID = 4518971185913581227L;

    /** status code */
    private String error;
    
    private String message;

    /** return data */
    private T data;

    /**
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public BaseModel<T> setData(T data) {
        this.data = data;
        return this;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public static <T> BaseModel<T> ok(T data) {
        return new BaseModel<T>().setData(data);
    }

    public static <T> BaseModel<T> error(String error, String message) {
        BaseModel<T> baseModel = new BaseModel<>();
        baseModel.setError(error);
        baseModel.setMessage(message);
        return baseModel;
    }
    public static <T> BaseModel<T> error(String message) {
        BaseModel<T> baseModel = new BaseModel<>();
        baseModel.setError("1");
        baseModel.setMessage(message);
        return baseModel;
    }
}
