package cn.iocoder.yudao.module.sns.controller.admin.hashtag.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 话题标签创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class HashtagCreateReqVO extends HashtagBaseVO {

}
