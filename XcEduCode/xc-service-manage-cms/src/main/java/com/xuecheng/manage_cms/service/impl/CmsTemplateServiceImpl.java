package com.xuecheng.manage_cms.service.impl;

import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms.dao.CmsTemplateRepository;
import com.xuecheng.manage_cms.service.CmsTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author yinkang
 * @Date 2020/3/15 2:33
 */
@Service
public class CmsTemplateServiceImpl implements CmsTemplateService {
    @Autowired
    private CmsTemplateRepository cmsTemplateRepository;

    @Override
    public QueryResponseResult findAll() {
        List<CmsTemplate> list = cmsTemplateRepository.findAll();
        QueryResult<CmsTemplate> queryResult = new QueryResult<>();
        queryResult.setList(list);
        queryResult.setTotal(list.size());
        return new QueryResponseResult(CommonCode.SUCCESS,queryResult);
    }

    @Override
    public QueryResponseResult findBySiteId(String siteId) {
        List<CmsTemplate> list = cmsTemplateRepository.findBySiteId(siteId);
        QueryResult<CmsTemplate> queryResult = new QueryResult<>();
        queryResult.setList(list);
        queryResult.setTotal(list.size());
        return new QueryResponseResult(CommonCode.SUCCESS,queryResult);
    }
}
