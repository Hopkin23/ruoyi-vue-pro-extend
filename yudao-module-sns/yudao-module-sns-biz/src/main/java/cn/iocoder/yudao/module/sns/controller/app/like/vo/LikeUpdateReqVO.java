package cn.iocoder.yudao.module.sns.controller.app.like.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.*;

@Schema(description = "管理后台 - 点赞更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LikeUpdateReqVO extends LikeBaseVO {

    @Schema(description = "用户点赞记录ID", required = true, example = "32448")
    @NotNull(message = "用户点赞记录ID不能为空")
    private Long id;

}
