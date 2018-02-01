package net.ccfish.jvue.service.acl;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AclResc {
    
    int id();// ACLResource 因为特殊原因不使用 id 自动增长,所以必须自定义ID ,并且不能重复

    String code();

    String name();

    String homePage() default "";

    boolean isMenu() default true;
}
