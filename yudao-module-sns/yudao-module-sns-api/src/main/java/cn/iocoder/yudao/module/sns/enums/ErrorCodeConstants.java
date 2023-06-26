package cn.iocoder.yudao.module.sns.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * 社交系统 错误码枚举类
 * 社交系统，使用 1-011-000-000 段
 */
public interface ErrorCodeConstants {
    ErrorCode PUBLISH_NOT_EXISTS = new ErrorCode(10010, "帖子主不存在");

    ErrorCode COMMENT_NOT_EXISTS = new ErrorCode(10011, "评论不存在");

    ErrorCode LIKE_NOT_EXISTS = new ErrorCode(10012, "点赞不存在");

    ErrorCode LIKE_NUMBER_ERROR = new ErrorCode(10014, "点赞记录条数有误");

    ErrorCode HASHTAG_NOT_EXISTS = new ErrorCode(10013, "话题标签不存在");

}