package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.domain.cms.request.QueryTemplateRequest;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author yinkang
 * @Date 2020/3/11 12:56
 * Cms站点管理业务层接口
 */
public interface CmsTemplateService {


    /**
     * 查询所有模板
     * @return
     */
    QueryResponseResult findAll();


    /**
     * 模板条件查询
     * @param page 当前页
     * @param size 每页显示数
     * @param queryTemplateRequest 查询条件
     * @return 模板列表
     */
   QueryResponseResult findList(int page, int size, QueryTemplateRequest queryTemplateRequest);

    /**
     * 根据站点id查询所有模板
     * @param siteId 站点id
     * @return 模板列表
     */
    QueryResponseResult findBySiteId(String siteId);

    /**
     * 模板文件上传
     * @param file 文件
     * @return 文件存入后对应的文件id
     */
    String upload(MultipartFile file);

    /**
     * 添加模板
     * @param cmsTemplate 模板信息
     * @return
     */
    ResponseResult add(CmsTemplate cmsTemplate);


    /**
     * 根据模板文件id删除模板文件
     * @param templateFileId 模板文件id
     * @return
     */
    ResponseResult delTemplateFileByFileId(String templateFileId);


    /**
     * 根据id删除模板
     * @param templateId 模板id
     * @return 该模板和操作结果
     */
    ResponseResult deleteByTemplateId(String templateId);
}
