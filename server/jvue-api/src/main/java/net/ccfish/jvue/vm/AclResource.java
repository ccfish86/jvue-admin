/*
 * Copyright © 2013-2019 BaoLaiTong, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.vm;

import java.io.Serializable;

/**
 * 资源定义
 * <br>
 * 由于采用了前后端分离，这里仅限接口
 * 
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
public class AclResource implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public enum Type {
        MODULE,
        METHOD
    }
    
    private Integer id;
    
    private String name;
    
    private String code;
    
    private Type type;
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
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
    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }
    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }
    /**
     * @return the type
     */
    public Type getType() {
        return type;
    }
    /**
     * @param type the type to set
     */
    public void setType(Type type) {
        this.type = type;
    }
    
}
