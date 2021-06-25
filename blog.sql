CREATE DATABASE IF NOT EXISTS `blog` DEFAULT CHARACTER SET utf8;

USE `blog`;

/* 评论表 */
DROP TABLE IF EXISTS `t_comment`;
/* 博客&标签多对多关联 */
DROP TABLE IF EXISTS `t_blog_tag_list`;
/* 博客表 */
DROP TABLE IF EXISTS `t_blog`;
/* 标签表 */
DROP TABLE IF EXISTS `t_tag`;
/* 类型表 */
DROP TABLE IF EXISTS `t_type`;
/* 用户表 */
DROP TABLE IF EXISTS `t_user`;


/*Table structure for table `t_user` */
CREATE TABLE `t_user`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `avatar`      varchar(255) DEFAULT NULL,
    `create_time` datetime(6)  DEFAULT NULL,
    `email`       varchar(255) DEFAULT NULL,
    `nickname`    varchar(255) DEFAULT NULL,
    `password`    varchar(255) DEFAULT NULL,
    `type`        int(11)      DEFAULT NULL,
    `update_time` datetime(6)  DEFAULT NULL,
    `username`    varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


/*Table structure for table `t_type` */
CREATE TABLE `t_type`
(
    `id`   bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


/*Table structure for table `t_tag` */
CREATE TABLE `t_tag`
(
    `id`   bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


/*Table structure for table `t_blog` */
CREATE TABLE `t_blog`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT,
    `appreciation`    bit(1)     NOT NULL,
    `commented`       bit(1)     NOT NULL,
    `content`         longtext,
    `create_time`     datetime(6)  DEFAULT NULL,
    `flag`            varchar(255) DEFAULT NULL,
    `published`       bit(1)     NOT NULL,
    `recommend`       bit(1)     NOT NULL,
    `share_statement` bit(1)     NOT NULL,
    `title`           varchar(255) DEFAULT NULL,
    `update_time`     datetime(6)  DEFAULT NULL,
    `views`           int(11)      DEFAULT NULL,
    `type_id`         bigint(20)   DEFAULT NULL,
    `user_id`         bigint(20)   DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY (`type_id`),
    KEY (`user_id`),
    CONSTRAINT FOREIGN KEY (`type_id`) REFERENCES `t_type` (`id`),
    CONSTRAINT FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


/*Table structure for table `t_blog_tag_list` */
CREATE TABLE `t_blog_tag_list`
(
    `blog_list_id` bigint(20) NOT NULL,
    `tag_list_id`  bigint(20) NOT NULL,
    KEY (`tag_list_id`),
    KEY (`blog_list_id`),
    CONSTRAINT FOREIGN KEY (`tag_list_id`) REFERENCES `t_tag` (`id`),
    CONSTRAINT FOREIGN KEY (`blog_list_id`) REFERENCES `t_blog` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


/*Table structure for table `t_comment` */
CREATE TABLE `t_comment`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT,
    `avatar`            varchar(255) DEFAULT NULL,
    `content`           varchar(255) DEFAULT NULL,
    `create_time`       datetime(6)  DEFAULT NULL,
    `email`             varchar(255) DEFAULT NULL,
    `nickname`          varchar(255) DEFAULT NULL,
    `blog_id`           bigint(20)   DEFAULT NULL,
    `parent_comment_id` bigint(20)   DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY (`blog_id`),
    KEY (`parent_comment_id`),
    CONSTRAINT FOREIGN KEY (`parent_comment_id`) REFERENCES `t_comment` (`id`),
    CONSTRAINT FOREIGN KEY (`blog_id`) REFERENCES `t_blog` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;



/* 添加测试数据 */
insert into t_user (avatar, create_time, nickname, password, type, update_time, username)
values ('image/avatar.jpg', NOW(), 'roderland', 'e10adc3949ba59abbe56e057f20f883e', 1, NOW(), 'roderland');