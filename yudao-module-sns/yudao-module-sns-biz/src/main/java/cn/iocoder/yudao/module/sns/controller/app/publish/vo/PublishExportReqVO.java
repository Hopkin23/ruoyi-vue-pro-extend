package cn.iocoder.yudao.module.sns.controller.app.publish.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 帖子 Excel 导出 Request VO，参数和 PublishPageReqVO 是一致的")
@Data
public class PublishExportReqVO {

    @Schema(description = "发布者 ID", example = "23618")
    private Long userId;

    @Schema(description = "话题标签 ID", example = "1234")
    private Long hashtagId;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
