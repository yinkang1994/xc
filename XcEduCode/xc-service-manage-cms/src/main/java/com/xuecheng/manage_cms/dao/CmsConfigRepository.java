package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author yinkang
 * @Date 2020/3/14 16:53
 */
public interface CmsConfigRepository extends MongoRepository<CmsConfig,String> {
}
