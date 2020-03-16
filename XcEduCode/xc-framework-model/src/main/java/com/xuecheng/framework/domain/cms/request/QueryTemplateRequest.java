package com.xuecheng.framework.domain.cms.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 模板查询实体类
 * @Author yinkang
 * @Date 2020/3/16 2:26
 */
@Data
public class QueryTemplateRequest {
    //站点id
    @ApiModelProperty("站点id")
    private String siteId;

    //页面id
    @ApiModelProperty("模板id")
    private String templateId;

    //页面名称
    @ApiModelProperty("模板名称")
    private String templateName;


}
