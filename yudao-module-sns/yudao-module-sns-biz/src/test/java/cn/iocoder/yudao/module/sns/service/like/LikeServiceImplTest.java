package cn.iocoder.yudao.module.sns.service.like;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.sns.controller.app.like.vo.*;
import cn.iocoder.yudao.module.sns.dal.dataobject.like.LikeDO;
import cn.iocoder.yudao.module.sns.dal.mysql.like.LikeMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.springframework.context.annotation.Import;
import java.util.*;

import static cn.iocoder.yudao.module.sns.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.LocalDateTimeUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static org.junit.jupiter.api.Assertions.*;

/**
* {@link LikeServiceImpl} 的单元测试类
*
* @author 芋道源码
*/
@Import(LikeServiceImpl.class)
public class LikeServiceImplTest extends BaseDbUnitTest {

    @Resource
    private LikeServiceImpl likeService;

    @Resource
    private LikeMapper likeMapper;

    @Test
    public void testCreateLike_success() {
        // 准备参数
        LikeCreateReqVO reqVO = randomPojo(LikeCreateReqVO.class);

        // 调用
        Long likeId = likeService.createLike(reqVO);
        // 断言
        assertNotNull(likeId);
        // 校验记录的属性是否正确
        LikeDO like = likeMapper.selectById(likeId);
        assertPojoEquals(reqVO, like);
    }

    @Test
    public void testUpdateLike_success() {
        // mock 数据
        LikeDO dbLike = randomPojo(LikeDO.class);
        likeMapper.insert(dbLike);// @Sql: 先插入出一条存在的数据
        // 准备参数
        LikeUpdateReqVO reqVO = randomPojo(LikeUpdateReqVO.class, o -> {
            o.setId(dbLike.getId()); // 设置更新的 ID
        });

        // 调用
        likeService.updateLike(reqVO);
        // 校验是否更新正确
        LikeDO like = likeMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, like);
    }

    @Test
    public void testUpdateLike_notExists() {
        // 准备参数
        LikeUpdateReqVO reqVO = randomPojo(LikeUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> likeService.updateLike(reqVO), LIKE_NOT_EXISTS);
    }

    @Test
    public void testDeleteLike_success() {
        // mock 数据
        LikeDO dbLike = randomPojo(LikeDO.class);
        likeMapper.insert(dbLike);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbLike.getId();

        // 调用
        likeService.deleteLike(id);
       // 校验数据不存在了
       assertNull(likeMapper.selectById(id));
    }

    @Test
    public void testDeleteLike_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> likeService.deleteLike(id), LIKE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetLikePage() {
       // mock 数据
       LikeDO dbLike = randomPojo(LikeDO.class, o -> { // 等会查询到
           o.setUserId(null);
           o.setMarkType(null);
           o.setLikeType(null);
           o.setLikeId(null);
           o.setCreateTime(null);
       });
       likeMapper.insert(dbLike);
       // 测试 userId 不匹配
       likeMapper.insert(cloneIgnoreId(dbLike, o -> o.setUserId(null)));
       // 测试 markType 不匹配
       likeMapper.insert(cloneIgnoreId(dbLike, o -> o.setMarkType(null)));
       // 测试 likeType 不匹配
       likeMapper.insert(cloneIgnoreId(dbLike, o -> o.setLikeType(null)));
       // 测试 likeId 不匹配
       likeMapper.insert(cloneIgnoreId(dbLike, o -> o.setLikeId(null)));
       // 测试 createTime 不匹配
       likeMapper.insert(cloneIgnoreId(dbLike, o -> o.setCreateTime(null)));
       // 准备参数
       LikePageReqVO reqVO = new LikePageReqVO();
       reqVO.setUserId(null);
       reqVO.setMarkType(null);
       reqVO.setLikeType(null);
       reqVO.setLikeId(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<LikeDO> pageResult = likeService.getLikePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbLike, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetLikeList() {
       // mock 数据
       LikeDO dbLike = randomPojo(LikeDO.class, o -> { // 等会查询到
           o.setUserId(null);
           o.setMarkType(null);
           o.setLikeType(null);
           o.setLikeId(null);
           o.setCreateTime(null);
       });
       likeMapper.insert(dbLike);
       // 测试 userId 不匹配
       likeMapper.insert(cloneIgnoreId(dbLike, o -> o.setUserId(null)));
       // 测试 markType 不匹配
       likeMapper.insert(cloneIgnoreId(dbLike, o -> o.setMarkType(null)));
       // 测试 likeType 不匹配
       likeMapper.insert(cloneIgnoreId(dbLike, o -> o.setLikeType(null)));
       // 测试 likeId 不匹配
       likeMapper.insert(cloneIgnoreId(dbLike, o -> o.setLikeId(null)));
       // 测试 createTime 不匹配
       likeMapper.insert(cloneIgnoreId(dbLike, o -> o.setCreateTime(null)));
       // 准备参数
       LikeExportReqVO reqVO = new LikeExportReqVO();
       reqVO.setUserId(null);
       reqVO.setMarkType(null);
       reqVO.setLikeType(null);
       reqVO.setLikeId(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<LikeDO> list = likeService.getLikeList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbLike, list.get(0));
    }

}
