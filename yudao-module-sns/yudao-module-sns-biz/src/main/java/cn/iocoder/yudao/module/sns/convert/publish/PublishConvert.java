package cn.iocoder.yudao.module.sns.convert.publish;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.sns.controller.admin.publish.vo.*;
import cn.iocoder.yudao.module.sns.dal.dataobject.publish.PublishDO;

/**
 * 帖子 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface PublishConvert {

    PublishConvert INSTANCE = Mappers.getMapper(PublishConvert.class);

    PublishDO convert(PublishCreateReqVO bean);

    PublishDO convert(PublishUpdateReqVO bean);

    PublishRespVO convert(PublishDO bean);

    List<PublishRespVO> convertList(List<PublishDO> list);

    PageResult<PublishRespVO> convertPage(PageResult<PublishDO> page);

    List<PublishExcelVO> convertList02(List<PublishDO> list);

}
