package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;

/**
 * @Author yinkang
 * @Date 2020/3/11 12:56
 * Cms页面管理业务层接口
 */
public interface CmsPageService {
    /**
     * 页面查询（条件加分页）
     * @param page 当前页
     * @param size 每页显示记录数
     * @param queryPageRequest  查询条件
     * @return 页面列表list和总数
     */
   QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);

    /**
     * 添加页面
     * @param cmsPage 新增页面对象
     * @return 页面对象和操作结果
     */
    CmsPageResult add(CmsPage cmsPage);

    /**
     * 根据页面id删除页面
     * @param 页面id
     * @return 操作结果
     */
    ResponseResult deleteByPageId(String pageId);

    /**
     * 修改页面
     * @param pageId 页面id
     * @param cmsPage 页面对象
     * @return 页面对象和操作结果
     */
    CmsPageResult edit(String pageId,CmsPage cmsPage);

    /**
     * 根据id查找页面
     * @param pageId 页面id
     * @return 页面和操作结果
     */
    CmsPageResult get(String pageId);
}
