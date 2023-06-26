package cn.iocoder.yudao.module.sns.service.hashtag;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.sns.controller.app.hashtag.vo.*;
import cn.iocoder.yudao.module.sns.dal.dataobject.hashtag.HashtagDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 话题标签 Service 接口
 */
public interface HashtagService {

    /**
     * 创建话题标签
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createHashtag(@Valid HashtagCreateReqVO createReqVO);

    /**
     * 更新话题标签
     *
     * @param updateReqVO 更新信息
     */
    void updateHashtag(@Valid HashtagUpdateReqVO updateReqVO);

    /**
     * 删除话题标签
     *
     * @param id 编号
     */
    void deleteHashtag(Long id);

    /**
     * 获得话题标签
     *
     * @param id 编号
     * @return 话题标签
     */
    HashtagDO getHashtag(Long id);

    /**
     * 获得话题标签列表
     *
     * @param ids 编号
     * @return 话题标签列表
     */
    List<HashtagDO> getHashtagList(Collection<Long> ids);

    /**
     * 获得话题标签分页
     *
     * @param pageReqVO 分页查询
     * @return 话题标签分页
     */
    PageResult<HashtagDO> getHashtagPage(HashtagPageReqVO pageReqVO);

    /**
     * 获得话题标签列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 话题标签列表
     */
    List<HashtagDO> getHashtagList(HashtagExportReqVO exportReqVO);

}
