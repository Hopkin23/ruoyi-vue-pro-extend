package cn.iocoder.yudao.module.sns.controller.app.publish.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 帖子 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PublishRespVO extends PublishBaseVO {

    @Schema(description = "帖子ID", required = true, example = "18078")
    private Long id;

    @Schema(description = "创建时间", required = true, example = "true")
    private LocalDateTime createTime;

    @Schema(description = "用户信息", required = true)
    private PublishUserRespVO user;

    @Schema(description = "话题标签", required = true, example = "数码家电")
    private PublishHashtagRespVO hashtag;

    @Schema(description = "是否我已经点赞", required = true, example = "true")
    private Boolean isLike;

}