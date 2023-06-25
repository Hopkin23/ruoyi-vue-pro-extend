package cn.iocoder.yudao.module.sns.controller.app.like.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "管理后台 - 点赞创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LikeCreateReqVO extends LikeBaseVO {

}
