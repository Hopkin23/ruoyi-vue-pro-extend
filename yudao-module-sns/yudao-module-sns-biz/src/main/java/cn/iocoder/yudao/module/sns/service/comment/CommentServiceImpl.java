package cn.iocoder.yudao.module.sns.service.comment;

import cn.iocoder.yudao.module.sns.enums.CountChangeValueConstants;
import cn.iocoder.yudao.module.sns.service.publish.PublishService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import java.util.*;
import cn.iocoder.yudao.module.sns.controller.app.comment.vo.*;
import cn.iocoder.yudao.module.sns.dal.dataobject.comment.CommentDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.sns.convert.comment.CommentConvert;
import cn.iocoder.yudao.module.sns.dal.mysql.comment.CommentMapper;
import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.sns.enums.ErrorCodeConstants.*;

/**
 * 评论 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private PublishService publishService;

    @Override
    public Long createComment(CommentCreateReqVO createReqVO) {
        // 插入
        CommentDO comment = CommentConvert.INSTANCE.convert(createReqVO);
        commentMapper.insert(comment);

        // todo 帖子评论数统计
        publishService.updateCommentCount(createReqVO.getPublishId(), CountChangeValueConstants.INC.getValue());
        // 返回
        return comment.getId();
    }

    @Override
    public void updateComment(CommentUpdateReqVO updateReqVO) {
        // 校验存在
        validateCommentExists(updateReqVO.getId());
        // 更新
        CommentDO updateObj = CommentConvert.INSTANCE.convert(updateReqVO);
        commentMapper.updateById(updateObj);
    }

    @Override
    public void deleteComment(Long id) {
        // 校验存在
        validateCommentExists(id);
        // 删除
        commentMapper.deleteById(id);
        // todo 帖子评论数统计
        CommentDO commentDO = commentMapper.selectById(id);
        publishService.updateCommentCount(commentDO.getPublishId(), CountChangeValueConstants.DEC.getValue());
    }

    private void validateCommentExists(Long id) {
        if (commentMapper.selectById(id) == null) {
            throw exception(COMMENT_NOT_EXISTS);
        }
    }

    @Override
    public CommentDO getComment(Long id) {
        return commentMapper.selectById(id);
    }

    @Override
    public List<CommentDO> getCommentList(Collection<Long> ids) {
        return commentMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<CommentDO> getCommentPage(CommentPageReqVO pageReqVO) {
        return commentMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CommentDO> getCommentList(CommentExportReqVO exportReqVO) {
        return commentMapper.selectList(exportReqVO);
    }

}
