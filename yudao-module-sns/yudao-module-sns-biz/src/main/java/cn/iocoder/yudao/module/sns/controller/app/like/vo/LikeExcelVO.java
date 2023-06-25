package cn.iocoder.yudao.module.sns.controller.app.like.vo;

import lombok.*;

import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 点赞 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class LikeExcelVO {

    @ExcelProperty("用户点赞记录ID")
    private Long id;

    @ExcelProperty("用户主键 ID")
    private Long userId;

    @ExcelProperty("1.正向（赞） / 2.反向（踩）")
    private Byte markType;

    @ExcelProperty("1.帖子（可扩展其他点赞的类型）")
    private Byte likeType;

    @ExcelProperty("1.关联字段 publish->id")
    private Long likeId;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
