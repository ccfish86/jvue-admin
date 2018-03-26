### 索引
- [jvue-admin是什么？](#jvue-admin是什么)
- [前端技术栈](#前端技术栈)
- [后端技术栈](#后端技术栈)
- [授权设计](#授权设计)

### jvue-admin是什么
通过整合前端流行框架vue和相关组件，及后台的Spring boot，实现的权限管理及菜单动态生成最佳实践。

### 前端技术栈
vue, vuet, element-ui, axios

### 后端技术栈
spring boot, hazelcast, hibernate(jpa), mysql, swagger2

### 授权设计
传统的Struts，SpringMVC或类似的MVC架构下，页面和接口的权限是并行的，有的是以类型区分，有的是混着来的。
本项目中，以画面为单位，把接口和画面项目(按钮/组件)等绑定到画面，在给角色授权时，直观的可授予对应的接口和按钮的权限。
页面权限和后台接口权限分开加载，前台权限不持续再占用后台JVM内存，后台Spring Security启动时通过URL去DB加载并绑定角色，用户登录后直接通过ROLE和Spring Security匹配访问权限。
后续，可以结合工具，通过excel把画面定义好生成对应的vue和JAVA代码，或者后台JAVA实现类似功能，提高开发效率和统一的命名。

PS: 正在努力开发中，预计春节以后会有一版Release