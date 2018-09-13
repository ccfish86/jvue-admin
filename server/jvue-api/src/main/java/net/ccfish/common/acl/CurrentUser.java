package net.ccfish.common.acl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于处理当前用户登录
 *
 * @author yuangui
 * @version 1.0
 * @since 2018-07-18
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface CurrentUser {

}
