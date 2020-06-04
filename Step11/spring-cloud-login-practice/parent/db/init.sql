-- ----------------------------
-- Table structure for auth_code
-- ----------------------------
DROP TABLE IF EXISTS `auth_code`;
CREATE TABLE `auth_code` (
--  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '⾃增主键',
 `email` varchar(64) DEFAULT NULL COMMENT '邮箱地址',
 `code` varchar(6) DEFAULT NULL COMMENT '验证码',
 `createtime` datetime DEFAULT NULL COMMENT '创建时间',
 `expiretime` datetime DEFAULT NULL COMMENT '过期时间',
 PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_userinfo`;
CREATE TABLE `t_userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
--   `code` varchar(100) DEFAULT '' COMMENT '验证码',
  `token` varchar(100) DEFAULT '' COMMENT '凭证',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;