package cn.iocoder.yudao.module.sns.service.comment;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.sns.controller.admin.comment.vo.*;
import cn.iocoder.yudao.module.sns.dal.dataobject.comment.CommentDO;
import cn.iocoder.yudao.module.sns.dal.mysql.comment.CommentMapper;
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
* {@link CommentServiceImpl} 的单元测试类
*
* @author 芋道源码
*/
@Import(CommentServiceImpl.class)
public class CommentServiceImplTest extends BaseDbUnitTest {

    @Resource
    private CommentServiceImpl commentService;

    @Resource
    private CommentMapper commentMapper;

    @Test
    public void testCreateComment_success() {
        // 准备参数
        CommentCreateReqVO reqVO = randomPojo(CommentCreateReqVO.class);

        // 调用
        Long commentId = commentService.createComment(reqVO);
        // 断言
        assertNotNull(commentId);
        // 校验记录的属性是否正确
        CommentDO comment = commentMapper.selectById(commentId);
        assertPojoEquals(reqVO, comment);
    }

    @Test
    public void testUpdateComment_success() {
        // mock 数据
        CommentDO dbComment = randomPojo(CommentDO.class);
        commentMapper.insert(dbComment);// @Sql: 先插入出一条存在的数据
        // 准备参数
        CommentUpdateReqVO reqVO = randomPojo(CommentUpdateReqVO.class, o -> {
            o.setId(dbComment.getId()); // 设置更新的 ID
        });

        // 调用
        commentService.updateComment(reqVO);
        // 校验是否更新正确
        CommentDO comment = commentMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, comment);
    }

    @Test
    public void testUpdateComment_notExists() {
        // 准备参数
        CommentUpdateReqVO reqVO = randomPojo(CommentUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> commentService.updateComment(reqVO), COMMENT_NOT_EXISTS);
    }

    @Test
    public void testDeleteComment_success() {
        // mock 数据
        CommentDO dbComment = randomPojo(CommentDO.class);
        commentMapper.insert(dbComment);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbComment.getId();

        // 调用
        commentService.deleteComment(id);
       // 校验数据不存在了
       assertNull(commentMapper.selectById(id));
    }

    @Test
    public void testDeleteComment_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> commentService.deleteComment(id), COMMENT_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCommentPage() {
       // mock 数据
       CommentDO dbComment = randomPojo(CommentDO.class, o -> { // 等会查询到
           o.setPublishId(null);
           o.setParentId(null);
           o.setTopParentId(null);
           o.setUserId(null);
           o.setContent(null);
           o.setCreateTime(null);
       });
       commentMapper.insert(dbComment);
       // 测试 publishId 不匹配
       commentMapper.insert(cloneIgnoreId(dbComment, o -> o.setPublishId(null)));
       // 测试 parentId 不匹配
       commentMapper.insert(cloneIgnoreId(dbComment, o -> o.setParentId(null)));
       // 测试 topParentId 不匹配
       commentMapper.insert(cloneIgnoreId(dbComment, o -> o.setTopParentId(null)));
       // 测试 userId 不匹配
       commentMapper.insert(cloneIgnoreId(dbComment, o -> o.setUserId(null)));
       // 测试 content 不匹配
       commentMapper.insert(cloneIgnoreId(dbComment, o -> o.setContent(null)));
       // 测试 createTime 不匹配
       commentMapper.insert(cloneIgnoreId(dbComment, o -> o.setCreateTime(null)));
       // 准备参数
       CommentPageReqVO reqVO = new CommentPageReqVO();
       reqVO.setPublishId(null);
       reqVO.setParentId(null);
       reqVO.setTopParentId(null);
       reqVO.setUserId(null);
       reqVO.setContent(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<CommentDO> pageResult = commentService.getCommentPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbComment, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCommentList() {
       // mock 数据
       CommentDO dbComment = randomPojo(CommentDO.class, o -> { // 等会查询到
           o.setPublishId(null);
           o.setParentId(null);
           o.setTopParentId(null);
           o.setUserId(null);
           o.setContent(null);
           o.setCreateTime(null);
       });
       commentMapper.insert(dbComment);
       // 测试 publishId 不匹配
       commentMapper.insert(cloneIgnoreId(dbComment, o -> o.setPublishId(null)));
       // 测试 parentId 不匹配
       commentMapper.insert(cloneIgnoreId(dbComment, o -> o.setParentId(null)));
       // 测试 topParentId 不匹配
       commentMapper.insert(cloneIgnoreId(dbComment, o -> o.setTopParentId(null)));
       // 测试 userId 不匹配
       commentMapper.insert(cloneIgnoreId(dbComment, o -> o.setUserId(null)));
       // 测试 content 不匹配
       commentMapper.insert(cloneIgnoreId(dbComment, o -> o.setContent(null)));
       // 测试 createTime 不匹配
       commentMapper.insert(cloneIgnoreId(dbComment, o -> o.setCreateTime(null)));
       // 准备参数
       CommentExportReqVO reqVO = new CommentExportReqVO();
       reqVO.setPublishId(null);
       reqVO.setParentId(null);
       reqVO.setTopParentId(null);
       reqVO.setUserId(null);
       reqVO.setContent(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<CommentDO> list = commentService.getCommentList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbComment, list.get(0));
    }

}