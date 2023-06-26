package cn.iocoder.yudao.module.sns.controller.app.hashtag.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 话题标签 Excel 导出 Request VO，参数和 HashtagPageReqVO 是一致的")
@Data
public class HashtagExportReqVO {

    @Schema(description = "话题名", example = "张三")
    private String name;

    @Schema(description = "描述", example = "你说的对")
    private String description;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
