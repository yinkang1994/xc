package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @Author yinkang
 * @Date 2020/3/11 10:32
 * cms页面管理接口
 */
@Api(value = "cms页面管理接口", description = "页面的增删改查")
public interface CmsPageControllerApi {
    /**
     * cms页面查询(条件分页)
     *
     * @param page             当前页
     * @param size             显示条数
     * @param queryPageRequest 条件
     * @return 页面列表list和总数
     */
    @ApiOperation("条件分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页",
                    required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页显示条数",
                    required = true, paramType = "path", dataType = "int")

    })
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);


    @ApiOperation("根据id查找页面")
    @ApiImplicitParam(name = "pageId", value = "页面id",
            required = true, paramType = "path", dataType = "int")
    public CmsPageResult get(String pageId);

    /**
     * 新增页面
     * @param cmsPage 页面对象
     * @return 该页面和操作结果
     */
    @ApiOperation("新增页面")
    public CmsPageResult add(CmsPage cmsPage);


    /**
     * 根据id删除
     * @param pageId 页面id
     * @return 该页面和操作结果
     */
    @ApiOperation("删除页面")
    @ApiImplicitParam(name = "pageId", value = "页面id",
            required = true, paramType = "path", dataType = "string")
    public ResponseResult deleteByPageId(String pageId);

    /**
     * 修改页面
     * @param cmsPage 页面对象
     * @return 该页面和操作结果
     */
    @ApiOperation("修改页面")
    public CmsPageResult edit(CmsPage cmsPage);
}
