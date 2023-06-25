package cn.iocoder.yudao.module.sns.controller.app.comment.vo;

import lombok.*;

import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 评论 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class CommentExcelVO {

    @ExcelProperty("评论ID")
    private Long id;

    @ExcelProperty("帖子 ID")
    private Long publishId;

    @ExcelProperty("父级评论 ID")
    private Long parentId;

    @ExcelProperty("顶级评论 ID")
    private Long topParentId;

    @ExcelProperty("发表者 ID")
    private Long userId;

    @ExcelProperty("内容")
    private String content;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
