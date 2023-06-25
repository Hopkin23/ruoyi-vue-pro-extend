package cn.iocoder.yudao.module.sns.service.hashtag;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.sns.controller.app.hashtag.vo.*;
import cn.iocoder.yudao.module.sns.dal.dataobject.hashtag.HashtagDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.sns.convert.hashtag.HashtagConvert;
import cn.iocoder.yudao.module.sns.dal.mysql.hashtag.HashtagMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.sns.enums.ErrorCodeConstants.*;

/**
 * 话题标签 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class HashtagServiceImpl implements HashtagService {

    @Resource
    private HashtagMapper hashtagMapper;

    @Override
    public Long createHashtag(HashtagCreateReqVO createReqVO) {
        // 插入
        HashtagDO hashtag = HashtagConvert.INSTANCE.convert(createReqVO);
        hashtagMapper.insert(hashtag);
        // 返回
        return hashtag.getId();
    }

    @Override
    public void updateHashtag(HashtagUpdateReqVO updateReqVO) {
        // 校验存在
        validateHashtagExists(updateReqVO.getId());
        // 更新
        HashtagDO updateObj = HashtagConvert.INSTANCE.convert(updateReqVO);
        hashtagMapper.updateById(updateObj);
    }

    @Override
    public void deleteHashtag(Long id) {
        // 校验存在
        validateHashtagExists(id);
        // 删除
        hashtagMapper.deleteById(id);
    }

    private void validateHashtagExists(Long id) {
        if (hashtagMapper.selectById(id) == null) {
            throw exception(HASHTAG_NOT_EXISTS);
        }
    }

    @Override
    public HashtagDO getHashtag(Long id) {
        return hashtagMapper.selectById(id);
    }

    @Override
    public List<HashtagDO> getHashtagList(Collection<Long> ids) {
        return hashtagMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<HashtagDO> getHashtagPage(HashtagPageReqVO pageReqVO) {
        return hashtagMapper.selectPage(pageReqVO);
    }

    @Override
    public List<HashtagDO> getHashtagList(HashtagExportReqVO exportReqVO) {
        return hashtagMapper.selectList(exportReqVO);
    }

}
