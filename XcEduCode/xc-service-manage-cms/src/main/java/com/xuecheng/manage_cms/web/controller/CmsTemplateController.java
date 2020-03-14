package com.xuecheng.manage_cms.web.controller;

import com.xuecheng.api.cms.CmsTemplateControllerApi;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.manage_cms.service.CmsTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yinkang
 * @Date 2020/3/15 2:47
 */
@RestController
@RequestMapping("/cms/template")
public class CmsTemplateController implements CmsTemplateControllerApi {
    @Autowired
    private CmsTemplateService cmsTemplateService;

    @GetMapping("/findAll")
    @Override
    public QueryResponseResult findAll() {
        return cmsTemplateService.findAll();
    }

    @GetMapping("/findBySiteId/{siteId}")
    @Override
    public QueryResponseResult findBySiteId(@PathVariable(value = "siteId",required = true) String siteId) {
        return cmsTemplateService.findBySiteId(siteId);
    }
}
