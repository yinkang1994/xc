package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;

/**
 * @Author yinkang
 * @Date 2020/3/11 12:56
 * Cms站点管理业务层接口
 */
public interface CmsSiteService {

    /**
     * 查询所有站点
     * @return 站点列表
     */
   QueryResponseResult findAll();

}
