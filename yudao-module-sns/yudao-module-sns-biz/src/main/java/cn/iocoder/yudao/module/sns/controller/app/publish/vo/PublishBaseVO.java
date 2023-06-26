package cn.iocoder.yudao.module.sns.controller.app.publish.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

/**
* 帖子 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class PublishBaseVO {

    @NotNull(message = "发布者 ID不能为空")
    private Long userId;

    @Schema(description = "话题标签 ID", required = true, example = "1234")
    @NotNull(message = "话题标签 ID不能为空")
    private Long hashtagId;

    @Schema(description = "标题", required = true, example = "手机售卖")
    @NotNull(message = "标题不能为空")
    private String title;

    @Schema(description = "内容", required = true, example = "iphone手机99新")
    @NotNull(message = "内容不能为空")
    private String content;

    @Schema(description = "图片的数组", required = false)
    private List<String> picUrls;

    @Schema(description = "评论数", required = true, example = "21")
    private Integer commentCount;

    @Schema(description = "点赞数", required = true, example = "99")
    private Integer likeCount;
}
