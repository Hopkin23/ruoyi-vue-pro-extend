package cn.iocoder.yudao.module.sns.enums;

import cn.iocoder.yudao.framework.common.core.IntArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 点赞业务类型
 */
@Getter
@AllArgsConstructor
public enum LikeTypeConstants implements IntArrayValuable {

    /**
     * 帖子点赞
     */
    PUBLISH(1),

    /**
     * 评论点赞
     */
    COMMENT(2);

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(LikeTypeConstants::getType).toArray();

    /**
     * 类型值
     */
    private final Integer type;

    @Override
    public int[] array() {
        return ARRAYS;
    }

}
