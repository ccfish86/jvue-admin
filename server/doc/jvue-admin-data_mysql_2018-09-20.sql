# Host: localhost  (Version 5.7.23)
# Date: 2018-09-20 14:57:10
# Generator: MySQL-Front 6.0  (Build 2.29)


#
# Structure for table "jvue_api"
#

DROP TABLE IF EXISTS `jvue_api`;
CREATE TABLE `jvue_api` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `page_id` int(11) NOT NULL DEFAULT '0' COMMENT '画面ID',
  `api_code` int(11) NOT NULL DEFAULT '0' COMMENT '接口编码',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '画面接口名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `api_m_a_idx` (`page_id`,`api_code`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COMMENT='画面接口';

#
# Data for table "jvue_api"
#

INSERT INTO `jvue_api` VALUES (3,33,5201,'画面管理列表'),(5,53,4011,'用户管理更新权限'),(6,54,4003,'用户管理追加'),(7,53,4001,'用户管理列表'),(8,53,5313,'角色管理权限名字列表'),(9,1,5501,'ACL管理资源'),(10,29,5501,'ACL管理资源'),(11,29,5005,'模块管理删除'),(12,30,5301,'角色管理列表'),(13,33,5500,''),(14,33,5001,'模块管理列表'),(15,33,5004,'模块管理更新'),(16,33,5002,'模块管理详情');

#
# Structure for table "jvue_dept"
#

DROP TABLE IF EXISTS `jvue_dept`;
CREATE TABLE `jvue_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门表ID',
  `code` varchar(9) NOT NULL DEFAULT '' COMMENT '部门编码',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '部门名',
  `parent_code` varchar(9) NOT NULL DEFAULT '' COMMENT '父部门编码',
  `level` int(11) NOT NULL DEFAULT '1' COMMENT '部门LEVEL',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

#
# Data for table "jvue_dept"
#


#
# Structure for table "jvue_module"
#

DROP TABLE IF EXISTS `jvue_module`;
CREATE TABLE `jvue_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '模块名',
  `enabled` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='模块';

#
# Data for table "jvue_module"
#

INSERT INTO `jvue_module` VALUES (1,'系统管理',1),(2,'用户管理',1),(10,'测试5',1),(11,'测试❹',1);

#
# Structure for table "jvue_oauth_user"
#

