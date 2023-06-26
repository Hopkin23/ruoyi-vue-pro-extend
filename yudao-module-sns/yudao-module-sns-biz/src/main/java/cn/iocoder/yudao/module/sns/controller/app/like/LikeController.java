package cn.iocoder.yudao.module.sns.controller.app.like;

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
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.USER_NOT_EXISTS;
import cn.iocoder.yudao.module.sns.controller.app.like.vo.*;
import cn.iocoder.yudao.module.sns.dal.dataobject.like.LikeDO;
import cn.iocoder.yudao.module.sns.convert.like.LikeConvert;
import cn.iocoder.yudao.module.sns.service.like.LikeService;

@Tag(name = "管理后台 - 点赞")
@RestController
@RequestMapping("/sns/like")
@Validated
public class LikeController {

    @Resource
    private LikeService likeService;

    @PostMapping("/create")
    @Operation(summary = "创建点赞")
    @PreAuthorize("@ss.hasPermission('sns:like:create')")
    public CommonResult<Long> createLike(@Valid @RequestBody LikeCreateReqVO createReqVO) {
        // 从上下文获取当前登录用户ID
        Long userId = Optional.ofNullable(SecurityFrameworkUtils.getLoginUserId())
                .orElseThrow(() -> ServiceExceptionUtil.exception(USER_NOT_EXISTS));
        createReqVO.setUserId(userId);
        return success(likeService.createLike(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新点赞")
    @PreAuthorize("@ss.hasPermission('sns:like:update')")
    public CommonResult<Boolean> updateLike(@Valid @RequestBody LikeUpdateReqVO updateReqVO) {
        likeService.updateLike(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除点赞")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('sns:like:delete')")
    public CommonResult<Boolean> deleteLike(@RequestParam("id") Long id) {
        likeService.deleteLike(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得点赞")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('sns:like:query')")
    public CommonResult<LikeRespVO> getLike(@RequestParam("id") Long id) {
        LikeDO like = likeService.getLike(id);
        return success(LikeConvert.INSTANCE.convert(like));
    }

    @GetMapping("/list")
    @Operation(summary = "获得点赞列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('sns:like:query')")
    public CommonResult<List<LikeRespVO>> getLikeList(@RequestParam("ids") Collection<Long> ids) {
        List<LikeDO> list = likeService.getLikeList(ids);
        return success(LikeConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得点赞分页")
    @PreAuthorize("@ss.hasPermission('sns:like:query')")
    public CommonResult<PageResult<LikeRespVO>> getLikePage(@Valid LikePageReqVO pageVO) {
        PageResult<LikeDO> pageResult = likeService.getLikePage(pageVO);
        return success(LikeConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出点赞 Excel")
    @PreAuthorize("@ss.hasPermission('sns:like:export')")
    @OperateLog(type = EXPORT)
    public void exportLikeExcel(@Valid LikeExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<LikeDO> list = likeService.getLikeList(exportReqVO);
        // 导出 Excel
        List<LikeExcelVO> datas = LikeConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "点赞.xls", "数据", LikeExcelVO.class, datas);
    }

}
