package cn.iocoder.yudao.module.sns.service.publish;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.sns.controller.app.publish.vo.*;
import cn.iocoder.yudao.module.sns.dal.dataobject.publish.PublishDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.sns.enums.LikeMarkTypeConstants;
import cn.iocoder.yudao.module.sns.enums.LikeTypeConstants;

/**
 * 帖子 Service 接口
 *
 * @author 芋道源码
 */
public interface PublishService {

    /**
     * 创建帖子
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPublish(@Valid PublishCreateReqVO createReqVO);

    /**
     * 更新帖子
     *
     * @param updateReqVO 更新信息
     */
    void updatePublish(@Valid PublishUpdateReqVO updateReqVO);

    /**
     * 删除帖子
     *
     * @param id 编号
     */
    void deletePublish(Long id);

    /**
     * 获得帖子
     *
     * @param id 编号
     * @return 帖子
     */
    PublishRespVO getPublish(Long id);

    /**
     * 获得帖子列表
     *
     * @param ids 编号
     * @return 帖子列表
     */
    List<PublishDO> getPublishList(Collection<Long> ids);

    /**
     * 获得帖子分页
     *
     * @param pageReqVO 分页查询
     * @return 帖子分页
     */
    PageResult<PublishRespVO> getPublishPage(PublishPageReqVO pageReqVO);

    /**
     * 获得帖子列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 帖子列表
     */
    List<PublishDO> getPublishList(PublishExportReqVO exportReqVO);

    /**
     * 修改帖子的评论数量
     * @param publishId 帖子主键
     * @param value 变化值
     * @return 成功或失败
     */
    Boolean updateCommentCount(Long publishId, Integer value);

    /**
     * 修改点赞的评论数量
     * @param likeTypeConstants 点赞类型
     * @param publishId 帖子主键
     * @param value 变化值
     * @return 成功或失败
     */
    Boolean updateLikeCount(LikeTypeConstants likeTypeConstants, LikeMarkTypeConstants likeMarkTypeConstants, Long publishId, Integer value);
}
