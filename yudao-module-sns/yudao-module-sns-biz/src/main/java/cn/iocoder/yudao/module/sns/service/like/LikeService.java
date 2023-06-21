package cn.iocoder.yudao.module.sns.service.like;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.sns.controller.admin.like.vo.*;
import cn.iocoder.yudao.module.sns.dal.dataobject.like.LikeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 点赞 Service 接口
 *
 * @author 芋道源码
 */
public interface LikeService {

    /**
     * 创建点赞
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createLike(@Valid LikeCreateReqVO createReqVO);

    /**
     * 更新点赞
     *
     * @param updateReqVO 更新信息
     */
    void updateLike(@Valid LikeUpdateReqVO updateReqVO);

    /**
     * 删除点赞
     *
     * @param id 编号
     */
    void deleteLike(Long id);

    /**
     * 获得点赞
     *
     * @param id 编号
     * @return 点赞
     */
    LikeDO getLike(Long id);

    /**
     * 获得点赞列表
     *
     * @param ids 编号
     * @return 点赞列表
     */
    List<LikeDO> getLikeList(Collection<Long> ids);

    /**
     * 获得点赞分页
     *
     * @param pageReqVO 分页查询
     * @return 点赞分页
     */
    PageResult<LikeDO> getLikePage(LikePageReqVO pageReqVO);

    /**
     * 获得点赞列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 点赞列表
     */
    List<LikeDO> getLikeList(LikeExportReqVO exportReqVO);

}
