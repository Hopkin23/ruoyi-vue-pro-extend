package cn.iocoder.yudao.module.sns.controller.app.hashtag.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.*;

/**
* 话题标签 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class HashtagBaseVO {

    @Schema(description = "话题名", required = true, example = "张三")
    @NotNull(message = "话题名不能为空")
    private String name;

    @Schema(description = "描述", required = true, example = "你说的对")
    @NotNull(message = "描述不能为空")
    private String description;

}
