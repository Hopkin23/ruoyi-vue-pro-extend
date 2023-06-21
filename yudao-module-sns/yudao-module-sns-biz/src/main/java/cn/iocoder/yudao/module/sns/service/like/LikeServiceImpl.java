package cn.iocoder.yudao.module.sns.service.like;

import cn.iocoder.yudao.module.sns.enums.CountChangeValueConstants;
import cn.iocoder.yudao.module.sns.enums.LikeMarkTypeConstants;
import cn.iocoder.yudao.module.sns.enums.LikeTypeConstants;
import cn.iocoder.yudao.module.sns.service.publish.PublishService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.sns.controller.admin.like.vo.*;
import cn.iocoder.yudao.module.sns.dal.dataobject.like.LikeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.sns.convert.like.LikeConvert;
import cn.iocoder.yudao.module.sns.dal.mysql.like.LikeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.sns.enums.ErrorCodeConstants.*;

/**
 * 点赞 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class LikeServiceImpl implements LikeService {

    @Resource
    private LikeMapper likeMapper;

    @Resource
    private PublishService publishService;

    @Override
    public Long createLike(LikeCreateReqVO createReqVO) {
        // 插入
        LikeDO like = LikeConvert.INSTANCE.convert(createReqVO);
        likeMapper.insert(like);
        // todo 点赞数统计
        publishService.updateLikeCount(LikeTypeConstants.PUBLISH,
                LikeMarkTypeConstants.LIKE,
                createReqVO.getLikeId(),
                CountChangeValueConstants.INC.getValue());
        // 返回
        return like.getId();
    }

    @Override
    public void updateLike(LikeUpdateReqVO updateReqVO) {
        // 校验存在
        validateLikeExists(updateReqVO.getId());
        // 更新
        LikeDO updateObj = LikeConvert.INSTANCE.convert(updateReqVO);
        likeMapper.updateById(updateObj);
    }

    @Override
    public void deleteLike(Long id) {
        // 校验存在
        validateLikeExists(id);
        // 删除
        likeMapper.deleteById(id);
    }

    private void validateLikeExists(Long id) {
        if (likeMapper.selectById(id) == null) {
            throw exception(LIKE_NOT_EXISTS);
        }
    }

    @Override
    public LikeDO getLike(Long id) {
        return likeMapper.selectById(id);
    }

    @Override
    public List<LikeDO> getLikeList(Collection<Long> ids) {
        return likeMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<LikeDO> getLikePage(LikePageReqVO pageReqVO) {
        return likeMapper.selectPage(pageReqVO);
    }

    @Override
    public List<LikeDO> getLikeList(LikeExportReqVO exportReqVO) {
        return likeMapper.selectList(exportReqVO);
    }

}
