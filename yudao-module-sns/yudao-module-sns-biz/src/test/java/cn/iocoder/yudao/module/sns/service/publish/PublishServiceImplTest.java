package cn.iocoder.yudao.module.sns.service.publish;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.sns.controller.app.publish.vo.*;
import cn.iocoder.yudao.module.sns.dal.dataobject.publish.PublishDO;
import cn.iocoder.yudao.module.sns.dal.mysql.publish.PublishMapper;
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
* {@link PublishServiceImpl} 的单元测试类
*
* @author 芋道源码
*/
@Import(PublishServiceImpl.class)
public class PublishServiceImplTest extends BaseDbUnitTest {

    @Resource
    private PublishServiceImpl publishService;

    @Resource
    private PublishMapper publishMapper;

    @Test
    public void testCreatePublish_success() {
        // 准备参数
        PublishCreateReqVO reqVO = randomPojo(PublishCreateReqVO.class);

        // 调用
        Long publishId = publishService.createPublish(reqVO);
        // 断言
        assertNotNull(publishId);
        // 校验记录的属性是否正确
        PublishDO publish = publishMapper.selectById(publishId);
        assertPojoEquals(reqVO, publish);
    }

    @Test
    public void testUpdatePublish_success() {
        // mock 数据
        PublishDO dbPublish = randomPojo(PublishDO.class);
        publishMapper.insert(dbPublish);// @Sql: 先插入出一条存在的数据
        // 准备参数
        PublishUpdateReqVO reqVO = randomPojo(PublishUpdateReqVO.class, o -> {
            o.setId(dbPublish.getId()); // 设置更新的 ID
        });

        // 调用
        publishService.updatePublish(reqVO);
        // 校验是否更新正确
        PublishDO publish = publishMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, publish);
    }

    @Test
    public void testUpdatePublish_notExists() {
        // 准备参数
        PublishUpdateReqVO reqVO = randomPojo(PublishUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> publishService.updatePublish(reqVO), PUBLISH_NOT_EXISTS);
    }

    @Test
    public void testDeletePublish_success() {
        // mock 数据
        PublishDO dbPublish = randomPojo(PublishDO.class);
        publishMapper.insert(dbPublish);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbPublish.getId();

        // 调用
        publishService.deletePublish(id);
       // 校验数据不存在了
       assertNull(publishMapper.selectById(id));
    }

    @Test
    public void testDeletePublish_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> publishService.deletePublish(id), PUBLISH_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetPublishPage() {
       // mock 数据
       PublishDO dbPublish = randomPojo(PublishDO.class, o -> { // 等会查询到
           o.setUserId(null);
           o.setTitle(null);
           o.setContent(null);
           o.setCreateTime(null);
       });
       publishMapper.insert(dbPublish);
       // 测试 userId 不匹配
       publishMapper.insert(cloneIgnoreId(dbPublish, o -> o.setUserId(null)));
       // 测试 title 不匹配
       publishMapper.insert(cloneIgnoreId(dbPublish, o -> o.setTitle(null)));
       // 测试 content 不匹配
       publishMapper.insert(cloneIgnoreId(dbPublish, o -> o.setContent(null)));
       // 测试 createTime 不匹配
       publishMapper.insert(cloneIgnoreId(dbPublish, o -> o.setCreateTime(null)));
       // 准备参数
       PublishPageReqVO reqVO = new PublishPageReqVO();
       reqVO.setUserId(null);
       reqVO.setTitle(null);
       reqVO.setContent(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<PublishRespVO> pageResult = publishService.getPublishPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbPublish, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetPublishList() {
       // mock 数据
       PublishDO dbPublish = randomPojo(PublishDO.class, o -> { // 等会查询到
           o.setUserId(null);
           o.setTitle(null);
           o.setContent(null);
           o.setCreateTime(null);
       });
       publishMapper.insert(dbPublish);
       // 测试 userId 不匹配
       publishMapper.insert(cloneIgnoreId(dbPublish, o -> o.setUserId(null)));
       // 测试 title 不匹配
       publishMapper.insert(cloneIgnoreId(dbPublish, o -> o.setTitle(null)));
       // 测试 content 不匹配
       publishMapper.insert(cloneIgnoreId(dbPublish, o -> o.setContent(null)));
       // 测试 createTime 不匹配
       publishMapper.insert(cloneIgnoreId(dbPublish, o -> o.setCreateTime(null)));
       // 准备参数
       PublishExportReqVO reqVO = new PublishExportReqVO();
       reqVO.setUserId(null);
       reqVO.setTitle(null);
       reqVO.setContent(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<PublishDO> list = publishService.getPublishList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbPublish, list.get(0));
    }

}
