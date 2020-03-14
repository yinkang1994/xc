package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.framework.domain.cms.CmsSite;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author yinkang
 * @Date 2020/3/14 16:53
 */
public interface CmsSiteRepository extends MongoRepository<CmsSite,String> {
}
