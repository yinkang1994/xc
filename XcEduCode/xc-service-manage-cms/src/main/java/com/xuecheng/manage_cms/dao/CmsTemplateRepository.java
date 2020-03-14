package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.framework.domain.cms.CmsTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @Author yinkang
 * @Date 2020/3/14 16:53
 */
public interface CmsTemplateRepository extends MongoRepository<CmsTemplate,String> {

    /**
     * 根据站点id查询模板列表
     * @param siteId 站点id
     * @return 站点对应的模板列表
     */
    public List<CmsTemplate> findBySiteId(String siteId);
}
