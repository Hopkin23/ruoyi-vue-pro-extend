package cn.iocoder.yudao.module.sns.dal.dataobject.like;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 点赞 DO
 */
@TableName("sns_like")
@KeySequence("sns_like_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeDO extends BaseDO {

    /**
     * 用户点赞记录ID
     */
    @TableId
    private Long id;
    /**
     * 用户主键 ID
     */
    private Long userId;
    /**
     * 1.正向（赞） / 2.反向（踩）
     */
    private Byte markType;
    /**
     * 1.帖子
（可扩展其他点赞的类型）
     */
    private Byte likeType;
    /**
     * 1.关联字段 publish->id
     */
    private Long likeId;

}
