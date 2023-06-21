package cn.iocoder.yudao.module.sns.enums;

import cn.iocoder.yudao.framework.common.core.IntArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 点赞操作类型
 */
@Getter
@AllArgsConstructor
public enum LikeMarkTypeConstants implements IntArrayValuable {

    /**
     * 正向点赞
     */
    LIKE(1),

    /**
     * 反向踩
     */
    DISLIKE(2);

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(LikeMarkTypeConstants::getType).toArray();

    /**
     * 类型值
     */
    private final Integer type;

    @Override
    public int[] array() {
        return ARRAYS;
    }

}
