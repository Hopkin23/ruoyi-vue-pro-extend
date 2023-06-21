package cn.iocoder.yudao.module.sns.convert.comment;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.sns.controller.admin.comment.vo.*;
import cn.iocoder.yudao.module.sns.dal.dataobject.comment.CommentDO;

/**
 * 评论 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface CommentConvert {

    CommentConvert INSTANCE = Mappers.getMapper(CommentConvert.class);

    CommentDO convert(CommentCreateReqVO bean);

    CommentDO convert(CommentUpdateReqVO bean);

    CommentRespVO convert(CommentDO bean);

    List<CommentRespVO> convertList(List<CommentDO> list);

    PageResult<CommentRespVO> convertPage(PageResult<CommentDO> page);

    List<CommentExcelVO> convertList02(List<CommentDO> list);

}
