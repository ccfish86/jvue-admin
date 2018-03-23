# Host: 192.168.10.9  (Version 5.7.18)
# Date: 2018-03-23 18:57:46
# Generator: MySQL-Front 6.0  (Build 1.163)


#
# Data for table "jvue_api"
#

INSERT INTO `jvue_api` (`id`,`menu_id`,`api_id`,`name`) VALUES (3,33,5201,'画面管理列表'),(4,32,6001,'用户管理列表');

#
# Data for table "jvue_menu"
#

INSERT INTO `jvue_menu` (`id`,`module_id`,`type`,`path`,`component`,`name`,`icon_class`,`parent_id`,`enabled`,`show_nav`) VALUES (1,0,1,'/home','home-index','首页',NULL,NULL,1,1),(29,1,1,'/sys','common-main','权限LAYOUT',NULL,NULL,1,1),(30,2,1,'/user','common-main','用户LAYOUT',NULL,NULL,1,1),(31,1,1,'/sys/module','sys-module-index','模块列表',NULL,29,1,1),(32,2,1,'/user/list','user-index','用户列表',NULL,30,1,1),(33,1,1,'/sys/menu','sys-menu-index','画面列表',NULL,29,1,1),(34,1,1,'/sys/api','sys-api-index','接口列表',NULL,29,1,1),(35,1,1,'/sys/module/add','sys-module-add','模块追加',NULL,29,1,1),(36,1,1,'/sys/module/edit/:id','sys-module-edit','模块编辑',NULL,29,1,0),(37,1,1,'/sys/module/detail/:id','sys-module-detail','模块详情',NULL,29,1,0),(39,1,1,'/sys/menu/add','sys-menu-add','画面追加',NULL,29,1,1),(40,1,0,'/sys/menu/edit/:id','sys-menu-edit','画面编辑',NULL,29,1,0),(41,1,0,'/sys/menu/detail/:id','sys-menu-detail','画面详情',NULL,29,1,0),(42,10,2,'/test','common-main','测试L',NULL,NULL,1,1),(43,10,1,'/test/ll','test-ll','测试LL',NULL,42,1,1),(46,10,1,'/test/xx/list','test-xx-list','测试XX',NULL,42,0,0),(47,1,1,'/sys/segment','sys-segment-index','画面片段列表',NULL,29,1,1),(48,1,1,'/sec','common-main','授权LAYOUT',NULL,NULL,1,1),(49,1,1,'/sys/role','sys-role-index','角色列表',NULL,48,1,1),(50,1,1,'/sys/role/add','sys-role-add','角色追加',NULL,48,1,1),(51,1,1,'/sys/role/edit/:id','sys-role-edit','角色编辑',NULL,48,1,0),(52,1,1,'/sys/role/detail/:id','sys-role-detail','角色查看',NULL,48,1,0);

#
# Data for table "jvue_module"
#

INSERT INTO `jvue_module` (`id`,`name`,`enabled`) VALUES (1,'系统管理',1),(2,'用户管理',1),(10,'测试3',1),(11,'测试❹',1);

#
# Data for table "jvue_role"
#

INSERT INTO `jvue_role` (`id`,`name`,`enabled`) VALUES (1,'开发人员',0),(2,'人事经理',1);

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

