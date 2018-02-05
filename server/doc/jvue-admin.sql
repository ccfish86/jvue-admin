# Host: 192.168.10.9  (Version 5.7.18)
# Date: 2018-02-05 13:46:00
# Generator: MySQL-Front 6.0  (Build 1.163)


#
# Structure for table "jvue_api"
#

CREATE TABLE `jvue_api` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '画面接口名',
  `menu_id` int(11) DEFAULT NULL COMMENT '画面菜单ID',
  `api_code` char(8) DEFAULT NULL COMMENT '接口编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='画面接口';

#
# Data for table "jvue_api"
#


#
# Structure for table "jvue_menu"
#

CREATE TABLE `jvue_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `module_id` int(11) NOT NULL DEFAULT '0' COMMENT '模块ID',
  `type` tinyint(3) DEFAULT '1' COMMENT '类型(1:菜单；2:画面；3:菜单+画面)',
  `path` varchar(64) DEFAULT NULL COMMENT '访问路径',
  `component` varchar(64) DEFAULT NULL COMMENT '页面模块',
  `name` varchar(64) DEFAULT NULL COMMENT '画面菜单名',
  `icon_class` varchar(64) DEFAULT NULL COMMENT '图标',
  `parent_id` int(11) DEFAULT NULL COMMENT '父菜单',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否有效',
  PRIMARY KEY (`id`),
  KEY `parentId` (`parent_id`),
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `jvue_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COMMENT='画面菜单';

#
# Data for table "jvue_menu"
#

INSERT INTO `jvue_menu` VALUES (1,0,1,'/home','home-index','首页',NULL,NULL,1),(29,1,1,'/sys','common-main','模块LAYOUT',NULL,NULL,1),(30,2,1,'/user','common-main','用户LAYOUT',NULL,NULL,1),(31,1,1,'/sys/module','sys-module-index','模块列表',NULL,29,1),(32,2,1,'/user/list','user-index','用户列表',NULL,30,1),(33,1,1,'/sys/menu','sys-munu-index','画面列表',NULL,29,1),(34,1,1,'/sys/api','sys-api-index','接口列表',NULL,29,1);

#
# Structure for table "jvue_module"
#

CREATE TABLE `jvue_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '模块名',
  `enabled` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='模块';

#
# Data for table "jvue_module"
#

INSERT INTO `jvue_module` VALUES (1,'系统管理',1),(2,'用户管理',1);

#
# Structure for table "jvue_role"
#

CREATE TABLE `jvue_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '角色名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色';

#
# Data for table "jvue_role"
#


#
# Structure for table "jvue_role_api"
#

CREATE TABLE `jvue_role_api` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '角色ID',
  `api_id` int(11) NOT NULL DEFAULT '0' COMMENT '接口ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色对应接口权限';

#
# Data for table "jvue_role_api"
#


#
# Structure for table "jvue_role_memu"
#

CREATE TABLE `jvue_role_memu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL DEFAULT '0',
  `memu_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色对应画面菜单';

#
# Data for table "jvue_role_memu"
#


#
# Structure for table "jvue_role_segment"
#

CREATE TABLE `jvue_role_segment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '角色ID',
  `segment_id` int(11) NOT NULL DEFAULT '0' COMMENT '画面片段ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色对应画面片段';

#
# Data for table "jvue_role_segment"
#


#
# Structure for table "jvue_segment"
#

CREATE TABLE `jvue_segment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) NOT NULL DEFAULT '0' COMMENT '画面菜单ID',
  `name` varchar(64) DEFAULT NULL COMMENT '画面片段名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='画面片断';

#
# Data for table "jvue_segment"
#


#
# Structure for table "user"
#

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` char(128) DEFAULT NULL COMMENT '密码',
  `email` varchar(64) DEFAULT NULL COMMENT '邮件',
  `status` tinyint(3) DEFAULT NULL COMMENT '状态',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `super_user` tinyint(1) NOT NULL DEFAULT '0' COMMENT '超级用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='用户';

#
# Data for table "user"
#

INSERT INTO `user` VALUES (3,'admin','4ae85175a2fac63b49644969c3e97fb2757b17ceb48a1ab36f16e530963fce41db2b2f40ce911809',NULL,1,'jvue super admin user',1);

#
# Structure for table "user_role"
#

CREATE TABLE `user_role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户',
  `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户权限';

#
# Data for table "user_role"
#

