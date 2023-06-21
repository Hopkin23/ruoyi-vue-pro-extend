package cn.iocoder.yudao.module.sns.dal.dataobject.publish;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 帖子 DO
 *
 * @author 芋道源码
 */
@TableName("sns_publish")
@KeySequence("sns_publish_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublishDO extends BaseDO {

    /**
     * 帖子ID
     */
    @TableId
    private Long id;
    /**
     * 发布者 ID
     */
    private Long userId;
    /**
     * 话题标签 ID
     */
    private Long hashtagId;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;

    /**
     * 图片的数组
     * 最多上传 9 张
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> picUrls;

    /**
     * 评论数
     */
    private Integer comment_count;

    /**
     * 点赞数
     */
    private Integer like_count;

}
