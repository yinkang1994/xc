package com.xuecheng.manage_cms.web.controller;

import com.xuecheng.api.cms.CmsTemplateControllerApi;
import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.domain.cms.request.QueryTemplateRequest;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.service.CmsTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author yinkang
 * @Date 2020/3/15 2:47
 */
@RestController
@RequestMapping("/cms/template")
public class CmsTemplateController implements CmsTemplateControllerApi {

    @Autowired
    private CmsTemplateService cmsTemplateService;


    @Override
    @GetMapping("/findAll")
    public QueryResponseResult findAll() {
        return cmsTemplateService.findAll();
    }

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable(value = "page",required = true) int page, @PathVariable(value = "size",required = true)int size, QueryTemplateRequest queryTemplateRequest) {
        return cmsTemplateService.findList(page,size,queryTemplateRequest);
    }


    @Override
    @PostMapping("/upload")
    public String upload(MultipartFile file) {
        return cmsTemplateService.upload(file);
    }


    @Override
    @PostMapping("/add")
    public ResponseResult add(@RequestBody CmsTemplate cmsTemplate) {
        return cmsTemplateService.add(cmsTemplate);
    }

    @Override
    @DeleteMapping("/delTemplateFileByFileId/{templateFileId}")
    public ResponseResult delTemplateFileByFileId(@PathVariable(value = "templateFileId",required = true) String templateFileId) {
        return cmsTemplateService.delTemplateFileByFileId(templateFileId);
    }


    @Override
    @DeleteMapping("/del/{templateId}")
    public ResponseResult deleteByTemplateId(@PathVariable(value = "templateId",required = true) String templateId) {
        return cmsTemplateService.deleteByTemplateId(templateId);
    }
}
