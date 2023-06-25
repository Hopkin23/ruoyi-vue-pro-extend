package cn.iocoder.yudao.module.sns.controller.app.hashtag.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "管理后台 - 话题标签创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class HashtagCreateReqVO extends HashtagBaseVO {

}
