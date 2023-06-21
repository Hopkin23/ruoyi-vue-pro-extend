package cn.iocoder.yudao.module.sns.controller.admin.comment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 评论更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CommentUpdateReqVO extends CommentBaseVO {

    @Schema(description = "评论ID", required = true, example = "16909")
    @NotNull(message = "评论ID不能为空")
    private Long id;

}
