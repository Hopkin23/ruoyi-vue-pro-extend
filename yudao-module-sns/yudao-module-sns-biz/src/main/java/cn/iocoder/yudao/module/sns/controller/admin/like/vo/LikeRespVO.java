package cn.iocoder.yudao.module.sns.controller.admin.like.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 点赞 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LikeRespVO extends LikeBaseVO {

    @Schema(description = "用户点赞记录ID", required = true, example = "32448")
    private Long id;

    @Schema(description = "创建时间", required = true)
    private LocalDateTime createTime;

}
