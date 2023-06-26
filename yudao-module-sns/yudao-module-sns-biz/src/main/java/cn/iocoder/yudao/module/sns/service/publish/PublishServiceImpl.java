package cn.iocoder.yudao.module.sns.service.publish;

import cn.iocoder.yudao.module.sns.dal.dataobject.hashtag.HashtagDO;
import cn.iocoder.yudao.module.sns.dal.dataobject.like.LikeDO;
import cn.iocoder.yudao.module.sns.enums.LikeMarkTypeConstants;
import cn.iocoder.yudao.module.sns.enums.LikeTypeConstants;
import cn.iocoder.yudao.module.sns.service.hashtag.HashtagService;
import cn.iocoder.yudao.module.sns.service.like.LikeService;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import java.util.*;
import java.util.stream.Collectors;
import cn.iocoder.yudao.module.sns.controller.app.publish.vo.*;
import cn.iocoder.yudao.module.sns.dal.dataobject.publish.PublishDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.sns.convert.publish.PublishConvert;
import cn.iocoder.yudao.module.sns.dal.mysql.publish.PublishMapper;
import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.sns.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.USER_NOT_EXISTS;

/**
 * 帖子 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PublishServiceImpl implements PublishService {

    @Resource
    private PublishMapper publishMapper;

    @Resource
    private AdminUserApi adminUserApi;

    @Resource
    private HashtagService hashtagService;

    @Resource
    private LikeService likeService;

    @Override
    public Long createPublish(PublishCreateReqVO createReqVO) {
        // 插入
        PublishDO publish = PublishConvert.INSTANCE.convert(createReqVO);
        publishMapper.insert(publish);
        // 返回
        return publish.getId();
    }

    @Override
    public void updatePublish(PublishUpdateReqVO updateReqVO) {
        // 校验存在
        validatePublishExists(updateReqVO.getId());
        // 更新
        PublishDO updateObj = PublishConvert.INSTANCE.convert(updateReqVO);
        publishMapper.updateById(updateObj);
    }

    @Override
    public void deletePublish(Long id) {
        // 校验存在
        validatePublishExists(id);
        // 删除
        publishMapper.deleteById(id);
    }

    private void validatePublishExists(Long id) {
        if (publishMapper.selectById(id) == null) {
            throw exception(PUBLISH_NOT_EXISTS);
        }
    }

    @Override
    public PublishRespVO getPublish(Long id) {
        PublishDO publishDO = publishMapper.selectById(id);
        PublishRespVO publishRespVO = PublishConvert.INSTANCE.convert(publishDO);
        Map<Long, AdminUserRespDTO> userInfoMap = getUserInfoMap(Collections.singletonList(publishDO));
        Map<Long, HashtagDO> hashtagInfoMap = getHashtagInfoMap(Collections.singletonList(publishDO));
        convertVO(publishRespVO, userInfoMap, hashtagInfoMap);
        // 点赞信息
        updateIsLikeRespVo(publishRespVO, publishDO);
        return publishRespVO;
    }

    @Override
    public List<PublishDO> getPublishList(Collection<Long> ids) {
        return publishMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<PublishRespVO> getPublishPage(PublishPageReqVO pageReqVO) {
        PageResult<PublishDO> publishDOPageResult = publishMapper.selectPage(pageReqVO);
        List<PublishDO> publishDOList = publishDOPageResult.getList();

        // 转换返回
        Map<Long, AdminUserRespDTO> userInfoMap = getUserInfoMap(publishDOList);
        Map<Long, HashtagDO> hashtagInfoMap = getHashtagInfoMap(publishDOList);
        PageResult<PublishRespVO> publishRespVOPageResult = convertPageVO(publishDOPageResult, userInfoMap, hashtagInfoMap);
        // 是否点赞
        updateIsLikeRespVo(publishRespVOPageResult, publishDOList);
        return publishRespVOPageResult;
    }

    /**
     * 是否点赞处理（单条数据处理）
     * @param publishRespVO 返回的帖子数据
     * @param publishDO 帖子
     */
    private void updateIsLikeRespVo(PublishRespVO publishRespVO, PublishDO publishDO) {
        // 获取点赞记录
        List<LikeDO> likeList = likeService.getLikeList(LikeTypeConstants.PUBLISH, LikeMarkTypeConstants.LIKE,
                Collections.singletonList(publishDO.getId()));
        if (likeList.size() > 1) {
            throw exception(LIKE_NUMBER_ERROR);
        }

        Set<Long> likedPublishIdSet = likeList.stream().map(LikeDO::getLikeId).collect(Collectors.toSet());
        publishRespVO.setIsLike(likedPublishIdSet.contains(likeList.get(0).getId()));
    }

    /**
     * 是否点赞处理（分页数据处理）
     * @param publishRespVOPageResult 分页列表
     * @param publishDOList 帖子列表
     */
    private void updateIsLikeRespVo(PageResult<PublishRespVO> publishRespVOPageResult, List<PublishDO> publishDOList) {
        // 获取点赞记录
        List<Long> publishIdList = publishDOList.stream().map(PublishDO::getId).distinct().collect(Collectors.toList());
        List<LikeDO> likeList = likeService.getLikeList(LikeTypeConstants.PUBLISH, LikeMarkTypeConstants.LIKE, publishIdList);

        Set<Long> likedPublishIdSet = likeList.stream().map(LikeDO::getLikeId).collect(Collectors.toSet());
        // 信息封装
        publishRespVOPageResult.getList().forEach((item) -> {
            boolean present = likedPublishIdSet.contains(item.getUserId());
            item.setIsLike(present);
        });
    }

    @Override
    public List<PublishDO> getPublishList(PublishExportReqVO exportReqVO) {
        return publishMapper.selectList(exportReqVO);
    }

    @Override
    public Boolean updateCommentCount(Long publishId, Integer value) {
        return publishMapper.updateCommentCount(publishId, value) > 0;
    }

    @Override
    public Boolean updateLikeCount(LikeTypeConstants likeTypeConstants,
                                   LikeMarkTypeConstants likeMarkTypeConstants,
                                   Long publishId, Integer value) {
        int count = 0;
        // todo 此处要根据业务扩充, 不同的情况可能修改不同的统计字段
        if (LikeTypeConstants.PUBLISH.equals(likeTypeConstants) && LikeMarkTypeConstants.LIKE.equals(likeMarkTypeConstants)) {
            count = publishMapper.updateLikeCount(publishId, value);
        }
        return count > 0;
    }

    /**
     * 数据处理，将分页结果数据整合其他传入数据
     * @param publishDOPageResult 分页基础数据
     * @param userInfoMap 用户信息数据
     * @param hashtagInfoMap 话题标签数据
     * @return 处理好的分页数据
     */
    private PageResult<PublishRespVO> convertPageVO(PageResult<PublishDO> publishDOPageResult, Map<Long, AdminUserRespDTO> userInfoMap, Map<Long, HashtagDO> hashtagInfoMap) {
        // DO VO转换
        PageResult<PublishRespVO> publishRespVOPageResult = PublishConvert.INSTANCE.convertPage(publishDOPageResult);
        // 其他信息设置
        publishRespVOPageResult.getList().forEach(p -> {
            convertVO(p, userInfoMap, hashtagInfoMap);
        });
        return publishRespVOPageResult;
    }

    /**
     * 单个PublishRespVO的数据转换保存
     * @param publishRespVO 帖子返回数据
     * @param userInfoMap 用户信息数据
     * @param hashtagInfoMap 话题标签数据
     */
    private void convertVO(PublishRespVO publishRespVO, Map<Long, AdminUserRespDTO> userInfoMap, Map<Long, HashtagDO> hashtagInfoMap) {
        // 用戶信息
        AdminUserRespDTO adminUserRespDTO = Optional.ofNullable(userInfoMap.get(publishRespVO.getUserId()))
                .orElseThrow(() -> exception(USER_NOT_EXISTS));
        PublishUserRespVO publishUserRespVO = new PublishUserRespVO();
        BeanUtils.copyProperties(adminUserRespDTO, publishUserRespVO);
        publishRespVO.setUser(publishUserRespVO);

        // 话题标签信息
        HashtagDO hashtagDO = Optional.ofNullable(hashtagInfoMap.get(publishRespVO.getHashtagId()))
                .orElseThrow(() -> exception(HASHTAG_NOT_EXISTS));
        PublishHashtagRespVO publishHashtagRespVO = new PublishHashtagRespVO();
        BeanUtils.copyProperties(hashtagDO, publishHashtagRespVO);
        publishRespVO.setHashtag(publishHashtagRespVO);
    }

    /**
     * 根据用户ID获取用户信息
     * @param publishDOList 帖子信息列表
     * @return 用户信息Map
     */
    private Map<Long, AdminUserRespDTO>  getUserInfoMap(List<PublishDO> publishDOList) {
        List<Long> userIdList = publishDOList.stream().map(PublishDO::getUserId).distinct().collect(Collectors.toList());
        List<AdminUserRespDTO> userList = adminUserApi.getUserList(userIdList);
        return userList.stream().collect(Collectors.toMap(AdminUserRespDTO::getId, a -> a, (v1, v2) -> v1));
    }

    /**
     * 根据话题标签ID获取话题标签信息
     * @param publishDOList 帖子信息列表
     * @return 话题标签信息Map
     */
    private Map<Long, HashtagDO> getHashtagInfoMap(List<PublishDO> publishDOList) {
        List<Long> hashtagIdList = publishDOList.stream().map(PublishDO::getHashtagId).distinct().collect(Collectors.toList());
        List<HashtagDO> hashtagList = hashtagService.getHashtagList(hashtagIdList);
        return hashtagList.stream().collect(Collectors.toMap(HashtagDO::getId, a -> a, (v1, v2) -> v1));
    }
}
