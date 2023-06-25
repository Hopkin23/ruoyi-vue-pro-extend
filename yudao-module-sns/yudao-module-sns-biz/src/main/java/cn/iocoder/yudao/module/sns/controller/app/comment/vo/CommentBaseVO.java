package cn.iocoder.yudao.module.sns.controller.app.comment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.*;

/**
* 评论 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class CommentBaseVO {

    /**
     * 帖子 ID
     */
    @Schema(description = "帖子 ID", required = true, example = "16629")
    @NotNull(message = "帖子 ID不能为空")
    private Long publishId;

    /**
     * 父级评论 ID
     */
    @Schema(description = "父级评论 ID", required = false, example = "631")
    private Long parentId;

    /**
     * 顶级评论 ID
     */
    private Long topParentId;

    /**
     * 发表者 ID
     */
    private Long userId;

    /**
     * 内容
     */
    @Schema(description = "内容", required = true)
    @NotNull(message = "内容不能为空")
    private String content;

}
