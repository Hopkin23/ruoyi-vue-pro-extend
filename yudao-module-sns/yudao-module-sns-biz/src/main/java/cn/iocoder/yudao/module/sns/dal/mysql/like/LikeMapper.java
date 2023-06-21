package cn.iocoder.yudao.module.sns.dal.mysql.like;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.sns.dal.dataobject.like.LikeDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.sns.controller.admin.like.vo.*;

/**
 * 点赞 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface LikeMapper extends BaseMapperX<LikeDO> {

    default PageResult<LikeDO> selectPage(LikePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<LikeDO>()
                .eqIfPresent(LikeDO::getUserId, reqVO.getUserId())
                .eqIfPresent(LikeDO::getMarkType, reqVO.getMarkType())
                .eqIfPresent(LikeDO::getLikeType, reqVO.getLikeType())
                .eqIfPresent(LikeDO::getLikeId, reqVO.getLikeId())
                .betweenIfPresent(LikeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(LikeDO::getId));
    }

    default List<LikeDO> selectList(LikeExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<LikeDO>()
                .eqIfPresent(LikeDO::getUserId, reqVO.getUserId())
                .eqIfPresent(LikeDO::getMarkType, reqVO.getMarkType())
                .eqIfPresent(LikeDO::getLikeType, reqVO.getLikeType())
                .eqIfPresent(LikeDO::getLikeId, reqVO.getLikeId())
                .betweenIfPresent(LikeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(LikeDO::getId));
    }

}
