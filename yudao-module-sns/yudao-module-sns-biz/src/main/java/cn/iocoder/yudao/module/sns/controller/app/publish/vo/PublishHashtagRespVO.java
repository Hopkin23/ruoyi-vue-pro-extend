package cn.iocoder.yudao.module.sns.controller.app.publish.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 帖子列表中的话题标签信息
 */
@Data
public class PublishHashtagRespVO {

    @Schema(description = "话题标签名称", example = "数码产品")
    private String name;

}
