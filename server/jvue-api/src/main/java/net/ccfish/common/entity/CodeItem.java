/*
 * Copyright © 2013-2019 BaoLaiTong, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.common.entity;

/**
 * 
 * @author 袁贵
 * @version 1.0
 * @since 1.0
 */
public class CodeItem<T> {

    private T code;
    private String name;

    /**
     * @return the code
     */
    public T getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(T code) {
        this.code = code;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
