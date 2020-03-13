package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author yinkang
 * @Date 2020/3/11 12:52
 * cms页面管理持久层
 */
public interface CmsPageRespository extends MongoRepository<CmsPage,String> {
    /**
     * 根据站点id、页面名称、页面访问地址查询页面
     * @param siteId 站点id
     * @param pageName 页面名称
     * @param pageWebPath 页面访问地址
     * @return 页面
     */
    CmsPage findBySiteIdAndPageNameAndPageWebPath(String siteId,String pageName,String pageWebPath);
}
