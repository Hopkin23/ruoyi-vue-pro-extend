package cn.iocoder.yudao.module.sns.controller.app.comment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 评论 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CommentRespVO extends CommentBaseVO {

    @Schema(description = "评论ID", required = true, example = "16909")
    private Long id;

    @Schema(description = "创建时间", required = true)
    private LocalDateTime createTime;

}
