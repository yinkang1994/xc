package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.model.response.QueryResponseResult;

/**
 * @Author yinkang
 * @Date 2020/3/11 12:56
 * Cms站点管理业务层接口
 */
public interface CmsTemplateService {

    /**
     * 查询所有模板
     * @return 模板列表
     */
   QueryResponseResult findAll();

    /**
     * 根据站点id查询所有模板
     * @param siteId 站点id
     * @return 模板列表
     */
    QueryResponseResult findBySiteId(String siteId);

}
