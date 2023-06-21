package cn.iocoder.yudao.module.sns.controller.admin.hashtag.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 话题标签 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class HashtagRespVO extends HashtagBaseVO {

    @Schema(description = "主键ID", required = true, example = "29336")
    private Long id;

    @Schema(description = "创建时间", required = true)
    private LocalDateTime createTime;

}
