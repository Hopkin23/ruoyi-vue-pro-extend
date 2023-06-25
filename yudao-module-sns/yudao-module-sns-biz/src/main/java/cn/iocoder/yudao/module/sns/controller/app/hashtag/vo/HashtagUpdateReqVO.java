package cn.iocoder.yudao.module.sns.controller.app.hashtag.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.*;

@Schema(description = "管理后台 - 话题标签更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class HashtagUpdateReqVO extends HashtagBaseVO {

    @Schema(description = "主键ID", required = true, example = "29336")
    @NotNull(message = "主键ID不能为空")
    private Long id;

}
