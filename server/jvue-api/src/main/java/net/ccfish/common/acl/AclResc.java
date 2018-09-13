package net.ccfish.common.acl;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.annotation.Secured;

/**
 * 定义接口信息，用于角色授权画面
 * <br>
 * 需要后续处理的事项，有一部分字段，与Swagger2重复，仅需保留Swagger2定义的一部分，便于开发与维护
 * 
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Secured("ROLE_USER")
@Documented
public @interface AclResc {
    
    int id();// ACLResource 因为特殊原因不使用 id 自动增长,所以必须自定义ID ,并且不能重复

//    String code();
//
//    String name();
//
//    // 前后端分离，可能用不着
//    String homePage() default "";

    // 前后端分离，用不着
//    boolean isMenu() default false;
}
