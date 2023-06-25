package cn.iocoder.yudao.module.sns.controller.app.publish.vo;

import lombok.*;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 帖子 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class PublishExcelVO {

    @ExcelProperty("帖子ID")
    private Long id;

    @ExcelProperty("发布者 ID")
    private Long userId;

    @ExcelProperty("话题标签 ID")
    private Long hashtagId;

    @ExcelProperty("标题")
    private String title;

    @ExcelProperty("内容")
    private String content;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
