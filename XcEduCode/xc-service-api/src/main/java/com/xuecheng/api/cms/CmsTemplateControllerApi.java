package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * cms配置管理接口
 * @Author yinkang
 * @Date 2020/3/14 16:50
 */

@Api(value = "CMS模板管理接口",description = "CMS模板管理接口，提供模板的查询全部、根据站点查询")
public interface CmsTemplateControllerApi {

    /**
     * 查询所有模板
     * @return 所有模板列表
     */
    @ApiOperation("查询所有模板")
    public QueryResponseResult findAll();


    /**
     * 根据站点id查询模板
     * @param siteId 站点id
     * @return 站点对应的模板列表
     */
    @ApiOperation("根据站点id查询模板")
    @ApiImplicitParam(name = "siteId", value = "站点id",
            required = true, paramType = "path", dataType = "string")
    public QueryResponseResult findBySiteId(String siteId);
}
