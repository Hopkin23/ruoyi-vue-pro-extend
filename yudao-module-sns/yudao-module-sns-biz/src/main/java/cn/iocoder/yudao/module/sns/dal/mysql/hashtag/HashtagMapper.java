package cn.iocoder.yudao.module.sns.dal.mysql.hashtag;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.sns.dal.dataobject.hashtag.HashtagDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.sns.controller.app.hashtag.vo.*;

/**
 * 话题标签 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface HashtagMapper extends BaseMapperX<HashtagDO> {

    default PageResult<HashtagDO> selectPage(HashtagPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<HashtagDO>()
                .likeIfPresent(HashtagDO::getName, reqVO.getName())
                .eqIfPresent(HashtagDO::getDescription, reqVO.getDescription())
                .betweenIfPresent(HashtagDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(HashtagDO::getId));
    }

    default List<HashtagDO> selectList(HashtagExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<HashtagDO>()
                .likeIfPresent(HashtagDO::getName, reqVO.getName())
                .eqIfPresent(HashtagDO::getDescription, reqVO.getDescription())
                .betweenIfPresent(HashtagDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(HashtagDO::getId));
    }

}
