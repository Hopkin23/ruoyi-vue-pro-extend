package cn.iocoder.yudao.module.sns.enums;

import cn.iocoder.yudao.framework.common.core.IntArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 常用统计字段加减值
 */
@Getter
@AllArgsConstructor
public enum CountChangeValueConstants implements IntArrayValuable {

    INC(1),
    DEC(-1);

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(CountChangeValueConstants::getValue).toArray();

    /**
     * 变化数值
     */
    private final Integer value;

    @Override
    public int[] array() {
        return ARRAYS;
    }

}