DROP TABLE IF EXISTS `jvue_oauth_user`;
CREATE TABLE `jvue_oauth_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0',
  `account_type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '用户类型',
  `account_id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '账号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='第三方账号';

#
# Data for table "jvue_oauth_user"
#


#
# Structure for table "jvue_page"
#

DROP TABLE IF EXISTS `jvue_page`;
CREATE TABLE `jvue_page` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `module_id` int(11) NOT NULL DEFAULT '0' COMMENT '模块ID',
  `type` int(3) DEFAULT '1' COMMENT '类型(1:菜单；2:画面；3:菜单+画面)',
  `path` varchar(64) DEFAULT NULL COMMENT '访问路径',
  `component` varchar(64) DEFAULT NULL COMMENT '页面模块',
  `name` varchar(64) DEFAULT NULL COMMENT '画面菜单名',
  `icon_class` varchar(64) DEFAULT NULL COMMENT '图标',
  `parent_id` int(11) DEFAULT NULL COMMENT '父菜单',
  `enabled` int(1) DEFAULT '1' COMMENT '是否有效',
  `show_nav` int(1) NOT NULL DEFAULT '1' COMMENT '菜单里显示',
  PRIMARY KEY (`id`),
  KEY `parentId` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COMMENT='画面';

#
# Data for table "jvue_page"
#

INSERT INTO `jvue_page` VALUES (1,1,1,'/home','home-index','首页',NULL,39,1,1),(29,1,1,'/sys','common-main','系统设置',NULL,NULL,1,1),(30,2,1,'/user','common-main','用户LAYOUT',NULL,NULL,1,1),(31,1,1,'/sys/module','sys-module-index','模块列表',NULL,NULL,1,1),(33,1,1,'/sys/page','sys-page-index','画面列表',NULL,29,1,1),(34,1,1,'/sys/api','sys-api-index','接口列表',NULL,29,1,1),(35,1,1,'/sys/module/add','sys-module-add','模块追加',NULL,29,1,0),(36,1,1,'/sys/module/edit/:id','sys-module-edit','模块编辑',NULL,29,1,0),(37,1,1,'/sys/module/detail/:id','sys-module-detail','模块详情',NULL,29,1,0),(39,1,1,'/sys/page/add','sys-page-add','画面追加',NULL,NULL,1,0),(40,1,0,'/sys/page/edit/:id','sys-page-edit','画面编辑',NULL,29,1,0),(41,1,0,'/sys/page/detail/:id','sys-page-detail','画面详情',NULL,29,1,0),(42,10,2,'/test','common-main','测试L',NULL,NULL,1,1),(43,10,1,'/test/ll','test-ll','测试LL',NULL,42,1,1),(46,10,1,'/test/xx/list','test-xx-list','测试XX',NULL,42,0,0),(47,1,1,'/sys/segment','sys-segment-index','画面片段列表',NULL,29,1,1),(48,1,1,'/sec','common-sysmain','角色和授权',NULL,NULL,1,1),(49,1,1,'/sys/role','sys-role-index','角色列表',NULL,48,1,1),(50,1,2,'/sys/role/add','sys-role-add','角色追加',NULL,48,1,1),(51,1,1,'/sys/role/edit/:id','sys-role-edit','角色编辑',NULL,48,1,0),(52,1,1,'/sys/role/detail/:id','sys-role-detail','角色查看',NULL,48,1,0),(53,2,1,'/user/list','user-index','用户列表',NULL,30,1,1),(54,2,1,'/user/add','user-add','用户添加',NULL,30,1,1),(55,2,1,'/user/edit/:id','user-edit','用户编辑',NULL,30,1,0),(56,1,1,'/sys/role/grant/:id','sys-role-grant','角色授权',NULL,48,1,0),(57,2,2,'/user/hehe',NULL,'画面测试',NULL,30,1,1);

#
# Structure for table "jvue_role"
#

DROP TABLE IF EXISTS `jvue_role`;
CREATE TABLE `jvue_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '角色名',
  `enabled` int(3) NOT NULL DEFAULT '0' COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COMMENT='角色';

#
# Data for table "jvue_role"
#

INSERT INTO `jvue_role` VALUES (2,'人事经理',1),(12,'aa',1);

#
# Structure for table "jvue_role_api"
#

