package cn.iocoder.yudao.module.sns.controller.admin.publish;

import cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.USER_NOT_EXISTS;
import cn.iocoder.yudao.module.sns.controller.admin.publish.vo.*;
import cn.iocoder.yudao.module.sns.dal.dataobject.publish.PublishDO;
import cn.iocoder.yudao.module.sns.convert.publish.PublishConvert;
import cn.iocoder.yudao.module.sns.service.publish.PublishService;

@Tag(name = "管理后台 - 帖子")
@RestController
@RequestMapping("/sns/publish")
@Validated
public class PublishController {

    @Resource
    private PublishService publishService;

    @PostMapping("/create")
    @Operation(summary = "创建帖子")
    @PreAuthorize("@ss.hasPermission('sns:publish:create')")
    public CommonResult<Long> createPublish(@Valid @RequestBody PublishCreateReqVO createReqVO) {
        // 从上下文获取当前登录用户ID
        Long userId = Optional.ofNullable(SecurityFrameworkUtils.getLoginUserId())
                .orElseThrow(() -> ServiceExceptionUtil.exception(USER_NOT_EXISTS));
        createReqVO.setUserId(userId);
        return success(publishService.createPublish(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新帖子")
    @PreAuthorize("@ss.hasPermission('sns:publish:update')")
    public CommonResult<Boolean> updatePublish(@Valid @RequestBody PublishUpdateReqVO updateReqVO) {
        publishService.updatePublish(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除帖子")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('sns:publish:delete')")
    public CommonResult<Boolean> deletePublish(@RequestParam("id") Long id) {
        publishService.deletePublish(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得帖子")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('sns:publish:query')")
    public CommonResult<PublishRespVO> getPublish(@RequestParam("id") Long id) {
        return success(publishService.getPublish(id));
    }

    @GetMapping("/list")
    @Operation(summary = "获得帖子列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('sns:publish:query')")
    public CommonResult<List<PublishRespVO>> getPublishList(@RequestParam("ids") Collection<Long> ids) {
        List<PublishDO> list = publishService.getPublishList(ids);
        return success(PublishConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得帖子分页")
    @PreAuthorize("@ss.hasPermission('sns:publish:query')")
    public CommonResult<PageResult<PublishRespVO>> getPublishPage(@Valid PublishPageReqVO pageVO) {
        // todo 是否我已经点赞
        return success(publishService.getPublishPage(pageVO));
    }

    @GetMapping("/my-page")
    @Operation(summary = "获得帖子分页（我的发布）")
    @PreAuthorize("@ss.hasPermission('sns:publish:query')")
    public CommonResult<PageResult<PublishRespVO>> getPublishMyPage(@Valid PublishPageReqVO pageVO) {
        // 从上下文获取当前登录用户ID
        Long userId = Optional.ofNullable(SecurityFrameworkUtils.getLoginUserId())
                .orElseThrow(() -> ServiceExceptionUtil.exception(USER_NOT_EXISTS));
        pageVO.setUserId(userId);
        return success(publishService.getPublishPage(pageVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出帖子 Excel")
    @PreAuthorize("@ss.hasPermission('sns:publish:export')")
    @OperateLog(type = EXPORT)
    public void exportPublishExcel(@Valid PublishExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<PublishDO> list = publishService.getPublishList(exportReqVO);
        // 导出 Excel
        List<PublishExcelVO> datas = PublishConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "帖子.xls", "数据", PublishExcelVO.class, datas);
    }

}
