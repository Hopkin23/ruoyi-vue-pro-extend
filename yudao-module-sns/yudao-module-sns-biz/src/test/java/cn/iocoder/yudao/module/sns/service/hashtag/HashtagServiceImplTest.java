package cn.iocoder.yudao.module.sns.service.hashtag;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.sns.controller.admin.hashtag.vo.*;
import cn.iocoder.yudao.module.sns.dal.dataobject.hashtag.HashtagDO;
import cn.iocoder.yudao.module.sns.dal.mysql.hashtag.HashtagMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.sns.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.LocalDateTimeUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
* {@link HashtagServiceImpl} 的单元测试类
*
* @author 芋道源码
*/
@Import(HashtagServiceImpl.class)
public class HashtagServiceImplTest extends BaseDbUnitTest {

    @Resource
    private HashtagServiceImpl hashtagService;

    @Resource
    private HashtagMapper hashtagMapper;

    @Test
    public void testCreateHashtag_success() {
        // 准备参数
        HashtagCreateReqVO reqVO = randomPojo(HashtagCreateReqVO.class);

        // 调用
        Long hashtagId = hashtagService.createHashtag(reqVO);
        // 断言
        assertNotNull(hashtagId);
        // 校验记录的属性是否正确
        HashtagDO hashtag = hashtagMapper.selectById(hashtagId);
        assertPojoEquals(reqVO, hashtag);
    }

    @Test
    public void testUpdateHashtag_success() {
        // mock 数据
        HashtagDO dbHashtag = randomPojo(HashtagDO.class);
        hashtagMapper.insert(dbHashtag);// @Sql: 先插入出一条存在的数据
        // 准备参数
        HashtagUpdateReqVO reqVO = randomPojo(HashtagUpdateReqVO.class, o -> {
            o.setId(dbHashtag.getId()); // 设置更新的 ID
        });

        // 调用
        hashtagService.updateHashtag(reqVO);
        // 校验是否更新正确
        HashtagDO hashtag = hashtagMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, hashtag);
    }

    @Test
    public void testUpdateHashtag_notExists() {
        // 准备参数
        HashtagUpdateReqVO reqVO = randomPojo(HashtagUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> hashtagService.updateHashtag(reqVO), HASHTAG_NOT_EXISTS);
    }

    @Test
    public void testDeleteHashtag_success() {
        // mock 数据
        HashtagDO dbHashtag = randomPojo(HashtagDO.class);
        hashtagMapper.insert(dbHashtag);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbHashtag.getId();

        // 调用
        hashtagService.deleteHashtag(id);
       // 校验数据不存在了
       assertNull(hashtagMapper.selectById(id));
    }

    @Test
    public void testDeleteHashtag_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> hashtagService.deleteHashtag(id), HASHTAG_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetHashtagPage() {
       // mock 数据
       HashtagDO dbHashtag = randomPojo(HashtagDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setDescription(null);
           o.setCreateTime(null);
       });
       hashtagMapper.insert(dbHashtag);
       // 测试 name 不匹配
       hashtagMapper.insert(cloneIgnoreId(dbHashtag, o -> o.setName(null)));
       // 测试 description 不匹配
       hashtagMapper.insert(cloneIgnoreId(dbHashtag, o -> o.setDescription(null)));
       // 测试 createTime 不匹配
       hashtagMapper.insert(cloneIgnoreId(dbHashtag, o -> o.setCreateTime(null)));
       // 准备参数
       HashtagPageReqVO reqVO = new HashtagPageReqVO();
       reqVO.setName(null);
       reqVO.setDescription(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<HashtagDO> pageResult = hashtagService.getHashtagPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbHashtag, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetHashtagList() {
       // mock 数据
       HashtagDO dbHashtag = randomPojo(HashtagDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setDescription(null);
           o.setCreateTime(null);
       });
       hashtagMapper.insert(dbHashtag);
       // 测试 name 不匹配
       hashtagMapper.insert(cloneIgnoreId(dbHashtag, o -> o.setName(null)));
       // 测试 description 不匹配
       hashtagMapper.insert(cloneIgnoreId(dbHashtag, o -> o.setDescription(null)));
       // 测试 createTime 不匹配
       hashtagMapper.insert(cloneIgnoreId(dbHashtag, o -> o.setCreateTime(null)));
       // 准备参数
       HashtagExportReqVO reqVO = new HashtagExportReqVO();
       reqVO.setName(null);
       reqVO.setDescription(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<HashtagDO> list = hashtagService.getHashtagList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbHashtag, list.get(0));
    }

}
