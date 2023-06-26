package cn.iocoder.yudao.module.sns.controller.app.publish.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 帖子更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PublishUpdateReqVO extends PublishBaseVO {

    @Schema(description = "帖子ID", required = true, example = "18078")
    @NotNull(message = "帖子ID不能为空")
    private Long id;

}
