package net.ccfish.jvue.service;

/**
 * 用户登录接口
 * 
 * @author 袁贵
 * @version 1.0
 * @since 1.0
 */
public interface AuthService {

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 操作结果
     */
    String login(String username, String password);

    /**
     * 刷新密钥
     *
     * @param oldToken 原密钥
     * @return 新密钥
     */
    String refreshToken(String oldToken);

}
