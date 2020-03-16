package com.xuecheng.manage_cms.service.impl;

import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.domain.cms.request.QueryTemplateRequest;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.dao.CmsSiteRepository;
import com.xuecheng.manage_cms.dao.CmsTemplateRepository;
import com.xuecheng.manage_cms.service.CmsTemplateService;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

/**
 * @Author yinkang
 * @Date 2020/3/15 2:33
 */
@Service
public class CmsTemplateServiceImpl implements CmsTemplateService {

    @Autowired
    private CmsSiteRepository cmsSiteRepository;
    @Autowired
    private CmsTemplateRepository cmsTemplateRepository;
    @Autowired
    private GridFsTemplate gridFsTemplate;


    @Override
    public QueryResponseResult findAll() {
        List<CmsTemplate> list = cmsTemplateRepository.findAll();
        QueryResult<CmsTemplate> queryResult = new QueryResult<>();
        queryResult.setList(list);
        queryResult.setTotal(list.size());
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    @Override
    public QueryResponseResult findList(int page, int size, QueryTemplateRequest queryTemplateRequest) {

        //没有传递页码和数目，则默认为page：1，size：20
        if (page < 0) {
            page = 1;
        }
        //mongodb默认第一页为0
        page = page - 1;

        if (size <= 0) {
            size = 20;
        }

        //构造分页对象
        Pageable pageable = PageRequest.of(page, size);


        //查询结果
        Page<CmsTemplate> all;

        //条件对象为空,直接查询
        if (queryTemplateRequest == null) {
            all = cmsTemplateRepository.findAll(pageable);
        } else {
            //条件对象不为空，则构建查询条件
            CmsTemplate ct = new CmsTemplate();
            if (StringUtils.isNotEmpty(queryTemplateRequest.getSiteId())) {
                ct.setSiteId(queryTemplateRequest.getSiteId());
            }
            if (StringUtils.isNotEmpty(queryTemplateRequest.getTemplateName())) {
                ct.setTemplateName(queryTemplateRequest.getTemplateName());
            }


            //构建匹配规则,模板名称模糊匹配
            ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("templateName", ExampleMatcher.GenericPropertyMatchers.contains());

            //查询条件对象
            Example<CmsTemplate> example = Example.of(ct, matcher);

            //执行查询
            all = cmsTemplateRepository.findAll(example, pageable);
        }

        List<CmsTemplate> list = all.getContent();
        if (list.size() > 0) {
            //根据站点id查询站点每个模板对应的站点名称，并赋值
            for (CmsTemplate cmsTemplate : list) {
                if(cmsTemplate.getSiteId()!=null){
                    Optional<CmsSite> optional = cmsSiteRepository.findById(cmsTemplate.getSiteId());
                    optional.ifPresent(cmsSite -> cmsTemplate.setSiteName(cmsSite.getSiteName()));
                }else {
                    cmsTemplate.setSiteName(null);
                }
            }
        }
        QueryResult<CmsTemplate> queryResult = new QueryResult<>();
        queryResult.setList(list);
        queryResult.setTotal(all.getTotalElements());
        System.out.println(queryResult.toString());
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    @Override
    public QueryResponseResult findBySiteId(String siteId) {
        List<CmsTemplate> list = cmsTemplateRepository.findBySiteId(siteId);
        QueryResult<CmsTemplate> queryResult = new QueryResult<>();
        queryResult.setList(list);
        queryResult.setTotal(list.size());
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }


    @Override
    public String upload(MultipartFile file) {
        //输入流
        InputStream inputStream=null;
        try {
            inputStream = file.getInputStream();
            byte[] bytes = file.getBytes();
            System.out.println(new String(bytes,"UTF-8"));
            ObjectId objectId = gridFsTemplate.store(inputStream, file.getName());
            return objectId.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @Override
    public ResponseResult add(CmsTemplate cmsTemplate) {
        CmsTemplate save = cmsTemplateRepository.save(cmsTemplate);
        return new ResponseResult() ;
    }

    @Override
    public ResponseResult delTemplateFileByFileId(String templateFileId) {
        gridFsTemplate.delete(Query.query(Criteria.where("_id").is(templateFileId)));
        return new ResponseResult();
    }


    @Override
    public ResponseResult deleteByTemplateId(String templateId) {
        Optional<CmsTemplate> op = cmsTemplateRepository.findById(templateId);
        if(op.isPresent()){
            CmsTemplate cmsTemplate = op.get();
            cmsTemplateRepository.delete(cmsTemplate);
            if(StringUtils.isNotEmpty(cmsTemplate.getTemplateFileId())){
                this.delTemplateFileByFileId(cmsTemplate.getTemplateFileId());
            }
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return ResponseResult.FAIL();
    }
}
