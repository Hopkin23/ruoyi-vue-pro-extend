package cn.iocoder.yudao.module.sns.dal.mysql.publish;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.sns.dal.dataobject.publish.PublishDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.sns.controller.app.publish.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 帖子 Mapper
 */
@Mapper
public interface PublishMapper extends BaseMapperX<PublishDO> {

    default PageResult<PublishDO> selectPage(PublishPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PublishDO>()
                .eqIfPresent(PublishDO::getUserId, reqVO.getUserId())
                .likeIfPresent(PublishDO::getTitle, reqVO.getTitle())
                .likeIfPresent(PublishDO::getContent, reqVO.getContent())
                .betweenIfPresent(PublishDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PublishDO::getId));
    }

    default List<PublishDO> selectList(PublishExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<PublishDO>()
                .eqIfPresent(PublishDO::getUserId, reqVO.getUserId())
                .likeIfPresent(PublishDO::getTitle, reqVO.getTitle())
                .likeIfPresent(PublishDO::getContent, reqVO.getContent())
                .betweenIfPresent(PublishDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PublishDO::getId));
    }

    /**
     * 修改帖子的评论数量
     * @param publishId 帖子主键
     * @param value 变化值
     * @return 功修改记录数量
     */
    int updateCommentCount(@Param("publishId") Long publishId, @Param("value") Integer value);

    /**
     * 修改点赞的评论数量
     * @param publishId 帖子主键
     * @param value 变化值
     * @return 成功修改记录数量
     */
    int updateLikeCount(@Param("publishId") Long publishId, @Param("value") Integer value);
}
