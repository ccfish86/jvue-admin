# Host: 192.168.10.9  (Version 5.7.18)
# Date: 2018-05-07 10:20:33
# Generator: MySQL-Front 6.0  (Build 1.163)


#
# Structure for table "jvue_api"
#

CREATE TABLE `jvue_api` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `page_id` int(11) NOT NULL DEFAULT '0' COMMENT '画面ID',
  `api_code` int(11) NOT NULL DEFAULT '0' COMMENT '接口编码',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '画面接口名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `api_m_a_idx` (`page_id`,`api_code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='画面接口';

#
# Structure for table "jvue_module"
#

CREATE TABLE `jvue_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '模块名',
  `enabled` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='模块';

#
# Structure for table "jvue_page"
#

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
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COMMENT='画面';

#
# Structure for table "jvue_role"
#

CREATE TABLE `jvue_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '角色名',
  `enabled` int(3) NOT NULL DEFAULT '0' COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='角色';

#
# Structure for table "jvue_role_api"
#

CREATE TABLE `jvue_role_api` (
  `id` int(11) DEFAULT NULL,
  `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '角色ID',
  `api_id` int(11) NOT NULL DEFAULT '0' COMMENT '接口ID',
  PRIMARY KEY (`role_id`,`api_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色对应接口权限';

#
# Structure for table "jvue_role_page"
#

CREATE TABLE `jvue_role_page` (
  `id` int(11) DEFAULT NULL,
  `role_id` int(11) NOT NULL DEFAULT '0',
  `page_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`role_id`,`page_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色对应画面菜单';

#
# Structure for table "jvue_role_segment"
#

CREATE TABLE `jvue_role_segment` (
  `id` int(11) DEFAULT NULL,
  `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '角色ID',
  `segment_id` int(11) NOT NULL DEFAULT '0' COMMENT '画面片段ID',
  PRIMARY KEY (`role_id`,`segment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色对应画面片段';

#
# Structure for table "jvue_segment"
#

CREATE TABLE `jvue_segment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `page_id` int(11) NOT NULL DEFAULT '0' COMMENT '画面ID',
  `segment_code` int(11) NOT NULL DEFAULT '0' COMMENT '片段ID，画面内唯一',
  `name` varchar(64) DEFAULT NULL COMMENT '画面片段名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `segment_m_s_idx` (`page_id`,`segment_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='画面片断';

#
# Structure for table "jvue_user"
#

CREATE TABLE `jvue_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` char(128) DEFAULT NULL COMMENT '密码',
  `email` varchar(64) DEFAULT NULL COMMENT '邮件',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `super_user` int(1) NOT NULL DEFAULT '0' COMMENT '超级用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='用户';

#
# Structure for table "jvue_user_role"
#

CREATE TABLE `jvue_user_role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户',
  `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='用户权限';
