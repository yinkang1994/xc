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
@Api(value = "cms站点管理接口", description = "站点查询")
public interface CmsSiteControllerApi {

    /**
     * 查询全部站点
     * @return 站点列表
     */
    @ApiOperation("查询全部")
    public QueryResponseResult findAll();
}
