package cn.iocoder.yudao.module.sns.controller.admin.comment.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 评论分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CommentPageReqVO extends PageParam {

    @Schema(description = "帖子 ID", example = "16629")
    private Long publishId;

    @Schema(description = "父级评论 ID", example = "631")
    private Long parentId;

    @Schema(description = "顶级评论 ID", example = "11932")
    private Long topParentId;

    @Schema(description = "发表者 ID", example = "4646")
    private Long userId;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}