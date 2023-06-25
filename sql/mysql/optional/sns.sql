----------------------
-- SNS模块社区功能建表SQL
----------------------

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sns_publish 帖子动态
-- ----------------------------
DROP TABLE IF EXISTS `sns_publish`;
CREATE TABLE sns_publish (
    id            bigint unsigned auto_increment comment '帖子ID' primary key,
    user_id       bigint unsigned                         not null comment '发布者 ID',
    hashtag_id    bigint unsigned                         not null comment '话题标签ID',
    title         varchar(255)                            not null comment '标题',
    content       longtext                                not null comment '内容',
    pic_urls      varchar(1024) default ''                null comment '图片地址 数组，以逗号分隔 最多上传9张',
    comment_count int           default 0                 not null comment '评论数',
    like_count    int           default 0                 not null comment '点赞数',
    creator       varchar(64)   default ''                not null comment '创建者',
    create_time   datetime      default CURRENT_TIMESTAMP not null comment '创建时间',
    updater       varchar(64)   default ''                not null comment '更新者',
    update_time   datetime      default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted       bit           default b'0'              not null comment '是否删除',
    constraint id_UNIQUE
        unique (id)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci  comment '帖子表';

-- ----------------------------
-- Records of sns_publish
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sns_comment 评论
-- ----------------------------
DROP TABLE IF EXISTS `sns_comment`;
CREATE TABLE sns_comment(
    id            bigint unsigned auto_increment comment '评论ID'   primary key,
    publish_id    bigint                                not null comment '帖子 ID',
    parent_id     bigint      default 0                 not null comment '父级评论 ID',
    top_parent_id bigint      default 0                 not null comment '顶级评论 ID',
    user_id       bigint                                not null comment '发表者 ID',
    content       longtext                              not null comment '内容',
    creator       varchar(64) default ''                not null comment '创建者',
    create_time   datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updater       varchar(64) default ''                not null comment '更新者',
    update_time   datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted       bit         default b'0'              not null comment '是否删除',
    constraint id_UNIQUE
        unique (id)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci comment '评论表';

-- ----------------------------
-- Records of sns_comment
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sns_like 点赞
-- ----------------------------
DROP TABLE IF EXISTS `sns_like`;
CREATE TABLE sns_like (
    id          bigint unsigned auto_increment comment '用户点赞记录ID'
    primary key,
    user_id     bigint unsigned                            not null comment '用户主键 ID',
    mark_type   tinyint unsigned default '1'               not null comment '操作类型 1.正向（赞） / 2.反向（踩）',
    like_type   tinyint unsigned default '1'               not null comment '目标类型 1.帖子
（可扩展其他点赞的类型）',
    like_id     bigint unsigned                            not null comment '1.关联字段 publish->id',
    creator     varchar(64)      default ''                not null comment '创建者',
    create_time datetime         default CURRENT_TIMESTAMP not null comment '更新时间',
    updater     varchar(64)      default ''                not null comment '更新者',
    update_time datetime         default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     bit              default b'0'              not null comment '是否删除',
    constraint id_UNIQUE
    unique (id)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci comment '点赞表';

-- ----------------------------
-- Records of sns_like
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sns_hashtag 话题分类
-- ----------------------------
DROP TABLE IF EXISTS `sns_hashtag`;
CREATE TABLE sns_hashtag (
    id          bigint unsigned auto_increment comment '主键ID'
    primary key,
    name        varchar(64)                           not null comment '话题名',
    description varchar(255)                          not null comment '描述',
    creator     varchar(64) default ''                not null comment '创建者',
    create_time datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updater     varchar(64) default ''                not null comment '更新者',
    update_time datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     bit         default b'0'              not null comment '是否删除'
) comment '话题标签表';


-- ----------------------------
-- Records of sns_hashtag
-- ----------------------------
BEGIN;
COMMIT;



SET FOREIGN_KEY_CHECKS = 1;
