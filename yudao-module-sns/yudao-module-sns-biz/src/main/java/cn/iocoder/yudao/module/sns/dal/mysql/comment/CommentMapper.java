package cn.iocoder.yudao.module.sns.dal.mysql.comment;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.sns.dal.dataobject.comment.CommentDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.sns.controller.app.comment.vo.*;

/**
 * 评论 Mapper
 */
@Mapper
public interface CommentMapper extends BaseMapperX<CommentDO> {

    default PageResult<CommentDO> selectPage(CommentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CommentDO>()
                .eqIfPresent(CommentDO::getPublishId, reqVO.getPublishId())
                .eqIfPresent(CommentDO::getParentId, reqVO.getParentId())
                .eqIfPresent(CommentDO::getTopParentId, reqVO.getTopParentId())
                .eqIfPresent(CommentDO::getUserId, reqVO.getUserId())
                .eqIfPresent(CommentDO::getContent, reqVO.getContent())
                .betweenIfPresent(CommentDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CommentDO::getId));
    }

    default List<CommentDO> selectList(CommentExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CommentDO>()
                .eqIfPresent(CommentDO::getPublishId, reqVO.getPublishId())
                .eqIfPresent(CommentDO::getParentId, reqVO.getParentId())
                .eqIfPresent(CommentDO::getTopParentId, reqVO.getTopParentId())
                .eqIfPresent(CommentDO::getUserId, reqVO.getUserId())
                .eqIfPresent(CommentDO::getContent, reqVO.getContent())
                .betweenIfPresent(CommentDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CommentDO::getId));
    }

}
