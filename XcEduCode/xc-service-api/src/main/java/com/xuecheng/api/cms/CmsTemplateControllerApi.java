package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.domain.cms.request.QueryTemplateRequest;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

/**
 * cms配置管理接口
 *
 * @Author yinkang
 * @Date 2020/3/14 16:50
 */

@Api(value = "CMS模板管理接口", description = "CMS模板管理接口，提供模板的查询全部、根据站点查询")
public interface CmsTemplateControllerApi {


    /**
     * 查询所有模板
     *
     * @return
     */
    @ApiOperation("查询所有模板")
    public QueryResponseResult findAll();


    /**
     * cms模板查询(条件分页)
     *
     * @param page                 当前页
     * @param size                 显示条数
     * @param queryTemplateRequest 条件
     * @return 模板列表list和总数
     */
    @ApiOperation("条件分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页",
                    required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页显示条数",
                    required = true, paramType = "path", dataType = "int")

    })
    public QueryResponseResult findList(int page, int size, QueryTemplateRequest queryTemplateRequest);


    /**
     * 模板文件上传
     * @param file
     * @return
     */
    @ApiOperation("模板文件上传")
    @ApiImplicitParam(name = "file", value = "模板文件",
            required = true)
    public String upload(MultipartFile file);

    /**
     * 添加模板
     * @param cmsTemplate 模板信息
     * @return
     */
    @ApiOperation("添加模板")
    public ResponseResult add(CmsTemplate cmsTemplate);


    /**
     * 根据模板文件id删除模板文件
     * @param templateFileId 模板文件id
     * @return
     */
    @ApiOperation("根据模板文件id删除模板文件")
    @ApiImplicitParam(name = "templateFileId", value = "模板文件id",
            required = true, paramType = "path", dataType = "string")
    public ResponseResult delTemplateFileByFileId(String templateFileId);



    /**
     * 根据id删除模板
     * @param templateId 模板id
     * @return 该模板和操作结果
     */
    @ApiOperation("删除模板")
    @ApiImplicitParam(name = "templateId", value = "模板id",
            required = true, paramType = "path", dataType = "string")
    public ResponseResult deleteByTemplateId(String templateId);
}
