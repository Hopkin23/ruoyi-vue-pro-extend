package cn.iocoder.yudao.module.sns.controller.app.hashtag;

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

import cn.iocoder.yudao.module.sns.controller.app.hashtag.vo.*;
import cn.iocoder.yudao.module.sns.dal.dataobject.hashtag.HashtagDO;
import cn.iocoder.yudao.module.sns.convert.hashtag.HashtagConvert;
import cn.iocoder.yudao.module.sns.service.hashtag.HashtagService;

@Tag(name = "管理后台 - 话题标签")
@RestController
@RequestMapping("/sns/hashtag")
@Validated
public class HashtagController {

    @Resource
    private HashtagService hashtagService;

    @PostMapping("/create")
    @Operation(summary = "创建话题标签")
    @PreAuthorize("@ss.hasPermission('sns:hashtag:create')")
    public CommonResult<Long> createHashtag(@Valid @RequestBody HashtagCreateReqVO createReqVO) {
        return success(hashtagService.createHashtag(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新话题标签")
    @PreAuthorize("@ss.hasPermission('sns:hashtag:update')")
    public CommonResult<Boolean> updateHashtag(@Valid @RequestBody HashtagUpdateReqVO updateReqVO) {
        hashtagService.updateHashtag(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除话题标签")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('sns:hashtag:delete')")
    public CommonResult<Boolean> deleteHashtag(@RequestParam("id") Long id) {
        hashtagService.deleteHashtag(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得话题标签")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('sns:hashtag:query')")
    public CommonResult<HashtagRespVO> getHashtag(@RequestParam("id") Long id) {
        HashtagDO hashtag = hashtagService.getHashtag(id);
        return success(HashtagConvert.INSTANCE.convert(hashtag));
    }

    @GetMapping("/list")
    @Operation(summary = "获得话题标签列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('sns:hashtag:query')")
    public CommonResult<List<HashtagRespVO>> getHashtagList(@RequestParam("ids") Collection<Long> ids) {
        List<HashtagDO> list = hashtagService.getHashtagList(ids);
        return success(HashtagConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得话题标签分页")
    @PreAuthorize("@ss.hasPermission('sns:hashtag:query')")
    public CommonResult<PageResult<HashtagRespVO>> getHashtagPage(@Valid HashtagPageReqVO pageVO) {
        PageResult<HashtagDO> pageResult = hashtagService.getHashtagPage(pageVO);
        return success(HashtagConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出话题标签 Excel")
    @PreAuthorize("@ss.hasPermission('sns:hashtag:export')")
    @OperateLog(type = EXPORT)
    public void exportHashtagExcel(@Valid HashtagExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<HashtagDO> list = hashtagService.getHashtagList(exportReqVO);
        // 导出 Excel
        List<HashtagExcelVO> datas = HashtagConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "话题标签.xls", "数据", HashtagExcelVO.class, datas);
    }

}
