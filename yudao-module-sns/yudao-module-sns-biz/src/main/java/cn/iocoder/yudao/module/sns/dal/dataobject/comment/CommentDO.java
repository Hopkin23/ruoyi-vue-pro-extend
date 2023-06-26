package cn.iocoder.yudao.module.sns.dal.dataobject.comment;

import lombok.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 评论 DO
 */
@TableName("sns_comment")
@KeySequence("sns_comment_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDO extends BaseDO {

    /**
     * 评论ID
     */
    @TableId
    private Long id;
    /**
     * 帖子 ID
     */
    private Long publishId;
    /**
     * 父级评论 ID
     */
    private Long parentId;
    /**
     * 顶级评论 ID
     */
    private Long topParentId;
    /**
     * 发表者 ID
     */
    private Long userId;
    /**
     * 内容
     */
    private String content;

}
