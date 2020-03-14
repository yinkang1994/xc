package com.xuecheng.manage_cms.web.controller;

import com.xuecheng.api.cms.CmsSiteControllerApi;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.manage_cms.service.CmsSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author yinkang
 * @Date 2020/3/11 12:48
 * cms页站点管理控制层
 */
@RestController
@RequestMapping("/cms/site")
public class CmsSiteController implements CmsSiteControllerApi {
    @Autowired
    private CmsSiteService cmsSiteService;

    @Override
    @GetMapping("/findAll")
    public QueryResponseResult findAll() {
        return cmsSiteService.findAll();
    }
}
