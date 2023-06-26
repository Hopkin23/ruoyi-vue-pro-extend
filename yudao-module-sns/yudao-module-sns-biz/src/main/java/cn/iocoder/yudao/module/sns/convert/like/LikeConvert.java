package cn.iocoder.yudao.module.sns.convert.like;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.sns.controller.app.like.vo.*;
import cn.iocoder.yudao.module.sns.dal.dataobject.like.LikeDO;

/**
 * 点赞 Convert
 */
@Mapper
public interface LikeConvert {

    LikeConvert INSTANCE = Mappers.getMapper(LikeConvert.class);

    LikeDO convert(LikeCreateReqVO bean);

    LikeDO convert(LikeUpdateReqVO bean);

    LikeRespVO convert(LikeDO bean);

    List<LikeRespVO> convertList(List<LikeDO> list);

    PageResult<LikeRespVO> convertPage(PageResult<LikeDO> page);

    List<LikeExcelVO> convertList02(List<LikeDO> list);

}
