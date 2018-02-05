/*
 * Copyright © 2013-2019 BaoLaiTong, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.rest.vm;

/**
 * 
 * @author 袁贵
 * @version 1.0
 * @since 1.0
 */
public class ReqLogin {

    private String username;
    private String password;
    private boolean remember;

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the remember
     */
    public boolean isRemember() {
        return remember;
    }

    /**
     * @param remember the remember to set
     */
    public void setRemember(boolean remember) {
        this.remember = remember;
    }

}
