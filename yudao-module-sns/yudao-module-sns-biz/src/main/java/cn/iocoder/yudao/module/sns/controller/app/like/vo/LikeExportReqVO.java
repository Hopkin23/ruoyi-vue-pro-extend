package cn.iocoder.yudao.module.sns.controller.app.like.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 点赞 Excel 导出 Request VO，参数和 LikePageReqVO 是一致的")
@Data
public class LikeExportReqVO {

    @Schema(description = "用户主键 ID", example = "25890")
    private Long userId;

    @Schema(description = "1.正向（赞） / 2.反向（踩）", example = "1")
    private Byte markType;

    @Schema(description = "1.帖子（可扩展其他点赞的类型）", example = "2")
    private Byte likeType;

    @Schema(description = "1.关联字段 publish->id", example = "24693")
    private Long likeId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
