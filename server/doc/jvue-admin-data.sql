# Host: 192.168.10.9  (Version 5.7.18)
# Date: 2018-03-22 12:18:06
# Generator: MySQL-Front 6.0  (Build 1.163)


#
# Data for table "jvue_api"
#


#
# Data for table "jvue_menu"
#

INSERT INTO `jvue_menu` (`id`,`module_id`,`type`,`path`,`component`,`name`,`icon_class`,`parent_id`,`enabled`,`show_nav`) VALUES (1,0,1,'/home','home-index','首页',NULL,NULL,1,1),(29,1,1,'/sys','common-main','模块LAYOUT',NULL,NULL,1,1),(30,2,1,'/user','common-main','用户LAYOUT',NULL,NULL,1,1),(31,1,1,'/sys/module','sys-module-index','模块列表',NULL,29,1,1),(32,2,1,'/user/list','user-index','用户列表',NULL,30,1,1),(33,1,1,'/sys/menu','sys-menu-index','画面列表',NULL,29,1,1),(34,1,1,'/sys/api','sys-api-index','接口列表',NULL,29,1,1),(35,1,1,'/sys/module/add','sys-module-add','模块追加',NULL,29,1,1),(36,1,1,'/sys/module/edit/:id','sys-module-edit','模块编辑',NULL,29,1,0),(37,1,1,'/sys/module/detail/:id','sys-module-detail','模块详情',NULL,29,1,0),(39,1,1,'/sys/menu/add','sys-menu-add','画面追加',NULL,29,1,1),(40,1,0,'/sys/menu/edit/:id','sys-menu-edit','画面编辑',NULL,29,1,0),(41,1,0,'/sys/menu/detail/:id','sys-menu-detail','画面详情',NULL,29,1,0),(42,10,2,'/test','common-main','测试L',NULL,NULL,1,1),(43,10,1,'/test/ll','test-ll','测试LL',NULL,42,1,1);

#
# Data for table "jvue_module"
#

INSERT INTO `jvue_module` (`id`,`name`,`enabled`) VALUES (1,'系统管理',1),(2,'用户管理',1),(10,'测试3',1),(11,'测试❹',1);

#
# Data for table "jvue_role"
#


#
# Data for table "jvue_role_api"
#


#
# Data for table "jvue_role_memu"
#


#
# Data for table "jvue_role_segment"
#


#
# Data for table "jvue_segment"
#


#
# Data for table "user"
#

INSERT INTO `user` (`id`,`username`,`password`,`email`,`status`,`nickname`,`super_user`) VALUES (3,'admin','$2a$10$yMiel5kU.Z47Dpc3iHgIo.zHwv2AH4JUk6ZQ0vYoHyzOjimfkLIDm',NULL,1,'jvue super admin',1);

#
# Data for table "user_role"
#

