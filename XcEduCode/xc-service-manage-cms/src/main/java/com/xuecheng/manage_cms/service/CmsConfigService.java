package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsConfig;

/**
 * @Author yinkang
 * @Date 2020/3/14 16:55
 */
public interface CmsConfigService {
    /**
     * 根据id查询配置管理信息
     * @param id id
     * @return
     */
    public CmsConfig getConfigById(String id);
}