DROP TABLE IF EXISTS `jvue_role_api`;
CREATE TABLE `jvue_role_api` (
  `id` int(11) DEFAULT NULL,
  `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '角色ID',
  `api_id` int(11) NOT NULL DEFAULT '0' COMMENT '接口ID',
  PRIMARY KEY (`role_id`,`api_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色对应接口权限';

#
# Data for table "jvue_role_api"
#

INSERT INTO `jvue_role_api` VALUES (NULL,1,5),(NULL,1,7),(NULL,1,8),(NULL,2,5),(NULL,2,6),(NULL,2,7),(NULL,2,8),(NULL,2,14),(NULL,2,15),(NULL,2,16);

#
# Structure for table "jvue_role_page"
#

DROP TABLE IF EXISTS `jvue_role_page`;
CREATE TABLE `jvue_role_page` (
  `id` int(11) DEFAULT NULL,
  `role_id` int(11) NOT NULL DEFAULT '0',
  `page_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`role_id`,`page_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色对应画面菜单';

#
# Data for table "jvue_role_page"
#

INSERT INTO `jvue_role_page` VALUES (NULL,1,31),(NULL,1,33),(NULL,1,36),(NULL,1,37),(NULL,1,40),(NULL,1,47),(NULL,1,49),(NULL,1,50),(NULL,1,51),(NULL,1,52),(NULL,1,53),(NULL,1,56),(NULL,2,33),(NULL,2,34),(NULL,2,40),(NULL,2,41),(NULL,2,49),(NULL,2,50),(NULL,2,51),(NULL,2,53),(NULL,2,54),(NULL,2,55),(NULL,2,57);

#
# Structure for table "jvue_role_segment"
#

DROP TABLE IF EXISTS `jvue_role_segment`;
CREATE TABLE `jvue_role_segment` (
  `id` int(11) DEFAULT NULL,
  `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '角色ID',
  `segment_id` int(11) NOT NULL DEFAULT '0' COMMENT '画面片段ID',
  PRIMARY KEY (`role_id`,`segment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色对应画面片段';

#
# Data for table "jvue_role_segment"
#


#
# Structure for table "jvue_segment"
#

DROP TABLE IF EXISTS `jvue_segment`;
CREATE TABLE `jvue_segment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `page_id` int(11) NOT NULL DEFAULT '0' COMMENT '画面ID',
  `segment_code` int(11) NOT NULL DEFAULT '0' COMMENT '片段ID，画面内唯一',
  `name` varchar(64) DEFAULT NULL COMMENT '画面片段名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `segment_m_s_idx` (`page_id`,`segment_code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='画面片断';

#
# Data for table "jvue_segment"
#

INSERT INTO `jvue_segment` VALUES (2,56,123,'測試');

#
# Structure for table "jvue_user"
#

DROP TABLE IF EXISTS `jvue_user`;
CREATE TABLE `jvue_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` char(128) DEFAULT NULL COMMENT '密码',
  `email` varchar(64) DEFAULT NULL COMMENT '邮件',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `super_user` int(1) NOT NULL DEFAULT '0' COMMENT '超级用户',
  `dept_code` char(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COMMENT='用户';

#
# Data for table "jvue_user"
#

INSERT INTO `jvue_user` VALUES (3,'admin','$2a$10$yMiel5kU.Z47Dpc3iHgIo.zHwv2AH4JUk6ZQ0vYoHyzOjimfkLIDm',NULL,1,'jvue super admin',1,NULL),(4,'ccfish','$2a$10$yMiel5kU.Z47Dpc3iHgIo.zHwv2AH4JUk6ZQ0vYoHyzOjimfkLIDm',NULL,1,'ccfish',0,NULL),(5,'猪',NULL,'c@ccfish.net',1,'雨猪',0,NULL),(6,'wang',NULL,'wang@ccfish.net',1,'sdfafsdafsadf',0,NULL),(7,'li',NULL,'li@ccfish.net',1,'lilliiii',0,NULL),(8,'llll','$2a$10$AFV7xAUsRE.D8pFF23Na7ONi.8A0Hyb9PL3u68qGwrlCj6zA.avSG','lldfsl@ccfish.net',1,'lldsf',0,NULL),(9,'user','$2a$10$Q5EfylMiqew8OjRbzb6t9.aPygoFV357ycMMbSK0mUARTv6jWAAxK','user@jvue.ccfish.net',1,'normal user',0,NULL),(10,'test','$2a$10$ZbG7yG8NcgtNu1acASbQ.OoiTk01a3l1SEtcn4pNJZI2jw522Sppe',NULL,1,'test',1,NULL),(11,'test5566','$2a$10$tpWDRWU8LxD5z2y5.KV.Qe6RBxhGdTyrTP5OVzesTKBwoKBsetGYG','21333@fff.com',0,'1234',0,NULL),(12,'dsaf','$2a$10$748Plgi9Zzfo.XgBwBXGDub/fHb3QZ5WeXe76iMuWpGYk21uGrImy','234@123.com',0,'1',0,NULL),(13,'aaa','$2a$10$a6.XlGxvquieDkkIN8sPmOVxsjVtqurtj8xhaEZv015AOWcDTgh7S','as@qq.com',1,'as',1,NULL),(15,'15622514#github',NULL,NULL,NULL,NULL,0,NULL),(16,'15622514#github',NULL,NULL,NULL,NULL,0,NULL);

#
# Structure for table "jvue_user_role"
#

DROP TABLE IF EXISTS `jvue_user_role`;
CREATE TABLE `jvue_user_role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户',
  `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='用户权限';

#
# Data for table "jvue_user_role"
#

INSERT INTO `jvue_user_role` VALUES (1,3,1),(2,4,2),(3,9,2),(4,6,2),(5,8,2),(6,8,1),(7,10,1),(8,10,2),(9,10,14);

#
# Structure for table "persistent_logins"
#

DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "persistent_logins"
#

INSERT INTO `persistent_logins` VALUES ('admin','YYLvyHuHPnbM8A/ORDjwfw==','s4Y58zeNfJXj1PQAo2r7kQ==','2018-09-13 11:48:20');
