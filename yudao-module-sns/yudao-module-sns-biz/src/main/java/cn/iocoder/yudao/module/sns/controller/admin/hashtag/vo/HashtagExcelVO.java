package cn.iocoder.yudao.module.sns.controller.admin.hashtag.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 话题标签 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class HashtagExcelVO {

    @ExcelProperty("主键ID")
    private Long id;

    @ExcelProperty("话题名")
    private String name;

    @ExcelProperty("描述")
    private String description;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
