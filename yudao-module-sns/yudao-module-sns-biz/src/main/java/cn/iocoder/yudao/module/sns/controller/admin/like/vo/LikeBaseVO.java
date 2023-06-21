package cn.iocoder.yudao.module.sns.controller.admin.like.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import javax.validation.constraints.*;

/**
* 点赞 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class LikeBaseVO {

    /**
     * 用户主键 ID
     */
    private Long userId;

    /**
     * 操作类型
     * 1.正向（赞） / 2.反向（踩）
     */
    private Byte markType;

    /**
     * 目标类型
     * 1.帖子（可扩展其他点赞的类型）
     */
    private Byte likeType;

    @Schema(description = "1.关联字段 publish->id", required = true, example = "24693")
    @NotNull(message = "1.关联字段 publish->id不能为空")
    private Long likeId;

}
