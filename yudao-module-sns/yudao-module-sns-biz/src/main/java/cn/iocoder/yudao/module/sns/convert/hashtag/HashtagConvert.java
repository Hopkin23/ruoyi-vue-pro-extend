package cn.iocoder.yudao.module.sns.convert.hashtag;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.sns.controller.app.hashtag.vo.*;
import cn.iocoder.yudao.module.sns.dal.dataobject.hashtag.HashtagDO;

/**
 * 话题标签 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface HashtagConvert {

    HashtagConvert INSTANCE = Mappers.getMapper(HashtagConvert.class);

    HashtagDO convert(HashtagCreateReqVO bean);

    HashtagDO convert(HashtagUpdateReqVO bean);

    HashtagRespVO convert(HashtagDO bean);

    List<HashtagRespVO> convertList(List<HashtagDO> list);

    PageResult<HashtagRespVO> convertPage(PageResult<HashtagDO> page);

    List<HashtagExcelVO> convertList02(List<HashtagDO> list);

}
